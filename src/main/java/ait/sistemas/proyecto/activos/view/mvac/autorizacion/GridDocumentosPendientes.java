package ait.sistemas.proyecto.activos.view.mvac.autorizacion;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;
import ait.sistemas.proyecto.activos.data.service.Impl.AutorizacionImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.NumberRenderer;

public class GridDocumentosPendientes extends Grid {
	
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<DocumentoPendiente> bean_documentos;
	
	final AutorizacionImpl autorizacionimpl = new AutorizacionImpl();
	
	public GridDocumentosPendientes(String id_usuario) {
		setWidth("100%");
		bean_documentos = new BeanItemContainer<DocumentoPendiente>(DocumentoPendiente.class,
				autorizacionimpl.getDocumentosPendientes(id_usuario));
		setContainerDataSource(bean_documentos);
		setHeightByRows(4);
		setHeightMode(HeightMode.ROW);
		removeColumn("dependencia_id");
		removeColumn("tipo_movimiento_id");
		removeColumn("unidad_organizacional_id");
		removeColumn("nro_autorizacion");
		removeColumn("ci_solicitante");
		
		getColumn("dependencia").setExpandRatio(1);
		getColumn("fecha_movimiento").setExpandRatio(1).setHeaderCaption("Fecha Movimiento")
				.setRenderer(new DateRenderer(new SimpleDateFormat("dd-MM-yyyy")));
		getColumn("nombre_solicitante").setExpandRatio(8).setHeaderCaption("Solicitante");
		getColumn("nro_documento").setExpandRatio(1).setHeaderCaption("Nro. Documento")
				.setRenderer(new NumberRenderer(new DecimalFormat("###")));
		getColumn("tipo_movimiento").setExpandRatio(4).setHeaderCaption("Tipo Movimiento");
		getColumn("unidad_organizacional").setExpandRatio(1).setHeaderCaption("Unidad Organizacional");
		setColumnOrder("dependencia", "unidad_organizacional", "tipo_movimiento", "nro_documento", "fecha_movimiento",
				"nombre_solicitante");
		Responsive.makeResponsive(this);
	}
	
	public void update(String id_usuario) {
		setWidth("100%");
		bean_documentos = new BeanItemContainer<DocumentoPendiente>(DocumentoPendiente.class,
				autorizacionimpl.getDocumentosPendientes(id_usuario));
		setContainerDataSource(bean_documentos);
		setHeightByRows(4);
		setHeightMode(HeightMode.ROW);
		try {
			removeColumn("dependencia_id");
			removeColumn("tipo_movimiento_id");
			removeColumn("unidad_organizacional_id");
			removeColumn("nro_autorizacion");
			removeColumn("ci_solicitante");
		} catch (Exception ex) {
		}
		getColumn("dependencia").setExpandRatio(1);
		getColumn("fecha_movimiento").setExpandRatio(1).setHeaderCaption("Fecha Movimiento")
				.setRenderer(new DateRenderer(new SimpleDateFormat("dd-MM-yyyy")));
		getColumn("nombre_solicitante").setExpandRatio(8).setHeaderCaption("Solicitante");
		getColumn("nro_documento").setExpandRatio(1).setHeaderCaption("Nro. Documento")
				.setRenderer(new NumberRenderer(new DecimalFormat("###")));
		getColumn("tipo_movimiento").setExpandRatio(4).setHeaderCaption("Tipo Movimiento");
		getColumn("unidad_organizacional").setExpandRatio(1).setHeaderCaption("Unidad Organizacional");
		setColumnOrder("dependencia", "unidad_organizacional", "tipo_movimiento", "nro_documento", "fecha_movimiento",
				"nombre_solicitante");
		Responsive.makeResponsive(this);
	}
	
}
