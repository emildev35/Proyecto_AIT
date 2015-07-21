package ait.sistemas.proyecto.seguridad.view.perm.otorgaryr;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.FullMenu;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.model.Perfil;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;
import ait.sistemas.proyecto.seguridad.data.service.Impl.PerfilImpl;
import ait.sistemas.proyecto.seguridad.view.usua.opcionperfil.GridOpcionPerfil;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;

public class FormOtorgar extends GridLayout implements ValueChangeListener{

	private static final long serialVersionUID = 1L;

	private ComboBox cbPerfil;
	private ComboBox cbSubSistema;
	private ComboBox cbMenu;
	private ComboBox cbSubMenu;
	private ComboBox cbOpcion;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	private final MenuImpl menuimpl = new MenuImpl();
	private final PerfilImpl perfilimpl = new PerfilImpl();
	
	public List<FullMenu> fullmenus;
	
	public GridOpcionPerfil gridOpcionPerfil;
	
	final PropertysetItem pitmOpcionPerfil = new PropertysetItem();
	private FieldGroup binderOpcionPerfil;
	
	public FormOtorgar() {
		super(4, 2);
		setMargin(true);
		setWidth("100%");
		
		this.cbPerfil = new ComboBox("Perfil");
		this.cbSubSistema = new ComboBox("Sub-Sistema");
		this.cbMenu = new ComboBox("Menu");
		this.cbSubMenu = new ComboBox("Sub-Menu");
		this.cbOpcion = new ComboBox("Opcion");
		this.fullmenus = new ArrayList<FullMenu>();
		this.gridOpcionPerfil = new GridOpcionPerfil();
		
		this.cbSubSistema.addValueChangeListener(this);
		this.cbMenu.addValueChangeListener(this);
		this.cbSubMenu.addValueChangeListener(this);
		this.cbOpcion.addValueChangeListener(this);
		
		cbPerfil.setInputPrompt("Seleccione un Perfil");
		cbPerfil.setRequired(true);
		cbPerfil.addValidator(new NullValidator("", false));
		cbSubSistema.setInputPrompt("Seleccione un SubSistema");
		cbSubSistema.setRequired(true);
		cbSubSistema.addValidator(new NullValidator("", false));
		cbMenu.setInputPrompt("Seleccione un Menu");
		cbSubMenu.setInputPrompt("Seleccione un Sub Menu");
		cbOpcion.setInputPrompt("Seleccione una Opcion");
		
		
		pitmOpcionPerfil.addItemProperty("perfil", new ObjectProperty<Integer>(0));
		pitmOpcionPerfil.addItemProperty("subSistema", new ObjectProperty<Long>((long)0));
		pitmOpcionPerfil.addItemProperty("menu", new ObjectProperty<Long>((long)0));
		pitmOpcionPerfil.addItemProperty("subMenu", new ObjectProperty<Long>((long)0));
		pitmOpcionPerfil.addItemProperty("opcion", new ObjectProperty<Long>((long)0));
	
		
		this.binderOpcionPerfil = new FieldGroup(this.pitmOpcionPerfil);
		
		binderOpcionPerfil.bind(this.cbPerfil, "perfil");
		binderOpcionPerfil.bind(this.cbSubSistema, "subSistema");
		binderOpcionPerfil.bind(this.cbMenu, "menu");
		binderOpcionPerfil.bind(this.cbSubMenu, "subMenu");
		binderOpcionPerfil.bind(this.cbOpcion, "opcion");

		
		this.cbPerfil.setWidth("90%");
		this.cbSubSistema.setWidth("90%");
		this.cbMenu.setWidth("90%");
		this.cbSubMenu.setWidth("90%");
		this.cbOpcion.setWidth("90%");

	
		fillcbPerfil();
		fillcbSubSistema();
		buildForm();
		buildGrid();
		Responsive.makeResponsive(this);
	}

	private void buildForm() {
		this.binderOpcionPerfil.clear();
		addComponent(this.cbPerfil, 0, 0, 1, 0);
		addComponent(this.cbSubSistema, 0, 1);
		addComponent(this.cbMenu, 1, 1);
		addComponent(this.cbSubMenu, 2, 1);
		addComponent(this.cbOpcion, 3, 1);
	}
	
	private void buildGrid() {
		this.gridOpcionPerfil.setWidth("100%");
		BeanItemContainer<FullMenu> beanOpcionPerfil = new BeanItemContainer<FullMenu>(FullMenu.class);
		beanOpcionPerfil.addAll(this.fullmenus);
		this.gridOpcionPerfil.setContainerDataSource(beanOpcionPerfil);
		this.gridOpcionPerfil.setColumnOrder("identificador", "subSistema", "menu", "subMenu", "opcion");
		this.gridOpcionPerfil.getColumn("identificador").setExpandRatio(1);
		this.gridOpcionPerfil.getColumn("subSistema").setExpandRatio(3);
		this.gridOpcionPerfil.getColumn("menu").setExpandRatio(3);
		this.gridOpcionPerfil.getColumn("subMenu").setExpandRatio(3);
		this.gridOpcionPerfil.getColumn("opcion").setExpandRatio(3);
	}

