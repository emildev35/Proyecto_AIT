package ait.sistemas.proyecto.seguridad.component;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "full-menu", entities = { @EntityResult(entityClass = FullMenu.class, fields = {
		@FieldResult(name = "Identificador", column = "AME_Id_Identificador"),
		@FieldResult(name = "SubSistema", column = "SubSistema"),
		@FieldResult(name = "Menu", column = "Menus"),
		@FieldResult(name = "SubMenu", column = "SubMenu"),
		@FieldResult(name = "Opcion", column = "Opcion") }) })
@Entity
public class FullMenu {

	@Id
	private long Identificador;
	private String SubSistema;
	private String Menu;
	private String SubMenu;
	private String Opcion;

	public String getSubSistema() {
		return SubSistema;
	}

	public void setSubSistema(String subSistema) {
		SubSistema = subSistema;
	}

	public String getMenu() {
		return Menu;
	}

	public void setMenu(String menu) {
		Menu = menu;
	}

	public String getSubMenu() {
		return SubMenu;
	}

	public void setSubMenu(String subMenu) {
		SubMenu = subMenu;
	}

	public String getOpcion() {
		return Opcion;
	}

	public void setOpcion(String opcion) {
		Opcion = opcion;
	}

	public long getIdentificador() {
		return Identificador;
	}

	public void setIdentificador(long identificador) {
		Identificador = identificador;
	}

}
