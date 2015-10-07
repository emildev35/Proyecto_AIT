package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.Tipos_Activo;
@SuppressWarnings("unchecked")
public class TiposactImpl implements Dao<Tipos_Activo> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public TiposactImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("exec Para_Tiposact_MAX");
		result += (Short)query.getSingleResult();
		return result;
	}
	@Override
	public List<Tipos_Activo> getall() {
		Query query = em.createNativeQuery("Para_Tiposact_Q ", Tipos_Activo.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<Tipos_Activo> resultlist = query.getResultList();		
		return resultlist;
	}

	/**
	 * Retorna un Tipo de Activo por su Codigo
	 * @param idTipoActivo
	 * @return
	 */
	public Tipos_Activo get(short idTipoActivo) {
		Query query = em.createNativeQuery("Para_Tiposact_UQ @TAC_Id_Tipo_Activo=?1", Tipos_Activo.class)
				.setHint(QueryHints.REFRESH, HintValues.TRUE)
				.setParameter(1, idTipoActivo);
		Tipos_Activo resultlist = (Tipos_Activo) query.getSingleResult();		
		return resultlist;
	}
	@Override
	public Tipos_Activo getone(long id) {
		return null;
	}

	@Override
	public Tipos_Activo add(Tipos_Activo table) {
		String strQuery = String.format("EXEC Para_Tiposact_I "
				+ "@TAC_Id_Tipo_Activo=?1, "
				+ "@TAC_Nombre_Tipo_Activo=?2");
		Query query = this.em.createNativeQuery(strQuery,Tipos_Activo.class);
		query.setParameter(1, table.getTAC_Id_Tipo_Activo());
		query.setParameter(2, table.getTAC_Nombre_Tipo_Activo());
		try{
			Tipos_Activo result = (Tipos_Activo) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public int delete(Tipos_Activo table) {
		return 0;
	}
	public int deletes(Short id_tipo_movimiento) {
	Query query = em.createNativeQuery("Para_Tiposact_D "
			+ "@TAC_Id_Tipo_Activo=?1 ");
	query.setParameter(1, id_tipo_movimiento);
	return (Integer)query.getSingleResult();	
	}
	@Override
	public Tipos_Activo update(Tipos_Activo table) {
		String strQuery = String.format("EXEC Para_Tiposact_U "
				+ "@TAC_Id_Tipo_Activo=?1, "
				+ "@TAC_Nombre_Tipo_Activo=?2 ");
		Query query = this.em.createNativeQuery(strQuery,Tipos_Activo.class);
		query.setParameter(1, table.getTAC_Id_Tipo_Activo());
		query.setParameter(2, table.getTAC_Nombre_Tipo_Activo());
		Tipos_Activo result = (Tipos_Activo) query.getSingleResult();
		return result;
	}

}
