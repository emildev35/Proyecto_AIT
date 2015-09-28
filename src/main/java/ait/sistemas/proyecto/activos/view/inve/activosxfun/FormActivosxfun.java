package ait.sistemas.proyecto.activos.view.inve.activosxfun;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.UnidadesOrganizacionalesModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Unidades_Organizacionale;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.PersonalImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UnidadImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FormActivosxfun extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;

	public DateField dt_fecha = new DateField("Fecha:");

	public TextField txt_ci = new TextField("No C.I.:");

	public ComboBox cb_dependencia = new ComboBox("Dependencia");
	public ComboBox cb_unidad_organizacional = new ComboBox("Unidad Organizacional");
	public ComboBox cb_funcionario = new ComboBox("Funcionario Publico");

	private List<BarMessage> mensajes = new ArrayList<BarMessage>();

	private PropertysetItem pitm_activosxfun = new PropertysetItem();
	private FieldGroup binder_activosxfun;

	private final DependenciaImpl dependencia_impl = new DependenciaImpl();
	private final UnidadImpl unidad_impl = new UnidadImpl();
	private final PersonalImpl personal_impl = new PersonalImpl();

	public FormActivosxfun() {

		super(7, 5);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		pitm_activosxfun.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));
		pitm_activosxfun.addItemProperty("ci", new ObjectProperty<String>(""));
		pitm_activosxfun.addItemProperty("dependencia", new ObjectProperty<Dependencia>(new Dependencia()));
		pitm_activosxfun.addItemProperty("unidad_organizacional", new ObjectProperty<Unidades_Organizacionale>(
				new Unidades_Organizacionale()));
		pitm_activosxfun.addItemProperty("funcionario", new ObjectProperty<PersonalModel>(new PersonalModel()));

		this.binder_activosxfun = new FieldGroup(this.pitm_activosxfun);

		binder_activosxfun.bind(this.dt_fecha, "fecha");
		binder_activosxfun.bind(this.txt_ci, "ci");
		binder_activosxfun.bind(this.cb_dependencia, "dependencia");
		binder_activosxfun.bind(this.cb_unidad_organizacional, "unidad_organizacional");
		binder_activosxfun.bind(this.cb_funcionario, "funcionario");
		binder_activosxfun.clear();

		this.dt_fecha.setRequired(true);
		this.cb_funcionario.setRequired(true);
		// this.cb_unidad_organizacional.addValidator(new
		// NullValidator("No Nulo", false));
		cb_dependencia.setInputPrompt("Dependencia");
		cb_dependencia.addValueChangeListener(this);
		cb_unidad_organizacional.setInputPrompt("Seleccione la Unidad Organizacional");
		cb_unidad_organizacional.addValueChangeListener(this);
		// this.cb_funcionario.setRequired(true);
		cb_funcionario.addValueChangeListener(this);
		// this.cb_funcionario.addValidator(new NullValidator("No Nulo",
		// false));
		cb_funcionario.setInputPrompt("Seleccione el Funcionario");

		dt_fecha.setWidth("90%");
		txt_ci.setWidth("90%");
		cb_dependencia.setWidth("90%");
		cb_unidad_organizacional.setWidth("90%");
		cb_funcionario.setWidth("90%");
		fillfecha();
		fillcbDependencia();
		buildContent();
		Responsive.makeResponsive(this);
	}

	private void fillfecha() {

		this.dt_fecha.setValue(new Date());
	}

	private void fillcbDependencia() {
		cb_dependencia.removeAllItems();
		cb_funcionario.removeAllItems();
		for (Dependencia dependencia : dependencia_impl.getall()) {
			cb_dependencia.addItem(dependencia);
			cb_dependencia.setItemCaption(dependencia,
					dependencia.getDEP_Nombre_Dependencia());
		}
	}

	private void fillcbUnidadOrganizacional(short id_depepndencia) {
		cb_unidad_organizacional.removeAllItems();
		cb_unidad_organizacional.setNullSelectionAllowed(false);
		for (Unidades_Organizacionale unidad_organizacional : unidad_impl.getunidad(id_depepndencia)) {
			cb_unidad_organizacional.addItem(unidad_organizacional);
			cb_unidad_organizacional.setItemCaption(unidad_organizacional,
					unidad_organizacional.getUNO_Nombre_Unidad_Organizacional());
		}
	}

	private void fillcbPersonal(short id_unidad) {
		cb_funcionario.removeAllItems();
		cb_funcionario.setNullSelectionAllowed(false);
		for (PersonalModel personal : personal_impl.getbyUnidad(id_unidad)) {
			cb_funcionario.addItem(personal);
			cb_funcionario.setItemCaption(
					personal,
					personal.getPER_Nombres() + " " + personal.getPER_Apellido_Paterno() + " "
							+ personal.getPER_Apellido_Materno());
		}
	}

	private void buildContent() {

		GridLayout grid_fecha = new GridLayout();
		grid_fecha.setWidth("100%");
		grid_fecha.setMargin(true);
		grid_fecha.setSpacing(true);
		grid_fecha.addComponent(dt_fecha);
		VerticalLayout vl_form = new VerticalLayout();
		Panel pn_form = new Panel("Fecha Elaboracion:  ");
		pn_form.setIcon(FontAwesome.EDIT);
		pn_form.setStyleName(AitTheme.PANEL_PRINT);
		pn_form.setContent(grid_fecha);

		vl_form.addComponent(pn_form);

		GridLayout grid_ci = new GridLayout(1, 1);
		grid_ci.setWidth("100%");
		grid_ci.setMargin(true);
		grid_ci.setSpacing(true);
		grid_ci.addComponent(txt_ci, 0, 0);
		Panel pn_ci = new Panel("Seleccion por No C.I. Funcionario:  ");
		pn_ci.setIcon(FontAwesome.EDIT);
		pn_ci.setStyleName(AitTheme.PANEL_PRINT);
		pn_ci.setContent(grid_ci);
		vl_form.setMargin(true);
		vl_form.addComponent(pn_ci);

		GridLayout grid_dependencia = new GridLayout(1, 3);
		grid_dependencia.setWidth("100%");
		grid_dependencia.setMargin(true);
		grid_dependencia.setSpacing(true);
		grid_dependencia.addComponent(cb_dependencia, 0, 0);
		grid_dependencia.addComponent(cb_unidad_organizacional, 0, 1);
		grid_dependencia.addComponent(cb_funcionario, 0, 2);
		Panel pn_dep = new Panel("Seleccione por Dependnecia, Unidad y Funcionario:  ");
		pn_dep.setIcon(FontAwesome.EDIT);
		pn_dep.setStyleName(AitTheme.PANEL_PRINT);
		pn_dep.setContent(grid_dependencia);
		vl_form.addComponent(pn_dep);

		addComponent(pn_form, 0, 0, 1, 0);
		addComponent(pn_ci, 2, 0, 3, 0);
		addComponent(pn_dep, 2, 1, 3, 1);
	}

	public void update() {
		binder_activosxfun.clear();
		this.dt_fecha.setValue(new Date());
	}

	public List<BarMessage> getMensajes() {
		return mensajes;
	}

	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}

	public boolean validate() {
		if (txt_ci.getValue() != null && !txt_ci.getValue().equals("")) {
			return true;
		}
		try {
			this.binder_activosxfun.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_dependencia.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_dependencia.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_unidad_organizacional.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_unidad_organizacional.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_funcionario.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_funcionario.getCaption(), Messages.EMPTY_MESSAGE));
			}
			return false;
		}
	}

	public PersonalModel getData() {
		PersonalModel resul = new PersonalModel();
		if (this.txt_ci.getValue() == null) {
			resul.setPER_Unidad_Organizacional_ID(((UnidadesOrganizacionalesModel) this.cb_unidad_organizacional
					.getValue()).getUNO_Unidad_Organizacional());
			resul.setPER_CI_Empleado(((PersonalModel) this.cb_funcionario.getValue()).getPER_CI_Empleado());
		} else {
			resul.setPER_CI_Empleado(this.txt_ci.getValue());
		}
		return resul;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty() == cb_dependencia && this.cb_dependencia.getValue() != null) {
			Dependencia grupo = (Dependencia) cb_dependencia.getValue();
			fillcbUnidadOrganizacional(grupo.getDEP_Dependencia());
		}
		if (event.getProperty() == cb_unidad_organizacional && this.cb_unidad_organizacional.getValue() != null) {
			Unidades_Organizacionale grupo = (Unidades_Organizacionale) cb_unidad_organizacional.getValue();
			fillcbPersonal(grupo.getUNO_Unidad_Organizacional());
		}
	}

	public void clear() {
		this.binder_activosxfun.clear();
	}
}
