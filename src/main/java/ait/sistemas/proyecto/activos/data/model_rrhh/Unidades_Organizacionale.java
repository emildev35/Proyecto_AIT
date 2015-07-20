package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Unidades_Organizacionales database table.
 * 
 */
@Entity
@Table(name="Unidades_Organizacionales")
@NamedQuery(name="Unidades_Organizacionale.findAll", query="SELECT u FROM Unidades_Organizacionale u")
public class Unidades_Organizacionale implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date UNO_Fecha_Registro;

	private String UNO_Nombre_Unidad_Organizacional;

	@Id
	private short UNO_Unidad_Organizacional;
	
	private short UNO_Dependencia;
	
	public Unidades_Organizacionale() {
	}

	public Date getUNO_Fecha_Registro() {
		return this.UNO_Fecha_Registro;
	}

	public void setUNO_Fecha_Registro(Date UNO_Fecha_Registro) {
		this.UNO_Fecha_Registro = UNO_Fecha_Registro;
	}

	public String getUNO_Nombre_Unidad_Organizacional() {
		return this.UNO_Nombre_Unidad_Organizacional;
	}

	public void setUNO_Nombre_Unidad_Organizacional(String UNO_Nombre_Unidad_Organizacional) {
		this.UNO_Nombre_Unidad_Organizacional = UNO_Nombre_Unidad_Organizacional;
	}

	public short getUNO_Unidad_Organizacional() {
		return this.UNO_Unidad_Organizacional;
	}
	public void setUNO_Unidad_Organizacional(short UNO_Unidad_Organizacional) {
		this.UNO_Unidad_Organizacional = UNO_Unidad_Organizacional;
	}
	public short getUNO_Dependencia() {
		return this.UNO_Dependencia;
	}
	public void setUNO_Dependencia(short UNO_Dependencia) {
		this.UNO_Dependencia = UNO_Dependencia;
	}
}