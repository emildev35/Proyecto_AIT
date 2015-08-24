package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReporteImpl {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ReporteImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getColumnName(String tablename) {
		String str_query = "EXEC Mvac_Get_ColumnNames @Table_Name=?1";
		Query query = this.em.createNativeQuery(str_query).setParameter(1, tablename);
		List<String> result = query.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String[][] getData(String cols, String view_name, int numofColumns) {
		
		String str_query = "SELECT " + cols + " FROM " + view_name;
		Query query = this.em.createNativeQuery(str_query);
		Collection<Object> data = query.getResultList();
		String[][] result = new String[data.size()][numofColumns];
		int row = 0;
		for (Object element : data) {
			Object[] fila = ((Object[]) element);
			for (int i = 0; i < fila.length; i++) {
				result[row][i] = fila[i] != null ? fila[i].toString() : "";
			}
			row++;
		}
		return result;
	}
	
}
