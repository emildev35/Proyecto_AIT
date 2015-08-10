package ait.sistemas.proyecto.activos.component.model;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "activo-simple", entities = { @EntityResult(entityClass = ActivoGrid.class, fields = {
		@FieldResult(name = "id_activo", column = "codigo_activo"),
		@FieldResult(name = "nombre", column = "nombre_activo") }) })
@Entity
public class ActivoGrid {
	@Id
	private long id_activo;
	
	private String nombre;
	
	public ActivoGrid() {
		// TODO Auto-generated constructor stub
	}
	
	public ActivoGrid(long id_activo, String nombre) {
		this.id_activo = id_activo;
		this.nombre = nombre;
	}
	
	public long getId_activo() {
		return id_activo;
	}
	
	public void setId_activo(long id_activo) {
		this.id_activo = id_activo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
