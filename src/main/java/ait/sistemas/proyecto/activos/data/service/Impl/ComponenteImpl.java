package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ComponenteImpl {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ComponenteImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public boolean add(long activo, short dependencia, String nombre, String caracteristica) {
		
		String str_query = "EXEC MVACT_Componente_A @Id_Activo=?1, "
				+ "@Id_Dependencia=?2, @Nombre=?3, @Caracteristica=?4, @Fecha_Registro=?5";
		
		Query query = this.em.createNativeQuery(str_query);
		query.setParameter(1, activo);
		query.setParameter(2, dependencia);
		query.setParameter(3, nombre);
		query.setParameter(4, caracteristica);
		query.setParameter(5, new java.sql.Date(new Date().getTime()));
		int result = (Integer) query.getSingleResult();
		return result > 0 ? true : false;
	}
}
