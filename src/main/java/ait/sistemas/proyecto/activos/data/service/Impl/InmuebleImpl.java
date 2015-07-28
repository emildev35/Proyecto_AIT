package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model_rrhh.Inmueble;
@SuppressWarnings("unchecked")
public class InmuebleImpl implements Dao<Inmueble> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public InmuebleImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-RecursosHumanos");
		this.em = emf.createEntityManager();
	}

	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("exec Rrhh_Inmueble_MAX");
		result += (Short)query.getSingleResult();
		return result;
	}

	@Override
	public List<Inmueble> getall() {
		Query query = em.createNativeQuery("Rrhh_Inmueble_Q ", Inmueble.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<Inmueble> resultlist = query.getResultList();		
		return resultlist;
	}

	@Override
	public Inmueble getone(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inmueble add(Inmueble table) {
		String strQuery = String.format("EXEC Rrhh_Inmueble_I "
				+ "@INM_Inmueble=?1, "
				+ "@INM_Nombre_Inmueble=?2, "
				+ "@INM_Ciudad=?3, "
				+ "@INM_Domicilio_Inmueble=?4, "
				+ "@INM_Fecha_Registro=?5 ");
		Query query = this.em.createNativeQuery(strQuery,Inmueble.class);
		query.setParameter(1, table.getINM_Inmueble());
		query.setParameter(2, table.getINM_Nombre_Inmueble());
		query.setParameter(3, table.getINM_Ciudad());
		query.setParameter(4, table.getINM_Domicilio_Inmueble());
		query.setParameter(5, table.getINM_Fecha_Registro());
		try{
			Inmueble result = (Inmueble) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public int delete(Inmueble table) {
		// TODO Auto-generated method stub
		return 0;
	}
	public int deletes(int id_inmueble) {
		String strQuery = String.format("EXEC Rrhh_Inmueble_D "
				+ "@INM_Inmueble=?1");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, (id_inmueble));
		return (Integer)query.getSingleResult();
	}
	@Override
	public Inmueble update(Inmueble table) {
		String strQuery = String.format("EXEC Rrhh_Inmueble_U "
				+ "@INM_Inmueble=?1, "
				+ "@INM_Nombre_Inmueble=?2, "
				+ "@INM_Ciudad=?3, "
				+ "@INM_Domicilio_Inmueble=?4, "
				+ "@INM_Fecha_Registro=?5 ");
		Query query = this.em.createNativeQuery(strQuery,Inmueble.class);
		query.setParameter(1, table.getINM_Inmueble());
		query.setParameter(2, table.getINM_Nombre_Inmueble());
		query.setParameter(3, table.getINM_Ciudad());
		query.setParameter(4, table.getINM_Domicilio_Inmueble());
		query.setParameter(5, table.getINM_Fecha_Registro());
		Inmueble result = (Inmueble) query.getSingleResult();
		return result;
	}

}
