package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the Ubicaciones_Fisicas database table.
 * 
 */
@Entity
@Table(name="Ubicaciones_Fisicas")
@NamedQuery(name="Ubicaciones_Fisica.findAll", query="SELECT u FROM Ubicaciones_Fisica u")
public class Ubicaciones_Fisica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int UBF_Ubicacion_Fisica;

	private short UBF_Dependencia;

	private Time UBF_Fecha_Registro;

	private short UBF_Inmueble;

	private String UBF_Nombre_Ubicacion_Fisica;

	public Ubicaciones_Fisica() {
	}

	public int getUBF_Ubicacion_Fisica() {
		return this.UBF_Ubicacion_Fisica;
	}

	public void setUBF_Ubicacion_Fisica(int UBF_Ubicacion_Fisica) {
		this.UBF_Ubicacion_Fisica = UBF_Ubicacion_Fisica;
	}

	public short getUBF_Dependencia() {
		return this.UBF_Dependencia;
	}

	public void setUBF_Dependencia(short UBF_Dependencia) {
		this.UBF_Dependencia = UBF_Dependencia;
	}

	public Time getUBF_Fecha_Registro() {
		return this.UBF_Fecha_Registro;
	}

	public void setUBF_Fecha_Registro(Time UBF_Fecha_Registro) {
		this.UBF_Fecha_Registro = UBF_Fecha_Registro;
	}

	public short getUBF_Inmueble() {
		return this.UBF_Inmueble;
	}

	public void setUBF_Inmueble(short UBF_Inmueble) {
		this.UBF_Inmueble = UBF_Inmueble;
	}

	public String getUBF_Nombre_Ubicacion_Fisica() {
		return this.UBF_Nombre_Ubicacion_Fisica;
	}

	public void setUBF_Nombre_Ubicacion_Fisica(String UBF_Nombre_Ubicacion_Fisica) {
		this.UBF_Nombre_Ubicacion_Fisica = UBF_Nombre_Ubicacion_Fisica;
	}

}