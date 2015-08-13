package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Autorizaciones database table.
 * 
 */
@Entity
@Table(name="Autorizaciones")
@NamedQuery(name="Autorizacion.findAll", query="SELECT a FROM Autorizacion a")
public class Autorizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short AUT_Dependencia;

	private short AUT_Unidad_Organizacional;

	private short AUT_Tipo_Movimiento;

	private short AUT_Orden_Autorizacion;

	private long AUT_No_Documento_Autorizado;

	private String AUT_CI_Autoriza;

	private Date AUT_Fecha_Autorizacion;

	private Date AUT_Fecha_Rechazo;

	private Date AUT_Fecha_Registro;

	private String AUT_ID_Usuario;

	private short AUT_Nivel_Autorizacion;

	private String AUT_PIN_Autoriza_Rechaza;

	private String AUT_Motivo_Autorizacion_Rechazo;
	
	public Autorizacion() {
	}

	public short getAUT_Dependencia() {
		return this.AUT_Dependencia;
	}
	public void setAUT_Dependencia(short AUT_Dependencia) {
		this.AUT_Dependencia = AUT_Dependencia;
	}
	public short getAUT_Unidad_Organizacional() {
		return this.AUT_Unidad_Organizacional;
	}
	public void setAUT_Unidad_Organizacional(short AUT_Unidad_Organizacional) {
		this.AUT_Unidad_Organizacional = AUT_Unidad_Organizacional;
	}
	public short getAUT_Tipo_Movimiento() {
		return this.AUT_Tipo_Movimiento;
	}
	public void setAUT_Tipo_Movimiento(short AUT_Tipo_Movimiento) {
		this.AUT_Tipo_Movimiento = AUT_Tipo_Movimiento;
	}
	public short getAUT_Orden_Autorizacion() {
		return this.AUT_Orden_Autorizacion;
	}
	public void setAUT_Orden_Autorizacion(short AUT_Orden_Autorizacion) {
		this.AUT_Orden_Autorizacion = AUT_Orden_Autorizacion;
	}
	public long getAUT_No_Documento_Autorizado() {
		return this.AUT_No_Documento_Autorizado;
	}
	public void setAUT_No_Documento_Autorizado(long AUT_No_Documento_Autorizado) {
		this.AUT_No_Documento_Autorizado = AUT_No_Documento_Autorizado;
	}

	public String getAUT_CI_Autoriza() {
		return this.AUT_CI_Autoriza;
	}

	public void setAUT_CI_Autoriza(String AUT_CI_Autoriza) {
		this.AUT_CI_Autoriza = AUT_CI_Autoriza;
	}

	public Date getAUT_Fecha_Autorizacion() {
		return this.AUT_Fecha_Autorizacion;
	}

	public void setAUT_Fecha_Autorizacion(Date AUT_Fecha_Autorizacion) {
		this.AUT_Fecha_Autorizacion = AUT_Fecha_Autorizacion;
	}

	public Date getAUT_Fecha_Rechazo() {
		return this.AUT_Fecha_Rechazo;
	}

	public void setAUT_Fecha_Rechazo(Date AUT_Fecha_Rechazo) {
		this.AUT_Fecha_Rechazo = AUT_Fecha_Rechazo;
	}

	public Date getAUT_Fecha_Registro() {
		return this.AUT_Fecha_Registro;
	}

	public void setAUT_Fecha_Registro(Date AUT_Fecha_Registro) {
		this.AUT_Fecha_Registro = AUT_Fecha_Registro;
	}

	public String getAUT_ID_Usuario() {
		return this.AUT_ID_Usuario;
	}

	public void setAUT_ID_Usuario(String AUT_ID_Usuario) {
		this.AUT_ID_Usuario = AUT_ID_Usuario;
	}

	public short getAUT_Nivel_Autorizacion() {
		return this.AUT_Nivel_Autorizacion;
	}

	public void setAUT_Nivel_Autorizacion(short AUT_Nivel_Autorizacion) {
		this.AUT_Nivel_Autorizacion = AUT_Nivel_Autorizacion;
	}

	public String getAUT_PIN_Autoriza_Rechaza() {
		return this.AUT_PIN_Autoriza_Rechaza;
	}

	public void setAUT_PIN_Autoriza_Rechaza(String AUT_PIN_Autoriza_Rechaza) {
		this.AUT_PIN_Autoriza_Rechaza = AUT_PIN_Autoriza_Rechaza;
	}

	public String getAUT_Motivo_Autorizacion_Rechazo() {
		return AUT_Motivo_Autorizacion_Rechazo;
	}

	public void setAUT_Motivo_Autorizacion_Rechazo(String aUT_Motivo_Autorizacion_Rechazo) {
		AUT_Motivo_Autorizacion_Rechazo = aUT_Motivo_Autorizacion_Rechazo;
	}

}