package ait.sistemas.proyecto.seguridad.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.seguridad.data.model.pk.Log_AuditoriaPK;

import java.sql.Time;


/**
 * The persistent class for the Log_Auditoria database table.
 * 
 */
@Entity
@NamedQuery(name="Log_Auditoria.findAll", query="SELECT l FROM Log_Auditoria l")
public class Log_Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Log_AuditoriaPK id;

	private Time LGA_Fecha_Entrada;

	private Time LGA_Fecha_Salida;

	private String LGA_Hora_Entrada;

	private String LGA_Hora_Salida;

	private int LGA_Id_Menu;

	private int LGA_Id_Opcion;

	private int LGA_Id_Submenu;

	private int LGA_Id_Subsistema;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="LGA_Id_Usuario")
	private Usuario usuario;

	public Log_Auditoria() {
	}

	public Log_AuditoriaPK getId() {
		return this.id;
	}

	public void setId(Log_AuditoriaPK id) {
		this.id = id;
	}

	public Time getLGA_Fecha_Entrada() {
		return this.LGA_Fecha_Entrada;
	}

	public void setLGA_Fecha_Entrada(Time LGA_Fecha_Entrada) {
		this.LGA_Fecha_Entrada = LGA_Fecha_Entrada;
	}

	public Time getLGA_Fecha_Salida() {
		return this.LGA_Fecha_Salida;
	}

	public void setLGA_Fecha_Salida(Time LGA_Fecha_Salida) {
		this.LGA_Fecha_Salida = LGA_Fecha_Salida;
	}

	public String getLGA_Hora_Entrada() {
		return this.LGA_Hora_Entrada;
	}

	public void setLGA_Hora_Entrada(String LGA_Hora_Entrada) {
		this.LGA_Hora_Entrada = LGA_Hora_Entrada;
	}

	public String getLGA_Hora_Salida() {
		return this.LGA_Hora_Salida;
	}

	public void setLGA_Hora_Salida(String LGA_Hora_Salida) {
		this.LGA_Hora_Salida = LGA_Hora_Salida;
	}

	public int getLGA_Id_Menu() {
		return this.LGA_Id_Menu;
	}

	public void setLGA_Id_Menu(int LGA_Id_Menu) {
		this.LGA_Id_Menu = LGA_Id_Menu;
	}

	public int getLGA_Id_Opcion() {
		return this.LGA_Id_Opcion;
	}

	public void setLGA_Id_Opcion(int LGA_Id_Opcion) {
		this.LGA_Id_Opcion = LGA_Id_Opcion;
	}

	public int getLGA_Id_Submenu() {
		return this.LGA_Id_Submenu;
	}

	public void setLGA_Id_Submenu(int LGA_Id_Submenu) {
		this.LGA_Id_Submenu = LGA_Id_Submenu;
	}

	public int getLGA_Id_Subsistema() {
		return this.LGA_Id_Subsistema;
	}

	public void setLGA_Id_Subsistema(int LGA_Id_Subsistema) {
		this.LGA_Id_Subsistema = LGA_Id_Subsistema;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}