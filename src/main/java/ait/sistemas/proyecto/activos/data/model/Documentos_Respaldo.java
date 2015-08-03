package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the Documentos_Respaldo database table.
 * 
 */
@Entity
@NamedQuery(name="Documentos_Respaldo.findAll", query="SELECT d FROM Documentos_Respaldo d")
public class Documentos_Respaldo implements Serializable {
	private static final long serialVersionUID = 1L;

	private short DOR_Dependencia;

	private String DOR_Codigo_Activo;
	
	@Id
	private int DOR_Id_Documento_Respaldo;

	private Time DOR_Fecha_Registro;

	private String DOR_Nombre_Documento;

	private String DOR_Ubicacion_Documento;

	public Documentos_Respaldo() {
	}

	public short getDOR_Dependencia() {
		return this.DOR_Dependencia;
	}
	public void setDOR_Dependencia(short DOR_Dependencia) {
		this.DOR_Dependencia = DOR_Dependencia;
	}
	public String getDOR_Codigo_Activo() {
		return this.DOR_Codigo_Activo;
	}
	public void setDOR_Codigo_Activo(String DOR_Codigo_Activo) {
		this.DOR_Codigo_Activo = DOR_Codigo_Activo;
	}
	public int getDOR_Id_Documento_Respaldo() {
		return this.DOR_Id_Documento_Respaldo;
	}
	public void setDOR_Id_Documento_Respaldo(int DOR_Id_Documento_Respaldo) {
		this.DOR_Id_Documento_Respaldo = DOR_Id_Documento_Respaldo;
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