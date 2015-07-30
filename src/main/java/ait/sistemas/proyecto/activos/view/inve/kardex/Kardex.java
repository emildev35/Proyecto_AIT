package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.util.List;

import javax.ws.rs.GET;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class Kardex {

	private float numberofcell = 50;
	private float margin;
	private float height;
	private PDRectangle pageSize;
	private boolean isLandscape;
	private float rowHeight;
	private float sizeofcell;
	private int headerSize;
	private PDFont textFont;
	private PDFont headerFont;
	private PDFont footerFont;
	private PDFont titleFont;
	private PDFont subtitleFont;
	private float fontSize;
	private float fontSizeheader;
	private float fontSizefooter;
	private float fontSizetitle;
	private float fontSizesubtitle;

	private Integer numberOfRows;
	private float cellMargin;

	private String usuario;
	private String unidad;
	private String dependencia;
	private String title;
	private String subtitle;
	List<KardexElement> elemenos;

	public Kardex() {

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

	public Integer getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
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

	public List<KardexElement> getElemenos() {
		return elemenos;
	}

	public void setElemenos(List<KardexElement> elemenos) {
		this.elemenos = elemenos;
	}

	public float getSizeWidthRow() {
		return ((this.pageSize.getWidth() - 2 * this.cellMargin) / this.numberofcell);
	}

}
