package ait.sistemas.proyecto.activos.view.mvac.soltransferencia;

import java.text.DecimalFormat;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.NumberRenderer;

public class GridSoltransferencia extends Grid{

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<ActivoGrid> bean_activos;

	final ActivoImpl activoimpl = new ActivoImpl();
	
	public GridSoltransferencia() {
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class);
		setContainerDataSource(bean_activos);
		setSizeFull();
		setColumnOrder("id_activo", "serie", "nombre");
		Column id_activo = getColumn("id_activo").setExpandRatio(0);
		id_activo.setHeaderCaption("Codigo");
		Column serie = getColumn("serie").setExpandRatio(2);
		serie.setHeaderCaption("Serie");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
		setHeightMode(HeightMode.ROW);
		setHeightByRows(9);
	}
	public void update(short dependencia,String grupo_contable, String auxiliar_contable){
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getActivosDisponibles(dependencia, grupo_contable, auxiliar_contable));
		setContainerDataSource(bean_activos);
		setSelectionMode(SelectionMode.MULTI);
		setColumnOrder("id_activo", "serie", "nombre");
		Column id_activo = getColumn("id_activo").setExpandRatio(0);
		id_activo.setHeaderCaption("Codigo");
		id_activo.setRenderer(new NumberRenderer(
			    new DecimalFormat("####")));
		Column serie = getColumn("serie").setExpandRatio(2);
		serie.setHeaderCaption("Serie");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
		setHeightByRows(9);
	}
}
