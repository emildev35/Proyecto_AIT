package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the C_Movimientos database table.
 * 
 */
@Embeddable
public class C_MovimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private short CMV_Dependencia;

	private short CMV_Unidad_Organizacional_Origen;

	@Column(insertable=false, updatable=false)
	private short CMV_Tipo_Movimiento;

	private long CMV_No_Documento;

	public C_MovimientoPK() {
	}
	public short getCMV_Dependencia() {
		return this.CMV_Dependencia;
	}
	public void setCMV_Dependencia(short CMV_Dependencia) {
		this.CMV_Dependencia = CMV_Dependencia;
	}
	public short getCMV_Unidad_Organizacional_Origen() {
		return this.CMV_Unidad_Organizacional_Origen;
	}
	public void setCMV_Unidad_Organizacional_Origen(short CMV_Unidad_Organizacional_Origen) {
		this.CMV_Unidad_Organizacional_Origen = CMV_Unidad_Organizacional_Origen;
	}
	public short getCMV_Tipo_Movimiento() {
		return this.CMV_Tipo_Movimiento;
	}
	public void setCMV_Tipo_Movimiento(short CMV_Tipo_Movimiento) {
		this.CMV_Tipo_Movimiento = CMV_Tipo_Movimiento;
	}
	public long getCMV_No_Documento() {
		return this.CMV_No_Documento;
	}
	public void setCMV_No_Documento(long CMV_No_Documento) {
		this.CMV_No_Documento = CMV_No_Documento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof C_MovimientoPK)) {
			return false;
		}
		C_MovimientoPK castOther = (C_MovimientoPK)other;
		return 
			(this.CMV_Dependencia == castOther.CMV_Dependencia)
			&& (this.CMV_Unidad_Organizacional_Origen == castOther.CMV_Unidad_Organizacional_Origen)
			&& (this.CMV_Tipo_Movimiento == castOther.CMV_Tipo_Movimiento)
			&& (this.CMV_No_Documento == castOther.CMV_No_Documento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.CMV_Dependencia);
		hash = hash * prime + ((int) this.CMV_Unidad_Organizacional_Origen);
		hash = hash * prime + ((int) this.CMV_Tipo_Movimiento);
		hash = hash * prime + ((int) (this.CMV_No_Documento ^ (this.CMV_No_Documento >>> 32)));
		
		return hash;
	}
}