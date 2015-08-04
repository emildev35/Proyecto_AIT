package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import ait.sistemas.proyecto.common.report.Column;

public class Kardex {

	private float numberofcell = 50;
	private float margin;
	private float height;
	private PDRectangle pageSize;
	private boolean isLandscape;
	private float rowHeight;
	private float rowTitleHeight;
	private float sizeofcell;
	private int headerSize;
	private PDFont textFont;
	private PDFont headerFont;
	private PDFont footerFont;
	private PDFont titleFont;
	private PDFont subtitleFont;
	private PDFont texttitleFont;
	private float fontSize;
	private float fontSizeheader;
	private float fontSizetexttitle;
	private float fontSizefooter;
	private float fontSizetitle;
	private float fontSizesubtitle;

	private float cellMargin;

	Integer numberOfRows;
	private String usuario;
	private String unidad;
	private String dependencia;
	private String title;
	private String subtitle;
	
	private KardexElement[][] elementos;
	
	private Integer numberOfRowsComponentes;
	private List<Column> columns_componentes;
	private String[][] componentes;
	private Integer numberOfRowsDocumentos;
	private List<Column> columns_documentos;
	private String[][] documentos;

	public Kardex() {

	}

	public Integer getNumberOfRows() {
		return (numberOfRows + numberOfRowsComponentes + numberOfRowsDocumentos);
	}

	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public void setSizeofcell(float sizeofcell) {
		this.sizeofcell = sizeofcell;
	}

	public float getSizeofcell() {
		return sizeofcell;
	}

	public float getNumberofcell() {
		return numberofcell;
	}

	public void setNumberofcell(float numberofcell) {
		this.numberofcell = numberofcell;
	}

	public float getMargin() {
		return margin;
	}

	public void setMargin(float margin) {
		this.margin = margin;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public PDRectangle getPageSize() {
		return pageSize;
	}

	public void setPageSize(PDRectangle pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isLandscape() {
		return isLandscape;
	}

	public void setLandscape(boolean isLandscape) {
		this.isLandscape = isLandscape;
	}

	public float getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(float rowHeight) {
		this.rowHeight = rowHeight;
	}

	public int getHeaderSize() {
		return headerSize;
	}

	public void setHeaderSize(int headerSize) {
		this.headerSize = headerSize;
	}

	public PDFont getTextFont() {
		return textFont;
	}

	public void setTextFont(PDFont textFont) {
		this.textFont = textFont;
	}

	public PDFont getHeaderFont() {
		return headerFont;
	}

	public void setHeaderFont(PDFont headerFont) {
		this.headerFont = headerFont;
	}

	public float getRowTitleHeight() {
		return rowTitleHeight;
	}

	public void setRowTitleHeight(float rowTitleHeight) {
		this.rowTitleHeight = rowTitleHeight;
	}

	public KardexElement[][] getElementos() {
		return elementos;
	}

	public void setElementos(KardexElement[][] elementos) {
		this.elementos = elementos;
	}

	public PDFont getFooterFont() {
		return footerFont;
	}

	public void setFooterFont(PDFont footerFont) {
		this.footerFont = footerFont;
	}

	public PDFont getTitleFont() {
		return titleFont;
	}

	public void setTitleFont(PDFont titleFont) {
		this.titleFont = titleFont;
	}

	public PDFont getSubtitleFont() {
		return subtitleFont;
	}

	public void setSubtitleFont(PDFont subtitleFont) {
		this.subtitleFont = subtitleFont;
	}

	public float getFontSize() {
		return fontSize;
	}

	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}

	public float getFontSizeheader() {
		return fontSizeheader;
	}

	public void setFontSizeheader(float fontSizeheader) {
		this.fontSizeheader = fontSizeheader;
	}

	public float getFontSizefooter() {
		return fontSizefooter;
	}

	public void setFontSizefooter(float fontSizefooter) {
		this.fontSizefooter = fontSizefooter;
	}

	public float getFontSizetitle() {
		return fontSizetitle;
	}

	public void setFontSizetitle(float fontSizetitle) {
		this.fontSizetitle = fontSizetitle;
	}

	public float getFontSizesubtitle() {
		return fontSizesubtitle;
	}

	public void setFontSizesubtitle(float fontSizesubtitle) {
		this.fontSizesubtitle = fontSizesubtitle;
	}


	public Integer getNumberOfRowsComponentes() {
		return numberOfRowsComponentes;
	}

	public void setNumberOfRowsComponentes(Integer numberOfRowsComponentes) {
		this.numberOfRowsComponentes = numberOfRowsComponentes;
	}

	public Integer getNumberOfRowsDocumentos() {
		return numberOfRowsDocumentos;
	}

	public void setNumberOfRowsDocumentos(Integer numberOfRowsDocumentos) {
		this.numberOfRowsDocumentos = numberOfRowsDocumentos;
	}

	public float getCellMargin() {
		return cellMargin;
	}

	public void setCellMargin(float cellMargin) {
		this.cellMargin = cellMargin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public KardexElement[][] getElemenos() {
		return elementos;
	}

	public void setElemenos( KardexElement[][] elemenos) {
		this.elementos = elemenos;
	}

	public PDFont getTexttitleFont() {
		return texttitleFont;
	}

	public void setTexttitleFont(PDFont texttitleFont) {
		this.texttitleFont = texttitleFont;
	}

	public float getFontSizetexttitle() {
		return fontSizetexttitle;
	}

	public void setFontSizetexttitle(float fontSizetexttitle) {
		this.fontSizetexttitle = fontSizetexttitle;
	}

	public float getWidth() {
		float tableWidth = 0f;

		for (int i = 0; i < elementos.length; i++) {
			if( this.elementos[0][i] == null){
				return tableWidth;
			}
			tableWidth += this.elementos[0][i].getAncho();
		}
		return tableWidth;
	}

	public float getSizeWidthRow() {
		return ((this.pageSize.getWidth() - 2 * this.cellMargin) / this.numberofcell);
	}

	public String[][] getComponentes() {
		return componentes;
	}

	public void setComponentes(String[][] componentes) {
		this.componentes = componentes;
	}

	public String[][] getDocumentos() {
		return documentos;
	}

	public void setDocumentos(String[][] documentos) {
		this.documentos = documentos;
	}

	public List<Column> getColumns_componentes() {
		return columns_componentes;
	}

	public void setColumns_componentes(List<Column> columns_componentes) {
		this.columns_componentes = columns_componentes;
	}

	public List<Column> getColumns_documentos() {
		return columns_documentos;
	}

	public void setColumns_documentos(List<Column> columns_documentos) {
		this.columns_documentos = columns_documentos;
	}

	public String[] getColumnsComponentsNamesAsArray() {
		 String[] columnNames = new String[columns_componentes.size()];
	        for (int i = 0; i < columns_componentes.size(); i++) {
	            columnNames[i] = columns_componentes.get(i).getName();
	        }
	        return columnNames;
	}
	
	public String[] getColumnsDocumentsNamesAsArray() {
		 String[] columnNames = new String[columns_documentos.size()];
	        for (int i = 0; i < columns_documentos.size(); i++) {
	            columnNames[i] = columns_documentos.get(i).getName();
	        }
	        return columnNames;
	}
	
}
