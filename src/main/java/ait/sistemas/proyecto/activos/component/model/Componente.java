package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "componente", entities = { @EntityResult(entityClass = Componente.class, fields = {
	@FieldResult(name = "id", column = "COM_Id_Componente"),
	@FieldResult(name = "nombre", column = "COM_Nombre_Componente"),
	@FieldResult(name = "caracteristica", column = "COM_Caracteristica_Componente")
	}) })
@Entity
public class Componente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private short id;
	private String nombre;
	private String caracteristica;
	
	public Componente() {
	}
	
	public Componente(String nombre, String caracteritica) {
		this.nombre = nombre;
		this.caracteristica = caracteritica;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCaracteritica() {
		return caracteristica;
	}
	
	public void setCaracteritica(String caracteritica) {
		this.caracteristica = caracteritica;
	}
	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	
}