	private void fillcbPerfil() {
		cbPerfil.removeAllItems();
		cbPerfil.setNullSelectionAllowed(false);
		for (Perfil perfil : perfilimpl.getall() )
		{
			cbPerfil.addItem(perfil.getPRF_Id_Perfil());
			cbPerfil.setItemCaption(perfil.getPRF_Id_Perfil(), perfil.getPRF_Nombre_Perfil());
		}
	}

	private void fillcbSubSistema() {
		cbSubSistema.removeAllItems();
		cbSubSistema.setNullSelectionAllowed(false);

		for (Arbol_menus menu : menuimpl.getallSubsistema())
		{
			if(menu.getAME_Programa() == null || menu.getAME_Programa().equals("")){
				cbSubSistema.addItem(menu.getAME_Id_Identificador() );
				cbSubSistema.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
			}else{
				cbSubSistema.addItem(menu.getAME_Id_Identificador() );
				cbSubSistema.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
				cbSubSistema.setItemIcon(menu.getAME_Id_Identificador(), FontAwesome.WINDOWS);
			}
		}
	}

	private void fillcbMenu(long subSistema) {
		cbMenu.removeAllItems();
		cbMenu.setNullSelectionAllowed(false);
		for (Arbol_menus menu : menuimpl.getallMenu(subSistema))
		{
			if(menu.getAME_Programa() == null || menu.getAME_Programa().equals("")){
				cbMenu.addItem(menu.getAME_Id_Identificador());
				cbMenu.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
			}else{
				cbMenu.addItem(menu.getAME_Id_Identificador());
				cbMenu.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
				cbMenu.setItemIcon(menu.getAME_Id_Identificador(), FontAwesome.WINDOWS);
			}
		}
	}
	private void fillcbSubMenu(long menu) {
		cbSubMenu.removeAllItems();
		cbSubMenu.setNullSelectionAllowed(false);
		for (Arbol_menus omenu : menuimpl.getallMenu(menu))
		{
			if(omenu.getAME_Programa() == null || omenu.getAME_Programa().equals("")){
				cbSubMenu.addItem(omenu.getAME_Id_Identificador());
				cbSubMenu.setItemCaption(omenu.getAME_Id_Identificador(), omenu.getAME_Nombre());
			}else{
				cbSubMenu.addItem(omenu.getAME_Id_Identificador());
				cbSubMenu.setItemCaption(omenu.getAME_Id_Identificador(), omenu.getAME_Nombre());
				cbSubMenu.setItemIcon(omenu.getAME_Id_Identificador(), FontAwesome.WINDOWS);
			}
		}
	}
	private void fillcbOpcion(long subMenu) {
		cbOpcion.removeAllItems();
		cbOpcion.setNullSelectionAllowed(false);
		cbOpcion.setInputPrompt("Seleccione una Opcion");
		for (Arbol_menus menu : menuimpl.getallMenu(subMenu))
		{	
			cbOpcion.addItem(menu.getAME_Id_Identificador());
			cbOpcion.setItemCaption(menu.getAME_Id_Identificador(), menu.getAME_Nombre());
			cbOpcion.setItemIcon(menu.getAME_Id_Identificador(), FontAwesome.WINDOWS);
		}
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		try{
		if(event.getProperty().getValue() == this.cbSubSistema.getValue() && this.cbSubSistema.getValue() != null ){
			cbOpcion.removeAllItems();
			cbSubMenu.removeAllItems();
			fillcbMenu((long)this.cbSubSistema.getValue());
		}
		if(event.getProperty().getValue() == this.cbMenu.getValue() && this.cbMenu.getValue() != null ){
			cbOpcion.removeAllItems();
			fillcbSubMenu((long)this.cbMenu.getValue());
		}
		if(event.getProperty().getValue() == this.cbSubMenu.getValue() && this.cbSubSistema.getValue() != null ){
			fillcbOpcion((long)this.cbSubMenu.getValue());
		}
		if(event.getProperty().getValue() != null && menuimpl.isProgram((long)event.getProperty().getValue())){	
			this.fullmenus.add(menuimpl.getFullData((long)event.getProperty().getValue()));	
			buildGrid();
		}
		}catch(Exception e){}
	}

	public List<FullMenu> getListMenu() {
		return this.fullmenus;
	}

	public int getIdPerfil() {	
		return (Integer)this.cbPerfil.getValue();
	}

	public void clean() {
		this.fullmenus.clear();
		buildGrid();
		buildForm();
	}	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public boolean validate(){
		try{
			this.binderOpcionPerfil.commit();
			this.mensajes.add(new BarMessage("Fomulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.cbPerfil.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cbPerfil.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.cbSubSistema.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cbSubSistema.getCaption(),  Messages.EMPTY_MESSAGE));
			}	
			return false;
		}		
	}

}
