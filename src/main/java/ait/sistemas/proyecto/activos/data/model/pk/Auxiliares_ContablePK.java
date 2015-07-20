package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Auxiliares_Contables database table.
 * 
 */
@Embeddable
public class Auxiliares_ContablePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String AUC_Grupo_Contable;

	private String AUC_Auxiliar_Contable;

	public Auxiliares_ContablePK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Auxiliares_ContablePK)) {
			return false;
		}
		Auxiliares_ContablePK castOther = (Auxiliares_ContablePK)other;
		return 
			this.AUC_Grupo_Contable.equals(castOther.AUC_Grupo_Contable)
			&& this.AUC_Auxiliar_Contable.equals(castOther.AUC_Auxiliar_Contable);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.AUC_Grupo_Contable.hashCode();
		hash = hash * prime + this.AUC_Auxiliar_Contable.hashCode();
		
		return hash;
	}
}