package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Auxiliares_Contables database table.
 * 
 */
@Entity
@Table(name="Auxiliares_Contables")
@NamedQuery(name="Auxiliares_Contable.findAll", query="SELECT a FROM Auxiliares_Contable a")
public class Auxiliares_Contable implements Serializable {
	private static final long serialVersionUID = 1L;

	private String AUC_Grupo_Contable;

	@Id
	private String AUC_Auxiliar_Contable;

	private Date AUC_Fecha_Registro;

	private String AUC_Nombre_Auxiliar_Contable;

	public Auxiliares_Contable() {
	}

	public String getAUC_Grupo_Contable() {
		return this.AUC_Grupo_Contable;
	}
	public void setAUC_Grupo_Contable(String AUC_Grupo_Contable) {
		this.AUC_Grupo_Contable = AUC_Grupo_Contable;
	}
	public String getAUC_Auxiliar_Contable() {
		return this.AUC_Auxiliar_Contable;
	}
	public void setAUC_Auxiliar_Contable(String AUC_Auxiliar_Contable) {
		this.AUC_Auxiliar_Contable = AUC_Auxiliar_Contable;
	}


	public Date getAUC_Fecha_Registro() {
		return this.AUC_Fecha_Registro;
	}

	public void setAUC_Fecha_Registro(Date AUC_Fecha_Registro) {
		this.AUC_Fecha_Registro = AUC_Fecha_Registro;
	}

	public String getAUC_Nombre_Auxiliar_Contable() {
		return this.AUC_Nombre_Auxiliar_Contable;
	}

	public void setAUC_Nombre_Auxiliar_Contable(String AUC_Nombre_Auxiliar_Contable) {
		this.AUC_Nombre_Auxiliar_Contable = AUC_Nombre_Auxiliar_Contable;
	}

}