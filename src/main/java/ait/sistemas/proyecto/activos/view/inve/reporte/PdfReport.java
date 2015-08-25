package ait.sistemas.proyecto.activos.view.inve.reporte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.common.report.Column;
import ait.sistemas.proyecto.common.report.pdf.Table;
import ait.sistemas.proyecto.common.report.pdf.TableBuilder;
import ait.sistemas.proyecto.common.report.pdf.TableGenerator;

public class PdfReport {
	
	private static final PDRectangle PAGE_SIZE = PDRectangle.LETTER;
	private static final float MARGIN = 60;
	private static final boolean IS_LANDSCAPE = false;
	
	// Font for textFont
	private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
	private static final float FONT_SIZE = 7;
	
	/**
	 * Font for footer report
	 */
	private static final PDFont FOOTER_FONT = PDType1Font.HELVETICA;
	private static final float FOOTER_FONT_SIZE = 8;
	
	/**
	 * Font for footer report
	 */
	private static final PDFont HEADER_FONT = PDType1Font.HELVETICA;
	private static final float HEADER_FONT_SIZE = 8;
	
	/**
	 * Font for footer report
	 */
	private static final PDFont TITLE_FONT = PDType1Font.HELVETICA_BOLD;
	private static final float TITLE_FONT_SIZE = 13;
	
	/**
	 * Font for footer report
	 */
	private static final PDFont SUBTITLE_FONT = PDType1Font.HELVETICA;
	private static final float SUBTITLE_FONT_SIZE = 11;
	
	private static final float ROW_HEIGHT = 15;
	private static final float CELL_MARGIN = 2;
	
	private static final int HEADER_SIZE = 3	;
	
	static String SAVE_PATH = "/tmp/" + String.valueOf(new java.util.Date().getTime()) + ".pdf";
	
	final ActivoImpl activoimpl = new ActivoImpl();
	
	String[][] data;
	
	public boolean getPdf(String[][] data, List<String> title, int[] sizes, String titulo) throws IOException {
		
		return new TableGenerator().generatePDF(createContent(data, titulo, title, sizes), SAVE_PATH);
		
	}
	
	private Table createContent(String[][] content, String titulo, List<String> title, int[] width) {
		
		List<Column> columns = new ArrayList<Column>();
		for (int i = 0; i < width.length; i++) {
			columns.add(new Column(title.get(i), width[i]));
		}
	
		
		float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);
		
		Table Inventario = new TableBuilder().setCellMargin(CELL_MARGIN).setColumns(columns).setContent(content)
				.setHeight(tableHeight).setNumberOfRows(content.length).setRowHeight(ROW_HEIGHT).setMargin(MARGIN)
				.setPageSize(PAGE_SIZE).setLandscape(IS_LANDSCAPE).setTextFont(TEXT_FONT).setFontSize(FONT_SIZE)
				.setHeaderFont(HEADER_FONT).setFontSizeHeader(HEADER_FONT_SIZE).setFooterFont(FOOTER_FONT)
				.setFontSizeFooter(FOOTER_FONT_SIZE).setTitleFont(TITLE_FONT).setFontSizeTitle(TITLE_FONT_SIZE)
				.setSubTitleFont(SUBTITLE_FONT).setFontSizeSubTitle(SUBTITLE_FONT_SIZE).setHeaderSize(HEADER_SIZE)
				.setUnidad("XXXXXX").setDependencia("XXXXX").setUsuario("XXXXXX").setTitle(titulo).build();
		return Inventario;
		
	}
	
}
