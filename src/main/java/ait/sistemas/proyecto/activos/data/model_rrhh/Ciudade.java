package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Ciudades database table.
 * 
 */
@Entity
@Table(name="Ciudades")
@NamedQuery(name="Ciudade.findAll", query="SELECT c FROM Ciudade c")
public class Ciudade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short CIU_Ciudad;

	private Date CIU_Fecha_Registro;

	private String CIU_Nombre_Ciudad;
	
	private String CIU_Sigla;

	public Ciudade() {
	}

	public short getCIU_Ciudad() {
		return this.CIU_Ciudad;
	}

	public void setCIU_Ciudad(short CIU_Ciudad) {
		this.CIU_Ciudad = CIU_Ciudad;
	}

	public Date getCIU_Fecha_Registro() {
		return this.CIU_Fecha_Registro;
	}

	public void setCIU_Fecha_Registro(Date CIU_Fecha_Registro) {
		this.CIU_Fecha_Registro = CIU_Fecha_Registro;
	}

	public String getCIU_Nombre_Ciudad() {
		return this.CIU_Nombre_Ciudad;
	}

	public void setCIU_Nombre_Ciudad(String CIU_Nombre_Ciudad) {
		this.CIU_Nombre_Ciudad = CIU_Nombre_Ciudad;
	}
	public String getCIU_Sigla() {
		return CIU_Sigla;
	}
	public void setCIU_Sigla(String cIU_Sigla) {
		CIU_Sigla = cIU_Sigla;
	}

}