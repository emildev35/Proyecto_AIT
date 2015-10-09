package ait.sistemas.proyecto.activos.view.mvac.historico;

import java.util.List;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;

import ait.sistemas.proyecto.common.report.Column;

public class HistoricoBuilder {

	private Historico kardex = new Historico();

	public HistoricoBuilder setHeight(float height) {
		kardex.setHeight(height);
		return this;
	}

	public HistoricoBuilder setNumberOfRows(Integer numberOfRows) {
		kardex.setNumberOfRows(numberOfRows);
		return this;
	}
	public HistoricoBuilder setNumberOfRowsComponentes(Integer numberOfRows) {
		kardex.setNumberOfRowsComponentes(numberOfRows);
		return this;
	}
	public HistoricoBuilder setNumberOfRowsDocumentos(Integer numberOfRows) {
		kardex.setNumberOfRowsDocumentos(numberOfRows);
		return this;
	}
	public HistoricoBuilder setRowHeight(float rowHeight) {
		kardex.setRowHeight(rowHeight);
		return this;
	}

	public HistoricoBuilder setCellMargin(float cellMargin) {
		kardex.setCellMargin(cellMargin);
		return this;
	}

	public HistoricoBuilder setMargin(float margin) {
		kardex.setMargin(margin);
		return this;
	}

	public HistoricoBuilder setPageSize(PDRectangle pageSize) {
		kardex.setPageSize(pageSize);
		return this;
	}

	public HistoricoBuilder setLandscape(boolean landscape) {
		kardex.setLandscape(landscape);
		return this;
	}

	public HistoricoBuilder setTextFont(PDFont textFont) {
		kardex.setTextFont(textFont);
		return this;
	}

	public HistoricoBuilder setFontSize(float fontSize) {
		kardex.setFontSize(fontSize);
		return this;
	}

	public HistoricoBuilder setHeaderFont(PDFont textFont) {
		kardex.setHeaderFont(textFont);
		return this;
	}

	public HistoricoBuilder setFontSizeHeader(float fontSize) {
		kardex.setFontSizeheader(fontSize);
		return this;
	}

	public HistoricoBuilder setFooterFont(PDFont textFont) {
		kardex.setFooterFont(textFont);
		return this;
	}

	public HistoricoBuilder setFontSizeFooter(float fontSize) {
		kardex.setFontSizefooter(fontSize);
		return this;
	}

	public HistoricoBuilder setTitleFont(PDFont textFont) {
		kardex.setTitleFont(textFont);
		return this;
	}

	public HistoricoBuilder setFontSizeTitle(float fontSize) {
		kardex.setFontSizetitle(fontSize);
		return this;
	}

	public HistoricoBuilder setSubTitleFont(PDFont textFont) {
		kardex.setSubtitleFont(textFont);
		return this;
	}

	public HistoricoBuilder setFontSizeSubTitle(float fontSize) {
		kardex.setFontSizesubtitle(fontSize);
		return this;
	}

	public HistoricoBuilder setTextTitleFont(PDFont textFont) {
		kardex.setTexttitleFont(textFont);
		return this;
	}

	public HistoricoBuilder setFontSizeTextTitle(float fontSize) {
		kardex.setFontSizetexttitle(fontSize);
		return this;
	}

	public HistoricoBuilder setHeaderSize(int size) {
		kardex.setHeaderSize(size);
		return this;
	}

	public HistoricoBuilder setTitle(String title) {
		kardex.setTitle(title);
		return this;
	}

	public HistoricoBuilder setSubTitle(String subTitle) {
		kardex.setSubtitle(subTitle);
		return this;
	}

	public HistoricoBuilder setUnidad(String unidad) {
		kardex.setUnidad(unidad);
		return this;
	}

	public HistoricoBuilder setDependencia(String dependencia) {
		kardex.setDependencia(dependencia);
		return this;
	}

	public HistoricoBuilder setelement(HisotricoElement[][] elemenos) {
		kardex.setElemenos(elemenos);
		return this;
	}

	public HistoricoBuilder setUsuario(String usuario) {
		kardex.setUsuario(usuario);
		return this;
	}

	public HistoricoBuilder setrowTitleHeigth(float heigth) {
		kardex.setRowTitleHeight(heigth);
		return this;
	}

	public HistoricoBuilder setComponentes(String[][] componentes) {
		kardex.setComponentes(componentes);
		return this;
	}

	public HistoricoBuilder setDocumentos(String[][] documentos) {
		kardex.setDocumentos(documentos);
		return this;
	}
	public HistoricoBuilder setColumnsComponentes(List<Column> columnsComponentes){
		kardex.setColumns_componentes(columnsComponentes);
		return this;
	}
	public HistoricoBuilder setColumnsDocumentos(List<Column> columnsDocumentos){
		kardex.setColumns_documentos(columnsDocumentos);
		return this;
	}
	public Historico build() {
		return kardex;
	}
}
