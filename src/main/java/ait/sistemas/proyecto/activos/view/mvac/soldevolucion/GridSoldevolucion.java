package ait.sistemas.proyecto.activos.view.mvac.soldevolucion;

import java.text.DecimalFormat;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.NumberRenderer;

public class GridSoldevolucion extends Grid {
	
	private static final long serialVersionUID = 1L;
	
	private BeanItemContainer<ActivoGrid> bean_activos;
	
	final ActivoImpl activoimpl = new ActivoImpl();
	
	public GridSoldevolucion() {
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class);
		setContainerDataSource(bean_activos);
		setSizeFull();
		Column id_activo = getColumn("id_activo").setExpandRatio(1).setRenderer(new NumberRenderer(new DecimalFormat("###")));
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
		setColumnOrder("id_activo", "serie", "nombre");
		
	}
	
	public void update(String ci_usuario) {
		removeAllColumns();
		this.bean_activos = new BeanItemContainer<ActivoGrid>(ActivoGrid.class, activoimpl.getAsignados(ci_usuario));
		setContainerDataSource(bean_activos);
		setSelectionMode(SelectionMode.MULTI);
		Column id_activo = getColumn("id_activo").setExpandRatio(1).setRenderer(new NumberRenderer(new DecimalFormat("###")));
		id_activo.setHeaderCaption("Codigo");
		Column nombre_activo = getColumn("nombre").setExpandRatio(5);
		nombre_activo.setHeaderCaption("Nombre Activo");
		setColumnOrder("id_activo", "serie", "nombre");
	}
}
