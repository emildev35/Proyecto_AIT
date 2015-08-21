package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 * The persistent class for the C_Movimientos database table.
 * 
 */
//@Entity
//@Table(name="C_Movimientos")
//@NamedQuery(name="C_Movimiento.findAll", query="SELECT c FROM C_Movimiento c")
public class C_Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

//	@Id
	private short CMV_Dependencia;

	private short CMV_Unidad_Organizacional_Origen;

	private short CMV_Tipo_Movimiento;

	private long CMV_No_Documento;

	private String CMV_CI_Funcionario_Destino;

	private String CMV_CI_Funcionario_Origen;

	private short CMV_Dependencia_Destino;

	private short CMV_Dependencia_Origen;

	private Time CMV_Fecha_Movimiento;

	private Time CMV_Fecha_Registro;


	private String CMV_No_Documento_referencia;

	private String CMV_Observaciones;

	private short CMV_Tipo_Documento_Referencia;

	private int CMV_Ubicacion_Fisica;

	private int CMV_Unidad_Organizacional_Destino;
//	@Version
	private long version;
	
	public C_Movimiento() {
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