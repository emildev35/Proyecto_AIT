package ait.sistemas.proyecto.seguridad.view.usua.usuario;

import ait.sistemas.proyecto.seguridad.component.model.UsuarioGridModel;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridUsuario extends Grid {

	private UsuarioImpl usuarioimpl = new UsuarioImpl();
	private static final long serialVersionUID = 1L;

	public GridUsuario() {
		buildGrid();
	}

	public void update() {
		this.removeAllColumns();
		buildGrid();
	}

	public void buildGrid() {

		BeanItemContainer<UsuarioGridModel> bean_usuario = new BeanItemContainer<UsuarioGridModel>(
				UsuarioGridModel.class, this.usuarioimpl.getGridData());
		setContainerDataSource(bean_usuario);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(10);

		removeColumn("fechaAlta");

		setWidth("100%");
		setColumnOrder("id", "CI", "fullName");

		Grid.Column id = this.getColumn("id");
		Grid.Column ci = this.getColumn("CI");
		Grid.Column full_name = this.getColumn("fullName");

		id.setHeaderCaption("Identificador");
		ci.setHeaderCaption("C.I.");
		full_name.setHeaderCaption("Nombre Completo");

		id.setExpandRatio(1);
		ci.setExpandRatio(2);
		full_name.setExpandRatio(4);
		Responsive.makeResponsive(this);
	}
}
