package ait.sistemas.proyecto.activos.component.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

/**
 * Movimiento de Activos para asginar un activo a un usuario
 * @author franzemil
 *
 */
@SqlResultSetMapping(name = "cmovimiento", entities = { @EntityResult(entityClass = Movimiento.class, fields = {
@FieldResult(name = "id_cmovimiento", column = "id_cmovimiento"),
	@FieldResult(name = "id_dependencia", column = "id_dependencia"),
	@FieldResult(name = "dependencia", column = "dependencia"),
	@FieldResult(name = "idUnidadOrganizacional", column = "id_unidad_organizacional"),
	@FieldResult(name = "unidadOrganizacional", column = "unidad_organizacional"),
	@FieldResult(name = "ciUsuario", column = "ci_usuario"),
	@FieldResult(name = "id_dependencia_destino", column = "id_dependencia_destino"),
	@FieldResult(name = "dependencia_destino", column = "dependencia_destino"),
	@FieldResult(name = "id_unidad_organizacional_origen", column = "id_unidad_organizacional_origen"),
	@FieldResult(name = "nro_documento", column = "nro_documento"),
	@FieldResult(name = "id_usuario", column = "id_usuario"),
	@FieldResult(name = "tipo_movimiento", column = "tipo_movimiento"),
	@FieldResult(name = "tipo_movimiento_referencia", column = "tipo_movimiento_referencia"),
	@FieldResult(name = "tipo_movimiento_nuevo", column = "tipo_movimiento_nuevo"),
	@FieldResult(name = "nro_documento_referencia", column = "nro_documento_referencia"),
	@FieldResult(name = "fecha_nro_referencia", column = "fecha_nro_referencia"),
	@FieldResult(name = "fecha_registro", column = "fecha_registro"),
	@FieldResult(name = "fecha_movimiento", column = "fecha_movimiento"),
	@FieldResult(name = "detalles", column = "detalles"),
	@FieldResult(name = "observacion", column = "observacion"),
	@FieldResult(name = "solicitante", column = "solicitante"),
	@FieldResult(name = "no_acta", column = "no_acta"),
	@FieldResult(name = "fecha_acta", column = "fecha_acta")
	})})
@Entity
public class Movimiento {
	
	@Id
	private String id_cmovimiento;
	private short id_dependencia;
	private String dependencia;
	private short idUnidadOrganizacional;
	private String unidadOrganizacional;
	private String ciUsuario;
	
	private short id_dependencia_destino;
	private String dependencia_destino;
	
	private short id_unidad_organizacional_origen;
	private long nro_documento;
	private String id_usuario;
	private short tipo_movimiento;
	private short tipo_movimiento_nuevo;
	private String nro_documento_referencia;
	private short tipo_movimiento_referencia;
	private Date fecha_nro_referencia;
	private Timestamp fecha_registro;
	private Timestamp fecha_movimiento;	
	private List<Detalle> detalles;
	private String observacion;
	private String solicitante;
	private long no_acta;
	private Date fecha_acta;
	private short tipo_soporte;
	private long id_subsistema;
	private short id_estado_soporte;
	
	public short getTipo_movimiento() {
		return tipo_movimiento;
	}
	
	public void setTipo_movimiento(short tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}
	
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Movimiento() {
		this.detalles = new ArrayList<Detalle>();
	}


	public short getId_dependencia() {
		return id_dependencia;
	}

	public void setId_dependencia(short id_dependencia) {
		this.id_dependencia = id_dependencia;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public short getId_dependencia_destino() {
		return id_dependencia_destino;
	}

	public void setId_dependencia_destino(short id_dependencia_destino) {
		this.id_dependencia_destino = id_dependencia_destino;
	}
	
	public String getDependencia_destino() {
		return dependencia_destino;
	}

	public void setDependencia_destino(String dependencia_destino) {
		this.dependencia_destino = dependencia_destino;
	}

	public short getId_unidad_organizacional_origen() {
		return id_unidad_organizacional_origen;
	}

	public void setId_unidad_organizacional_origen(short id_unidad_organizacional_origen) {
		this.id_unidad_organizacional_origen = id_unidad_organizacional_origen;
	}

	public long getNro_documento() {
		return nro_documento;
	}

	public void setNro_documento(long nro_documento) {
		this.nro_documento = nro_documento;
	}

	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Timestamp getFecha_movimiento() {
		return fecha_movimiento;
	}

	public void setFecha_movimiento(Timestamp fecha_movimiento) {
		this.fecha_movimiento = fecha_movimiento;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}
	public void addDetalle(Detalle detalle){
		this.detalles.add(detalle);	
	}

	public String getUsuario() {
		return id_usuario;
	}

	public void setUsuario(String usuario) {
		this.id_usuario = usuario;
	}

	public String getNro_documento_referencia() {
		return nro_documento_referencia;
	}

	public void setNro_documento_referencia(String nro_documento_referencia) {
		this.nro_documento_referencia = nro_documento_referencia;
	}

	public Date getFecha_nro_referencia() {
		return fecha_nro_referencia;
	}

	public void setFecha_nro_referencia(Date fecha_nro_referencia) {
		this.fecha_nro_referencia = fecha_nro_referencia;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public long getNo_acta() {
		return no_acta;
	}

	public void setNo_acta(long no_acta) {
		this.no_acta = no_acta;
	}
	public Date getFecha_acta() {
		return fecha_acta;
	}
	public void setFecha_acta(Date fecha_acta) {
		this.fecha_acta = fecha_acta;
	}

	public short getTipo_soporte() {
		return tipo_soporte;
	}

	public void setTipo_soporte(short tipo_soporte) {
		this.tipo_soporte = tipo_soporte;
	}

	public long getId_subsistema() {
		return id_subsistema;
	}

	public void setId_subsistema(long id_subsistema) {
		this.id_subsistema = id_subsistema;
	}

	public short getId_estado_soporte() {
		return id_estado_soporte;
	}

	public void setId_estado_soporte(short id_estado_soporte) {
		this.id_estado_soporte = id_estado_soporte;
	}

	public short getTipo_movimiento_referencia() {
		return tipo_movimiento_referencia;
	}

	public void setTipo_movimiento_referencia(short tipo_movimiento_referencia) {
		this.tipo_movimiento_referencia = tipo_movimiento_referencia;
	}

	public short getTipo_movimiento_nuevo() {
		return tipo_movimiento_nuevo;
	}

	public void setTipo_movimiento_nuevo(short tipo_movimiento_nuevo) {
		this.tipo_movimiento_nuevo = tipo_movimiento_nuevo;
	}

	public short getIdUnidadOrganizacional() {
		return idUnidadOrganizacional;
	}

	public void setIdUnidadOrganizacional(short idUnidadOrganizacional) {
		this.idUnidadOrganizacional = idUnidadOrganizacional;
	}

	public String getUnidadOrganizacional() {
		return unidadOrganizacional;
	}

	public void setUnidadOrganizacional(String unidadOrganizacional) {
		this.unidadOrganizacional = unidadOrganizacional;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCiUsuario() {
		return ciUsuario;
	}

	public void setCiUsuario(String ciUsuario) {
		this.ciUsuario = ciUsuario;
	}
	

	
}
