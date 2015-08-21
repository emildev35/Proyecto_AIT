package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.jsoup.parser.ParseError;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.CmovimientoDocumento;
import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.component.model.Movimiento;

public class MovimientoImpl {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public MovimientoImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public long getId() {
		String str_id = "EXEC Mvac_Generar_Codigo_Movimiento_Q";
		Query query = this.em.createNativeQuery(str_id);
		long result = (Long) query.getSingleResult();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivoGrid> activos_asinados(short id_dependencia) {
		this.em.getEntityManagerFactory().getCache().evict(ActivoGrid.class);
		Query query = em.createNativeQuery("exec Mvac_ActivoAsignadobyDependencia @ACT_Dependencia=?1 ", "activo-simple")
				.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, (id_dependencia));
		List<ActivoGrid> resultlist = query.getResultList();
		return resultlist;
	}
	
	public int addMovimiento(Movimiento data) {
		this.emf = null;
		this.em = null;
		EntityManagerFactory emfdos = Persistence.createEntityManagerFactory("AIT-Activos");
		EntityManager emdos = emfdos.createEntityManager();
		String str_cabezera = "EXEC Mvac_CMovimiento_I " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
				+ "@Numero_Documento=?3," + "@Fecha_Registro=?4," + "@Fecha_Movimiento=?5," + "@CI_Usuario=?6,"
				+ "@Tipo_Movimiento=?7," + "@Observaciones=?8";
		Query query_cabezera = emdos.createNativeQuery(str_cabezera);
		query_cabezera.setParameter(1, data.getId_dependencia());
		query_cabezera.setParameter(2, data.getId_unidad_organizacional_origen());
		query_cabezera.setParameter(3, data.getNro_documento());
		query_cabezera.setParameter(4, data.getFecha_registro());
		query_cabezera.setParameter(5, data.getFecha_movimiento());
		query_cabezera.setParameter(6, data.getUsuario());
		query_cabezera.setParameter(7, data.getTipo_movimiento());
		query_cabezera.setParameter(8, data.getObservacion());
		int result_cabezera = (Integer) query_cabezera.getSingleResult();
		int result_detalle = 0;
		if (result_cabezera > 0) {
			for (Detalle detalle : data.getDetalles()) {
				
				String str_detalle = "EXEC Mvac_DMovimiento_I " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
						+ "@Numero_Documento=?3," + "@Fecha_Registro=?4," + "@Tipo_Movimiento=?5," + "@Activo_Id=?6,"
						+ "@Observaciones=?7";
				Query query_detalle = emdos.createNativeQuery(str_detalle);
				query_detalle.setParameter(1, detalle.getId_dependencia());
				query_detalle.setParameter(2, detalle.getId_unidad_organizacional_origen());
				query_detalle.setParameter(3, detalle.getNro_documento());
				query_detalle.setParameter(4, detalle.getFecha_registro());
				query_detalle.setParameter(5, detalle.getTipo_movimiento());
				query_detalle.setParameter(6, detalle.getId_activo());
				query_detalle.setParameter(7, detalle.getObservacion());
				result_detalle += (Integer) query_detalle.getSingleResult();
			}
			
			if (result_detalle == data.getDetalles().size()) {
				return 1;
			} else {
				dropmovimiento(data);
				return 0;
			}
			
		} else {
			return 0;
		}
	}
	
