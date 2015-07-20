package ait.sistemas.proyecto.activos.view.rrhh.personal;

import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
import ait.sistemas.proyecto.activos.data.service.Impl.PersonalImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridPersonal extends Grid{

	private PersonalImpl personal_impl = new PersonalImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<PersonalModel> bean_personal;

	public GridPersonal() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_personal = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_personal = new BeanItemContainer<PersonalModel>(PersonalModel.class);
		bean_personal.addAll(personal_impl.getalls());
		setContainerDataSource(bean_personal);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		removeColumn("PER_Dependencia_ID");
		removeColumn("PER_Unidad_Organizacional_ID");
	//	removeColumn("PER_No_Interno");	
		removeColumn("PER_Fecha_Registro");	
		
		setWidth("100%");

		setColumnOrder("PER_CI_Empleado", "PER_Nombres","PER_Apellido_Paterno",
				"PER_Apellido_Materno","PER_No_Telefono_Oficina","PER_No_Interno");
		
		Grid.Column ci_empleadoColumn = this.getColumn("PER_CI_Empleado");
		Grid.Column nombresColumn = this.getColumn("PER_Nombres");
		Grid.Column ap_paternoColumn = this.getColumn("PER_Apellido_Paterno");
		Grid.Column ap_maternoColumn = this.getColumn("PER_Apellido_Materno");
		Grid.Column telefonoColumn = this.getColumn("PER_No_Telefono_Oficina");
		Grid.Column internoColumn = this.getColumn("PER_No_Interno");
		Grid.Column dependenciaColumn = this.getColumn("PER_Dependencia");
		Grid.Column unidadColumn = this.getColumn("PER_Unidad_Organizacional");

		ci_empleadoColumn.setHeaderCaption("Carnet Identidad");
		nombresColumn.setHeaderCaption("Nombres");
		ap_paternoColumn.setHeaderCaption("Apellido Paterno");
		ap_maternoColumn.setHeaderCaption("Apellido Materno");
		telefonoColumn.setHeaderCaption("Telefono");
		internoColumn.setHeaderCaption("Interno");
		dependenciaColumn.setHeaderCaption("Dependencia");
		unidadColumn.setHeaderCaption("Unidad Organizacional");
		Responsive.makeResponsive(this);
	}
}

