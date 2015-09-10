package ait.sistemas.proyecto.activos.view.mvac.transferencia.reporte;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.common.report.pdf.movimiento.Acta;

public class ReportPdf {
	// Page configuration
	private static final PDRectangle PAGE_SIZE = PDRectangle.LEGAL;
	private static final float MARGIN = 60;
	private static final boolean IS_LANDSCAPE = true;

	// Font for textFont
	private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
	private static final float FONT_SIZE = 8;

//	private static final PDFont TEXT_FONT_TITLE = PDType1Font.HELVETICA;
	private static final float FONT_TITLE_SIZE = 8;

	/**
	 * Font for footer report
	 */
	private static final PDFont FOOTER_FONT = PDType1Font.HELVETICA;
	private static final float FOOTER_FONT_SIZE = 9;

	/**
	 * Font for footer report
	 */
	private static final PDFont HEADER_FONT = PDType1Font.HELVETICA;
//	private static final float HEADER_FONT_SIZE = 10;

	/**
	 * Font for footer report
	 */
	private static final PDFont TITLE_FONT = PDType1Font.HELVETICA_BOLD;
//	private static final float TITLE_FONT_SIZE = 14;

	/**
	 * Font for footer report
//	 */
//	private static final PDFont SUBTITLE_FONT = PDType1Font.HELVETICA;
//	private static final float SUBTITLE_FONT_SIZE = 11;
//
//	private static final float ROW_HEIGHT = 15;
//	private static final float ROW_TITLE_HEIGHT = 20;
//	private static final float CELL_MARGIN = 2;
//
//	private static final int HEADER_SIZE = 3;
	public static String SAVE_PATH = "C:\\Reporte-Acta-Movimiento" + String.valueOf(new java.util.Date().getTime()) + ".pdf";

	public boolean getPdf(Acta data, long no_documento, short tipo_movimiento) throws IOException {

		return new PdfActaGenerator().generatePDF(build(data), SAVE_PATH);

	}

	private Acta build(Acta acta){
		
//		float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);
		
//		acta.setHeight(tableHeight);
		acta.setFontSize(FONT_SIZE);
		acta.setTextFont(TEXT_FONT);
		acta.setMargin(MARGIN);
		acta.setHeaderSize(8);
		acta.setLandscape(IS_LANDSCAPE);
		acta.setPageSize(PAGE_SIZE);
		acta.setHeaderFont(HEADER_FONT);
		acta.setTitleFont(TITLE_FONT);
		acta.setFontSizetitle(FONT_TITLE_SIZE);
		acta.setFontSizeheader(FONT_TITLE_SIZE);
		acta.setFooterFont(FOOTER_FONT);
		acta.setFontSizefooter(FOOTER_FONT_SIZE);
		acta.setTexttitleFont(TITLE_FONT);
		acta.setFontSizetexttitle(FONT_TITLE_SIZE);
		return acta;
	}

}
