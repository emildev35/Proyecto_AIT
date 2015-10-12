package ait.sistemas.proyecto.activos.view.mvac.autorizacion;

import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;
import ait.sistemas.proyecto.activos.data.service.Impl.AutorizacionImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

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
		
		getColumn("dependencia").setExpandRatio(2);
		getColumn("fecha_movimiento").setExpandRatio(1).setHeaderCaption("Fecha Movimiento");
		getColumn("nombre_solicitante").setExpandRatio(2).setHeaderCaption("Solicitante");
		getColumn("nro_documento").setExpandRatio(1).setHeaderCaption("Nro. Documento");
		getColumn("tipo_movimiento").setExpandRatio(2).setHeaderCaption("Tipo Movimiento");
		getColumn("unidad_organizacional").setExpandRatio(2).setHeaderCaption("Unidad Organizacional");
		setColumnOrder("dependencia", "unidad_organizacional", "tipo_movimiento", "nro_documento", "fecha_movimiento",
				"nombre_solicitante");
		Responsive.makeResponsive(this);
	}
	
}
