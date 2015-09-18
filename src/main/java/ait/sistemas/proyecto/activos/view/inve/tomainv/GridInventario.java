package ait.sistemas.proyecto.activos.view.inve.tomainv;

import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoInventario;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

/**
 * Grid asociado a la Clase ActivoInventario
 * 
 * @author franzemil
 *
 */
public class GridInventario extends Grid {
	
	private static final long serialVersionUID = 1L;
	
	private BeanItemContainer<ActivoInventario> bean_inventario;
	
	public GridInventario() {
		bean_inventario = new BeanItemContainer<ActivoInventario>(ActivoInventario.class);
		setWidth("100%");
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
	}
	
	public void buildGrid(List<ActivoInventario> activos) {
		bean_inventario.addAll(activos);
		setContainerDataSource(bean_inventario);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		setWidth("100%");
		setColumnOrder("id_activo", "nombre_activo", "observaciones");
		getColumn("id_activo").setHeaderCaption("Codigo Activo").setExpandRatio(1);
		getColumn("nombre_activo").setHeaderCaption("Nombre del Activo").setExpandRatio(7);
		getColumn("observaciones").setHeaderCaption("Observaciones").setExpandRatio(6);
		
		// sort("AUC_Auxiliar_Contable", SortDirection.ASCENDING);
		Responsive.makeResponsive(this);
	}
	public void clean(){
		removeAllColumns();
	}
}
