package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Documentos_Respaldo database table.
 * 
 */
@Embeddable
public class Documentos_RespaldoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private short DOR_Dependencia;

	//@Column(insertable=false, updatable=false)
	private String DOR_Codigo_Activo;
	
	
	private int DOR_Id_Documento_Respaldo;

	public Documentos_RespaldoPK() {
	}
	public short getDOR_Dependencia() {
		return this.DOR_Dependencia;
	}
	public void setDOR_Dependencia(short DOR_Dependencia) {
		this.DOR_Dependencia = DOR_Dependencia;
	}
	public String getDOR_Codigo_Activo() {
		return this.DOR_Codigo_Activo;
	}
	public void setDOR_Codigo_Activo(String DOR_Codigo_Activo) {
		this.DOR_Codigo_Activo = DOR_Codigo_Activo;
	}
	public int getDOR_Id_Documento_Respaldo() {
		return this.DOR_Id_Documento_Respaldo;
	}
	public void setDOR_Id_Documento_Respaldo(int DOR_Id_Documento_Respaldo) {
		this.DOR_Id_Documento_Respaldo = DOR_Id_Documento_Respaldo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Documentos_RespaldoPK)) {
			return false;
		}
		Documentos_RespaldoPK castOther = (Documentos_RespaldoPK)other;
		return 
			(this.DOR_Dependencia == castOther.DOR_Dependencia)
			&& this.DOR_Codigo_Activo.equals(castOther.DOR_Codigo_Activo)
			&& (this.DOR_Id_Documento_Respaldo == castOther.DOR_Id_Documento_Respaldo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.DOR_Dependencia);
		hash = hash * prime + this.DOR_Codigo_Activo.hashCode();
		hash = hash * prime + this.DOR_Id_Documento_Respaldo;
		
		return hash;
	}
}