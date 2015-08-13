package ait.sistemas.proyecto.activos.view.mvac.actbaja;

import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

	public class GridActbaja extends Grid{

		private MovimientoImpl movimiento_impl = new MovimientoImpl();
		private static final long serialVersionUID = 1L;
		private BeanItemContainer<Detalle> bean_detalle;

		public GridActbaja() {
		
		}
		public void update(long nro_documenro, short id_dependencia) {
			this.removeAllColumns();
			bean_detalle = null;
			buildGrid( nro_documenro,  id_dependencia);
		}
		public void buildGrid(long nro_documenro, short id_dependencia){
			bean_detalle = new BeanItemContainer<Detalle>(Detalle.class);
			bean_detalle.addAll(movimiento_impl.getDetallesbyMovimiento(nro_documenro, id_dependencia));
			setContainerDataSource(bean_detalle);
			setHeightMode(HeightMode.ROW);
			setHeightByRows(5);

			removeColumn("id_unidad_organizacional_origen");
			
			setWidth("100%");

			setColumnOrder("id_activo", "Nombre_Activo","Motivo_Baja","observacion");
			
			Grid.Column id_activoColumn = this.getColumn("id_activo");
			Grid.Column nombre_activoColumn = this.getColumn("Nombre_Activo");
			Grid.Column motivo_bajaColumn = this.getColumn("Motivo_Baja");
			Grid.Column observacionColumn = this.getColumn("observacion");
			
			id_activoColumn.setHeaderCaption("Codigo");
			nombre_activoColumn.setHeaderCaption("Nombre del Activo");
			motivo_bajaColumn.setHeaderCaption("Causal o motivo de baja");
			observacionColumn.setHeaderCaption("Observaciones");
			
			Responsive.makeResponsive(this);
		}
	}

