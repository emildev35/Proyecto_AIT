package ait.sistemas.proyecto.seguridad.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.seguridad.data.model.pk.OpcionesXPerfilPK;

import java.sql.Time;


/**
 * The persistent class for the OpcionesXPerfil database table.
 * 
 */
@Entity
@NamedQuery(name="OpcionesXPerfil.findAll", query="SELECT o FROM OpcionesXPerfil o")
public class OpcionesXPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OpcionesXPerfilPK id;

	private Time OPF_Fecha_Registro;

	private int OPF_Id_Menu;

	private int OPF_Id_Opcion;

	private int OPF_Id_Submenu;

	private int OPF_Id_Subsistema;

	//bi-directional many-to-one association to Perfile
	@ManyToOne
	@JoinColumn(name="OPF_Id_Perfil")
	private Perfil perfil;

	public OpcionesXPerfil() {
	}

	public OpcionesXPerfilPK getId() {
		return this.id;
	}

	public void setId(OpcionesXPerfilPK id) {
		this.id = id;
	}

	public Time getOPF_Fecha_Registro() {
		return this.OPF_Fecha_Registro;
	}

	public void setOPF_Fecha_Registro(Time OPF_Fecha_Registro) {
		this.OPF_Fecha_Registro = OPF_Fecha_Registro;
	}

	public int getOPF_Id_Menu() {
		return this.OPF_Id_Menu;
	}

	public void setOPF_Id_Menu(int OPF_Id_Menu) {
		this.OPF_Id_Menu = OPF_Id_Menu;
	}

	public int getOPF_Id_Opcion() {
		return this.OPF_Id_Opcion;
	}

	public void setOPF_Id_Opcion(int OPF_Id_Opcion) {
		this.OPF_Id_Opcion = OPF_Id_Opcion;
	}

	public int getOPF_Id_Submenu() {
		return this.OPF_Id_Submenu;
	}

	public void setOPF_Id_Submenu(int OPF_Id_Submenu) {
		this.OPF_Id_Submenu = OPF_Id_Submenu;
	}

	public int getOPF_Id_Subsistema() {
		return this.OPF_Id_Subsistema;
	}

	public void setOPF_Id_Subsistema(int OPF_Id_Subsistema) {
		this.OPF_Id_Subsistema = OPF_Id_Subsistema;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfile) {
		this.perfil = perfile;
	}

}