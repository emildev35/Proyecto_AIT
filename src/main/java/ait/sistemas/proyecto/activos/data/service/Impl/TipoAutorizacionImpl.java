package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.data.model.TipoAutorizacionModel;

@SuppressWarnings("unchecked")
public class TipoAutorizacionImpl {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TipoAutorizacionImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public boolean add(TipoAutorizacionModel tipo_autorizacion) {
		String str_get_by_dependencia = "EXEC Act_Autorizaciones_I " + "@Dependencia_Id=?1, "
				+ "@Tipo_Movimiento=?2," + "@Orden=?3," + "@Nivel_Autorizacion_Id=?4," + "@CI=?5," + "@Fecha_Registro=?6";
		Query query = this.em.createNativeQuery(str_get_by_dependencia);
		query.setParameter(1, tipo_autorizacion.getDependencia_id());
		query.setParameter(2, tipo_autorizacion.getTipo_movimiento_id());
		query.setParameter(3, tipo_autorizacion.getOrden());
		query.setParameter(4, tipo_autorizacion.getNivel_autorizacion_id());
		query.setParameter(5, tipo_autorizacion.getCi());
		query.setParameter(6, tipo_autorizacion.getFecha_registro());
		int result = (Integer) query.getSingleResult();
		return result > 0 ? true : false;
	}
	
	public List<TipoAutorizacionModel> getall() {
		String str_get_by_dependencia = "EXEC Act_getTipoAutorizacion";
		Query query = this.em.createNativeQuery(str_get_by_dependencia, "tipo-autorizacion");
		List<TipoAutorizacionModel> result = query.getResultList();
		return result;
	}
	
	
	public List<TipoAutorizacionModel> getallbyusuario(String id_usuario) {
		String str_get_by_dependencia = "EXEC Act_getTipoAutorizacionByUsuario @Id_Usuario=?1";
		Query query = this.em.createNativeQuery(str_get_by_dependencia, "tipo-autorizacion");
		query.setParameter(1, id_usuario);
		List<TipoAutorizacionModel> result = query.getResultList();
		this.em.close();
		return result;
	}
	
}
