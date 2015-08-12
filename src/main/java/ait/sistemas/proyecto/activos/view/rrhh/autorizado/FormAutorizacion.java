package ait.sistemas.proyecto.activos.view.rrhh.autorizado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.TipoAutorizacionModel;
import ait.sistemas.proyecto.activos.data.model.Tipos_Movimiento;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Unidades_Organizacionale;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.PersonalImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposmovImpl;
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
import com.vaadin.ui.TextField;

public class FormAutorizacion extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	
	public ComboBox cb_tipo_movimiento = new ComboBox("Tipo de  Movimiento");
	private TextField txt_orden = new TextField("Orden");
	public ComboBox cb_dependencia = new ComboBox("Dependencia");
	public ComboBox cb_unidad = new ComboBox("Unidad Organizacional");
	public ComboBox cb_servidor_publico = new ComboBox("Sevidor Publico");
	public ComboBox cb_nivel_autorizacion = new ComboBox("Nivel de Autorizacion");
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private final DependenciaImpl dependencia_impl = new DependenciaImpl();
	private final UnidadImpl unidad_impl = new UnidadImpl();
	private final PersonalImpl personal_impl = new PersonalImpl();
	private final TiposmovImpl tipomov_impl = new TiposmovImpl();
	
	private PropertysetItem pitm_autorizacion = new PropertysetItem();
	private FieldGroup binder_autorizacion;
	
	public FormAutorizacion() {
		
		super.setColumns(4);
		super.setRows(2);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		
		pitm_autorizacion.addItemProperty("tipo_movimiento", new ObjectProperty<Tipos_Movimiento>(new Tipos_Movimiento()));
		pitm_autorizacion.addItemProperty("servidor_publico", new ObjectProperty<PersonalModel>(new PersonalModel()));
		pitm_autorizacion.addItemProperty("nivel_autorizacion", new ObjectProperty<Integer>(0));
		pitm_autorizacion.addItemProperty("orden", new ObjectProperty<Integer>(1));
		
		this.binder_autorizacion = new FieldGroup(this.pitm_autorizacion);
		
		binder_autorizacion.bind(this.cb_nivel_autorizacion, "nivel_autorizacion");
		binder_autorizacion.bind(this.cb_tipo_movimiento, "tipo_movimiento");
		binder_autorizacion.bind(this.cb_servidor_publico, "servidor_publico");
		binder_autorizacion.bind(this.txt_orden, "orden");
		
		this.cb_servidor_publico.setRequired(true);
		this.cb_servidor_publico.addValidator(new NullValidator("No Nulo", false));
		
		this.txt_orden.setRequired(true);
		this.txt_orden.addValidator(new NullValidator("No Nulo", false));
//		this.txt_orden.addValidator(new RegexpValidator("[0-9]", Messages.NUMBER_VALUE));
		
		this.cb_nivel_autorizacion.setRequired(true);
		this.cb_nivel_autorizacion.addValidator(new NullValidator("No Nulo", false));
		
		this.cb_tipo_movimiento.setRequired(true);
		this.cb_tipo_movimiento.addValidator(new NullValidator("No Nulo", false));
		
		this.cb_dependencia.addValueChangeListener(this);
		this.cb_unidad.addValueChangeListener(this);
		
		txt_orden.setWidth("30%");
		cb_dependencia.setWidth("90%");
		cb_unidad.setWidth("90%");
		cb_servidor_publico.setWidth("90%");
		cb_nivel_autorizacion.setWidth("90%");
		cb_tipo_movimiento.setWidth("90%");
		
		buildContent();
		buildCbDependencia();
		buildCbTipoMovimiento();
		buildCbNivelAutorizacion();
		Responsive.makeResponsive(this);
		
	}
	
	private void buildContent() {
		this.binder_autorizacion.clear();
		this.txt_orden.setValue("0");
		addComponent(this.cb_tipo_movimiento, 0, 0);
		addComponent(this.txt_orden, 1, 0);
		addComponent(this.cb_dependencia, 0, 1);
		addComponent(this.cb_unidad, 1, 1);
		addComponent(this.cb_servidor_publico, 2, 1);
		addComponent(this.cb_nivel_autorizacion, 3, 1);
	}
	
	public void update() {
		binder_autorizacion.clear();
		txt_orden.setValue("1");
	}
	
	// public void enabled() {
	// this.txt_nombre_ciudad.setEnabled(false);
	// this.txt_sigla_ciudad.setEnabled(false);
	// }
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public void buildCbDependencia() {
		cb_dependencia.setInputPrompt("Seleccione una Dependencia");
		cb_dependencia.setNullSelectionAllowed(false);
		for (Dependencia dependencia : dependencia_impl.getall()) {
			this.cb_dependencia.addItem(dependencia);
			this.cb_dependencia.setItemCaption(dependencia, dependencia.getDEP_Nombre_Dependencia());
		}
	}
	
	public void buildCbNivelAutorizacion() {
		cb_nivel_autorizacion.setInputPrompt("Seleccione un Nivel de Autorizacion");
		cb_nivel_autorizacion.setNullSelectionAllowed(false);
		this.cb_nivel_autorizacion.addItem(1);
		this.cb_nivel_autorizacion.setItemCaption(1, "Unidad Organizacinal");
		this.cb_nivel_autorizacion.addItem(2);
		this.cb_nivel_autorizacion.setItemCaption(2, "Dependencia");
		this.cb_nivel_autorizacion.addItem(3);
		this.cb_nivel_autorizacion.setItemCaption(3, "Nacional");
	}
	
	public void buildCbTipoMovimiento() {
		cb_tipo_movimiento.setInputPrompt("Seleccione un Tipo de Movimiento");
		cb_tipo_movimiento.setNullSelectionAllowed(false);
		for (Tipos_Movimiento tipo_mov : tipomov_impl.getall()) {
			this.cb_tipo_movimiento.addItem(tipo_mov);
			this.cb_tipo_movimiento.setItemCaption(tipo_mov, tipo_mov.getTMV_Nombre_Tipo_Movimiento());
		}
	}
	
	public boolean validate() {
		
		try {
			this.binder_autorizacion.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_tipo_movimiento.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_tipo_movimiento.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.txt_orden.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(txt_orden.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_orden.getCaption(), ex.getMessage()));
			}
			try {
				this.cb_servidor_publico.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_servidor_publico.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_nivel_autorizacion.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_nivel_autorizacion.getCaption(), Messages.EMPTY_MESSAGE));
			}
			return false;
		}
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty() == cb_dependencia && cb_dependencia.getValue() != null) {
			Dependencia dependencia = (Dependencia) cb_dependencia.getValue();
			buildCbUnidad(dependencia);
		}
		if (event.getProperty() == cb_unidad && cb_unidad.getValue() != null) {
			Unidades_Organizacionale unidad = (Unidades_Organizacionale) cb_unidad.getValue();
			buildCbServidor(unidad);
		}
		
	}
	
	private void buildCbUnidad(Dependencia dependencia) {
		cb_unidad.removeAllItems();
		cb_unidad.setInputPrompt("Seleccione una Unidad Organizacional");
		cb_unidad.setNullSelectionAllowed(false);
		for (Unidades_Organizacionale unidad : unidad_impl.getunidad(dependencia.getDEP_Dependencia())) {
			this.cb_unidad.addItem(unidad);
			this.cb_unidad.setItemCaption(unidad, unidad.getUNO_Nombre_Unidad_Organizacional());
		}
	}
	
	private void buildCbServidor(Unidades_Organizacionale unidad) {
		cb_servidor_publico.removeAllItems();
		cb_servidor_publico.setInputPrompt("Seleccione un Servidor Publico");
		cb_servidor_publico.setNullSelectionAllowed(false);
		for (PersonalModel personal : personal_impl.getbyUnidad(unidad.getUNO_Unidad_Organizacional())) {
			this.cb_servidor_publico.addItem(personal);
			this.cb_servidor_publico.setItemCaption(
					personal,
					personal.getPER_Nombres() + " " + personal.getPER_Apellido_Paterno() + " "
							+ personal.getPER_Apellido_Materno());
		}
	}
	
	public TipoAutorizacionModel getData() {
		TipoAutorizacionModel resul = new TipoAutorizacionModel();
		resul.setDependencia_id(((Dependencia)cb_dependencia.getValue()).getDEP_Dependencia());
		resul.setUnidad_organizacional_id(((Unidades_Organizacionale)cb_unidad.getValue()).getUNO_Unidad_Organizacional());
		resul.setTipo_movimiento_id(((Tipos_Movimiento)cb_tipo_movimiento.getValue()).getTMV_Tipo_Movimiento());
		resul.setOrden(Short.valueOf(txt_orden.getValue()));
		resul.setNivel_autorizacion_id(Short.parseShort(cb_nivel_autorizacion.getValue().toString()));
		resul.setCi(((PersonalModel)cb_servidor_publico.getValue()).getPER_CI_Empleado());
		

		long lnMilis = new Date().getTime();
		resul.setFecha_registro(new java.sql.Date(lnMilis));
		return resul;
	}
	
	// public void setData(Ciudade data) {
	// this.txt_id_ciudad.setValue(String.valueOf(data.getCIU_Ciudad()));
	// this.txt_nombre_ciudad.setValue(String.valueOf(data.getCIU_Nombre_Ciudad()));
	// this.txt_sigla_ciudad.setValue(String.valueOf(data.getCIU_Sigla()));
	//
	// }
}