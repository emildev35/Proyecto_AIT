package ait.sistemas.proyecto.activos.view.rrhh.autorizado;

import ait.sistemas.proyecto.activos.data.model.TipoAutorizacionModel;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoAutorizacionImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridAutorizado extends Grid {
	private TipoAutorizacionImpl tipo_autorizacion_impl = new TipoAutorizacionImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<TipoAutorizacionModel> bean_tipo_autorizacion;

	public GridAutorizado() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_tipo_autorizacion = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_tipo_autorizacion = new BeanItemContainer<TipoAutorizacionModel>(
				TipoAutorizacionModel.class, this.tipo_autorizacion_impl.getall());
		setContainerDataSource(bean_tipo_autorizacion);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		removeColumn("dependencia_id");	
		removeColumn("ci");	
		removeColumn("nivel_autorizacion_id");	
		removeColumn("tipo_movimiento_id");	
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
