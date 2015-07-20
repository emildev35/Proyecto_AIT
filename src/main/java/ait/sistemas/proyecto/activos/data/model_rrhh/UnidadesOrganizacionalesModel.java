package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(
		  name="archive-map",
		  entities={
		    @EntityResult(
		      entityClass=UnidadesOrganizacionalesModel.class,
		      fields={
		        @FieldResult(name="UNO_Dependencia", column="UNO_Dependencia"),
		        @FieldResult(name="UNO_Unidad_Organizacional", column="UNO_Unidad_Organizacional"),
		        @FieldResult(name="UNO_Nombre_Unidad_Organizacional", column="UNO_Nombre_Unidad_Organizacional"),
		        @FieldResult(name="UNO_Fecha_Registro", column="UNO_Fecha_Registro"),
		        @FieldResult(name="UNO_Dependencia_ID", column="UNO_Dependencia_ID")
		
		      }
		    )
		  }
		)
@Entity
public class UnidadesOrganizacionalesModel {

	private String UNO_Dependencia;
	private short UNO_Dependencia_ID;
	@Id
	private short UNO_Unidad_Organizacional;
	private String UNO_Nombre_Unidad_Organizacional;
	private Date UNO_Fecha_Registro;

	
	
	
	public short getUNO_Dependencia_ID() {
		return UNO_Dependencia_ID;
	}

	public void setUNO_Dependencia_ID(short uNO_Dependencia_ID) {
		UNO_Dependencia_ID = uNO_Dependencia_ID;
	}

	public UnidadesOrganizacionalesModel() {
	}


	public Date getUNO_Fecha_Registro() {
		return this.UNO_Fecha_Registro;
	}

	public void setUNO_Fecha_Registro(Date UNO_Fecha_Registro) {
		this.UNO_Fecha_Registro = UNO_Fecha_Registro;
	}

	public String getUNO_Nombre_Unidad_Organizacional() {
		return this.UNO_Nombre_Unidad_Organizacional;
	}

	public void setUNO_Nombre_Unidad_Organizacional(String UNO_Nombre_Unidad_Organizacional) {
		this.UNO_Nombre_Unidad_Organizacional = UNO_Nombre_Unidad_Organizacional;
	}

	public short getUNO_Unidad_Organizacional() {
		return this.UNO_Unidad_Organizacional;
	}
	public void setUNO_Unidad_Organizacional(short UNO_Unidad_Organizacional) {
		this.UNO_Unidad_Organizacional = UNO_Unidad_Organizacional;
	}
	public String getUNO_Dependencia() {
		return this.UNO_Dependencia;
	}
	public void setUNO_Dependencia(String UNO_Dependencia) {
		this.UNO_Dependencia = UNO_Dependencia;
	}
}