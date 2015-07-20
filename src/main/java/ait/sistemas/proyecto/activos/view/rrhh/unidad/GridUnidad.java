package ait.sistemas.proyecto.activos.view.rrhh.unidad;

import ait.sistemas.proyecto.activos.data.model_rrhh.UnidadesOrganizacionalesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.UnidadImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridUnidad extends Grid{

	private UnidadImpl unidad_impl = new UnidadImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<UnidadesOrganizacionalesModel> bean_unidad;

	public GridUnidad() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_unidad = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_unidad = new BeanItemContainer<UnidadesOrganizacionalesModel>(
				UnidadesOrganizacionalesModel.class);
		bean_unidad.addAll(unidad_impl.getalls());
		setContainerDataSource(bean_unidad);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		setWidth("100%");
		
		
		removeColumn("UNO_Fecha_Registro");	
		removeColumn("UNO_Dependencia_ID");	
		

		setColumnOrder("UNO_Unidad_Organizacional","UNO_Dependencia", "UNO_Nombre_Unidad_Organizacional");
		
		Grid.Column id_unidadColumn = this.getColumn("UNO_Unidad_Organizacional");
		Grid.Column id_dependenciaColumn = this.getColumn("UNO_Dependencia");
		Grid.Column nombre_unidadColumn = this.getColumn("UNO_Nombre_Unidad_Organizacional");

		id_unidadColumn.setHeaderCaption("Codigo de Unidad").setExpandRatio(1);
		id_dependenciaColumn.setHeaderCaption("Dependencia").setExpandRatio(4);
		nombre_unidadColumn.setHeaderCaption("Nombre Unidad Organizacional").setExpandRatio(4);
		
		sort("UNO_Unidad_Organizacional", SortDirection.ASCENDING);
		Responsive.makeResponsive(this);
	}
}

