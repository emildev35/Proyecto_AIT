package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Tipos_Activo database table.
 * 
 */
@Entity
@NamedQuery(name="Tipos_Activo.findAll", query="SELECT t FROM Tipos_Activo t")
public class Tipos_Activo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short TAC_Id_Tipo_Activo;

	private String TAC_Nombre_Tipo_Activo;

	public Tipos_Activo() {
	}

	public short getTAC_Id_Tipo_Activo() {
		return this.TAC_Id_Tipo_Activo;
	}

	public void setTAC_Id_Tipo_Activo(short TAC_Id_Tipo_Activo) {
		this.TAC_Id_Tipo_Activo = TAC_Id_Tipo_Activo;
	}

	public String getTAC_Nombre_Tipo_Activo() {
		return this.TAC_Nombre_Tipo_Activo;
	}

	public void setTAC_Nombre_Tipo_Activo(String TAC_Nombre_Tipo_Activo) {
		this.TAC_Nombre_Tipo_Activo = TAC_Nombre_Tipo_Activo;
	}

}