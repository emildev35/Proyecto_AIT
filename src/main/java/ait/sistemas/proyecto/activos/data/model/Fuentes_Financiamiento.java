package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the Fuentes_Financiamiento database table.
 * 
 */
@Entity
@NamedQuery(name="Fuentes_Financiamiento.findAll", query="SELECT f FROM Fuentes_Financiamiento f")
public class Fuentes_Financiamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int FFI_Fuente_Financiamiento;

	private Date FFI_Fecha_Registro;

	private String FFI_Nombre_Fuente_Financiamiento;

	public Fuentes_Financiamiento() {
	}

	public int getFFI_Fuente_Financiamiento() {
		return this.FFI_Fuente_Financiamiento;
	}

	public void setFFI_Fuente_Financiamiento(int FFI_Fuente_Financiamiento) {
		this.FFI_Fuente_Financiamiento = FFI_Fuente_Financiamiento;
	}

	public Date getFFI_Fecha_Registro() {
		return this.FFI_Fecha_Registro;
	}

	public void setFFI_Fecha_Registro(Date FFI_Fecha_Registro) {
		this.FFI_Fecha_Registro = FFI_Fecha_Registro;
	}

	public String getFFI_Nombre_Fuente_Financiamiento() {
		return this.FFI_Nombre_Fuente_Financiamiento;
	}

	public void setFFI_Nombre_Fuente_Financiamiento(String FFI_Nombre_Fuente_Financiamiento) {
		this.FFI_Nombre_Fuente_Financiamiento = FFI_Nombre_Fuente_Financiamiento;
	}

}