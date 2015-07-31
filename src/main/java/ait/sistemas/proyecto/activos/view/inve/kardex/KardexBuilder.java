package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class KardexBuilder {

    private Kardex kardex = new Kardex();

    public KardexBuilder setHeight(float height) {
        kardex.setHeight(height);
        return this;
    }

    public KardexBuilder setNumberOfRows(Integer numberOfRows) {
        kardex.setNumberOfRows(numberOfRows);
        return this;
    }

    public KardexBuilder setRowHeight(float rowHeight) {
        kardex.setRowHeight(rowHeight);
        return this;
    }

    public KardexBuilder setCellMargin(float cellMargin) {
        kardex.setCellMargin(cellMargin);
        return this;
    }

    public KardexBuilder setMargin(float margin) {
        kardex.setMargin(margin);
        return this;
    }

    public KardexBuilder setPageSize(PDRectangle pageSize) {
        kardex.setPageSize(pageSize);
        return this;
    }

    public KardexBuilder setLandscape(boolean landscape) {
        kardex.setLandscape(landscape);
        return this;
    }

    public KardexBuilder setTextFont(PDFont textFont) {
        kardex.setTextFont(textFont);
        return this;
    }

    public KardexBuilder setFontSize(float fontSize) {
        kardex.setFontSize(fontSize);
        return this;
    }
    public KardexBuilder setHeaderFont(PDFont textFont) {
        kardex.setHeaderFont(textFont);
        return this;
    }

    public KardexBuilder setFontSizeHeader(float fontSize) {
        kardex.setFontSizeheader(fontSize);
        return this;
    }

    public KardexBuilder setFooterFont(PDFont textFont) {
        kardex.setFooterFont(textFont);
        return this;
    }

    public KardexBuilder setFontSizeFooter(float fontSize) {
        kardex.setFontSizefooter(fontSize);
        return this;
    }

    public KardexBuilder setTitleFont(PDFont textFont) {
        kardex.setTitleFont(textFont);
        return this;
    }

    public KardexBuilder setFontSizeTitle(float fontSize) {
        kardex.setFontSizetitle(fontSize);
        return this;
    }
    public KardexBuilder setSubTitleFont(PDFont textFont) {
        kardex.setSubtitleFont(textFont);
        return this;
    }

    public KardexBuilder setFontSizeSubTitle(float fontSize) {
        kardex.setFontSizesubtitle(fontSize);
        return this;
    }
    public KardexBuilder setTextTitleFont(PDFont textFont) {
        kardex.setTexttitleFont(textFont);
        return this;
    }

    public KardexBuilder setFontSizeTextTitle(float fontSize) {
        kardex.setFontSizetexttitle(fontSize);
        return this;
    }
    public KardexBuilder setHeaderSize(int size) {
        kardex.setHeaderSize(size);
        return this;
    }
    public KardexBuilder setTitle(String title){
    	kardex.setTitle(title);
    	return this;
    }
    public KardexBuilder setSubTitle(String subTitle){
    	kardex.setSubtitle(subTitle);
    	return this;
    }
    public KardexBuilder setUnidad(String unidad){
    	kardex.setUnidad(unidad);
    	return this;
    }
    public KardexBuilder setDependencia(String dependencia){
    	kardex.setDependencia(dependencia);
    	return this;
    }
    public KardexBuilder setelement(KardexElement[][] elemenos){
    	kardex.setElemenos(elemenos);
    	return this;
    }
    public KardexBuilder setUsuario(String usuario){
    	kardex.setUsuario(usuario);
    	return this;
    }
    public KardexBuilder setrowTitleHeigth(float heigth){
    	kardex.setRowTitleHeight(heigth);
    	return this;
    }
    public Kardex build() {
        return kardex;
    }
}
