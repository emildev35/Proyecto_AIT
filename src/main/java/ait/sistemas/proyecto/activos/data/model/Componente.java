package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Componentes database table.
 * 
 */

@Table(name="Componentes")
@NamedQuery(name="Componente.findAll", query="SELECT c FROM Componente c")
public class Componente implements Serializable {
	private static final long serialVersionUID = 1L;

	private short COM_Dependencia;

	private String COM_Codigo_Activo;

	@Id
	private short COM_Id_Componente;

	private String COM_Caracteristica_Componente;

	private Time COM_Fecha_Registro;

	private String COM_Nombre_Componente;

	public Componente() {
	}

	public short getCOM_Dependencia() {
		return this.COM_Dependencia;
	}
	public void setCOM_Dependencia(short COM_Dependencia) {
		this.COM_Dependencia = COM_Dependencia;
	}
	public String getCOM_Codigo_Activo() {
		return this.COM_Codigo_Activo;
	}
	public void setCOM_Codigo_Activo(String COM_Codigo_Activo) {
		this.COM_Codigo_Activo = COM_Codigo_Activo;
	}
	public short getCOM_Id_Componente() {
		return this.COM_Id_Componente;
	}
	public void setCOM_Id_Componente(short COM_Id_Componente) {
		this.COM_Id_Componente = COM_Id_Componente;
	}
	public String getCOM_Caracteristica_Componente() {
		return this.COM_Caracteristica_Componente;
	}

	public void setCOM_Caracteristica_Componente(String COM_Caracteristica_Componente) {
		this.COM_Caracteristica_Componente = COM_Caracteristica_Componente;
	}

	public Time getCOM_Fecha_Registro() {
		return this.COM_Fecha_Registro;
	}

	public void setCOM_Fecha_Registro(Time COM_Fecha_Registro) {
		this.COM_Fecha_Registro = COM_Fecha_Registro;
	}

	public String getCOM_Nombre_Componente() {
		return this.COM_Nombre_Componente;
	}

	public void setCOM_Nombre_Componente(String COM_Nombre_Componente) {
		this.COM_Nombre_Componente = COM_Nombre_Componente;
	}

}