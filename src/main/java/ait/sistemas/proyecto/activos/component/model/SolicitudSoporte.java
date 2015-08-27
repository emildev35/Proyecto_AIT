package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import ait.sistemas.proyecto.common.component.Messages;

@SqlResultSetMapping(name = "solicitud-soporte", entities = { @EntityResult(entityClass = SolicitudSoporte.class, fields = {
	@FieldResult(name = "nro_documento", column = "nro_documento"),
	@FieldResult(name = "dependencia", column = "dependencia"),
	@FieldResult(name = "fecha", column = "fecha"),
	@FieldResult(name = "hora", column = "hora"),
	@FieldResult(name = "ci_solicitante", column = "ci_solicitante"),
	@FieldResult(name = "nombre_solicitante", column = "nombre_solicitante"),
	@FieldResult(name = "descripcion", column = "descripcion"),
	@FieldResult(name = "nombre_sistema", column = "nombre_sistema"),
	@FieldResult(name = "tipo_soporte", column = "tipo_soporte")})})
@Entity
public class SolicitudSoporte implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private long nro_documento;
	private short tipo_movimiento;
	private short dependencia;
	private Date fecha;
	private Time hora;
	private String ci_solicitante;
	private String nombre_solicitante;
	private String tipo_soporte;
	private String descripcion;
	private String nombre_sistema;
	
	public SolicitudSoporte() {
	}

	

	public SolicitudSoporte(long nro_documento, short tipo_movimiento, short dependencia, Date fecha, Time hora,
			String ci_solicitante, String nombre_solicitante, String tipo_soporte, String descripcion, String nombre_sistema) {
		this.nro_documento = nro_documento;
		this.tipo_movimiento = tipo_movimiento;
		this.dependencia = dependencia;
		this.fecha = fecha;
		this.hora = hora;
		this.ci_solicitante = ci_solicitante;
		this.nombre_solicitante = nombre_solicitante;
		this.tipo_soporte = tipo_soporte;
		this.descripcion = descripcion;
		this.nombre_sistema = nombre_sistema;
	}





	public long getNro_documento() {
		return nro_documento;
	}

	public void setNro_documento(long nro_documento) {
		this.nro_documento = nro_documento;
	}


	public short getTipo_movimiento() {
		return tipo_movimiento;
	}


	public void setTipo_movimiento(short tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}


	public short getDependencia() {
		return dependencia;
	}

	public void setDependencia(short dependencia) {
		this.dependencia = dependencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
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


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getNombre_sistema() {
		return nombre_sistema == null ? Messages.NOT_FOUND_STRING :nombre_sistema;
	}


	public void setNombre_sistema(String nombre_sistema) {
		this.nombre_sistema = nombre_sistema;
	}

	
	

	
}
