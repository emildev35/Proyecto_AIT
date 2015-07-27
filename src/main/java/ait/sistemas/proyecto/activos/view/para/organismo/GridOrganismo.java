package ait.sistemas.proyecto.activos.view.para.organismo;

import ait.sistemas.proyecto.activos.data.model.Organismo_Financiador;
import ait.sistemas.proyecto.activos.data.service.Impl.OrganismoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

	public class GridOrganismo extends Grid{

		private OrganismoImpl organismo_impl = new OrganismoImpl();
		private static final long serialVersionUID = 1L;
		private BeanItemContainer<Organismo_Financiador> bean_organismo;

		public GridOrganismo() {
			buildGrid();
		}
		public void update() {
			this.removeAllColumns();
			bean_organismo = null;
			buildGrid();
		}
		public void buildGrid(){
			bean_organismo = new BeanItemContainer<Organismo_Financiador>(Organismo_Financiador.class);
			bean_organismo.addAll(organismo_impl.getall());
			setContainerDataSource(bean_organismo);
			setHeightMode(HeightMode.ROW);
			setHeightByRows(5);

			removeColumn("ORF_Fecha_Registro");
			
			setWidth("100%");

			setColumnOrder("ORF_Organismo_Financiador", "ORF_Nombre_Organismo_Financiador");
			
			Grid.Column id_tiposactColumn = this.getColumn("ORF_Organismo_Financiador");
			Grid.Column nombre_tiposactColumn = this.getColumn("ORF_Nombre_Organismo_Financiador");
			
			id_tiposactColumn.setHeaderCaption("Codigo");
			nombre_tiposactColumn.setHeaderCaption("Nombre Organismo de Financiamiento");
			
			Responsive.makeResponsive(this);
		}
	}

