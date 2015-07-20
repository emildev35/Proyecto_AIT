package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;


/**
 * The persistent class for the Grupos_Contables database table.
 * 
 */
@Entity
@Table(name="Grupos_Contables")
@NamedQuery(name="Grupos_Contable.findAll", query="SELECT g FROM Grupos_Contable g")
public class Grupos_Contable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String GRC_Grupo_Contable;

	private BigDecimal GRC_Coeficiente;

	private Time GRC_Fecha_Registro;

	private String GRC_Nombre_Grupo_Contable;

	private int GRC_Partida;

	private short GRC_Vida_Util;

	public Grupos_Contable() {
	}

	public String getGRC_Grupo_Contable() {
		return this.GRC_Grupo_Contable;
	}

	public void setGRC_Grupo_Contable(String GRC_Grupo_Contable) {
		this.GRC_Grupo_Contable = GRC_Grupo_Contable;
	}

	public BigDecimal getGRC_Coeficiente() {
		return this.GRC_Coeficiente;
	}

	public void setGRC_Coeficiente(BigDecimal GRC_Coeficiente) {
		this.GRC_Coeficiente = GRC_Coeficiente;
	}

	public Time getGRC_Fecha_Registro() {
		return this.GRC_Fecha_Registro;
	}

	public void setGRC_Fecha_Registro(Time GRC_Fecha_Registro) {
		this.GRC_Fecha_Registro = GRC_Fecha_Registro;
	}

	public String getGRC_Nombre_Grupo_Contable() {
		return this.GRC_Nombre_Grupo_Contable;
	}

	public void setGRC_Nombre_Grupo_Contable(String GRC_Nombre_Grupo_Contable) {
		this.GRC_Nombre_Grupo_Contable = GRC_Nombre_Grupo_Contable;
	}

	public int getGRC_Partida() {
		return this.GRC_Partida;
	}

	public void setGRC_Partida(int GRC_Partida) {
		this.GRC_Partida = GRC_Partida;
	}

	public short getGRC_Vida_Util() {
		return this.GRC_Vida_Util;
	}

	public void setGRC_Vida_Util(short GRC_Vida_Util) {
		this.GRC_Vida_Util = GRC_Vida_Util;
	}

}