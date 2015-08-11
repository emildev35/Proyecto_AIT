package ait.sistemas.proyecto.activos.view.para.motivobaja;

import ait.sistemas.proyecto.activos.data.model.Motivo_Baja;
import ait.sistemas.proyecto.activos.data.service.Impl.MotivobajaImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

	public class GridMotivobaja extends Grid{

		private MotivobajaImpl motivo_impl = new MotivobajaImpl();
		private static final long serialVersionUID = 1L;
		private BeanItemContainer<Motivo_Baja> bean_motivo_baja;

		public GridMotivobaja() {
			buildGrid();
		}
		public void update() {
			this.removeAllColumns();
			bean_motivo_baja = null;
			buildGrid();
		}
		public void buildGrid(){
			bean_motivo_baja = new BeanItemContainer<Motivo_Baja>(Motivo_Baja.class);
			bean_motivo_baja.addAll(motivo_impl.getall());
			setContainerDataSource(bean_motivo_baja);
			setHeightMode(HeightMode.ROW);
			setHeightByRows(5);

			setWidth("100%");

			setColumnOrder("MBA_Motivo_Baja", "MBA_Descripcion");
			
			Grid.Column id_tiposactColumn = this.getColumn("MBA_Motivo_Baja");
			Grid.Column nombre_tiposactColumn = this.getColumn("MBA_Descripcion");
			
			id_tiposactColumn.setHeaderCaption("Codigo Motivo");
			nombre_tiposactColumn.setHeaderCaption("Nombre Motivo de Baja");
			
			Responsive.makeResponsive(this);
		}
	}

