package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.activos.data.model.pk.ProveedorePK;

import java.sql.Time;


/**
 * The persistent class for the Proveedores database table.
 * 
 */
@Entity
@Table(name="Proveedores")
@NamedQuery(name="Proveedore.findAll", query="SELECT p FROM Proveedore p")
public class Proveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Id
	private ProveedorePK id;

	private String PRV_Celular_Contacto;

	private String PRV_Domicilio;

	private Time PRV_Fecha_Registro;

	private String PRV_Nombre_Proveedor;

	private String PRV_Nombre_Representante;

	private String PRV_Telefono;

	public Proveedore() {
	}

	public ProveedorePK getId() {
		return this.id;
	}

	public void setId(ProveedorePK id) {
		this.id = id;
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

	public Time getPRV_Fecha_Registro() {
		return this.PRV_Fecha_Registro;
	}

	public void setPRV_Fecha_Registro(Time PRV_Fecha_Registro) {
		this.PRV_Fecha_Registro = PRV_Fecha_Registro;
	}

	public String getPRV_Nombre_Proveedor() {
		return this.PRV_Nombre_Proveedor;
	}

	public void setPRV_Nombre_Proveedor(String PRV_Nombre_Proveedor) {
		this.PRV_Nombre_Proveedor = PRV_Nombre_Proveedor;
	}

	public String getPRV_Nombre_Representante() {
		return this.PRV_Nombre_Representante;
	}

	public void setPRV_Nombre_Representante(String PRV_Nombre_Representante) {
		this.PRV_Nombre_Representante = PRV_Nombre_Representante;
	}

	public String getPRV_Telefono() {
		return this.PRV_Telefono;
	}

	public void setPRV_Telefono(String PRV_Telefono) {
		this.PRV_Telefono = PRV_Telefono;
	}

}