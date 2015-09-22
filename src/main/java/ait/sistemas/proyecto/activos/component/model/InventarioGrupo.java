package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;


@SqlResultSetMapping(name = "inventario-grupo", entities = { @EntityResult(entityClass = InventarioGrupo.class, fields = {
	@FieldResult(name = "id", column = "id"),
	@FieldResult(name = "dependencia", column = "dependencia"),
	@FieldResult(name = "grupoContable", column = "grupoContable"),
	@FieldResult(name = "cantidad", column = "cantidad"),
	@FieldResult(name = "valorCompra", column = "valorCompra"),
	@FieldResult(name = "valorNeto", column = "valorNeto") }) })
@Entity
public class InventarioGrupo implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String dependencia;
	private String grupoContable;
	private int cantidad;
	private BigDecimal valorCompra;
	private BigDecimal valorNeto;
	
	
	public InventarioGrupo(String dependencia, String grupoContable, int cantidad, BigDecimal valorCompra, BigDecimal valorNeto) {
		this.dependencia = dependencia;
		this.grupoContable = grupoContable;
		this.cantidad = cantidad;
		this.valorCompra = valorCompra;
		this.valorNeto = valorNeto;
	}

	public InventarioGrupo() {
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getGrupoContable() {
		return grupoContable;
	}

	public void setGrupoContable(String grupoContable) {
		this.grupoContable = grupoContable;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getValorNeto() {
		return valorNeto;
	}

	public void setValorNeto(BigDecimal valorNeto) {
		this.valorNeto = valorNeto;
	}
}
