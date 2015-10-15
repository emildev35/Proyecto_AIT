package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Mantenimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private short id_dependencia;
	private short id_unidad_organizacional;
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
	
	public short getId_dependencia() {
		return id_dependencia;
	}
	public void setId_dependencia(short id_dependencia) {
		this.id_dependencia = id_dependencia;
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

	public short getId_unidad_organizacional() {
		return id_unidad_organizacional;
	}

	public void setId_unidad_organizacional(short id_unidad_organizacional) {
		this.id_unidad_organizacional = id_unidad_organizacional;
	}
	
	
}
