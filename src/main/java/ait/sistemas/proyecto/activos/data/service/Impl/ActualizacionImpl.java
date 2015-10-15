package ait.sistemas.proyecto.activos.data.service.Impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.component.model.Actualizacion;
import ait.sistemas.proyecto.activos.data.ConnecctionActivos;
import ait.sistemas.proyecto.activos.data.model.ActivosModel;

/**
 * Clase de Implementacion de Activos
 * 
 * @author franzemil
 *
 */
public class ActualizacionImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ActualizacionImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public int add_Actualizacion(Actualizacion table) {
		ConnecctionActivos conn = new ConnecctionActivos();
		try {
			return conn.callproc("EXEC Reva_Actualizacion_I " + "@Fecha_Actual='" + new SimpleDateFormat("yyyy-MM-dd").format(table.getFecha()) +"T00:00:00'" );
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
			return 0;
		}
		
//		String strQuery = String.format("EXEC Reva_Actualizacion_I " + "@Fecha_Actual=?1");
//		Query query = this.em.createNativeQuery(strQuery);
//		
//		query.setParameter(1, new SimpleDateFormat("yyyy-MM-dd").format(table.getFecha()) + "T00:00:00");
//		
//		try {
//			int result = (Integer) query.getSingleResult();
//			return result;
//		} catch (Exception e) {
//			return 0;
//		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getActualizacion(short id_dependencia, String fecha) {
		Query query = em.createNativeQuery("Reva_Actualizacion_Q " + "@ACT_Dependencia=?1 , @Fecha_Reporte=?2",
				ActivosModel.class);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_dependencia);
		query.setParameter(2, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	/**
	 * Retorna la Lista de Resumen de Activos Actualizados organizados y
	 * ordenados por la Dependencia
	 * 
	 * @param id_dependencia
	 * @param fechaElaboracion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getResumenActualizacion(short id_dependencia, String fechaElaboracion) {
		Query query = em.createNativeQuery("Reva_ResumenAct_Q " + "@ACT_Dependencia=?1 , @Fecha_Reporte=?2", ActivosModel.class);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_dependencia);
		query.setParameter(2, fechaElaboracion);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	/**
	 * Retorna la Lista General de Resumen de Activos Actualizados
	 * 
	 * @param fechaElaboracion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getResumenGeneralActualizacion(String fechaElaboracion) {
		Query query = em.createNativeQuery("Reva_ResumenActG_Q " + "@Fecha_Reporte=?1", ActivosModel.class);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, fechaElaboracion);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
}