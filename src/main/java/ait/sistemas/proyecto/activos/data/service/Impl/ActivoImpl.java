package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.Asignacion;
import ait.sistemas.proyecto.activos.component.model.CaracteristicasActivo;
import ait.sistemas.proyecto.activos.component.model.Componente;
import ait.sistemas.proyecto.activos.component.model.DatosGeneralesActivos;
import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.component.model.Documento;
import ait.sistemas.proyecto.activos.component.session.ActivoSession;
import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.model.ComponentesModel;
import ait.sistemas.proyecto.activos.data.model.DocumentosRespaldoModel;

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
		result = (Integer) query.getSingleResult();
		return (result + 1);
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activos_by_auxiliar(String id_auxiliar) {
		Query query = em.createNativeQuery("Mvac_ActivosbyAuxiliar " + "@ACT_Auxiliar_Contable=?1 ", ActivosModel.class);
		query.setParameter(1, id_auxiliar);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activosgrid_by_auxiliar(String id_auxiliar) {
		Query query = em.createNativeQuery("Mvac_ActivosbyAuxiliar " + "@ACT_Auxiliar_Contable=?1 ", ActivosModel.class);
		query.setParameter(1, id_auxiliar);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activos_by_dependencia(short id_dependencia) {
		Query query = em.createNativeQuery("Mvac_ActivobyDependencia " + "@ACT_Dependencia=?1 ", ActivosModel.class);
		query.setParameter(1, id_dependencia);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activo_aux_grup(String auxiliar, String grupo, short id_dependencia) {
		Query query = em.createNativeQuery("Mvac_ActivobyAuxiliar-Grupo " + "@ACT_Dependencia=?1, "
				+ "@ACT_Auxiliar_Contable=?2," + "@ACT_Grupo_Contable=?3", ActivosModel.class);
		query.setParameter(1, id_dependencia);
		query.setParameter(2, auxiliar);
		query.setParameter(3, grupo);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getactivos() {
		Query query = em.createNativeQuery("Mvac_Activo_Q ", "mapeo-activo");
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getall(long id_activo) {
		Query query = em.createNativeQuery("Mvac_Activo_Cod " + "@ACT_Codigo_Activo=?1 ", "mapeo-activo");
		query.setParameter(1, id_activo);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ComponentesModel> getcomponente(long id_activo, short id_dependencia) {
		Query query = em.createNativeQuery("Mvac_ActivobyComponente " + "@COM_Codigo_Activo=?1, " + "@COM_Dependencia=?2",
				"mapeo-componente");
		query.setParameter(1, id_activo);
		query.setParameter(2, id_dependencia);
		List<ComponentesModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentosRespaldoModel> getdocumento(long id_activo, short id_dependencia) {
		Query query = em.createNativeQuery("Mvac_ActivobyDocumentoRespaldo " + "@DOR_Codigo_Activo=?1," + "@DOR_Dependencia=?2 ",
				"mapeo-documento");
		query.setParameter(1, id_activo);
		query.setParameter(2, id_dependencia);
		List<DocumentosRespaldoModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	public boolean add(DatosGeneralesActivos datos_generales) {
		
		String str_datos_generales = "EXEC MVAC_INGRESO_A" + " @Id_Activos=?1, " + "@Id_Dependencia=?2, " + "@Nombre_Activo=?3, "
				+ "@Tipo_Activo=?4, " + "@Fecha_Compra=?5, " + "@Valor=?6, " + "@Tipo_Cambio=?7, " + "@Grupo_Contable=?8, "
				+ "@Auxiliar_Contable=?9, " + "@Vida_Util=?10, " + "@Fuente_Financiamiento=?11, "
				+ "@Organismo_Financiador=?12, " + "@Ubicacion_Fisica=?13, " + "@Fecha_ComoDato=?14";
		
		Query query = this.em.createNativeQuery(str_datos_generales).setParameter(1, datos_generales.getId_activo())
				.setParameter(2, datos_generales.getId_dependencia()).setParameter(3, datos_generales.getNombre_activo())
				.setParameter(4, datos_generales.getTipo_activo()).setParameter(5, datos_generales.getFecha_compra())
				.setParameter(6, datos_generales.getValor()).setParameter(7, datos_generales.getTipo_cambio())
				.setParameter(8, datos_generales.getId_grupo_contable())
				.setParameter(9, datos_generales.getId_auxiliar_contalbe()).setParameter(10, datos_generales.getVida_util())
				.setParameter(11, datos_generales.getId_fuente_financiamiento())
				.setParameter(12, datos_generales.getId_organimismo_financiador())
				.setParameter(13, datos_generales.getId_ubicacion_fisica())
				.setParameter(14, datos_generales.getFecha_como_dato());
		
		int result = (Integer) query.getSingleResult();
		
		return (result > 0) ? true : false;
	}
	
	public boolean addCaracteristica(CaracteristicasActivo caracteristicas) {
		String str_add_caracteristicas = "EXEC MVAC_INGRESO_CARACTERISTICA_A" + " @Id_Activos=?1, " + "@Id_Dependencia=?2, "
				+ "@Nit_Proveedor=?3, " + "@Marca=?4, " + "@Numero_Serie=?5, " + "@Numero_Garantia=?6, " + "@Numero_Ruat=?7, "
				+ "@Numero_Folio_Real=?8, " + "@Numero_Poliza_Seguro=?9, " + "@Numero_Contrato_Mantenimiento=?10, "
				+ "@Vencimiento_Garantia=?11, " + "@Vencimiento_Seguro=?12, " + "@Vencimiento_Contrato_Mantenumiento=?13, "
				+ "@Ubicacion_Imagen=?14";
		
		Query query = this.em.createNativeQuery(str_add_caracteristicas).setParameter(1, caracteristicas.getCodigo())
				.setParameter(2, caracteristicas.getDependencia()).setParameter(3, caracteristicas.getNit_proveedor())
				.setParameter(4, caracteristicas.getMarca()).setParameter(5, caracteristicas.getNumero_serie())
				.setParameter(6, caracteristicas.getNumero_garantia()).setParameter(7, caracteristicas.getNumero_ruat())
				.setParameter(8, caracteristicas.getNumero_folio_real())
				.setParameter(9, caracteristicas.getNumero_poliza_seguro())
				.setParameter(10, caracteristicas.getNumero_contrato_mantenimiento())
				.setParameter(11, caracteristicas.getVencimiento_garantia())
				.setParameter(12, caracteristicas.getVencimiento_seguro())
				.setParameter(13, caracteristicas.getVencimiento_contrato_mantenumiento())
				.setParameter(14, caracteristicas.getUbicacion_imagen());
		
		int result = (Integer) query.getSingleResult();
		
		return (result > 0) ? true : false;
	}
	
	public boolean addComponentes(List<Componente> componentes, ActivoSession sessionactivo) {
		final ComponenteImpl componenteimpl = new ComponenteImpl();
		for (Componente componente : componentes) {
			if (!componenteimpl.add(sessionactivo.getCodigo(), sessionactivo.getDependencia(), componente.getNombre(),
					componente.getCaracteritica())) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addDocumentos(List<Documento> documetnos, ActivoSession sessionactivo) {
		// TODO Auto-generated method stub
		final DocumentoRespaldoImpl documentoimpl = new DocumentoRespaldoImpl();
		for (Documento componente : documetnos) {
			if (!documentoimpl.add(sessionactivo.getCodigo(), sessionactivo.getDependencia(), componente.getNombre(),
					componente.getDireccion())) {
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivoGrid> getDisponibles(String grupo_contable, String auxiliar_contable) {
		String str_query_act_disponibles = "EXEC Mvact_Select_Disponibles @Grupo_Contable_Id=?1,@Auxiliar_Contable_Id=?2";
		Query query = this.em.createNativeQuery(str_query_act_disponibles, "activo-simple").setParameter(1, grupo_contable)
				.setParameter(2, auxiliar_contable);
		List<ActivoGrid> result = (List<ActivoGrid>) query.getResultList();
		return result;
	}
	
	public int mov_asignacion(Asignacion data) {
		String str_cabezera = "EXEC Mvac_CAsignacion_I " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
				+ "@Numero_Documento=?3," + "@Fecha_Registro=?4," + "@Fecha_Movimiento=?5," + "@CI_Usuario=?6,"
				+ "@Observaciones=?7";
		Query query_cabezera = this.em.createNativeQuery(str_cabezera);
		query_cabezera.setParameter(1, data.getId_dependencia());
		query_cabezera.setParameter(2, data.getId_unidad_organizacional_origen());
		query_cabezera.setParameter(3, data.getNro_documento());
		query_cabezera.setParameter(4, data.getFecha_registro());
		query_cabezera.setParameter(5, data.getFecha_movimiento());
		query_cabezera.setParameter(6, data.getUsuario());
		query_cabezera.setParameter(7, data.getObservacion());
		
		int result_cabezera = (Integer) query_cabezera.getSingleResult();
		int result_detalle = 0;
		if (result_cabezera > 0) {
			for (Detalle detalle : data.getDetalles()) {
				
				String str_detalle = "EXEC Mvac_DMovimiento_I " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
						+ "@Numero_Documento=?3," + "@Fecha_Registro=?4," + "@Tipo_Movimiento=?5," + "@Activo_Id=?6,"
						+ "@Observaciones=?7";
				Query query_detalle = this.em.createNativeQuery(str_detalle);
				query_detalle.setParameter(1, detalle.getId_dependencia());
				query_detalle.setParameter(2, detalle.getId_unidad_organizacional_origen());
				query_detalle.setParameter(3, detalle.getNro_documento());
				query_detalle.setParameter(4, detalle.getFecha_registro());
				query_detalle.setParameter(5, 3);
				query_detalle.setParameter(6, detalle.getId_activo());
				query_detalle.setParameter(7, detalle.getObservacion());
				result_detalle += (Integer) query_detalle.getSingleResult();
			}
			
			if (result_detalle == data.getDetalles().size()) {
				return 1;
			} else {
				drop_mov_asignacion(data);
				return 0;
			}
			
		} else {
			return 0;
		}
	}
	
	public int drop_mov_asignacion(Asignacion data) {
		int result_cabezera;
		for (Detalle detalle : data.getDetalles()) {
			
			String str_detalle = "EXEC Mvact_DMovimiento_D " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
					+ "@Numero_Documento=?3," + "@Tipo_Movimiento=?4," + "@Activo_Id=?4";
			
			Query query_detalle = this.em.createNativeQuery(str_detalle);
			query_detalle.setParameter(1, detalle.getId_dependencia());
			query_detalle.setParameter(2, detalle.getId_unidad_organizacional_origen());
			query_detalle.setParameter(3, detalle.getNro_documento());
			query_detalle.setParameter(4, 3);
			query_detalle.setParameter(5, detalle.getId_activo());
			result_cabezera = (Integer) query_detalle.getSingleResult();
		}
		
		String str_cabezera = "EXEC Mvact_CAsignacion_D " + "@Dependencia_Id=?1," + "@Unidad_Organizacional_Id=?2,"
				+ "@Numero_Documento=?3";
		Query query_cabezera = this.em.createNativeQuery(str_cabezera);
		query_cabezera.setParameter(1, data.getId_dependencia());
		query_cabezera.setParameter(2, data.getId_unidad_organizacional_origen());
		query_cabezera.setParameter(3, data.getNro_documento());
		
		result_cabezera = (Integer) query_cabezera.getSingleResult();
		return result_cabezera;
		
	}
	
}
