package ait.sistemas.proyecto.common.view;

import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

public class AitView {
	
	public static String getNavText(Arbol_menus menu) {
		if (menu == null) {
			return "";
		} else {
			String txtNav = "";
			
			if (menu.getArbolMenus() != null) {
				txtNav += getNavText(menu.getArbolMenus()) + " >> ";
			}
			if (menu.getArbolMenuses().size() == 0) {
				txtNav += "<strong>" + menu.getAME_Nombre() + "</strong>";
			} else {
				txtNav += menu.getAME_Nombre();
			}
			return txtNav;
		}
	}
}
