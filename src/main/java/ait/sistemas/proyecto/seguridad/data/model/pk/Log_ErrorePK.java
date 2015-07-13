package ait.sistemas.proyecto.seguridad.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Log_Errores database table.
 * 
 */
@Embeddable
public class Log_ErrorePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date LGE_Fecha_Registro;

	private String LGE_Hora_Registro;

	private long LGE_Identificador;

	public Log_ErrorePK() {
	}
	public java.util.Date getLGE_Fecha_Registro() {
		return this.LGE_Fecha_Registro;
	}
	public void setLGE_Fecha_Registro(java.util.Date LGE_Fecha_Registro) {
		this.LGE_Fecha_Registro = LGE_Fecha_Registro;
	}
	public String getLGE_Hora_Registro() {
		return this.LGE_Hora_Registro;
	}
	public void setLGE_Hora_Registro(String LGE_Hora_Registro) {
		this.LGE_Hora_Registro = LGE_Hora_Registro;
	}
	public long getLGE_Identificador() {
		return this.LGE_Identificador;
	}
	public void setLGE_Identificador(long LGE_Identificador) {
		this.LGE_Identificador = LGE_Identificador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Log_ErrorePK)) {
			return false;
		}
		Log_ErrorePK castOther = (Log_ErrorePK)other;
		return 
			this.LGE_Fecha_Registro.equals(castOther.LGE_Fecha_Registro)
			&& this.LGE_Hora_Registro.equals(castOther.LGE_Hora_Registro)
			&& (this.LGE_Identificador == castOther.LGE_Identificador);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.LGE_Fecha_Registro.hashCode();
		hash = hash * prime + this.LGE_Hora_Registro.hashCode();
		hash = hash * prime + ((int) (this.LGE_Identificador ^ (this.LGE_Identificador >>> 32)));
		
		return hash;
	}
}