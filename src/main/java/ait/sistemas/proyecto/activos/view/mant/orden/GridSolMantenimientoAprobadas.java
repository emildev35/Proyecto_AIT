package ait.sistemas.proyecto.activos.view.mant.orden;

import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;
import ait.sistemas.proyecto.activos.data.service.Impl.MantenimientoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridSolMantenimientoAprobadas extends Grid {
	
	private static final long serialVersionUID = 1L;
	private final MantenimientoImpl mantenimientoimpl = new MantenimientoImpl();
	private BeanItemContainer<DocumentoPendiente> bean_solmantenimiento;
	
	public GridSolMantenimientoAprobadas() {
		setWidth("100%");
		bean_solmantenimiento = new BeanItemContainer<DocumentoPendiente>(DocumentoPendiente.class,
				mantenimientoimpl.getMantenimientosAprobados());
		setContainerDataSource(bean_solmantenimiento);
		setHeightByRows(3);
		setHeightMode(HeightMode.ROW);
		removeColumn("nro_autorizacion");
		removeColumn("dependencia_id");
		removeColumn("unidad_organizacional_id");
		removeColumn("tipo_movimiento_id");
		removeColumn("ci_solicitante");
		removeColumn("tipo_movimiento");
		removeColumn("dependencia");
		removeColumn("unidad_organizacional");
		Responsive.makeResponsive(this);
	}
	
	public void update(short dependencia) {
		bean_solmantenimiento = new BeanItemContainer<DocumentoPendiente>(DocumentoPendiente.class,
				mantenimientoimpl.getMantenimientosAprobados());
		setContainerDataSource(bean_solmantenimiento);
		setHeightByRows(3);
		setHeightMode(HeightMode.ROW);
	}
}
