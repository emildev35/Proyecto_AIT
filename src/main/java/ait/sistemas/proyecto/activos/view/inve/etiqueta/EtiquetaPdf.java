package ait.sistemas.proyecto.activos.view.inve.etiqueta;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import ait.sistemas.proyecto.activos.component.etiqueta.Etiqueta;
import ait.sistemas.proyecto.activos.component.etiqueta.PdfEtiqueta;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.ProveedorImpl;
import ait.sistemas.proyecto.common.component.CodeBar;
import ait.sistemas.proyecto.common.component.PathValues;

/**
 * Clase de Gestion para la impresion del pdf
 * @author franzemil
 *
 */
public class EtiquetaPdf {
	
	private static final PDRectangle PAGE_SIZE = PDRectangle.A4;
	private static final float MARGIN = 40;
	private static final PDFont TEXT_FONT = PDType1Font.HELVETICA;
	private static final float FONT_SIZE = 6;
	private static final float CELL_MARGIN = 2;


	static String SAVE_PATH = PathValues.CODE_PATH + String.valueOf(new java.util.Date().getTime()) + ".pdf";

	final ProveedorImpl provedorimpl = new ProveedorImpl();
	final ActivoImpl activoimpl = new ActivoImpl();

	public boolean getPdf(List<CodeBar> codigos) throws IOException {

		return new PdfEtiqueta().generatePDF(createContent(codigos), SAVE_PATH);

	}
	/**
	 * Encarga de Generar el Objeto Etiqueta
	 * @param codigos
	 * @return
	 */
	private Etiqueta createContent(List<CodeBar> codigos) {
		Etiqueta etiqueta = new  Etiqueta();
		etiqueta.setCellMargin(CELL_MARGIN);
		etiqueta.setCodigos(codigos);
		etiqueta.setPageSize(PAGE_SIZE);
		etiqueta.setMargin(MARGIN);
		etiqueta.setFontSize(FONT_SIZE);
		etiqueta.setTextFont(TEXT_FONT);
		etiqueta.setHeigthCode(65);
		etiqueta.setWidthCode(140);
		return etiqueta;
	}
}
