package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import ait.sistemas.proyecto.common.report.Util;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.ui.UI;

@SuppressWarnings("deprecation")
public class PdfKardexGenerator {
	
	private PDDocument doc;
	
	private int intNumberPages = 5;
	
	// Generates document from kardex object
	public boolean generatePDF(Kardex kardex, String savePath) throws IOException {
		boolean result = false;
		doc = null;
		try {
			doc = new PDDocument();
			drawkardex(doc, kardex);
			doc.save(savePath);
			result = true;
		} finally {
			if (doc != null) {
				doc.close();
			}
		}
		return result;
	}
	
	public void drawkardex(PDDocument doc, Kardex kardex) throws IOException {
		// Calcula la cantidad de filas por pagina
		Integer rowsPerPage = new Double(Math.floor(kardex.getHeight() / kardex.getRowHeight())).intValue() - 1;
		// Calcula la cantidad de hojas necesarias
		Integer numberOfPages = new Double(Math.ceil((kardex.getNumberOfRows().floatValue() + kardex.getHeaderSize())
				/ rowsPerPage)).intValue();
		
		this.intNumberPages = numberOfPages;
		for (int pageCount = 0; pageCount < numberOfPages; pageCount++) {
			PDPage page = new PDPage();
			page.setMediaBox(kardex.getPageSize());
			if (kardex.isLandscape()) {
				page.setRotation(90);
			}
			this.doc.addPage(page);
			PDPageContentStream contentStream = generateContentStream(doc, page, kardex);
			drawCurrentPage(kardex, contentStream, pageCount);
		}
		
	}
	
	// Draws current page table grid and border lines and content
	private void drawCurrentPage(Kardex table, PDPageContentStream contentStream, int pageCount) throws IOException {
		float tableTopY;
		
		tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight()
				- table.getMargin();
		
		float nextTextX = table.getMargin() + table.getCellMargin();
		if (pageCount == 0) {
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin()
					- (table.getRowHeight() * table.getHeaderSize()) : table.getPageSize().getHeight() - table.getMargin()
					- table.getRowHeight() - (table.getRowHeight() * table.getHeaderSize());
			
			writeHeader(contentStream, nextTextX, table);
		}
		
		drawTableGrid(table, contentStream, tableTopY);
		// Calculate center alignment for text in cell considering font
		// height
		float nextTextY = tableTopY - (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * table.getFontSize()) / 4);
		// nextTextY -= table.getRowHeight();
		
		if (pageCount == 0) {
			// Write content
			for (int i = 0; i < table.getElemenos().length; i++) {
				if (table.getElemenos()[i][0] == null) {
					continue;
				}
				writeContentLine(table.getElemenos()[i], contentStream, nextTextX, nextTextY, table);
				nextTextY -= (table.getRowHeight() + table.getRowTitleHeight());
				nextTextX = table.getMargin() + table.getCellMargin();
			}
			
		}
		// Tabla de Componentes
		nextTextY -= table.getRowHeight();
		drawTableComponentGrid(table, table.getComponentes(), contentStream, nextTextY);
		nextTextY -= table.getRowHeight() / 2;
		writeContentLineComponent(table.getColumnsComponentsNamesAsArray(), contentStream, nextTextX, nextTextY, table);
		contentStream.setFont(table.getTexttitleFont(), table.getFontSize());
		nextTextY -= table.getRowHeight();
		nextTextX = table.getMargin() + table.getCellMargin();
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		// Write content
		for (int i = 0; i < table.getComponentes().length; i++) {
			writeContentLineComponent(table.getComponentes()[i], contentStream, nextTextX, nextTextY, table);
			nextTextY -= table.getRowHeight();
			nextTextX = table.getMargin() + table.getCellMargin();
		}
		// Tabla de Documentos
		nextTextY -= table.getRowHeight();
		
