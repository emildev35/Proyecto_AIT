package ait.sistemas.proyecto.activos.view.mvac.historico;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.activos.component.model.MovimientoReporte;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.ProveedorImpl;
import ait.sistemas.proyecto.common.component.PathValues;
import ait.sistemas.proyecto.common.report.Column;

/**
 * Reporte Pdf que contiene el Kardex de Cada Activo
 * se definio un acho de 725 puntos
 * @author franzemil
 *
 */
public class ReportPdf {
	// Page configuration
	private static final PDRectangle PAGE_SIZE = PDRectangle.LETTER;
	private static final float MARGIN = 30;
	private static final boolean IS_LANDSCAPE = true;

	// Font for textFont
	private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
	private static final float FONT_SIZE = 8;

	private static final PDFont TEXT_FONT_TITLE = PDType1Font.HELVETICA_BOLD;
	private static final float FONT_TITLE_SIZE = 6.5f;

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
	private static final float ROW_TITLE_HEIGHT = 20;
	
	private static final float CELL_MARGIN = 2;

	private static final int HEADER_SIZE = 4;

	public  String SAVE_PATH = PathValues.PATH_REPORTS + "Historico-" + String.valueOf(new java.util.Date().getTime()) + ".pdf";

	final ProveedorImpl provedorimpl = new ProveedorImpl();
//	final ActivoImpl activoimpl = new ActivoImpl();
	final MovimientoImpl movimiento_impl = new MovimientoImpl();

	public boolean getPdf(HisotricoElement[][] data, Object id_activo, Date fecha) throws IOException {

		return new PdfHistoricoGenerator().generatePDF(createContent(data, fecha), SAVE_PATH);

	}

	private String[][] getComponentes(long id_activo, Date fecha) {
		String[][] result = new String[this.movimiento_impl.getMovimientobyActivo(id_activo,new java.sql.Date (fecha.getTime())).size()][2];
		int r = 0;
		for (MovimientoReporte movimiento : this.movimiento_impl.getMovimientobyActivo(id_activo,new java.sql.Date( fecha.getTime()))) {
			String[] row = { new SimpleDateFormat("dd-MM-yyyy").format(movimiento.getCMV_Fecha_Registro()), 
					movimiento.getCaracteristicas(),
					movimiento.getNo_Acta(),
					movimiento.getUsuario_Origen(),
					movimiento.getUsuario_Destino()};
			result[r] = row;
			r++;
		}
		return result;
//		return null;
	}

//	private String[][] getDocumentos(long id_activo, Date id_dependencia) {
//		String[][] result = new String[this.activoimpl.getdocumento(id_activo, id_dependencia).size()][2];
//		int r = 0;
//		for (DocumentosRespaldoModel documento : this.activoimpl.getdocumento(id_activo, id_dependencia)) {
//			String[] row = { documento.getDOR_Nombre_Documento(), documento.getDOR_Ubicacion_Documento() };
//			result[r] = row;
//			r++;
//		}
//		return result;
//		return null;
//	}

	private Historico createContent(HisotricoElement[][] data,  Date fecha) {

		HisotricoElement[][] content = data;
		List<Column> columns_componentes = new ArrayList<Column>();
		columns_componentes.add(new Column("Fecha", 50));
		columns_componentes.add(new Column("Tipo Movimiento", 135));
		columns_componentes.add(new Column("No Documento", 80));
		columns_componentes.add(new Column("Funcionario Origen", 230));
		columns_componentes.add(new Column("Funcionario Destino", 230));

//		List<Column> columns_documentos = new ArrayList<Column>();
//		columns_documentos.add(new Column("Nombre del Documento de Respaldo", 135));
//		columns_documentos.add(new Column("Ubicacion del Documento de Respaldo", 590));

		String[][] componentes = getComponentes(Long.parseLong(data[0][0].getContenido()), fecha);
//		String[][] documentos = getDocumentos(Long.parseLong(data[0][0].getContenido()), fecha);
		float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight() - (2 * MARGIN);

		Historico table = new HistoricoBuilder()
				.setCellMargin(CELL_MARGIN).setelement(content)
				.setColumnsComponentes(columns_componentes)
				.setComponentes(componentes)
//				.setColumnsDocumentos(columns_documentos)
//				.setDocumentos(documentos)
				.setHeight(tableHeight)
				.setNumberOfRows(content.length)
				.setNumberOfRowsComponentes(componentes.length)
//				.setNumberOfRowsDocumentos(documentos.length)
				.setRowHeight(ROW_HEIGHT).setMargin(MARGIN)
				.setPageSize(PAGE_SIZE).setLandscape(IS_LANDSCAPE).setTextFont(TEXT_FONT).setFontSize(FONT_SIZE)
				.setTextTitleFont(TEXT_FONT_TITLE).setFontSizeTextTitle(FONT_TITLE_SIZE)
				.setrowTitleHeigth(ROW_TITLE_HEIGHT).setHeaderFont(HEADER_FONT).setFontSizeHeader(HEADER_FONT_SIZE)
				.setFooterFont(FOOTER_FONT).setFontSizeFooter(FOOTER_FONT_SIZE).setTitleFont(TITLE_FONT)
				.setFontSizeTitle(TITLE_FONT_SIZE).setSubTitleFont(SUBTITLE_FONT)
				.setFontSizeSubTitle(SUBTITLE_FONT_SIZE).setHeaderSize(HEADER_SIZE).setUnidad("XXXXXX")
				.setDependencia("XXXXX").setUsuario("XXXXXX").setTitle("HISTÓRICO DE MOVIMIENTOS DE ACTIVOS FIJOS")
				.setSubTitle("Elaborado al : " +  new SimpleDateFormat("dd-MM-yyy").format(fecha)).build();
		return table;

	}

