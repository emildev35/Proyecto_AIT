package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Componentes database table.
 * 
 */
@Embeddable
public class ComponentePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private short COM_Dependencia;

	//@Column(insertable=false, updatable=false)
	private String COM_Codigo_Activo;

	private short COM_Id_Componente;

	public ComponentePK() {
	}
	public short getCOM_Dependencia() {
		return this.COM_Dependencia;
	}
	public void setCOM_Dependencia(short COM_Dependencia) {
		this.COM_Dependencia = COM_Dependencia;
	}
	public String getCOM_Codigo_Activo() {
		return this.COM_Codigo_Activo;
	}
	public void setCOM_Codigo_Activo(String COM_Codigo_Activo) {
		this.COM_Codigo_Activo = COM_Codigo_Activo;
	}
	public short getCOM_Id_Componente() {
		return this.COM_Id_Componente;
	}
	public void setCOM_Id_Componente(short COM_Id_Componente) {
		this.COM_Id_Componente = COM_Id_Componente;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ComponentePK)) {
			return false;
		}
		ComponentePK castOther = (ComponentePK)other;
		return 
			(this.COM_Dependencia == castOther.COM_Dependencia)
			&& this.COM_Codigo_Activo.equals(castOther.COM_Codigo_Activo)
			&& (this.COM_Id_Componente == castOther.COM_Id_Componente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.COM_Dependencia);
		hash = hash * prime + this.COM_Codigo_Activo.hashCode();
		hash = hash * prime + ((int) this.COM_Id_Componente);
		
		return hash;
	}
}