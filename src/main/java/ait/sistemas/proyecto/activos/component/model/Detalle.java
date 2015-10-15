package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

/**
 * Detalle de Movimientos Movimiento, Devolucion
 * @author franzemil
 *
 */
@SqlResultSetMapping(name = "detalle-movimiento", entities = { @EntityResult(entityClass = Detalle.class, fields = {
	@FieldResult(name = "id_detalle", column = "id_detalle"),
	@FieldResult(name = "id_dependencia", column = "id_dependencia"),
	@FieldResult(name = "id_unidad_organizacional", column = "id_unidad_organizacional"),
	@FieldResult(name = "nro_documento", column = "nro_documento"),
	@FieldResult(name = "id_activo", column = "id_activo"),
	@FieldResult(name = "tipo_movimiento", column = "tipo_movimiento"),
	@FieldResult(name = "id_motivo_baja", column = "id_motivo_baja"),
	@FieldResult(name = "nombre_activo", column = "nombre_activo"),
	@FieldResult(name = "motivo_baja", column = "motivo_baja"),
	@FieldResult(name = "observacion", column = "observacion"),
	@FieldResult(name = "fecha_registro", column = "fecha_registro"),
	@FieldResult(name = "nombre_activo", column = "nombre_activo"),
	@FieldResult(name = "motivo_baja", column = "motivo_baja"),
	@FieldResult(name = "nro_seguro", column = "nro_seguro"),
	@FieldResult(name = "serie", column = "serie"),
	@FieldResult(name = "vto_seguro", column = "vto_seguro"),
	@FieldResult(name = "nro_garantia", column = "nro_garantia"),
	@FieldResult(name = "vto_garantia", column = "vto_garantia"),
	@FieldResult(name = "nuevo_valor", column = "nuevo_valor"),
	@FieldResult(name = "nueva_vida_util", column = "nueva_vida_util")
	})})
@Entity
public class Detalle implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private String id_detalle;
	private short id_dependencia;
	private short id_unidad_organizacional;
	private long nro_documento;
	private long id_activo;
	private short tipo_movimiento;
	private short id_motivo_baja;
	private String observacion;
	private Timestamp fecha_registro;
	private String nombre_activo;
	private String motivo_baja;
	private String nro_seguro;
	private Date vto_seguro;
	private String serie;
	private String nro_garantia;
	private Date vto_garantia;
	private BigDecimal nuevo_valor;
	private int nueva_vida_util;
	public Detalle() {
	}
	
	public short getTipo_movimiento() {
		return tipo_movimiento;
	}
	public void setTipo_movimiento(short tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}
	public short getId_dependencia() {
		return id_dependencia;
	}
	public void setId_dependencia(short id_dependencia) {
		this.id_dependencia = id_dependencia;
	}
	public short getId_unidad_organizacional() {
		return id_unidad_organizacional;
	}
	public void setId_unidad_organizacional(short id_unidad_organizacional) {
		this.id_unidad_organizacional= id_unidad_organizacional;
	}

	public long getNro_documento() {
		return nro_documento;
	}
	public void setNro_documento(long nro_documento) {
		this.nro_documento = nro_documento;
	}
	public long getId_activo() {
		return id_activo;
	}
	public void setId_activo(long id_activo) {
		this.id_activo = id_activo;
	}
	public short getId_motivo_baja() {
		return id_motivo_baja;
	}
	public void setId_motivo_baja(short id_motivo_baja) {
		this.id_motivo_baja = id_motivo_baja;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getNombre_activo() {
		return nombre_activo;
	}
	public void setNombre_activo(String nombre_activo) {
		this.nombre_activo = nombre_activo;
	}
	public String getMotivo_baja() {
		return motivo_baja;
	}
	public void setMotivo_baja(String motivo_baja) {
		this.motivo_baja = motivo_baja;
	}
	public String getNro_seguro() {
		return nro_seguro;
	}
	public void setNro_seguro(String nro_seguro) {
		this.nro_seguro = nro_seguro;
	}
	public Date getVto_seguro() {
		return vto_seguro;
	}
	public void setVto_seguro(Date vto_seguro) {
		this.vto_seguro = vto_seguro;
	}
	public String getNro_garantia() {
		return nro_garantia;
	}
	public void setNro_garantia(String nro_garantia) {
		this.nro_garantia = nro_garantia;
	}
	public Date getVto_garantia() {
		return vto_garantia;
	}
	public void setVto_garantia(Date vto_garantia) {
		this.vto_garantia = vto_garantia;
	}
	public BigDecimal getNuevo_valor() {
		return nuevo_valor;
	}
	public void setNuevo_valor(BigDecimal nuevo_valor) {
		this.nuevo_valor = nuevo_valor;
	}
	public int getNueva_vida_util() {
		return nueva_vida_util;
	}
	public void setNueva_vida_util(int nueva_vida_util) {
		this.nueva_vida_util = nueva_vida_util;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	

	
}
