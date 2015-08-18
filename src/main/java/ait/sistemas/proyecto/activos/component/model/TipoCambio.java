package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "tipo-cambio", entities = { @EntityResult(entityClass = TipoCambio.class, fields = {
	@FieldResult(name = "moneda", column = "c_moneda"),
	@FieldResult(name = "tipo_cambio", column = "n_tipocambio")})})
@Entity
public class TipoCambio implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String moneda;
	private BigDecimal tipo_cambio;
	
	public TipoCambio() {
	}


	public TipoCambio(String moneda, BigDecimal tipo_cambio) {
		this.moneda = moneda;
		this.tipo_cambio = tipo_cambio;
	}



	public String getMoneda() {
		return moneda;
	}


	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}


	public BigDecimal getTipo_cambio() {
		return tipo_cambio;
	}


	public void setTipo_cambio(BigDecimal tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}



	
	
	
	
}
