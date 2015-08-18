package ait.sistemas.proyecto.activos.view.mvac.autorizacion;

import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;
import ait.sistemas.proyecto.activos.data.service.Impl.AutorizacionImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridDocumentosPendientes extends Grid{
	
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<DocumentoPendiente> bean_documentos;

	final AutorizacionImpl autorizacionimpl = new AutorizacionImpl();
	
	public GridDocumentosPendientes(String id_usuario) {
		setWidth("100%");
		bean_documentos = new BeanItemContainer<DocumentoPendiente>(DocumentoPendiente.class, autorizacionimpl.getDocumentosPendientes(id_usuario));
		setContainerDataSource(bean_documentos);
		setHeightByRows(3);
		setHeightMode(HeightMode.ROW);
		Responsive.makeResponsive(this);
	}

}
