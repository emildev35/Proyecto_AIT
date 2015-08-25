package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.Motivo_Baja;

@SuppressWarnings("unchecked")
public class MotivobajaImpl implements Dao<Motivo_Baja> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public MotivobajaImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("exec Para_Motivobaja_MAX");
		result += (Short) query.getSingleResult();
		return result;
	}
	@Override
	public List<Motivo_Baja> getall() {
		Query query = em.createNativeQuery("Para_Motivobaja_Q ", Motivo_Baja.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<Motivo_Baja> resultlist = query.getResultList();		
		return resultlist;
	}

	@Override
	public Motivo_Baja getone(long id) {
		return null;
	}

	@Override
	public Motivo_Baja add(Motivo_Baja table) {
		String strQuery = String.format("EXEC Para_Motivobaja_I "
				+ "@MBA_Motivo_Baja=?1, "
				+ "@MBA_Descripcion=?2 ");
		Query query = this.em.createNativeQuery(strQuery,Motivo_Baja.class);
		query.setParameter(1, table.getMBA_Motivo_Baja());
		query.setParameter(2, table.getMBA_Descripcion());
		try{
			Motivo_Baja result = (Motivo_Baja) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public int delete(Motivo_Baja table) {
		return 0;
	}
	public int deletes(int id_motivobaja) {
	Query query = em.createNativeQuery("Para_Motivobaja_D "
			+ "@MBA_Motivo_Baja=?1 ");
	query.setParameter(1, id_motivobaja);
	return (Integer)query.getSingleResult();	
	}
	@Override
	public Motivo_Baja update(Motivo_Baja table) {
		String strQuery = String.format("EXEC Para_Motivobaja_U "
				+ "@MBA_Motivo_Baja=?1, "
				+ "@MBA_Descripcion=?2 ");
		Query query = this.em.createNativeQuery(strQuery,Motivo_Baja.class);
		query.setParameter(1, table.getMBA_Motivo_Baja());
		query.setParameter(2, table.getMBA_Descripcion());
		Motivo_Baja result = (Motivo_Baja) query.getSingleResult();
		return result;
	}

}
