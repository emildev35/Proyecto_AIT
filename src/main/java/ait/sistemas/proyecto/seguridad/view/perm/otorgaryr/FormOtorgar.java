package ait.sistemas.proyecto.seguridad.view.perm.otorgaryr;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.PermisosUsuario;
import ait.sistemas.proyecto.seguridad.component.model.UsuarioGridModel;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;

public class FormOtorgar extends GridLayout implements ValueChangeListener, SelectionListener {
	
	private static final long serialVersionUID = 1L;
	
	private ComboBox cb_usuario;
	private ComboBox cbSubSistema;
	private ComboBox cbMenu;
	private ComboBox cb_subMenu;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	private final MenuImpl menuimpl = new MenuImpl();
	private final UsuarioImpl usuarioimpl = new UsuarioImpl();
	
	private long id_padre;
	
	public List<PermisosUsuario> permisos_usuario = new ArrayList<PermisosUsuario>();
	
	public Grid grid_otorgar = new Grid();
	
	final PropertysetItem pitmOpcionPerfil = new PropertysetItem();
	private FieldGroup binderOtorgar;
	
	public FormOtorgar() {
		super(3, 2);
		setMargin(true);
		setWidth("100%");
		
		this.cb_usuario = new ComboBox("Usuario");
		this.cbSubSistema = new ComboBox("Sub-Sistema");
		this.cbMenu = new ComboBox("Menu");
		this.cb_subMenu = new ComboBox("Sub-Menu");
		this.permisos_usuario = new ArrayList<PermisosUsuario>();
		
		cb_usuario.setInputPrompt("Seleccione un Usuario ");
		cb_usuario.setRequired(true);
		cb_usuario.addValidator(new NullValidator("", false));
		cbSubSistema.setInputPrompt("Seleccione un SubSistema");
		cbMenu.setInputPrompt("Seleccione un Menu");
		cb_subMenu.setInputPrompt("Seleccione un Sub Menu");
		
		pitmOpcionPerfil.addItemProperty("usuario", new ObjectProperty<UsuarioGridModel>(
				new UsuarioGridModel()));
		pitmOpcionPerfil.addItemProperty("subSistema", new ObjectProperty<Long>((long) 0));
		pitmOpcionPerfil.addItemProperty("menu", new ObjectProperty<Long>((long) 0));
		pitmOpcionPerfil.addItemProperty("subMenu", new ObjectProperty<Long>((long) 0));
		
		this.binderOtorgar = new FieldGroup(this.pitmOpcionPerfil);
		
		binderOtorgar.bind(this.cb_usuario, "usuario");
		binderOtorgar.bind(this.cbSubSistema, "subSistema");
		binderOtorgar.bind(this.cbMenu, "menu");
		binderOtorgar.bind(this.cb_subMenu, "subMenu");
		
		this.cb_usuario.setWidth("90%");
		this.cbSubSistema.setWidth("90%");
		this.cbMenu.setWidth("90%");
		this.cb_subMenu.setWidth("90%");
		this.grid_otorgar.setWidth("100%");
		
		this.cb_usuario.addValueChangeListener(this);
		this.cbSubSistema.addValueChangeListener(this);
		this.cbMenu.addValueChangeListener(this);
		this.cb_subMenu.addValueChangeListener(this);
		
		fillcb_usuario();
		fillcbSubSistema();
		buildForm();
		Responsive.makeResponsive(this);
	}
	
	private void buildForm() {
		
		this.binderOtorgar.clear();
		
		addComponent(this.cb_usuario, 0, 0, 1, 0);
		addComponent(this.cbSubSistema, 0, 1);
		addComponent(this.cbMenu, 1, 1);
		addComponent(this.cb_subMenu, 2, 1);
	}
	
	private void buildGrid(String usuario, long menu) {
		this.grid_otorgar.removeSelectionListener(this);
		this.grid_otorgar.removeAllColumns();
		this.grid_otorgar.setSelectionMode(SelectionMode.NONE);
		this.permisos_usuario = (List<PermisosUsuario>) usuarioimpl.listarPermisos(usuario, menu);
		if (permisos_usuario.size() > 0) {
			
			BeanItemContainer<PermisosUsuario> bean_otorgar = new BeanItemContainer<PermisosUsuario>(
					PermisosUsuario.class, permisos_usuario);
			this.grid_otorgar.setContainerDataSource(bean_otorgar);
			this.grid_otorgar.setSelectionMode(SelectionMode.MULTI);
			for (PermisosUsuario item : permisos_usuario) {
				if (item.getCheck() == 1) {
					this.grid_otorgar.select(item);
				}
			}
			this.grid_otorgar.removeColumn("check");
			this.grid_otorgar.removeColumn("id_usuario");
			this.grid_otorgar.setContainerDataSource(bean_otorgar);
			this.grid_otorgar.setColumnOrder("identificador", "subSistema", "menu", "subMenu", "opcion");
			this.grid_otorgar.getColumn("identificador").setExpandRatio(1);
			this.grid_otorgar.getColumn("subSistema").setExpandRatio(3);
			this.grid_otorgar.getColumn("menu").setExpandRatio(3);
			this.grid_otorgar.getColumn("subMenu").setExpandRatio(3);
			this.grid_otorgar.getColumn("opcion").setExpandRatio(3);
			this.grid_otorgar.addSelectionListener(this);
		}
	}
	
