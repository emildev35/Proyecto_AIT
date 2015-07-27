package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(
		  name="archive-map-g",
		  entities={
		    @EntityResult(
		      entityClass=GruposContablesModel.class,
		      fields={
		        @FieldResult(name="GRC_Grupo_Contable", column="GRC_Grupo_Contable"),
		        @FieldResult(name="GRC_Coeficiente", column="GRC_Coeficiente"),
		        @FieldResult(name="GRC_Fecha_Registro", column="GRC_Fecha_Registro"),
		        @FieldResult(name="GRC_Nombre_Grupo_Contable", column="GRC_Nombre_Grupo_Contable"),
		        @FieldResult(name="GRC_Partida_ID", column="GRC_Partida_ID"),
		        @FieldResult(name="GRC_Partida", column="GRC_Partida"),
		        @FieldResult(name="GRC_Vida_Util", column="GRC_Vida_Util")
		
		      }
		    )
		  }
		)
@Entity
public class GruposContablesModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String GRC_Grupo_Contable;

	private BigDecimal GRC_Coeficiente;

	private Date GRC_Fecha_Registro;

	private String GRC_Nombre_Grupo_Contable;

	private int GRC_Partida_ID;
	private String GRC_Partida;

	private short GRC_Vida_Util;

	public GruposContablesModel() {
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

	public Date getGRC_Fecha_Registro() {
		return this.GRC_Fecha_Registro;
	}

	public void setGRC_Fecha_Registro(Date GRC_Fecha_Registro) {
		this.GRC_Fecha_Registro = GRC_Fecha_Registro;
	}

	public String getGRC_Nombre_Grupo_Contable() {
		return this.GRC_Nombre_Grupo_Contable;
	}

	public void setGRC_Nombre_Grupo_Contable(String GRC_Nombre_Grupo_Contable) {
		this.GRC_Nombre_Grupo_Contable = GRC_Nombre_Grupo_Contable;
	}
public int getGRC_Partida_ID() {
	return GRC_Partida_ID;
}
public void setGRC_Partida_ID(int gRC_Partida_ID) {
	GRC_Partida_ID = gRC_Partida_ID;
}	
	public String getGRC_Partida() {
		return this.GRC_Partida;
	}

	public void setGRC_Partida(String GRC_Partida) {
		this.GRC_Partida = GRC_Partida;
	}

	public short getGRC_Vida_Util() {
		return this.GRC_Vida_Util;
	}

	public void setGRC_Vida_Util(short GRC_Vida_Util) {
		this.GRC_Vida_Util = GRC_Vida_Util;
	}

}