		drawTableDocumentGrid(table, table.getDocumentos(), contentStream, nextTextY);
		nextTextY -= table.getRowHeight() / 2;
		contentStream.setFont(table.getTexttitleFont(), table.getFontSize());
		writeContentLineDocument(table.getColumnsDocumentsNamesAsArray(), contentStream, nextTextX, nextTextY, table);
		nextTextY -= table.getRowHeight();
		nextTextX = table.getMargin() + table.getCellMargin();
		
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		// Write content
		for (int i = 0; i < table.getDocumentos().length; i++) {
			writeContentLineDocument(table.getDocumentos()[i], contentStream, nextTextX, nextTextY, table);
			nextTextY -= table.getRowHeight();
			nextTextX = table.getMargin() + table.getCellMargin();
		}
		writeFooter(contentStream, nextTextX, 0, table, pageCount);
		contentStream.close();
	}
	
	/*
	 * // Writes the content for one line private void
	 * writeContentLine(PDPageContentStream contentStream, float nextTextX,
	 * float nextTextY, Kardex table) throws IOException {
	 * contentStream.setFont(table.getTextFont(), table.getFontSize()); for (int
	 * i = 0; i < 10; i++) { String text = "kim"; contentStream.beginText();
	 * contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
	 * contentStream.showText(text != null ? text : "");
	 * contentStream.endText(); nextTextX += 50; } }
	 */
	private void drawTableGrid(Kardex table, PDPageContentStream contentStream, float tableTopY) throws IOException {
		// Draw row lines
		float nextY = tableTopY;
		
		for (int i = 0; i < table.getElemenos().length; i++) {
			if (table.getElemenos()[i][0] == null) {
				continue;
			}
			contentStream.drawLine(table.getMargin(), nextY, (table.getMargin() + table.getWidth(i)), nextY);
			
			final float tableYLength = table.getRowHeight() + table.getRowTitleHeight();
			final float tableBottomY = nextY - tableYLength;
			
			float nextX = table.getMargin();
			
			for (int j = 0; j < table.getElemenos()[i].length; j++) {
				if (table.getElemenos()[i][j] == null) {
					continue;
				}
				contentStream.drawLine(nextX, nextY, nextX, tableBottomY);
				nextX += table.getElemenos()[i][j].getAncho();
				contentStream.drawLine(nextX, nextY, nextX, tableBottomY);
			}
			
			nextY -= table.getRowHeight();
			contentStream.drawLine(table.getMargin(), nextY, (table.getMargin() + table.getWidth(i)), nextY);
			nextY -= table.getRowTitleHeight();
			contentStream.drawLine(table.getMargin(), nextY, (table.getMargin() + table.getWidth(i)), nextY);
		}
		
	}
	
	// Writes the content for one line
	private void writeContentLine(KardexElement[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Kardex table) throws IOException {
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		for (int i = 0; i < lineContent.length; i++) {
			if (lineContent[i] == null) {
				continue;
			}
			
			contentStream.setFont(table.getTexttitleFont(), table.getFontSizetexttitle());
			String titulo = lineContent[i].getTitulo();
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(titulo != null && !titulo.equals("null") ? titulo : "");
			contentStream.endText();
			
			contentStream.setFont(table.getTextFont(), table.getFontSize());
			String contenido = lineContent[i].getContenido();
			contentStream.beginText();
			if (Util.isDate(contenido, "yyyy-MM-dd") || Util.isNumber(contenido)) {
				contentStream.moveTextPositionByAmount(
						Util.justificar(contenido, nextTextX + lineContent[i].getAncho(), table.getTextFont(),
								table.getFontSize()), (nextTextY - table.getRowTitleHeight()));
			} else {
				contentStream.moveTextPositionByAmount(nextTextX, (nextTextY - table.getRowTitleHeight()));
			}
			if (Util.isInt(contenido)) {
			} else {
				if (Util.isFloat(contenido)) {
					contenido = Util.numberfloatFormat(contenido);
				} else {
					if (Util.isNumber(contenido)) {
						contenido = Util.numberFormat(contenido);
					}
				}
			}
			contentStream.showText(contenido != null && !titulo.equals("null") ? contenido : "");
			contentStream.endText();
			
			nextTextX += lineContent[i].getAncho();
		}
	}
	
	private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, Kardex table) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, false, false);
		if (table.isLandscape()) {
			contentStream.concatenate2CTM(0, 1, -1, 0, table.getPageSize().getWidth(), 0);
		}
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		return contentStream;
	}
	
	private void writeFooter(PDPageContentStream contentStream, float nextTextX, float nextTextY, Kardex table, int pagecount)
			throws IOException {
		
		contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
		
		nextTextY = table.isLandscape() ? table.getMargin() : table.getMargin();
		nextTextY -= (table.getRowHeight() * 2.5);
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		
		nextTextY += table.getRowHeight() - table.getMargin();
		
		String footertext = String.format("Página %d de %d", (pagecount + 1), intNumberPages);
		contentStream.showText(footertext);
		contentStream.endText();
		
	}
	
	// Writes the content for one line
	private void writeContentLineDocument(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Kardex table) throws IOException {
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		for (int i = 0; i < table.getColumns_documentos().size(); i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null && !text.equals("null") ? text : "");
			contentStream.endText();
			nextTextX += table.getColumns_documentos().get(i).getWidth();
		}
	}
	
	private void writeContentLineComponent(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Kardex table) throws IOException {
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		for (int i = 0; i < table.getColumns_componentes().size(); i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			nextTextX += table.getColumns_componentes().get(i).getWidth();
		}
	}
	
	private void drawTableComponentGrid(Kardex table, String[][] currentPageContent, PDPageContentStream contentStream,
			float tableTopY) throws IOException {
		// Draw row lines
		float nextY = tableTopY;
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i <= table.getNumberOfRowsComponentes() + 1; i++) {
			
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(i), nextY);
			nextY -= table.getRowHeight();
		}
		
		// Modificado solo pra el titulo para grilla modificar por
		final float tableYLength = table.getRowHeight() + (table.getRowHeight() * currentPageContent.length);
		final float tableBottomY = tableTopY - tableYLength;
		
		float nextX = table.getMargin();
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < table.getColumns_componentes().size(); i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
			nextX += table.getColumns_componentes().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
	}
	
	private void drawTableDocumentGrid(Kardex table, String[][] currentPageContent, PDPageContentStream contentStream,
			float tableTopY) throws IOException {
		// Draw row lines
		float nextY = tableTopY;
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i <= table.getNumberOfRowsDocumentos() + 1; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(i), nextY);
			nextY -= table.getRowHeight();
		}
		
		// Modificado solo pra el titulo para grilla modificar por
		final float tableYLength = table.getRowHeight() + (table.getRowHeight() * currentPageContent.length);
		final float tableBottomY = tableTopY - tableYLength;
		
		float nextX = table.getMargin();
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < table.getColumns_documentos().size(); i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
			nextX += table.getColumns_documentos().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
	}
	
	private void writeHeader(PDPageContentStream contentStream, float nextTextX, Kardex table) throws IOException {
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		
		contentStream.setFont(table.getHeaderFont(), table.getFontSizeheader());
		
		float nextTextXCopy = nextTextX;
		float nextTextY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize()
				.getHeight() - table.getMargin();
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		
		contentStream.showText(usuario.getDependecia());
		
		contentStream.endText();
		
		Date date = new Date();
		DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = fechaHora.format(date);
		
		nextTextX = table.getWidth(0) - table.getMargin();
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Fecha : " + fecha);
		contentStream.endText();
		
		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight() * 0.70;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(usuario.getUnidad());
		contentStream.endText();
		
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		String strhora = hora.format(date);
		
		nextTextX = table.getWidth(0) - table.getMargin();
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Hora : " + strhora);
		contentStream.endText();
		
		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight() * 0.70;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText(usuario.getFull_name());
		contentStream.endText();
		
		contentStream.setFont(table.getTitleFont(), table.getFontSizetitle());
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		long text_width = (long) ((table.getTitleFont().getStringWidth(table.getTitle()) / 1000.0f) * table.getFontSizetitle());
		contentStream.moveTextPositionByAmount((table.getWidth(0) / 2) - (text_width / 2) + (table.getMargin() / 2), nextTextY);
		contentStream.showText(table.getTitle());
		contentStream.endText();
		
		contentStream.setFont(table.getSubtitleFont(), table.getFontSizesubtitle());
		nextTextY -= table.getRowHeight();
		// Descomentar para habilidar subtitulos
		
		contentStream.beginText();
		long sub_width = (long) ((table.getSubtitleFont().getStringWidth(table.getSubtitle()) / 1000.0f) * table
				.getFontSizesubtitle());
		contentStream.moveTextPositionByAmount((table.getWidth(0) / 2) - (sub_width / 2) + (table.getMargin() / 2), nextTextY);
		contentStream.showText(table.getSubtitle());
		contentStream.endText();
		
	}
}
