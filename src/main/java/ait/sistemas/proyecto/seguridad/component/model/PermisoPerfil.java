package ait.sistemas.proyecto.seguridad.component.model;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "permiso-perfil", entities = { @EntityResult(entityClass = PermisoPerfil.class, fields = {
	@FieldResult(name = "id_perfil", column = "Id_Perfil"),
	@FieldResult(name = "identificador", column = "Id_Identificador"),
	@FieldResult(name = "nombre_menu", column = "Nombre_Menu"),
	@FieldResult(name = "icono", column = "Icono"),
	@FieldResult(name = "programa", column = "Programa")}) })
@Entity
public class PermisoPerfil {
	
	private int id_perfil;
	@Id
	private int identificador;
	private String nombre_menu;
	private String icono;
	private String programa;
	
	public PermisoPerfil() {
	}
	
	public PermisoPerfil(int id, int identificador, String nombre_menu,
			String icono, String programa) {
		this.id_perfil = id;
		this.identificador = identificador;
		this.nombre_menu = nombre_menu;
		this.icono = icono;
		this.programa = programa;
	}

	public int getId_perfil() {
		return id_perfil;
	}
	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getNombre_menu() {
		return nombre_menu;
	}
	public void setNombre_menu(String nombre_menu) {
		this.nombre_menu = nombre_menu;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	
	

}
