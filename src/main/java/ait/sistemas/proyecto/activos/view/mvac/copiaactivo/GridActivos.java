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
	/**
	 * muestra los activos que no estan dados de baja
	 */
	public GridActivos() {
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class);
		setContainerDataSource(bean_activos);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(10);
		setWidth("100%");
		
		setColumnOrder("id_activo","serie", "nombre");
		
		Column id_activo = getColumn("id_activo").setExpandRatio(0);
		id_activo.setHeaderCaption("Codigo");
		Column serie = getColumn("serie").setExpandRatio(2);
		serie.setHeaderCaption("Serie");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
	}
	public void update(String grupo_contable, String auxiliar_contable){
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getActivosbyACyGC(grupo_contable, auxiliar_contable));
		setContainerDataSource(bean_activos);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(10);
		setWidth("100%");
		
//		setSelectionMode(SelectionMode.MULTI);
		setColumnOrder("id_activo","serie", "nombre");
		Column id_activo = getColumn("id_activo").setExpandRatio(0);
		id_activo.setHeaderCaption("Codigo");
		id_activo.setRenderer(new NumberRenderer(
			    new DecimalFormat("####")));
		Column serie = getColumn("serie").setExpandRatio(2);
		serie.setHeaderCaption("Serie");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
	}
	public void updateActivo(String cod_Activo){
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getActivoGrid(Long.parseLong(cod_Activo)));
		setContainerDataSource(bean_activos);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(10);
		setWidth("100%");
		
//		setSelectionMode(SelectionMode.MULTI);
		setColumnOrder("id_activo","serie", "nombre");
		Column id_activo = getColumn("id_activo").setExpandRatio(0);
		id_activo.setHeaderCaption("Codigo");
		id_activo.setRenderer(new NumberRenderer(
				new DecimalFormat("####")));
		Column serie = getColumn("serie").setExpandRatio(2);
		serie.setHeaderCaption("Serie");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
	}
	
}
