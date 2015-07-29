package ait.sistemas.proyecto.seguridad.view.usua.usuario;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
import ait.sistemas.proyecto.activos.data.service.Impl.PersonalImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.UsuarioGridModel;
import ait.sistemas.proyecto.seguridad.data.model.Usuario;

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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormUsuario extends GridLayout implements ValueChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	public ComboBox cbPersonal = new ComboBox("Funcionario");
	
	public TextField txtCI = new TextField("C.I.");
	public TextField txtIdenticadorUsuario = new TextField("Identicador Usuario");
	
	final PropertysetItem pitmUsuario = new PropertysetItem();
	private FieldGroup binderUsuario;
	private List<BarMessage> mesagges = new ArrayList<BarMessage>();
	
	public FormUsuario() {
		super(3, 2);
		setMargin(true);
		setWidth("100%");
		
		this.cbPersonal.setWidth("70%");
		
		this.txtCI.setWidth("90%");
		this.txtIdenticadorUsuario.setWidth("90%");
		
		pitmUsuario.addItemProperty("personal", new ObjectProperty<PersonalModel>(new PersonalModel()));
		pitmUsuario.addItemProperty("ci", new ObjectProperty<String>(""));
		pitmUsuario.addItemProperty("identificador", new ObjectProperty<String>(""));
		
		this.binderUsuario = new FieldGroup(this.pitmUsuario);
		
		binderUsuario.bind(this.cbPersonal, "personal");
		binderUsuario.bind(this.txtCI, "ci");
		binderUsuario.bind(this.txtIdenticadorUsuario, "identificador");
		
		this.txtCI.setEnabled(false);
		this.cbPersonal.addValidator(new NullValidator("", false));
		this.txtIdenticadorUsuario.addValidator(new NullValidator("", false));
		this.txtIdenticadorUsuario.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(10,
				12), 10, 12, false));
		this.cbPersonal.setRequired(true);
		this.txtIdenticadorUsuario.setRequired(true);
		buildForm();
	}
	
	private void buildForm() {
		this.binderUsuario.clear();
		
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 2);
		setColumnExpandRatio(2, 4);
		addComponent(this.cbPersonal, 0, 0, 2, 0);
		addComponent(this.txtCI, 0, 1);
		addComponent(this.txtIdenticadorUsuario, 1, 1);
		fullcbUsuario();
		this.cbPersonal.addValueChangeListener(this);
	}
	
	private void fullcbUsuario() {
		this.cbPersonal.setNullSelectionAllowed(false);
		this.cbPersonal.setInputPrompt("Seleccione un Personal");
		PersonalImpl personalimpl = new PersonalImpl();
		for (PersonalModel personal : personalimpl.getalls()) {
			this.cbPersonal.addItem(personal);
			String strNombreCompleto = String.format("%s %s %s", personal.getPER_Nombres(),
					personal.getPER_Apellido_Paterno(), personal.getPER_Apellido_Materno());
			this.cbPersonal.setItemCaption(personal, strNombreCompleto);
		}
	}
	
	private void fillData(PersonalModel value) {
		this.txtCI.setValue(value.getPER_CI_Empleado());
		String strIdentificador = String.format("%s-%s-%s", value.getPER_Nombres().substring(0, 2),
				value.getPER_Apellido_Paterno().substring(0, 4),
				value.getPER_Apellido_Materno().substring(0, 2)).toUpperCase();
		this.txtIdenticadorUsuario.setValue(strIdentificador);
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		PersonalModel value = (PersonalModel) this.cbPersonal.getValue();
		if (value != null) {
			fillData(value);
		}
	}
	
	public boolean validate() {
		this.mesagges = new ArrayList<BarMessage>();
		try {
			this.binderUsuario.commit();
			this.mesagges.add(new BarMessage("Form", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			this.mesagges.add(new BarMessage("Form", Messages.NOT_SUCCESS_MESSAGE));
			try {
				this.cbPersonal.validate();
			} catch (EmptyValueException e2) {
				this.mesagges.add(new BarMessage(cbPersonal.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.txtIdenticadorUsuario.validate();
			} catch (EmptyValueException e2) {
				this.mesagges.add(new BarMessage(txtIdenticadorUsuario.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ev) {
				this.mesagges.add(new BarMessage(txtIdenticadorUsuario.getCaption(), ev.getMessage()));
			}
			return false;
		}
		
	}
	
	public List<BarMessage> getMessages() {
		return this.mesagges;
	}
	
	public void clear() {
		this.binderUsuario.clear();
	}
	
	public Usuario getDataUsuario() {
		Usuario usuario = new Usuario();
		usuario.setUSU_CI_Empleado(this.txtCI.getValue());
		usuario.setUSU_Id_Usuario(this.txtIdenticadorUsuario.getValue());
		return usuario;
	}
	
	public void setDataUsuario(UsuarioGridModel usuario) {
		this.cbPersonal.removeAllItems();
		this.cbPersonal.removeValueChangeListener(this);
		this.cbPersonal.addItem(usuario.getFullName());
		this.cbPersonal.select(usuario.getFullName());
		this.txtCI.setValue(usuario.getCI());
		this.txtIdenticadorUsuario.setValue(usuario.getId());
		this.cbPersonal.addValueChangeListener(this);
	}
	
}
