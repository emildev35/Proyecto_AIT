package ait.sistemas.proyecto.activos.view.para.ciudad;

import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.service.Impl.CiudadImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridCiudad extends Grid{

	private CiudadImpl menuImpl = new CiudadImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<Ciudade> bean_ciudad;

	public GridCiudad() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_ciudad = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_ciudad = new BeanItemContainer<Ciudade>(
		Ciudade.class, this.menuImpl.getall());
		setContainerDataSource(bean_ciudad);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		removeColumn("CIU_Fecha_Registro");	
		
		setWidth("100%");

		setColumnOrder("CIU_Ciudad", "CIU_Nombre_Ciudad","CIU_Sigla");
		
		Grid.Column id_ciudadColumn = this.getColumn("CIU_Ciudad");
		Grid.Column nombre_ciudadColumn = this.getColumn("CIU_Nombre_Ciudad");
		Grid.Column sigla_ciudadColumn = this.getColumn("CIU_Sigla");

		id_ciudadColumn.setHeaderCaption("Id. Ciudad");
		nombre_ciudadColumn.setHeaderCaption("Nombre Ciudad");
		sigla_ciudadColumn.setHeaderCaption("Sigla Ciudad");
		Responsive.makeResponsive(this);
	}
}

