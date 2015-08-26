package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SolicitudSoporte implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long nro_documento;
	private short tipo_movimiento_id;
	private short dependencia_id;
	private Date fecha_movimiento;
	private String ci_solicitante;
	private String nombre_solicitante;
	private String tipo_soporte;
	private short nro_autorizacion;
	private short unidad_organizacional_id;
	
	public SolicitudSoporte() {
	}

	
	public SolicitudSoporte(long nro_documento, short tipo_movimiento_id, short dependencia_id, Date fecha_movimiento,
			String ci_solicitante, String nombre_solicitante, String tipo_soporte, short nro_autorizacion,
			short unidad_organizacional_id) {
		this.nro_documento = nro_documento;
		this.tipo_movimiento_id = tipo_movimiento_id;
		this.dependencia_id = dependencia_id;
		this.fecha_movimiento = fecha_movimiento;
		this.ci_solicitante = ci_solicitante;
		this.nombre_solicitante = nombre_solicitante;
		this.tipo_soporte = tipo_soporte;
		this.nro_autorizacion = nro_autorizacion;
		this.unidad_organizacional_id = unidad_organizacional_id;
	}


	public long getNro_documento() {
		return nro_documento;
	}

	public void setNro_documento(long nro_documento) {
		this.nro_documento = nro_documento;
	}

	public short getTipo_movimiento_id() {
		return tipo_movimiento_id;
	}

	public void setTipo_movimiento_id(short tipo_movimiento_id) {
		this.tipo_movimiento_id = tipo_movimiento_id;
	}

	public short getDependencia_id() {
		return dependencia_id;
	}

	public void setDependencia_id(short dependencia_id) {
		this.dependencia_id = dependencia_id;
	}

	public Date getFecha_movimiento() {
		return fecha_movimiento;
	}

	public void setFecha_movimiento(Date fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}

	public String getCi_solicitante() {
		return ci_solicitante;
	}

	public void setCi_solicitante(String ci_solicitante) {
		this.ci_solicitante = ci_solicitante;
	}

	public String getNombre_solicitante() {
		return nombre_solicitante;
	}

	public void setNombre_solicitante(String nombre_solicitante) {
		this.nombre_solicitante = nombre_solicitante;
	}

	public String getTipo_soporte() {
		return tipo_soporte;
	}

	public void setTipo_soporte(String tipo_soporte) {
		this.tipo_soporte = tipo_soporte;
	}

	public short getNro_autorizacion() {
		return nro_autorizacion;
	}

	public void setNro_autorizacion(short nro_autorizacion) {
		this.nro_autorizacion = nro_autorizacion;
	}

	public short getUnidad_organizacional_id() {
		return unidad_organizacional_id;
	}

	public void setUnidad_organizacional_id(short unidad_organizacional_id) {
		this.unidad_organizacional_id = unidad_organizacional_id;
	}
	
}
