package ait.sistemas.proyecto.activos.data.service.Impl;

	import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.Tipos_Movimiento;
@SuppressWarnings("unchecked")
	public class TiposmovImpl implements Dao<Tipos_Movimiento> {

		private EntityManagerFactory emf;
		private EntityManager em;

		public TiposmovImpl() {
			this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
			this.em = emf.createEntityManager();
		}
		public short generateId() {
			short result = 1;
			Query query = this.em.createNativeQuery("exec Para_Tiposmov_MAX");
			result += (Short)query.getSingleResult();
			return result;
		}
		@Override
		public List<Tipos_Movimiento> getall() {
			Query query = em.createNativeQuery("Para_Tiposmov_Q ", Tipos_Movimiento.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
			List<Tipos_Movimiento> resultlist = query.getResultList();		
			return resultlist;
		}

		@Override
		public Tipos_Movimiento getone(long id) {
			return null;
		}

		@Override
		public Tipos_Movimiento add(Tipos_Movimiento table) {
			String strQuery = String.format("EXEC Para_Tiposmov_I "
					+ "@TMV_Tipo_Movimiento=?1, "
					+ "@TMV_Nombre_Tipo_Movimiento=?2, "
					+ "@TMV_Sigla_Tipo_Movimiento=?3, "
					+ "@TMV_Fecha_Registro=?4 ");
			Query query = this.em.createNativeQuery(strQuery,Tipos_Movimiento.class);
			query.setParameter(1, table.getTMV_Tipo_Movimiento());
			query.setParameter(2, table.getTMV_Nombre_Tipo_Movimiento());
			query.setParameter(3, table.getTMV_Sigla_Tipo_Movimiento());
			query.setParameter(4, table.getTMV_Fecha_Registro());
			try{
				Tipos_Movimiento result = (Tipos_Movimiento) query.getSingleResult();
				return result;
			}catch(Exception e){
				return null;
			}
			
		}

		@Override
		public int delete(Tipos_Movimiento table) {
			return 0;
		}
		public int deletes(Short id_tipo_movimiento) {
		Query query = em.createNativeQuery("Para_Tiposmov_D "
				+ "@TMV_Tipo_Movimiento=?1 ");
		query.setParameter(1, id_tipo_movimiento);
		return (Integer)query.getSingleResult();	
		}
		@Override
		public Tipos_Movimiento update(Tipos_Movimiento table) {
			String strQuery = String.format("EXEC Para_Tiposmov_U "
					+ "@TMV_Tipo_Movimiento=?1, "
					+ "@TMV_Nombre_Tipo_Movimiento=?2, "
					+ "@TMV_Sigla_Tipo_Movimiento=?3, "
					+ "@TMV_Fecha_Registro=?4 ");
			Query query = this.em.createNativeQuery(strQuery,Tipos_Movimiento.class);
			query.setParameter(1, table.getTMV_Tipo_Movimiento());
			query.setParameter(2, table.getTMV_Nombre_Tipo_Movimiento());
			query.setParameter(3, table.getTMV_Sigla_Tipo_Movimiento());
			query.setParameter(4, table.getTMV_Fecha_Registro());
			Tipos_Movimiento result = (Tipos_Movimiento) query.getSingleResult();
			return result;
		}

		public int getNivelAutorizacion(short dependencia, short tipo_movimiento){
			String str_query = "EXEC Act_GetNivelAutorizacion_Q @Id_Dependencia=?1, @Tipo_Movimiento=?2";
			Query query = this.em.createNativeQuery(str_query);
			query.setParameter(1, dependencia)
			.setParameter(2, tipo_movimiento);
			return (Integer)query.getSingleResult();
		}
	}
