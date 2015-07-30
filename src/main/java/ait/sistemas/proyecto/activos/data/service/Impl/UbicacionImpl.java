package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model_rrhh.UbicacionesFisicasModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Ubicaciones_Fisica;

@SuppressWarnings("unchecked")
public class UbicacionImpl implements Dao<Ubicaciones_Fisica> {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public UbicacionImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-RecursosHumanos");
		this.em = emf.createEntityManager();
	}
	
	public int generateId(short id_dependencia) {
		int result = 1;
		Query query = this.em.createNativeQuery("exec Rrhh_Ubicacion_MAX @UBF_Dependencia=?1");
		query.setParameter(1, id_dependencia);
		result += (Integer) query.getSingleResult();
		return result;
	}
	
	@Override
	public List<Ubicaciones_Fisica> getall() {
		return null;
	}
	
	public List<UbicacionesFisicasModel> getalls(short id_dependencia) {
		this.em.getEntityManagerFactory().getCache().evict(UbicacionesFisicasModel.class);
		Query query = em.createNativeQuery("exec Rrhh_Ubicacion_Q @UBF_Dependencia=?1", "archive-map-UF")
				.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_dependencia);
		List<UbicacionesFisicasModel> resultlist = query.getResultList();
		return resultlist;
	}
	public List<UbicacionesFisicasModel> getbyInmueble(short id_inmueble) {
		this.em.getEntityManagerFactory().getCache().evict(UbicacionesFisicasModel.class);
		Query query = em.createNativeQuery("exec Rrhh_UbicacionByInmueble_Q @Id_Inmueble=?1", "archive-map-UF")
				.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_inmueble);
		List<UbicacionesFisicasModel> resultlist = query.getResultList();
		return resultlist;
	}
	// public List<Ubicaciones_Fisica> getallreporte(Short id_dependencia) {
	// this.em.getEntityManagerFactory().getCache().evict(PersonalModel.class);
	// Query query =
	// em.createNativeQuery("exec Rrhh_personal_Dependencia @DEP_Dependencia=?1",
	// "archive-map-UF").setHint(QueryHints.REFRESH, HintValues.TRUE);
	// query.setParameter(1, id_dependencia);
	// List<PersonalModel> resultlist = query.getResultList();
	// return resultlist;
	// }
	@Override
	public Ubicaciones_Fisica getone(long id) {
		return null;
	}
	
	@Override
	public Ubicaciones_Fisica add(Ubicaciones_Fisica table) {
		String strQuery = String.format("EXEC Rrhh_Ubicacion_I " + "@UBF_Dependencia=?1, "
				+ "@UBF_Inmueble=?2, " + "@UBF_Ubicacion_Fisica=?3, " + "@UBF_Nombre_Ubicacion_Fisica=?4, "
				+ "@UBF_Fecha_Registro=?5 ");
		Query query = this.em.createNativeQuery(strQuery, Ubicaciones_Fisica.class);
		query.setParameter(1, table.getUBF_Dependencia());
		query.setParameter(2, table.getUBF_Inmueble());
		query.setParameter(3, table.getUBF_Ubicacion_Fisica());
		query.setParameter(4, table.getUBF_Nombre_Ubicacion_Fisica());
		query.setParameter(5, table.getUBF_Fecha_Registro());
		try {
			Ubicaciones_Fisica result = (Ubicaciones_Fisica) query.getSingleResult();
			return result;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@Override
	public int delete(Ubicaciones_Fisica table) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int deletes(short id_dependencia, int id_ubicacion) {
		Query query = em.createNativeQuery("Rrhh_Ubicacion_D " + "@UBF_Dependencia=?1, "
				+ "@UBF_Ubicacion_Fisica=?2");
		query.setParameter(1, id_dependencia);
		query.setParameter(2, id_ubicacion);
		return (Integer) query.getSingleResult();
	}
	
	@Override
	public Ubicaciones_Fisica update(Ubicaciones_Fisica table) {
		String strQuery = String.format("EXEC Rrhh_Ubicacion_U " + "@UBF_Dependencia=?1, "
				+ "@UBF_Inmueble=?2, " + "@UBF_Ubicacion_Fisica=?3, " + "@UBF_Nombre_Ubicacion_Fisica=?4, "
				+ "@UBF_Fecha_Registro=?5 ");
		Query query = this.em.createNativeQuery(strQuery, Ubicaciones_Fisica.class);
		query.setParameter(1, table.getUBF_Dependencia());
		query.setParameter(2, table.getUBF_Inmueble());
		query.setParameter(3, table.getUBF_Ubicacion_Fisica());
		query.setParameter(4, table.getUBF_Nombre_Ubicacion_Fisica());
		query.setParameter(5, table.getUBF_Fecha_Registro());
		Ubicaciones_Fisica result = (Ubicaciones_Fisica) query.getSingleResult();
		return result;
	}
	
}
