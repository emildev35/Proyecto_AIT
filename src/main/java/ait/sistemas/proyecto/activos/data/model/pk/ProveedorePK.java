package ait.sistemas.proyecto.activos.data.model.pk;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Proveedores database table.
 * 
 */
@Embeddable
public class ProveedorePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private short PRV_Dependencia;

	private int PRV_Proveedor;

	public ProveedorePK() {
	}
	public short getPRV_Dependencia() {
		return this.PRV_Dependencia;
	}
	public void setPRV_Dependencia(short PRV_Dependencia) {
		this.PRV_Dependencia = PRV_Dependencia;
	}
	public int getPRV_Proveedor() {
		return this.PRV_Proveedor;
	}
	public void setPRV_Proveedor(int PRV_Proveedor) {
		this.PRV_Proveedor = PRV_Proveedor;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProveedorePK)) {
			return false;
		}
		ProveedorePK castOther = (ProveedorePK)other;
		return 
			(this.PRV_Dependencia == castOther.PRV_Dependencia)
			&& (this.PRV_Proveedor == castOther.PRV_Proveedor);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.PRV_Dependencia);
		hash = hash * prime + this.PRV_Proveedor;
		
		return hash;
	}
}