package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.Fuentes_Financiamiento;

@SuppressWarnings("unchecked")
public class FuenteImpl implements Dao<Fuentes_Financiamiento> {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public FuenteImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("exec Para_Fuente_MAX");
		result += (Integer) query.getSingleResult();
		return result;
	}
	
	@Override
	public List<Fuentes_Financiamiento> getall() {
		Query query = em.createNativeQuery("Para_Fuente_Q ", Fuentes_Financiamiento.class).setHint(
				QueryHints.REFRESH, HintValues.TRUE);
		List<Fuentes_Financiamiento> resultlist = query.getResultList();
		return resultlist;
	}
	
	@Override
	public Fuentes_Financiamiento getone(long id) {
		return null;
	}
	
	@Override
	public Fuentes_Financiamiento add(Fuentes_Financiamiento table) {
		String strQuery = String.format("EXEC Para_Fuente_I " + "@FFI_Fuente_Financiamiento=?1, "
				+ "@FFI_Nombre_Fuente_Financiamiento=?2, " + "@FFI_Fecha_Registro=?3 ");
		Query query = this.em.createNativeQuery(strQuery, Fuentes_Financiamiento.class);
		query.setParameter(1, table.getFFI_Fuente_Financiamiento());
		query.setParameter(2, table.getFFI_Nombre_Fuente_Financiamiento());
		query.setParameter(3, table.getFFI_Fuente_Financiamiento());
		try {
			Fuentes_Financiamiento result = (Fuentes_Financiamiento) query.getSingleResult();
			return result;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	@Override
	public int delete(Fuentes_Financiamiento table) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int deletes(int id_fuente) {
		Query query = em.createNativeQuery("Para_Fuente_D " + "@FFI_Fuente_Financiamiento=?1 ");
		query.setParameter(1, id_fuente);
		return (Integer) query.getSingleResult();
	}
	
	@Override
	public Fuentes_Financiamiento update(Fuentes_Financiamiento table) {
		String strQuery = String.format("EXEC Para_Fuente_U " + "@FFI_Fuente_Financiamiento=?1, "
				+ "@FFI_Nombre_Fuente_Financiamiento=?2, " + "@FFI_Fecha_Registro=?3 ");
		Query query = this.em.createNativeQuery(strQuery, Fuentes_Financiamiento.class);
		query.setParameter(1, table.getFFI_Fuente_Financiamiento());
		query.setParameter(2, table.getFFI_Nombre_Fuente_Financiamiento());
		query.setParameter(3, table.getFFI_Fuente_Financiamiento());
		Fuentes_Financiamiento result = (Fuentes_Financiamiento) query.getSingleResult();
		return result;
	}
	
}
