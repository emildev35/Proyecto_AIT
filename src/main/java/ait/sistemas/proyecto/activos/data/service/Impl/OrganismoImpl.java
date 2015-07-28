package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.Organismo_Financiador;
@SuppressWarnings("unchecked")
	public class OrganismoImpl implements Dao<Organismo_Financiador> {

		private EntityManagerFactory emf;
		private EntityManager em;

		public OrganismoImpl() {
			this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
			this.em = emf.createEntityManager();
		}
		public short generateId() {
			short result = 1;
			Query query = this.em.createNativeQuery("exec Para_Organismo_MAX");
			result += (short)query.getSingleResult();
			return result;
		}
		@Override
		public List<Organismo_Financiador> getall() {
			Query query = em.createNativeQuery("Para_Organismo_Q ", Organismo_Financiador.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
			List<Organismo_Financiador> resultlist = query.getResultList();		
			return resultlist;
		}

		@Override
		public Organismo_Financiador getone(long id) {
			return null;
		}

		@Override
		public Organismo_Financiador add(Organismo_Financiador table) {
			String strQuery = String.format("EXEC Para_Organismo_I "
					+ "@ORF_Organismo_Financiador=?1, "
					+ "@ORF_Nombre_Organismo_Financiador=?2, "
					+ "@ORF_Fecha_Registro=?3 ");
			Query query = this.em.createNativeQuery(strQuery,Organismo_Financiador.class);
			query.setParameter(1, table.getORF_Organismo_Financiador());
			query.setParameter(2, table.getORF_Nombre_Organismo_Financiador());
			query.setParameter(3, table.getORF_Fecha_Registro());
			try{
				Organismo_Financiador result = (Organismo_Financiador) query.getSingleResult();
				return result;
			}catch(Exception e){
				return null;
			}
			
		}

		@Override
		public int delete(Organismo_Financiador table) {
			// TODO Auto-generated method stub
			return 0;
		}
		public int deletes(short id_organismo) {
		Query query = em.createNativeQuery("Para_Organismo_D "
				+ "@ORF_Organismo_Financiador=?1 ");
		query.setParameter(1, id_organismo);
		return (Integer)query.getSingleResult();	
		}
		@Override
		public Organismo_Financiador update(Organismo_Financiador table) {
			String strQuery = String.format("EXEC Para_Organismo_U "
					+ "@ORF_Organismo_Financiador=?1, "
					+ "@ORF_Nombre_Organismo_Financiador=?2, "
					+ "@ORF_Fecha_Registro=?3 ");
			Query query = this.em.createNativeQuery(strQuery,Organismo_Financiador.class);
			query.setParameter(1, table.getORF_Organismo_Financiador());
			query.setParameter(2, table.getORF_Nombre_Organismo_Financiador());
			query.setParameter(3, table.getORF_Fecha_Registro());
			Organismo_Financiador result = (Organismo_Financiador) query.getSingleResult();
			return result;
		}

	}
