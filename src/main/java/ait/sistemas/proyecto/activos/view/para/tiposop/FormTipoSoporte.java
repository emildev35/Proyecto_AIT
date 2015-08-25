package ait.sistemas.proyecto.activos.view.para.tiposop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.TipoSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoSoporteImpl;
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

public class FormTipoSoporte extends GridLayout {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_tipo_soporte;
	public TextField txt_nombre_tsoporte;
	private TextField txt_sigla;
	private List<BarMessage> mensajes;

	private TipoSoporteImpl tipo_soporteimpl = new TipoSoporteImpl();
	private PropertysetItem pitm_tsoporte = new PropertysetItem();
	private FieldGroup binder_tsoporte;

	public FormTipoSoporte() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_tipo_soporte = new TextField("Id. Tipo Soporte:");
		this.txt_nombre_tsoporte = new TextField("Nombre del Tipo de Soporte: ");
		this.txt_sigla = new TextField("Sigla del Tipo de Soporte: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_tsoporte.addItemProperty("id_tipo_soporte", new ObjectProperty<Integer>(0));
		pitm_tsoporte.addItemProperty("nombre_tipo_soporte", new ObjectProperty<String>(""));
		pitm_tsoporte.addItemProperty("sigla", new ObjectProperty<String>(""));

		this.binder_tsoporte = new FieldGroup(this.pitm_tsoporte);

		binder_tsoporte.bind(this.txt_id_tipo_soporte, "id_tipo_soporte");
		binder_tsoporte.bind(this.txt_nombre_tsoporte, "nombre_tipo_soporte");
		binder_tsoporte.bind(this.txt_sigla, "sigla");

		this.txt_nombre_tsoporte.setRequired(true);
		this.txt_nombre_tsoporte.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_tsoporte.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_sigla.setRequired(true);
		this.txt_sigla.addValidator(new NullValidator("No Nulo", false));
		this.txt_sigla.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(2, 12), 2,12,false));
		this.txt_id_tipo_soporte.setEnabled(false);

		txt_id_tipo_soporte.setWidth("90%");
		txt_nombre_tsoporte.setWidth("90%");
		txt_sigla.setWidth("90%");

		updateId();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void buildContent() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		addComponent(this.txt_id_tipo_soporte, 0,1);
		addComponent(this.txt_nombre_tsoporte, 1,1);
		addComponent(this.txt_sigla, 2,1);

	}
	
	public void update(){
		binder_tsoporte.clear();
		this.txt_id_tipo_soporte.setValue("0");
	}
	public void updateId(){
		this.txt_id_tipo_soporte.setValue(tipo_soporteimpl.generateId() + "");
	}
	public void enabled(){
		this.txt_nombre_tsoporte.setEnabled(false);
		this.txt_sigla.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_tsoporte.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.txt_nombre_tsoporte.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_tsoporte.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_tsoporte.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_sigla.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_sigla.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_sigla.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public TipoSoporte getData(){
		TipoSoporte result = new TipoSoporte();
		result.setId(Short.parseShort(this.txt_id_tipo_soporte.getValue()));
		result.setNombre(this.txt_nombre_tsoporte.getValue());
		result.setSigla(this.txt_sigla.getValue());
		long lnMilis = new Date().getTime();
		result.setFecha_registro(new java.sql.Date(lnMilis));
		return result;
	}
	public void setData(TipoSoporte data){	
		this.txt_id_tipo_soporte.setValue(String.valueOf(data.getId()));
		this.txt_nombre_tsoporte.setValue(String.valueOf(data.getNombre()));
		this.txt_sigla.setValue(String.valueOf(data.getSigla()));
				
	}
}
