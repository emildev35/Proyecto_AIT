package ait.sistemas.proyecto.activos.data.service.Impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.QueryHint;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.model.Cierre_Gestion;

public class CierreGestionImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public CierreGestionImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public Cierre_Gestion getall() {
		
		Query query = em.createNativeQuery("EXEC Reva_CierreGestion_Q", "cierre-gestion");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		Cierre_Gestion resultlist = (Cierre_Gestion)query.getSingleResult();
		return resultlist;
	}
}
