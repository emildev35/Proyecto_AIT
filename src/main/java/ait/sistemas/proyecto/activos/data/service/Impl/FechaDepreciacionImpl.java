package ait.sistemas.proyecto.activos.data.service.Impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FechaDepreciacionImpl  {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public FechaDepreciacionImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public short getFechaDep() {
		short result = 1;
//		Query query = this.em.createNativeQuery("exec Para_Fuente_MAX");
//		result += (Integer) query.getSingleResult();
		return result;
	}
}
