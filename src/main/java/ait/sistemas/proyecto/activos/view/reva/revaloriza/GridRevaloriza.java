package ait.sistemas.proyecto.activos.view.reva.revaloriza;

import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoInventario;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
/**
 * Grid asociado a la Clase ActivoInventario
 * @author Kim
 *
 */
public class GridRevaloriza extends Grid{

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<ActivoInventario> bean_inventario;
	
	public GridRevaloriza() {
		bean_inventario = new BeanItemContainer<ActivoInventario>(ActivoInventario.class);
		setContainerDataSource(bean_inventario);
		setWidth("100%");
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		removeColumn("ci_funcionario");
		removeColumn("dependencia");
		removeColumn("documento_referencia");
		removeColumn("dr");
		removeColumn("sr");
		removeColumn("mr");
		removeColumn("fecha_referencia");
		removeColumn("fecha_registro");
		removeColumn("nombre_funcionario");
		removeColumn("numero_documento");
		removeColumn("observacion");
	}
	/**
	 * LLena del Grid con los datos enviados
	 * @param activos
	 */
	public void buildGrid(List<ActivoInventario> activos) {
		bean_inventario.addAll(activos);
		setContainerDataSource(bean_inventario);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		setWidth("100%");
		

		setColumnOrder("codigo_activo", "nombre_activo", "nuevo_valor","nueva_vida_util");
		getColumn("codigo_activo").setHeaderCaption("Codigo").setExpandRatio(0);
		getColumn("nombre_activo").setHeaderCaption("Nombre del Activo").setExpandRatio(7);
		getColumn("nuevo_valor").setHeaderCaption("Nuevo Valor").setExpandRatio(1);
		getColumn("nueva_vida_util").setHeaderCaption("Nueva Vida Util").setExpandRatio(1);
		
		// sort("AUC_Auxiliar_Contable", SortDirection.ASCENDING);
		Responsive.makeResponsive(this);
	}
	public void clean(){
		removeAllColumns();
		
		bean_inventario = new BeanItemContainer<ActivoInventario>(ActivoInventario.class);
		setContainerDataSource(bean_inventario);
		removeColumn("ci_funcionario");
		removeColumn("dependencia");
		removeColumn("documento_referencia");
		removeColumn("dr");
		removeColumn("sr");
		removeColumn("mr");
		removeColumn("fecha_referencia");
		removeColumn("fecha_registro");
		removeColumn("nombre_funcionario");
		removeColumn("numero_documento");
		removeColumn("observacion");
	}
}
