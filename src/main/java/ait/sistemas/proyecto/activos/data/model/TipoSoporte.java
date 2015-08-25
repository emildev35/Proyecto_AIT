package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;


@SqlResultSetMapping(name = "tipo-soporte", entities = { @EntityResult(entityClass = TipoSoporte.class, fields = {
	@FieldResult(name = "id", column = "TSO_Tipo_Soporte"),
	@FieldResult(name = "nombre", column = "TSO_Nombre_Tipo_Soporte"),
	@FieldResult(name = "sigla", column = "TSO_Sigla"),
	@FieldResult(name = "fecha_registro", column = "TSO_Fecha_Registro")})})
@Entity
public class TipoSoporte implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private short id;
	private String nombre;
	private String sigla;
	private Date fecha_registro;
	
	public TipoSoporte() {
	}
	

	public TipoSoporte(short id, String nombre, String sigla, Date fecha_registro) {
		this.id = id;
		this.nombre = nombre;
		this.sigla = sigla;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
}
