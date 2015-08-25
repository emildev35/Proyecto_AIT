package ait.sistemas.proyecto.activos.view.para.tiposop;

import ait.sistemas.proyecto.activos.data.model.TipoSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoSoporteImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridTipoSoporte extends Grid{

	private TipoSoporteImpl tipo_soporteimpl = new TipoSoporteImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<TipoSoporte> bean_tipo_soporte;

	public GridTipoSoporte() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_tipo_soporte = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_tipo_soporte = new BeanItemContainer<TipoSoporte>(
				TipoSoporte.class, this.tipo_soporteimpl.getall());
		setContainerDataSource(bean_tipo_soporte);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		removeColumn("fecha_registro");	
		
		setWidth("100%");

		setColumnOrder("id", "nombre","sigla");
	
		Responsive.makeResponsive(this);
	}
}

