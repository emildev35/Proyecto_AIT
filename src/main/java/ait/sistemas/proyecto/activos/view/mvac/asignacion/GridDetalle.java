package ait.sistemas.proyecto.activos.view.mvac.asignacion;

import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

	public class GridDetalle extends Grid{

		private MovimientoImpl movimiento_impl = new MovimientoImpl();
		private static final long serialVersionUID = 1L;
		private BeanItemContainer<Detalle> bean_detalle;

		public GridDetalle() {
			bean_detalle = new BeanItemContainer<Detalle>(Detalle.class);
			setContainerDataSource(bean_detalle);
			setHeightMode(HeightMode.ROW);
			setWidth("100%");
			removeColumn("id_dependencia");
			removeColumn("id_unidad_organizacional");
			removeColumn("nro_documento");
			removeColumn("tipo_movimiento");
			removeColumn("fecha_registro");
			removeColumn("id_motivo_baja");
			removeColumn("motivo_baja");
			removeColumn("observacion");
			removeColumn("nro_garantia");
			removeColumn("nro_seguro");
			removeColumn("vto_garantia");
			removeColumn("vto_seguro");
			removeColumn("nuevo_valor");
			removeColumn("nueva_vida_util");
			
			setColumnOrder("id_activo","serie", "nombre_activo");
			 	
			Grid.Column id_activoColumn = this.getColumn("id_activo");
			Grid.Column nombre_activoColumn = this.getColumn("nombre_activo");
			
			id_activoColumn.setHeaderCaption("Codigo");
			nombre_activoColumn.setHeaderCaption("Nombre del Activo");
			
			setHeightByRows(5);
		}
		public void update(long nro_documenro, short id_dependencia, short tipo_movimiento) {
			this.removeAllColumns();
			bean_detalle = null;
			buildGrid( nro_documenro,  id_dependencia, tipo_movimiento);
		}
		public void vaciar (){
			this.removeAllColumns();
			bean_detalle = null;
			bean_detalle = new BeanItemContainer<Detalle>(Detalle.class);
			setContainerDataSource(bean_detalle);
			setHeightMode(HeightMode.ROW);
			setWidth("100%");
			removeColumn("id_dependencia");
			removeColumn("id_unidad_organizacional_origen");
			removeColumn("nro_documento");
			removeColumn("tipo_movimiento");
			removeColumn("fecha_registro");
			removeColumn("id_motivo_baja");
			removeColumn("motivo_baja");
			removeColumn("observacion");
			removeColumn("nro_garantia");
			removeColumn("nro_seguro");
			removeColumn("vto_garantia");
			removeColumn("vto_seguro");
			removeColumn("nuevo_valor");
			removeColumn("nueva_vida_util");
			setColumnOrder("id_activo","serie", "nombre_activo");
			 	
			Grid.Column id_activoColumn = this.getColumn("id_activo");
			Grid.Column nombre_activoColumn = this.getColumn("nombre_activo");
			
			id_activoColumn.setHeaderCaption("Codigo");
			nombre_activoColumn.setHeaderCaption("Nombre del Activo");
			
			setHeightByRows(5);
		}
		public void buildGrid(long nro_documenro, short id_dependencia, short tipo_movimiento){
			bean_detalle = new BeanItemContainer<Detalle>(Detalle.class);
			bean_detalle.addAll(movimiento_impl.getDetallesbyMovimiento(nro_documenro, id_dependencia, tipo_movimiento));
			setContainerDataSource(bean_detalle);

//			removeColumn("id_detalle");
			removeColumn("id_dependencia");
			removeColumn("id_unidad_organizacional_origen");
			removeColumn("nro_documento");
			removeColumn("tipo_movimiento");
			removeColumn("fecha_registro");
			removeColumn("id_motivo_baja");
			removeColumn("motivo_baja");
			removeColumn("observacion");
			removeColumn("nro_garantia");
			removeColumn("nro_seguro");
			removeColumn("vto_garantia");
			removeColumn("vto_seguro");
			removeColumn("nuevo_valor");
			removeColumn("nueva_vida_util");

			setColumnOrder("id_activo","serie", "nombre_activo");
			
			Grid.Column id_activoColumn = this.getColumn("id_activo");
			Grid.Column nombre_activoColumn = this.getColumn("nombre_activo");
			
			id_activoColumn.setHeaderCaption("Codigo");
			nombre_activoColumn.setHeaderCaption("Nombre del Activo");
			
			Responsive.makeResponsive(this);
		}
	}

