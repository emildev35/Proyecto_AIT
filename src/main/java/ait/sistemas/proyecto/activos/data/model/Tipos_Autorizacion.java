package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.activos.data.model.pk.Tipos_AutorizacionPK;

import java.sql.Time;


/**
 * The persistent class for the Tipos_Autorizacion database table.
 * 
 */
@Entity
@NamedQuery(name="Tipos_Autorizacion.findAll", query="SELECT t FROM Tipos_Autorizacion t")
public class Tipos_Autorizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Id
	private Tipos_AutorizacionPK id;

	private String TAU_CI_Autoriza;

	private Time TAU_Fecha_Registro;

	private String TAU_Id_Usuario;

	private short TAU_Nivel_Autorizacion;

	private String TAU_PIN_Autoriza;

	public Tipos_Autorizacion() {
	}

	public Tipos_AutorizacionPK getId() {
		return this.id;
	}

	public void setId(Tipos_AutorizacionPK id) {
		this.id = id;
	}

	public String getTAU_CI_Autoriza() {
		return this.TAU_CI_Autoriza;
	}

	public void setTAU_CI_Autoriza(String TAU_CI_Autoriza) {
		this.TAU_CI_Autoriza = TAU_CI_Autoriza;
	}

	public Time getTAU_Fecha_Registro() {
		return this.TAU_Fecha_Registro;
	}

	public void setTAU_Fecha_Registro(Time TAU_Fecha_Registro) {
		this.TAU_Fecha_Registro = TAU_Fecha_Registro;
	}

	public String getTAU_Id_Usuario() {
		return this.TAU_Id_Usuario;
	}

	public void setTAU_Id_Usuario(String TAU_Id_Usuario) {
		this.TAU_Id_Usuario = TAU_Id_Usuario;
	}

	public short getTAU_Nivel_Autorizacion() {
		return this.TAU_Nivel_Autorizacion;
	}

	public void setTAU_Nivel_Autorizacion(short TAU_Nivel_Autorizacion) {
		this.TAU_Nivel_Autorizacion = TAU_Nivel_Autorizacion;
	}

	public String getTAU_PIN_Autoriza() {
		return this.TAU_PIN_Autoriza;
	}

	public void setTAU_PIN_Autoriza(String TAU_PIN_Autoriza) {
		this.TAU_PIN_Autoriza = TAU_PIN_Autoriza;
	}

}