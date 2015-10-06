package ait.sistemas.proyecto.activos.data.service.Impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	public int Cierre(Cierre_Gestion cierre_Gestion) {
		Query query = em.createNativeQuery("EXEC Reva_CierreGestion_I "
				+ "@Fecha=?1, "
				+ "@Tipo_Cambio=?2 ");
		query.setParameter(1,cierre_Gestion.getCGE_Fecha_Cierre_Gestion());
		query.setParameter(2,cierre_Gestion.getCGE_Tipo_Cambio_UFV());
		try {
			int result = (Integer) query.getSingleResult();
			return result;
		} catch (Exception e) {
			return 0;
		}
	}

}