	private void fillcb_usuario() {
		cb_usuario.removeAllItems();
		cb_usuario.setNullSelectionAllowed(false);
		for (UsuarioGridModel usuario : usuarioimpl.getGridData()) {
			cb_usuario.addItem(usuario);
			cb_usuario.setItemCaption(usuario, usuario.getFullName());
		}
	}
	
	private void fillcbSubSistema() {
		cbSubSistema.removeAllItems();
		cbSubSistema.setNullSelectionAllowed(false);
		
		for (Arbol_menus menu : menuimpl.getallSubsistema()) {
			if (menu.getAME_Programa() == null || menu.getAME_Programa().equals("")) {
				cbSubSistema.addItem(menu.getAME_Id_Identificador());
				cbSubSistema.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
			} else {
				cbSubSistema.addItem(menu.getAME_Id_Identificador());
				cbSubSistema.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
				cbSubSistema.setItemIcon(menu.getAME_Id_Identificador(), FontAwesome.WINDOWS);
			}
		}
	}
	
	private void fillcbMenu(long subSistema) {
		cbMenu.removeAllItems();
		cbMenu.setNullSelectionAllowed(false);
		for (Arbol_menus menu : menuimpl.getallMenu(subSistema)) {
			if (menu.getAME_Programa() == null || menu.getAME_Programa().equals("")) {
				cbMenu.addItem(menu.getAME_Id_Identificador());
				cbMenu.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
			} else {
				cbMenu.addItem(menu.getAME_Id_Identificador());
				cbMenu.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
				cbMenu.setItemIcon(menu.getAME_Id_Identificador(), FontAwesome.WINDOWS);
			}
		}
	}
	
	private void fillcb_subMenu(long menu) {
		cb_subMenu.removeAllItems();
		cb_subMenu.setNullSelectionAllowed(false);
		for (Arbol_menus omenu : menuimpl.getallMenu(menu)) {
			if (omenu.getAME_Programa() == null || omenu.getAME_Programa().equals("")) {
				cb_subMenu.addItem(omenu.getAME_Id_Identificador());
				cb_subMenu.setItemCaption(omenu.getAME_Id_Identificador(), omenu.getAME_Nombre());
			} else {
				cb_subMenu.addItem(omenu.getAME_Id_Identificador());
				cb_subMenu.setItemCaption(omenu.getAME_Id_Identificador(), omenu.getAME_Nombre());
				cb_subMenu.setItemIcon(omenu.getAME_Id_Identificador(), FontAwesome.WINDOWS);
			}
		}
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (this.cb_usuario.getValue() != null) {
			this.cb_subMenu.setEnabled(true);
			this.cbSubSistema.setEnabled(true);
			this.cbMenu.setEnabled(true);
			try {
				UsuarioGridModel usuario = (UsuarioGridModel) this.cb_usuario.getValue();
				if (event.getProperty().getValue() == this.cbSubSistema.getValue()
						&& this.cbSubSistema.getValue() != null) {
					cb_subMenu.removeAllItems();
					fillcbMenu((long) this.cbSubSistema.getValue());
				}
				if (event.getProperty().getValue() == this.cbMenu.getValue()
						&& this.cbMenu.getValue() != null) {
					fillcb_subMenu((long) this.cbMenu.getValue());
				}
				if (event.getProperty().getValue() == this.cb_subMenu.getValue()
						&& this.cbSubSistema.getValue() != null) {
					
				}
				
				if (this.cbSubSistema.getValue() == null) {
					buildGrid(usuario.getId(), 0);
					this.id_padre = 0;
				} else {
					buildGrid(usuario.getId(), ((long) event.getProperty().getValue()));
					this.id_padre = (long) event.getProperty().getValue();
				}
				
			} catch (Exception e) {
				
			}
		} else {
			this.cb_subMenu.setEnabled(false);
			this.cbSubSistema.setEnabled(false);
			this.cbMenu.setEnabled(false);
		}
	}
	
	public List<PermisosUsuario> getListMenu() {
		return this.permisos_usuario;
	}
	
	public int getIdPerfil() {
		return (Integer) this.cb_usuario.getValue();
	}
	
	public long getIdPadre() {
		return this.id_padre;
	}
	
	public void clean() {
		this.permisos_usuario.clear();
		buildForm();
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public boolean validate() {
		try {
			this.binderOtorgar.commit();
			this.mensajes.add(new BarMessage("Fomulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_usuario.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_usuario.getCaption(), Messages.EMPTY_MESSAGE));
			}
			return false;
		}
	}
	
	@Override
	public void select(SelectionEvent event) {
		
	}
	
	public List<PermisosUsuario> getPermisos() {
		List<PermisosUsuario> result = new ArrayList<PermisosUsuario>();
		for (Object item : grid_otorgar.getSelectedRows()) {
			PermisosUsuario permiso = (PermisosUsuario) item;
			result.add(permiso);
		}
		return result;
	}
	
	public String getUsuario() {
		return ((UsuarioGridModel) this.cb_usuario.getValue()).getId();
		
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
}
