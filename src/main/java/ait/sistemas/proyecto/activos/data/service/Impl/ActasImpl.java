package ait.sistemas.proyecto.activos.data.service.Impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.component.model.MovimientoReporte;
import ait.sistemas.proyecto.activos.component.model.SolicitudGrid;
import ait.sistemas.proyecto.activos.data.ConnecctionActivos;

public class ActasImpl {
	ConnecctionActivos conn = new ConnecctionActivos();
	private EntityManagerFactory emf;
	private EntityManager em;

	public ActasImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<SolicitudGrid> getSolicitudAsignacion(int tipo_movimiento) {
		// this.em.getEntityManagerFactory().getCache().evict(Movimiento.class);
		Query query = this.em.createNativeQuery("EXEC Mvac_SolAsignacion_Q " + "@tipo_movimiento=?1 ", "grid_cmovimiento");
		query.setParameter(1, tipo_movimiento);
		List<SolicitudGrid> resultlist = query.getResultList();
		return resultlist;
	}

	@SuppressWarnings("unchecked")
	public List<Movimiento> getSolicitudDevolucion(Movimiento table) {
		Query query = this.em.createNativeQuery("EXEC Mvac_SolDevolucion_Q " + "@tipo_movimiento=?1 ", "cmovimiento");
		query.setParameter(1, table.getTipo_movimiento());
		List<Movimiento> resultlist = query.getResultList();
		return resultlist;
	}

	@SuppressWarnings("unchecked")
	public List<Movimiento> getSolTransferencia(Movimiento table) {
		// this.em.getEntityManagerFactory().getCache().evict(Movimiento.class);
		Query query = this.em
				.createNativeQuery("EXEC Mvac_SolTransferencia_Q " + "@tipo_movimiento=?1 ", "cmovimiento");
		query.setParameter(1, table.getTipo_movimiento());
		List<Movimiento> resultlist = query.getResultList();
		return resultlist;
	}

	@SuppressWarnings("unchecked")
	public List<MovimientoReporte> ReporteActa(String nro_acta, short tipo_movimiento) {
		// this.em.getEntityManagerFactory().getCache().evict(Movimiento.class);
		Query query = this.em.createNativeQuery("EXEC Mvac_ReporteActa_Q " + "@nro_acta=?1, "
				+ "@tipo_movimiento=?2 ", "reporte-movimiento");
		query.setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, nro_acta);
		query.setParameter(2, tipo_movimiento);
		List<MovimientoReporte> resultlist = query.getResultList();
		return resultlist;
	}

	public int addActaAsignacion(Movimiento table) {
		String str_proc = String.format("EXEC Mvac_ActaAsignacion_I " + "@nro_documento=%d, " + "@no_acta=%d, "
				+ "@fecha_acta='%s', " + "@Tipo_Movimiento=%d," + "@Tipo_Movimiento_Nuevo=%d ",
				table.getNro_documento(), table.getNo_acta(),
				new SimpleDateFormat("yyyy-dd-MM").format(table.getFecha_acta()), table.getTipo_movimiento(),
				table.getTipo_movimiento_nuevo());
		try {
			return conn.callproc(str_proc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int addActaDevolucion(Movimiento table) {
		String str_proc = String.format("EXEC Mvac_ActaDevolucion_I " + "@nro_documento=%d, " + "@no_acta=%d, "
				+ "@fecha_acta='%s', " + "@Tipo_Movimiento=%d," + "@Tipo_Movimiento_Nuevo=%d ",
				table.getNro_documento(), table.getNo_acta(),
				new SimpleDateFormat("yyyy-dd-MM").format(table.getFecha_acta()), table.getTipo_movimiento(),
				table.getTipo_movimiento_nuevo());
		try {
			return conn.callproc(str_proc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int addActaTransferencia(Movimiento table) {
		String str_proc = String.format("EXEC Mvac_ActaTransferencia_I " + "@nro_documento=%d, " + "@no_acta=%d, "
				+ "@fecha_acta='%s', " + "@Tipo_Movimiento=%d," + "@Tipo_Movimiento_Nuevo=%d ",
				table.getNro_documento(), table.getNo_acta(),
				new SimpleDateFormat("yyyy-dd-MM").format(table.getFecha_acta()), table.getTipo_movimiento(),
				table.getTipo_movimiento_nuevo());
		try {
			return conn.callproc(str_proc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
