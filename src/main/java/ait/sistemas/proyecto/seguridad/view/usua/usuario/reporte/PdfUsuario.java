package ait.sistemas.proyecto.seguridad.view.usua.usuario.reporte;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.common.component.PathValues;
import ait.sistemas.proyecto.common.report.Column;
import ait.sistemas.proyecto.common.report.Table;
import ait.sistemas.proyecto.common.report.TableBuilder;
import ait.sistemas.proyecto.seguridad.component.report.PdfUsuarioGenerator;

public class PdfUsuario {
	

	private static final PDRectangle PAGE_SIZE = PDRectangle.LETTER;
	private static final float MARGIN = 40;
	private static final boolean IS_LANDSCAPE = false;

	// Font for textFont
	private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
	private static final float FONT_SIZE = 10;

	/**
	 * Font for footer report
	 */
	private static final PDFont FOOTER_FONT = PDType1Font.HELVETICA;
	private static final float FOOTER_FONT_SIZE = 7;

	/**
	 * Font for footer report
	 */
	private static final PDFont HEADER_FONT = PDType1Font.HELVETICA;
	private static final float HEADER_FONT_SIZE = 8;

	/**
	 * Font for footer report
	 */
	private static final PDFont TITLE_FONT = PDType1Font.HELVETICA_BOLD;
	private static final float TITLE_FONT_SIZE = 14;

	/**
	 * Font for footer report
	 */
	private static final PDFont SUBTITLE_FONT = PDType1Font.HELVETICA;
	private static final float SUBTITLE_FONT_SIZE = 12;

	private static final float ROW_HEIGHT = 15;
	private static final float CELL_MARGIN = 2;

	private static final int HEADER_SIZE = 3;


	public static String SAVE_PATH = PathValues.PATH_REPORTS + String.valueOf(new java.util.Date().getTime()) + ".pdf";
	
	final ActivoImpl activoimpl = new ActivoImpl();

	public boolean getPdf(String[][] data) throws IOException {

		return new PdfUsuarioGenerator().generatePDF(createContent(data), SAVE_PATH);

	}

	private Table createContent(String[][] content) {


		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column("Nombre Usuario", 100));
		columns.add(new Column("C. Identidad", 80));
		columns.add(new Column("Nombre Completo del Funcionario", 340));

		float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);
		Table usuarios = new TableBuilder().setCellMargin(CELL_MARGIN).setColumns(columns).setContent(content)
				.setHeight(tableHeight).setNumberOfRows(content.length).setRowHeight(ROW_HEIGHT).setMargin(MARGIN)
				.setPageSize(PAGE_SIZE).setLandscape(IS_LANDSCAPE).setTextFont(TEXT_FONT).setFontSize(FONT_SIZE)
				.setHeaderFont(HEADER_FONT).setFontSizeHeader(HEADER_FONT_SIZE).setFooterFont(FOOTER_FONT)
				.setFontSizeFooter(FOOTER_FONT_SIZE).setTitleFont(TITLE_FONT).setFontSizeTitle(TITLE_FONT_SIZE)
				.setSubTitleFont(SUBTITLE_FONT).setFontSizeSubTitle(SUBTITLE_FONT_SIZE).setHeaderSize(HEADER_SIZE)
				.setUnidad("XXXXXX").setDependencia("XXXXX").setUsuario("XXXXXX").setTitle("Reporte de Usuarios del Sistema")
				.build();
		return usuarios;

	}

}
