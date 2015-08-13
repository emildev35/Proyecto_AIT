package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;

public class AutorizacionImpl {

	private EntityManagerFactory emf;
	private EntityManager em;

	public AutorizacionImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	@SuppressWarnings("unchecked")
	public List<DocumentoPendiente> getDocumentosPendientes(String id_usuario){
		String str_query = "EXEC Mvac_Get_DocumentosbyUsuario @Id_Usuario=?1";
		
		Query query = this.em.createNativeQuery(str_query, "documento-pendiente");
		query.setParameter(1, id_usuario);
		List<DocumentoPendiente> result = query.getResultList();
		return result;
	}

}
