package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.activos.data.model.ComponentesModel;
import ait.sistemas.proyecto.activos.data.model.DocumentosRespaldoModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.ProveedorImpl;
import ait.sistemas.proyecto.common.component.PathValues;
import ait.sistemas.proyecto.common.report.Column;

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
	static String SAVE_PATH = PathValues.PATH_REPORTS + "Informe-Kardex-final" + String.valueOf(new java.util.Date().getTime()) + ".pdf";

	final ProveedorImpl provedorimpl = new ProveedorImpl();
	final ActivoImpl activoimpl = new ActivoImpl();

	public boolean getPdf(KardexElement[][] data, Object id_activo, short id_dependencia) throws IOException {

		return new PdfKardexGenerator().generatePDF(createContent(data, id_dependencia), SAVE_PATH);

	}

	private String[][] getComponentes(long id_activo, short id_dependencia) {
		String[][] result = new String[this.activoimpl.getcomponente(id_activo, id_dependencia).size()][2];
		int r = 0;
		for (ComponentesModel componente : this.activoimpl.getcomponente(id_activo, id_dependencia)) {
			String[] row = { componente.getCOM_Nombre_Componente(), componente.getCOM_Caracteristica_Componente() };
			result[r] = row;
			r++;
		}
		return result;
	}

	private String[][] getDocumentos(long id_activo, short id_dependencia) {
		String[][] result = new String[this.activoimpl.getdocumento(id_activo, id_dependencia).size()][2];
		int r = 0;
		for (DocumentosRespaldoModel documento : this.activoimpl.getdocumento(id_activo, id_dependencia)) {
			String[] row = { documento.getDOR_Nombre_Documento(), documento.getDOR_Ubicacion_Documento() };
			result[r] = row;
			r++;
		}
		return result;
	}

	private Kardex createContent(KardexElement[][] data, short dependencia) {

		KardexElement[][] content = data;
		List<Column> columns_componentes = new ArrayList<Column>();
		columns_componentes.add(new Column("Nombre del Componente", 250));
		columns_componentes.add(new Column("Caracteristicas", 250));

		List<Column> columns_documentos = new ArrayList<Column>();
		columns_documentos.add(new Column("Nombre del Documento de Respaldo", 250));
		columns_documentos.add(new Column("Ubicacion del Documento de Respaldo", 250));

		String[][] componentes = getComponentes(Long.parseLong(data[0][0].getContenido()), dependencia);
		String[][] documentos = getDocumentos(Long.parseLong(data[0][0].getContenido()), dependencia);
		float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);

		Kardex table = new KardexBuilder().setCellMargin(CELL_MARGIN).setelement(content)
				.setColumnsComponentes(columns_componentes).setComponentes(componentes)
				.setColumnsDocumentos(columns_documentos).setDocumentos(documentos).setHeight(tableHeight)
				.setNumberOfRows(content.length).setNumberOfRowsComponentes(componentes.length)
				.setNumberOfRowsDocumentos(documentos.length).setRowHeight(ROW_HEIGHT).setMargin(MARGIN)
				.setPageSize(PAGE_SIZE).setLandscape(IS_LANDSCAPE).setTextFont(TEXT_FONT).setFontSize(FONT_SIZE)
				.setTextTitleFont(TEXT_FONT_TITLE).setFontSizeTextTitle(FONT_TITLE_SIZE)
				.setrowTitleHeigth(ROW_TITLE_HEIGHT).setHeaderFont(HEADER_FONT).setFontSizeHeader(HEADER_FONT_SIZE)
				.setFooterFont(FOOTER_FONT).setFontSizeFooter(FOOTER_FONT_SIZE).setTitleFont(TITLE_FONT)
				.setFontSizeTitle(TITLE_FONT_SIZE).setSubTitleFont(SUBTITLE_FONT)
				.setFontSizeSubTitle(SUBTITLE_FONT_SIZE).setHeaderSize(HEADER_SIZE).setUnidad("XXXXXX")
				.setDependencia("XXXXX").setUsuario("XXXXXX").setTitle("KARDEX").build();
		return table;

	}

	private Kardex[] createContent(KardexElement[][][] data, short dependencia) {

		Kardex[] result = new Kardex[data.length];

		for (int i = 0; i < data.length; i++) {

			KardexElement[][] content = data[i];

			List<Column> columns_componentes = new ArrayList<Column>();
			columns_componentes.add(new Column("Nombre del Componente", 250));
			columns_componentes.add(new Column("Caracteristicas", 250));

			List<Column> columns_documentos = new ArrayList<Column>();
			columns_documentos.add(new Column("Nombre del Documento de Respaldo", 250));
			columns_documentos.add(new Column("Ubicacion del Documento de Respaldo", 250));

			String[][] componentes = getComponentes(Long.parseLong(data[i][0][0].getContenido()), dependencia);
			String[][] documentos = getDocumentos(Long.parseLong(data[i][0][0].getContenido()), dependencia);
			float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight()
					- (2 * MARGIN);

			Kardex kardex = new KardexBuilder().setCellMargin(CELL_MARGIN).setelement(content)
					.setColumnsComponentes(columns_componentes).setComponentes(componentes)
					.setColumnsDocumentos(columns_documentos).setDocumentos(documentos).setHeight(tableHeight)
					.setNumberOfRows(content.length).setNumberOfRowsComponentes(componentes.length)
					.setNumberOfRowsDocumentos(documentos.length).setRowHeight(ROW_HEIGHT).setMargin(MARGIN)
					.setPageSize(PAGE_SIZE).setLandscape(IS_LANDSCAPE).setTextFont(TEXT_FONT).setFontSize(FONT_SIZE)
					.setTextTitleFont(TEXT_FONT_TITLE).setFontSizeTextTitle(FONT_TITLE_SIZE)
					.setrowTitleHeigth(ROW_TITLE_HEIGHT).setHeaderFont(HEADER_FONT).setFontSizeHeader(HEADER_FONT_SIZE)
					.setFooterFont(FOOTER_FONT).setFontSizeFooter(FOOTER_FONT_SIZE).setTitleFont(TITLE_FONT)
					.setFontSizeTitle(TITLE_FONT_SIZE).setSubTitleFont(SUBTITLE_FONT)
					.setFontSizeSubTitle(SUBTITLE_FONT_SIZE).setHeaderSize(HEADER_SIZE).setUnidad("XXXXXX")
					.setDependencia("XXXXX").setUsuario("XXXXXX").setTitle("KARDEX").build();
			result[i] = kardex;

		}
		return result;
	}

	public boolean getPdfKardexGeneratorMulti(KardexElement[][][] data, Object value, short id_dependecia)
			throws IOException {
		return new PdfKardexGeneratorMulti().generatePDF(createContent(data, id_dependecia), SAVE_PATH);

	}
}
