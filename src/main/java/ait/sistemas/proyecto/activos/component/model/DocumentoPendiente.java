package ait.sistemas.proyecto.activos.component.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "documento-pendiente", entities = { @EntityResult(entityClass = DocumentoPendiente.class, fields = {
	@FieldResult(name = "nro_documento", column = "nro_documento"),
	@FieldResult(name = "tipo_movimiento_id", column = "tipo_movimiento_id"),
	@FieldResult(name = "fecha_movimiento", column = "fecha_movimiento"),
	@FieldResult(name = "ci_solicitante", column = "ci_solicitante"),
	@FieldResult(name = "dependencia_id", column = "dependencia_id"),
	@FieldResult(name = "nombre_solicitante", column = "nombre_solicitante"),
	@FieldResult(name = "tipo_movimiento", column = "tipo_movimiento"),
	@FieldResult(name = "nro_autorizacion", column = "nro_autorizacion")})})
@Entity
public class DocumentoPendiente {
	@Id
	private long nro_documento;
	private short tipo_movimiento_id;
	private short dependencia_id;
	private Date fecha_movimiento;
	private String ci_solicitante;
	private String nombre_solicitante;
	private String tipo_movimiento;
	private String nro_autorizacion;
	
	
	public DocumentoPendiente() {
	}
	
	
	public DocumentoPendiente(long nro_documento, short tipo_movimiento_id, Date fecha_movimiento, String ci_solicitante,
			String nombre_solicitante, String tipo_solicitante, String nro_autorizacion) {
		this.nro_documento = nro_documento;
		this.tipo_movimiento_id = tipo_movimiento_id;
		this.fecha_movimiento = fecha_movimiento;
		this.ci_solicitante = ci_solicitante;
		this.nombre_solicitante = nombre_solicitante;
		this.tipo_movimiento = tipo_solicitante;
		this.nro_autorizacion = nro_autorizacion;
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


	public String getTipo_movimiento() {
		return tipo_movimiento;
	}


	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}


	public String getNro_autorizacion() {
		return nro_autorizacion;
	}


	public void setNro_autorizacion(String nro_autorizacion) {
		this.nro_autorizacion = nro_autorizacion;
	}


}
