package ait.sistemas.proyecto.seguridad.data.service.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.seguridad.data.dao.Dao;
import ait.sistemas.proyecto.seguridad.data.model.Perfil;

public class PerfilImpl implements Dao<Perfil> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public PerfilImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Seguridad");
		this.em = emf.createEntityManager();
	}

	public int generateId() {
		int result = 1;
		Query query = this.em
				.createQuery("select max(p.PRF_Id_Perfil) from Perfil p");
		result += (int) query.getSingleResult();
		return result;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> getall() {
		String strQuery = String.format("EXEC Usua_Perfil_Q  "
				+ "@PRF_Id_Perfil=null");
		Query query = this.em.createNativeQuery(strQuery, Perfil.class);
		List<Perfil> resultlist = query.getResultList();
		return resultlist;
	}

	@Override
	public Perfil getone(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Perfil add(Perfil table) {
		String strQuery = String.format("EXEC Usua_Perfil_I "
				+ "@PRF_Id_perfil=?1, " + "@PRF_Nombre_Perfil=?2, "
				+ "@PRF_Fecha_Registro=?3");
		Query query = this.em.createNativeQuery(strQuery, Perfil.class);
		query.setParameter(1, table.getPRF_Id_Perfil());
		query.setParameter(2, table.getPRF_Nombre_Perfil());
		query.setParameter(3, table.getPRF_Fecha_Registro());
		try {
			Perfil result = (Perfil) query.getSingleResult();
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public int delete(int id_perfil) {
		String strQuery = String.format("EXEC Usua_Perfil_D "
				+ "@PRF_Id_perfil=?1 ");
		Query query = this.em.createNativeQuery(strQuery, Perfil.class);
		query.setParameter(1, id_perfil);
		int result = query.executeUpdate();
		return result;

	}
	
	@Override
	public Perfil update(Perfil table) {
		String strQuery = String.format("EXEC Usua_Perfil_U "
				+ "@PRF_Id_perfil=?1, " + "@PRF_Nombre_Perfil=?2, "
				+ "@PRF_Fecha_Registro=?3");
		Query query = this.em.createNativeQuery(strQuery, Perfil.class);
		query.setParameter(1, table.getPRF_Id_Perfil());
		query.setParameter(2, table.getPRF_Nombre_Perfil());
		query.setParameter(3, table.getPRF_Fecha_Registro());
		Perfil result = (Perfil) query.getSingleResult();
		return result;
	}

	@Override
	public int delete(Perfil table) {
		return 0;
	}
	/**
	 * Este Método se encarga de realizar el registro en la base de datos
	 * @param id_perfil		Long - Identifcador del Perfil
	 * @param permisos		List<Long> - Lista de lo Identificadores
	 * @return
	 */
	public int otortgarPermisos(int id_perfil, List<Long> permisos){
		int result = 0;
		for (Long menu_id : permisos) {
			String strQuery = String.format("EXEC Estr_OpcionxPerfil_I "
					+ "@PRF_Id_perfil=?1, "
					+ "@AME_Identificador=?2, "
					+ "@Fecha_Registro=?3");
			Query query = this.em.createNativeQuery(strQuery, Perfil.class);
			query.setParameter(1, id_perfil);
			query.setParameter(1, menu_id);
			query.setParameter(1, new java.sql.Date(new Date().getTime()));
			result = (Integer)query.getSingleResult();
		}
		return result;
	}

}
