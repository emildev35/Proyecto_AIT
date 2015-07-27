package ait.sistemas.proyecto.activos.view.para.partida;

import ait.sistemas.proyecto.activos.data.model.Partidas_Presupuestaria;
import ait.sistemas.proyecto.activos.data.service.Impl.PartidaImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

	public class GridPartida extends Grid{

		private PartidaImpl partida_impl = new PartidaImpl();
		private static final long serialVersionUID = 1L;
		private BeanItemContainer<Partidas_Presupuestaria> bean_partida;

		public GridPartida() {
			buildGrid();
		}
		public void update() {
			this.removeAllColumns();
			bean_partida = null;
			buildGrid();
		}
		public void buildGrid(){
			bean_partida = new BeanItemContainer<Partidas_Presupuestaria>(Partidas_Presupuestaria.class);
			bean_partida.addAll(partida_impl.getall());
			setContainerDataSource(bean_partida);
			setHeightMode(HeightMode.ROW);
			setHeightByRows(5);

			removeColumn("PAP_Fecha_Registro");
			
			setWidth("100%");

			setColumnOrder("PAP_Partida", "PAP_Nombre_Partida");
			
			Grid.Column id_tiposactColumn = this.getColumn("PAP_Partida");
			Grid.Column nombre_tiposactColumn = this.getColumn("PAP_Nombre_Partida");
			
			id_tiposactColumn.setHeaderCaption("Codigo");
			nombre_tiposactColumn.setHeaderCaption("Nombre Partidas Presupuestarias");
			
			Responsive.makeResponsive(this);
		}
	}

