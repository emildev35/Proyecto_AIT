package ait.sistemas.proyecto.activos.component.model;

import java.sql.Timestamp;

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
@SqlResultSetMapping(name = "grid_cmovimiento", entities = { @EntityResult(entityClass = SolicitudGrid.class, fields = {
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
public class SolicitudGrid {
	
	@Id
	private String id_cmovimiento;
	
	private long nro_documento;
	
	private Timestamp fecha_registro;

	private String solicitante;

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

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
	
	
}
