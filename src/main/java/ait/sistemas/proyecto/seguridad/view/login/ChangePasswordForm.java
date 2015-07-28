package ait.sistemas.proyecto.seguridad.view.login;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.PasswordField;


public class ChangePasswordForm extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	public PasswordField pwd_password = new PasswordField("Contraseña de Usuario");
	public PasswordField pwd_password_confirmation = new PasswordField("Confirme Contraseña");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	final PropertysetItem pitm_reset_password = new PropertysetItem();
	private FieldGroup binder_reset_password;
	
	public ChangePasswordForm() {
		super(1, 2);
		setWidth("100%");
		this.pitm_reset_password.addItemProperty("password", new ObjectProperty<String>(""));
		this.pitm_reset_password.addItemProperty("password_confirm", new ObjectProperty<String>(""));
		this.binder_reset_password = new FieldGroup(pitm_reset_password);
		this.binder_reset_password.bind(this.pwd_password, "password");
		this.binder_reset_password.bind(this.pwd_password_confirmation, "password_confirm");
		
		this.pwd_password.setRequired(true);
		this.pwd_password
		.addValidator(new RegexpValidator(
				"^(?=^.{8,}$)(?=.*\\d)(?=.*\\W+)(?=.*[a-z])(?=.*[A-Z])(?!^.*\\n).*$",
				Messages.PASSWORD_MESSAGE));
		this.pwd_password_confirmation.setRequired(true);
		buildForm();
	}
	
	private void buildForm() {
		addComponent(this.pwd_password, 0, 0);
		addComponent(this.pwd_password_confirmation, 0, 1);
	}
	
	public boolean validate() {
		this.mensajes = new ArrayList<BarMessage>();
		try {
			this.binder_reset_password.commit();
			if(getComponent(0, 2) !=null){
				
				if(!this.pwd_password.getValue().equals(this.pwd_password_confirmation.getValue())){
					this.mensajes.add(new BarMessage(this.pwd_password_confirmation.getCaption(), Messages.ERROR_EQUALS_PASSWORD));
					return false;
				}

			}
			return true;
		} catch (Exception e) {
			try {
				this.pwd_password.validate();
			
			} catch (EmptyValueException eve) {
				this.mensajes
						.add(new BarMessage(this.pwd_password.getCaption(),
								Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException sle) {
				this.mensajes.add(new BarMessage(
						this.pwd_password.getCaption(), sle.getMessage()));
			}
			
			return false;
		}
	}

	public List<BarMessage> getMessages() {
		return this.mensajes;
	}

	public void clear() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	public String getPassword() {
		return this.pwd_password.getValue();
	}
	
}
