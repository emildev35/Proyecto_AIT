package ait.sistemas.proyecto.activos.view.mant.infsoporte;

import java.util.Locale;

import ait.sistemas.proyecto.activos.component.model.SolicitudSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.SoporteImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.DateRenderer;

public class GridSolSoporte extends Grid {
	

	private static final long serialVersionUID = 1L;
	
	private BeanItemContainer<SolicitudSoporte> bean_solsoporte;
	
	private final SoporteImpl soporteimpl = new SoporteImpl();
	
	public GridSolSoporte() {
		bean_solsoporte = new BeanItemContainer<SolicitudSoporte>(SolicitudSoporte.class);
		setContainerDataSource(bean_solsoporte);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(4);
		removeColumn("tipo_movimiento");
		removeColumn("ci_solicitante");
		removeColumn("dependencia");
		removeColumn("descripcion");
		removeColumn("nombre_sistema");
		getColumn("hora").setRenderer(new DateRenderer("%tT", new Locale("es", "BO")));
		getColumn("fecha").setRenderer(new DateRenderer("%1$tA %1$td de %1$tB %1$tY",  new Locale("es", "BO")));
		getColumn("nro_documento").setHeaderCaption("Número de Documento");
		setWidth("100%");
		Responsive.makeResponsive(this);
	}
	public void update(short id_dependencia){
		bean_solsoporte = new BeanItemContainer<SolicitudSoporte>(SolicitudSoporte.class,soporteimpl.getall(id_dependencia));
		setContainerDataSource(bean_solsoporte);
	}
	
}
