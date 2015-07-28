package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.Partidas_Presupuestaria;
@SuppressWarnings("unchecked")
	public class PartidaImpl implements Dao<Partidas_Presupuestaria> {

		private EntityManagerFactory emf;
		private EntityManager em;

		public PartidaImpl() {
			this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
			this.em = emf.createEntityManager();
		}
		public short generateId() {
			short result = 1;
			Query query = this.em.createNativeQuery("exec Para_Partida_MAX");
			result += (Integer)query.getSingleResult();
			return result;
		}
		@Override
		public List<Partidas_Presupuestaria> getall() {
			Query query = em.createNativeQuery("Para_Partida_Q ", Partidas_Presupuestaria.class).setHint(QueryHints.REFRESH, HintValues.TRUE);
			List<Partidas_Presupuestaria> resultlist = query.getResultList();		
			return resultlist;
		}

		@Override
		public Partidas_Presupuestaria getone(long id) {
			return null;
		}

		@Override
		public Partidas_Presupuestaria add(Partidas_Presupuestaria table) {
			String strQuery = String.format("EXEC Para_Partida_I "
					+ "@PAP_Partida=?1, "
					+ "@PAP_Nombre_Partida=?2, "
					+ "@PAP_Fecha_Registro=?3 ");
			Query query = this.em.createNativeQuery(strQuery,Partidas_Presupuestaria.class);
			query.setParameter(1, table.getPAP_Partida());
			query.setParameter(2, table.getPAP_Nombre_Partida());
			query.setParameter(3, table.getPAP_Fecha_Registro());
			try{
				Partidas_Presupuestaria result = (Partidas_Presupuestaria) query.getSingleResult();
				return result;
			}catch(Exception e){
				return null;
			}
			
		}

		@Override
		public int delete(Partidas_Presupuestaria table) {
			// TODO Auto-generated method stub
			return 0;
		}
		public int deletes(int id_partida) {
		Query query = em.createNativeQuery("Para_Partida_D "
				+ "@PAP_Partida=?1 ");
		query.setParameter(1, id_partida);
		return (Integer)query.getSingleResult();	
		}
		@Override
		public Partidas_Presupuestaria update(Partidas_Presupuestaria table) {
			String strQuery = String.format("EXEC Para_Partida_U "
					+ "@PAP_Partida=?1, "
					+ "@PAP_Nombre_Partida=?2, "
					+ "@PAP_Fecha_Registro=?3 ");
			Query query = this.em.createNativeQuery(strQuery,Partidas_Presupuestaria.class);
			query.setParameter(1, table.getPAP_Partida());
			query.setParameter(2, table.getPAP_Nombre_Partida());
			query.setParameter(3, table.getPAP_Fecha_Registro());
			Partidas_Presupuestaria result = (Partidas_Presupuestaria) query.getSingleResult();
			return result;
		}

	}
