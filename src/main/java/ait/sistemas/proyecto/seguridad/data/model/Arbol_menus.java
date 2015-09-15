package ait.sistemas.proyecto.seguridad.data.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the Arbol_menus database table.
 * 
 */
@Entity
@NamedQuery(name = "Arbol_menus.findAll", query = "SELECT a FROM Arbol_menus a")
public class Arbol_menus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private long AME_Id_Identificador;
	
	private Date AME_Fecha_Registro;
	
	private String AME_Icono;
	
	private int AME_Id_Menus;
	
	private int AME_Id_Opcion;
	
	private int AME_Id_SubMenu;
	
	private int AME_Id_Subsistema;
	
	private short AME_Nivel;
	
	private String AME_NavegacionRedireccion;
	
	private String AME_Nombre;
	
	private short AME_Orden;
	
	private String AME_Programa;
	
	// bi-directional many-to-one association to Arbol_menus
	@ManyToOne
	@JoinColumn(name = "AME_Id_Padre")
	private Arbol_menus arbolMenus;
	
	// bi-directional many-to-one association to Arbol_menus
	@OneToMany(mappedBy = "arbolMenus")
	private List<Arbol_menus> arbolMenuses;
	
	public Arbol_menus() {
	}
	
	public long getAME_Id_Identificador() {
		return this.AME_Id_Identificador;
	}
	
	
	public void setAME_Id_Identificador(long AME_Id_Identificador) {
		this.AME_Id_Identificador = AME_Id_Identificador;
	}
	
	public String getAME_NavegacionRedireccion() {
		return AME_NavegacionRedireccion;
	}
	
	public void setAME_NavegacionRedireccion(String aME_NavegacionRedireccion) {
		AME_NavegacionRedireccion = aME_NavegacionRedireccion;
	}
	
	public Date getAME_Fecha_Registro() {
		return this.AME_Fecha_Registro;
	}
	
	public void setAME_Fecha_Registro(Date AME_Fecha_Registro) {
		this.AME_Fecha_Registro = AME_Fecha_Registro;
	}
	
	public String getAME_Icono() {
		return this.AME_Icono;
	}
	
	public void setAME_Icono(String AME_Icono) {
		this.AME_Icono = AME_Icono;
	}
	
	public int getAME_Id_Menus() {
		return this.AME_Id_Menus;
	}
	
	public void setAME_Id_Menus(int AME_Id_Menus) {
		this.AME_Id_Menus = AME_Id_Menus;
	}
	
	public int getAME_Id_Opcion() {
		return this.AME_Id_Opcion;
	}
	
	public void setAME_Id_Opcion(int AME_Id_Opcion) {
		this.AME_Id_Opcion = AME_Id_Opcion;
	}
	
	public int getAME_Id_SubMenu() {
		return this.AME_Id_SubMenu;
	}
	
	public void setAME_Id_SubMenu(int AME_Id_SubMenu) {
		this.AME_Id_SubMenu = AME_Id_SubMenu;
	}
	
	public int getAME_Id_Subsistema() {
		return this.AME_Id_Subsistema;
	}
	
	public void setAME_Id_Subsistema(int AME_Id_Subsistema) {
		this.AME_Id_Subsistema = AME_Id_Subsistema;
	}
	
	public short getAME_Nivel() {
		return this.AME_Nivel;
	}
	
	public void setAME_Nivel(short AME_Nivel) {
		this.AME_Nivel = AME_Nivel;
	}
	
	public String getAME_Nombre() {
		return this.AME_Nombre;
	}
	
	public void setAME_Nombre(String AME_Nombre) {
		this.AME_Nombre = AME_Nombre;
	}
	
	public short getAME_Orden() {
		return this.AME_Orden;
	}
	
	public void setAME_Orden(short AME_Orden) {
		this.AME_Orden = AME_Orden;
	}
	
	public String getAME_Programa() {
		return this.AME_Programa;
	}
	
	public void setAME_Programa(String AME_Programa) {
		this.AME_Programa = AME_Programa;
	}
	
	public Arbol_menus getArbolMenus() {
		return this.arbolMenus;
	}
	
	public void setArbolMenus(Arbol_menus arbolMenus) {
		this.arbolMenus = arbolMenus;
	}
	
	public List<Arbol_menus> getArbolMenuses() {
		return this.arbolMenuses;
	}
	
	public void setArbolMenuses(List<Arbol_menus> arbolMenuses) {
		this.arbolMenuses = arbolMenuses;
	}
	
	public Arbol_menus addArbolMenus(Arbol_menus arbolMenus) {
		getArbolMenuses().add(arbolMenus);
		arbolMenus.setArbolMenus(this);
		
		return arbolMenus;
	}
	
	public Arbol_menus removeArbolMenus(Arbol_menus arbolMenus) {
		getArbolMenuses().remove(arbolMenus);
		arbolMenus.setArbolMenus(null);
		
		return arbolMenus;
	}
	
	/**
	 * Retorna el Nombre del Menu
	 */
	@Override
	public String toString() {
		return this.getAME_Nombre();
	}
	
}