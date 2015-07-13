package ait.sistemas.proyecto.seguridad.data.model;

import java.io.Serializable;

import javax.persistence.*;

import ait.sistemas.proyecto.seguridad.data.model.pk.Log_ErrorePK;


/**
 * The persistent class for the Log_Errores database table.
 * 
 */
@Entity
@Table(name="Log_Errores")
@NamedQuery(name="Log_Errore.findAll", query="SELECT l FROM Log_Errore l")
public class Log_Errore implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Log_ErrorePK id;

	private int LGE_Id_Menu;

	private int LGE_Id_Opcion;

	private int LGE_Id_SubMenu;

	private int LGE_Id_Subsistema;

	private String LGE_Mensaje_Excepcion;

	public Log_Errore() {
	}

	public Log_ErrorePK getId() {
		return this.id;
	}

	public void setId(Log_ErrorePK id) {
		this.id = id;
	}

	public int getLGE_Id_Menu() {
		return this.LGE_Id_Menu;
	}

	public void setLGE_Id_Menu(int LGE_Id_Menu) {
		this.LGE_Id_Menu = LGE_Id_Menu;
	}

	public int getLGE_Id_Opcion() {
		return this.LGE_Id_Opcion;
	}

	public void setLGE_Id_Opcion(int LGE_Id_Opcion) {
		this.LGE_Id_Opcion = LGE_Id_Opcion;
	}

	public int getLGE_Id_SubMenu() {
		return this.LGE_Id_SubMenu;
	}

	public void setLGE_Id_SubMenu(int LGE_Id_SubMenu) {
		this.LGE_Id_SubMenu = LGE_Id_SubMenu;
	}

	public int getLGE_Id_Subsistema() {
		return this.LGE_Id_Subsistema;
	}

	public void setLGE_Id_Subsistema(int LGE_Id_Subsistema) {
		this.LGE_Id_Subsistema = LGE_Id_Subsistema;
	}

	public String getLGE_Mensaje_Excepcion() {
		return this.LGE_Mensaje_Excepcion;
	}

	public void setLGE_Mensaje_Excepcion(String LGE_Mensaje_Excepcion) {
		this.LGE_Mensaje_Excepcion = LGE_Mensaje_Excepcion;
	}

}