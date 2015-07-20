package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the Tipos_Movimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Tipos_Movimiento.findAll", query="SELECT t FROM Tipos_Movimiento t")
public class Tipos_Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short TMV_Tipo_Movimiento;

	private Date TMV_Fecha_Registro;

	private String TMV_Nombre_Tipo_Movimiento;

	private String TMV_Sigla_Tipo_Movimiento;

	public Tipos_Movimiento() {
	}

	public short getTMV_Tipo_Movimiento() {
		return this.TMV_Tipo_Movimiento;
	}

	public void setTMV_Tipo_Movimiento(short TMV_Tipo_Movimiento) {
		this.TMV_Tipo_Movimiento = TMV_Tipo_Movimiento;
	}

	public Date getTMV_Fecha_Registro() {
		return this.TMV_Fecha_Registro;
	}

	public void setTMV_Fecha_Registro(Date TMV_Fecha_Registro) {
		this.TMV_Fecha_Registro = TMV_Fecha_Registro;
	}

	public String getTMV_Nombre_Tipo_Movimiento() {
		return this.TMV_Nombre_Tipo_Movimiento;
	}

	public void setTMV_Nombre_Tipo_Movimiento(String TMV_Nombre_Tipo_Movimiento) {
		this.TMV_Nombre_Tipo_Movimiento = TMV_Nombre_Tipo_Movimiento;
	}

	public String getTMV_Sigla_Tipo_Movimiento() {
		return TMV_Sigla_Tipo_Movimiento;
	}
	public void setTMV_Sigla_Tipo_Movimiento(String tMV_Sigla_Tipo_Movimiento) {
		TMV_Sigla_Tipo_Movimiento = tMV_Sigla_Tipo_Movimiento;
	}
}