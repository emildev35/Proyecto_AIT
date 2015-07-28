package ait.sistemas.proyecto.common.report;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.ui.UI;

@SuppressWarnings("deprecation")
public class PDFTableGenerator {

	private PDDocument doc;

	private int intNumberPages = 5;

	// Generates document from Table object
	public boolean generatePDF(Table table, String savePath) throws IOException {
		boolean result = false;
		doc = null;
		try {
			doc = new PDDocument();
			drawTable(doc, table);
			doc.save(savePath);
			result = true;
		} finally {
			if (doc != null) {
				doc.close();
			}
		}
		return result;
	}

	// Configures basic setup for the table and draws it page by page
	public void drawTable(PDDocument doc, Table table) throws IOException {
		// Calculate pagination
		Integer rowsPerPage = new Double(Math.floor(table.getHeight()
				/ table.getRowHeight())).intValue() - 1;
		Integer numberOfPages = new Double(Math.ceil((table.getNumberOfRows()
				.floatValue() + table.getHeaderSize()) / rowsPerPage))
				.intValue();
		this.intNumberPages = numberOfPages;
		// Generate each page, get the content and draw it
		for (int pageCount = 0; pageCount < numberOfPages; pageCount++) {
			PDPage page = generatePage(doc, table);
			PDPageContentStream contentStream = generateContentStream(doc,
					page, table);
			String[][] currentPageContent;
			currentPageContent = getContentForCurrentPage(table, rowsPerPage,
					pageCount);
			drawCurrentPage(table, currentPageContent, contentStream, pageCount);
		}
	}

	// Draws current page table grid and border lines and content
	private void drawCurrentPage(Table table, String[][] currentPageContent,
			PDPageContentStream contentStream, int pageCount)
			throws IOException {
		float tableTopY;

		tableTopY = table.isLandscape() ? table.getPageSize().getWidth()
				- table.getMargin() : table.getPageSize().getHeight()
				- table.getMargin();

		if (pageCount == 0) {
			tableTopY = table.isLandscape() ? table.getPageSize().getWidth()
					- table.getMargin()
					- (table.getRowHeight() * table.getHeaderSize()) : table
					.getPageSize().getHeight()
					- table.getMargin()
					- table.getRowHeight()
					- (table.getRowHeight() * table.getHeaderSize());

		}
		// Draws grid and borders
		drawTableGrid(table, currentPageContent, contentStream, tableTopY);

		// Position cursor to start drawing content
		float nextTextX = table.getMargin() + table.getCellMargin();
		// Calculate center alignment for text in cell considering font height
		float nextTextY = tableTopY
				- (table.getRowHeight() / 2)
				- ((table.getTextFont().getFontDescriptor()
						.getFontBoundingBox().getHeight() / 1000 * table
						.getFontSize()) / 4);

		// Write column headers
		writeContentLine(table.getColumnsNamesAsArray(), contentStream,
				nextTextX, nextTextY, table);
		nextTextY -= table.getRowHeight();

		nextTextX = table.getMargin() + table.getCellMargin();

		// Write content
		for (int i = 0; i < currentPageContent.length; i++) {
			writeContentLine(currentPageContent[i], contentStream, nextTextX,
					nextTextY, table);
			nextTextY -= table.getRowHeight();
			nextTextX = table.getMargin() + table.getCellMargin();
		}

		if (pageCount == 0) {
			writeHeader(contentStream, nextTextX, nextTextY, table);
		}
		// Se dibuja el footer
		writeFooter(contentStream, nextTextX, nextTextY, table, pageCount);
		contentStream.close();
	}

