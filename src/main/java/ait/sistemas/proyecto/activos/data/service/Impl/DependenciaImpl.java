package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.ProveedoresModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;

public class DependenciaImpl implements Dao<Dependencia> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public DependenciaImpl() {
		this.emf = Persistence
				.createEntityManagerFactory("AIT-RecursosHumanos");
		this.em = emf.createEntityManager();
	}

	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("exec Rrhh_Dependencia_MAX");
		result += (Short) query.getSingleResult();
		return result;
	}

	@Override
	public List<Dependencia> getall() {
		// String strQuery = String.format("EXEC Rrhh_Dependencia_Q  "
		// + "@DEP_Dependencia=null");
		// Query query = this.em.createNativeQuery(strQuery, Dependencia.class);
		// List<Dependencia> resultlist = query.getResultList();
		Query query = em.createNativeQuery("exec Rrhh_Dependencia_Q",
				Dependencia.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<Dependencia> resultlist = query.getResultList();
		return resultlist;
	}
	public List<Dependencia> getdependencia_ciudad(short padre) {
		this.em.getEntityManagerFactory().getCache().evict(Dependencia.class);
		Query query = em.createNativeQuery("exec Rrhh_Dependencia_Ciudad_Q "
				+ "@DEP_Ciudad=?1", Dependencia.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, padre);
		List<Dependencia> resultlist = query.getResultList();		
		return resultlist;
	}
	@SuppressWarnings("unchecked")
	public List<Ciudade> getallsigla() {
		Query query = em.createNativeQuery("exec Rrhh_Ciudad_Q", Ciudade.class);
		List<Ciudade> resultlist = query.getResultList();
		return resultlist;
	}

	@Override
	public Dependencia getone(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dependencia add(Dependencia table) {
		String strQuery = String.format("EXEC Rrhh_Dependencia_I "
				+ "@DEP_Dependencia=?1, " + "@DEP_Nombre_Dependencia=?2, "
				+ "@DEP_Sigla=?3, " + "@DEP_Ciudad=?4, "
				+ "@DEP_Domicilio=?5, " + "@DEP_Pagina_Web=?6, "
				+ "@DEP_Telefono=?7, " + "@DEP_Fecha_Registro=?8");
		Query query = this.em.createNativeQuery(strQuery, Dependencia.class);
		query.setParameter(1, table.getDEP_Dependencia());
		query.setParameter(2, table.getDEP_Nombre_Dependencia());
		query.setParameter(3, table.getDEP_Sigla());
		query.setParameter(4, table.getDEP_Ciudad());
		query.setParameter(5, table.getDEP_Domicilio());
		query.setParameter(6, table.getDEP_Pagina_Web());
		query.setParameter(7, table.getDEP_Telefono());
		query.setParameter(8, table.getDEP_Fecha_Registro());
		try {
			Dependencia result = (Dependencia) query.getSingleResult();
			return result;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public int delete(Dependencia table) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deletes(int id_dependencia) {
		String strQuery = String.format("EXEC Rrhh_Dependencia_D "
				+ "@DEP_Dependencia=?1");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, (id_dependencia));
		return (Integer)query.getSingleResult();
	}

	@Override
	public Dependencia update(Dependencia table) {
		String strQuery = String.format("EXEC Rrhh_Dependencia_U "
				+ "@DEP_Dependencia=?1, " + "@DEP_Nombre_Dependencia=?2, "
				+ "@DEP_Sigla=?3, " + "@DEP_Ciudad=?4, "
				+ "@DEP_Domicilio=?5, " + "@DEP_Pagina_Web=?6, "
				+ "@DEP_Telefono=?7, " + "@DEP_Fecha_Registro=?8");
		Query query = this.em.createNativeQuery(strQuery, Dependencia.class);
		query.setParameter(1, table.getDEP_Dependencia());
		query.setParameter(2, table.getDEP_Nombre_Dependencia());
		query.setParameter(3, table.getDEP_Sigla());
		query.setParameter(4, table.getDEP_Ciudad());
		query.setParameter(5, table.getDEP_Domicilio());
		query.setParameter(6, table.getDEP_Pagina_Web());
		query.setParameter(7, table.getDEP_Telefono());
		query.setParameter(8, table.getDEP_Fecha_Registro());
		Dependencia result = (Dependencia) query.getSingleResult();
		return result;
	}

}
