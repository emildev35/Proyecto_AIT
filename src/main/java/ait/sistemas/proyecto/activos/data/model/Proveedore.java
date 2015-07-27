package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Proveedores database table.
 * 
 */
@Entity
@Table(name="Proveedores")
@NamedQuery(name="Proveedore.findAll", query="SELECT p FROM Proveedore p")
public class Proveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	private short PRV_Dependencia;

	@Id
	private String PRV_NIT;

	private String PRV_Celular_Contacto;

	private String PRV_Domicilio;

	private Date PRV_Fecha_Registro;

	private String PRV_Nombre;

	private String PRV_Nombre_Contacto;

	private String PRV_Telefono;
	
	private Short PRV_Ciudad;

	public Proveedore() {
	}

	public short getPRV_Dependencia() {
		return this.PRV_Dependencia;
	}
	public void setPRV_Dependencia(short PRV_Dependencia) {
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
	public Short getPRV_Ciudad() {
		return PRV_Ciudad;
	}
	public void setPRV_Ciudad(Short pRV_Ciudad) {
		PRV_Ciudad = pRV_Ciudad;
	}

}