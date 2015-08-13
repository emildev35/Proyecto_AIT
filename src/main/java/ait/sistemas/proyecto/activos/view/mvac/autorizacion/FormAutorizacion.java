package ait.sistemas.proyecto.activos.view.mvac.autorizacion;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.Auth;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;

public class FormAutorizacion extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	private PasswordField txt_pin = new PasswordField("PIN");
	private TextArea tarea_motivo = new TextArea("Descripcion del Motivo");
	
	public List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private PropertysetItem pitm_autorizacion = new PropertysetItem();
	private FieldGroup binder_autorizacion;
	
	public FormAutorizacion() {
		super(2, 1);
		setWidth("100%");
		setMargin(true);
		pitm_autorizacion.addItemProperty("pin", new ObjectProperty<String>(""));
		pitm_autorizacion.addItemProperty("motivo", new ObjectProperty<String>(""));
		binder_autorizacion = new FieldGroup(pitm_autorizacion);
		binder_autorizacion.bind(txt_pin, "pin");
		binder_autorizacion.bind(tarea_motivo, "motivo");
		
		this.txt_pin.setRequired(true);
		this.tarea_motivo.setRequired(true);
		
		txt_pin.setWidth("90%");
		tarea_motivo.setWidth("90%");
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 4);
		addComponent(txt_pin, 0, 0);
		addComponent(tarea_motivo, 1, 0);
	}
	
	public List<BarMessage> getMensajes() {
		return this.mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		try{

			this.binder_autorizacion.commit();
			return true;
		}catch(CommitException ex)
		{
			try{
				this.txt_pin.validate();
			}catch(EmptyValueException eve){
				this.mensajes.add(new BarMessage(txt_pin.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try{
				this.tarea_motivo.validate();
			}catch(EmptyValueException eve){
				this.mensajes.add(new BarMessage(tarea_motivo.getCaption(), Messages.EMPTY_MESSAGE));
			}
			this.mensajes.add(new BarMessage("Fomulario", Messages.NOT_SUCCESS_MESSAGE));
			return false;
		}
	}

	public String getPIN() {
		return Auth.hash(this.txt_pin.getValue().toString());
	}
}
