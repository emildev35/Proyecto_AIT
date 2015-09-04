package ait.sistemas.proyecto.seguridad.view.estr.menu;

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

public class FormMenu extends GridLayout implements Property.ValueChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	public TextField txt_identificador;
	public TextField txt_id_menu;
	public TextField txt_nombre_menu;
	public TextField txt_nombre_programa;
	public ComboBox cbSubsistema;
	public ComboBoxIcons cb_icons;
	
	private List<BarMessage> mensajes;
	
	final private MenuImpl menuimpl = new MenuImpl();
	
	final PropertysetItem pitmMenu = new PropertysetItem();
	private FieldGroup binderMenu;
	
	public FormMenu() {
		setColumns(4);
		setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		
		this.mensajes = new ArrayList<BarMessage>();
		this.txt_identificador = new TextField("Identificador");
		this.txt_id_menu = new TextField("Codigo");
		this.txt_nombre_menu = new TextField("Nombre del Menu");
		this.txt_nombre_programa = new TextField("URL del Programa");
		this.cb_icons = new ComboBoxIcons("Seleccione Icono");
		this.cbSubsistema = new ComboBox("Sub-Sistema");
		this.cbSubsistema.addValueChangeListener(this);
		
		pitmMenu.addItemProperty("identificador", new ObjectProperty<Integer>(0));
		pitmMenu.addItemProperty("id_menu", new ObjectProperty<Integer>(0));
		pitmMenu.addItemProperty("nombre_menu", new ObjectProperty<String>(""));
		pitmMenu.addItemProperty("nombre_programa", new ObjectProperty<String>(""));
		pitmMenu.addItemProperty("icono", new ObjectProperty<String>(""));
		pitmMenu.addItemProperty("sub_sistema", new ObjectProperty<Long>((long) 1));
		
		this.binderMenu = new FieldGroup(this.pitmMenu);
		
		binderMenu.bind(this.txt_identificador, "identificador");
		binderMenu.bind(this.txt_id_menu, "id_menu");
		binderMenu.bind(this.txt_nombre_menu, "nombre_menu");
		binderMenu.bind(this.txt_nombre_programa, "nombre_programa");
		binderMenu.bind(this.cb_icons, "icono");
		binderMenu.bind(this.cbSubsistema, "sub_sistema");
		
		this.txt_nombre_menu.setRequired(true);
		this.txt_nombre_menu.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_menu.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3, 50, false));
		this.txt_identificador.setEnabled(false);
		this.txt_id_menu.setEnabled(false);
		this.cbSubsistema.setRequired(true);
		this.cbSubsistema.select((long) 1);
		cbSubsistema.setWidth("90%");
		txt_id_menu.setWidth("90%");
		txt_identificador.setWidth("90%");
		txt_nombre_menu.setWidth("90%");
		txt_nombre_programa.setWidth("90%");
		cb_icons.setWidth("90%");
		
		fillSubsistema();
		buildContent();
		GenerarIds();
		Responsive.makeResponsive(this);
	}
	
	public void update() {
		this.binderMenu.clear();
		this.cbSubsistema.setValue((long) 1);
		GenerarIds();
	}
	
	private void fillSubsistema() {
		cbSubsistema.setNullSelectionAllowed(false);
		cbSubsistema.setInputPrompt("Seleccione un SubSistema");
		for (Arbol_menus subSistema : menuimpl.getallSubsistema()) {
			cbSubsistema.addItem(subSistema.getAME_Id_Identificador());
			cbSubsistema.setItemCaption(subSistema.getAME_Id_Identificador(), subSistema.getAME_Nombre());
		}
	}
	
	private void buildContent() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 2);
		setColumnExpandRatio(3, 2);
		addComponent(this.cbSubsistema, 0, 0, 1, 0);
		addComponent(this.txt_identificador, 0, 1);
		addComponent(this.txt_id_menu, 1, 1);
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
			this.binderMenu.commit();
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
			return false;
		}
	}
	
	public void GenerarIds() {
		this.txt_identificador.setValue(this.menuimpl.generateId());
		this.txt_id_menu.setValue(this.menuimpl.generateMenuId((long) this.cbSubsistema.getValue()));
	}
	
	public Arbol_menus getData() {
		Arbol_menus resul = new Arbol_menus();
		resul.setAME_Id_Identificador(Integer.parseInt(this.txt_identificador.getValue()));
		resul.setAME_Id_Menus(Integer.parseInt(this.txt_id_menu.getValue()));
		resul.setAME_Nombre(this.txt_nombre_menu.getValue());
		resul.setAME_Id_Subsistema((int) (long) this.cbSubsistema.getValue());
		resul.setAME_Programa(this.txt_nombre_programa.getValue());
		if (this.cb_icons.getValue() != null) {
			resul.setAME_Icono(this.cb_icons.getValue().toString());
		}
		long lnMilis = new Date().getTime();
		resul.setAME_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	
	public void setData(Arbol_menus data) {
		this.txt_identificador.setValue(String.valueOf(data.getAME_Id_Identificador()));
		this.txt_id_menu.setValue(String.valueOf(data.getAME_Id_Menus()));
		this.txt_nombre_menu.setValue(data.getAME_Nombre());
		this.cbSubsistema.setValue((long) data.getAME_Id_Subsistema());
		this.txt_nombre_programa.setValue(data.getAME_Programa());
		if (data.getAME_Icono() != null)
			this.cb_icons.setValue(data.getAME_Icono());
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (cbSubsistema.getValue() != null) {
			GenerarIds();
		}
		
	}
}
