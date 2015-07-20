package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Autorizaciones database table.
 * 
 */
@Embeddable
public class AutorizacionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private short AUT_Dependencia;

	@Column(insertable=false, updatable=false)
	private short AUT_Unidad_Organizacional;

	@Column(insertable=false, updatable=false)
	private short AUT_Tipo_Movimiento;

	@Column(insertable=false, updatable=false)
	private short AUT_Orden_Autorizacion;

	@Column(insertable=false, updatable=false)
	private long AUT_No_Documento_Autorizado;

	public AutorizacionePK() {
	}
	public short getAUT_Dependencia() {
		return this.AUT_Dependencia;
	}
	public void setAUT_Dependencia(short AUT_Dependencia) {
		this.AUT_Dependencia = AUT_Dependencia;
	}
	public short getAUT_Unidad_Organizacional() {
		return this.AUT_Unidad_Organizacional;
	}
	public void setAUT_Unidad_Organizacional(short AUT_Unidad_Organizacional) {
		this.AUT_Unidad_Organizacional = AUT_Unidad_Organizacional;
	}
	public short getAUT_Tipo_Movimiento() {
		return this.AUT_Tipo_Movimiento;
	}
	public void setAUT_Tipo_Movimiento(short AUT_Tipo_Movimiento) {
		this.AUT_Tipo_Movimiento = AUT_Tipo_Movimiento;
	}
	public short getAUT_Orden_Autorizacion() {
		return this.AUT_Orden_Autorizacion;
	}
	public void setAUT_Orden_Autorizacion(short AUT_Orden_Autorizacion) {
		this.AUT_Orden_Autorizacion = AUT_Orden_Autorizacion;
	}
	public long getAUT_No_Documento_Autorizado() {
		return this.AUT_No_Documento_Autorizado;
	}
	public void setAUT_No_Documento_Autorizado(long AUT_No_Documento_Autorizado) {
		this.AUT_No_Documento_Autorizado = AUT_No_Documento_Autorizado;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AutorizacionePK)) {
			return false;
		}
		AutorizacionePK castOther = (AutorizacionePK)other;
		return 
			(this.AUT_Dependencia == castOther.AUT_Dependencia)
			&& (this.AUT_Unidad_Organizacional == castOther.AUT_Unidad_Organizacional)
			&& (this.AUT_Tipo_Movimiento == castOther.AUT_Tipo_Movimiento)
			&& (this.AUT_Orden_Autorizacion == castOther.AUT_Orden_Autorizacion)
			&& (this.AUT_No_Documento_Autorizado == castOther.AUT_No_Documento_Autorizado);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.AUT_Dependencia);
		hash = hash * prime + ((int) this.AUT_Unidad_Organizacional);
		hash = hash * prime + ((int) this.AUT_Tipo_Movimiento);
		hash = hash * prime + ((int) this.AUT_Orden_Autorizacion);
		hash = hash * prime + ((int) (this.AUT_No_Documento_Autorizado ^ (this.AUT_No_Documento_Autorizado >>> 32)));
		
		return hash;
	}
}