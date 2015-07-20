package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Tipos_Autorizacion database table.
 * 
 */
@Embeddable
public class Tipos_AutorizacionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private short TAU_Dependencia;

	private short TAU_Unidad_Organizacional;

	private short TAU_Tipo_Movimiento;

	private short TAU_Orden_Autorizacion;

	public Tipos_AutorizacionPK() {
	}
	public short getTAU_Dependencia() {
		return this.TAU_Dependencia;
	}
	public void setTAU_Dependencia(short TAU_Dependencia) {
		this.TAU_Dependencia = TAU_Dependencia;
	}
	public short getTAU_Unidad_Organizacional() {
		return this.TAU_Unidad_Organizacional;
	}
	public void setTAU_Unidad_Organizacional(short TAU_Unidad_Organizacional) {
		this.TAU_Unidad_Organizacional = TAU_Unidad_Organizacional;
	}
	public short getTAU_Tipo_Movimiento() {
		return this.TAU_Tipo_Movimiento;
	}
	public void setTAU_Tipo_Movimiento(short TAU_Tipo_Movimiento) {
		this.TAU_Tipo_Movimiento = TAU_Tipo_Movimiento;
	}
	public short getTAU_Orden_Autorizacion() {
		return this.TAU_Orden_Autorizacion;
	}
	public void setTAU_Orden_Autorizacion(short TAU_Orden_Autorizacion) {
		this.TAU_Orden_Autorizacion = TAU_Orden_Autorizacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Tipos_AutorizacionPK)) {
			return false;
		}
		Tipos_AutorizacionPK castOther = (Tipos_AutorizacionPK)other;
		return 
			(this.TAU_Dependencia == castOther.TAU_Dependencia)
			&& (this.TAU_Unidad_Organizacional == castOther.TAU_Unidad_Organizacional)
			&& (this.TAU_Tipo_Movimiento == castOther.TAU_Tipo_Movimiento)
			&& (this.TAU_Orden_Autorizacion == castOther.TAU_Orden_Autorizacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.TAU_Dependencia);
		hash = hash * prime + ((int) this.TAU_Unidad_Organizacional);
		hash = hash * prime + ((int) this.TAU_Tipo_Movimiento);
		hash = hash * prime + ((int) this.TAU_Orden_Autorizacion);
		
		return hash;
	}
}