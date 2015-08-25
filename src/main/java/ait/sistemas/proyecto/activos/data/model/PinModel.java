package ait.sistemas.proyecto.activos.data.model;

import java.sql.Date;

import ait.sistemas.proyecto.seguridad.component.Auth;

public class PinModel {
	private String usuario_id;
	
	private short dependencia_id;
	private short unidad_organizacional_id;
	private Date fecha_generacion;
	private Date fecha_registro;
	private String pin;
	private String ci;
	
	public PinModel() {
	}
	
	public PinModel(String usuario_id, short dependencia_id, short unidad_organizacional_id, Date fecha_generacion,
			Date fecha_registro, String pin, String ci) {
		this.usuario_id = usuario_id;
		this.dependencia_id = dependencia_id;
		this.unidad_organizacional_id = unidad_organizacional_id;
		this.fecha_generacion = fecha_generacion;
		this.fecha_registro = fecha_registro;
		this.pin = pin;
		this.ci = ci;
	}
	
	public String getUsuario_id() {
		return usuario_id;
	}
	
	public void setUsuario_id(String usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	public short getDependencia_id() {
		return dependencia_id;
	}
	
	public void setDependencia_id(short dependencia_id) {
		this.dependencia_id = dependencia_id;
	}
	
	public short getUnidad_organizacional_id() {
		return unidad_organizacional_id;
	}
	
	public void setUnidad_organizacional_id(short unidad_organizacional_id) {
		this.unidad_organizacional_id = unidad_organizacional_id;
	}
	
	public Date getFecha_generacion() {
		return fecha_generacion;
	}
	
	public void setFecha_generacion(Date fecha_generacion) {
		this.fecha_generacion = fecha_generacion;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}
	
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin = Auth.hash(pin);
	}
	
	public String getCi() {
		return ci;
	}
	
	public void setCi(String ci) {
		this.ci = ci;
	}
	
}
