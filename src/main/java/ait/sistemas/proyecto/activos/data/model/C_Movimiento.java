package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.activos.data.model.pk.C_MovimientoPK;

import java.sql.Time;


/**
 * The persistent class for the C_Movimientos database table.
 * 
 */
@Entity
@Table(name="C_Movimientos")
@NamedQuery(name="C_Movimiento.findAll", query="SELECT c FROM C_Movimiento c")
public class C_Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Id
	private C_MovimientoPK id;

	private String CMV_CI_Funcionario_Destino;

	private String CMV_CI_Funcionario_Origen;

	private short CMV_Dependencia_Destino;

	private short CMV_Dependencia_Origen;

	private Time CMV_Fecha_Movimiento;

	private Time CMV_Fecha_Registro;

	private short CMV_Inmueble;

	private String CMV_No_Documento_referencia;

	private String CMV_Observaciones;

	private short CMV_Tipo_Documento_Referencia;

	private int CMV_Ubicacion_Fisica;

	private int CMV_Unidad_Organizacional_Destino;

	public C_Movimiento() {
	}

	public C_MovimientoPK getId() {
		return this.id;
	}

	public void setId(C_MovimientoPK id) {
		this.id = id;
	}

	public String getCMV_CI_Funcionario_Destino() {
		return this.CMV_CI_Funcionario_Destino;
	}

	public void setCMV_CI_Funcionario_Destino(String CMV_CI_Funcionario_Destino) {
		this.CMV_CI_Funcionario_Destino = CMV_CI_Funcionario_Destino;
	}

	public String getCMV_CI_Funcionario_Origen() {
		return this.CMV_CI_Funcionario_Origen;
	}

	public void setCMV_CI_Funcionario_Origen(String CMV_CI_Funcionario_Origen) {
		this.CMV_CI_Funcionario_Origen = CMV_CI_Funcionario_Origen;
	}

	public short getCMV_Dependencia_Destino() {
		return this.CMV_Dependencia_Destino;
	}

	public void setCMV_Dependencia_Destino(short CMV_Dependencia_Destino) {
		this.CMV_Dependencia_Destino = CMV_Dependencia_Destino;
	}

	public short getCMV_Dependencia_Origen() {
		return this.CMV_Dependencia_Origen;
	}

	public void setCMV_Dependencia_Origen(short CMV_Dependencia_Origen) {
		this.CMV_Dependencia_Origen = CMV_Dependencia_Origen;
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

	public short getCMV_Inmueble() {
		return this.CMV_Inmueble;
	}

	public void setCMV_Inmueble(short CMV_Inmueble) {
		this.CMV_Inmueble = CMV_Inmueble;
	}

	public String getCMV_No_Documento_referencia() {
		return this.CMV_No_Documento_referencia;
	}

	public void setCMV_No_Documento_referencia(String CMV_No_Documento_referencia) {
		this.CMV_No_Documento_referencia = CMV_No_Documento_referencia;
	}

	public String getCMV_Observaciones() {
		return this.CMV_Observaciones;
	}

	public void setCMV_Observaciones(String CMV_Observaciones) {
		this.CMV_Observaciones = CMV_Observaciones;
	}

	public short getCMV_Tipo_Documento_Referencia() {
		return this.CMV_Tipo_Documento_Referencia;
	}

	public void setCMV_Tipo_Documento_Referencia(short CMV_Tipo_Documento_Referencia) {
		this.CMV_Tipo_Documento_Referencia = CMV_Tipo_Documento_Referencia;
	}

	public int getCMV_Ubicacion_Fisica() {
		return this.CMV_Ubicacion_Fisica;
	}

	public void setCMV_Ubicacion_Fisica(int CMV_Ubicacion_Fisica) {
		this.CMV_Ubicacion_Fisica = CMV_Ubicacion_Fisica;
	}

	public int getCMV_Unidad_Organizacional_Destino() {
		return this.CMV_Unidad_Organizacional_Destino;
	}

	public void setCMV_Unidad_Organizacional_Destino(int CMV_Unidad_Organizacional_Destino) {
		this.CMV_Unidad_Organizacional_Destino = CMV_Unidad_Organizacional_Destino;
	}

}