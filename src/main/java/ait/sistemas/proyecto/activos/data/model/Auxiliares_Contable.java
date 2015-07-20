package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.activos.data.model.pk.Auxiliares_ContablePK;

import java.sql.Time;


/**
 * The persistent class for the Auxiliares_Contables database table.
 * 
 */
@Entity
@Table(name="Auxiliares_Contables")
@NamedQuery(name="Auxiliares_Contable.findAll", query="SELECT a FROM Auxiliares_Contable a")
public class Auxiliares_Contable implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@Id
	private Auxiliares_ContablePK id;

	private Time AUC_Fecha_Registro;

	private String AUC_Nombre_Auxiliar_Contable;

	public Auxiliares_Contable() {
	}

	public Auxiliares_ContablePK getId() {
		return this.id;
	}

	public void setId(Auxiliares_ContablePK id) {
		this.id = id;
	}

	public Time getAUC_Fecha_Registro() {
		return this.AUC_Fecha_Registro;
	}

	public void setAUC_Fecha_Registro(Time AUC_Fecha_Registro) {
		this.AUC_Fecha_Registro = AUC_Fecha_Registro;
	}

	public String getAUC_Nombre_Auxiliar_Contable() {
		return this.AUC_Nombre_Auxiliar_Contable;
	}

	public void setAUC_Nombre_Auxiliar_Contable(String AUC_Nombre_Auxiliar_Contable) {
		this.AUC_Nombre_Auxiliar_Contable = AUC_Nombre_Auxiliar_Contable;
	}

}