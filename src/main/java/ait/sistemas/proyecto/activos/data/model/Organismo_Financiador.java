package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


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

	private Time ORF_Fecha_Registro;

	private String ORF_Nombre_Organismo_Financiador;

	public Organismo_Financiador() {
	}

	public short getORF_Organismo_Financiador() {
		return this.ORF_Organismo_Financiador;
	}

	public void setORF_Organismo_Financiador(short ORF_Organismo_Financiador) {
		this.ORF_Organismo_Financiador = ORF_Organismo_Financiador;
	}

	public Time getORF_Fecha_Registro() {
		return this.ORF_Fecha_Registro;
	}

	public void setORF_Fecha_Registro(Time ORF_Fecha_Registro) {
		this.ORF_Fecha_Registro = ORF_Fecha_Registro;
	}

	public String getORF_Nombre_Organismo_Financiador() {
		return this.ORF_Nombre_Organismo_Financiador;
	}

	public void setORF_Nombre_Organismo_Financiador(String ORF_Nombre_Organismo_Financiador) {
		this.ORF_Nombre_Organismo_Financiador = ORF_Nombre_Organismo_Financiador;
	}

}