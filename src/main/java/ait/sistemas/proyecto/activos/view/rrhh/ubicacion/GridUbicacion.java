package ait.sistemas.proyecto.activos.view.rrhh.ubicacion;

import ait.sistemas.proyecto.activos.data.model_rrhh.UbicacionesFisicasModel;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UbicacionImpl;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

public class GridUbicacion extends Grid{

	private UbicacionImpl ubicacion_impl = new UbicacionImpl();
	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<UbicacionesFisicasModel> bean_ubicacion;

	public GridUbicacion() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_ubicacion = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_ubicacion = new BeanItemContainer<UbicacionesFisicasModel>(
				UbicacionesFisicasModel.class);
		SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
		
		 short dependencia = dependencia_impl.getdependencia_ID(usuario.getDependecia());
		bean_ubicacion.addAll(ubicacion_impl.getalls(dependencia));
		setContainerDataSource(bean_ubicacion);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		setWidth("100%");
		
		
		removeColumn("UBF_Fecha_Registro");	
		removeColumn("UBF_Dependencia");	
		removeColumn("UBF_Inmueble_ID");	
		

		setColumnOrder("UBF_Ubicacion_Fisica","UBF_Nombre_Ubicacion_Fisica", "UBF_Inmueble");
		
		Grid.Column id_ubicacionColumn = this.getColumn("UBF_Ubicacion_Fisica");
		Grid.Column nombre_ubicacionColumn = this.getColumn("UBF_Nombre_Ubicacion_Fisica");
		Grid.Column id_inmuebleColumn = this.getColumn("UBF_Inmueble");

		id_ubicacionColumn.setHeaderCaption("Codigo de Ubicacion").setExpandRatio(1);
		nombre_ubicacionColumn.setHeaderCaption("Nombre de la Ubicacion Fisica").setExpandRatio(4);
		id_inmuebleColumn.setHeaderCaption("Inmueble").setExpandRatio(4);
		
		sort("UBF_Ubicacion_Fisica", SortDirection.ASCENDING);
		Responsive.makeResponsive(this);
	}
}

