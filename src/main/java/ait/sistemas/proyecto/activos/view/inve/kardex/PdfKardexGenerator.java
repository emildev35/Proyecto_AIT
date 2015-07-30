package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

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
			PDPage page = generatePage(doc, kardex);
			PDPageContentStream contentStream = generateContentStream(doc, page, kardex);
			drawCurrentPage(kardex, contentStream, pageCount);
		}
	}

	// Draws current page kardex grid and border lines and content
	private void drawCurrentPage(Kardex kardex, PDPageContentStream contentStream, int pageCount) throws IOException {
		float nexPosY = 50;
		for (KardexElement element : kardex.getElemenos()) {
			contentStream.moveTo(50, nexPosY);
			contentStream.beginText();
			contentStream.showText(element.getTitulo());
			contentStream.endText();
			nexPosY+=20;
		}
	}

	private void writeContentLine(String[] lineContent, PDPageContentStream contentStream, float nextTextX,
			float nextTextY, Kardex kardex) throws IOException {
	}


	

	private PDPage generatePage(PDDocument doc, Kardex kardex) {
		PDPage page = new PDPage();
		page.setMediaBox(kardex.getPageSize());
		page.setRotation(kardex.isLandscape() ? 90 : 0);
		doc.addPage(page);
		return page;
	}

	private PDPageContentStream generateContentStream(PDDocument doc, PDPage page, Kardex kardex) throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(doc, page, false, false);
		if (kardex.isLandscape()) {
			contentStream.concatenate2CTM(0, 1, -1, 0, kardex.getPageSize().getWidth(), 0);
		}
		contentStream.setFont(kardex.getTextFont(), kardex.getFontSize());
		return contentStream;
	}

}
