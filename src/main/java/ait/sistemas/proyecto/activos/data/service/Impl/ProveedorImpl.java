package ait.sistemas.proyecto.activos.data.service.Impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.activos.data.model.Proveedore;
import ait.sistemas.proyecto.activos.data.model.ProveedoresModel;
@SuppressWarnings("unchecked")
public class ProveedorImpl implements Dao<Proveedore> {

	private EntityManagerFactory emf;
	private EntityManager em;

	public ProveedorImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Activos");
		this.em = emf.createEntityManager();
	}

	@Override
	public List<Proveedore> getall() {
		return null;
	}
	public List<ProveedoresModel> getalls() {
		this.em.getEntityManagerFactory().getCache().evict(ProveedoresModel.class);
		Query query = em.createNativeQuery("exec Mant_Proveedor_Q", "archive-map-proveedor").setHint(QueryHints.REFRESH, HintValues.TRUE);
		List<ProveedoresModel> resultlist = query.getResultList();		
		return resultlist;
	}
	
	public List<ProveedoresModel> getallreporte(Short id_ciudad, Short id_dependencia) {
		this.em.getEntityManagerFactory().getCache().evict(ProveedoresModel.class);
		Query query = em.createNativeQuery("exec Mant_Proveedor_Dependencia_Ciudad_Q "
				+ "@PRV_Dependencia=?1,"
				+ "@PRV_Ciudad=?2", "archive-map-proveedor").setHint(QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, id_dependencia);
		query.setParameter(2, id_ciudad);
		List<ProveedoresModel> resultlist = query.getResultList();		
		return resultlist;
	}
	
	
	@Override
	public Proveedore getone(long id) {
		return null;
	}

	@Override
	public Proveedore add(Proveedore table) {
		String strQuery = String.format("EXEC Mant_Proveedor_I "
				+ "@PRV_Dependencia=?1, "
				+ "@PRV_NIT=?2, "
				+ "@PRV_Nombre=?3, "
				+ "@PRV_Ciudad=?4, "
				+ "@PRV_Domicilio=?5, "
				+ "@PRV_Telefono=?6, "
				+ "@PRV_Celular_Contacto=?7, "
				+ "@PRV_Nombre_Contacto=?8, "
				+ "@PRV_Fecha_Registro=?9");
		Query query = this.em.createNativeQuery(strQuery,Proveedore.class);
		query.setParameter(1, table.getPRV_Dependencia());
		query.setParameter(2, table.getPRV_NIT());
		query.setParameter(3, table.getPRV_Nombre());
		query.setParameter(4, table.getPRV_Ciudad());
		query.setParameter(5, table.getPRV_Domicilio());
		query.setParameter(6, table.getPRV_Telefono());
		query.setParameter(7, table.getPRV_Celular_Contacto());
		query.setParameter(8, table.getPRV_Nombre_Contacto());
		query.setParameter(9, table.getPRV_Fecha_Registro());
		try{
			Proveedore result = (Proveedore) query.getSingleResult();
			return result;
		}catch(Exception e){
			return null;
		}
		
	}

	@Override
	public int delete(Proveedore table) {
		return 0;
	}
	public int deletes(Short id_dependencia, String NIT) {
	Query query = em.createNativeQuery("Mant_Proveedor_D "
			+ "@PRV_Dependencia=?1, "
			+ "@PRV_NIT=?2");
	query.setParameter(1, id_dependencia);
	query.setParameter(2, NIT);
	return (Integer)query.getSingleResult();	
	}
	@Override
	public Proveedore update(Proveedore table) {
		String strQuery = String.format("EXEC Mant_Proveedor_U "
				+ "@PRV_Dependencia=?1, "
				+ "@PRV_NIT=?2, "
				+ "@PRV_Nombre=?3, "
				+ "@PRV_Ciudad=?4, "
				+ "@PRV_Domicilio=?5, "
				+ "@PRV_Telefono=?6, "
				+ "@PRV_Celular_Contacto=?7, "
				+ "@PRV_Nombre_Contacto=?8, "
				+ "@PRV_Fecha_Registro=?9");
		Query query = this.em.createNativeQuery(strQuery,Proveedore.class);
		query.setParameter(1, table.getPRV_Dependencia());
		query.setParameter(2, table.getPRV_NIT());
		query.setParameter(3, table.getPRV_Nombre());
		query.setParameter(4, table.getPRV_Ciudad());
		query.setParameter(5, table.getPRV_Domicilio());
		query.setParameter(6, table.getPRV_Telefono());
		query.setParameter(7, table.getPRV_Celular_Contacto());
		query.setParameter(8, table.getPRV_Nombre_Contacto());
		query.setParameter(9, table.getPRV_Fecha_Registro());
		Proveedore result = (Proveedore) query.getSingleResult();
		return result;
	}
	
	public int[][] getProvedoreCuidad(){
		String str_proveedorexcuidad = "EXEC Proveedores_X_Ciudad";
		Query query = this.em.createNativeQuery(str_proveedorexcuidad);
		Collection<Collection<Object>> data = query.getResultList();
		int[][] result = new int[data.size()][2];
		int row=0;
		for (Object item : data) {
			Object[] fila = ((Object[])item);
			for (int i = 0; i < fila.length; i++) {
				result[row][i] = (Integer)fila[i];
			}
			row++;
		}
		return result;
	}
	public List<ProveedoresModel> getByCiudad(int id_ciudad){
		String str_get_by_ciudad = "EXEC Mant_Proveedor_Ciudad @PRV_Ciudad=?1";
		Query query = this.em.createNativeQuery(str_get_by_ciudad, "archive-map-proveedor")
				.setParameter(1, id_ciudad);
		List<ProveedoresModel> result = query.getResultList();
		return result;
	}
	public List<ProveedoresModel> getByDependencia(short id_dependencia){
		String str_get_by_dependencia = "EXEC Mant_ProveedorByDependencia @Id_Dependencia=?1";
		Query query = this.em.createNativeQuery(str_get_by_dependencia, "archive-map-proveedor")
				.setParameter(1, id_dependencia);
		List<ProveedoresModel> result = query.getResultList();
		return result;
	}
	public ProveedoresModel get(String nitProveedor){
		String str_get_by_dependencia = "EXEC Mant_Proveedor_UQ @Id_Proveedor=?1";
		Query query = this.em.createNativeQuery(str_get_by_dependencia, "archive-map-proveedor")
				.setParameter(1, nitProveedor);
		ProveedoresModel result = (ProveedoresModel)query.getSingleResult();
		return result;
	}
}
