package ait.sistemas.proyecto.activos.view.mvac.asignacion.reporte;

import java.util.List;

import ait.sistemas.proyecto.common.report.Column;

public class TablaActivos {
	
	private float rowheigth;
	private List<Column> columns;
	private String[][] data;
	
	public TablaActivos() {
	
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public float getRowHeight() {
		return rowheigth; 
	}

	public float getRowheigth() {
		return rowheigth;
	}

	public void setRowheigth(float rowheigth) {
		this.rowheigth = rowheigth;
	}
	
	
	
}