	private Historico[] createContent(HisotricoElement[][][] data, Date fecha) {

		Historico[] result = new Historico[data.length];

		for (int i = 0; i < data.length; i++) {

			HisotricoElement[][] content = data[i];

			List<Column> columns_componentes = new ArrayList<Column>();
			columns_componentes.add(new Column("Fecha", 50));
			columns_componentes.add(new Column("Tipo Movimiento", 135));
			columns_componentes.add(new Column("No Documento", 80));
			columns_componentes.add(new Column("Funcionario Origen", 230));
			columns_componentes.add(new Column("Funcionario Destino", 230));

//			List<Column> columns_documentos = new ArrayList<Column>();
//			columns_documentos.add(new Column("Nombre del Documento de Respaldo", 350));
//			columns_documentos.add(new Column("Ubicacion del Documento de Respaldo", 375));

			String[][] componentes = getComponentes(Long.parseLong(data[i][0][0].getContenido()), fecha);
//			String[][] documentos = getDocumentos(Long.parseLong(data[i][0][0].getContenido()), fecha);
//			float tableHeight = IS_LANDSCAPE ? PAGE_SIZE.getWidth() - (2 * MARGIN) : PAGE_SIZE.getHeight()
//					- (2 * MARGIN);

			Historico kardex = new HistoricoBuilder().setCellMargin(CELL_MARGIN).setelement(content)
					.setColumnsComponentes(columns_componentes).setComponentes(componentes)
//					.setColumnsDocumentos(columns_documentos)
//					.setDocumentos(documentos).setHeight(tableHeight)
					.setNumberOfRows(content.length)
					.setNumberOfRowsComponentes(componentes.length)
//					.setNumberOfRowsDocumentos(documentos.length)
					.setRowHeight(ROW_HEIGHT).setMargin(MARGIN)
					.setPageSize(PAGE_SIZE).setLandscape(IS_LANDSCAPE).setTextFont(TEXT_FONT).setFontSize(FONT_SIZE)
					.setTextTitleFont(TEXT_FONT_TITLE).setFontSizeTextTitle(FONT_TITLE_SIZE)
					.setrowTitleHeigth(ROW_TITLE_HEIGHT).setHeaderFont(HEADER_FONT).setFontSizeHeader(HEADER_FONT_SIZE)
					.setFooterFont(FOOTER_FONT).setFontSizeFooter(FOOTER_FONT_SIZE).setTitleFont(TITLE_FONT)
					.setFontSizeTitle(TITLE_FONT_SIZE).setSubTitleFont(SUBTITLE_FONT)
					.setFontSizeSubTitle(SUBTITLE_FONT_SIZE).setHeaderSize(HEADER_SIZE).setUnidad("XXXXXX")
					.setDependencia("XXXXX").setUsuario("XXXXXX").setTitle("HISTÓRICO DE MOVIMIENTOS DE ACTIVOS FIJOS").setSubTitle("Elaborado al : " +  new SimpleDateFormat("dd-MM-yyy").format(fecha)).build();
			result[i] = kardex;

		}
		return result;
	}

	public boolean getPdfKardexGeneratorMulti(HisotricoElement[][][] data,Object id_activo, Date fecha)
			throws IOException {
		return new PdfHistoricoGeneratorMulti().generatePDF(createContent(data, fecha), SAVE_PATH);

	}
}
