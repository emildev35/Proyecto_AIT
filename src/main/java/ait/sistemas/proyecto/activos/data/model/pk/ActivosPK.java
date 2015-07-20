package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the Activos database table.
 * 
 */
@Embeddable
public class ActivosPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private short ACT_Dependencia;
	
	@Column(insertable=false, updatable=false)
	private String ACT_Codigo_Activo;

	public ActivosPK() {
	}
	public short getACT_Dependencia() {
		return this.ACT_Dependencia;
	}
	public void setACT_Dependencia(short ACT_Dependencia) {
		this.ACT_Dependencia = ACT_Dependencia;
	}
	public String getACT_Codigo_Activo() {
		return this.ACT_Codigo_Activo;
	}
	public void setACT_Codigo_Activo(String ACT_Codigo_Activo) {
		this.ACT_Codigo_Activo = ACT_Codigo_Activo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActivosPK)) {
			return false;
		}
		ActivosPK castOther = (ActivosPK)other;
		return 
			(this.ACT_Dependencia == castOther.ACT_Dependencia)
			&& this.ACT_Codigo_Activo.equals(castOther.ACT_Codigo_Activo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.ACT_Dependencia);
		hash = hash * prime + this.ACT_Codigo_Activo.hashCode();
		
		return hash;
	}
}