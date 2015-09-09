package ait.sistemas.proyecto.seguridad.view.estr.opcion;

import java.util.Locale;

import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.DateRenderer;

public class GridOpcion  extends Grid{

	private MenuImpl menuImpl = new MenuImpl();
	private static final long serialVersionUID = 1L;

	public GridOpcion() {
		buildGrid(0);
	}
	public void update(long id_submenu) {
		this.removeAllColumns();
		buildGrid(id_submenu);
	}
	public void buildGrid(long id_submenu){
		BeanItemContainer<Arbol_menus> bean_subsistema = new BeanItemContainer<Arbol_menus>(
				Arbol_menus.class, this.menuImpl.getallOpcion(id_submenu));
		setContainerDataSource(bean_subsistema);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);

		removeColumn("AME_Nivel");
		removeColumn("AME_Orden");
		removeColumn("AME_Id_Subsistema");
		removeColumn("AME_Id_Menus");
		removeColumn("AME_Id_SubMenu");
		removeColumn("arbolMenus");
		removeColumn("arbolMenuses");
		
		
		setWidth("100%");
		setColumnOrder("AME_Id_Identificador", "AME_Id_Opcion",
				"AME_Nombre","AME_Icono","AME_Programa","AME_Fecha_Registro");

		Grid.Column identificadorColumn = this.getColumn("AME_Id_Identificador");
		Grid.Column id_opcionColumn = this.getColumn("AME_Id_Opcion");
		Grid.Column nombreColumn = this.getColumn("AME_Nombre");
		Grid.Column iconlColumn = this.getColumn("AME_Icono");
		Grid.Column programaColumn = this.getColumn("AME_Programa");
		Grid.Column fechaRegistroColumn = this.getColumn("AME_Fecha_Registro");
		
		identificadorColumn.setHeaderCaption("Identificador");
		id_opcionColumn.setHeaderCaption("Id. Opcion");
		nombreColumn.setHeaderCaption("Nombre Opcion	");
		iconlColumn.setHeaderCaption("Icono");
		programaColumn.setHeaderCaption("Programa");
		fechaRegistroColumn.setHeaderCaption("Fecha Registro");
		fechaRegistroColumn.setRenderer(new DateRenderer("%1$tB de %1$te, %1$tY",
				new Locale("es", "BO")));
		Responsive.makeResponsive(this);
	}
}