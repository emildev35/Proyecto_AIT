package ait.sistemas.proyecto.activos.component.etiqueta;

import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import ait.sistemas.proyecto.common.component.CodeBar;

/**
 * Clase donde se almcacena los parametros para la impresion del documento 
 * PDF de las etiquetas
 * @author franzemil
 *
 */
public class Etiqueta {
	/**
	 * Margen de la pagina
	 */
	private float margin;
	/**
	 * Tamaño de la Pagina ej: LETTER, A4, etc.
	 */
	private PDRectangle pageSize;
	/**
	 * Fuente de los nombre del Activo
	 */
	private PDFont textFont;
	/**
	 * Tamaño de la Fuente de los nombre del Activo
	 */
	private float fontSize;
	/**
	 * Codigos a ser impresos
	 */
	private List<CodeBar> codigos;
	private String[][] content;
	
	private float cellMargin;
	
	/**
	 * Alto de los codigos
	 */
	private float heigthCode;
	/**
	 * Ancho de los codigos
	 */
	private float widthCode;
	
	
	public List<CodeBar> getCodigos() {
		return codigos;
	}

	public void setCodigos(List<CodeBar> codigos) {
		this.codigos = codigos;
	}

	public float getHeigthCode() {
		return heigthCode;
	}

	public void setHeigthCode(float heigthCode) {
		this.heigthCode = heigthCode;
	}

	public float getWidthCode() {
		return widthCode;
	}

	public void setWidthCode(float widthCode) {
		this.widthCode = widthCode;
	}

	public float getMargin() {
		return margin;
	}
	
	public void setMargin(float margin) {
		this.margin = margin;
	}
	
	public PDRectangle getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(PDRectangle pageSize) {
		this.pageSize = pageSize;
	}
	
	public PDFont getTextFont() {
		return textFont;
	}
	
	public void setTextFont(PDFont textFont) {
		this.textFont = textFont;
	}
	
	public float getFontSize() {
		return fontSize;
	}
	
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}
	
	public String[][] getContent() {
		return content;
	}
	
	public void setContent(String[][] content) {
		this.content = content;
	}
	
	public float getCellMargin() {
		return cellMargin;
	}
	
	public void setCellMargin(float cellMargin) {
		this.cellMargin = cellMargin;
	}
	
}
