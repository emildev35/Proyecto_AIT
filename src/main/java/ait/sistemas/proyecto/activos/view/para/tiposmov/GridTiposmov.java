package ait.sistemas.proyecto.activos.view.para.tiposmov;

import ait.sistemas.proyecto.activos.data.model.Tipos_Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposmovImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

	public class GridTiposmov extends Grid{

		private TiposmovImpl tiposmov_impl = new TiposmovImpl();
		private static final long serialVersionUID = 1L;
		private BeanItemContainer<Tipos_Movimiento> bean_tiposmov;

		public GridTiposmov() {
			buildGrid();
		}
		public void update() {
			this.removeAllColumns();
			bean_tiposmov = null;
			buildGrid();
		}
		public void buildGrid(){
			bean_tiposmov = new BeanItemContainer<Tipos_Movimiento>(Tipos_Movimiento.class);
			bean_tiposmov.addAll(tiposmov_impl.getall());
			setContainerDataSource(bean_tiposmov);
			setHeightMode(HeightMode.ROW);
			setHeightByRows(5);

			removeColumn("TMV_Fecha_Registro");	
			
			setWidth("100%");

			setColumnOrder("TMV_Tipo_Movimiento", "TMV_Nombre_Tipo_Movimiento","TMV_Sigla_Tipo_Movimiento");
			
			Grid.Column id_tiposmovColumn = this.getColumn("TMV_Tipo_Movimiento");
			Grid.Column nombre_tiposmovColumn = this.getColumn("TMV_Nombre_Tipo_Movimiento");
			Grid.Column sigla_tiposmovColumn = this.getColumn("TMV_Sigla_Tipo_Movimiento");
			
			id_tiposmovColumn.setHeaderCaption("Codigo");
			nombre_tiposmovColumn.setHeaderCaption("Nombre del tipo de movimiento");
			sigla_tiposmovColumn.setHeaderCaption("Sigla");
			
			Responsive.makeResponsive(this);
		}
	}

