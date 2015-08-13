package ait.sistemas.proyecto.activos.data.service.Impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.data.model.PinModel;

public class PinImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public PinImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-RecursosHumanos");
		this.em = emf.createEntityManager();
	}
	
	public boolean addPIN(PinModel pin) {
		String str_query = "EXEC Rrhh_Pin_I " + "@Id_Usuario=?1," + "@Id_Dependencia=?2," + "@Id_Unidad_Organizacional=?3,"
				+ "@Fecha_Generacion=?4," + "@Fecha_Registro=?5," + "@PIN=?6," + "@CI=?7";
		Query query = this.em.createNativeQuery(str_query);
		query.setParameter(1, pin.getUsuario_id());
		query.setParameter(2, pin.getDependencia_id());
		query.setParameter(3, pin.getUnidad_organizacional_id());
		query.setParameter(4, pin.getFecha_generacion());
		query.setParameter(5, pin.getFecha_registro());
		query.setParameter(6, pin.getPin());
		query.setParameter(7, pin.getCi());
		int result = (Integer) query.getSingleResult();
		return result > 0 ? true : false;
	}
	
}
