package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Partidas_Presupuestarias database table.
 * 
 */
@Entity
@Table(name="Motivo_Baja")
@NamedQuery(name="Motivo_Baja.findAll", query="SELECT m FROM Motivo_Baja m")
public class Motivo_Baja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short MBA_Motivo_Baja;

	private String MBA_Descripcion;

	public Motivo_Baja() {
	}

	public short getMBA_Motivo_Baja() {
		return MBA_Motivo_Baja;
	}

	public void setMBA_Motivo_Baja(short mBA_Motivo_Baja) {
		MBA_Motivo_Baja = mBA_Motivo_Baja;
	}

	public String getMBA_Descripcion() {
		return MBA_Descripcion;
	}

	public void setMBA_Descripcion(String mBA_Descripcion) {
		MBA_Descripcion = mBA_Descripcion;
	}
}