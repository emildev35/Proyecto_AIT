package ait.sistemas.proyecto.seguridad.data.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ait.sistemas.proyecto.seguridad.data.dao.Dao;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.model.Perfil;

/**
 * Clase de Servicio con la se implementa el CRUD de la
 * tabla Arbol_menus
 * @author franzemil
 *
 */
public class MenuImpl implements Dao<Arbol_menus> {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	
	public MenuImpl() {
		this.emf = Persistence.createEntityManagerFactory("AIT-Seguridad");
		this.em = this.emf.createEntityManager();
	}
	
	/**
	 * Este Metodo Retorna dentro de un List<Arbol_menus> todos los registros
	 * de la Base de Datos
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Arbol_menus> getall() {
		Query query = em.createQuery("select m from Arbol_menus m");
		List<Arbol_menus> resultlist = query.getResultList();
		return resultlist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Arbol_menus> getallSubsistema() {
		Query query = em.createNativeQuery("exec Estr_SubSistema_Q", Arbol_menus.class);
		List<Arbol_menus> resultlist = query.getResultList();
		return resultlist;
	}
	@SuppressWarnings("unchecked")
	public List<Arbol_menus> getallMenu() {
		Query query = em.createNativeQuery("EXEC  Estr_Menu_Q", Arbol_menus.class);
		List<Arbol_menus> resultlist = query.getResultList();
		return resultlist;
	}
	@SuppressWarnings("unchecked")
	public List<Arbol_menus> getallSubMenu() {
		Query query = em.createNativeQuery("EXEC  Estr_SubMenus_Q", Arbol_menus.class);
		List<Arbol_menus> resultlist = query.getResultList();
		return resultlist;
	}
	@SuppressWarnings("unchecked")
	public List<Arbol_menus> getallOpcion() {
		Query query = em.createNativeQuery("EXEC  Estr_Opcion_Q", Arbol_menus.class);
		List<Arbol_menus> resultlist = query.getResultList();
		return resultlist;
	}
	public int deleteSubsistema(long identifcador){
		Query query = em.createNativeQuery("Estr_SubSistema_D @AME_Id_Identificador=?1");
		query.setParameter(1, identifcador);
		return (Integer)query.getSingleResult();	
	}

	@Override
	public Arbol_menus getone(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Arbol_menus addSubSistema(Arbol_menus table) {
		// TODO Auto-generated method stub
		String strQuery = String.format("exec  Estr_Subsistema_I "
				+ "@AME_Id_Identificador = ?1, "
				+ "@AME_Id_Subsistema = ?2, "
				+ "@AME_Nombre = ?3, "
				+ "@AME_Nivel  = NULL, "
				+ "@AME_Orden  = NULL, "
				+ "@AME_Icono  = ?4, "
				+ "@AME_Programa = ?5, "
				+ "@AME_Fecha_Registro  = ?6");
		Query query = this.em.createNativeQuery(strQuery, Arbol_menus.class);
		query.setParameter(1, table.getAME_Id_Identificador());
		query.setParameter(2, table.getAME_Id_Subsistema());
		query.setParameter(3, table.getAME_Nombre());
		query.setParameter(4, table.getAME_Icono());
		query.setParameter(5, table.getAME_Programa());
		query.setParameter(6, table.getAME_Fecha_Registro());
		Arbol_menus result = (Arbol_menus) query.getSingleResult();
		return result;
	}
	
	
	public Arbol_menus addMenu(Arbol_menus table) {
		// TODO Auto-generated method stub
		String strQuery = String.format("exec  Estr_Menu_I "
				+ "@AME_Id_Identificador = ?1, "
				+ "@AME_Id_Subsistema = ?2, "
				+ "@AME_Id_Menus = ?3, "
				+ "@AME_Nombre = ?4, "
				+ "@AME_Nivel  = NULL, "
				+ "@AME_Orden  = NULL, "
				+ "@AME_Icono  = ?5, "
				+ "@AME_Programa = ?6, "
				+ "@AME_Fecha_Registro  = ?7");
		Query query = this.em.createNativeQuery(strQuery, Arbol_menus.class);
		query.setParameter(1, table.getAME_Id_Identificador());
		query.setParameter(2, table.getAME_Id_Subsistema());
		query.setParameter(3, table.getAME_Id_Menus());
		query.setParameter(4, table.getAME_Nombre());
		query.setParameter(5, table.getAME_Icono());
		query.setParameter(6, table.getAME_Programa());	
		query.setParameter(7, table.getAME_Fecha_Registro());
		Arbol_menus result = (Arbol_menus) query.getSingleResult();
		return result;
	}
	public Arbol_menus addSubMenu(Arbol_menus table) {
		// TODO Auto-generated method stub
		String strQuery = String.format("exec  Estr_SubMenu_I "
				+ "@AME_Id_Identificador = ?1, "
				+ "@AME_Id_Subsistema = ?2, "
				+ "@AME_Id_Menus = ?3, "
				+ "@AME_Id_SubMenu = ?4, "
				+ "@AME_Nombre = ?5, "
				+ "@AME_Nivel  = NULL, "
				+ "@AME_Orden  = NULL, "
				+ "@AME_Icono  = ?6, "
				+ "@AME_Programa = ?7, "
				+ "@AME_Fecha_Registro  = ?8");
		Query query = this.em.createNativeQuery(strQuery, Arbol_menus.class);
		query.setParameter(1, table.getAME_Id_Identificador());
		query.setParameter(2, table.getAME_Id_Subsistema());
		query.setParameter(3, table.getAME_Id_Menus());
		query.setParameter(4, table.getAME_Id_SubMenu());
		query.setParameter(5, table.getAME_Nombre());
		query.setParameter(6, table.getAME_Icono());
		query.setParameter(7, table.getAME_Programa());	
		query.setParameter(8, table.getAME_Fecha_Registro());
		Arbol_menus result = (Arbol_menus) query.getSingleResult();
		return result;
	}
	public Arbol_menus addOpcion(Arbol_menus table) {
		// TODO Auto-generated method stub
		String strQuery = String.format("exec  Estr_Opcion_I "
				+ "@AME_Id_Identificador = ?1, "
				+ "@AME_Id_Subsistema = ?2, "
				+ "@AME_Id_Menus = ?3, "
				+ "@AME_Id_SubMenu = ?4, "
				+ "@AME_Id_Opcion = ?5, "
				+ "@AME_Nombre = ?6, "
				+ "@AME_Nivel  = NULL, "
				+ "@AME_Orden  = NULL, "
				+ "@AME_Icono  = ?7, "
				+ "@AME_Programa = ?8, "
				+ "@AME_Fecha_Registro  = ?9");
		Query query = this.em.createNativeQuery(strQuery, Arbol_menus.class);
		query.setParameter(1, table.getAME_Id_Identificador());
		query.setParameter(2, table.getAME_Id_Subsistema());
		query.setParameter(3, table.getAME_Id_Menus());
		query.setParameter(4, table.getAME_Id_SubMenu());
		query.setParameter(5, table.getAME_Id_Opcion());
		query.setParameter(6, table.getAME_Nombre());
		query.setParameter(7, table.getAME_Icono());
		query.setParameter(8, table.getAME_Programa());	
		query.setParameter(9, table.getAME_Fecha_Registro());
		Arbol_menus result = (Arbol_menus) query.getSingleResult();
		return result;
	}
	public Arbol_menus updatesubmenu(Arbol_menus table) {
		// TODO Auto-generated method stub
		String strQuery = String.format("exec  Estr_SubMenu_U "
				+ "@AME_Id_Identificador = ?1, "
				+ "@AME_Id_Subsistema = ?2, "
				+ "@AME_Id_Menu = ?3, "
				+ "@AME_Id_subMenu = ?4, "
				+ "@AME_Nombre = ?5, "
				+ "@AME_Nivel  = NULL, "
				+ "@AME_Orden  = NULL, "
				+ "@AME_Icono  = ?6, "
				+ "@AME_Programa = ?7, "
				+ "@AME_Fecha_Registro  = ?8");
		Query query = this.em.createNativeQuery(strQuery, Arbol_menus.class);
		query.setParameter(1, table.getAME_Id_Identificador());
		query.setParameter(2, table.getAME_Id_Subsistema());
		query.setParameter(3, table.getAME_Id_Menus());
		query.setParameter(4, table.getAME_Id_SubMenu());
		query.setParameter(5, table.getAME_Nombre());
		query.setParameter(6, table.getAME_Icono());
		query.setParameter(7, table.getAME_Programa());
		query.setParameter(8, table.getAME_Fecha_Registro());
		Arbol_menus result = (Arbol_menus) query.getSingleResult();
		return result;
	}
	
	public Arbol_menus updatemenu(Arbol_menus table) {
		// TODO Auto-generated method stub
		String strQuery = String.format("exec  Estr_Menu_U "
				+ "@AME_Id_Identificador = ?1, "
				+ "@AME_Nombre = ?2, "
				+ "@AME_Nivel  = NULL, "
				+ "@AME_Orden  = NULL, "
				+ "@AME_Icono  = ?3, "
				+ "@AME_Programa = ?4, "
				+ "@AME_Fecha_Registro  = ?5");
		Query query = this.em.createNativeQuery(strQuery, Arbol_menus.class);
		query.setParameter(1, table.getAME_Id_Identificador());
		query.setParameter(2, table.getAME_Nombre());
		query.setParameter(3, table.getAME_Icono());
		query.setParameter(4, table.getAME_Programa());
		query.setParameter(5, table.getAME_Fecha_Registro());
		Arbol_menus result = (Arbol_menus) query.getSingleResult();
		return result;
	}
	public Arbol_menus updateSubSistema(Arbol_menus table) {
		// TODO Auto-generated method stub
		String strQuery = String.format("exec  Estr_SubSistema_U "
				+ "@AME_Id_Identificador = ?1, "
				+ "@AME_Nombre = ?2, "
				+ "@AME_Nivel  = NULL, "
				+ "@AME_Orden  = NULL, "
				+ "@AME_Icono  = ?3, "
				+ "@AME_Programa = ?4, "
				+ "@AME_Fecha_Registro  = ?5");
		Query query = this.em.createNativeQuery(strQuery, Arbol_menus.class);
		query.setParameter(1, table.getAME_Id_Identificador());
		query.setParameter(2, table.getAME_Nombre());
		query.setParameter(3, table.getAME_Icono());
		query.setParameter(4, table.getAME_Programa());
		query.setParameter(5, table.getAME_Fecha_Registro());
		Arbol_menus result = (Arbol_menus) query.getSingleResult();
		return result;
	}
	@Override
	public Arbol_menus add(Arbol_menus table) {
		// TODO Auto-generated method stub
		String strQuery = String.format("EXEC EstrArbol_menusI  "
				+ "@AME_Id_Identificador=?1, "
				+ "@AME_Id_Subsistema int=?2, "
				+ "@AME_Id_Menus=?3, "
				+ "@AME_Id_SubMenu=?4, "
				+ "@AME_Id_Opcion=?5, "
				+ "@AME_Nombre=?6, "
				+ "@AME_Nivel=?7, "
				+ "@AME_Orden=?8, "
				+ "@AME_Id_Padre=?9, "
				+ "@AME_Icono=?10, "
				+ "@AME_Programa=?11, "
				+ "@AME_Fecha_Registro=?12");
		Query query = this.em.createNativeQuery(strQuery, Perfil.class);
		query.setParameter(1, table.getAME_Id_Identificador());
		query.setParameter(2, table.getAME_Id_Subsistema());
		query.setParameter(3, table.getAME_Id_Menus());
		query.setParameter(4, table.getAME_Id_SubMenu());
		query.setParameter(5, table.getAME_Id_Opcion());
		query.setParameter(6, table.getAME_Nombre());
		query.setParameter(7, table.getAME_Nivel());
		query.setParameter(8, table.getAME_Orden());
		query.setParameter(9, table.getArbolMenus().getAME_Id_Identificador());
		query.setParameter(10, table.getAME_Id_Identificador());
		query.setParameter(11, table.getAME_Programa());
		query.setParameter(12, table.getAME_Fecha_Registro());
		Arbol_menus result = (Arbol_menus) query.getSingleResult();
		return result;
	}

	@Override
	public int delete(Arbol_menus table) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Arbol_menus update(Arbol_menus table) {
		// TODO Auto-generated method stub
		return null;
	}
	public String generateId(){
		long result = 1;
		Query query = this.em
				.createQuery("select max(m.AME_Id_Identificador) from Arbol_menus m");
		result += (Long) query.getSingleResult();
		return String.valueOf(result);
	}
	public String generateSubSistemaId(){
		int result = 1;
		Query query = this.em
				.createQuery("select max(m.AME_Id_Subsistema) from Arbol_menus m");
		result += (int) query.getSingleResult();
		return String.valueOf(result);
	}
	public String generateMenuId(long subSistema){
		int result = 1;
		Query query = em.createNativeQuery("EXEC Estr_Menu_Get_Id @AME_Id_Subsistema=?1");
		query.setParameter(1, subSistema);
		result += (Integer)query.getSingleResult();	
		return String.valueOf(result);
	}
	public String generateSubMenuId(int subsistema, int menu ){
		int result = 1;
		Query query = em.createNativeQuery("EXEC Estr_SubMenu_Get_Id "
				+ " @AME_Id_Subsistema=?1,"
				+ " @AME_Id_Menus=?2");
		query.setParameter(1, subsistema);
		query.setParameter(2, menu);
		if(query.getSingleResult()!=null){
		result += (Integer)query.getSingleResult();	
		}
		return String.valueOf(result);
	}
	
	public String generateOpcionId(int subsistema, int menu, int submenu ){
		int result = 1;
		Query query = em.createNativeQuery("EXEC Estr_Opcion_Get_Id "
				+ " @AME_Id_Subsistema=?1,"
				+ " @AME_Id_Menus=?2,   "
				+ "	@AME_Id_Submenu=?3");
		query.setParameter(1, subsistema);
		query.setParameter(2, menu);
		query.setParameter(3, submenu);
		if(query.getSingleResult()!=null){
		result += (Integer)query.getSingleResult();	
		}
		return String.valueOf(result);
	}
	public String generateOpcionId(int SubMenu){
		int result = 1;
		Query query = this.em
				.createQuery("select max(m.AME_Id_Opcion) from Arbol_menus m");
		result += (int) query.getSingleResult();
		return result+"";
	}
	@SuppressWarnings("unchecked")
	public  List<Arbol_menus> getallMenu(long padre){
		Query query = this.em
				.createNativeQuery("EXEC Estr_Menu_HIjos_Q @Id_Padre = ?1 ", Arbol_menus.class);
		query.setParameter(1, padre);
		List<Arbol_menus> result = query.getResultList();
		return result;
	}
	@SuppressWarnings("unchecked")
	public  List<Arbol_menus> generateIdSubmenu(long padre){
		Query query = this.em
				.createQuery("select max(m.AME_Id_Menus) from Arbol_menus m "
						+ "where m.AME_Id_Subsistema = ?1 and m.AME_Id_Opcion = 0", Arbol_menus.class);
		query.setParameter(1, padre);
		List<Arbol_menus> result = query.getResultList();
		return result;
	}
}
