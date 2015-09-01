package ait.sistemas.proyecto.activos.data.service.Impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.component.model.TipoCambio;

public class TipoCambioImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TipoCambioImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	@SuppressWarnings("unchecked")
	public List<TipoCambio> getTipoCambio(Date fecha_incorporacion){
		String str_get_tipo_cambio = "EXEC Mvac_Tipo_Cambio_Q @Fecha_Incorporacion=?1";
		Query query = this.em.createNativeQuery(str_get_tipo_cambio, "tipo-cambio");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, fecha_incorporacion);
		List<TipoCambio> result = query.getResultList();
		return result;
	}
	public TipoCambio getTipoCambioUFV(Date fecha){
		String str_get_tipo_cambio = "EXEC Mvac_Tipo_Cambio_UFV_Q @Fecha=?1 ";
		Query query = this.em.createNativeQuery(str_get_tipo_cambio, "tipo-cambio");
		query.setParameter(1, fecha);
		TipoCambio result = (TipoCambio)query.getSingleResult();
		return result;
	}
}
