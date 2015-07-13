package ait.sistemas.proyecto.seguridad.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Permisos database table.
 * 
 */
@Embeddable
public class PermisoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String PRM_Id_Usuario;

	@Column(insertable=false, updatable=false)
	private long PRM_Identificador;

	public PermisoPK() {
	}
	public String getPRM_Id_Usuario() {
		return this.PRM_Id_Usuario;
	}
	public void setPRM_Id_Usuario(String PRM_Id_Usuario) {
		this.PRM_Id_Usuario = PRM_Id_Usuario;
	}
	public long getPRM_Identificador() {
		return this.PRM_Identificador;
	}
	public void setPRM_Identificador(long PRM_Identificador) {
		this.PRM_Identificador = PRM_Identificador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PermisoPK)) {
			return false;
		}
		PermisoPK castOther = (PermisoPK)other;
		return 
			this.PRM_Id_Usuario.equals(castOther.PRM_Id_Usuario)
			&& (this.PRM_Identificador == castOther.PRM_Identificador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.PRM_Id_Usuario.hashCode();
		hash = hash * prime + ((int) (this.PRM_Identificador ^ (this.PRM_Identificador >>> 32)));
		
		return hash;
	}
}