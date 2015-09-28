package ait.sistemas.proyecto.common.report.pdf.movimiento;

import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import ait.sistemas.proyecto.common.report.Column;

public class Acta {

	private float numberofcell = 50;
	private float margin;
	private PDRectangle pageSize;
	private boolean isLandscape;
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

	private String title;
	private String subtitle;
	private String ci;
	private String nro_acta_entrega;
	private String fecha;
	private String dependencia_origen;
	private String dependencia_destino;
	private String unidad_origen;
	private String unidad_destino;
	private String usuario_origen;
	private String usuario_destino;

	private TablaActivos tb_activos;

	private List<Firma> firmas;

	private List<Column> columns;

	public Acta() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Integer getNumberOfRows() {
		return (tb_activos.getData().length + 16);
	}
	public Integer getNumberOfRowsTable() {
		return tb_activos.getData().length;
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
		return pageSize.getHeight();
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
		return tb_activos.getRowHeight();
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

	public float getCellMargin() {
		return cellMargin;
	}

	public void setCellMargin(float cellMargin) {
		this.cellMargin = cellMargin;
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
		return isLandscape ? pageSize.getHeight() : pageSize.getWidth();
	}

	public float getSizeWidthRow() {
		return ((this.pageSize.getWidth() - 2 * this.cellMargin) / this.numberofcell);
	}

	public String getNro_acta_entrega() {
		return nro_acta_entrega;
	}

	public void setNro_acta_entrega(String nro_acta_entrega) {
		this.nro_acta_entrega = nro_acta_entrega;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDependencia_origen() {
		return dependencia_origen;
	}

	public void setDependencia_origen(String dependencia_origen) {
		this.dependencia_origen = dependencia_origen;
	}

	public String getDependencia_destino() {
		return dependencia_destino;
	}

	public void setDependencia_destino(String dependencia_destino) {
		this.dependencia_destino = dependencia_destino;
	}

	public String getUnidad_origen() {
		return unidad_origen;
	}

	public void setUnidad_origen(String unidad_origen) {
		this.unidad_origen = unidad_origen;
	}

	public String getUnidad_destino() {
		return unidad_destino;
	}

	public void setUnidad_destino(String unidad_destino) {
		this.unidad_destino = unidad_destino;
	}

	public String getUsuario_origen() {
		return usuario_origen;
	}

	public void setUsuario_origen(String usuario_origen) {
		this.usuario_origen = usuario_origen;
	}

	public String getUsuario_destino() {
		return usuario_destino;
	}

	public void setUsuario_destino(String usuario_destino) {
		this.usuario_destino = usuario_destino;
	}

	public TablaActivos getTb_activos() {
		return tb_activos;
	}

	public void setTb_activos(TablaActivos tb_activos) {
		this.tb_activos = tb_activos;
	}

	public List<Firma> getFirmas() {
		return firmas;
	}

	public void setFirmas(List<Firma> firmas) {
		this.firmas = firmas;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public float getColumnWidth() {
		float result = 0;
		for (Column column : columns) {
			result += column.getWidth();
		}
		return result;
	}

	public String[] getColumnsNames() {
		String []  result = new String[columns.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = columns.get(i).getName();
		}
		return result;
	}
	
	

}
