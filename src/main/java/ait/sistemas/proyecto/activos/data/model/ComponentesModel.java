package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
@SqlResultSetMapping(name = "mapeo-componente", entities = { @EntityResult(entityClass = ComponentesModel.class, fields = {
	@FieldResult(name = "COM_Dependencia_Activo_Componente", column = "COM_Dependencia_Activo_Componente"),
	@FieldResult(name = "COM_Dependencia", column = "COM_Dependencia"),
	@FieldResult(name = "COM_Codigo_Activo", column = "COM_Codigo_Activo"),
	@FieldResult(name = "COM_Id_Componente", column = "COM_Id_Componente"),
	@FieldResult(name = "COM_Caracteristica_Componente", column = "COM_Caracteristica_Componente"),
	@FieldResult(name = "COM_Fecha_Registro", column = "COM_Fecha_Registro"),
	@FieldResult(name = "COM_Nombre_Componente", column = "COM_Nombre_Componente")

}) })
@Entity
public class ComponentesModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String COM_Dependencia_Activo_Componente;
	private short COM_Dependencia;

	private String COM_Codigo_Activo;

	private short COM_Id_Componente;

	private String COM_Caracteristica_Componente;

	private Time COM_Fecha_Registro;

	private String COM_Nombre_Componente;

	public ComponentesModel() {
	}

	public String getCOM_Dependencia_Activo_Componente() {
		return COM_Dependencia_Activo_Componente;
	}

	public void setCOM_Dependencia_Activo_Componente(String cOM_Dependencia_Activo_Componente) {
		COM_Dependencia_Activo_Componente = cOM_Dependencia_Activo_Componente;
	}

	public short getCOM_Dependencia() {
		return this.COM_Dependencia;
	}

	public void setCOM_Dependencia(short COM_Dependencia) {
		this.COM_Dependencia = COM_Dependencia;
	}

	public String getCOM_Codigo_Activo() {
		return this.COM_Codigo_Activo;
	}

	public void setCOM_Codigo_Activo(String COM_Codigo_Activo) {
		this.COM_Codigo_Activo = COM_Codigo_Activo;
	}

	public short getCOM_Id_Componente() {
		return this.COM_Id_Componente;
	}

	public void setCOM_Id_Componente(short COM_Id_Componente) {
		this.COM_Id_Componente = COM_Id_Componente;
	}

	public String getCOM_Caracteristica_Componente() {
		return this.COM_Caracteristica_Componente;
	}

	public void setCOM_Caracteristica_Componente(String COM_Caracteristica_Componente) {
		this.COM_Caracteristica_Componente = COM_Caracteristica_Componente;
	}

	public Time getCOM_Fecha_Registro() {
		return this.COM_Fecha_Registro;
	}

	public void setCOM_Fecha_Registro(Time COM_Fecha_Registro) {
		this.COM_Fecha_Registro = COM_Fecha_Registro;
	}

	public String getCOM_Nombre_Componente() {
		return this.COM_Nombre_Componente;
	}

	public void setCOM_Nombre_Componente(String COM_Nombre_Componente) {
		this.COM_Nombre_Componente = COM_Nombre_Componente;
	}

}