package ait.sistemas.proyecto.seguridad.component.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;


@SqlResultSetMapping(name = "usuario-grid", entities = { @EntityResult(entityClass = UsuarioGridModel.class, fields = {
	@FieldResult(name = "Id", column = "USU_Id_Usuario"),
	@FieldResult(name = "CI", column = "USU_CI_Usuario"),
	@FieldResult(name = "FechaAlta", column = "USU_Fecha_Alta"),
	@FieldResult(name = "FullName", column = "USU_Nombre_Completo")}) })
@Entity
public class UsuarioGridModel {

	@Id
	private String Id;
	private String CI;
	private String FullName;
	private Date FechaAlta;
	
	public UsuarioGridModel() {
	
	}
	
	public UsuarioGridModel(String id, String cI, String fullName) {
		super();
		Id = id;
		CI = cI;
		FullName = fullName;
	}

	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getCI() {
		return CI;
	}
	public void setCI(String cI) {
		CI = cI;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public Date getFechaAlta() {
		return FechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		FechaAlta = fechaAlta;
	}
	
}
