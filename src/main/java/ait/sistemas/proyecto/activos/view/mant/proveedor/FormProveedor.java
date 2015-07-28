package ait.sistemas.proyecto.activos.view.mant.proveedor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Proveedore;
import ait.sistemas.proyecto.activos.data.model.ProveedoresModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.service.Impl.CiudadImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormProveedor extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	private TextField txt_NIT;
	public TextField txt_nombre_proveedor;
	private TextField txt_domicilio;
	private TextField txt_nombre_contacto;
	public ComboBox cb_ciudad;
	private TextField txt_telefono;
	private TextField txt_celular;
	private List<BarMessage> mensajes;

	private CiudadImpl ciudad_impl = new CiudadImpl();
	DependenciaImpl dependencia_impl = new DependenciaImpl();
	private PropertysetItem pitm_Proveedor = new PropertysetItem();
	private FieldGroup binder_Proveedor;

	public FormProveedor() {

		super.setColumns(5);
		super.setRows(4);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_NIT = new TextField("NIT:");
		this.txt_nombre_proveedor = new TextField("Nombre Proveedor: ");
		this.txt_domicilio = new TextField("Domicilio: ");
		this.txt_nombre_contacto = new TextField("Nombre Contacto: ");
		this.cb_ciudad = new ComboBox("Ciudad:");
		this.cb_ciudad.addValueChangeListener(this);
		this.txt_telefono = new TextField("Telefono: ");
		this.txt_celular = new TextField("Celular: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Proveedor.addItemProperty("NIT", new ObjectProperty<String>(""));
		pitm_Proveedor.addItemProperty("nombre_proveedor", new ObjectProperty<String>(""));
		pitm_Proveedor.addItemProperty("domicilio", new ObjectProperty<String>(""));
		pitm_Proveedor.addItemProperty("nombre_contacto", new ObjectProperty<String>(""));
		pitm_Proveedor.addItemProperty("id_ciudad", new ObjectProperty<Short>((short) 1));
		pitm_Proveedor.addItemProperty("telefono", new ObjectProperty<String>(""));
		pitm_Proveedor.addItemProperty("celular", new ObjectProperty<String>(""));

		this.binder_Proveedor = new FieldGroup(this.pitm_Proveedor);

		binder_Proveedor.bind(this.txt_NIT, "NIT");
		binder_Proveedor.bind(this.txt_nombre_proveedor, "nombre_proveedor");
		binder_Proveedor.bind(this.txt_domicilio, "domicilio");
		binder_Proveedor.bind(this.txt_nombre_contacto, "nombre_contacto");
		binder_Proveedor.bind(this.cb_ciudad, "id_ciudad");
		binder_Proveedor.bind(this.txt_telefono, "telefono");
		binder_Proveedor.bind(this.txt_celular, "celular");
		binder_Proveedor.clear();

		this.txt_NIT.setRequired(true);
		this.txt_NIT.addValidator(new NullValidator("No Nulo", false));
		this.txt_NIT.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(5, 12), 5,12,false));
		this.txt_nombre_proveedor.setRequired(true);
		this.txt_nombre_proveedor.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_proveedor.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_domicilio.setRequired(true);
		this.txt_domicilio.addValidator(new NullValidator("No Nulo", false));
		this.txt_domicilio.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_nombre_contacto.setRequired(true);
		this.txt_nombre_contacto.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_contacto.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.cb_ciudad.setRequired(true);
		this.cb_ciudad.select((long)1);
		this.txt_telefono.setRequired(true);
		this.txt_telefono.addValidator(new NullValidator("No Nulo", false));
		this.txt_telefono.addValidator(new RegexpValidator("[0-9]{7}", "Campo solo de numeros"));
		this.txt_celular.setRequired(true);
		this.txt_celular.addValidator(new NullValidator("No Nulo", false));
		this.txt_celular.addValidator(new RegexpValidator("[0-9]{8}", "Campo solo de numeros"));
		
		
		cb_ciudad.setWidth("90%");
		txt_NIT.setWidth("90%");
		txt_nombre_proveedor.setWidth("90%");
		txt_domicilio.setWidth("90%");
		txt_nombre_contacto.setWidth("90%");
		txt_telefono.setWidth("90%");
		txt_celular.setWidth("90%");

		fillcomboCiudad();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void fillcomboCiudad() {
		cb_ciudad.setNullSelectionAllowed(false);
		cb_ciudad.setInputPrompt("Seleccione una Ciudad");
		for (Ciudade ciudad : ciudad_impl.getall())
		{
			cb_ciudad.addItem(ciudad.getCIU_Ciudad());
			cb_ciudad.setItemCaption(ciudad.getCIU_Ciudad(), ciudad.getCIU_Nombre_Ciudad());
		}
		
	
	}

	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		addComponent(this.txt_NIT, 0,0);
		addComponent(this.txt_nombre_proveedor, 1,0);
		addComponent(this.txt_domicilio, 1,1);
		addComponent(this.txt_nombre_contacto, 1,2);
		addComponent(this.cb_ciudad, 2,0);
		addComponent(this.txt_telefono, 2,1);
		addComponent(this.txt_celular, 2,2);

	}
	
	public void update(){
		binder_Proveedor.clear();
	}

	public void enabled(){
		this.cb_ciudad.setEnabled(false);
		this.txt_NIT.setEnabled(false);
		this.txt_nombre_proveedor.setEnabled(false);
		this.txt_domicilio.setEnabled(false);
		this.txt_nombre_contacto.setEnabled(false);
		this.txt_telefono.setEnabled(false);
		this.txt_celular.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Proveedor.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.cb_ciudad.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_ciudad.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_ciudad.getCaption(), ex.getMessage()));
			}
		
			try {
				this.txt_NIT.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_NIT.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_NIT.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_nombre_proveedor.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_proveedor.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_proveedor.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_domicilio.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_domicilio.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_domicilio.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_nombre_contacto.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_contacto.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_contacto.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_telefono.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_telefono.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_telefono.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_celular.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_celular.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_celular.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public Proveedore getData(){
		Proveedore resul = new Proveedore();
		SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
		
		 short dependencia = dependencia_impl.getdependencia_ID(usuario.getDependecia());
		
		resul.setPRV_Dependencia(dependencia);
		resul.setPRV_Ciudad((short)this.cb_ciudad.getValue());
		resul.setPRV_NIT(this.txt_NIT.getValue());
		resul.setPRV_Nombre(this.txt_nombre_proveedor.getValue());
		resul.setPRV_Domicilio(this.txt_domicilio.getValue());
		resul.setPRV_Nombre_Contacto(this.txt_nombre_contacto.getValue());
		resul.setPRV_Telefono(this.txt_telefono.getValue());
		resul.setPRV_Celular_Contacto(this.txt_celular.getValue());
		long lnMilis = new Date().getTime();
		resul.setPRV_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(ProveedoresModel data){	
		this.cb_ciudad.setValue((Short)data.getPRV_Ciudad_ID());
		this.txt_NIT.setValue(String.valueOf(data.getPRV_NIT()));
		this.txt_nombre_proveedor.setValue(String.valueOf(data.getPRV_Nombre()));
		this.txt_domicilio.setValue(String.valueOf(data.getPRV_Domicilio()));
		this.txt_nombre_contacto.setValue(String.valueOf(data.getPRV_Nombre_Contacto()));
		this.txt_telefono.setValue(String.valueOf(data.getPRV_Telefono()));
		this.txt_celular.setValue(String.valueOf(data.getPRV_Celular_Contacto()));			
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
		
	}
}

