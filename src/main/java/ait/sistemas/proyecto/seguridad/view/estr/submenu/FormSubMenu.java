package ait.sistemas.proyecto.seguridad.view.estr.submenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.ComboBoxIcons;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormSubMenu extends GridLayout implements Property.ValueChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	public TextField txt_identificador;
	public TextField txt_id_submenu;
	public TextField txt_nombre_menu;
	public TextField txt_nombre_programa;
	public ComboBox cbSubsistema;
	public ComboBox cbMenus;
	public ComboBoxIcons cb_icons;
	
	private List<BarMessage> mensajes;
	
	final private MenuImpl menuimpl = new MenuImpl();
	
	final PropertysetItem pitmSubMenu = new PropertysetItem();
	private FieldGroup binderSubMenu;
	
	public FormSubMenu() {
		setColumns(4);
		setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		
		this.mensajes = new ArrayList<BarMessage>();
		this.txt_identificador = new TextField("Identificador");
		this.txt_id_submenu = new TextField("Codigo");
		this.txt_nombre_menu = new TextField("Nombre del Sub-Menu");
		this.txt_nombre_programa = new TextField("URL del Programa");
		this.cb_icons = new ComboBoxIcons("Seleccione Icono");
		this.cbSubsistema = new ComboBox("Sub-Sistema");
		this.cbMenus = new ComboBox("Menus");
		this.cbSubsistema.addValueChangeListener(this);
		this.cbMenus.addValueChangeListener(this);
		
		pitmSubMenu.addItemProperty("identificador", new ObjectProperty<Integer>(0));
		pitmSubMenu.addItemProperty("id_submenu", new ObjectProperty<Integer>(0));
		pitmSubMenu.addItemProperty("nombre_menu", new ObjectProperty<String>(""));
		pitmSubMenu.addItemProperty("nombre_programa", new ObjectProperty<String>(""));
		pitmSubMenu.addItemProperty("icono", new ObjectProperty<String>(""));
		pitmSubMenu.addItemProperty("sub_sistema", new ObjectProperty<Long>((long) 1));
		pitmSubMenu.addItemProperty("menu", new ObjectProperty<Long>((long) 1));
		
		this.binderSubMenu = new FieldGroup(this.pitmSubMenu);
		
		binderSubMenu.bind(this.txt_identificador, "identificador");
		binderSubMenu.bind(this.txt_id_submenu, "id_submenu");
		binderSubMenu.bind(this.txt_nombre_menu, "nombre_menu");
		binderSubMenu.bind(this.txt_nombre_programa, "nombre_programa");
		binderSubMenu.bind(this.cb_icons, "icono");
		binderSubMenu.bind(this.cbSubsistema, "sub_sistema");
		binderSubMenu.bind(this.cbMenus, "menu");
		
		this.txt_nombre_menu.setRequired(true);
		this.txt_nombre_menu.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_menu.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3, 50, false));
		this.txt_identificador.setEnabled(false);
		this.txt_id_submenu.setEnabled(false);
		this.cbSubsistema.setRequired(true);
		this.cbSubsistema.select((long) 1);
		
		this.cbMenus.setRequired(true);
		
		cbSubsistema.setWidth("90%");
		cbMenus.setWidth("90%");
		txt_id_submenu.setWidth("90%");
		txt_identificador.setWidth("90%");
		txt_nombre_menu.setWidth("90%");
		txt_nombre_programa.setWidth("90%");
		cb_icons.setWidth("90%");
		
		fillSubsistema();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	/**
	 * Inicializacion de los campos
	 */
	public void init() {
		update();
	}
	
	/**
	 * Actualizacion de los Campos
	 */
	public void update() {
		this.binderSubMenu.clear();
		this.cbSubsistema.setValue((long) 1);
		this.txt_identificador.setValue(this.menuimpl.generateId());
		GenerarIds();
	}
	
	/**
	 * Llenado del Combo Box Subsistema
	 */
	private void fillSubsistema() {
		cbSubsistema.setNullSelectionAllowed(false);
		cbSubsistema.setInputPrompt("Seleccione un SubSistema");
		for (Arbol_menus subSistema : menuimpl.getallSubsistema()) {
			cbSubsistema.addItem(subSistema.getAME_Id_Identificador());
			cbSubsistema.setItemCaption(subSistema.getAME_Id_Identificador(), subSistema.getAME_Nombre());
		}
		fillMenu((long) this.cbSubsistema.getValue());
	}
	
	/**
	 * Llenado del ComboBox Menu
	 * 
	 * @param subsistema
	 */
	private void fillMenu(long subsistema) {
		cbMenus.removeAllItems();
		cbMenus.setNullSelectionAllowed(false);
		cbMenus.setInputPrompt("Seleccione un Menu");
		for (Arbol_menus subSistema : menuimpl.getallMenu(subsistema)) {
			cbMenus.addItem(subSistema.getAME_Id_Identificador());
			cbMenus.setItemCaption(subSistema.getAME_Id_Identificador(), subSistema.getAME_Nombre());
		}
	}
	
	private void buildContent() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 2);
		setColumnExpandRatio(3, 2);
		addComponent(this.cbSubsistema, 0, 0, 1, 0);
		addComponent(this.cbMenus, 2, 0);
		addComponent(this.txt_identificador, 0, 1);
		addComponent(this.txt_id_submenu, 1, 1);
		addComponent(this.txt_nombre_menu, 2, 1);
		addComponent(this.txt_nombre_programa, 2, 2);
		addComponent(this.cb_icons, 3, 2);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			this.binderSubMenu.commit();
			this.mensajes.add(new BarMessage("Fomulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.txt_nombre_menu.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_menu.getCaption(), "Campo no debe ser Vacio"));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_menu.getCaption(), ex.getMessage()));
			}
			try {
				this.cbSubsistema.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cbSubsistema.getCaption(), "Campo no debe ser Vacio"));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cbSubsistema.getCaption(), ex.getMessage()));
			}
			try {
				this.cbMenus.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cbMenus.getCaption(), "Campo no debe ser Vacio"));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cbMenus.getCaption(), ex.getMessage()));
			}
			return false;
		}
	}
	
	public void GenerarIds() {
		if (this.cbMenus.getValue() != null) {
			this.txt_id_submenu.setValue(this.menuimpl.generateSubMenuId((int) (long) this.cbSubsistema.getValue(),
					(int) (long) this.cbMenus.getValue()));
		}
	}
	
	public Arbol_menus getData() {
		Arbol_menus resul = new Arbol_menus();
		resul.setAME_Id_Identificador(Integer.parseInt(this.txt_identificador.getValue()));
		resul.setAME_Id_SubMenu(Integer.parseInt(this.txt_id_submenu.getValue()));
		
		resul.setAME_Nombre(this.txt_nombre_menu.getValue());
		
		resul.setAME_Id_Subsistema((int) (long) this.cbSubsistema.getValue());
		resul.setAME_Id_Menus((int) (long) this.cbMenus.getValue());
		
		resul.setAME_Programa(this.txt_nombre_programa.getValue());
		resul.setAME_Icono(this.cb_icons.getValue().toString());
		
		long lnMilis = new Date().getTime();
		resul.setAME_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	
	public void setData(Arbol_menus data) {
		this.cbMenus.removeValueChangeListener(this);
		this.cbSubsistema.removeValueChangeListener(this);
		
		this.txt_identificador.setValue(String.valueOf(data.getAME_Id_Identificador()));
		this.txt_id_submenu.setValue(String.valueOf(data.getAME_Id_SubMenu()));
		this.cbMenus.setValue((long) data.getAME_Id_Menus());
		this.txt_nombre_menu.setValue(data.getAME_Nombre());
		this.cbSubsistema.setValue((long) data.getAME_Id_Subsistema());
		this.txt_nombre_programa.setValue(data.getAME_Programa()==null?"":data.getAME_Programa());
		if (data.getAME_Icono() != null)
			this.cb_icons.setValue(data.getAME_Icono());
		this.cbMenus.addValueChangeListener(this);
		this.cbSubsistema.addValueChangeListener(this);
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (this.cbSubsistema.getValue() != null) {
			if (this.cbSubsistema.getValue() == event.getProperty().getValue()) {
				fillMenu((long) this.cbSubsistema.getValue());
			} else {
				GenerarIds();
			}
			
		}
	}

	public long getMenu() {
		if (cbMenus.getValue()!=null) {
			return (Long)cbMenus.getValue();
		}
		return 0;
	}
}
