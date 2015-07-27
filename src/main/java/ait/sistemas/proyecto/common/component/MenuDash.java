package ait.sistemas.proyecto.common.component;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


/**
 * Componente que proporciona una barra horizontal de navegacion
 * en la cual que encuentran componentes como:
 * 	Informacion del Usuario.
 * 	Menu
 * @author franzemil
 *
 */
public class MenuDash extends CustomComponent implements Serializable, ItemClickListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Este MenuItem se utiliza al generar en MenuInfo del Usuario
	 */
    private MenuItem settingsItem;


    private Tree Treemenu;
    private List<Arbol_menus> menuelements;
	private UsuarioImpl usuarioimpl;
	
	public MenuDash() {
		// TODO Auto-generated constructor stub
			
		this.Treemenu = new Tree();	
		this.usuarioimpl = new UsuarioImpl(); 
		this.menuelements = new ArrayList<Arbol_menus>();
		ObtenerDatos();
		Treemenu.addItemClickListener(this);
		addStyleName("valo-menu");
		addStyleName("ait-menu-full");
		setCompositionRoot(builComponent());
		Responsive.makeResponsive(this);
	}

	private Component builComponent() {
		// TODO Auto-generated method stub
		CssLayout mainContent = new CssLayout();
		mainContent.addStyleName("ait-menu");
		mainContent.addComponent(UserInfo());
		mainContent.addComponent(builMenuItems());
		return mainContent;
	}


	private Component builMenuItems() {
		VerticalLayout menuContent = new VerticalLayout();
		//menuContent.setMargin(true);
		menuContent.addStyleName("ait-menu-items");
		for (Arbol_menus menu : this.menuelements) {
			Treemenu.addItem(menu);
		}
		for (Arbol_menus menu : this.menuelements) {
			if(menu.getArbolMenus()!=null){
				Treemenu.setParent(menu, menu.getArbolMenus());
			}
		}
		menuContent.addComponent(this.Treemenu);
		return menuContent;
	}

	@SuppressWarnings("serial")
	private Component UserInfo() {

		final MenuBar settings = new MenuBar();
	    settings.addStyleName("user-menu");
	    
	    String usuario = ((SessionModel)UI.getCurrent().getSession().getAttribute("user")).getFull_name();
		settingsItem = settings.addItem("", new ThemeResource("img/profile-pic-300px.jpg"), null);    
		settingsItem.setText(usuario);
        settingsItem.addItem("Cerrar Session", new Command() {
		    @Override
		    public void menuSelected(final MenuItem selectedItem) {
		        
		    }
		});
		return settings;

	}
	/**
	 * Este evento permite ocultar y hacer visible el menu cuando
	 * la definicion de pantalla es pequeña	
	 * @param style ESte recieve in 
	 */
	public void show(String style) {
		// TODO Auto-generated method stub
		if (getCompositionRoot().getStyleName().contains(style)) {
			getCompositionRoot().removeStyleName(style);
		} else {
			getCompositionRoot().addStyleName(style);
		}
		Notification.show(getCompositionRoot().getStyleName());
	}

	
	public void ObtenerDatos(){
		
		String usuario = ((SessionModel)UI.getCurrent().getSession().getAttribute("user")).getId();
		
        for (Arbol_menus menu : this.usuarioimpl.getMenus(usuario)){
        	this.menuelements.add(menu);
        	ObtenerTodos(menu);
        }
	}
	public void ObtenerTodos(Arbol_menus menu){    
    	if (menu.getArbolMenus() != null) {				
    		this.menuelements.add(menu.getArbolMenus());
    		ObtenerTodos(menu.getArbolMenus());
		}       
	}

	@Override
	public void itemClick(ItemClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}
