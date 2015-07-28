package ait.sistemas.proyecto.activos.view.rrhh.ciudad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.service.Impl.CiudadImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormCiudad extends GridLayout {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_ciudad;
	public TextField txt_nombre_ciudad;
	private TextField txt_sigla_ciudad;
	private List<BarMessage> mensajes;

	private CiudadImpl ciudad_impl = new CiudadImpl();
	private PropertysetItem pitm_Ciudad = new PropertysetItem();
	private FieldGroup binder_Ciudad;

	public FormCiudad() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_ciudad = new TextField("Id. Ciudad:");
		this.txt_nombre_ciudad = new TextField("Nombre de la Ciudad: ");
		this.txt_sigla_ciudad = new TextField("Sigla de la Ciudad: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Ciudad.addItemProperty("id_ciudad", new ObjectProperty<Integer>(0));
		pitm_Ciudad.addItemProperty("nombre_ciudad", new ObjectProperty<String>(""));
		pitm_Ciudad.addItemProperty("sigla_ciudad", new ObjectProperty<String>(""));

		this.binder_Ciudad = new FieldGroup(this.pitm_Ciudad);

		binder_Ciudad.bind(this.txt_id_ciudad, "id_ciudad");
		binder_Ciudad.bind(this.txt_nombre_ciudad, "nombre_ciudad");
		binder_Ciudad.bind(this.txt_sigla_ciudad, "sigla_ciudad");

		this.txt_nombre_ciudad.setRequired(true);
		this.txt_nombre_ciudad.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_ciudad.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 15), 3,15,false));
		this.txt_sigla_ciudad.setRequired(true);
		this.txt_sigla_ciudad.addValidator(new NullValidator("No Nulo", false));
		this.txt_sigla_ciudad.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(2, 5), 2,5,false));
		this.txt_id_ciudad.setEnabled(false);

		txt_id_ciudad.setWidth("90%");
		txt_nombre_ciudad.setWidth("90%");
		txt_sigla_ciudad.setWidth("90%");

		updateId();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void buildContent() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		addComponent(this.txt_id_ciudad, 0,1);
		addComponent(this.txt_nombre_ciudad, 1,1);
		addComponent(this.txt_sigla_ciudad, 2,1);

	}
	
	public void update(){
		binder_Ciudad.clear();
		
	}
	public void updateId(){
		this.txt_id_ciudad.setValue(ciudad_impl.generateId() + "");
	}
	public void enabled(){
		this.txt_nombre_ciudad.setEnabled(false);
		this.txt_sigla_ciudad.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Ciudad.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.txt_nombre_ciudad.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_ciudad.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_ciudad.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_sigla_ciudad.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_sigla_ciudad.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_sigla_ciudad.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public Ciudade getData(){
		Ciudade resul = new Ciudade();
		resul.setCIU_Ciudad(Short.parseShort(this.txt_id_ciudad.getValue()));
		resul.setCIU_Nombre_Ciudad(this.txt_nombre_ciudad.getValue());
		resul.setCIU_Sigla(this.txt_sigla_ciudad.getValue());
		long lnMilis = new Date().getTime();
		resul.setCIU_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(Ciudade data){	
		this.txt_id_ciudad.setValue(String.valueOf(data.getCIU_Ciudad()));
		this.txt_nombre_ciudad.setValue(String.valueOf(data.getCIU_Nombre_Ciudad()));
		this.txt_sigla_ciudad.setValue(String.valueOf(data.getCIU_Sigla()));
				
	}
}
