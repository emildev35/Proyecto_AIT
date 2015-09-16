package ait.sistemas.proyecto.seguridad.view.estr.menu;

import java.util.Locale;

import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.DateRenderer;

public class GridMenu extends Grid{

	private MenuImpl menuImpl = new MenuImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<Arbol_menus> bean_subsistema;

	public GridMenu() {
		buildGrid(0);
	}
	public void update(long id_subsistema) {
		this.removeAllColumns();
		bean_subsistema = null;
		buildGrid(id_subsistema);
	}
	public void buildGrid(long id_subsistema){
		menuImpl = new MenuImpl();
		bean_subsistema = new BeanItemContainer<Arbol_menus>(Arbol_menus.class);		
		bean_subsistema.addAll(menuImpl.getallMenus(id_subsistema));
		setContainerDataSource(bean_subsistema);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);

		removeColumn("AME_Nivel");
		removeColumn("AME_Orden");
		removeColumn("AME_Id_Subsistema");
		removeColumn("AME_Id_SubMenu");
		removeColumn("AME_Id_Opcion");
		removeColumn("arbolMenus");
		removeColumn("arbolMenuses");
		removeColumn("AME_NavegacionRedireccion");
		
		
		setWidth("100%");
		setColumnOrder("AME_Id_Identificador", "AME_Id_Menus",
				"AME_Nombre","AME_Icono","AME_Programa","AME_Fecha_Registro");

		Grid.Column identificadorColumn = this.getColumn("AME_Id_Identificador");
		Grid.Column id_menuColumn = this.getColumn("AME_Id_Menus");
		Grid.Column nombreColumn = this.getColumn("AME_Nombre");
		Grid.Column iconlColumn = this.getColumn("AME_Icono");
		Grid.Column programaColumn = this.getColumn("AME_Programa");
		Grid.Column fechaRegistroColumn = this.getColumn("AME_Fecha_Registro");
		
		identificadorColumn.setHeaderCaption("Identificador").setExpandRatio(1);
		id_menuColumn.setHeaderCaption("Id. Menu").setExpandRatio(1);
		nombreColumn.setHeaderCaption("Nombre Menu").setExpandRatio(7);
		iconlColumn.setHeaderCaption("Icono").setExpandRatio(2);
		programaColumn.setHeaderCaption("Programa").setExpandRatio(5);
		fechaRegistroColumn.setHeaderCaption("Fecha Registro").setExpandRatio(1);
		fechaRegistroColumn.setRenderer(new DateRenderer("%1$tB de %1$te, %1$tY", new Locale("es", "BO")));
		Responsive.makeResponsive(this);
	}
}