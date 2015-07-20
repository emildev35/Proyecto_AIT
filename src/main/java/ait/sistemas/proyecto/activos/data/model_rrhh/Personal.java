package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the Personal database table.
 * 
 */
@Entity
@NamedQuery(name="Personal.findAll", query="SELECT p FROM Personal p")
public class Personal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String PER_CI_Empleado;

	private String PER_Apellido_Materno;

	private String PER_Apellido_Paterno;

	private short PER_Dependencia;

	private Date PER_Fecha_Registro;

	private String PER_No_Interno;

	private String PER_No_Telefono_Oficina;

	private String PER_Nombres;

	private short PER_Unidad_Organizacional;

	public Personal() {
	}

	public String getPER_CI_Empleado() {
		return this.PER_CI_Empleado;
	}

	public void setPER_CI_Empleado(String PER_CI_Empleado) {
		this.PER_CI_Empleado = PER_CI_Empleado;
	}

	public String getPER_Apellido_Materno() {
		return this.PER_Apellido_Materno;
	}

	public void setPER_Apellido_Materno(String PER_Apellido_Materno) {
		this.PER_Apellido_Materno = PER_Apellido_Materno;
	}

	public String getPER_Apellido_Paterno() {
		return this.PER_Apellido_Paterno;
	}

	public void setPER_Apellido_Paterno(String PER_Apellido_Paterno) {
		this.PER_Apellido_Paterno = PER_Apellido_Paterno;
	}

	public short getPER_Dependencia() {
		return this.PER_Dependencia;
	}

	public void setPER_Dependencia(short PER_Dependencia) {
		this.PER_Dependencia = PER_Dependencia;
	}

	public Date getPER_Fecha_Registro() {
		return this.PER_Fecha_Registro;
	}

	public void setPER_Fecha_Registro(Date PER_Fecha_Registro) {
		this.PER_Fecha_Registro = PER_Fecha_Registro;
	}

	public String getPER_No_Interno() {
		return this.PER_No_Interno;
	}

	public void setPER_No_Interno(String PER_No_Interno) {
		this.PER_No_Interno = PER_No_Interno;
	}

	public String getPER_No_Telefono_Oficina() {
		return this.PER_No_Telefono_Oficina;
	}

	public void setPER_No_Telefono_Oficina(String PER_No_Telefono_Oficina) {
		this.PER_No_Telefono_Oficina = PER_No_Telefono_Oficina;
	}

	public String getPER_Nombres() {
		return this.PER_Nombres;
	}

	public void setPER_Nombres(String PER_Nombres) {
		this.PER_Nombres = PER_Nombres;
	}

	public short getPER_Unidad_Organizacional() {
		return this.PER_Unidad_Organizacional;
	}

	public void setPER_Unidad_Organizacional(short PER_Unidad_Organizacional) {
		this.PER_Unidad_Organizacional = PER_Unidad_Organizacional;
	}

}