package ait.sistemas.proyecto.activos.data.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.data.model.Activos;
import ait.sistemas.proyecto.activos.data.model.ActivosModel;

public class ActivoImpl {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ActivoImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}

	public List<Activos> activos_by_auxiliar(String id_auxiliar) {
		Query query = em.createNativeQuery("Mvac_ActivosbyAuxiliar " + "@ACT_Auxiliar_Contable=?1 ", Activos.class);
		query.setParameter(1, id_auxiliar);
		List<Activos> resultlist = query.getResultList();
		return resultlist;
	}
public List<ActivosModel> getall (BigDecimal id_activo){
	Query query = em.createNativeQuery("Mvac_Activo_Q " + "@ACT_Codigo_Activo=?1 ", Activos.class);
	query.setParameter(1, id_activo);
	List<ActivosModel> resultlist = query.getResultList();
	return resultlist;
}
}
