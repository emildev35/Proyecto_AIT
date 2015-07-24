package ait.sistemas.proyecto.seguridad.view.login;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginForm extends GridLayout implements TextChangeListener {

	private static final long serialVersionUID = 1L;

	public TextField txt_id_usuario = new TextField("Identificador de Usuario");
	public PasswordField pwd_password = new PasswordField(
			"Contraseña de Usuario");
	public PasswordField pwd_password_verification = new PasswordField(
			"Confirme Contraseña");

	final PropertysetItem pitm_login = new PropertysetItem();
	private FieldGroup binder_login;
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();

	private final UsuarioImpl usuarioimpl = new UsuarioImpl();

	public LoginForm() {
		super(1, 3);
		setWidth("100%");
		setMargin(true);
		this.pitm_login.addItemProperty("usuario", new ObjectProperty<String>(
				""));
		this.pitm_login.addItemProperty("password", new ObjectProperty<String>(
				""));
		this.binder_login = new FieldGroup(pitm_login);
		this.binder_login.bind(this.txt_id_usuario, "usuario");
		this.binder_login.bind(this.pwd_password, "password");
		this.txt_id_usuario .addTextChangeListener(this);

		// Validation for txt_id_usuario
		this.txt_id_usuario.setRequired(true);
		this.txt_id_usuario.addValidator(new NullValidator("", false));
		this.txt_id_usuario.addValidator(new StringLengthValidator(Messages
				.STRING_LENGTH_MESSAGE(10, 12), 10, 12, false));
		// Validation for pwd_password
		// this.pwd_password.addValidator(new
		// StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(8, 30), 8 ,30,
		// false));
		this.pwd_password.setRequired(true);
		this.pwd_password
				.addValidator(new RegexpValidator(
						"^(?=^.{8,}$)(?=.*\\d)(?=.*\\W+)(?=.*[a-z])(?=.*[A-Z])(?!^.*\\n).*$",
						Messages.PASSWORD_MESSAGE));

		buildForm();
		Responsive.makeResponsive(this);
	}

	private void buildForm() {
		this.binder_login.clear();
		addComponent(this.txt_id_usuario, 0, 0);
		addComponent(this.pwd_password, 0, 1);
	}

	public boolean validate() {
		this.mensajes = new ArrayList<BarMessage>();
		try {
			this.binder_login.commit();
			if(getComponent(0, 2) !=null){
				
				if(!this.pwd_password.getValue().equals(this.pwd_password_verification.getValue())){
					this.mensajes.add(new BarMessage(this.pwd_password_verification.getCaption(), Messages.ERROR_EQUALS_PASSWORD));
					return false;
				}

			}
			return true;
		} catch (Exception e) {
			try {
				this.txt_id_usuario.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(this.txt_id_usuario
						.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException sle) {
				this.mensajes.add(new BarMessage(this.txt_id_usuario
						.getCaption(), sle.getMessage()));
			}
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

	@Override
	public void textChange(TextChangeEvent event) {
		if (event.getText().length() >= 5) {
			int result = this.usuarioimpl.isNewUser(event.getText());
			switch (result) {
			case -1:
				if (getComponent(0, 2) != null) {
					removeComponent(this.pwd_password_verification);
				}
				this.pwd_password.setEnabled(false);
				break;
			case 0:
				this.pwd_password.setEnabled(true);
				if (getComponent(0, 2) == null) {
					addComponent(this.pwd_password_verification, 0, 2);
				}
				break;
			case 1:
				this.pwd_password.setEnabled(true);
				if (getComponent(0, 2) != null) {
					removeComponent(this.pwd_password_verification);
				}
				break;
			}
		}
	}

	public boolean isNew() {
		if(getComponent(0, 2)!=null){
			return true;
		}
		return false;
	}

	public String getUsuario() {
		return this.txt_id_usuario.getValue();
	}

	public String getPassword() {
		return this.pwd_password.getValue();
	}

}
