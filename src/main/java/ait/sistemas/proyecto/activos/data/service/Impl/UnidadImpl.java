package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model_rrhh.UnidadesOrganizacionalesModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Unidades_Organizacionale;
public class UnidadImpl implements Dao<Unidades_Organizacionale> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public UnidadImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-RecursosHumanos");
		this.em = emf.createEntityManager();
	}

	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("exec Rrhh_Unidad_MAX");
		result += (Short)query.getSingleResult();
		return result;
	}

	public List<UnidadesOrganizacionalesModel> getalls() {
		this.em.getEntityManagerFactory().getCache().evict(UnidadesOrganizacionalesModel.class);
		Query query = em.createNativeQuery("exec Rrhh_Unidad_Q", "archive-map").setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<UnidadesOrganizacionalesModel> resultlist = query.getResultList();		
		return resultlist;
	}
	public List<Unidades_Organizacionale> getunidad(short id_dependencia) {
		Query query = em.createNativeQuery("Rrhh_Unidad_Dependencia "
				+ "@UNO_Dependencia=?1 ", Unidades_Organizacionale.class);
		query.setParameter(1, id_dependencia);
		List<Unidades_Organizacionale> resultlist = query.getResultList();		
		return resultlist;
	}

	public Unidades_Organizacionale getone(long id) {
		return null;
	}
	
	public Unidades_Organizacionale add(Unidades_Organizacionale table) {
		String strQuery = String.format("EXEC Rrhh_Unidad_I "
				+ "@UNO_Dependencia=?1, "
				+ "@UNO_Unidad_Organizacional=?2, "
				+ "@UNO_Nombre_Unidad_Organizacional=?3, "
				+ "@UNO_Fecha_Registro=?4 ");
		Query query = this.em.createNativeQuery(strQuery, Unidades_Organizacionale.class);
		query.setParameter(1, table.getUNO_Dependencia());
		query.setParameter(2, table.getUNO_Unidad_Organizacional());
		query.setParameter(3, table.getUNO_Nombre_Unidad_Organizacional());
		query.setParameter(4, table.getUNO_Fecha_Registro());
		try{
			Unidades_Organizacionale result = (Unidades_Organizacionale) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}
	
	public int delete(int id_unidad_oraganizacional, int id_dependencia) {
	Query query = em.createNativeQuery("Rrhh_Unidad_D "
			+ "@UNO_Dependencia=?1, "
			+ "@UNO_Unidad_Organizacional=?2 ");
	query.setParameter(1, id_dependencia);
	query.setParameter(2, id_unidad_oraganizacional);
	return (Integer)query.getSingleResult();	
		
	}

	public Unidades_Organizacionale update(Unidades_Organizacionale table) {
		String strQuery = String.format("EXEC Rrhh_Unidad_U "
				+ "@UNO_Dependencia=?1, "
				+ "@UNO_Unidad_Organizacional=?2, "
				+ "@UNO_Nombre_Unidad_Organizacional=?3, "
				+ "@UNO_Fecha_Registro=?4 ");
		Query query = this.em.createNativeQuery(strQuery, Unidades_Organizacionale.class);
		query.setParameter(1, table.getUNO_Dependencia());
		query.setParameter(2, table.getUNO_Unidad_Organizacional());
		query.setParameter(3, table.getUNO_Nombre_Unidad_Organizacional());
		query.setParameter(4, table.getUNO_Fecha_Registro());
	
		Unidades_Organizacionale result = (Unidades_Organizacionale) query.getSingleResult();
		return result;
	}

	@Override
	public int delete(Unidades_Organizacionale table) {
		return 0;
	}

	@Override
	public List<Unidades_Organizacionale> getall() {
		return null;
	}


}

