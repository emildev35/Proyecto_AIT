package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.activos.data.model.pk.ComponentePK;

import java.sql.Time;


/**
 * The persistent class for the Componentes database table.
 * 
 */
@Entity
@Table(name="Componentes")
@NamedQuery(name="Componente.findAll", query="SELECT c FROM Componente c")
public class Componente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Column(insertable = false, updatable = false)
	private ComponentePK id;

	private String COM_Caracteristica_Componente;

	private Time COM_Fecha_Registro;

	private String COM_Nombre_Componente;

	public Componente() {
	}

	public ComponentePK getId() {
		return this.id;
	}

	public void setId(ComponentePK id) {
		this.id = id;
	}

	public String getCOM_Caracteristica_Componente() {
		return this.COM_Caracteristica_Componente;
	}

	public void setCOM_Caracteristica_Componente(String COM_Caracteristica_Componente) {
		this.COM_Caracteristica_Componente = COM_Caracteristica_Componente;
	}

	public Time getCOM_Fecha_Registro() {
		return this.COM_Fecha_Registro;
	}

	public void setCOM_Fecha_Registro(Time COM_Fecha_Registro) {
		this.COM_Fecha_Registro = COM_Fecha_Registro;
	}

	public String getCOM_Nombre_Componente() {
		return this.COM_Nombre_Componente;
	}

	public void setCOM_Nombre_Componente(String COM_Nombre_Componente) {
		this.COM_Nombre_Componente = COM_Nombre_Componente;
	}

}