package ait.sistemas.proyecto.common.report.pdf;


import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import ait.sistemas.proyecto.common.report.Column;


public class TableBuilder {

    private Table table = new Table();

    public TableBuilder setHeight(float height) {
        table.setHeight(height);
        return this;
    }

    public TableBuilder setNumberOfRows(Integer numberOfRows) {
        table.setNumberOfRows(numberOfRows);
        return this;
    }

    public TableBuilder setRowHeight(float rowHeight) {
        table.setRowHeight(rowHeight);
        return this;
    }

    public TableBuilder setContent(String[][] content) {
        table.setContent(content);
        return this;
    }

    public TableBuilder setColumns(List<Column> columns) {
        table.setColumns(columns);
        return this;
    }

    public TableBuilder setCellMargin(float cellMargin) {
        table.setCellMargin(cellMargin);
        return this;
    }

    public TableBuilder setMargin(float margin) {
        table.setMargin(margin);
        return this;
    }

    public TableBuilder setPageSize(PDRectangle pageSize) {
        table.setPageSize(pageSize);
        return this;
    }

    public TableBuilder setLandscape(boolean landscape) {
        table.setLandscape(landscape);
        return this;
    }

    public TableBuilder setTextFont(PDFont textFont) {
        table.setTextFont(textFont);
        return this;
    }

    public TableBuilder setFontSize(float fontSize) {
        table.setFontSize(fontSize);
        return this;
    }
    public TableBuilder setHeaderFont(PDFont textFont) {
        table.setHeaderFont(textFont);
        return this;
    }

    public TableBuilder setFontSizeHeader(float fontSize) {
        table.setFontSizeheader(fontSize);
        return this;
    }

    public TableBuilder setFooterFont(PDFont textFont) {
        table.setFooterFont(textFont);
        return this;
    }

    public TableBuilder setFontSizeFooter(float fontSize) {
        table.setFontSizefooter(fontSize);
        return this;
    }

    public TableBuilder setTitleFont(PDFont textFont) {
        table.setTitleFont(textFont);
        return this;
    }

    public TableBuilder setFontSizeTitle(float fontSize) {
        table.setFontSizetitle(fontSize);
        return this;
    }
    public TableBuilder setSubTitleFont(PDFont textFont) {
        table.setSubtitleFont(textFont);
        return this;
    }

    public TableBuilder setFontSizeSubTitle(float fontSize) {
        table.setFontSizesubtitle(fontSize);
        return this;
    }
    
    public TableBuilder setHeaderSize(int size) {
        table.setHeaderSize(size);
        return this;
    }
    public TableBuilder setTitle(String title){
    	table.setTitle(title);
    	return this;
    }
    public TableBuilder setSubTitle(String subTitle){
    	table.setSubtitle(subTitle);
    	return this;
    }
    public TableBuilder setUnidad(String unidad){
    	table.setUnidad(unidad);
    	return this;
    }
    public TableBuilder setDependencia(String dependencia){
    	table.setDependencia(dependencia);
    	return this;
    }
    public TableBuilder setUsuario(String usuario){
    	table.setUsuario(usuario);
    	return this;
    }
    public Table build() {
        return table;
    }
}

