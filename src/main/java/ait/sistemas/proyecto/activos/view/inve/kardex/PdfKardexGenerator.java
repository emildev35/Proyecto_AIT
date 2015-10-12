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
		Integer rowsPerPage = new Double(Math.floor((kardex.getHeight() - 2 * kardex.getMargin())/ kardex.getRowHeight())).intValue() - 3;
		
		int r = kardex.getElemenos().length + kardex.getHeaderSize();
		
		PDPage page = new PDPage();
		page.setMediaBox(kardex.getPageSize());
		if (kardex.isLandscape()) {
			page.setRotation(90);
		}
		this.doc.addPage(page);
		PDPageContentStream contentStream = generateContentStream(doc, page, kardex);
		
		float tableTopY = kardex.isLandscape() ? kardex.getPageSize().getWidth() - kardex.getMargin() : kardex.getPageSize()
				.getHeight() - kardex.getMargin();
		float nextTextX = kardex.getMargin() + kardex.getCellMargin();
		
		tableTopY = kardex.isLandscape() ? kardex.getPageSize().getWidth() - kardex.getMargin()
				- (kardex.getRowHeight() * kardex.getHeaderSize()) : kardex.getPageSize().getHeight() - kardex.getMargin()
				- kardex.getRowHeight() - (kardex.getRowHeight() * kardex.getHeaderSize());
		
		writeHeader(contentStream, nextTextX, kardex);
		
		drawTableGrid(kardex, contentStream, tableTopY);
		
		float nextTextY = tableTopY - (kardex.getRowHeight() / 2)
				- ((kardex.getTextFont().getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * kardex.getFontSize()) / 4);
		
		for (int i = 0; i < kardex.getElemenos().length; i++) {
			if (kardex.getElemenos()[i][0] == null) {
				continue;
			}
			writeContentLine(kardex.getElemenos()[i], contentStream, nextTextX, nextTextY, kardex);
			nextTextY -= (kardex.getRowHeight() + kardex.getRowTitleHeight());
			nextTextX = kardex.getMargin() + kardex.getCellMargin();
		}
		// Tabla de Componentes
		nextTextY -= kardex.getRowHeight();
		drawTableComponentGrid(kardex, kardex.getComponentes(), contentStream, nextTextY);
		nextTextY -= kardex.getRowHeight() / 2;
		writeContentLineComponent(kardex.getColumnsComponentsNamesAsArray(), contentStream, nextTextX, nextTextY, kardex);
		contentStream.setFont(kardex.getTexttitleFont(), kardex.getFontSize());
		nextTextY -= kardex.getRowHeight();
		nextTextX = kardex.getMargin() + kardex.getCellMargin();
		r += 3;
		contentStream.setFont(kardex.getTextFont(), kardex.getFontSize());
		// Write content
		for (int i = 0; i < kardex.getComponentes().length; i++) {
			writeContentLineComponent(kardex.getComponentes()[i], contentStream, nextTextX, nextTextY, kardex);
			nextTextY -= kardex.getRowHeight();
			r++;
			if (r >= rowsPerPage) {
				contentStream.close();
				nextTextY = kardex.isLandscape() ? kardex.getPageSize().getWidth() - kardex.getMargin() : kardex.getPageSize()
						.getHeight() - kardex.getMargin();
				
				page = new PDPage();
				page.setMediaBox(kardex.getPageSize());
				if (kardex.isLandscape()) {
					page.setRotation(90);
				}
				r = 1;
				this.doc.addPage(page);
				
				contentStream = generateContentStream(doc, page, kardex);
				
				nextTextY -= kardex.getRowHeight();
				drawTableComponentGrid(kardex, kardex.getComponentes(), contentStream, nextTextY);
				nextTextY -= kardex.getRowHeight() / 2;
				writeContentLineComponent(kardex.getColumnsComponentsNamesAsArray(), contentStream, nextTextX, nextTextY, kardex);
				contentStream.setFont(kardex.getTexttitleFont(), kardex.getFontSize());
				nextTextY -= kardex.getRowHeight()*1.1;
			}
			nextTextX = kardex.getMargin() + kardex.getCellMargin();
		}
		// Tabla de Documentos
		nextTextY -= kardex.getRowHeight();
		
		drawTableDocumentGrid(kardex, kardex.getDocumentos(), contentStream, nextTextY);
		nextTextY -= kardex.getRowHeight() / 2;
		contentStream.setFont(kardex.getTexttitleFont(), kardex.getFontSize());
		writeContentLineDocument(kardex.getColumnsDocumentsNamesAsArray(), contentStream, nextTextX, nextTextY, kardex);
		nextTextY -= kardex.getRowHeight();
		nextTextX = kardex.getMargin() + kardex.getCellMargin();
		r += 3;
		contentStream.setFont(kardex.getTextFont(), kardex.getFontSize());
		// Write content
		for (int i = 0; i < kardex.getDocumentos().length; i++) {
			writeContentLineDocument(kardex.getDocumentos()[i], contentStream, nextTextX, nextTextY, kardex);
			nextTextY -= kardex.getRowHeight();
			r++;
			if (r >= rowsPerPage) {
				contentStream.close();
				nextTextY = kardex.isLandscape() ? kardex.getPageSize().getWidth() - kardex.getMargin() : kardex.getPageSize()
						.getHeight() - kardex.getMargin();
				page = new PDPage();
				page.setMediaBox(kardex.getPageSize());
				if (kardex.isLandscape()) {
					page.setRotation(90);
				}
				r = 1;
				this.doc.addPage(page);
				contentStream = generateContentStream(doc, page, kardex);
				
				
				nextTextY -= kardex.getRowHeight();
				drawTableDocumentGrid(kardex, kardex.getDocumentos(), contentStream, nextTextY);
				nextTextY -= kardex.getRowHeight() / 2;
				contentStream.setFont(kardex.getTexttitleFont(), kardex.getFontSize());
				writeContentLineDocument(kardex.getColumnsDocumentsNamesAsArray(), contentStream, nextTextX, nextTextY, kardex);
				nextTextY -= kardex.getRowHeight()*1.1;
			}
			nextTextX = kardex.getMargin() + kardex.getCellMargin();
		}
		contentStream.close();
		drawFooter(doc, kardex); 
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
			contentStream.showText(contenido != null && !contenido.equals("null") && !contenido.equals("null null null") ? contenido : "");
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
		for (int i = 0; i <= 1; i++) {
			
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(0), nextY);
			nextY -= table.getRowHeight();
		}
		
		// Modificado solo pra el titulo para grilla modificar por
		
		float nextX = table.getMargin();
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < table.getColumns_componentes().size(); i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableTopY - table.getRowHeight());
			nextX += table.getColumns_componentes().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableTopY - table.getRowHeight());
	}
	
	private void drawTableDocumentGrid(Kardex table, String[][] currentPageContent, PDPageContentStream contentStream,
			float tableTopY) throws IOException {
		// Draw row lines
		float nextY = tableTopY;
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i <= 1; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(i), nextY);
			nextY -= table.getRowHeight();
		}
		
		
		float nextX = table.getMargin();
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < table.getColumns_documentos().size(); i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableTopY - table.getRowHeight());
			nextX += table.getColumns_documentos().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableTopY - table.getRowHeight());
	}
	
	
	@SuppressWarnings("static-access")
	private void drawFooter(PDDocument doc, Kardex kardex) {
		int numofPages = doc.getPages().getCount();
		int actual_row = 1;
		for (PDPage page : doc.getPages()) {
			try {
				PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);
				contentStream.drawLine(kardex.getMargin(), kardex.getMargin(), kardex.getWidth(1), kardex.getMargin());
				contentStream.beginText();
				contentStream.setFont(kardex.getFooterFont(), kardex.getFontSizefooter());
				contentStream.moveTextPositionByAmount(kardex.getMargin(), kardex.getMargin() - 7);
				contentStream.showText(new String().format("Página %d de %d", actual_row, numofPages));
				contentStream.endMarkedContent();
				contentStream.close();
			} catch (IOException e) {
			}
			actual_row++;
		}
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
