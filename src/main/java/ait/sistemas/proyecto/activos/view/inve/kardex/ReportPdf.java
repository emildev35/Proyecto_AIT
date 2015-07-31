package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.activos.data.service.Impl.ProveedorImpl;

public class ReportPdf {
	// Page configuration
	private static final PDRectangle PAGE_SIZE = PDRectangle.LETTER;
	private static final float MARGIN = 60;
	private static final boolean IS_LANDSCAPE = false;

	// Font for textFont
	private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
	private static final float FONT_SIZE = 8;
	
	private static final PDFont TEXT_FONT_TITLE = PDType1Font.HELVETICA_BOLD;
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
	private static final float HEADER_FONT_SIZE = 10;

	/**
	 * Font for footer report
	 */
	private static final PDFont TITLE_FONT = PDType1Font.HELVETICA_BOLD;
	private static final float TITLE_FONT_SIZE = 14;

	/**
	 * Font for footer report
	 */
	private static final PDFont SUBTITLE_FONT = PDType1Font.HELVETICA;
	private static final float SUBTITLE_FONT_SIZE = 11;

	private static final float ROW_HEIGHT = 15;
	private static final float ROW_TITLE_HEIGHT = 20;
	private static final float CELL_MARGIN = 2;

	private static final int HEADER_SIZE = 3;
	static String SAVE_PATH = "C:\\Informe-Kardex-final" + String.valueOf(new java.util.Date().getTime()) + ".pdf";

	final ProveedorImpl provedorimpl = new ProveedorImpl();

	public boolean getPdf(KardexElement[][] data) throws IOException {

		return new PdfKardexGenerator().generatePDF(createContent(data), SAVE_PATH);

	}

	private static Kardex createContent(KardexElement[][] data) {

		KardexElement[][] content = data;

		float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);

			Kardex table = new KardexBuilder().
					setCellMargin(CELL_MARGIN)
					.setelement(content)
					.setHeight(tableHeight)
					.setNumberOfRows(content.length).
					setRowHeight(ROW_HEIGHT).
					setMargin(MARGIN).
					setPageSize(PAGE_SIZE)
					.setLandscape(IS_LANDSCAPE)
					.setTextFont(TEXT_FONT)
					.setFontSize(FONT_SIZE)
					.setTextTitleFont(TEXT_FONT_TITLE)
					.setFontSizeTextTitle(FONT_TITLE_SIZE)
					.setrowTitleHeigth(ROW_TITLE_HEIGHT)
					.setHeaderFont(HEADER_FONT)
					.setFontSizeHeader(HEADER_FONT_SIZE).setFooterFont(FOOTER_FONT)
					.setFontSizeFooter(FOOTER_FONT_SIZE).setTitleFont(TITLE_FONT).setFontSizeTitle(TITLE_FONT_SIZE)
					.setSubTitleFont(SUBTITLE_FONT).setFontSizeSubTitle(SUBTITLE_FONT_SIZE).setHeaderSize(HEADER_SIZE)
					.setUnidad("XXXXXX").setDependencia("XXXXX").setUsuario("XXXXXX").setTitle("KARDEX").build();
			return table;

	

	}
}