	public int addMovimiento_Baja(Movimiento data) {
		
		String str_cabezera = "EXEC Mvac_CMovimientoBaja_I " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
				+ "@Numero_Documento=?3," + "@Fecha_Registro=?4," + "@Fecha_Movimiento=?5," + "@CI_Usuario=?6,"
				+ "@No_Documento_Referencia=?7," + "@Fecha_Documento_Referencia=?8," + "@Tipo_Movimiento=?9,"
				+ "@Observaciones=?10 ";
		Query query_cabezera = this.em.createNativeQuery(str_cabezera);
		query_cabezera.setParameter(1, data.getId_dependencia());
		query_cabezera.setParameter(2, data.getId_unidad_organizacional_origen());
		query_cabezera.setParameter(3, data.getNro_documento());
		query_cabezera.setParameter(4, data.getFecha_registro());
		query_cabezera.setParameter(5, data.getFecha_movimiento());
		query_cabezera.setParameter(6, data.getUsuario());
		query_cabezera.setParameter(7, data.getNro_documento_referencia());
		query_cabezera.setParameter(8, data.getFecha_nro_referencia());
		query_cabezera.setParameter(9, data.getTipo_movimiento());
		query_cabezera.setParameter(10, data.getObservacion());
		int result_cabezera = (Integer) query_cabezera.getSingleResult();
		int result_detalle = 0;
		if (result_cabezera > 0) {
			for (Detalle detalle : data.getDetalles()) {
				
				String str_detalle = "EXEC Mvac_DMovimientoBaja_I " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
						+ "@Numero_Documento=?3," + "@Fecha_Registro=?4," + "@Tipo_Movimiento=?5," + "@Activo_Id=?6,"
						+ "@Motivo_Baja=?7," + "@Observaciones=?8 ";
				Query query_detalle = this.em.createNativeQuery(str_detalle);
				query_detalle.setParameter(1, detalle.getId_dependencia());
				query_detalle.setParameter(2, detalle.getId_unidad_organizacional_origen());
				query_detalle.setParameter(3, detalle.getNro_documento());
				query_detalle.setParameter(4, detalle.getFecha_registro());
				query_detalle.setParameter(5, detalle.getTipo_movimiento());
				query_detalle.setParameter(6, detalle.getId_activo());
				query_detalle.setParameter(7, detalle.getId_motivo_baja());
				query_detalle.setParameter(8, detalle.getObservacion());
				result_detalle += (Integer) query_detalle.getSingleResult();
			}
			
			if (result_detalle == data.getDetalles().size()) {
				return 1;
			} else {
				
				return -1 * dropmovimiento(data);
			}
			
		} else {
			return 0;
		}
	}
	
	public int dropmovimiento(Movimiento data) {
		int result_cabezera;
		for (Detalle detalle : data.getDetalles()) {
			
			String str_detalle = "EXEC Mvac_DMovimiento_D " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
					+ "@Numero_Documento=?3," + "@Tipo_Movimiento=?4," + "@Activo_Id=?4";
			
			Query query_detalle = this.em.createNativeQuery(str_detalle);
			query_detalle.setParameter(1, detalle.getId_dependencia());
			query_detalle.setParameter(2, detalle.getId_unidad_organizacional_origen());
			query_detalle.setParameter(3, detalle.getNro_documento());
			query_detalle.setParameter(4, detalle.getTipo_movimiento());
			query_detalle.setParameter(5, detalle.getId_activo());
			result_cabezera = (Integer) query_detalle.getSingleResult();
		}
		
		String str_cabezera = "EXEC Mvac_CMovimiento_D " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
				+ "@Numero_Documento=?3," + "@Tipo_Movimiento=?4";
		Query query_cabezera = this.em.createNativeQuery(str_cabezera);
		query_cabezera.setParameter(1, data.getId_dependencia());
		query_cabezera.setParameter(2, data.getId_unidad_organizacional_origen());
		query_cabezera.setParameter(3, data.getNro_documento());
		query_cabezera.setParameter(4, data.getTipo_movimiento());
		result_cabezera = (Integer) query_cabezera.getSingleResult();
		return result_cabezera;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimiento> getActivos_Resolucion() {
		// this.em.getEntityManagerFactory().getCache().evict(Movimiento.class);
		Query query = this.em.createNativeQuery("EXEC Mvac_Activos_Resolucion", "cmovimiento");
		List<Movimiento> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Detalle> getDetallesbyMovimiento(long nro_documento, short id_dependencia) {
		String str_detallesbymovimiento = "EXEC Mavc_GetDetallesByDocumento @Id_Dependencia=?1,@Nro_Documento=?2";
		Query query = this.em.createNativeQuery(str_detallesbymovimiento, "detalle-movimiento");
		query.setParameter(1, id_dependencia);
		query.setParameter(2, nro_documento);
		List<Detalle> result = (List<Detalle>) query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Detalle> getDetallesbyCmovimiento(long nro_documento, short id_dependencia) {
		String str_detallesbymovimiento = "EXEC Mavc_DetallesByDocumento @Id_Dependencia=?1,@Nro_Documento=?2";
		Query query = this.em.createNativeQuery(str_detallesbymovimiento, "detalle-movimiento");
		query.setParameter(1, id_dependencia);
		query.setParameter(2, nro_documento);
		List<Detalle> result = (List<Detalle>) query.getResultList();
		return result;
	}
	
	public int update(CmovimientoDocumento table) {
		String strQuery = String.format("EXEC Mvac_Actbaja_I " + "@id_dependencia=?1, " + "@nro_documento_referencia=?2, "
				+ "@nombre_documento=?3, " + "@direccion_documento=?4, " + "@fecha_nro_referencia=?5 ");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, table.getId_dependencia());
		query.setParameter(2, table.getNro_documento_referencia());
		query.setParameter(3, table.getNombre_documento());
		query.setParameter(4, table.getDireccion_documento());
		query.setParameter(5, table.getFecha_nro_referencia());
		return 1;
	}
}
