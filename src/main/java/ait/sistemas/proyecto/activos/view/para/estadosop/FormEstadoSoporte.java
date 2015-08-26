package ait.sistemas.proyecto.activos.view.para.estadosop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.EstadoSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.EstadoSoporteImpl;
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

public class FormEstadoSoporte extends GridLayout {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_estado_soporte;
	public TextField txt_nombre_esoporte;
	private List<BarMessage> mensajes;

	private EstadoSoporteImpl estado_soporteimpl = new EstadoSoporteImpl();
	private PropertysetItem pitm_esoporte = new PropertysetItem();
	private FieldGroup binder_esoporte;

	public FormEstadoSoporte() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_estado_soporte = new TextField("Id. Estado Soporte:");
		this.txt_nombre_esoporte = new TextField("Descripcion del Estado de Soporte: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_esoporte.addItemProperty("id_estado_soporte", new ObjectProperty<Integer>(0));
		pitm_esoporte.addItemProperty("nombre_estado_soporte", new ObjectProperty<String>(""));
		pitm_esoporte.addItemProperty("sigla", new ObjectProperty<String>(""));

		this.binder_esoporte = new FieldGroup(this.pitm_esoporte);

		binder_esoporte.bind(this.txt_id_estado_soporte, "id_estado_soporte");
		binder_esoporte.bind(this.txt_nombre_esoporte, "nombre_estado_soporte");

		this.txt_nombre_esoporte.setRequired(true);
		this.txt_nombre_esoporte.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_esoporte.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_id_estado_soporte.setEnabled(false);

		txt_id_estado_soporte.setWidth("90%");
		txt_nombre_esoporte.setWidth("90%");

		updateId();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void buildContent() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		addComponent(this.txt_id_estado_soporte, 0,1);
		addComponent(this.txt_nombre_esoporte, 1,1);

	}
	
	public void update(){
		binder_esoporte.clear();
		this.txt_id_estado_soporte.setValue("0");
	}
	public void updateId(){
		this.txt_id_estado_soporte.setValue(estado_soporteimpl.generateId() + "");
	}
	public void enabled(){
		this.txt_nombre_esoporte.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_esoporte.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.txt_nombre_esoporte.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_esoporte.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_esoporte.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public EstadoSoporte getData(){
		EstadoSoporte result = new EstadoSoporte();
		result.setId(Short.parseShort(this.txt_id_estado_soporte.getValue()));
		result.setNombre(this.txt_nombre_esoporte.getValue());
		long lnMilis = new Date().getTime();
		result.setFecha_registro(new java.sql.Date(lnMilis));
		return result;
	}
	public void setData(EstadoSoporte data){	
		this.txt_id_estado_soporte.setValue(String.valueOf(data.getId()));
		this.txt_nombre_esoporte.setValue(String.valueOf(data.getNombre()));
				
	}
}
