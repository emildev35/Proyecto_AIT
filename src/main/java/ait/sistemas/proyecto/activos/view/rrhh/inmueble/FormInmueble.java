package ait.sistemas.proyecto.activos.view.rrhh.inmueble;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.model_rrhh.Inmueble;
import ait.sistemas.proyecto.activos.data.model_rrhh.InmuebleModel;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.InmuebleImpl;
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

public class FormInmueble extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	private TextField txt_id_inmueble;
	public TextField txt_nombre_inmueble;
	public ComboBox cb_ciudad;
	public TextField txt_domicilio;
	private List<BarMessage> mensajes;

	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private InmuebleImpl inmueble_impl = new InmuebleImpl();
	private PropertysetItem pitm_Inmueble = new PropertysetItem();
	private FieldGroup binder_Inmueble;

	public FormInmueble() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_inmueble = new TextField("Codigo:");
		this.txt_nombre_inmueble = new TextField("Nombre del Inmueble: ");
		this.cb_ciudad = new ComboBox("Ciudad:");
		this.txt_domicilio = new TextField("Domicilio: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Inmueble.addItemProperty("id_inmueble", new ObjectProperty<Short>((short) 1));
		pitm_Inmueble.addItemProperty("nombre_inmueble", new ObjectProperty<String>(""));
		pitm_Inmueble.addItemProperty("id_ciudad", new ObjectProperty<Short>((short) 1));
		pitm_Inmueble.addItemProperty("domicilio", new ObjectProperty<String>(""));

		this.binder_Inmueble = new FieldGroup(this.pitm_Inmueble);

		binder_Inmueble.bind(this.txt_id_inmueble, "id_inmueble");
		binder_Inmueble.bind(this.txt_nombre_inmueble, "nombre_inmueble");
		binder_Inmueble.bind(this.cb_ciudad, "id_ciudad");
		binder_Inmueble.bind(this.txt_domicilio, "domicilio");
		binder_Inmueble.clear();
		
		this.txt_nombre_inmueble.setRequired(true);
		this.txt_nombre_inmueble.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_inmueble.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_domicilio.setRequired(true);
		this.txt_domicilio.addValidator(new NullValidator("No Nulo", false));
		this.txt_domicilio.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(5, 100), 5,100,false));
		this.txt_id_inmueble.setEnabled(false);
		this.cb_ciudad.setRequired(true);
		this.cb_ciudad.addValidator(new NullValidator("No Nulo", false));

		txt_id_inmueble.setWidth("90%");
		txt_nombre_inmueble.setWidth("90%");
		cb_ciudad.setWidth("90%");
		txt_domicilio.setWidth("90%");

		updateId();
		fillcbCiudad();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void fillcbCiudad() {
		cb_ciudad.setNullSelectionAllowed(false);
		cb_ciudad.setInputPrompt("Seleccione una Sigla");
		for (Ciudade ciudad : dependencia_impl.getallsigla())
		{
			cb_ciudad.addItem(ciudad.getCIU_Ciudad());
			cb_ciudad.setItemCaption(ciudad.getCIU_Ciudad(), ciudad.getCIU_Nombre_Ciudad());
		}
	}
	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		setColumnExpandRatio(2, 1);
		addComponent(this.txt_id_inmueble, 0,0);
		addComponent(this.txt_nombre_inmueble, 1,0);
		addComponent(this.cb_ciudad, 1,1);
		addComponent(this.txt_domicilio, 2,1);

	}
	
	public void update(){
		binder_Inmueble.clear();
	}
	public void updateId(){
		this.txt_id_inmueble.setValue(inmueble_impl.generateId() + "");
	}
	public void enabled(){
		this.txt_nombre_inmueble.setEnabled(false);
		this.txt_domicilio.setEnabled(false);
		this.cb_ciudad.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Inmueble.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.txt_nombre_inmueble.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_inmueble.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_inmueble.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_domicilio.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_domicilio.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_domicilio.getCaption(), ex.getMessage()));
			}

			try {
				this.cb_ciudad.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_ciudad.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_ciudad.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public Inmueble getData(){
		Inmueble resul = new Inmueble();
		resul.setINM_Inmueble(Short.parseShort(this.txt_id_inmueble.getValue()));
		resul.setINM_Nombre_Inmueble(this.txt_nombre_inmueble.getValue());
		resul.setINM_Domicilio_Inmueble(this.txt_domicilio.getValue());
		resul.setINM_Ciudad((short)this.cb_ciudad.getValue());
		long lnMilis = new Date().getTime();
		resul.setINM_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(InmuebleModel data){	
		this.txt_id_inmueble.setValue(String.valueOf(data.getINM_Inmueble()));
		this.txt_nombre_inmueble.setValue(String.valueOf(data.getINM_Nombre_Inmueble()));
		this.txt_domicilio.setValue(String.valueOf(data.getINM_Domicilio_Inmueble()));
		this.cb_ciudad.setValue((Short)data.getINM_Ciudad_ID());
				
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
		//updateId();
	}
}

