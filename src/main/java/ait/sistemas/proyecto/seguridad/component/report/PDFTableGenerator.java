package ait.sistemas.proyecto.seguridad.component.report;

import java.io.IOException;
import java.util.Arrays;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

@SuppressWarnings("deprecation")
public class PDFTableGenerator {

	private PDDocument doc;

	// Generates document from Table object
	public PDDocument generatePDF(Table table) throws IOException {
		doc = null;
		try {
			doc = new PDDocument();
			drawTable(doc, table);
			doc.save("Documentos/documento.pdf");
		} finally {
			if (doc != null) {
				doc.close();
			}
		}
		return doc;
	}

	// Configures basic setup for the table and draws it page by page
	public void drawTable(PDDocument doc, Table table) throws IOException {
		// Calculate pagination
		Integer rowsPerPage = new Double(Math.floor(table.getHeight()
				/ table.getRowHeight())).intValue() - 1;
		Integer numberOfPages = new Double(Math.ceil((table.getNumberOfRows()
				.floatValue() + 8) / rowsPerPage)).intValue();

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
					- table.getMargin() - (table.getRowHeight() * 8) : table
					.getPageSize().getHeight()
					- table.getMargin()
					- table.getRowHeight() - (table.getRowHeight() * 8);

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
		writeFooter(contentStream, nextTextX, nextTextY, table);
		contentStream.close();
	}

	// Writes the content for one line
	private void writeContentLine(String[] lineContent,
			PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Table table) throws IOException {
		for (int i = 0; i < table.getNumberOfColumns(); i++) {
			String text = lineContent[i];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
			contentStream.drawString(text != null ? text : "");
			contentStream.endText();
			nextTextX += table.getColumns().get(i).getWidth();
		}
	}

	private void drawTableGrid(Table table, String[][] currentPageContent,
			PDPageContentStream contentStream, float tableTopY)
			throws IOException {
		// Draw row lines
		float nextY = tableTopY;
		for (int i = 0; i <= currentPageContent.length + 1; i++) {
			contentStream.drawLine(table.getMargin(), nextY, table.getMargin()
					+ table.getWidth(), nextY);
			nextY -= table.getRowHeight();
		}

		// Draw column lines
		final float tableYLength = table.getRowHeight()
				+ (table.getRowHeight() * currentPageContent.length);
		final float tableBottomY = tableTopY - tableYLength;
		float nextX = table.getMargin();
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
			endRange = (pageCount * rowsPerPage) - 8 + rowsPerPage;
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
			float nextTextX, float nextTextY, Table table) throws IOException {

		nextTextY = table.isLandscape() ? table.getMargin() : table.getMargin();
		nextTextY -= (table.getRowHeight() * 2.5);
		contentStream.drawLine(nextTextX, (nextTextY + table.getRowHeight()),
				nextTextX * table.getNumberOfColumns(),
				(nextTextY + table.getRowHeight()));

		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);

		nextTextY += table.getRowHeight();
		nextTextX += table.getColumns().get(0).getWidth();
		contentStream.showText("Pagina 1 de 1");
		contentStream.endText();

	}

	private void writeHeader(PDPageContentStream contentStream,
			float nextTextX, float nextTextY, Table table) throws IOException {

		nextTextY = table.isLandscape() ? table.getPageSize().getWidth()
				- table.getMargin() : table.getPageSize().getHeight()
				- table.getMargin();
//		String basepath = VaadinService.getCurrent().getBaseDirectory()
//				.getAbsolutePath();
//
//		final Logger logger =
//		          Logger.getLogger(VReporteP.class.getName());
//		
//		logger.log(Level.SEVERE, basepath);
//		PDImageXObject pdImage = PDImageXObject.createFromFile(basepath
//				+ "/VAADIN/ait-theme/logos/Logo_AIT.png", this.doc);
//		float scale = 1f;
//		contentStream.drawImage(pdImage, 20, 20, pdImage.getWidth() * scale,
//				pdImage.getHeight() * scale);

		
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
	
		contentStream.showText("Dependencia : XXXXXXXX" + String.valueOf(nextTextX) +" - y:"+ String.valueOf(nextTextY));
		
		contentStream.endText();
		
		nextTextX += 500;
		
		contentStream.beginText();
		contentStream.moveTextPositionByAmount(nextTextX + 500, nextTextY);
		contentStream.showText("Fecha : XXXXXXXX");
		contentStream.endText();
		
		
/*

		contentStream.showText("Hora : XXXXXXXX");
		
		nextTextY -= table.getRowHeight();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Unidad : XXXXXXXX");
	
		nextTextY -= table.getRowHeight();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Usuario : XXXXXXXX");
		
		nextTextY = table.getRowHeight();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);		
		contentStream.showText("Titulo : XXXXXXXX");
	
		nextTextY -= table.getRowHeight();
		contentStream.moveTextPositionByAmount(nextTextX, nextTextY);
		contentStream.showText("Subtitulo : XXXXXXXX");
	
		contentStream.endText();*/

	}
}
