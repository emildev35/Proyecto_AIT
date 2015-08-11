package ait.sistemas.proyecto.activos.data.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "tipo-autorizacion", entities = { @EntityResult(entityClass = TipoAutorizacionModel.class, fields = {
		@FieldResult(name = "id_tipo_autorizacion", column = "id_tipo_autorizacion"),
		@FieldResult(name = "dependencia", column = "dependencia"),
		@FieldResult(name = "dependencia_id", column = "dependencia_id"),
		@FieldResult(name = "unidad_organizacional", column = "unidad_organizacional"),
		@FieldResult(name = "unidad_organizacional", column = "unidad_organizacional"),
		@FieldResult(name = "tipo_movimiento", column = "tipo_movimiento"),
		@FieldResult(name = "tipo_movimiento_id", column = "tipo_movimiento_id"), @FieldResult(name = "orden", column = "orden"),
		@FieldResult(name = "ci", column = "ci"), @FieldResult(name = "fecha_registro", column = "fecha_registro"),
		@FieldResult(name = "usuario_id", column = "usuario_id"),
		@FieldResult(name = "servidor_publico", column = "servidor_publico"),
		@FieldResult(name = "usuario_id", column = "usuario_id"),
		@FieldResult(name = "nivel_autorizacion", column = "nivel_autorizacion"),
		@FieldResult(name = "nivel_autorizacion_id", column = "nivel_autorizacion_id") }) })
@Entity
public class TipoAutorizacionModel {
	@Id
	private String id_tipo_autorizacion;
	private String dependencia;
	private short dependencia_id;
	
	private String unidad_organizacional;
	private short unidad_organizacional_id;
	
	private String tipo_movimiento;
	private short tipo_movimiento_id;
	
	private short orden;
	
	private String ci;
	
	private Date fecha_registro;
	
	private String usuario_id;
	
	private String servidor_publico;
	
	private String nivel_autorizacion;
	private short nivel_autorizacion_id;
	
	public TipoAutorizacionModel() {
		// TODO Auto-generated constructor stub
	}
	
	public TipoAutorizacionModel(String dependencia, short dependencia_id, String unidad_organizacional,
			short unidad_organizacional_id, String tipo_movimiento, short tipo_movimiento_id, short orden, String ci,
			Date fecha_registro, String usuario_id, String servidor_publico, String nivel_autorizacion,
			short nivel_autorizacion_id) {
		this.dependencia = dependencia;
		this.dependencia_id = dependencia_id;
		this.unidad_organizacional = unidad_organizacional;
		this.unidad_organizacional_id = unidad_organizacional_id;
		this.tipo_movimiento = tipo_movimiento;
		this.tipo_movimiento_id = tipo_movimiento_id;
		this.orden = orden;
		this.ci = ci;
		this.fecha_registro = fecha_registro;
		this.usuario_id = usuario_id;
		this.servidor_publico = servidor_publico;
		this.nivel_autorizacion = nivel_autorizacion;
		this.nivel_autorizacion_id = nivel_autorizacion_id;
	}
	
	public String getDependencia() {
		return dependencia;
	}
	
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	
	public short getDependencia_id() {
		return dependencia_id;
	}
	
	public void setDependencia_id(short dependencia_id) {
		this.dependencia_id = dependencia_id;
	}
	
	public String getUnidad_organizacional() {
		return unidad_organizacional;
	}
	
	public void setUnidad_organizacional(String unidad_organizacional) {
		this.unidad_organizacional = unidad_organizacional;
	}
	
	public short getUnidad_organizacional_id() {
		return unidad_organizacional_id;
	}
	
	public void setUnidad_organizacional_id(short unidad_organizacional_id) {
		this.unidad_organizacional_id = unidad_organizacional_id;
	}
	
	public String getTipo_movimiento() {
		return tipo_movimiento;
	}
	
	public void setTipo_movimiento(String tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}
	
	public short getTipo_movimiento_id() {
		return tipo_movimiento_id;
	}
	
	public void setTipo_movimiento_id(short tipo_movimiento_id) {
		this.tipo_movimiento_id = tipo_movimiento_id;
	}
	
	public short getOrden() {
		return orden;
	}
	
	public void setOrden(short orden) {
		this.orden = orden;
	}
	
	public String getCi() {
		return ci;
	}
	
	public void setCi(String ci) {
		this.ci = ci;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}
	
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	public String getUsuario_id() {
		return usuario_id;
	}
	
	public void setUsuario_id(String usuario_id) {
		this.usuario_id = usuario_id;
	}
	
	public String getServidor_publico() {
		return servidor_publico;
	}
	
	public void setServidor_publico(String servidor_publico) {
		this.servidor_publico = servidor_publico;
	}
	
	public String getNivel_autorizacion() {
		return nivel_autorizacion;
	}
	
	public void setNivel_autorizacion(String nivel_autorizacion) {
		this.nivel_autorizacion = nivel_autorizacion;
	}
	
	public short getNivel_autorizacion_id() {
		return nivel_autorizacion_id;
	}
	
	public void setNivel_autorizacion_id(short nivel_autorizacion_id) {
		this.nivel_autorizacion_id = nivel_autorizacion_id;
	}
	
}
