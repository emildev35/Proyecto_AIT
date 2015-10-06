package ait.sistemas.proyecto.activos.data.service.Impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.CaracteristicasActivo;
import ait.sistemas.proyecto.activos.component.model.Componente;
import ait.sistemas.proyecto.activos.component.model.DatosGeneralesActivos;
import ait.sistemas.proyecto.activos.component.model.Documento;
import ait.sistemas.proyecto.activos.component.model.MovimientoReporte;
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
	
	public int getIdAcivo() {
		int result = 0;
		Query query = this.em.createNativeQuery("EXEC MVAC_INGRESO_GET_ID");
		result = (Integer) query.getSingleResult();
		return (result + 1);
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activos_by_auxiliar(String id_auxiliar) {
		Query query = em.createNativeQuery("Mvac_ActivosbyAuxiliar " + "@ACT_Auxiliar_Contable=?1 ", ActivosModel.class);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_auxiliar);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activosgrid_by_auxiliar(String id_auxiliar) {
		Query query = em.createNativeQuery("Mvac_ActivosbyAuxiliar " + "@ACT_Auxiliar_Contable=?1 ", ActivosModel.class);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_auxiliar);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activos_by_dependencia(short id_dependencia, Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivobyDependencia " + "@ACT_Dependencia=?1, " + "@fecha=?2 ",
				ActivosModel.class);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_dependencia);
		query.setParameter(2, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	/**
	 * Activos Sin Asignar Por Dependencia
	 * @param id_dependencia
	 * @param fecha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activosingasig_by_dependencia(short id_dependencia, Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivobyDependenciaSinAsig " + "@ACT_Dependencia=?1, " + "@fecha=?2 ",
				ActivosModel.class);
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_dependencia);
		query.setParameter(2, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	@SuppressWarnings("unchecked")
	public List<ActivosModel> ActivosNominal_by_dependencia(short id_dependencia, Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivoNominalbyDependencia " + "@ACT_Dependencia=?1, " + "@fecha=?2 ",
				ActivosModel.class);
		query.setParameter(1, id_dependencia);
		query.setParameter(2, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> activosbaja_by_dependencia(short id_dependencia, Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivoBajabyDependencia_Q " + "@ACT_Dependencia=?1, " + "@fecha=?2 ",
				ActivosModel.class);
		query.setParameter(1, id_dependencia);
		query.setParameter(2, fecha);
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
	public List<ActivosModel> getActivosbyFechaCompra(Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivobyFechaCompra_Q " + "@fecha=?1 ", "mapeo-activo");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	/**
	 * Lista de Activos sin Asignar
	 * @param fecha
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getActivosbyFechaCompraSinasig(Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivobyFechaCompraSinAsig_Q " + "@fecha=?1 ", "mapeo-activo");
		query.setParameter(1, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getActivosNominalbyFechaCompra(Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivoNominalbyFechaCompra_Q " + "@fecha=?1 ", "mapeo-activo");
		query.setParameter(1, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getActivosConsolidado(Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivoConsolidado_Q " + "@fecha=?1 ", "mapeo-activo");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getActivosBaja(Date fecha) {
		Query query = em.createNativeQuery("Mvac_ActivoBaja_Q " + "@fecha=?1 ", "mapeo-activo");
		query.setParameter(1, fecha);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	public int getResol(String resolucion) {
		Query query = em.createNativeQuery("Mvac_Resolucion_Q " + "@no_resolucion=?1 ");
		query.setParameter(1, resolucion);
		
		int resultlist = (int) query.getSingleResult();
		return resultlist;
	}
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getActivosbyResol(Date fecha, String resolucion) {
		Query query = em.createNativeQuery("Mvac_ActivobyResolucion_Q " + "@fecha=?1, "
				+ "@resolucion=?2 ", "mapeo-activo");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, fecha);
		query.setParameter(2, resolucion);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getActivosbyResolDependnecia(Date fecha, String resolucion, short id_dependencia) {
		Query query = em.createNativeQuery("Mvac_ActivobyResolucionDependencia_Q " + "@fecha=?1, "
				+ "@resolucion=?2,"
				+ "@id_dependencia=?3 ", "mapeo-activo");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, fecha);
		query.setParameter(2, resolucion);
		query.setParameter(3, id_dependencia);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getactivosbydependencia(short id_dependencia) {
		Query query = em.createNativeQuery("Mvac_ActivobyDependencia_Q @Id_Dependencia=?1 ", "mapeo-activo");
		query.setParameter(1, id_dependencia);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getactivos(short id_unidad_organizacional) {
		Query query = em.createNativeQuery("Mvac_ActivobyUnidad_Q @Id_Unidad=?1 ", "mapeo-activo");
		query.setParameter(1, id_unidad_organizacional);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getactivos(String ci_usuario) {
		Query query = em.createNativeQuery("Mvac_ActivobyUsuario_Q @CI_Usuario=?1", "mapeo-activo");
		query.setParameter(1, ci_usuario);
		List<ActivosModel> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivosModel> getall(long id_activo) {
		Query query = em.createNativeQuery("Mvac_Activo_Cod " + "@ACT_Codigo_Activo=?1 ", "mapeo-activo");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
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
		
		String str_datos_generales = "EXEC Mvac_Activos_I" + " @Id_Activos=?1, " + "@Id_Dependencia=?2, " + "@Nombre_Activo=?3, "
				+ "@Tipo_Activo=?4, " + "@Fecha_Compra=?5, " + "@Valor=?6, " + "@Tipo_Cambio_UFV=?7, " + "@Grupo_Contable=?8, "
				+ "@Auxiliar_Contable=?9, " + "@Vida_Util=?10, " + "@Fuente_Financiamiento=?11, "
				+ "@Organismo_Financiador=?12, " + "@Ubicacion_Fisica=?13, " + "@Fecha_ComoDato=?14, "
				+ "@Fecha_Incorporacion=?15," + "@Tipo_Cambio_Dolar=?16";
		
		Query query = this.em.createNativeQuery(str_datos_generales).setParameter(1, datos_generales.getId_activo())
				.setParameter(2, datos_generales.getId_dependencia()).setParameter(3, datos_generales.getNombre_activo())
				.setParameter(4, datos_generales.getTipo_activo()).setParameter(5, datos_generales.getFecha_compra())
				.setParameter(6, datos_generales.getValor()).setParameter(7, datos_generales.getTipo_cambio_ufv())
				.setParameter(8, datos_generales.getId_grupo_contable())
				.setParameter(9, datos_generales.getId_auxiliar_contalbe()).setParameter(10, datos_generales.getVida_util())
				.setParameter(11, datos_generales.getId_fuente_financiamiento())
				.setParameter(12, datos_generales.getId_organimismo_financiador())
				.setParameter(13, datos_generales.getId_ubicacion_fisica())
				.setParameter(14, datos_generales.getFecha_como_dato())
				.setParameter(15, datos_generales.getFecha_incorporacion())
				.setParameter(16, datos_generales.getTipo_cambio_dolar());
		
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
	
	@SuppressWarnings("unchecked")
	public List<ActivoGrid> getActivosDisponibles(short dependencia, String grupo_contable, String auxiliar_contable) {
		String str_query_act_disponibles = "EXEC Mvact_Activos_Disponibles @Grupo_Contable_Id=?1,@Auxiliar_Contable_Id=?2,@Dependencia_Id=?3";
		Query query = this.em.createNativeQuery(str_query_act_disponibles, "activo-simple").setParameter(1, grupo_contable)
				.setParameter(2, auxiliar_contable).setParameter(3, dependencia);
		List<ActivoGrid> result = (List<ActivoGrid>) query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivoGrid> getAsignados(String ci_usuario) {
		Query query = em.createNativeQuery("exec Mvac_ActivoAsignadobyUsuario @CI_Usuario=?1", "activo-simple").setHint(
				QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, ci_usuario);
		List<ActivoGrid> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<MovimientoReporte> ActivosbyUsuario(String ci_usuario, Date fecha) {
		Query query = em.createNativeQuery("exec Mvac_ActivosbyFuncionario_Q @CI_Usuario=?1, "
				+ "@fecha=?2 ", "reporte-movimiento").setHint(
				QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, ci_usuario);
		query.setParameter(2, fecha);
		List<MovimientoReporte> resultlist = query.getResultList();
		return resultlist;
	}
	
	public ActivoGrid getone(long id_activo, long id_dependencia) {
		Query query = em.createNativeQuery("exec Mvac_ActivosGetOne_Q @Id_Activo=?1 ", "activo-simple").setHint(
				QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_activo);
		ActivoGrid resultlist;
		try {
			resultlist = (ActivoGrid) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
		return resultlist;
	}
	
}
