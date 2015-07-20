package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.activos.data.model.pk.Documentos_RespaldoPK;

import java.sql.Time;


/**
 * The persistent class for the Documentos_Respaldo database table.
 * 
 */
@Entity
@NamedQuery(name="Documentos_Respaldo.findAll", query="SELECT d FROM Documentos_Respaldo d")
public class Documentos_Respaldo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Documentos_RespaldoPK id;

	private Time DOR_Fecha_Registro;

	private String DOR_Nombre_Documento;

	private String DOR_Ubicacion_Documento;

	public Documentos_Respaldo() {
	}

	public Documentos_RespaldoPK getId() {
		return this.id;
	}

	public void setId(Documentos_RespaldoPK id) {
		this.id = id;
	}

	public Time getDOR_Fecha_Registro() {
		return this.DOR_Fecha_Registro;
	}

	public void setDOR_Fecha_Registro(Time DOR_Fecha_Registro) {
		this.DOR_Fecha_Registro = DOR_Fecha_Registro;
	}

	public String getDOR_Nombre_Documento() {
		return this.DOR_Nombre_Documento;
	}

	public void setDOR_Nombre_Documento(String DOR_Nombre_Documento) {
		this.DOR_Nombre_Documento = DOR_Nombre_Documento;
	}

	public String getDOR_Ubicacion_Documento() {
		return this.DOR_Ubicacion_Documento;
	}

	public void setDOR_Ubicacion_Documento(String DOR_Ubicacion_Documento) {
		this.DOR_Ubicacion_Documento = DOR_Ubicacion_Documento;
	}

}