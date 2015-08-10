package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the C_Movimientos database table.
 * 
 */
@Entity
@Table(name="C_Movimiento_Solicitud")
@NamedQuery(name="C_Movimiento_Solicitud.findAll", query="SELECT cs FROM C_Movimiento_Solicitud cs")
public class C_Movimiento_Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short CMV_Dependencia;

	private short CMV_Unidad_Organizacional_Origen;

	private short CMV_Tipo_Movimiento;

	private long CMV_No_Documento;

	private Time CMV_Fecha_Movimiento;

	private Time CMV_Fecha_Registro;

	public C_Movimiento_Solicitud() {
	}

	public short getCMV_Dependencia() {
		return this.CMV_Dependencia;
	}
	public void setCMV_Dependencia(short CMV_Dependencia) {
		this.CMV_Dependencia = CMV_Dependencia;
	}
	public short getCMV_Unidad_Organizacional_Origen() {
		return this.CMV_Unidad_Organizacional_Origen;
	}
	public void setCMV_Unidad_Organizacional_Origen(short CMV_Unidad_Organizacional_Origen) {
		this.CMV_Unidad_Organizacional_Origen = CMV_Unidad_Organizacional_Origen;
	}
	public short getCMV_Tipo_Movimiento() {
		return this.CMV_Tipo_Movimiento;
	}
	public void setCMV_Tipo_Movimiento(short CMV_Tipo_Movimiento) {
		this.CMV_Tipo_Movimiento = CMV_Tipo_Movimiento;
	}
	public long getCMV_No_Documento() {
		return this.CMV_No_Documento;
	}
	public void setCMV_No_Documento(long CMV_No_Documento) {
		this.CMV_No_Documento = CMV_No_Documento;
	}

	public Time getCMV_Fecha_Movimiento() {
		return this.CMV_Fecha_Movimiento;
	}

	public void setCMV_Fecha_Movimiento(Time CMV_Fecha_Movimiento) {
		this.CMV_Fecha_Movimiento = CMV_Fecha_Movimiento;
	}

	public Time getCMV_Fecha_Registro() {
		return this.CMV_Fecha_Registro;
	}

	public void setCMV_Fecha_Registro(Time CMV_Fecha_Registro) {
		this.CMV_Fecha_Registro = CMV_Fecha_Registro;
	}

}