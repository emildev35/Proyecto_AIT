package ait.sistemas.proyecto.seguridad.view.perm.otorgaryr;

import java.util.List;

import ait.sistemas.proyecto.seguridad.component.model.FullMenu;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;

public class GridOtorgar extends Grid {

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<FullMenu> gridPerfilPermisos = new BeanItemContainer<FullMenu>(
			FullMenu.class);

	public GridOtorgar() {

	}
	public void update(List<FullMenu> datasource){
		this.gridPerfilPermisos.removeAllItems();
		this.gridPerfilPermisos.addAll(datasource);
		setContainerDataSource(this.gridPerfilPermisos);
	}
}
