package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the D_Movimientos database table.
 * 
 */
@Entity
@Table(name="D_Movimientos")
@NamedQuery(name="D_Movimiento.findAll", query="SELECT d FROM D_Movimiento d")
public class D_Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id()
	private short DMV_Dependencia;

	private short DMV_Unidad_Organizacional;

	private short DMV_Tipo_Movimiento;

	private long DMV_No_Documento;

	private String DMV_Codigo_Activo;

	private Time DMV_Fecha_Registro;

	private String DMV_Observacion;

	public D_Movimiento() {
	}

	public short getDMV_Dependencia() {
		return this.DMV_Dependencia;
	}
	public void setDMV_Dependencia(short DMV_Dependencia) {
		this.DMV_Dependencia = DMV_Dependencia;
	}
	public short getDMV_Unidad_Organizacional() {
		return this.DMV_Unidad_Organizacional;
	}
	public void setDMV_Unidad_Organizacional(short DMV_Unidad_Organizacional) {
		this.DMV_Unidad_Organizacional = DMV_Unidad_Organizacional;
	}
	public short getDMV_Tipo_Movimiento() {
		return this.DMV_Tipo_Movimiento;
	}
	public void setDMV_Tipo_Movimiento(short DMV_Tipo_Movimiento) {
		this.DMV_Tipo_Movimiento = DMV_Tipo_Movimiento;
	}
	public long getDMV_No_Documento() {
		return this.DMV_No_Documento;
	}
	public void setDMV_No_Documento(long DMV_No_Documento) {
		this.DMV_No_Documento = DMV_No_Documento;
	}
	public String getDMV_Codigo_Activo() {
		return this.DMV_Codigo_Activo;
	}
	public void setDMV_Codigo_Activo(String DMV_Codigo_Activo) {
		this.DMV_Codigo_Activo = DMV_Codigo_Activo;
	}
	public Time getDMV_Fecha_Registro() {
		return this.DMV_Fecha_Registro;
	}

	public void setDMV_Fecha_Registro(Time DMV_Fecha_Registro) {
		this.DMV_Fecha_Registro = DMV_Fecha_Registro;
	}

	public String getDMV_Observacion() {
		return this.DMV_Observacion;
	}

	public void setDMV_Observacion(String DMV_Observacion) {
		this.DMV_Observacion = DMV_Observacion;
	}

}