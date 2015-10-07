package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.component.model.Documento;

public class DocumentoRespaldoImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public DocumentoRespaldoImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	public boolean add(long activo, short dependencia, String nombre, String ubicacion) {
		
		String str_query = "EXEC MVACT_Documentos_Respaldo_A @Id_Activo=?1, "
				+ "@Id_Dependencia=?2, @Nombre=?3, @Ubicacion=?4, @Fecha_Registro=?5";
		
		Query query = this.em.createNativeQuery(str_query);
		query.setParameter(1, activo);
		query.setParameter(2, dependencia);
		query.setParameter(3, nombre);
		query.setParameter(4, ubicacion);
		query.setParameter(5, new java.sql.Date(new Date().getTime()));
		int result = (Integer) query.getSingleResult();
		return result > 0 ? true : false;
	}
	@SuppressWarnings("unchecked")
	public List<Documento> getDocumentos(long idActivo){
		Query query = this.em.createNativeQuery("EXEC Mvac_DocumentoActivo_Q @Id_Activo=?1", "documento")
				.setHint(QueryHints.REFRESH, HintValues.TRUE)
				.setParameter(1, idActivo);
		List<Documento> documentos = query.getResultList();
		return documentos;
	}
}
