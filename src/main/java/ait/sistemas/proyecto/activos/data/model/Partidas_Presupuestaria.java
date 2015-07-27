package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Partidas_Presupuestarias database table.
 * 
 */
@Entity
@Table(name="Partidas_Presupuestarias")
@NamedQuery(name="Partidas_Presupuestaria.findAll", query="SELECT p FROM Partidas_Presupuestaria p")
public class Partidas_Presupuestaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int PAP_Partida;

	private Date PAP_Fecha_Registro;

	private String PAP_Nombre_Partida;

	public Partidas_Presupuestaria() {
	}

	public int getPAP_Partida() {
		return this.PAP_Partida;
	}

	public void setPAP_Partida(int PAP_Partida) {
		this.PAP_Partida = PAP_Partida;
	}

	public Date getPAP_Fecha_Registro() {
		return this.PAP_Fecha_Registro;
	}

	public void setPAP_Fecha_Registro(Date PAP_Fecha_Registro) {
		this.PAP_Fecha_Registro = PAP_Fecha_Registro;
	}

	public String getPAP_Nombre_Partida() {
		return this.PAP_Nombre_Partida;
	}

	public void setPAP_Nombre_Partida(String PAP_Nombre_Partida) {
		this.PAP_Nombre_Partida = PAP_Nombre_Partida;
	}

}