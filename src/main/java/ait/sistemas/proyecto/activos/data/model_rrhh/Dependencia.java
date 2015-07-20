package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the Dependencias database table.
 * 
 */
@Entity
@Table(name="Dependencias")
@NamedQuery(name="Dependencia.findAll", query="SELECT d FROM Dependencia d")
public class Dependencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short DEP_Dependencia;

	private short DEP_Ciudad;

	private String DEP_Domicilio;

	private Date DEP_Fecha_Registro;

	private String DEP_Nombre_Dependencia;

	private String DEP_Sigla;
	
	private String DEP_Pagina_Web;

	private String DEP_Telefono;

	public Dependencia() {
	}

	public short getDEP_Dependencia() {
		return this.DEP_Dependencia;
	}

	public void setDEP_Dependencia(short DEP_Dependencia) {
		this.DEP_Dependencia = DEP_Dependencia;
	}

	public short getDEP_Ciudad() {
		return this.DEP_Ciudad;
	}

	public void setDEP_Ciudad(short DEP_Ciudad) {
		this.DEP_Ciudad = DEP_Ciudad;
	}

	public String getDEP_Domicilio() {
		return this.DEP_Domicilio;
	}

	public void setDEP_Domicilio(String DEP_Domicilio) {
		this.DEP_Domicilio = DEP_Domicilio;
	}

	public Date getDEP_Fecha_Registro() {
		return this.DEP_Fecha_Registro;
	}

	public void setDEP_Fecha_Registro(Date DEP_Fecha_Registro) {
		this.DEP_Fecha_Registro = DEP_Fecha_Registro;
	}

	public String getDEP_Nombre_Dependencia() {
		return this.DEP_Nombre_Dependencia;
	}

	public void setDEP_Nombre_Dependencia(String DEP_Nombre_Dependencia) {
		this.DEP_Nombre_Dependencia = DEP_Nombre_Dependencia;
	}
	
	public String getDEP_Sigla() {
		return DEP_Sigla;
	}
	public void setDEP_Sigla(String dEP_Sigla) {
		DEP_Sigla = dEP_Sigla;
	}

	public String getDEP_Pagina_Web() {
		return this.DEP_Pagina_Web;
	}

	public void setDEP_Pagina_Web(String DEP_Pagina_Web) {
		this.DEP_Pagina_Web = DEP_Pagina_Web;
	}

	public String getDEP_Telefono() {
		return this.DEP_Telefono;
	}

	public void setDEP_Telefono(String DEP_Telefono) {
		this.DEP_Telefono = DEP_Telefono;
	}

}