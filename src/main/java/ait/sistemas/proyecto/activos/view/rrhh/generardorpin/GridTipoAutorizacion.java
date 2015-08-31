package ait.sistemas.proyecto.activos.view.rrhh.generardorpin;

import ait.sistemas.proyecto.activos.data.model.TipoAutorizacionModel;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoAutorizacionImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;


public class GridTipoAutorizacion extends Grid{
	
	private static final long serialVersionUID = 1L;
	final TipoAutorizacionImpl tipo_autorizacionimpl = new TipoAutorizacionImpl();
	private BeanItemContainer<TipoAutorizacionModel> bean_autorizacion;
	
	public GridTipoAutorizacion(String usuario) {
		bean_autorizacion = new BeanItemContainer<TipoAutorizacionModel>(
				TipoAutorizacionModel.class, this.tipo_autorizacionimpl.getallbyusuario(usuario));
		setContainerDataSource(bean_autorizacion);
		setHeightMode(HeightMode.ROW);
		setSelectionMode(SelectionMode.NONE);
		setHeightByRows(4);
		removeColumn("dependencia_id");	
		removeColumn("ci");	
		removeColumn("nivel_autorizacion_id");	
		removeColumn("tipo_movimiento_id");	
		try{
		removeColumn("unidad_organizacional_id");
		}catch(Exception ex){}
		removeColumn("usuario_id");	
		removeColumn("fecha_registro");	
		
		setWidth("100%");

		setColumnOrder("tipo_movimiento", "orden","nivel_autorizacion", "dependencia","servidor_publico");
		
		getColumn("tipo_movimiento").setHeaderCaption("Tipos de Movimiento").setExpandRatio(3);
		getColumn("orden").setHeaderCaption("Orden").setExpandRatio(1);
		getColumn("nivel_autorizacion").setHeaderCaption("Nivel de Autorizacion").setExpandRatio(2);
		getColumn("dependencia").setHeaderCaption("Dependencia").setExpandRatio(3);
		getColumn("servidor_publico").setHeaderCaption("Servidor Publico").setExpandRatio(2);
	
		Responsive.makeResponsive(this);
	}
}
