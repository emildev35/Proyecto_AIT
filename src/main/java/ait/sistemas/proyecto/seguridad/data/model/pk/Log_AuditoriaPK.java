package ait.sistemas.proyecto.seguridad.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Log_Auditoria database table.
 * 
 */
@Embeddable
public class Log_AuditoriaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String LGA_Id_Usuario;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date LGA_Fecha_registro;

	private String LGA_Hora_Registro;

	private long LGA_Identificador;

	public Log_AuditoriaPK() {
	}
	public String getLGA_Id_Usuario() {
		return this.LGA_Id_Usuario;
	}
	public void setLGA_Id_Usuario(String LGA_Id_Usuario) {
		this.LGA_Id_Usuario = LGA_Id_Usuario;
	}
	public java.util.Date getLGA_Fecha_registro() {
		return this.LGA_Fecha_registro;
	}
	public void setLGA_Fecha_registro(java.util.Date LGA_Fecha_registro) {
		this.LGA_Fecha_registro = LGA_Fecha_registro;
	}
	public String getLGA_Hora_Registro() {
		return this.LGA_Hora_Registro;
	}
	public void setLGA_Hora_Registro(String LGA_Hora_Registro) {
		this.LGA_Hora_Registro = LGA_Hora_Registro;
	}
	public long getLGA_Identificador() {
		return this.LGA_Identificador;
	}
	public void setLGA_Identificador(long LGA_Identificador) {
		this.LGA_Identificador = LGA_Identificador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Log_AuditoriaPK)) {
			return false;
		}
		Log_AuditoriaPK castOther = (Log_AuditoriaPK)other;
		return 
			this.LGA_Id_Usuario.equals(castOther.LGA_Id_Usuario)
			&& this.LGA_Fecha_registro.equals(castOther.LGA_Fecha_registro)
			&& this.LGA_Hora_Registro.equals(castOther.LGA_Hora_Registro)
			&& (this.LGA_Identificador == castOther.LGA_Identificador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.LGA_Id_Usuario.hashCode();
		hash = hash * prime + this.LGA_Fecha_registro.hashCode();
		hash = hash * prime + this.LGA_Hora_Registro.hashCode();
		hash = hash * prime + ((int) (this.LGA_Identificador ^ (this.LGA_Identificador >>> 32)));
		
		return hash;
	}
}