package ait.sistemas.proyecto.activos.view.rrhh.personal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.model_rrhh.Personal;
import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Unidades_Organizacionale;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UnidadImpl;
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

public class FormPersonal extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	private TextField txt_ci_personal;
	public TextField txt_nombres;
	private TextField txt_ap_paterno;
	private TextField txt_ap_materno;
	private TextField txt_telefono;
	private TextField txt_interno;
	public ComboBox cb_dependencia;
	public ComboBox cb_unidad;
	private List<BarMessage> mensajes;

	private UnidadImpl unidad_impl = new UnidadImpl();
	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private PropertysetItem pitm_Personal = new PropertysetItem();
	private FieldGroup binder_Personal;

	public FormPersonal() {

		super.setColumns(5);
		super.setRows(4);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.cb_dependencia = new ComboBox("Dependencia:");
		this.cb_dependencia.addValueChangeListener(this);
		this.cb_unidad = new ComboBox("Unidad Organizacional:");
//		this.cb_unidad.addValueChangeListener(this);
		this.txt_ci_personal = new TextField("Carnet Identidad:");
		this.txt_nombres = new TextField("Nombres: ");
		this.txt_ap_paterno = new TextField("Apellido Paterno: ");
		this.txt_ap_materno = new TextField("Apellido Materno: ");
		this.txt_telefono = new TextField("Telefono: ");
		this.txt_interno = new TextField("Interno: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Personal.addItemProperty("id_dependencia", new ObjectProperty<Short>((short) 1));
		pitm_Personal.addItemProperty("id_unidad", new ObjectProperty<Short>((short) 1));
		pitm_Personal.addItemProperty("carnet_identidad", new ObjectProperty<String>(""));
		pitm_Personal.addItemProperty("nombres", new ObjectProperty<String>(""));
		pitm_Personal.addItemProperty("ap_paterno", new ObjectProperty<String>(""));
		pitm_Personal.addItemProperty("ap_materno", new ObjectProperty<String>(""));
		pitm_Personal.addItemProperty("telefono", new ObjectProperty<String>(""));
		pitm_Personal.addItemProperty("interno", new ObjectProperty<String>(""));

		this.binder_Personal = new FieldGroup(this.pitm_Personal);

		binder_Personal.bind(this.cb_dependencia, "id_dependencia");
		binder_Personal.bind(this.cb_unidad, "id_unidad");
		binder_Personal.bind(this.txt_ci_personal, "carnet_identidad");
		binder_Personal.bind(this.txt_nombres, "nombres");
		binder_Personal.bind(this.txt_ap_paterno, "ap_paterno");
		binder_Personal.bind(this.txt_ap_materno, "ap_materno");
		binder_Personal.bind(this.txt_telefono, "telefono");
		binder_Personal.bind(this.txt_interno, "interno");
		binder_Personal.clear();

		this.txt_ci_personal.setRequired(true);
		this.txt_ci_personal.addValidator(new NullValidator("No Nulo", false));
		this.txt_ci_personal.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(5, 9), 5,9,false));
		this.txt_nombres.setRequired(true);
		this.txt_nombres.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombres.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_ap_paterno.setRequired(true);
		this.txt_ap_paterno.addValidator(new NullValidator("No Nulo", false));
		this.txt_ap_paterno.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 49), 3,49,false));
		this.txt_ap_materno.setRequired(true);
		this.txt_ap_materno.addValidator(new NullValidator("No Nulo", false));
		this.txt_ap_materno.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 49), 3,49,false));
		this.txt_telefono.setRequired(true);
		this.txt_telefono.addValidator(new NullValidator("No Nulo", false));
		this.txt_telefono.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(7, 9), 7,9,false));
		this.txt_interno.setRequired(true);
		this.txt_interno.addValidator(new NullValidator("No Nulo", false));
		this.txt_interno.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 8), 3,8,false));
		this.cb_dependencia.setRequired(true);
		this.cb_dependencia.select((long)1);
		this.cb_unidad.setRequired(true);
		this.cb_unidad.select((long)1);
		
		
		cb_dependencia.setWidth("90%");
		cb_unidad.setWidth("90%");
		txt_ci_personal.setWidth("90%");
		txt_nombres.setWidth("90%");
		txt_ap_paterno.setWidth("90%");
		txt_ap_materno.setWidth("90%");
		txt_telefono.setWidth("90%");
		txt_interno.setWidth("90%");

		fillcombo();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void fillcombo() {
		cb_dependencia.setNullSelectionAllowed(false);
		cb_dependencia.setInputPrompt("Seleccione una Dependencia");
		for (Dependencia dependencia : dependencia_impl.getall())
		{
			cb_dependencia.addItem(dependencia.getDEP_Dependencia());
			cb_dependencia.setItemCaption(dependencia.getDEP_Dependencia(), dependencia.getDEP_Nombre_Dependencia());
		}
		
	
	}
	private void fillcbUnidad(short padre){
		cb_unidad.removeAllItems();
		cb_unidad.setNullSelectionAllowed(false);
		cb_unidad.setInputPrompt("Seleccione una Unidad");
		for (Unidades_Organizacionale unidad : unidad_impl.getunidad(padre))
		{
			cb_unidad.addItem(unidad.getUNO_Unidad_Organizacional());
			cb_unidad.setItemCaption(unidad.getUNO_Unidad_Organizacional(), unidad.getUNO_Nombre_Unidad_Organizacional());
		}
	}
	private void buildContent() {
		setColumnExpandRatio(1, 0);
		setColumnExpandRatio(2, 0);
		setColumnExpandRatio(3, 0);
		setColumnExpandRatio(4, 0);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		setColumnExpandRatio(3, 1);
		setColumnExpandRatio(4, 1);
		addComponent(this.cb_dependencia, 1,0);
		addComponent(this.cb_unidad, 2,0);
		addComponent(this.txt_telefono, 3,0);
		addComponent(this.txt_interno, 4,0);
		addComponent(this.txt_ci_personal, 1,1);
		addComponent(this.txt_nombres, 2,1);
		addComponent(this.txt_ap_paterno, 3,1);
		addComponent(this.txt_ap_materno, 4,1);

	}
	
	public void update(){
		binder_Personal.clear();
	}

	public void enabled(){
		this.cb_dependencia.setEnabled(false);
		this.cb_unidad.setEnabled(false);
		this.txt_ci_personal.setEnabled(false);
		this.txt_nombres.setEnabled(false);
		this.txt_ap_paterno.setEnabled(false);
		this.txt_ap_materno.setEnabled(false);
		this.txt_telefono.setEnabled(false);
		this.txt_interno.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Personal.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.cb_dependencia.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_dependencia.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_dependencia.getCaption(), ex.getMessage()));
			}
			try {
				this.cb_unidad.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_unidad.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_unidad.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_ci_personal.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_ci_personal.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_ci_personal.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_nombres.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombres.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombres.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_ap_paterno.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_ap_paterno.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_ap_paterno.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_ap_materno.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_ap_materno.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_ap_materno.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_telefono.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_telefono.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_telefono.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_interno.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_interno.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_interno.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public Personal getData(){
		Personal resul = new Personal();
		resul.setPER_Dependencia((short)this.cb_dependencia.getValue());
		resul.setPER_Unidad_Organizacional((short)this.cb_unidad.getValue());
		resul.setPER_CI_Empleado(this.txt_ci_personal.getValue());
		resul.setPER_Nombres(this.txt_nombres.getValue());
		resul.setPER_Apellido_Paterno(this.txt_ap_paterno.getValue());
		resul.setPER_Apellido_Materno(this.txt_ap_materno.getValue());
		resul.setPER_No_Telefono_Oficina(this.txt_telefono.getValue());
		resul.setPER_No_Interno(this.txt_interno.getValue());
		long lnMilis = new Date().getTime();
		resul.setPER_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(PersonalModel data){	
		//this.cb_dependencia.setValue(String.valueOf(data.getDEP_Sigla()));
		this.cb_dependencia.setValue((Short)data.getPER_Dependencia_ID());
		this.cb_unidad.setValue((Short)data.getPER_Unidad_Organizacional_ID());
		//this.cb_unidad.setValue(String.valueOf(data.getDEP_Sigla()));
		this.txt_ci_personal.setValue(String.valueOf(data.getPER_CI_Empleado()));
		this.txt_nombres.setValue(String.valueOf(data.getPER_Nombres()));
		this.txt_ap_paterno.setValue(String.valueOf(data.getPER_Apellido_Paterno()));
		this.txt_ap_materno.setValue(String.valueOf(data.getPER_Apellido_Materno()));
		this.txt_telefono.setValue(String.valueOf(data.getPER_No_Telefono_Oficina()));
		this.txt_interno.setValue(String.valueOf(data.getPER_No_Interno()));			
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
		//updateId();
		if(event.getProperty().getValue() == this.cb_dependencia.getValue() && (Short)this.cb_dependencia.getValue() != null){
			fillcbUnidad((short)this.cb_dependencia.getValue());
		}	
	}
}

