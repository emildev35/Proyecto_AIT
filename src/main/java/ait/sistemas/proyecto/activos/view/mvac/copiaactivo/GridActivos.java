package ait.sistemas.proyecto.activos.view.mvac.copiaactivo;

import java.text.DecimalFormat;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.NumberRenderer;

public class GridActivos extends Grid{

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<ActivoGrid> bean_activos;

	final ActivoImpl activoimpl = new ActivoImpl();
	
	public GridActivos() {
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class);
		setContainerDataSource(bean_activos);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(11);
		setWidth("100%");
		
		Column id_activo = getColumn("id_activo").setExpandRatio(0);
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
	}
	public void update(String grupo_contable, String auxiliar_contable){
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getActivosbyACyGC(grupo_contable, auxiliar_contable));
		setContainerDataSource(bean_activos);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(11);
		setWidth("100%");
		
//		setSelectionMode(SelectionMode.MULTI);
		Column id_activo = getColumn("id_activo").setExpandRatio(0);
		id_activo.setHeaderCaption("Codigo");
		id_activo.setRenderer(new NumberRenderer(
			    new DecimalFormat("####")));
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
	}
	
//	public void updateasignados(String ci_usuario){
//		removeAllColumns();
//		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getAsignados(ci_usuario));
//		setContainerDataSource(bean_activos);
//		setSelectionMode(SelectionMode.MULTI);
//		Column id_activo = getColumn("id_activo").setExpandRatio(1);
//		id_activo.setHeaderCaption("Codigo");
//		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
//		nombre_activo.setHeaderCaption("Nombre Activo");
//	}
}
