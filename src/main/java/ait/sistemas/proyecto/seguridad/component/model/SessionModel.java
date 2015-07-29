package ait.sistemas.proyecto.seguridad.component.model;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;


@SqlResultSetMapping(name = "session-usuario", entities = { @EntityResult(entityClass = SessionModel.class, fields = {
	@FieldResult(name = "id", column = "id"),
	@FieldResult(name = "full_name", column = "full_name"),
	@FieldResult(name = "dependecia", column = "dependencia"),
	@FieldResult(name = "id_dependecia", column = "id_dependencia"),
	@FieldResult(name = "unidad", column = "unidad_organizacional")}) })
@Entity
public class SessionModel {

	@Id
	private String id;
	private String full_name;
	private String dependecia;
	private short id_dependecia;
	private String unidad_organizacional;
	
	public SessionModel() {
	}
	
	public SessionModel(String id, String full_name, String dependecia,
			String unidad) {
		this.id = id;
		this.full_name = full_name;
		this.dependecia = dependecia;
		this.unidad_organizacional = unidad;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getDependecia() {
		return dependecia;
	}
	public void setDependecia(String dependecia) {
		this.dependecia = dependecia;
	}
	public String getUnidad() {
		return unidad_organizacional;
	}
	public void setUnidad(String unidad) {
		this.unidad_organizacional = unidad;
	}
	public void setId_dependecia(short id_dependecia) {
		this.id_dependecia = id_dependecia;
	}
	public short getId_dependecia() {
		return id_dependecia;
	}
	
	
}
