package ait.sistemas.proyecto.seguridad.view.usua.opcionperfil;

import java.util.List;

import ait.sistemas.proyecto.seguridad.component.model.FullMenu;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;

public class GridOpcionPerfil extends Grid {

	private static final long serialVersionUID = 1L;
	private BeanItemContainer<FullMenu> gridPerfilPermisos = new BeanItemContainer<FullMenu>(
			FullMenu.class);

	public GridOpcionPerfil() {

	}
	public void update(List<FullMenu> datasource){
		setSelectionMode(SelectionMode.NONE);
		this.gridPerfilPermisos.removeAllItems();
		this.gridPerfilPermisos.addAll(datasource);
		setContainerDataSource(this.gridPerfilPermisos);
		setSelectionMode(SelectionMode.MULTI);
	}
}
