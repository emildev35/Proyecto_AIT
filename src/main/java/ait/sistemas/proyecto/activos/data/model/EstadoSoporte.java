package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;


@SqlResultSetMapping(name = "estado-soporte", entities = { @EntityResult(entityClass = EstadoSoporte.class, fields = {
	@FieldResult(name = "id", column = "ESO_Estado"),
	@FieldResult(name = "nombre", column = "ESO_Descripcion_Estado"),
	@FieldResult(name = "fecha_registro", column = "ESO_Fecha_Registro")})})
@Entity
public class EstadoSoporte implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private short id;
	private String nombre;
	private Date fecha_registro;

	public EstadoSoporte() {
	}

	public EstadoSoporte(short id, String nombre, Date fecha_registro) {
		this.id = id;
		this.nombre = nombre;
		this.fecha_registro = fecha_registro;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
}
