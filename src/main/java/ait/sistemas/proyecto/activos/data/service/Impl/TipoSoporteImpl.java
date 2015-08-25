package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.model.TipoSoporte;

public class TipoSoporteImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TipoSoporteImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("EXEC Para_TipoSoporte_GetId");
		result += (Short)query.getSingleResult();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoSoporte> getall(){
		String strQuery = String.format("EXEC Para_TipoSoporte_Q ");
		Query query = this.em.createNativeQuery(strQuery, "tipo-soporte");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		return (List<TipoSoporte>) query.getResultList();
	}
	
	public int agregar(TipoSoporte tipo_soporte){
		String strQuery = String.format("EXEC Para_TipoSoporte_I "
				+ "@Id_TipoSoporte=?1, "
				+ "@Nombre_TipoSoporte=?2, "
				+ "@Sigla_TipoSoporte=?3, "
				+ "@Fecha_Regitro=?4");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, tipo_soporte.getId());
		query.setParameter(2, tipo_soporte.getNombre());
		query.setParameter(3, tipo_soporte.getSigla());
		query.setParameter(4, tipo_soporte.getFecha_registro());
		
		return (Integer) query.getSingleResult();
	}
	
	public int modificar(TipoSoporte tipo_soporte){
		String strQuery = String.format("EXEC Para_TipoSoporte_U "
				+ "@Id_TipoSoporte=?1, "
				+ "@Nombre_TipoSoporte=?2, "
				+ "@Sigla_TipoSoporte=?3, "
				+ "@Fecha_Regitro=?4");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, tipo_soporte.getId());
		query.setParameter(2, tipo_soporte.getNombre());
		query.setParameter(3, tipo_soporte.getSigla());
		query.setParameter(4, tipo_soporte.getFecha_registro());
		
		return (Integer) query.getSingleResult();
	}
	
	public int eliminar(short tipo_soporte_id){
		String strQuery = String.format("EXEC Para_TipoSoporte_D "
				+ "@Id_TipoSoporte=?1 ");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, tipo_soporte_id);
		return (Integer) query.getSingleResult();
	}
}
