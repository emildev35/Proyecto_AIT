package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;

public class CiudadImpl implements Dao<Ciudade> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public CiudadImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-RecursosHumanos");
		this.em = emf.createEntityManager();
	}

	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("exec Rrhh_Ciudad_MAX");
		result += (Short)query.getSingleResult();
		return result;
	}

	@Override
	public List<Ciudade> getall() {
		Query query = em.createNativeQuery("Rrhh_Ciudad_Q ", Ciudade.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<Ciudade> resultlist = query.getResultList();		
		return resultlist;
	}

	@Override
	public Ciudade getone(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ciudade add(Ciudade table) {
		String strQuery = String.format("EXEC Rrhh_Ciudad_I "
				+ "@CIU_Ciudad=?1, "
				+ "@CIU_Nombre_Ciudad=?2, "
				+ "@CIU_Sigla=?3, "
				+ "@CIU_Fecha_Registro=?4");
		Query query = this.em.createNativeQuery(strQuery,Ciudade.class);
		query.setParameter(1, table.getCIU_Ciudad());
		query.setParameter(2, table.getCIU_Nombre_Ciudad());
		query.setParameter(3, table.getCIU_Sigla());
		query.setParameter(4, table.getCIU_Fecha_Registro());
		try{
			Ciudade result = (Ciudade) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public int delete(Ciudade table) {
		// TODO Auto-generated method stub
		return 0;
	}
	public int deletes(int id_ciudad) {
		String strQuery = String.format("EXEC Rrhh_Ciudad_D "
				+ "@CIU_Ciudad=?1");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, (id_ciudad));
		return (Integer)query.getSingleResult();
	}
	@Override
	public Ciudade update(Ciudade table) {
		String strQuery = String.format("EXEC Rrhh_Ciudad_U "
				+ "@CIU_Ciudad=?1, " 
				+ "@CIU_Nombre_Ciudad=?2, "
				+ "@CIU_Sigla=?3, "
				+ "@CIU_Fecha_Registro=?4");
		Query query = this.em.createNativeQuery(strQuery, Ciudade.class);
		query.setParameter(1, table.getCIU_Ciudad());
		query.setParameter(2, table.getCIU_Nombre_Ciudad());
		query.setParameter(3, table.getCIU_Sigla());
		query.setParameter(4, table.getCIU_Fecha_Registro());
		Ciudade result = (Ciudade) query.getSingleResult();
		return result;
	}

}
