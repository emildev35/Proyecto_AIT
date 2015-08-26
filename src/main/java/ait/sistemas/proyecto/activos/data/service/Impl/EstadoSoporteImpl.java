package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.model.EstadoSoporte;

public class EstadoSoporteImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public EstadoSoporteImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public short generateId() {
		short result = 1;
		Query query = this.em.createNativeQuery("EXEC Para_EstadoSoporte_GetId");
		result += (Short)query.getSingleResult();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<EstadoSoporte> getall(){
		String strQuery = String.format("EXEC Para_EstadoSoporte_Q ");
		Query query = this.em.createNativeQuery(strQuery, "estado-soporte");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		return (List<EstadoSoporte>) query.getResultList();
	}
	
	public int agregar(EstadoSoporte tipo_soporte){
		String strQuery = String.format("EXEC Para_EstadoSoporte_I "
				+ "@Id_EstadoSoporte=?1, "
				+ "@Descripcion=?2, "
				+ "@Fecha_Registro=?3");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, tipo_soporte.getId());
		query.setParameter(2, tipo_soporte.getNombre());
		query.setParameter(4, tipo_soporte.getFecha_registro());
		
		return (Integer) query.getSingleResult();
	}
	
	public int modificar(EstadoSoporte tipo_soporte){
		String strQuery = String.format("EXEC Para_EstadoSoporte_U "
				+ "@Id_EstadoSoporte=?1, "
				+ "@Descripcion=?2, "
				+ "@Fecha_Registro=?3");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, tipo_soporte.getId());
		query.setParameter(2, tipo_soporte.getNombre());
		query.setParameter(3, tipo_soporte.getFecha_registro());
		
		return (Integer) query.getSingleResult();
	}
	
	public int eliminar(short estado_soporte_id){
		String strQuery = String.format("EXEC Para_EstadoSoporte_D "
				+ "@Id_EstadoSoporte=?1 ");
		Query query = this.em.createNativeQuery(strQuery);
		query.setParameter(1, estado_soporte_id);
		return (Integer) query.getSingleResult();
	}
}
