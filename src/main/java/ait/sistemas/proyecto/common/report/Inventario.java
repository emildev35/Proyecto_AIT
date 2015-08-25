package ait.sistemas.proyecto.common.report;

import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

@SuppressWarnings("unused")
public class Inventario {
	private float margin;
	private float height;
	private PDRectangle pageSize;
	private boolean isLandscape;
	private float rowHeight;

	private int headerSize;
	// font attributes
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
	// Content attributes
	private Integer numberOfRows;
	private List<Column> columns;
	private String[][] content;
	private List<Column> columns_data;
	private String[][][] content_data;
	private float cellMargin;
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
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public float getCellMargin() {
		return cellMargin;
	}
	public void setCellMargin(float cellMargin) {
		this.cellMargin = cellMargin;
	}



}
