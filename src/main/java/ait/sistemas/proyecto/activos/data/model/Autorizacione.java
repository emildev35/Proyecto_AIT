package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.activos.data.model.pk.AutorizacionePK;

import java.sql.Time;


/**
 * The persistent class for the Autorizaciones database table.
 * 
 */
@Entity
@Table(name="Autorizaciones")
@NamedQuery(name="Autorizacione.findAll", query="SELECT a FROM Autorizacione a")
public class Autorizacione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Id
	private AutorizacionePK id;

	private String AUT_CI_Autoriza;

	private Time AUT_Fecha_Autorizacion;

	private Time AUT_Fecha_Rechazo;

	private Time AUT_Fecha_Registro;

	private String AUT_ID_Usuario;

	private short AUT_Nivel_Autorizacion;

	private String AUT_PIN_Autoriza_Rechaza;

	public Autorizacione() {
	}

	public AutorizacionePK getId() {
		return this.id;
	}

	public void setId(AutorizacionePK id) {
		this.id = id;
	}

	public String getAUT_CI_Autoriza() {
		return this.AUT_CI_Autoriza;
	}

	public void setAUT_CI_Autoriza(String AUT_CI_Autoriza) {
		this.AUT_CI_Autoriza = AUT_CI_Autoriza;
	}

	public Time getAUT_Fecha_Autorizacion() {
		return this.AUT_Fecha_Autorizacion;
	}

	public void setAUT_Fecha_Autorizacion(Time AUT_Fecha_Autorizacion) {
		this.AUT_Fecha_Autorizacion = AUT_Fecha_Autorizacion;
	}

	public Time getAUT_Fecha_Rechazo() {
		return this.AUT_Fecha_Rechazo;
	}

	public void setAUT_Fecha_Rechazo(Time AUT_Fecha_Rechazo) {
		this.AUT_Fecha_Rechazo = AUT_Fecha_Rechazo;
	}

	public Time getAUT_Fecha_Registro() {
		return this.AUT_Fecha_Registro;
	}

	public void setAUT_Fecha_Registro(Time AUT_Fecha_Registro) {
		this.AUT_Fecha_Registro = AUT_Fecha_Registro;
	}

	public String getAUT_ID_Usuario() {
		return this.AUT_ID_Usuario;
	}

	public void setAUT_ID_Usuario(String AUT_ID_Usuario) {
		this.AUT_ID_Usuario = AUT_ID_Usuario;
	}

	public short getAUT_Nivel_Autorizacion() {
		return this.AUT_Nivel_Autorizacion;
	}

	public void setAUT_Nivel_Autorizacion(short AUT_Nivel_Autorizacion) {
		this.AUT_Nivel_Autorizacion = AUT_Nivel_Autorizacion;
	}

	public String getAUT_PIN_Autoriza_Rechaza() {
		return this.AUT_PIN_Autoriza_Rechaza;
	}

	public void setAUT_PIN_Autoriza_Rechaza(String AUT_PIN_Autoriza_Rechaza) {
		this.AUT_PIN_Autoriza_Rechaza = AUT_PIN_Autoriza_Rechaza;
	}

}