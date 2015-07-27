package ait.sistemas.proyecto.activos.view.para.auxiliar;

import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridAuxiliar extends Grid{

	private AuxiliarImpl auxiliar_impl = new AuxiliarImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<AuxiliaresContablesModel> bean_auxiliar;

	public GridAuxiliar() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_auxiliar = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_auxiliar = new BeanItemContainer<AuxiliaresContablesModel>(
				AuxiliaresContablesModel.class);
		bean_auxiliar.addAll(auxiliar_impl.getalls());
		setContainerDataSource(bean_auxiliar);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		setWidth("100%");
		
		
		removeColumn("AUC_Fecha_Registro");
		removeColumn("AUC_Grupo_Contable_ID");
		removeColumn("AUC_Auxiliar_Grupo_Contable");
		
		setColumnOrder("AUC_Auxiliar_Contable","AUC_Nombre_Auxiliar_Contable", "AUC_Grupo_Contable");
		
		Grid.Column id_auxiliarColumn = this.getColumn("AUC_Auxiliar_Contable");
		Grid.Column nombre_auxiliarColumn = this.getColumn("AUC_Nombre_Auxiliar_Contable");
		Grid.Column id_grupoColumn = this.getColumn("AUC_Grupo_Contable");

		id_auxiliarColumn.setHeaderCaption("Codigo").setExpandRatio(1);
		nombre_auxiliarColumn.setHeaderCaption("Nombre Auxiliar Contable").setExpandRatio(4);
		id_grupoColumn.setHeaderCaption("Grupo Contable").setExpandRatio(4);
		
	//	sort("AUC_Auxiliar_Contable", SortDirection.ASCENDING);
		Responsive.makeResponsive(this);
	}
}

