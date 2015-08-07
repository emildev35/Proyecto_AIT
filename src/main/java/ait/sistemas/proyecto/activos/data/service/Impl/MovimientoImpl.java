package ait.sistemas.proyecto.activos.data.service.Impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MovimientoImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public MovimientoImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	public int getId(){
		String str_id = "EXEC Mvac_Generar_Codigo_Movimiento_Q";
		Query query = this.em.createNativeQuery(str_id);
		int result = (Integer)query.getSingleResult();
		return result;
	}
}
