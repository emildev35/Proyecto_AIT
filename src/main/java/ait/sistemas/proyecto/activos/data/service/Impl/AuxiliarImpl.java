package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.Auxiliares_Contable;
public class AuxiliarImpl implements Dao<Auxiliares_Contable> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public AuxiliarImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}

	public List<AuxiliaresContablesModel> getalls() {
		this.em.getEntityManagerFactory().getCache().evict(AuxiliaresContablesModel.class);
		Query query = em.createNativeQuery("exec Para_Auxiliar_Q", "archive-map-a").setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<AuxiliaresContablesModel> resultlist = query.getResultList();		
		return resultlist;
	}
	public List<AuxiliaresContablesModel> getreporte(String id_grupo) {
		Query query = em.createNativeQuery("Para_Auxiliar_Grupo "
				+ "@AUC_Grupo_Contable=?1 ", AuxiliaresContablesModel.class);
		query.setParameter(1, id_grupo);
		List<AuxiliaresContablesModel> resultlist = query.getResultList();		
		return resultlist;
	}

	public Auxiliares_Contable getone(long id) {
		return null;
	}
	
	public Auxiliares_Contable add(Auxiliares_Contable table) {
		String strQuery = String.format("EXEC Para_Auxiliar_I "
				+ "@AUC_Grupo_Contable=?1, "
				+ "@AUC_Auxiliar_Contable=?2, "
				+ "@AUC_Nombre_Auxiliar_Contable=?3, "
				+ "@AUC_Fecha_Registro=?4 ");
		Query query = this.em.createNativeQuery(strQuery, Auxiliares_Contable.class);
		query.setParameter(1, table.getAUC_Grupo_Contable());
		query.setParameter(2, table.getAUC_Auxiliar_Contable());
		query.setParameter(3, table.getAUC_Nombre_Auxiliar_Contable());
		query.setParameter(4, table.getAUC_Fecha_Registro());
		try{
			Auxiliares_Contable result = (Auxiliares_Contable) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}
	
	public int delete( String id_grupo, String id_auxiliar) {
	Query query = em.createNativeQuery("Para_Auxiliar_D "
			+ "@AUC_Grupo_Contable=?1,"
			+ "@AUC_Auxiliar_Contable=?2");
	query.setParameter(1, id_grupo);
	query.setParameter(2, id_auxiliar);
	return (Integer)query.getSingleResult();	
		
	}

	public Auxiliares_Contable update(Auxiliares_Contable table) {
		String strQuery = String.format("EXEC Para_Auxiliar_U "
				+ "@AUC_Grupo_Contable=?1, "
				+ "@AUC_Auxiliar_Contable=?2, "
				+ "@AUC_Nombre_Auxiliar_Contable=?3, "
				+ "@AUC_Fecha_Registro=?4 ");
		Query query = this.em.createNativeQuery(strQuery, Auxiliares_Contable.class);
		query.setParameter(1, table.getAUC_Grupo_Contable());
		query.setParameter(2, table.getAUC_Auxiliar_Contable());
		query.setParameter(3, table.getAUC_Nombre_Auxiliar_Contable());
		query.setParameter(4, table.getAUC_Fecha_Registro());
	
		Auxiliares_Contable result = (Auxiliares_Contable) query.getSingleResult();
		return result;
	}

	@Override
	public int delete(Auxiliares_Contable table) {
		return 0;
	}

	@Override
	public List<Auxiliares_Contable> getall() {
		return null;
	}


}

