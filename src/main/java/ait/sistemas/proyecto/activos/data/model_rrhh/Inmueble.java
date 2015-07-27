package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Time;


/**
 * The persistent class for the Inmuebles database table.
 * 
 */
@Entity
@Table(name="Inmuebles")
@NamedQuery(name="Inmueble.findAll", query="SELECT i FROM Inmueble i")
public class Inmueble implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short INM_Inmueble;

	private short INM_Ciudad;

	private String INM_Domicilio_Inmueble;

	private Date INM_Fecha_Registro;

	private String INM_Nombre_Inmueble;

	public Inmueble() {
	}

	public short getINM_Inmueble() {
		return this.INM_Inmueble;
	}

	public void setINM_Inmueble(short INM_Inmueble) {
		this.INM_Inmueble = INM_Inmueble;
	}

	public short getINM_Ciudad() {
		return this.INM_Ciudad;
	}

	public void setINM_Ciudad(short INM_Ciudad) {
		this.INM_Ciudad = INM_Ciudad;
	}

	public String getINM_Domicilio_Inmueble() {
		return this.INM_Domicilio_Inmueble;
	}

	public void setINM_Domicilio_Inmueble(String INM_Domicilio_Inmueble) {
		this.INM_Domicilio_Inmueble = INM_Domicilio_Inmueble;
	}

	public Date getINM_Fecha_Registro() {
		return this.INM_Fecha_Registro;
	}

	public void setINM_Fecha_Registro(Date INM_Fecha_Registro) {
		this.INM_Fecha_Registro = INM_Fecha_Registro;
	}

	public String getINM_Nombre_Inmueble() {
		return this.INM_Nombre_Inmueble;
	}

	public void setINM_Nombre_Inmueble(String INM_Nombre_Inmueble) {
		this.INM_Nombre_Inmueble = INM_Nombre_Inmueble;
	}

}