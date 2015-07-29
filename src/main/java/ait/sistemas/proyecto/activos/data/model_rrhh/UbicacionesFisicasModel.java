package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "archive-map-UF", entities = { @EntityResult(entityClass = UbicacionesFisicasModel.class, fields = {
		@FieldResult(name = "UBF_Ubicacion_Fisica", column = "UBF_Ubicacion_Fisica"),
		@FieldResult(name = "UBF_Dependencia", column = "UBF_Dependencia"),
		@FieldResult(name = "UBF_Fecha_Registro", column = "UBF_Fecha_Registro"),
		@FieldResult(name = "UBF_Inmueble_ID", column = "UBF_Inmueble_ID"),
		@FieldResult(name = "UBF_Inmueble", column = "UBF_Inmueble"),
		@FieldResult(name = "UBF_Nombre_Ubicacion_Fisica", column = "UBF_Nombre_Ubicacion_Fisica"),
		@FieldResult(name = "UBF_Inmueble_ID", column = "UBF_Inmueble_ID")

}) })
@Entity
public class UbicacionesFisicasModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int UBF_Ubicacion_Fisica;

	private short UBF_Dependencia;

	private Date UBF_Fecha_Registro;

	private short UBF_Inmueble_ID;
	private String UBF_Inmueble;

	private String UBF_Nombre_Ubicacion_Fisica;

	public UbicacionesFisicasModel() {
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

	public Date getUBF_Fecha_Registro() {
		return this.UBF_Fecha_Registro;
	}

	public void setUBF_Fecha_Registro(Date UBF_Fecha_Registro) {
		this.UBF_Fecha_Registro = UBF_Fecha_Registro;
	}

	public short getUBF_Inmueble_ID() {
		return UBF_Inmueble_ID;
	}

	public void setUBF_Inmueble_ID(short uBF_Inmueble_ID) {
		UBF_Inmueble_ID = uBF_Inmueble_ID;
	}

	public String getUBF_Inmueble() {
		return this.UBF_Inmueble;
	}

	public void setUBF_Inmueble(String UBF_Inmueble) {
		this.UBF_Inmueble = UBF_Inmueble;
	}

	public String getUBF_Nombre_Ubicacion_Fisica() {
		return this.UBF_Nombre_Ubicacion_Fisica;
	}

	public void setUBF_Nombre_Ubicacion_Fisica(
			String UBF_Nombre_Ubicacion_Fisica) {
		this.UBF_Nombre_Ubicacion_Fisica = UBF_Nombre_Ubicacion_Fisica;
	}

}