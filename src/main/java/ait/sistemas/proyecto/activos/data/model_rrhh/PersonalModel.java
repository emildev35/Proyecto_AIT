package ait.sistemas.proyecto.activos.data.model_rrhh;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(
		  name="archive-map-p",
		  entities={
		    @EntityResult(
		      entityClass=PersonalModel.class,
		      fields={
		        @FieldResult(name="PER_CI_Empleado", column="PER_CI_Empleado"),
		        @FieldResult(name="PER_Apellido_Materno", column="PER_Apellido_Materno"),
		        @FieldResult(name="PER_Apellido_Paterno", column="PER_Apellido_Paterno"),
		        @FieldResult(name="PER_Dependencia_ID", column="PER_Dependencia_ID"),
		        @FieldResult(name="PER_Dependencia", column="PER_Dependencia"),
		        @FieldResult(name="PER_Fecha_Registro", column="PER_Fecha_Registro"),
		        @FieldResult(name="PER_No_Interno", column="PER_No_Interno"),
		        @FieldResult(name="PER_No_Telefono_Oficina", column="PER_No_Telefono_Oficina"),
		        @FieldResult(name="PER_Nombres", column="PER_Nombres"),
		        @FieldResult(name="PER_Unidad_Organizacional_ID", column="PER_Unidad_Organizacional_ID"),
		        @FieldResult(name="PER_Unidad_Organizacional", column="PER_Unidad_Organizacional")
		
		      }
		    )
		  }
		)
@Entity
public class PersonalModel  {

	@Id
	private String PER_CI_Empleado;

	private String PER_Apellido_Materno;

	private String PER_Apellido_Paterno;

	private short PER_Dependencia_ID;
	private String PER_Dependencia;

	private Date PER_Fecha_Registro;

	private String PER_No_Interno;

	private String PER_No_Telefono_Oficina;

	private String PER_Nombres;

	private short PER_Unidad_Organizacional_ID;
	private String PER_Unidad_Organizacional;

	public String getPER_CI_Empleado() {
		return this.PER_CI_Empleado;
	}

	public void setPER_CI_Empleado(String PER_CI_Empleado) {
		this.PER_CI_Empleado = PER_CI_Empleado;
	}

	public String getPER_Apellido_Materno() {
		return this.PER_Apellido_Materno;
	}

	public void setPER_Apellido_Materno(String PER_Apellido_Materno) {
		this.PER_Apellido_Materno = PER_Apellido_Materno;
	}

	public String getPER_Apellido_Paterno() {
		return this.PER_Apellido_Paterno;
	}

	public void setPER_Apellido_Paterno(String PER_Apellido_Paterno) {
		this.PER_Apellido_Paterno = PER_Apellido_Paterno;
	}

	public short getPER_Dependencia_ID() {
		return this.PER_Dependencia_ID;
	}
	
	public void setPER_Dependencia_ID(short PER_Dependencia_ID) {
		this.PER_Dependencia_ID = PER_Dependencia_ID;
	}
	public String getPER_Dependencia() {
		return PER_Dependencia;
	}
	
	public void setPER_Dependencia(String pER_Dependencia) {
		PER_Dependencia = pER_Dependencia;
	}

	public Date getPER_Fecha_Registro() {
		return this.PER_Fecha_Registro;
	}

	public void setPER_Fecha_Registro(Date PER_Fecha_Registro) {
		this.PER_Fecha_Registro = PER_Fecha_Registro;
	}

	public String getPER_No_Interno() {
		return this.PER_No_Interno;
	}

	public void setPER_No_Interno(String PER_No_Interno) {
		this.PER_No_Interno = PER_No_Interno;
	}

	public String getPER_No_Telefono_Oficina() {
		return this.PER_No_Telefono_Oficina;
	}

	public void setPER_No_Telefono_Oficina(String PER_No_Telefono_Oficina) {
		this.PER_No_Telefono_Oficina = PER_No_Telefono_Oficina;
	}

	public String getPER_Nombres() {
		return this.PER_Nombres;
	}

	public void setPER_Nombres(String PER_Nombres) {
		this.PER_Nombres = PER_Nombres;
	}

	public short getPER_Unidad_Organizacional_ID() {
		return this.PER_Unidad_Organizacional_ID;
	}

	public void setPER_Unidad_Organizacional_ID(short PER_Unidad_Organizacional_ID) {
		this.PER_Unidad_Organizacional_ID = PER_Unidad_Organizacional_ID;
	}
	
	public String getPER_Unidad_Organizacional() {
		return PER_Unidad_Organizacional;
	}
	public void setPER_Unidad_Organizacional(String pER_Unidad_Organizacional) {
		PER_Unidad_Organizacional = pER_Unidad_Organizacional;
	}
	

}
