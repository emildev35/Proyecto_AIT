package ait.sistemas.proyecto.activos.component.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "actualizacion-depreciacion", entities = { @EntityResult(entityClass = Actualizacion.class, fields = {
	@FieldResult(name = "CA", column = "CA"),
	@FieldResult(name = "DA", column = "DA"),
	@FieldResult(name = "Valor_Neto", column = "Valor_Neto"),
	@FieldResult(name = "T", column = "T"),
	@FieldResult(name = "ufvf", column = "ufvf"),
	@FieldResult(name = "fecha", column = "fecha"),
	@FieldResult(name = "ufvi", column = "ufvi"),
	@FieldResult(name = "fecha_ultima_depreciacion", column = "fecha_ultima_depreciacion")
	}) })
@Entity
public class Actualizacion {
	

private long CA;

private long DA;

private long Valor_Neto;

private Date T;

private double ufvf;

private Date fecha;

private double ufvi;

@Id
private Date fecha_ultima_depreciacion;

public long getCA() {
	return CA;
}

public void setCA(long cA) {
	CA = cA;
}

public long getDA() {
	return DA;
}

public void setDA(long dA) {
	DA = dA;
}

public long getValor_Neto() {
	return Valor_Neto;
}

public void setValor_Neto(long valor_Neto) {
	Valor_Neto = valor_Neto;
}

public Date getT() {
	return T;
}

public void setT(Date t) {
	T = t;
}

public double getUfvf() {
	return ufvf;
}

public void setUfvf(double ufvf) {
	this.ufvf = ufvf;
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public double getUfvi() {
	return ufvi;
}

public void setUfvi(double ufvi) {
	this.ufvi = ufvi;
}

public Date getFecha_ultima_depreciacion() {
	return fecha_ultima_depreciacion;
}

public void setFecha_ultima_depreciacion(Date fecha_ultima_depreciacion) {
	this.fecha_ultima_depreciacion = fecha_ultima_depreciacion;
}

}