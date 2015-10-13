package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;
import ait.sistemas.proyecto.activos.data.model.Autorizacion;

public class AutorizacionImpl {

	private EntityManagerFactory emf;
	private EntityManager em;

	public AutorizacionImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	@SuppressWarnings("unchecked")
	public List<DocumentoPendiente> getDocumentosPendientes(String id_usuario){
		String str_query = "EXEC Mvac_GetDocumentoSinAut @Id_Usuario=?1";
		Query query = this.em.createNativeQuery(str_query, "documento-pendiente");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_usuario);
		List<DocumentoPendiente> result = query.getResultList();
		return result;
	}
	public int add(Autorizacion autorizacion) {
		String str_query = "EXEC Mvac_Autorizacion_I "
				+ "@Id_Dependencia=?1,"
				+ "@Id_Unidad_Organizacional=?2,"
				+ "@Id_Tipo_Movimiento=?3,"
				+ "@Orden_Autorizacion=?4,"
				+ "@Nro_Documento=?5,"
				+ "@Id_Usuario=?6,"
				+ "@CI=?7,"
				+ "@PIN=?8,"
				+ "@Fecha_Autorizacion=?9,"
				+ "@Fecha_Rechazo=?10,"
				+ "@Fecha_Registro=?11";
		Query query = this.em.createNativeQuery(str_query);
		query.setParameter(1, autorizacion.getAUT_Dependencia());
		query.setParameter(2, autorizacion.getAUT_Unidad_Organizacional());
		query.setParameter(3, autorizacion.getAUT_Tipo_Movimiento());
		query.setParameter(4, autorizacion.getAUT_Orden_Autorizacion());
		query.setParameter(5, autorizacion.getAUT_No_Documento_Autorizado());
		query.setParameter(6, autorizacion.getAUT_ID_Usuario());
		query.setParameter(7, autorizacion.getAUT_CI_Autoriza());
		query.setParameter(8, autorizacion.getAUT_PIN_Autoriza_Rechaza());
		query.setParameter(9, autorizacion.getAUT_Fecha_Autorizacion());
		query.setParameter(10, autorizacion.getAUT_Fecha_Rechazo());
		query.setParameter(11, autorizacion.getAUT_Fecha_Registro());
		
		System.out.println(autorizacion.getAUT_PIN_Autoriza_Rechaza());
		int result = (Integer)query.getSingleResult();
		return result;
	}

}
