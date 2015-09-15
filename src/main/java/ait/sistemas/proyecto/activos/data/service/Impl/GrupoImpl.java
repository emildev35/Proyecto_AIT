package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.model.Grupos_Contable;

@SuppressWarnings("unchecked")
public class GrupoImpl implements Dao<Grupos_Contable> {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public GrupoImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	public String getId() {
		String str_id = "EXEC Para_Grupo_Getid ";
		Query query = this.em.createNativeQuery(str_id);
		return (String) query.getSingleResult();
		//return result;
	}
	public List<GruposContablesModel> getalls() {
		this.em.getEntityManagerFactory().getCache().evict(GruposContablesModel.class);
		Query query = em.createNativeQuery("exec Para_Grupo_Q", "archive-map-g").setHint(QueryHints.REFRESH,
				HintValues.TRUE);
		List<GruposContablesModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	public List<GruposContablesModel> getpartida(int id_partida) {
		Query query = em.createNativeQuery("Para_Grupo_Partida " + "@GRC_Partida=?1 ",
				GruposContablesModel.class);
		query.setParameter(1, id_partida);
		List<GruposContablesModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	public Grupos_Contable getone(long id) {
		return null;
	}
	
	public Grupos_Contable add(Grupos_Contable table) {
		String strQuery = String.format("EXEC Para_Grupo_I " + "@GRC_Grupo_Contable=?1, "
				+ "@GRC_Nombre_Grupo_Contable=?2, " + "@GRC_Vida_Util=?3, " + "@GRC_Coeficiente=?4, "
				+ "@GRC_Partida=?5, " + "@GRC_Fecha_Registro=?6");
		Query query = this.em.createNativeQuery(strQuery, Grupos_Contable.class);
		query.setParameter(1, table.getGRC_Grupo_Contable());
		query.setParameter(2, table.getGRC_Nombre_Grupo_Contable());
		query.setParameter(3, table.getGRC_Vida_Util());
		query.setParameter(4, table.getGRC_Coeficiente());
		query.setParameter(5, table.getGRC_Partida());
		query.setParameter(6, table.getGRC_Fecha_Registro());
		try {
			Grupos_Contable result = (Grupos_Contable) query.getSingleResult();
			return result;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public int delete(String id_grupo) {
		Query query = em.createNativeQuery("Para_Grupo_D " + "@GRC_Grupo_Contable=?1 ");
		query.setParameter(1, id_grupo);
		return (Integer) query.getSingleResult();
		
	}
	
	public Grupos_Contable update(Grupos_Contable table) {
		String strQuery = String.format("EXEC Para_Grupo_U " + "@GRC_Grupo_Contable=?1, "
				+ "@GRC_Nombre_Grupo_Contable=?2, " + "@GRC_Vida_Util=?3, " + "@GRC_Coeficiente=?4, "
				+ "@GRC_Partida=?5, " + "@GRC_Fecha_Registro=?6");
		Query query = this.em.createNativeQuery(strQuery, Grupos_Contable.class);
		query.setParameter(1, table.getGRC_Grupo_Contable());
		query.setParameter(2, table.getGRC_Nombre_Grupo_Contable());
		query.setParameter(3, table.getGRC_Vida_Util());
		query.setParameter(4, table.getGRC_Coeficiente());
		query.setParameter(5, table.getGRC_Partida());
		query.setParameter(6, table.getGRC_Fecha_Registro());
		
		Grupos_Contable result = (Grupos_Contable) query.getSingleResult();
		return result;
	}
	
	@Override
	public int delete(Grupos_Contable table) {
		return 0;
	}
	
	@Override
	public List<Grupos_Contable> getall() {
		return null;
	}
	
}
