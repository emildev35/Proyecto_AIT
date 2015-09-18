package ait.sistemas.proyecto.activos.view.mvac.soltransferencia;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridSoltransferencia extends Grid{

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<ActivoGrid> bean_activos;

	final ActivoImpl activoimpl = new ActivoImpl();
	
	public GridSoltransferencia() {
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class);
		setContainerDataSource(bean_activos);
		setSizeFull();
		Column id_activo = getColumn("id_activo").setExpandRatio(1);
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
	}
	public void update(short dependencia,String grupo_contable, String auxiliar_contable){
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getActivosDisponibles(dependencia, grupo_contable, auxiliar_contable));
		setContainerDataSource(bean_activos);
		setSelectionMode(SelectionMode.MULTI);
		
		Column id_activo = getColumn("id_activo").setExpandRatio(1);
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
	}
}
