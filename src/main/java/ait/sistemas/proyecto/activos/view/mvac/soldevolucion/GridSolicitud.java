package ait.sistemas.proyecto.activos.view.mvac.soldevolucion;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

public class GridSolicitud extends Grid{

	private static final long serialVersionUID = 1L;
	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private MovimientoImpl movimiento_impl = new MovimientoImpl();
	private BeanItemContainer<ActivoGrid> bean_movimiento;

	
	public GridSolicitud() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_movimiento = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_movimiento = new BeanItemContainer<ActivoGrid>(ActivoGrid.class);
		setSelectionMode(SelectionMode.MULTI);
		SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
		
		 short dependencia = dependencia_impl.getdependencia_ID(usuario.getDependecia());
		bean_movimiento.addAll(this.movimiento_impl.activos_asinados(dependencia));
		setContainerDataSource(bean_movimiento);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		setWidth("100%");

		setColumnOrder("id_activo", "nombre");
		
		Grid.Column cod_activoColumn = this.getColumn("id_activo");
		Grid.Column nombre_activoColumn = this.getColumn("nombre");

		cod_activoColumn.setHeaderCaption("Codigo");
		nombre_activoColumn.setHeaderCaption("Nombre del Activo").setExpandRatio(6);
		Responsive.makeResponsive(this);
	}

}
