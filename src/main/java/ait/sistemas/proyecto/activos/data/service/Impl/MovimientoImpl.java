package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;

public class MovimientoImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public MovimientoImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	public long getId(){
		String str_id = "EXEC Mvac_Generar_Codigo_Movimiento_Q";
		Query query = this.em.createNativeQuery(str_id);
		long result = (Long)query.getSingleResult();
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<ActivoGrid> activos_asinados(short id_dependencia) {
		this.em.getEntityManagerFactory().getCache().evict(ActivoGrid.class);
		Query query = em.createNativeQuery("exec Mvac_ActivoAsignadobyDependencia @ACT_Dependencia=?1 ", "activo-simple").setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, (id_dependencia));
		List<ActivoGrid> resultlist = query.getResultList();		
		return resultlist;
	}


}
