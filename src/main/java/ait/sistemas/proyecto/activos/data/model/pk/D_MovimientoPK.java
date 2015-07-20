package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the D_Movimientos database table.
 * 
 */
@Embeddable
public class D_MovimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false)
	private short DMV_Dependencia;

	//@Column(insertable=false, updatable=false)
	private short DMV_Unidad_Organizacional;

	//@Column(insertable=false, updatable=false)
	private short DMV_Tipo_Movimiento;

	//@Column(insertable=false, updatable=false)
	private long DMV_No_Documento;

	//@Column(insertable=false, updatable=false)
	private String DMV_Codigo_Activo;

	public D_MovimientoPK() {
	}
	public short getDMV_Dependencia() {
		return this.DMV_Dependencia;
	}
	public void setDMV_Dependencia(short DMV_Dependencia) {
		this.DMV_Dependencia = DMV_Dependencia;
	}
	public short getDMV_Unidad_Organizacional() {
		return this.DMV_Unidad_Organizacional;
	}
	public void setDMV_Unidad_Organizacional(short DMV_Unidad_Organizacional) {
		this.DMV_Unidad_Organizacional = DMV_Unidad_Organizacional;
	}
	public short getDMV_Tipo_Movimiento() {
		return this.DMV_Tipo_Movimiento;
	}
	public void setDMV_Tipo_Movimiento(short DMV_Tipo_Movimiento) {
		this.DMV_Tipo_Movimiento = DMV_Tipo_Movimiento;
	}
	public long getDMV_No_Documento() {
		return this.DMV_No_Documento;
	}
	public void setDMV_No_Documento(long DMV_No_Documento) {
		this.DMV_No_Documento = DMV_No_Documento;
	}
	public String getDMV_Codigo_Activo() {
		return this.DMV_Codigo_Activo;
	}
	public void setDMV_Codigo_Activo(String DMV_Codigo_Activo) {
		this.DMV_Codigo_Activo = DMV_Codigo_Activo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof D_MovimientoPK)) {
			return false;
		}
		D_MovimientoPK castOther = (D_MovimientoPK)other;
		return 
			(this.DMV_Dependencia == castOther.DMV_Dependencia)
			&& (this.DMV_Unidad_Organizacional == castOther.DMV_Unidad_Organizacional)
			&& (this.DMV_Tipo_Movimiento == castOther.DMV_Tipo_Movimiento)
			&& (this.DMV_No_Documento == castOther.DMV_No_Documento)
			&& this.DMV_Codigo_Activo.equals(castOther.DMV_Codigo_Activo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.DMV_Dependencia);
		hash = hash * prime + ((int) this.DMV_Unidad_Organizacional);
		hash = hash * prime + ((int) this.DMV_Tipo_Movimiento);
		hash = hash * prime + ((int) (this.DMV_No_Documento ^ (this.DMV_No_Documento >>> 32)));
		hash = hash * prime + this.DMV_Codigo_Activo.hashCode();
		
		return hash;
	}
}