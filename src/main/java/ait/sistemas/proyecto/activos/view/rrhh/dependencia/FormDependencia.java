package ait.sistemas.proyecto.activos.view.rrhh.dependencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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

public class FormDependencia extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	private TextField txt_id_dependencia;
	private TextField txt_nombre_dependencia;
	public ComboBox cb_sigla_ciudad;
	private TextField txt_domicilio;
	private TextField txt_pagina;
	private TextField txt_telefono;
	private List<BarMessage> mensajes;

	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private PropertysetItem pitm_Dependencia = new PropertysetItem();
	private FieldGroup binder_Dependencia;

	public FormDependencia() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_dependencia = new TextField("Id. Dependencia:");
		this.txt_nombre_dependencia = new TextField("Nombre Motivo de Dependencia: ");
		this.cb_sigla_ciudad = new ComboBox("Sigla de la Ciudad:");
		this.cb_sigla_ciudad.addValueChangeListener(this);
		this.txt_domicilio = new TextField("Domicilio: ");
		this.txt_pagina = new TextField("Pagina Web: ");
		this.txt_telefono = new TextField("Telefono: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Dependencia.addItemProperty("id_dependencia", new ObjectProperty<Integer>(0));
		pitm_Dependencia.addItemProperty("nombre_dependencia", new ObjectProperty<String>(""));
		pitm_Dependencia.addItemProperty("sigla_ciudad", new ObjectProperty<Short>((short) 1));
		pitm_Dependencia.addItemProperty("domicilio", new ObjectProperty<String>(""));
		pitm_Dependencia.addItemProperty("pagina_web", new ObjectProperty<String>(""));
		pitm_Dependencia.addItemProperty("telefono", new ObjectProperty<String>(""));

		this.binder_Dependencia = new FieldGroup(this.pitm_Dependencia);

		binder_Dependencia.bind(this.txt_id_dependencia, "id_dependencia");
		binder_Dependencia.bind(this.txt_nombre_dependencia, "nombre_dependencia");
		binder_Dependencia.bind(this.cb_sigla_ciudad, "sigla_ciudad");
		binder_Dependencia.bind(this.txt_domicilio, "domicilio");
		binder_Dependencia.bind(this.txt_pagina, "pagina_web");
		binder_Dependencia.bind(this.txt_telefono, "telefono");

		this.txt_nombre_dependencia.setRequired(true);
		this.txt_nombre_dependencia.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_dependencia.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_domicilio.setRequired(true);
		this.txt_domicilio.addValidator(new NullValidator("No Nulo", false));
		this.txt_domicilio.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(5, 100), 5,100,false));
		this.txt_pagina.setRequired(true);
		this.txt_pagina.addValidator(new NullValidator("No Nulo", false));
		this.txt_pagina.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_telefono.setRequired(true);
		this.txt_telefono.addValidator(new NullValidator("No Nulo", false));
		this.txt_telefono.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(6, 15), 6,15,false));
		this.txt_id_dependencia.setEnabled(false);
		this.cb_sigla_ciudad.setRequired(true);
		this.cb_sigla_ciudad.select((long)1);
		
		
		txt_id_dependencia.setWidth("90%");
		txt_nombre_dependencia.setWidth("90%");
		cb_sigla_ciudad.setWidth("90%");
		txt_domicilio.setWidth("90%");
		txt_pagina.setWidth("90%");
		txt_telefono.setWidth("90%");

		updateId();
		fillsigla();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void fillsigla() {
		cb_sigla_ciudad.setNullSelectionAllowed(false);
		cb_sigla_ciudad.setInputPrompt("Seleccione una Sigla");
		for (Ciudade sigla : dependencia_impl.getallsigla())
		{
			cb_sigla_ciudad.addItem(sigla.getCIU_Ciudad());
			cb_sigla_ciudad.setItemCaption(sigla.getCIU_Ciudad(), sigla.getCIU_Sigla());
		}
		cb_sigla_ciudad.select(1);
	}
	private void buildContent() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(2, 0);
		setColumnExpandRatio(2, 1);
		setColumnExpandRatio(2, 2);
		setColumnExpandRatio(3, 0);
		setColumnExpandRatio(3, 1);
		addComponent(this.txt_id_dependencia, 0,1);
		addComponent(this.txt_nombre_dependencia, 2,0);
		addComponent(this.txt_domicilio, 2,1);
		addComponent(this.txt_pagina, 2,2);
		addComponent(this.cb_sigla_ciudad, 3,0);
		addComponent(this.txt_telefono, 3,1);

	}
	
	public void update(){
		binder_Dependencia.clear();
		this.cb_sigla_ciudad.setValue((long)1);
	}
	public void updateId(){
		this.txt_id_dependencia.setValue(dependencia_impl.generateId() + "");
		//this.txt_id_menu.setValue(this.menuimpl.generateMenuId((int)(long)this.cbSubsistema.getValue()));
	}
	public void enabled(){
		this.txt_nombre_dependencia.setEnabled(false);
		this.txt_domicilio.setEnabled(false);
		this.txt_pagina.setEnabled(false);
		this.cb_sigla_ciudad.setEnabled(false);
		this.txt_telefono.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Dependencia.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.txt_nombre_dependencia.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_dependencia.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_dependencia.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_domicilio.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_domicilio.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_domicilio.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_pagina.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_pagina.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_pagina.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_telefono.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_telefono.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_telefono.getCaption(), ex.getMessage()));
			}
			try {
				this.cb_sigla_ciudad.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_sigla_ciudad.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_sigla_ciudad.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public Dependencia getData(){
		Dependencia resul = new Dependencia();
		resul.setDEP_Dependencia(Short.parseShort(this.txt_id_dependencia.getValue()));
		resul.setDEP_Nombre_Dependencia(this.txt_nombre_dependencia.getValue());
		resul.setDEP_Domicilio(this.txt_domicilio.getValue());
		resul.setDEP_Pagina_Web(this.txt_pagina.getValue());
		resul.setDEP_Ciudad((short)this.cb_sigla_ciudad.getValue());
		resul.setDEP_Sigla(this.cb_sigla_ciudad.getItemCaption(this.cb_sigla_ciudad.getValue()));
		resul.setDEP_Telefono(this.txt_telefono.getValue());
		long lnMilis = new Date().getTime();
		resul.setDEP_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(Dependencia data){	
		this.txt_id_dependencia.setValue(String.valueOf(data.getDEP_Dependencia()));
		this.txt_nombre_dependencia.setValue(String.valueOf(data.getDEP_Nombre_Dependencia()));
		this.txt_domicilio.setValue(String.valueOf(data.getDEP_Domicilio()));
		this.txt_pagina.setValue(String.valueOf(data.getDEP_Pagina_Web()));
		this.cb_sigla_ciudad.setValue(String.valueOf(data.getDEP_Sigla()));
		this.cb_sigla_ciudad.setValue((Short)data.getDEP_Ciudad());
		this.txt_telefono.setValue(String.valueOf(data.getDEP_Telefono()));
				
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
		//updateId();
	}
}

