package ait.sistemas.proyecto.activos.view.mvac.solactivos;

import java.text.DecimalFormat;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.NumberRenderer;

public class GridSolactivos extends Grid {
	
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<ActivoGrid> bean_activos;
	
	final ActivoImpl activoimpl = new ActivoImpl();
	
	public GridSolactivos() {
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class);
		setContainerDataSource(bean_activos);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(6);
		setSizeFull();
		Column id_activo = getColumn("id_activo").setExpandRatio(1).setRenderer(new NumberRenderer(new DecimalFormat("####")));
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
		id_activo.setExpandRatio(1);
		nombre_activo.setExpandRatio(15);
		getColumn("serie").setExpandRatio(2);
		setColumnOrder("id_activo", "serie", "nombre");
	}
	
	/**
	 * Llena el Grid con los Activos de la Base que esten Disponbles (Que no
	 * esten dados de baja ni esten pendientes dentro de una solicitud)
	 * 
	 * @param grupo_contable
	 * @param auxiliar_contable
	 */
	public void update(String grupo_contable, String auxiliar_contable) {
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getDisponibles(grupo_contable,
				auxiliar_contable));
		setContainerDataSource(bean_activos);
		setSelectionMode(SelectionMode.MULTI);
		Column id_activo = getColumn("id_activo").setExpandRatio(1).setRenderer(new NumberRenderer(new DecimalFormat("####")));
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
		setColumnOrder("id_activo", "serie", "nombre");
		id_activo.setExpandRatio(1);
		nombre_activo.setExpandRatio(15);
		getColumn("serie").setExpandRatio(2);
	}
	
	public void updateasignados(String ci_usuario) {
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getAsignados(ci_usuario));
		setContainerDataSource(bean_activos);
		setSelectionMode(SelectionMode.MULTI);
		Column id_activo = getColumn("id_activo").setExpandRatio(1).setRenderer(new NumberRenderer(new DecimalFormat("####")));
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
	}
	
	public void update(List<ActivoGrid> listaActivos) {
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, listaActivos);
		setContainerDataSource(bean_activos);
		setSelectionMode(SelectionMode.MULTI);
		Column id_activo = getColumn("id_activo").setExpandRatio(1).setRenderer(new NumberRenderer(new DecimalFormat("####")));
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
		setColumnOrder("id_activo", "serie", "nombre");
		id_activo.setExpandRatio(1);
		nombre_activo.setExpandRatio(15);
		getColumn("serie").setExpandRatio(2);
		
	}
}
