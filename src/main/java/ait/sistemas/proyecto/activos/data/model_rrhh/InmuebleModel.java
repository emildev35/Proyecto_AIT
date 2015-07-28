package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
@SqlResultSetMapping(
		  name="archive-map-inmueble",
		  entities={
		    @EntityResult(
		      entityClass=InmuebleModel.class,
		      fields={
		        @FieldResult(name="INM_Inmueble", column="INM_Inmueble"),
		        @FieldResult(name="INM_Ciudad_ID", column="INM_Ciudad_ID"),
		        @FieldResult(name="INM_Ciudad", column="INM_Ciudad"),
		        @FieldResult(name="INM_Domicilio_Inmueble", column="INM_Domicilio_Inmueble"),
		        @FieldResult(name="INM_Fecha_Registro", column="INM_Fecha_Registro"),
		        @FieldResult(name="INM_Nombre_Inmueble", column="INM_Nombre_Inmueble")
		
		      }
		    )
		  }
		)
@Entity
public class InmuebleModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short INM_Inmueble;

	private short INM_Ciudad_ID;
	private String INM_Ciudad;

	private String INM_Domicilio_Inmueble;

	private Date INM_Fecha_Registro;

	private String INM_Nombre_Inmueble;

	public InmuebleModel() {
	}

	public short getINM_Inmueble() {
		return this.INM_Inmueble;
	}

	public void setINM_Inmueble(short INM_Inmueble) {
		this.INM_Inmueble = INM_Inmueble;
	}

	public short getINM_Ciudad_ID() {
		return INM_Ciudad_ID;
	}

	public void setINM_Ciudad_ID(short iNM_Ciudad_ID) {
		INM_Ciudad_ID = iNM_Ciudad_ID;
	}

	public String getINM_Ciudad() {
		return this.INM_Ciudad;
	}

	public void setINM_Ciudad(String INM_Ciudad) {
		this.INM_Ciudad = INM_Ciudad;
	}

	public String getINM_Domicilio_Inmueble() {
		return this.INM_Domicilio_Inmueble;
	}

	public void setINM_Domicilio_Inmueble(String INM_Domicilio_Inmueble) {
		this.INM_Domicilio_Inmueble = INM_Domicilio_Inmueble;
	}

	public Date getINM_Fecha_Registro() {
		return this.INM_Fecha_Registro;
	}

	public void setINM_Fecha_Registro(Date INM_Fecha_Registro) {
		this.INM_Fecha_Registro = INM_Fecha_Registro;
	}

	public String getINM_Nombre_Inmueble() {
		return this.INM_Nombre_Inmueble;
	}

	public void setINM_Nombre_Inmueble(String INM_Nombre_Inmueble) {
		this.INM_Nombre_Inmueble = INM_Nombre_Inmueble;
	}

}