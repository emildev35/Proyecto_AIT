package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model_rrhh.Personal;
import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
@SuppressWarnings("unchecked")
public class PersonalImpl implements Dao<Personal> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public PersonalImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-RecursosHumanos");
		this.em = emf.createEntityManager();
	}

	@Override
	public List<Personal> getall() {
		return null;
	}
	public List<PersonalModel> getalls() {
		this.em.getEntityManagerFactory().getCache().evict(PersonalModel.class);
		Query query = em.createNativeQuery("exec Rrhh_Personal_Q", "archive-map-p").setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<PersonalModel> resultlist = query.getResultList();		
		return resultlist;
	}
	public List<PersonalModel> getPersonal() {
		this.em.getEntityManagerFactory().getCache().evict(PersonalModel.class);
		Query query = em.createNativeQuery("exec Rrhh_PersonalOrdenDep_Q", "archive-map-p").setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<PersonalModel> resultlist = query.getResultList();		
		return resultlist;
	}
	public List<PersonalModel> getbyUnidad(short unidad_organizacional) {
		this.em.getEntityManagerFactory().getCache().evict(PersonalModel.class);
		Query query = em.createNativeQuery("exec Rrhh_PersonaByUnidad_Q @Unidad_Organizacional_Id=?1", "archive-map-p").setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, unidad_organizacional);
		List<PersonalModel> resultlist = query.getResultList();		
		return resultlist;
	}
	public List<PersonalModel> getallreporte(Short id_dependencia) {
		this.em.getEntityManagerFactory().getCache().evict(PersonalModel.class);
		Query query = em.createNativeQuery("exec Rrhh_personal_Dependencia @DEP_Dependencia=?1", "archive-map-p").setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_dependencia);
		List<PersonalModel> resultlist = query.getResultList();		
		return resultlist;
	}
	@Override
	public Personal getone(long id) {
		return null;
	}

	@Override
	public Personal add(Personal table) {
		String strQuery = String.format("EXEC Rrhh_Personal_I "
				+ "@PER_Dependencia=?1, "
				+ "@PER_Unidad_Organizacional=?2, "
				+ "@PER_CI_Empleado=?3, "
				+ "@PER_Apellido_Paterno=?4, "
				+ "@PER_Apellido_Materno=?5, "
				+ "@PER_Nombres=?6, "
				+ "@PER_No_Telefono_Oficina=?7, "
				+ "@PER_No_Interno=?8, "
				+ "@PER_Fecha_Registro=?9");
		Query query = this.em.createNativeQuery(strQuery,Personal.class);
		query.setParameter(1, table.getPER_Dependencia());
		query.setParameter(2, table.getPER_Unidad_Organizacional());
		query.setParameter(3, table.getPER_CI_Empleado());
		query.setParameter(4, table.getPER_Apellido_Paterno());
		query.setParameter(5, table.getPER_Apellido_Materno());
		query.setParameter(6, table.getPER_Nombres());
		query.setParameter(7, table.getPER_No_Telefono_Oficina());
		query.setParameter(8, table.getPER_No_Interno());
		query.setParameter(9, table.getPER_Fecha_Registro());
		try{
			Personal result = (Personal) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public int delete(Personal table) {
		return 0;
	}
	public int deletes(String ci_personal) {
	Query query = em.createNativeQuery("Rrhh_Personal_D "
			+ "@PER_CI_Empleado=?1 ");
	query.setParameter(1, ci_personal);
	return (Integer)query.getSingleResult();	
	}
	@Override
	public Personal update(Personal table) {
		String strQuery = String.format("EXEC Rrhh_Personal_U "
				+ "@PER_Dependencia=?1, "
				+ "@PER_Unidad_Organizacional=?2, "
				+ "@PER_CI_Empleado=?3, "
				+ "@PER_Apellido_Paterno=?4, "
				+ "@PER_Apellido_Materno=?5, "
				+ "@PER_Nombres=?6, "
				+ "@PER_No_Telefono_Oficina=?7, "
				+ "@PER_No_Interno=?8, "
				+ "@PER_Fecha_Registro=?9");
		Query query = this.em.createNativeQuery(strQuery,Personal.class);
		query.setParameter(1, table.getPER_Dependencia());
		query.setParameter(2, table.getPER_Unidad_Organizacional());
		query.setParameter(3, table.getPER_CI_Empleado());
		query.setParameter(4, table.getPER_Apellido_Paterno());
		query.setParameter(5, table.getPER_Apellido_Materno());
		query.setParameter(6, table.getPER_Nombres());
		query.setParameter(7, table.getPER_No_Telefono_Oficina());
		query.setParameter(8, table.getPER_No_Interno());
		query.setParameter(9, table.getPER_Fecha_Registro());
		Personal result = (Personal) query.getSingleResult();
		return result;
	}

	
	
	public Personal getbyCI(String ci) {
		String strQuery = String.format("EXEC Rrrh_GetByCi @Ci=?1");
		Query query = this.em.createNativeQuery(strQuery,Personal.class);
		query.setParameter(1, ci);
		try{
			Personal result = (Personal) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}

}
