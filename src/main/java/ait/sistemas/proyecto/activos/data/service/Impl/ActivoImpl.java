package ait.sistemas.proyecto.activos.data.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.component.model.DatosGeneralesActivos;
import ait.sistemas.proyecto.activos.data.model.Activos;
import ait.sistemas.proyecto.activos.data.model.ActivosModel;

public class ActivoImpl {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ActivoImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}

	public int getIdAcivo(short id_dependencia) {
		int result = 0;
		Query query = this.em.createNativeQuery("EXEC MVAC_INGRESO_GET_ID @Id_Dependencia=?1");
		query.setParameter(1, id_dependencia);
		result = (Integer)query.getSingleResult();
		return (result + 1);
	}
	
	@SuppressWarnings("unchecked")
	public List<Activos> activos_by_auxiliar(String id_auxiliar) {
		Query query = em.createNativeQuery("Mvac_ActivosbyAuxiliar " + "@ACT_Auxiliar_Contable=?1 ",
				Activos.class);
		query.setParameter(1, id_auxiliar);
		List<Activos> resultlist = query.getResultList();
		return resultlist;
	}

public List<ActivosModel> getall (BigDecimal id_activo){
	Query query = em.createNativeQuery("Mvac_Activo_Q " + "@ACT_Codigo_Activo=?1 ", Activos.class);
	query.setParameter(1, id_activo);
	List<ActivosModel> resultlist = query.getResultList();
	return resultlist;
}

	
	public boolean add(DatosGeneralesActivos datos_generales) {
		String str_datos_generales = "EXEC MVAC_INGRESO_A" + " @Id_Activos=?1, "
				+ "@Id_Dependencia=?2, " + "@Nombre_Activo=?3, " + "@Tipo_Activo=?4, " + "@Fecha_Compra=?5, "
				+ "@Valor=?6, " + "@Tipo_Cambio=?7, " + "@Grupo_Contable=?8, " + "@Auxiliar_Contable=?9, "
				+ "@Vida_Util=?10, " + "@Fuente_Financiamiento=?11, " + "@Organismo_Financiador=?12, "
				+ "@Ubicacion_Fisica=?13, " + "@Fecha_ComoDato=?14";
		
		Query query = this.em.createNativeQuery(str_datos_generales)
				.setParameter(1, datos_generales.getId_activo())
				.setParameter(2, datos_generales.getId_dependencia())
				.setParameter(3, datos_generales.getNombre_activo())
				.setParameter(4, datos_generales.getTipo_activo())
				.setParameter(5, datos_generales.getFecha_compra())
				.setParameter(6, datos_generales.getValor())
				.setParameter(7, datos_generales.getTipo_cambio())
				.setParameter(8, datos_generales.getId_grupo_contable())
				.setParameter(9, datos_generales.getId_auxiliar_contalbe())
				.setParameter(10, datos_generales.getVida_util())
				.setParameter(11, datos_generales.getId_fuente_financiamiento())
				.setParameter(12, datos_generales.getId_organimismo_financiador())
				.setParameter(13, datos_generales.getId_ubicacion_fisica())
				.setParameter(14, datos_generales.getFecha_como_dato());
		
		int result = (Integer) query.getSingleResult();
		
		return (result > 0) ? true : false;
	}

}
