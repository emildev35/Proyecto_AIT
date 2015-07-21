package ait.sistemas.proyecto.seguridad.data.service.Impl;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.seguridad.component.model.PermisoPerfil;
import ait.sistemas.proyecto.seguridad.component.model.UsuarioGridModel;
import ait.sistemas.proyecto.seguridad.data.model.Usuario;

public class UsuarioImpl implements Dao<Usuario> {

	private EntityManagerFactory emf;
	private EntityManager em;

	private Date fecha_Registro;

	public UsuarioImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Seguridad");
		this.em = this.emf.createEntityManager();
		this.fecha_Registro = new Date(new java.util.Date().getTime());
	}

	@Override
	public List<Usuario> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getone(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario add(Usuario table) {
		Usuario result = null;
		String str_Usuario_I = "EXEC Usua_Usuario_I " + "@Id_Usuario=?1, "
				+ "@CI_Empleado=?2, " + "@Fecha_Alta=?3";
		Query query = this.em.createNativeQuery(str_Usuario_I, Usuario.class);
		query.setParameter(1, table.getUSU_Id_Usuario())
				.setParameter(2, table.getUSU_CI_Empleado())
				.setParameter(3, this.fecha_Registro);
		try {
			result = (Usuario) query.getSingleResult();
		} catch (Exception e) {
		}
		return result;
	}

	public int delete(String Codigo) {
		String str_query_grid = "EXEC Usua_Usuario_D @Id_Usuario=?1, @Fecha_baja=?2";
		Query query = this.em.createNativeQuery(str_query_grid);
		query.setParameter(1, Codigo);
		query.setParameter(2, this.fecha_Registro);
		int result = (Integer) query.getSingleResult();
		return result;
	}

	@Override
	public int delete(Usuario table) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Usuario update(Usuario table) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioGridModel> getGridData() {
		String str_query_grid = "EXEC Usua_Usuario_QGrid";
		Query query = this.em.createNativeQuery(str_query_grid, "usuario-grid");
		List<UsuarioGridModel> result = query.getResultList();
		return result;
	}

	public int agregarPermisos(int perfil, String usuario,
			List<PermisoPerfil> permisos) {
		int result = 0;
		String str_otorgar_permisos = "EXEC Estr_OtorgarPerfil_I "
				+ "@Id_Perfil=?1," + "@Identificador=?2, " + "@Id_Usuario=?3, "
				+ "@Fecha_Registro=?4";
		for (PermisoPerfil permisoPerfil : permisos) {

			Query query = this.em.createNativeQuery(str_otorgar_permisos);
			query.setParameter(1, perfil);
			query.setParameter(2, permisoPerfil.getIdentificador());
			query.setParameter(3, usuario);
			query.setParameter(4, this.fecha_Registro);
			result += (Integer) query.getSingleResult();
		}
		return result;
	}

}
