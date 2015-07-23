package ait.sistemas.proyecto.seguridad.component.model;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;


@SqlResultSetMapping(name = "permiso-usuario", entities = { @EntityResult(entityClass = PermisosUsuario.class, fields = {
	@FieldResult(name = "subSistema", column = "SubSistema"),
	@FieldResult(name = "menu", column = "Menus"),
	@FieldResult(name = "subMenu", column = "SubMenu"),
	@FieldResult(name = "opcion", column = "Opcion"),
	@FieldResult(name = "identificador", column = "Identificador"),
	@FieldResult(name = "id_usuario", column = "Usuario"),
	@FieldResult(name = "check", column = "Check")}) })
@Entity
public class PermisosUsuario {
	private String subSistema;
	private String menu;
	private String subMenu;
	private String opcion;
	@Id
	private long identificador;
	private String id_usuario;
	private int check;
	
	public PermisosUsuario() {
	
	}
	
	public PermisosUsuario(long identificador, String subSistema, String menu,
			String subMenu, String opcion, String id_usuario, int check) {
		this.identificador = identificador;
		this.subSistema = subSistema;
		this.menu = menu;
		this.subMenu = subMenu;
		this.opcion = opcion;
		this.id_usuario = id_usuario;
		this.check = check;
	}


	public long getIdentificador() {
		return identificador;
	}

	public void setIdentificador(long identificador) {
		this.identificador = identificador;
	}

	public String getSubSistema() {
		return subSistema;
	}

	public void setSubSistema(String subSistema) {
		this.subSistema = subSistema;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

}
