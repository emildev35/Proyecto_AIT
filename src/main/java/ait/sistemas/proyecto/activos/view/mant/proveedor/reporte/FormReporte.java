package ait.sistemas.proyecto.activos.view.mant.proveedor.reporte;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.CiudadImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;

public class FormReporte extends GridLayout implements ValueChangeListener{

	private static final long serialVersionUID = 1L;

	public ComboBox cb_Ciudad;
	public ComboBox cb_Dependencia;

	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	final private CiudadImpl ciudad_impl = new CiudadImpl();
	final private DependenciaImpl dependencia_impl = new DependenciaImpl();

	final PropertysetItem pitm_Proveedor = new PropertysetItem();
	private FieldGroup binder_Proveedor;

	public FormReporte() {
		setColumns(2);
		setRows(1);
		setWidth("40%");
		setMargin(true);
		setSpacing(true);

		this.cb_Ciudad = new ComboBox("Elija una Ciudad");
		this.cb_Dependencia = new ComboBox("Elija una Dependencia");

		pitm_Proveedor.addItemProperty("ciudad", new ObjectProperty<Short>(
				(short) 1));
		pitm_Proveedor.addItemProperty("dependencia", new ObjectProperty<Short>(
				(short) 1));

		this.binder_Proveedor = new FieldGroup(pitm_Proveedor);

		this.binder_Proveedor.bind(this.cb_Ciudad, "ciudad");
		this.binder_Proveedor.bind(this.cb_Dependencia, "dependencia");

		this.cb_Ciudad.addValidator(new NullValidator("", false));
		this.cb_Dependencia.addValidator(new NullValidator("", false));
		this.cb_Ciudad.addValueChangeListener(this);
		this.cb_Ciudad.setRequired(true);
		this.cb_Dependencia.setRequired(true);
		fillcbCiudad();
		buildContent();
	}

	public void init() {
		update();
	}

	/**
	 * Actualizacion de los Campos
	 */
	public void update() {
		this.binder_Proveedor.clear();
		this.cb_Ciudad.setValue((short) 1);
		this.cb_Dependencia.setValue((short) 1);

	}

	/**
	 * Llenado del Combo Box
	 */
	private void fillcbCiudad() {
		cb_Ciudad.setNullSelectionAllowed(false);
		cb_Ciudad.setInputPrompt("Seleccione una Ciudad");
		for (Ciudade ciudad : ciudad_impl.getall()) {
			cb_Ciudad.addItem(ciudad.getCIU_Ciudad());
			cb_Ciudad.setItemCaption(ciudad.getCIU_Ciudad(),
					ciudad.getCIU_Nombre_Ciudad());
		}
		short a=90;
		cb_Ciudad.addItem(a);
		cb_Ciudad.setItemCaption(a,"Todas las ciudades");
	}

	private void fillcbDependencia(short padre) {
		cb_Dependencia.removeAllItems();
		cb_Dependencia.setNullSelectionAllowed(false);
		cb_Dependencia.setInputPrompt("Seleccione una Dependencia");
		for (Dependencia dependencia : dependencia_impl.getdependencia_ciudad(padre)) {
			cb_Dependencia.addItem(dependencia.getDEP_Dependencia());
			cb_Dependencia.setItemCaption(dependencia.getDEP_Dependencia(),dependencia.getDEP_Nombre_Dependencia());
		}
	}

	private void buildContent() {
		this.binder_Proveedor.clear();
		this.cb_Ciudad.setWidth("100%");
		this.cb_Dependencia.setWidth("100%");
		setColumnExpandRatio(0, 2);
		setColumnExpandRatio(1, 2);
		addComponent(this.cb_Ciudad, 0, 0);
		addComponent(this.cb_Dependencia, 1, 0);

	}

	public boolean validate() {
		try {
			this.binder_Proveedor.commit();
			return true;
		} catch (CommitException ce) {
			try {
				this.cb_Ciudad.validate();
				return true;
			} catch (Exception e) {
				this.mensajes.add(new BarMessage(this.cb_Ciudad.getCaption(),
						Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_Dependencia.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_Dependencia
						.getCaption(), Messages.EMPTY_MESSAGE));
			}
			return false;
		}
	}

	public List<BarMessage> getMessage() {
		return this.mensajes;
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		//updateId();
		if(event.getProperty().getValue() == this.cb_Ciudad.getValue() && (Short)this.cb_Ciudad.getValue() != null){
			fillcbDependencia(((short)this.cb_Ciudad.getValue()));
		}
		
	}
}
