package ait.sistemas.proyecto.activos.data.service.Impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.data.model.Fecha_Depreciacion;

public class FechaDepreciacionImpl  {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public FechaDepreciacionImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public Fecha_Depreciacion getFechaDep() {
		
		Query query = em.createNativeQuery("EXEC Reva_FechaDepre_Q", "fecha-depre");
		Fecha_Depreciacion resultlist = (Fecha_Depreciacion)query.getSingleResult();
		return resultlist;
	}
}
