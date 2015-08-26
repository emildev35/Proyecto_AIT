package ait.sistemas.proyecto.activos.view.para.estadosop;

import ait.sistemas.proyecto.activos.data.model.EstadoSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.EstadoSoporteImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridEstadoSoporte extends Grid{

	private EstadoSoporteImpl estado_soporteimpl = new EstadoSoporteImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<EstadoSoporte> bean_estado_soporte;

	public GridEstadoSoporte() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_estado_soporte = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_estado_soporte = new BeanItemContainer<EstadoSoporte>(
				EstadoSoporte.class, this.estado_soporteimpl.getall());
		setContainerDataSource(bean_estado_soporte);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		removeColumn("fecha_registro");	
		
		setWidth("100%");

		setColumnOrder("id", "nombre");
	
		Responsive.makeResponsive(this);
	}
}

