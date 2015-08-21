package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Mantenimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private short id_dependencia;
	private short id_unidad_organizacional_origen;
	private long nro_documento;
	private String id_usuario;
	private short tipo_movimiento;
	private String nro_documento_referencia;
	private Date fecha_nro_referencia;
	private Date fecha_registro;
	private Date fecha_movimiento;	
	private List<Detalle> detalles;
	private String observacion;
	
	public Mantenimiento() {
		this.detalles = new ArrayList<Detalle>();
	}
	public Mantenimiento(short id_dependencia, short id_unidad_organizacional_origen, long nro_documento, String id_usuario,
			short tipo_movimiento, String nro_documento_referencia, Date fecha_nro_referencia, Date fecha_registro,
			Date fecha_movimiento, List<Detalle> detalles, String observacion) {
		this.id_dependencia = id_dependencia;
		this.id_unidad_organizacional_origen = id_unidad_organizacional_origen;
		this.nro_documento = nro_documento;
		this.id_usuario = id_usuario;
		this.tipo_movimiento = tipo_movimiento;
		this.nro_documento_referencia = nro_documento_referencia;
		this.fecha_nro_referencia = fecha_nro_referencia;
		this.fecha_registro = fecha_registro;
		this.fecha_movimiento = fecha_movimiento;
		this.detalles = detalles;
		this.observacion = observacion;
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
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public short getTipo_movimiento() {
		return tipo_movimiento;
	}
	public void setTipo_movimiento(short tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}
	public String getNro_documento_referencia() {
		return nro_documento_referencia;
	}
	public void setNro_documento_referencia(String nro_documento_referencia) {
		this.nro_documento_referencia = nro_documento_referencia;
	}
	public Date getFecha_nro_referencia() {
		return fecha_nro_referencia;
	}
	public void setFecha_nro_referencia(Date fecha_nro_referencia) {
		this.fecha_nro_referencia = fecha_nro_referencia;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public Date getFecha_movimiento() {
		return fecha_movimiento;
	}
	public void setFecha_movimiento(Date fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}
	public List<Detalle> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public void addDetalle(Detalle detalle) {
		this.detalles.add(detalle);
		
	}
	
}
