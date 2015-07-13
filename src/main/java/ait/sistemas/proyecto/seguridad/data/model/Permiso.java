package ait.sistemas.proyecto.seguridad.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.seguridad.data.model.pk.PermisoPK;

import java.sql.Time;


/**
 * The persistent class for the Permisos database table.
 * 
 */
@Entity
@Table(name="Permisos")
@NamedQuery(name="Permiso.findAll", query="SELECT p FROM Permiso p")
public class Permiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PermisoPK id;

	private Time PRM_Fecha_Otorgacion;

	private Time PRM_Fecha_Revocacion;

	private int PRM_Id_Menu;

	private int PRM_Id_Opcion;

	private int PRM_Id_Submenu;

	private int PRM_Id_Subsistema;

	//bi-directional many-to-one association to Arbol_menus
	@ManyToOne
	@JoinColumn(name="PRM_Identificador")
	private Arbol_menus arbolMenus;

	//bi-directional many-to-one association to Perfile
	@ManyToOne
	@JoinColumn(name="PRM_Id_Perfil")
	private Perfil perfil;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="PRM_Id_Usuario")
	private Usuario usuario;

	public Permiso() {
	}

	public PermisoPK getId() {
		return this.id;
	}

	public void setId(PermisoPK id) {
		this.id = id;
	}

	public Time getPRM_Fecha_Otorgacion() {
		return this.PRM_Fecha_Otorgacion;
	}

	public void setPRM_Fecha_Otorgacion(Time PRM_Fecha_Otorgacion) {
		this.PRM_Fecha_Otorgacion = PRM_Fecha_Otorgacion;
	}

	public Time getPRM_Fecha_Revocacion() {
		return this.PRM_Fecha_Revocacion;
	}

	public void setPRM_Fecha_Revocacion(Time PRM_Fecha_Revocacion) {
		this.PRM_Fecha_Revocacion = PRM_Fecha_Revocacion;
	}

	public int getPRM_Id_Menu() {
		return this.PRM_Id_Menu;
	}

	public void setPRM_Id_Menu(int PRM_Id_Menu) {
		this.PRM_Id_Menu = PRM_Id_Menu;
	}

	public int getPRM_Id_Opcion() {
		return this.PRM_Id_Opcion;
	}

	public void setPRM_Id_Opcion(int PRM_Id_Opcion) {
		this.PRM_Id_Opcion = PRM_Id_Opcion;
	}

	public int getPRM_Id_Submenu() {
		return this.PRM_Id_Submenu;
	}

	public void setPRM_Id_Submenu(int PRM_Id_Submenu) {
		this.PRM_Id_Submenu = PRM_Id_Submenu;
	}

	public int getPRM_Id_Subsistema() {
		return this.PRM_Id_Subsistema;
	}

	public void setPRM_Id_Subsistema(int PRM_Id_Subsistema) {
		this.PRM_Id_Subsistema = PRM_Id_Subsistema;
	}

	public Arbol_menus getArbolMenus() {
		return this.arbolMenus;
	}

	public void setArbolMenus(Arbol_menus arbolMenus) {
		this.arbolMenus = arbolMenus;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfile) {
		this.perfil = perfile;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}