package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the Organismo_Financiador database table.
 * 
 */
@Entity
@NamedQuery(name="Organismo_Financiador.findAll", query="SELECT o FROM Organismo_Financiador o")
public class Organismo_Financiador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short ORF_Organismo_Financiador;

	private Date ORF_Fecha_Registro;

	private String ORF_Nombre_Organismo_Financiador;

	public Organismo_Financiador() {
	}

	public short getORF_Organismo_Financiador() {
		return this.ORF_Organismo_Financiador;
	}

	public void setORF_Organismo_Financiador(short ORF_Organismo_Financiador) {
		this.ORF_Organismo_Financiador = ORF_Organismo_Financiador;
	}

	public Date getORF_Fecha_Registro() {
		return this.ORF_Fecha_Registro;
	}

	public void setORF_Fecha_Registro(Date ORF_Fecha_Registro) {
		this.ORF_Fecha_Registro = ORF_Fecha_Registro;
	}

	public String getORF_Nombre_Organismo_Financiador() {
		return this.ORF_Nombre_Organismo_Financiador;
	}

	public void setORF_Nombre_Organismo_Financiador(String ORF_Nombre_Organismo_Financiador) {
		this.ORF_Nombre_Organismo_Financiador = ORF_Nombre_Organismo_Financiador;
	}

}