package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "mapeo-documento", entities = { @EntityResult(entityClass = DocumentosRespaldoModel.class, fields = {
		@FieldResult(name = "DOR_Dependencia_Activo_Documento", column = "DOR_Dependencia_Activo_Documento"),
		@FieldResult(name = "DOR_Dependencia", column = "DOR_Dependencia"),
		@FieldResult(name = "DOR_Codigo_Activo", column = "DOR_Codigo_Activo"),
		@FieldResult(name = "DOR_Id_Documento_Respaldo", column = "DOR_Id_Documento_Respaldo"),
		@FieldResult(name = "DOR_Fecha_Registro", column = "DOR_Fecha_Registro"),
		@FieldResult(name = "DOR_Nombre_Documento", column = "DOR_Nombre_Documento"),
		@FieldResult(name = "DOR_Ubicacion_Documento", column = "DOR_Ubicacion_Documento")

}) })
@Entity
@NamedQuery(name = "Documentos_Respaldo.findAll", query = "SELECT d FROM Documentos_Respaldo d")
public class DocumentosRespaldoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String DOR_Dependencia_Activo_Documento;

	private short DOR_Dependencia;

	private String DOR_Codigo_Activo;

	private int DOR_Id_Documento_Respaldo;

	private Time DOR_Fecha_Registro;

	private String DOR_Nombre_Documento;

	private String DOR_Ubicacion_Documento;

	public DocumentosRespaldoModel() {
	}

	public String getDependencia_Activo_Documento() {
		return DOR_Dependencia_Activo_Documento;
	}

	public void setDependencia_Activo_Documento(String dependencia_Activo_Documento) {
		DOR_Dependencia_Activo_Documento = dependencia_Activo_Documento;
	}

	public short getDOR_Dependencia() {
		return this.DOR_Dependencia;
	}

	public void setDOR_Dependencia(short DOR_Dependencia) {
		this.DOR_Dependencia = DOR_Dependencia;
	}

	public String getDOR_Codigo_Activo() {
		return this.DOR_Codigo_Activo;
	}

	public void setDOR_Codigo_Activo(String DOR_Codigo_Activo) {
		this.DOR_Codigo_Activo = DOR_Codigo_Activo;
	}

	public int getDOR_Id_Documento_Respaldo() {
		return this.DOR_Id_Documento_Respaldo;
	}

	public void setDOR_Id_Documento_Respaldo(int DOR_Id_Documento_Respaldo) {
		this.DOR_Id_Documento_Respaldo = DOR_Id_Documento_Respaldo;
	}

	public Time getDOR_Fecha_Registro() {
		return this.DOR_Fecha_Registro;
	}

	public void setDOR_Fecha_Registro(Time DOR_Fecha_Registro) {
		this.DOR_Fecha_Registro = DOR_Fecha_Registro;
	}

	public String getDOR_Nombre_Documento() {
		return this.DOR_Nombre_Documento;
	}

	public void setDOR_Nombre_Documento(String DOR_Nombre_Documento) {
		this.DOR_Nombre_Documento = DOR_Nombre_Documento;
	}

	public String getDOR_Ubicacion_Documento() {
		return this.DOR_Ubicacion_Documento;
	}

	public void setDOR_Ubicacion_Documento(String DOR_Ubicacion_Documento) {
		this.DOR_Ubicacion_Documento = DOR_Ubicacion_Documento;
	}

}