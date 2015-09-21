package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

/**
 * Clase que contiene la informacion de un Activo desde la perspectiva de un
 * inventario fisico
 * 
 * @author franzemil
 *
 */
@SqlResultSetMapping(name = "inv-activo", entities = { @EntityResult(entityClass = ActivoInventario.class, fields = {
		@FieldResult(name = "id", column = "id"),	
		@FieldResult(name = "codigo_activo", column = "codigo_activo"),
		@FieldResult(name = "numero_documento", column = "numero_documento"),
		@FieldResult(name = "nombre_activo", column = "nombre_activo"), 
		@FieldResult(name = "observacion", column = "observacion"),
		@FieldResult(name = "ci_funcionario", column = "ci_funcionario"), 
		@FieldResult(name = "dependencia", column = "dependencia"),
		@FieldResult(name = "dr", column = "dr"), 
		@FieldResult(name = "mr", column = "mr"),
		@FieldResult(name = "sr", column = "sr"), 
		@FieldResult(name = "fecha_registro", column = "fecha_registro"),
		@FieldResult(name = "documento_referencia", column = "documento_referencia"),
		@FieldResult(name = "nombre_funcionario", column = "nombre_funcionario"),
		@FieldResult(name = "fecha_referencia", column = "fecha_referencia") }) })
@Entity
public class ActivoInventario implements Serializable {	
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private long codigo_activo;
	private long numero_documento;
	private String nombre_activo;
	private String observacion;
	private String ci_funcionario;
	private String dependencia;
	private String sr;
	private String dr;
	private String mr;
	private Date fecha_registro;
	private String documento_referencia;
	private String fecha_referencia;
	private String nombre_funcionario;
	
	public ActivoInventario() {
	}

	public ActivoInventario(long codigo_activo, String nombre_activo, String observacion, String ci_funcionario) {
		this.codigo_activo = codigo_activo;
		this.nombre_activo = nombre_activo;
		this.observacion = observacion;
		this.ci_funcionario = ci_funcionario;
	}

	public long getCodigo_activo() {
		return codigo_activo;
	}
	
	public void setCodigo_activo(long codigo_activo) {
		this.codigo_activo = codigo_activo;
	}
	
	public long getNumero_documento() {
		return numero_documento;
	}
	
	public void setNumero_documento(long numero_documento) {
		this.numero_documento = numero_documento;
	}
	
	public String getNombre_activo() {
		return nombre_activo;
	}
	
	public void setNombre_activo(String nombre_activo) {
		this.nombre_activo = nombre_activo;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public String getCi_funcionario() {
		return ci_funcionario;
	}
	
	public void setCi_funcionario(String ci_funcionario) {
		this.ci_funcionario = ci_funcionario;
	}
	
	public String getDependencia() {
		return dependencia;
	}
	
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	
	public String getSr() {
		return sr;
	}
	
	public void setSr(String sr) {
		this.sr = sr;
	}
	
	public String getDr() {
		return dr;
	}
	
	public void setDr(String dr) {
		this.dr = dr;
	}
	
	public String getMr() {
		return mr;
	}
	
	public void setMr(String mr) {
		this.mr = mr;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}
	
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	public String getDocumento_referencia() {
		return documento_referencia;
	}
	
	public void setDocumento_referencia(String documento_referencia) {
		this.documento_referencia = documento_referencia;
	}
	
	public String getFecha_referencia() {
		return fecha_referencia;
	}
	
	public void setFecha_referencia(String fecha_referencia) {
		this.fecha_referencia = fecha_referencia;
	}
	
	public String getNombre_funcionario() {
		return nombre_funcionario;
	}
	
	public void setNombre_funcionario(String nombre_funcionario) {
		this.nombre_funcionario = nombre_funcionario;
	}
	
}
