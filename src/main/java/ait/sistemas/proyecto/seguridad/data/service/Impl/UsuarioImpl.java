package ait.sistemas.proyecto.seguridad.data.service.Impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import ait.sistemas.proyecto.activos.data.dao.Dao;
import ait.sistemas.proyecto.seguridad.component.Auth;
import ait.sistemas.proyecto.seguridad.component.model.PermisoPerfil;
import ait.sistemas.proyecto.seguridad.component.model.PermisosUsuario;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.component.model.UsuarioGridModel;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
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
		String str_Usuario_I = "EXEC Usua_Usuario_I " + "@Id_Usuario=?1, " + "@CI_Empleado=?2, "
				+ "@Fecha_Alta=?3";
		Query query = this.em.createNativeQuery(str_Usuario_I, Usuario.class);
		query.setParameter(1, table.getUSU_Id_Usuario()).setParameter(2, table.getUSU_CI_Empleado())
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
	
	public int agregarPermisos(int perfil, String usuario, List<PermisoPerfil> permisos) {
		int result = 0;
		String str_otorgar_permisos = "EXEC Estr_OtorgarPerfil_I " + "@Id_Perfil=?1," + "@Identificador=?2, "
				+ "@Id_Usuario=?3, " + "@Fecha_Registro=?4";
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
	
	/**
	 * Metodo encarga de propocionar un lista con los datos de los permisos
	 * registrados para un usuario dentro de un menu
	 * 
	 * @param usuario
	 *            Usuario
	 * @param menu
	 *            Identificador del Menu
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PermisosUsuario> listarPermisos(String usuario, long menu) {
		String str_list_permisos = "EXEC Perm_Listar_Permisos_Q " + "@Id_Usuario=?1, " + "@Id_Menu=?2";
		Query query = this.em.createNativeQuery(str_list_permisos, "permiso-usuario").setHint(
				QueryHints.REFRESH, HintValues.TRUE);
		query.setParameter(1, usuario);
		query.setParameter(2, menu);
		List<PermisosUsuario> result = query.getResultList();
		return result;
	}
	
	/**
	 * Este Metodo se encarga de agregar y modificar permisos en la base de
	 * datos para ello elimina de manera temporal todos los permisos de un
	 * usuario espesifico, posterior a ello realiza la insersion de los nuevos
	 * permisos.
	 * 
	 * @param usuario
	 *            Identificador del Usuario
	 * @param permisos
	 *            Lista de permisos que seran asignados
	 * @param id_padre
	 *            El Identificador del Menu padre
	 * @param fechaRegistro
	 *            Fecha de Registro
	 * @return integer
	 */
	public int otortgarPermisos(String usuario, List<PermisosUsuario> permisos, long id_padre,
			java.sql.Date fechaRegistro) {
		
		int result = 0;
		String str_quitar_permisos = "EXEC Perm_QuitarPermiso_P " + "@Identificador=?1, "
				+ "@Id_Usuario=?2, " + "@Fecha_Registro=?3";
		
		Query query = this.em.createNativeQuery(str_quitar_permisos);
		query.setParameter(1, id_padre);
		query.setParameter(2, usuario);
		query.setParameter(3, fechaRegistro);
		result += (Integer) query.getSingleResult();
		
		if (permisos.size() > 0) {
			String str_agregar_permisos = "EXEC Perm_AgregarPermiso_P " + "@Identificador=?1, "
					+ "@Id_Usuario=?2, " + "@Fecha_Registro=?3";
			
			for (PermisosUsuario permisosUsuario : permisos) {
				Query query_agregar = this.em.createNativeQuery(str_agregar_permisos);
				query_agregar.setParameter(1, permisosUsuario.getIdentificador());
				query_agregar.setParameter(2, usuario);
				query_agregar.setParameter(3, fechaRegistro);
				result += (Integer) query_agregar.getSingleResult();
			}
		}
		return result;
	}
	
	public int isNewUser(String id_usuario) {
		String str_new_user = "EXEC Usua_IsNewUser_P @Id_Usuario=?1";
		Query query = this.em.createNativeQuery(str_new_user).setParameter(1, id_usuario);
		return (Integer) query.getSingleResult();
	}
	
	public int addPassword(String usuario, String password) {
		String str_add_Password = "EXEC Perm_AddPassword @Id_Usuario=?1, @Password=?2";
		password = Auth.hash(password);
		Query query = this.em.createNativeQuery(str_add_Password).setParameter(1, usuario)
				.setParameter(2, password);
		return (Integer) query.getSingleResult();
	}
	
	public SessionModel login(String usuario, String password) {
		String str_find_user = "EXEC Usua_FindUser_P @Id_Usuario=?1, @Password=?2";
		Query query = this.em.createNativeQuery(str_find_user, "session-usuario").setParameter(1, usuario)
				.setParameter(2, password);
		return (SessionModel) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Arbol_menus> getMenus(String usuario) {
		List<Arbol_menus> result = new ArrayList<Arbol_menus>();
		String str_get_menus = "EXEC PER_GetPermisos_P @Id_Usuario=?1";
		try {
			Query query = this.em.createNativeQuery(str_get_menus, Arbol_menus.class)
					.setParameter(1, usuario);
			List<Arbol_menus> resultList = (List<Arbol_menus>) query.getResultList();
			result = resultList;
		} catch (Exception e) {
		}
		return result;
	}
	
	public boolean changePassword(String id_usuario, String password) {
		password = Auth.hash(password);
		String str_query = "EXEC Perm_ChangePassword_P @Id_Usuario=?1, @New_Password=?2";
		Query query = this.em.createNativeQuery(str_query);
		query.setParameter(1, id_usuario);
		query.setParameter(2, password);
		int result = (Integer) query.getSingleResult();
		return (result > 0) ? true : false;
	}
}
