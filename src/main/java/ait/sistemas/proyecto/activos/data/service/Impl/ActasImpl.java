package ait.sistemas.proyecto.activos.data.service.Impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
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
	public List<Movimiento> getsolicitud(Movimiento table) {
//		this.em.getEntityManagerFactory().getCache().evict(Movimiento.class);
		Query query = this.em.createNativeQuery("EXEC Mant_SolAsignacion_Q "+"@tipo_movimiento=?1 ", "cmovimiento");
		query.setParameter(1, table.getTipo_movimiento());
		List<Movimiento> resultlist = query.getResultList();		
		return resultlist;
	}
	public int addActa(Movimiento table){
		String str_proc = String.format("EXEC Mvac_ActaAsignacion_I "
				+ "@nro_documento=%d, "
				+ "@no_acta=%d, "
				+ "@fecha_acta='%s', "
				+ "@Tipo_Movimiento=%d ",
		table.getNro_documento(), table.getNo_acta(), new SimpleDateFormat("yyyy-dd-MM").format(table.getFecha_acta()), table.getTipo_movimiento());
		try {
			return conn.callproc(str_proc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
