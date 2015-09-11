package ait.sistemas.proyecto.activos.component.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "reporte-movimiento", entities = { @EntityResult(entityClass = MovimientoReporte.class, fields = {
	@FieldResult(name = "id_cmovimiento", column = "id_cmovimiento"),
	@FieldResult(name = "Dependencia_Origen", column = "Dependencia_Origen"),
	@FieldResult(name = "Dependencia_Destino", column = "Dependencia_Destino"),
	@FieldResult(name = "Unidad_organizacional_Origen", column = "Unidad_organizacional_Origen"),
	@FieldResult(name = "Unidad_organizacional_Destino", column = "Unidad_organizacional_Destino"),
	@FieldResult(name = "Usuario_Origen", column = "Usuario_Origen"),
	@FieldResult(name = "Usuario_Destino", column = "Usuario_Destino"),
	@FieldResult(name = "CMV_No_Documento", column = "CMV_No_Documento"),
	@FieldResult(name = "CMV_Fecha_Registro", column = "CMV_Fecha_Registro"),
	@FieldResult(name = "Codigo_Activo", column = "Codigo_Activo"),
	@FieldResult(name = "Nombre_Activo", column = "Nombre_Activo"),
	@FieldResult(name = "Componentes", column = "Componentes"),
	@FieldResult(name = "Caracteristicas", column = "Caracteristicas")
	})})
@Entity
public class MovimientoReporte {
	

	@Id
	private String id_cmovimiento;
	private String Dependencia_Origen;
	private String Dependencia_Destino;
	private String Unidad_organizacional_Origen;
	private String Unidad_organizacional_Destino;
	private String Usuario_Origen;
	private String Usuario_Destino;
	private long CMV_No_Documento;
	private Date CMV_Fecha_Registro;
	private long Codigo_Activo;
	private String Nombre_Activo;
	private String Componentes;
	private String Caracteristicas;
	
	public String getId_cmovimiento() {
		return id_cmovimiento;
	}
	public void setId_cmovimiento(String id_cmovimiento) {
		this.id_cmovimiento = id_cmovimiento;
	}
	public String getDependencia_Origen() {
		return Dependencia_Origen;
	}
	public void setDependencia_Origen(String dependencia_Origen) {
		Dependencia_Origen = dependencia_Origen;
	}
	public String getDependencia_Destino() {
		return Dependencia_Destino;
	}
	public void setDependencia_Destino(String dependencia_Destino) {
		Dependencia_Destino = dependencia_Destino;
	}
	public String getUnidad_organizacional_Origen() {
		return Unidad_organizacional_Origen;
	}
	public void setUnidad_organizacional_Origen(String unidad_organizacional_Origen) {
		Unidad_organizacional_Origen = unidad_organizacional_Origen;
	}
	public String getUnidad_organizacional_Destino() {
		return Unidad_organizacional_Destino;
	}
	public void setUnidad_organizacional_Destino(String unidad_organizacional_Destino) {
		Unidad_organizacional_Destino = unidad_organizacional_Destino;
	}
	public String getUsuario_Origen() {
		return Usuario_Origen;
	}
	public void setUsuario_Origen(String usuario_Origen) {
		Usuario_Origen = usuario_Origen;
	}
	public String getUsuario_Destino() {
		return Usuario_Destino;
	}
	public void setUsuario_Destino(String usuario_Destino) {
		Usuario_Destino = usuario_Destino;
	}
	public long getCMV_No_Documento() {
		return CMV_No_Documento;
	}
	public void setCMV_No_Documento(long cMV_No_Documento) {
		CMV_No_Documento = cMV_No_Documento;
	}
	public Date getCMV_Fecha_Registro() {
		return CMV_Fecha_Registro;
	}
	public void setCMV_Fecha_Registro(Date cMV_Fecha_Registro) {
		CMV_Fecha_Registro = cMV_Fecha_Registro;
	}
	public long getCodigo_Activo() {
		return Codigo_Activo;
	}
	public void setCodigo_Activo(long codigo_Activo) {
		Codigo_Activo = codigo_Activo;
	}
	public String getNombre_Activo() {
		return Nombre_Activo;
	}
	public void setNombre__Activo(String nombre_Activo) {
		Nombre_Activo = nombre_Activo;
	}
	public String getComponentes() {
		return Componentes;
	}
	public void setComponentes(String componentes) {
		Componentes = componentes;
	}
	public String getCaracteristicas() {
		return Caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		Caracteristicas = caracteristicas;
	}
	
}
