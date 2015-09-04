package ait.sistemas.proyecto.seguridad.view.estr.subsistema;

import java.util.Date;

import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.ComboBoxIcons;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class VSubSistemaA extends VerticalLayout implements View, ItemSetChangeListener, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_submit;
	private Button btn_limpiar;
	private TextField txt_identificador;
	private TextField txt_id_subsistema;
	private TextField txt_nombre_subsistema;
	private TextField txt_nombre_programa;
	private ComboBoxIcons cb_icons;
	
	final PropertysetItem pitmSubSistema = new PropertysetItem();
	private FieldGroup binderSubSistema;
	
	private CssLayout hl_errores;
	
	private MenuImpl menuImpl;
	private Arbol_menus subsistema;
	
	public VSubSistemaA() {
		
		this.hl_errores = new CssLayout();
		this.txt_identificador = new TextField("Identificador");
		this.txt_id_subsistema = new TextField("Id. Sub-Sistema");
		this.txt_nombre_subsistema = new TextField("Nombre del Sub-Sistema");
		this.txt_nombre_programa = new TextField("Nombre del Programa");
		this.cb_icons = new ComboBoxIcons("Icono del Sus-Sistema");
		this.cb_icons.addItemSetChangeListener(this);
		this.btn_submit = new Button("Guardar");
		this.btn_limpiar = new Button("Limpiar");
		this.menuImpl = new MenuImpl();
		
		pitmSubSistema.addItemProperty("identificador", new ObjectProperty<Integer>(0));
		pitmSubSistema.addItemProperty("id_subsistema", new ObjectProperty<Integer>(0));
		pitmSubSistema.addItemProperty("nombre_subsistema", new ObjectProperty<String>(""));
		pitmSubSistema.addItemProperty("nombre_programa", new ObjectProperty<String>(""));
		pitmSubSistema.addItemProperty("iconos", new ObjectProperty<String>(""));
		
		binderSubSistema = new FieldGroup(pitmSubSistema);
		binderSubSistema.bind(this.txt_identificador, "identificador");
		binderSubSistema.bind(this.txt_id_subsistema, "id_subsistema");
		binderSubSistema.bind(this.txt_nombre_subsistema, "nombre_subsistema");
		binderSubSistema.bind(this.txt_nombre_programa, "nombre_programa");
		binderSubSistema.bind(this.cb_icons, "iconos");
		
		this.txt_identificador.setEnabled(false);
		this.txt_id_subsistema.setEnabled(false);
		
		this.txt_nombre_subsistema.setRequired(true);
		this.txt_nombre_subsistema.setWidth("100%");
		this.txt_nombre_subsistema.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3, 50, false));
		this.txt_nombre_programa.setRequired(true);
		this.txt_nombre_programa.setWidth("100%");
		this.txt_nombre_programa.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3, 50, false));
		this.cb_icons.setWidth("100%");
		
		generarIdentificador();
		generarIdSubsistema();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
	}
	
	private Component buildFormContent() {
		VerticalLayout vl_content = new VerticalLayout();
		vl_content.setStyleName("ait-content-form");
		vl_content.setMargin(true);
		GridLayout frmAddSubsistema = new GridLayout(4, 2);
		frmAddSubsistema.setWidth("85%");
		frmAddSubsistema.setSpacing(true);
		frmAddSubsistema.setColumnExpandRatio(2, 2);
		frmAddSubsistema.setColumnExpandRatio(3, 2);
		frmAddSubsistema.addComponent(this.txt_identificador, 0, 0);
		frmAddSubsistema.addComponent(this.txt_id_subsistema, 1, 0);
		frmAddSubsistema.addComponent(this.txt_nombre_subsistema, 2, 0);
		frmAddSubsistema.addComponent(this.txt_nombre_programa, 2, 1);
		frmAddSubsistema.addComponent(this.cb_icons, 3, 0);
		
		vl_content.addComponent(frmAddSubsistema);
		
		return vl_content;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Seguridad » "));
		nav.addComponent(new Label("Estructura del Sistema » "));
		nav.addComponent(new Label("Su-Sistema » "));
		nav.addComponent(new Label("<strong>Agregar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_submit);
		this.btn_submit.addStyleName("ait-buttons-btn");
		this.btn_submit.addClickListener(this);
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
		this.btn_limpiar.addStyleName("ait-buttons-btn");
		this.btn_limpiar.addClickListener(this);
		return buttonContent;
	}
	
	private void generarIdentificador() {
		this.txt_identificador.setValue(this.menuImpl.generateId());
	}
	
	private void generarIdSubsistema() {
		this.txt_id_subsistema.setValue(this.menuImpl.generateSubSistemaId());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	@Override
	public void containerItemSetChange(ItemSetChangeEvent event) {
		Notification.show("XD");
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {
			this.txt_nombre_subsistema.setValue(this.txt_nombre_subsistema.getValue().replaceAll("\\s+", ""));
			this.txt_nombre_programa.setValue(this.txt_nombre_programa.getValue().replaceAll("\\s+", ""));
			try {
				this.binderSubSistema.commit();
				
				subsistema = new Arbol_menus();
				subsistema.setAME_Id_Identificador(Long.parseLong(this.txt_identificador.getValue()));
				subsistema.setAME_Id_Subsistema(Integer.parseInt(this.txt_id_subsistema.getValue()));
				subsistema.setAME_Nombre(this.txt_nombre_subsistema.getValue());
				subsistema.setAME_Programa(this.txt_nombre_programa.getValue());
				long lnMilis = new Date().getTime();
				subsistema.setAME_Fecha_Registro(new java.sql.Date(lnMilis));
				subsistema.setAME_Icono(this.cb_icons.getValue().toString());
				Arbol_menus result = this.menuImpl.addSubSistema(subsistema);
				if (result == null) {
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				} else {
					Notification.show(Messages.SUCCESS_MESSAGE);
					this.binderSubSistema.clear();
					generarIdentificador();
					generarIdSubsistema();
				}
				
			} catch (CommitException e) {
				buildError(e.getMessage());
			} catch (InvalidValueException e) {
				buildError(e.getMessage());
			}
			
		}
		if (event.getButton() == this.btn_limpiar) {
			this.binderSubSistema.clear();
			generarIdentificador();
			generarIdSubsistema();
		}
	}
	
	private void buildError(String strmessage) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		Label lbError = new Label(new Label(strmessage));
		this.hl_errores.addComponent(lbError);
		
	}
	
}
