package ait.sistemas.proyecto.activos.component.grid;

import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridDetalleActivo extends Grid {

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<Detalle> bean_detalles;

	final MovimientoImpl movimientoimpl = new MovimientoImpl();
	
	public GridDetalleActivo() {
		this.bean_detalles = new BeanItemContainer<Detalle>(Detalle.class);
		setContainerDataSource(bean_detalles);
		setSizeFull();
		setHeightByRows(3);
		setHeightMode(HeightMode.ROW);
//		Column id_activo = getColumn("id_activo").setExpandRatio(1);
//		id_activo.setHeaderCaption("Codigo");
//		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
//		nombre_activo.setHeaderCaption("Nombre Activo");
	}
	public void update(long nro_documento, short id_dependencia, short tipo_movimiento){
		removeAllColumns();
		this.bean_detalles = new BeanItemContainer<Detalle>(Detalle.class, movimientoimpl.getDetallesbyMovimiento(nro_documento, id_dependencia, tipo_movimiento));
		setContainerDataSource(bean_detalles);
		setSelectionMode(SelectionMode.NONE);
//		Column id_activo = getColumn("id_activo").setExpandRatio(1);
//		id_activo.setHeaderCaption("Codigo");
//		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
//		nombre_activo.setHeaderCaption("Nombre Activo");
	}

}
