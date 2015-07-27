package ait.sistemas.proyecto.activos.view.rrhh.dependencia;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridDependencia extends Grid{

	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<Dependencia> bean_dependencia;

	
	public GridDependencia() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_dependencia = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_dependencia = new BeanItemContainer<Dependencia>(
				Dependencia.class);
		bean_dependencia.addAll(this.dependencia_impl.getall());
		setContainerDataSource(bean_dependencia);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		removeColumn("DEP_Ciudad");
		removeColumn("DEP_Fecha_Registro");	
		
		setWidth("100%");

		setColumnOrder("DEP_Dependencia", "DEP_Nombre_Dependencia","DEP_Sigla",
				"DEP_Domicilio","DEP_Pagina_Web","DEP_Telefono");
		
		Grid.Column id_dependenciaColumn = this.getColumn("DEP_Dependencia");
		Grid.Column nombre_dependenciaColumn = this.getColumn("DEP_Nombre_Dependencia");
		Grid.Column sigla_dependenciaColumn = this.getColumn("DEP_Sigla");
		Grid.Column domicilio_dependenciaColumn = this.getColumn("DEP_Domicilio");
		Grid.Column pagina_web_dependenciaColumn = this.getColumn("DEP_Pagina_Web");
		Grid.Column telefono_dependenciaColumn = this.getColumn("DEP_Telefono");

		id_dependenciaColumn.setHeaderCaption("Id. Dependencia");
		nombre_dependenciaColumn.setHeaderCaption("Nombre Motivo de Dependencia").setExpandRatio(6);
		sigla_dependenciaColumn.setHeaderCaption("Sigla Ciudad");
		domicilio_dependenciaColumn.setHeaderCaption("Domicilio").setExpandRatio(4);
		pagina_web_dependenciaColumn.setHeaderCaption("Pagina Web").setExpandRatio(4);
		telefono_dependenciaColumn.setHeaderCaption("Telefono");
		Responsive.makeResponsive(this);
	}
}

