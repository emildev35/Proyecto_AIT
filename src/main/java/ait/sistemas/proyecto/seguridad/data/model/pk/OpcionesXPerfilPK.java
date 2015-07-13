package ait.sistemas.proyecto.seguridad.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OpcionesXPerfil database table.
 * 
 */
@Embeddable
public class OpcionesXPerfilPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int OPF_Id_Perfil;

	private int OPF_Id_Identificador;

	public OpcionesXPerfilPK() {
	}
	public int getOPF_Id_Perfil() {
		return this.OPF_Id_Perfil;
	}
	public void setOPF_Id_Perfil(int OPF_Id_Perfil) {
		this.OPF_Id_Perfil = OPF_Id_Perfil;
	}
	public int getOPF_Id_Identificador() {
		return this.OPF_Id_Identificador;
	}
	public void setOPF_Id_Identificador(int OPF_Id_Identificador) {
		this.OPF_Id_Identificador = OPF_Id_Identificador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OpcionesXPerfilPK)) {
			return false;
		}
		OpcionesXPerfilPK castOther = (OpcionesXPerfilPK)other;
		return 
			(this.OPF_Id_Perfil == castOther.OPF_Id_Perfil)
			&& (this.OPF_Id_Identificador == castOther.OPF_Id_Identificador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.OPF_Id_Perfil;
		hash = hash * prime + this.OPF_Id_Identificador;
		
		return hash;
	}
}