package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;


@SqlResultSetMapping(name = "documento", entities = { @EntityResult(entityClass = Documento.class, fields = {
	@FieldResult(name = "id", column = "DOR_Id_Documento_Respaldo"),
	@FieldResult(name = "nombre", column = "DOR_Nombre_Documento"),
	@FieldResult(name = "direccion", column = "DOR_Ubicacion_Documento")
	}) })
@Entity
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private short id;
	private String nombre;
	private String direccion;
	
	public Documento() {
	}
	
	public Documento(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
