package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "archive-map-a", entities = { @EntityResult(entityClass = AuxiliaresContablesModel.class, fields = {
		@FieldResult(name = "AUC_Grupo_Contable_ID", column = "AUC_Grupo_Contable_ID"),
		@FieldResult(name = "AUC_Grupo_Contable", column = "AUC_Grupo_Contable"),
		@FieldResult(name = "AUC_Auxiliar_Contable", column = "AUC_Auxiliar_Contable"),
		@FieldResult(name = "AUC_Fecha_Registro", column = "AUC_Fecha_Registro"),
		@FieldResult(name = "AUC_Nombre_Auxiliar_Contable", column = "AUC_Nombre_Auxiliar_Contable")

}) })
@Entity
public class AuxiliaresContablesModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String AUC_Grupo_Contable_ID;
	private String AUC_Grupo_Contable;

	@Id
	private String AUC_Auxiliar_Grupo_Contable;

	private String AUC_Auxiliar_Contable;

	private Date AUC_Fecha_Registro;

	private String AUC_Nombre_Auxiliar_Contable;

	public AuxiliaresContablesModel() {
	}

	public String getAUC_Auxiliar_Grupo_Contable() {
		return AUC_Auxiliar_Grupo_Contable;
	}

	public void setAUC_Auxiliar_Grupo_Contable(
			String aUC_Auxiliar_Grupo_Contable) {
		AUC_Auxiliar_Grupo_Contable = aUC_Auxiliar_Grupo_Contable;
	}

	public String getAUC_Grupo_Contable_ID() {
		return this.AUC_Grupo_Contable_ID;
	}

	public void setAUC_Grupo_Contable_ID(String AUC_Grupo_Contable_ID) {
		this.AUC_Grupo_Contable_ID = AUC_Grupo_Contable_ID;
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

	public void setAUC_Nombre_Auxiliar_Contable(
			String AUC_Nombre_Auxiliar_Contable) {
		this.AUC_Nombre_Auxiliar_Contable = AUC_Nombre_Auxiliar_Contable;
	}

}