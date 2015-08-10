package ait.sistemas.proyecto.activos.component.model;

import java.sql.Date;

/**
 * Detalle de Movimientos Asignacion, Devolucion
 * @author franzemil
 *
 */
public class Detalle {
	private short id_dependencia;
	private short id_unidad_organizacional_origen;
	private long nro_documento;
	private long id_activo;
	private String observacion;
	private Date fecha_registro;
	public Detalle() {
	}
	public Detalle(short id_dependencia, short id_unidad_organizacional_origen, long nro_documento,
			long id_activo, String observacion, Date fecha_registro) {
		this.id_dependencia = id_dependencia;
		this.id_unidad_organizacional_origen = id_unidad_organizacional_origen;
		this.nro_documento = nro_documento;
		this.id_activo = id_activo;
		this.observacion = observacion;
		this.fecha_registro = fecha_registro;
	}
	public short getId_dependencia() {
		return id_dependencia;
	}
	public void setId_dependencia(short id_dependencia) {
		this.id_dependencia = id_dependencia;
	}
	public short getId_unidad_organizacional_origen() {
		return id_unidad_organizacional_origen;
	}
	public void setId_unidad_organizacional_origen(short id_unidad_organizacional_origen) {
		this.id_unidad_organizacional_origen = id_unidad_organizacional_origen;
	}

	public long getNro_documento() {
		return nro_documento;
	}
	public void setNro_documento(long nro_documento) {
		this.nro_documento = nro_documento;
	}
	public long getId_activo() {
		return id_activo;
	}
	public void setId_activo(long id_activo) {
		this.id_activo = id_activo;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
}
