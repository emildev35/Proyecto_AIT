package ait.sistemas.proyecto.activos.view.para.tiposact;

import ait.sistemas.proyecto.activos.data.model.Tipos_Activo;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposactImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

	public class GridTiposact extends Grid{

		private TiposactImpl tiposact_impl = new TiposactImpl();
		private static final long serialVersionUID = 1L;
		private BeanItemContainer<Tipos_Activo> bean_tiposact;

		public GridTiposact() {
			buildGrid();
		}
		public void update() {
			this.removeAllColumns();
			bean_tiposact = null;
			buildGrid();
		}
		public void buildGrid(){
			bean_tiposact = new BeanItemContainer<Tipos_Activo>(Tipos_Activo.class);
			bean_tiposact.addAll(tiposact_impl.getall());
			setContainerDataSource(bean_tiposact);
			setHeightMode(HeightMode.ROW);
			setHeightByRows(5);

			setWidth("100%");

			setColumnOrder("TAC_Id_Tipo_Activo", "TAC_Nombre_Tipo_Activo");
			
			Grid.Column id_tiposactColumn = this.getColumn("TAC_Id_Tipo_Activo");
			Grid.Column nombre_tiposactColumn = this.getColumn("TAC_Nombre_Tipo_Activo");
			
			id_tiposactColumn.setHeaderCaption("Codigo");
			nombre_tiposactColumn.setHeaderCaption("Nombre del tipo de activo");
			
			Responsive.makeResponsive(this);
		}
	}