	// Writes the content for one line
	private void writeContentLine(String[] lineContent,
			PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {
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

	private void drawTableGrid(Table table, String[][] currentPageContent,
			PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		// Draw row lines
		float nextY = tableTopY;
		
		//Modificado para solo el tititulo para grilla completa modificar por
		//for (int i = 0; i <= currentPageContent.length + 1; i++) {
		for (int i = 0; i <= 1; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin()
					+ table.getWidth(), nextY);
			nextY -= table.getRowHeight();
		}

		// Modificado solo pra el titulo para grilla modificar por
		// final float tableYLength = table.getRowHeight() + (table.getRowHeight() * currentPageContent.length);
		// final float tableBottomY = tableTopY - tableYLength;
		final float tableBottomY = tableTopY - table.getRowHeight();
		
		float nextX = table.getMargin();
		
		//Modificado para solo el tititulo para grilla completa modificar por
		for (int i = 0; i < table.getNumberOfColumns(); i++) {	
			contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
			nextX += table.getColumns().get(i).getWidth();
		}
		contentStream.drawLine(nextX, tableTopY, nextX, tableBottomY);
	}

	private String[][] getContentForCurrentPage(Table table,
			Integer rowsPerPage, int pageCount) {
		int startRange = pageCount * rowsPerPage;

		int endRange = (pageCount * rowsPerPage) + rowsPerPage;

		if (pageCount == 0) {
			endRange = (pageCount * rowsPerPage) - table.getHeaderSize()
					+ rowsPerPage;
		}
		if (endRange > table.getNumberOfRows()) {
			endRange = table.getNumberOfRows();
		}
		return Arrays.copyOfRange(table.getContent(), startRange, endRange);
	}

	private PDPage generatePage(PDDocument doc, Table table) {
		PDPage page = new PDPage();
		page.setMediaBox(table.getPageSize());
		page.setRotation(table.isLandscape() ? 90 : 0);
		doc.addPage(page);
		return page;
	}

	private PDPageContentStream generateContentStream(PDDocument doc,
			PDPage page, Table table) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page,
				false, false);
		if (table.isLandscape()) {
			contentStream.concatenate2CTM(0, 1, -1, 0, table.getPageSize()
					.getWidth(), 0);
		}
		contentStream.setFont(table.getTextFont(), table.getFontSize());
		return contentStream;
	}

	private void writeFooter(PDPageContentStream contentStream,
			float nextTextX, float nextTextY, Table table, int pagecount)
			throws IOException {

		contentStream.setFont(table.getFooterFont(), table.getFontSizefooter());

		nextTextY = table.isLandscape() ? table.getMargin() : table.getMargin();
		nextTextY -= (table.getRowHeight() * 2.5);
		contentStream.drawLine(nextTextX, (nextTextY + table.getRowHeight()),
				nextTextX * table.getNumberOfColumns(),
				(nextTextY + table.getRowHeight()));

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		nextTextY += table.getRowHeight() - table.getMargin();
		nextTextX += table.getWidth();
		String footertext = String.format("Página %d de %d", (pagecount + 1),
				this.intNumberPages);
		contentStream.showText(footertext);
		contentStream.endText();

	}

	private void writeHeader(PDPageContentStream contentStream,
			float nextTextX, float nextTextY, Table table) throws IOException {
		SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");

		contentStream.setFont(table.getHeaderFont(), table.getFontSizeheader());

		float nextTextXCopy = nextTextX;
		nextTextY = table.isLandscape() ? table.getPageSize().getWidth()
				- table.getMargin() : table.getPageSize().getHeight()
				- table.getMargin();

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		contentStream.showText("Dependencia : " + usuario.getDependecia());

		contentStream.endText();

		Date date = new Date();
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
		String fecha = fechaHora.format(date);
 
		
		nextTextX += 400;

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Fecha : " + fecha);
		contentStream.endText();

		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Unidad : " + usuario.getUnidad());
		contentStream.endText();


		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		String strhora = hora.format(date);
		
		nextTextX += 400;

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Hora : " + strhora);
		contentStream.endText();

		nextTextX = nextTextXCopy;
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Usuario : " + usuario.getFull_name());
		contentStream.endText();

		contentStream.setFont(table.getTitleFont(), table.getFontSizetitle());
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		long text_width = (long) ((table.getTitleFont().getStringWidth(
				table.getTitle()) / 1000.0f) * table.getFontSizetitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2)
				- (text_width / 2) + (table.getMargin() / 2), nextTextY);
		contentStream.showText(table.getTitle());
		contentStream.endText();

		contentStream.setFont(table.getSubtitleFont(),
				table.getFontSizesubtitle());
		nextTextY -= table.getRowHeight();
		contentStream.beginText();
		long sub_width = (long) ((table.getSubtitleFont().getStringWidth(
				table.getSubtitle()) / 1000.0f) * table.getFontSizesubtitle());
		contentStream.moveTextPositionByAmount((table.getWidth() / 2)
				- (sub_width / 2) + (table.getMargin() / 2), nextTextY);
		contentStream.showText(table.getSubtitle());
		contentStream.endText();

	}
}
