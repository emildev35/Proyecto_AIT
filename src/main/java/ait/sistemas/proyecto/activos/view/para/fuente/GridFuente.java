package ait.sistemas.proyecto.activos.view.para.fuente;

import ait.sistemas.proyecto.activos.data.model.Fuentes_Financiamiento;
import ait.sistemas.proyecto.activos.data.service.Impl.FuenteImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridFuente extends Grid {
	
	private FuenteImpl fuente_impl = new FuenteImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<Fuentes_Financiamiento> bean_fuente;
	
	public GridFuente() {
		buildGrid();
	}
	
	public void update() {
		this.removeAllColumns();
		bean_fuente = null;
		buildGrid();
	}
	
	public void buildGrid() {
		bean_fuente = new BeanItemContainer<Fuentes_Financiamiento>(Fuentes_Financiamiento.class);
		bean_fuente.addAll(fuente_impl.getall());
		setContainerDataSource(bean_fuente);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(10);
		
		removeColumn("FFI_Fecha_Registro");
		
		setWidth("100%");
		
		setColumnOrder("FFI_Fuente_Financiamiento", "FFI_Nombre_Fuente_Financiamiento");
		
		Grid.Column id_tiposactColumn = this.getColumn("FFI_Fuente_Financiamiento");
		Grid.Column nombre_tiposactColumn = this.getColumn("FFI_Nombre_Fuente_Financiamiento");
		
		id_tiposactColumn.setHeaderCaption("Codigo").setExpandRatio(1);
		nombre_tiposactColumn.setHeaderCaption("Nombre Fuente de Financiamiento").setExpandRatio(10);
		
		Responsive.makeResponsive(this);
	}
}
