package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "archive-map-proveedor", entities = { @EntityResult(entityClass = ProveedoresModel.class, fields = {
		@FieldResult(name = "PRV_Dependencia_NIT", column = "PRV_Dependencia_NIT"),
		@FieldResult(name = "PRV_Dependencia_ID", column = "PRV_Dependencia_ID"),
		@FieldResult(name = "PRV_Dependencia", column = "PRV_Dependencia"),
		@FieldResult(name = "PRV_NIT", column = "PRV_NIT"),
		@FieldResult(name = "PRV_Celular_Contacto", column = "PRV_Celular_Contacto"),
		@FieldResult(name = "PRV_Domicilio", column = "PRV_Domicilio"),
		@FieldResult(name = "PRV_Fecha_Registro", column = "PRV_Fecha_Registro"),
		@FieldResult(name = "PRV_Nombre", column = "PRV_Nombre"),
		@FieldResult(name = "PRV_Nombre_Contacto", column = "PRV_Nombre_Contacto"),
		@FieldResult(name = "PRV_Telefono", column = "PRV_Telefono"),
		@FieldResult(name = "PRV_Ciudad_ID", column = "PRV_Ciudad_ID"),
		@FieldResult(name = "PRV_Ciudad", column = "PRV_Ciudad")

}) })
@Entity
public class ProveedoresModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private short PRV_Dependencia_ID;
	private String PRV_Dependencia;

	@Id
	private String PRV_Dependencia_NIT;// clave primaria concatenada de las dos
										// llaves primarias de la BD

	private String PRV_NIT;

	private String PRV_Celular_Contacto;

	private String PRV_Domicilio;

	private Date PRV_Fecha_Registro;

	private String PRV_Nombre;

	private String PRV_Nombre_Contacto;

	private String PRV_Telefono;

	private short PRV_Ciudad_ID;
	private String PRV_Ciudad;

	public ProveedoresModel() {
	}

	public String getPRV_Dependencia_NIT() {
		return PRV_Dependencia_NIT;
	}

	public void setPRV_Dependencia_NIT(String pRV_Dependencia_NIT) {
		PRV_Dependencia_NIT = pRV_Dependencia_NIT;
	}

	public short getPRV_Dependencia_ID() {
		return PRV_Dependencia_ID;
	}

	public void setPRV_Dependencia_ID(short pRV_Dependencia_ID) {
		PRV_Dependencia_ID = pRV_Dependencia_ID;
	}

	public String getPRV_Dependencia() {
		return this.PRV_Dependencia;
	}

	public void setPRV_Dependencia(String PRV_Dependencia) {
		this.PRV_Dependencia = PRV_Dependencia;
	}

	public String getPRV_NIT() {
		return this.PRV_NIT;
	}

	public void setPRV_NIT(String PRV_NIT) {
		this.PRV_NIT = PRV_NIT;
	}

	public String getPRV_Celular_Contacto() {
		return this.PRV_Celular_Contacto;
	}

	public void setPRV_Celular_Contacto(String PRV_Celular_Contacto) {
		this.PRV_Celular_Contacto = PRV_Celular_Contacto;
	}

	public String getPRV_Domicilio() {
		return this.PRV_Domicilio;
	}

	public void setPRV_Domicilio(String PRV_Domicilio) {
		this.PRV_Domicilio = PRV_Domicilio;
	}

	public Date getPRV_Fecha_Registro() {
		return this.PRV_Fecha_Registro;
	}

	public void setPRV_Fecha_Registro(Date PRV_Fecha_Registro) {
		this.PRV_Fecha_Registro = PRV_Fecha_Registro;
	}

	public String getPRV_Nombre() {
		return this.PRV_Nombre;
	}

	public void setPRV_Nombre(String PRV_Nombre) {
		this.PRV_Nombre = PRV_Nombre;
	}

	public String getPRV_Nombre_Contacto() {
		return this.PRV_Nombre_Contacto;
	}

	public void setPRV_Nombre_Contacto(String PRV_Nombre_Contacto) {
		this.PRV_Nombre_Contacto = PRV_Nombre_Contacto;
	}

	public String getPRV_Telefono() {
		return this.PRV_Telefono;
	}

	public void setPRV_Telefono(String PRV_Telefono) {
		this.PRV_Telefono = PRV_Telefono;
	}

	public short getPRV_Ciudad_ID() {
		return PRV_Ciudad_ID;
	}

	public void setPRV_Ciudad_ID(short pRV_Ciudad_ID) {
		PRV_Ciudad_ID = pRV_Ciudad_ID;
	}

	public String getPRV_Ciudad() {
		return PRV_Ciudad;
	}

	public void setPRV_Ciudad(String pRV_Ciudad) {
		PRV_Ciudad = pRV_Ciudad;
	}

}