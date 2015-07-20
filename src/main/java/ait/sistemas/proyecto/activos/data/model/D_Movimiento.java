package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.activos.data.model.pk.D_MovimientoPK;

import java.sql.Time;


/**
 * The persistent class for the D_Movimientos database table.
 * 
 */
@Entity
@Table(name="D_Movimientos")
@NamedQuery(name="D_Movimiento.findAll", query="SELECT d FROM D_Movimiento d")
public class D_Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId()
	@Column(insertable = false, updatable = false)
	private D_MovimientoPK id;

	private Time DMV_Fecha_Registro;

	private String DMV_Observacion;

	public D_Movimiento() {
	}

	public D_MovimientoPK getId() {
		return this.id;
	}

	public void setId(D_MovimientoPK id) {
		this.id = id;
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