package ait.sistemas.proyecto.activos.view.inve.activosxfun.reporte;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.common.component.PathValues;
import ait.sistemas.proyecto.common.report.PageSize;
import ait.sistemas.proyecto.common.report.pdf.movimiento.Acta;

public class ReportPdf {
	// Page configuration
	private static final PDRectangle PAGE_SIZE = PageSize.OFICIO;
	private static final float MARGIN = 30;
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
	private static final float FOOTER_FONT_SIZE = 7;

	/**
	 * Font for footer report
	 */
	private static final PDFont HEADER_FONT = PDType1Font.HELVETICA;
//	private static final float HEADER_FONT_SIZE = 8;

	/**
	 * Font for footer report
	 */
	private static final PDFont TITLE_FONT = PDType1Font.HELVETICA_BOLD;
	private static final float TITLE_FONT_SIZE = 14;

	/**
	 * Font for footer report
//	 */
	private static final PDFont SUBTITLE_FONT = PDType1Font.HELVETICA;
	private static final float SUBTITLE_FONT_SIZE = 12;
//
//	private static final float ROW_HEIGHT = 15;
//	private static final float ROW_TITLE_HEIGHT = 20;
//	private static final float CELL_MARGIN = 2;
//
//	private static final int HEADER_SIZE = 4;
	public static String SAVE_PATH = PathValues.PATH_REPORTS + "Reporte-Activos-x-Funcionario" + String.valueOf(new java.util.Date().getTime()) + ".pdf";

	public boolean getPdf(Acta data, String fecha) throws IOException {

		return new PDFActivosxFunGenerator().generatePDF(build(data, fecha), SAVE_PATH);

	}

	private Acta build(Acta acta, String fecha){
		
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
		acta.setFontSizetitle(TITLE_FONT_SIZE);
		acta.setFontSizeheader(FONT_TITLE_SIZE);
		acta.setSubtitleFont(SUBTITLE_FONT);
		acta.setFontSizesubtitle(SUBTITLE_FONT_SIZE);
		acta.setFooterFont(FOOTER_FONT);
		acta.setFontSizefooter(FOOTER_FONT_SIZE);
		acta.setTexttitleFont(TITLE_FONT);
		acta.setFontSizetexttitle(FONT_TITLE_SIZE);
		acta.setTitle("INVENTARIO DE ACTIVOS POR FUNCIONARIO");
		acta.setSubtitle("Elaborado al : "+ fecha);
		return acta;
	}

}
