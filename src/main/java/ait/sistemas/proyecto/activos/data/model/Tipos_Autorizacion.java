package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the Tipos_Autorizacion database table.
 * 
 */
@Entity
@NamedQuery(name="Tipos_Autorizacion.findAll", query="SELECT t FROM Tipos_Autorizacion t")
public class Tipos_Autorizacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short TAU_Dependencia;

	private short TAU_Unidad_Organizacional;

	private short TAU_Tipo_Movimiento;

	private short TAU_Orden_Autorizacion;

	private String TAU_CI_Autoriza;

	private Time TAU_Fecha_Registro;

	private String TAU_Id_Usuario;

	private short TAU_Nivel_Autorizacion;

	private String TAU_PIN_Autoriza;

	public Tipos_Autorizacion() {
	}

	public short getTAU_Dependencia() {
		return this.TAU_Dependencia;
	}
	public void setTAU_Dependencia(short TAU_Dependencia) {
		this.TAU_Dependencia = TAU_Dependencia;
	}
	public short getTAU_Unidad_Organizacional() {
		return this.TAU_Unidad_Organizacional;
	}
	public void setTAU_Unidad_Organizacional(short TAU_Unidad_Organizacional) {
		this.TAU_Unidad_Organizacional = TAU_Unidad_Organizacional;
	}
	public short getTAU_Tipo_Movimiento() {
		return this.TAU_Tipo_Movimiento;
	}
	public void setTAU_Tipo_Movimiento(short TAU_Tipo_Movimiento) {
		this.TAU_Tipo_Movimiento = TAU_Tipo_Movimiento;
	}
	public short getTAU_Orden_Autorizacion() {
		return this.TAU_Orden_Autorizacion;
	}
	public void setTAU_Orden_Autorizacion(short TAU_Orden_Autorizacion) {
		this.TAU_Orden_Autorizacion = TAU_Orden_Autorizacion;
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