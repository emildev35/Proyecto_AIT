package ait.sistemas.proyecto.activos.component.grid;

import java.text.DecimalFormat;

import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.NumberRenderer;

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
		removeColumn("id_dependencia");
		removeColumn("id_unidad_organizacional_origen");
		removeColumn("motivo_baja");
		removeColumn("observacion");
		removeColumn("tipo_movimiento");
		removeColumn("nro_documento");
		removeColumn("id_motivo_baja");
		removeColumn("fecha_registro");
		removeColumn("nro_seguro");
		removeColumn("vto_seguro");
		removeColumn("nro_garantia");
		removeColumn("vto_garantia");
		removeColumn("nueva_vida_util");
		removeColumn("nuevo_valor");
		getColumn("id_activo").setExpandRatio(1).setHeaderCaption("Codigo");
		getColumn("serie").setExpandRatio(1);
		getColumn("nombre_activo").setExpandRatio(5).setHeaderCaption("Nombre Activo");
		setColumnOrder("id_activo", "serie", "nombre_activo");
		Responsive.makeResponsive(this);
	}
	public void update(long nro_documento, short id_dependencia, short tipo_movimiento){
		removeAllColumns();
		this.bean_detalles = new BeanItemContainer<Detalle>(Detalle.class, movimientoimpl.getDetallesbyMovimiento(nro_documento, id_dependencia, tipo_movimiento));
		setContainerDataSource(bean_detalles);
		setSelectionMode(SelectionMode.NONE);
		removeColumn("id_dependencia");
		removeColumn("id_unidad_organizacional_origen");
		removeColumn("motivo_baja");
		removeColumn("observacion");
		removeColumn("tipo_movimiento");
		removeColumn("nro_documento");
		removeColumn("id_motivo_baja");
		removeColumn("fecha_registro");
		removeColumn("nro_seguro");
		removeColumn("vto_seguro");
		removeColumn("nro_garantia");
		removeColumn("vto_garantia");
		removeColumn("nueva_vida_util");
		removeColumn("nuevo_valor");
		getColumn("id_activo").setExpandRatio(1).setHeaderCaption("Codigo").setRenderer(new NumberRenderer(new DecimalFormat("###")));
		getColumn("serie").setExpandRatio(1);
		getColumn("nombre_activo").setExpandRatio(5).setHeaderCaption("Nombre Activo");
		setColumnOrder("id_activo", "serie", "nombre_activo");
		Responsive.makeResponsive(this);
	}
	
	
	public void update_salida(long nro_documento, short id_dependencia, short tipo_movimiento){
		removeAllColumns();
		this.bean_detalles = new BeanItemContainer<Detalle>(Detalle.class, movimientoimpl.getDetallesbyMovimiento(nro_documento, id_dependencia, tipo_movimiento));
		setContainerDataSource(bean_detalles);
		setSelectionMode(SelectionMode.NONE);
		removeColumn("id_dependencia");
		removeColumn("id_unidad_organizacional_origen");
		removeColumn("motivo_baja");
		removeColumn("observacion");
		removeColumn("tipo_movimiento");
		removeColumn("nro_documento");
		removeColumn("id_motivo_baja");
		removeColumn("fecha_registro");
		addColumn("nro_seguro");
		addColumn("vto_seguro");
		addColumn("nro_garantia");
		addColumn("vto_garantia");
		
		getColumn("id_activo").setExpandRatio(1).setHeaderCaption("Codigo");
		getColumn("nombre_activo").setExpandRatio(5).setHeaderCaption("Nombre Activo");
		getColumn("nro_seguro").setExpandRatio(1).setHeaderCaption("Numero Seguro");
		getColumn("vto_seguro").setExpandRatio(1).setHeaderCaption("Vencimiento Seguro");
		getColumn("nro_garantia").setExpandRatio(1).setHeaderCaption("Numero Garantia");
		getColumn("vto_garantia").setExpandRatio(1	).setHeaderCaption("Vencimiento Garantia");
		Responsive.makeResponsive(this);
	}

}
