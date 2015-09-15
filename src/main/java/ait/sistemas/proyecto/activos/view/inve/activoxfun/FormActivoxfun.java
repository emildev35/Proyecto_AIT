package ait.sistemas.proyecto.activos.view.inve.activoxfun;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.UnidadesOrganizacionalesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.PersonalImpl;
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
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;

public class FormActivoxfun extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	
	public ComboBox cb_unidad_organizacional = new ComboBox("Unidad Organizacional");
	public ComboBox cb_funcionario = new ComboBox("Funcionario");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private PropertysetItem pitm_activosxfun = new PropertysetItem();
	private FieldGroup binder_activosxfun;
	
	private final UnidadImpl unidad_impl = new UnidadImpl();
	private final PersonalImpl personal_impl = new PersonalImpl();
	
	
	public FormActivoxfun() {
		
		super(7, 4);
		setSpacing(true);
		setWidth("100%");
		
		pitm_activosxfun.addItemProperty("unidad_organizacional", new ObjectProperty<UnidadesOrganizacionalesModel>(new UnidadesOrganizacionalesModel()));
		pitm_activosxfun.addItemProperty("funcionario", new ObjectProperty<PersonalModel>(
				new PersonalModel()));
		pitm_activosxfun.addItemProperty("observaciones", new ObjectProperty<String>(""));

		this.binder_activosxfun = new FieldGroup(this.pitm_activosxfun);
		
		binder_activosxfun.bind(this.cb_unidad_organizacional, "unidad_organizacional");
		binder_activosxfun.bind(this.cb_funcionario, "funcionario");
		binder_activosxfun.clear();
		
		this.cb_unidad_organizacional.setRequired(true);
		this.cb_unidad_organizacional.addValidator(new NullValidator("No Nulo", false));
		cb_unidad_organizacional.setInputPrompt("Seleccione la Unidad Organizacional");
		cb_unidad_organizacional.addValueChangeListener(this);
		this.cb_funcionario.setRequired(true);
		cb_funcionario.addValueChangeListener(this);
		this.cb_funcionario.addValidator(new NullValidator("No Nulo", false));
		cb_funcionario.setInputPrompt("Seleccione el Funcionario");
		
		cb_unidad_organizacional.setWidth("90%");
		cb_funcionario.setWidth("90%");
		
		fillcbUnidadOrganizacional();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	private void fillcbUnidadOrganizacional() {
		cb_funcionario.setNullSelectionAllowed(false);
		for (UnidadesOrganizacionalesModel unidad_organizacional : unidad_impl.getalls()) {
			cb_unidad_organizacional.addItem(unidad_organizacional);
			cb_unidad_organizacional.setItemCaption(unidad_organizacional, unidad_organizacional.getUNO_Nombre_Unidad_Organizacional());
		}
	}
	private void fillcbPersonal(short id_unidad) {
		cb_funcionario.removeAllItems();
		cb_funcionario.setNullSelectionAllowed(false);
		for (PersonalModel personal : personal_impl.getbyUnidad(id_unidad)) {
			cb_funcionario.addItem(personal);
			cb_funcionario.setItemCaption(personal, personal.getPER_Nombres() + personal.getPER_Apellido_Paterno() + personal.getPER_Apellido_Materno());
		}
	}
	
	private void buildContent() {
		
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		addComponent(this.cb_unidad_organizacional, 0,1);
		addComponent(this.cb_funcionario, 1,1);
		
	}
	
	public void update() {
		binder_activosxfun.clear();
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}

	public boolean validate() {
		
		try {
			this.binder_activosxfun.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_unidad_organizacional.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_unidad_organizacional.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_unidad_organizacional.getCaption(), ex.getMessage()));
			}
			try {
				this.cb_funcionario.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_funcionario.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_funcionario.getCaption(), ex.getMessage()));
			}
			
			return false;
		}
	}
	
	public PersonalModel getData(){
		PersonalModel resul = new PersonalModel();
		resul.setPER_Unidad_Organizacional_ID(((UnidadesOrganizacionalesModel)this.cb_unidad_organizacional.getValue()).getUNO_Unidad_Organizacional());
		resul.setPER_CI_Empleado(((PersonalModel)this.cb_funcionario.getValue()).getPER_CI_Empleado());
		return resul;
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty() == cb_unidad_organizacional && this.cb_unidad_organizacional.getValue() != null) {
			UnidadesOrganizacionalesModel grupo = (UnidadesOrganizacionalesModel) cb_unidad_organizacional.getValue();
			fillcbPersonal(grupo.getUNO_Unidad_Organizacional());
		}
	}

	public void clear() {
		this.binder_activosxfun.clear();
	}
}
