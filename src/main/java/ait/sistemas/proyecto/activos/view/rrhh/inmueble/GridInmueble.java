package ait.sistemas.proyecto.activos.view.rrhh.inmueble;

import ait.sistemas.proyecto.activos.data.model_rrhh.InmuebleModel;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.InmuebleImpl;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

public class GridInmueble extends Grid{

	private InmuebleImpl inmueble_impl = new InmuebleImpl();
	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<InmuebleModel> bean_inmueble;

	
	public GridInmueble() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_inmueble = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_inmueble = new BeanItemContainer<InmuebleModel>(
				InmuebleModel.class);
		SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
		
		 short dependencia = dependencia_impl.getdependencia_ID(usuario.getDependecia());
		bean_inmueble.addAll(this.inmueble_impl.getalls(dependencia));
		setContainerDataSource(bean_inmueble);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		removeColumn("INM_Fecha_Registro");	
		removeColumn("INM_Dependencia_ID");	
		removeColumn("INM_Ciudad_ID");	
		
		setWidth("100%");

		setColumnOrder("INM_Inmueble", "INM_Nombre_Inmueble","INM_Ciudad",
				"INM_Domicilio_Inmueble");
		
		Grid.Column id_inmuebleColumn = this.getColumn("INM_Inmueble");
		Grid.Column nombre_inmuebleColumn = this.getColumn("INM_Nombre_Inmueble");
		Grid.Column id_ciudadColumn = this.getColumn("INM_Ciudad");
		Grid.Column domicilioColumn = this.getColumn("INM_Domicilio_Inmueble");

		id_inmuebleColumn.setHeaderCaption("Codigo");
		nombre_inmuebleColumn.setHeaderCaption("Nombre del Inmueble").setExpandRatio(6);
		id_ciudadColumn.setHeaderCaption("Ciudad").setExpandRatio(2);
		domicilioColumn.setHeaderCaption("Domicilio").setExpandRatio(4);
		Responsive.makeResponsive(this);
	}
}

