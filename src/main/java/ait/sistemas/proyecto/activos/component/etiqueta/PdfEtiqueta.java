package ait.sistemas.proyecto.activos.component.etiqueta;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.eclipse.persistence.internal.oxm.StrBuffer;

import ait.sistemas.proyecto.common.component.CodeBar;
import ait.sistemas.proyecto.common.report.pdf.Table;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.ui.UI;
import com.vaadin.ui.PopupView.Content;

/**
 * Clase encargada de la generacion del documentos que contiene todos los
 * codigos de barras de los activos listos para su impresion
 * 
 * @author franzemil
 *
 */
public class PdfEtiqueta {
	
	private PDDocument doc;
	
	private int intNumberPages = 5;
	
	public boolean generatePDF(Etiqueta etiqueta, String savePath) throws IOException {
		boolean result = false;
		doc = null;
		try {
			doc = new PDDocument();
			drawDocument(doc, etiqueta);
			doc.save(savePath);
			result = true;
		} finally {
			if (doc != null) {
				doc.close();
			}
		}
		return result;
	}
	
	public void drawDocument(PDDocument doc, Etiqueta etiqueta) throws IOException {
		float nextY;
		float nextX;
		for (CodeBar codigo : etiqueta.getCodigos()) {
			PDPage page = generatePage(doc, etiqueta);
			PDPageContentStream contentStream = generateContentStream(doc, page, etiqueta);
			PDImageXObject pdImage = PDImageXObject.createFromFile(codigo.getPathCode(), doc);
			contentStream.drawImage(pdImage, 150, 150, etiqueta.getWidthCode(), etiqueta.getHeigthCode());
			contentStream.beginText();
			contentStream.showText(codigo.getNombre());
			contentStream.endText();
			contentStream.close();
		}
		
	}
	
	public String separeString(String data){
		String[] row = new String[data.length()/40];
		for (int i = 0; i <= data.length()/40; i++) {
			row[i] = data.substring(i, 40);
		}
		return null;
	}
	
	// Draws current page table grid and border lines and content
	private void drawCurrentPage(Etiqueta etiqueta, PDPageContentStream contentStream) throws IOException {
		
	}
	
	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX, float nextTextY,
			Table table) throws IOException {
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		for (int i = 0; i < table.getNumberOfColumns(); i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.showText(text != null ? text : "");
			contentStream.endText();
			nextTextX += table.getColumns().get(i).getWidth();
		}
	}
	
	private void drawTableGrid(Table table, String[][] currentPageContent, PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		// Draw row lines
		float nextY = tableTopY;
		
		// Modificado para solo el tititulo para grilla completa modificar por
		// for (int i = 0; i <= currentPageContent.length + 1; i++) {
		for (int i = 0; i <= 1; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin() + table.getWidth(), nextY);
			nextY -= table.getRowHeight();
		}
		
		// Modificado solo pra el titulo para grilla modificar por
		// final float tableYLength = table.getRowHeight() +
		// (table.getRowHeight() * currentPageContent.length);
		// final float tableBottomY = tableTopY - tableYLength;
		final float tableBottomY = tableTopY - table.getRowHeight();
		
		float nextX = table.getMargin();
		
		// Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < table.getNumberOfColumns(); i++) {
			contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
			nextX += table.getColumns().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
	}
	
	private String[][] getContentForCurrentPage(Table table, Integer rowsPerPage, int pageCount) {
		int startRange = pageCount * rowsPerPage;
		
		int endRange = (pageCount * rowsPerPage) + rowsPerPage;
		
		if (pageCount == 0) {
			endRange = (pageCount * rowsPerPage) - table.getHeaderSize() + rowsPerPage;
		}
		if (endRange > table.getNumberOfRows()) {
			endRange = table.getNumberOfRows();
		}
		return Arrays.copyOfRange(table.getContent(), startRange, endRange);
	}
	
	private PDPage generatePage(PDDocument doc, Etiqueta table) {
		PDPage page = new PDPage();
		page.setMediaBox(table.getPageSize());
		doc.addPage(page);
		return page;
	}
	
	private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, Etiqueta table) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, false, false);
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		return contentStream;
	}
	
	private void writeFooter(PDPageContentStream contentStream, float nextTextX, float nextTextY, Table table, int pagecount)
			throws IOException {
		
		contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());
		
		nextTextY = table.isLandscape() ? table.getMargin() : table.getMargin();
		nextTextY -= (table.getRowHeight() * 2.5);
		contentStream.drawLine(nextTextX, (nextTextY + table.getRowHeight()), table.getWidth(),
				(nextTextY + table.getRowHeight()));
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		
		nextTextY += table.getRowHeight() - table.getMargin();
		nextTextX += table.getWidth();
		String footertext = String.format("Página %d de %d", (pagecount + 1), this.intNumberPages);
		contentStream.showText(footertext);
		contentStream.endText();
		
	}
	
	private void writeHeader(PDPageContentStream contentStream, float nextTextX, float nextTextY, Table table) throws IOException {
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		
		contentStream.setFont(table.getHeaderFont(), table.getFontSizeheader());
		
		float nextTextXCopy = nextTextX;
		nextTextY = table.isLandscape() ? table.getPageSize().getWidth() - table.getMargin() : table.getPageSize().getHeight()
				- table.getMargin();
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		
		contentStream.showText("Dependencia : " + usuario.getDependecia());
		
		contentStream.endText();
		
		Date date = new Date();
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = fechaHora.format(date);
		
		nextTextX = table.getWidth() - table.getFontSizeheader() * 2;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Fecha : " + fecha);
		contentStream.endText();
		
		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight() * 0.75;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Unidad : " + usuario.getUnidad());
		contentStream.endText();
		
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		String strhora = hora.format(date);
		
		nextTextX = table.getWidth() - table.getFontSizeheader() * 2;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Hora : " + strhora);
		contentStream.endText();
		
		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight() * 0.75;
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Usuario : " + usuario.getFull_name());
		contentStream.endText();
		
		contentStream.setFont(table.getTitleFont(), table.getFontSizetitle());
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		long text_width = (long) ((table.getTitleFont().getStringWidth(table.getTitle()) / 1000.0f) * table.getFontSizetitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2) - (text_width / 2) + (table.getMargin() / 2), nextTextY);
		contentStream.showText(table.getTitle());
		contentStream.endText();
		
		contentStream.setFont(table.getSubtitleFont(), table.getFontSizesubtitle());
		nextTextY -= table.getRowHeight();
		
		// contentStream.beginText();
		// long sub_width = (long)
		// ((table.getSubtitleFont().getStringWidth(table.getSubtitle()) /
		// 1000.0f) * table
		// .getFontSizesubtitle());
		// contentStream.moveTextPositionByAmount((table.getWidth() / 2) -
		// (sub_width / 2) + (table.getMargin() / 2), nextTextY);
		// contentStream.showText(table.getSubtitle());
		// contentStream.endText();
		
	}
}
