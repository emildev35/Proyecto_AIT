package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "cierre-gestion", entities = { @EntityResult(entityClass = Cierre_Gestion.class, fields = {
	@FieldResult(name = "CGE_Fecha_Cierre_Gestion", column = "CGE_Fecha_Cierre_Gestion"),
	@FieldResult(name = "CGE_Tipo_Cambio_UFV", column = "CGE_Tipo_Cambio_UFV"),
	@FieldResult(name = "CGE_Fecha", column = "CGE_Fecha"),
	@FieldResult(name = "CGE_Fecha_Registro", column = "CGE_Fecha_Registro")

}) })
@Entity
public class Cierre_Gestion implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Date CGE_Fecha_Cierre_Gestion;

	private BigDecimal CGE_Tipo_Cambio_UFV;

	private Date CGE_Fecha_Registro;
	
	private Date CGE_Fecha;


	public Cierre_Gestion() {
	}


	public Date getCGE_Fecha_Cierre_Gestion() {
		return CGE_Fecha_Cierre_Gestion;
	}


	public void setCGE_Fecha_Cierre_Gestion(Date cGE_Fecha_Cierre_Gestion) {
		CGE_Fecha_Cierre_Gestion = cGE_Fecha_Cierre_Gestion;
	}


	public BigDecimal getCGE_Tipo_Cambio_UFV() {
		return CGE_Tipo_Cambio_UFV;
	}


	public void setCGE_Tipo_Cambio_UFV(BigDecimal cGE_Tipo_Cambio_UFV) {
		CGE_Tipo_Cambio_UFV = cGE_Tipo_Cambio_UFV;
	}


	public Date getCGE_Fecha_Registro() {
		return CGE_Fecha_Registro;
	}


	public void setCGE_Fecha_Registro(Date cGE_Fecha_Registro) {
		CGE_Fecha_Registro = cGE_Fecha_Registro;
	}
	public Date getCGE_Fecha() {
		return CGE_Fecha;
	}
	public void setCGE_Fecha(Date cGE_Fecha) {
		CGE_Fecha = cGE_Fecha;
	}
}
