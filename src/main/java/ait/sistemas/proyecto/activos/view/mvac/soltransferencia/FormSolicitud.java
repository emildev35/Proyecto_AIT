package ait.sistemas.proyecto.activos.view.mvac.soltransferencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

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
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormSolicitud extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_solicitud = new TextField("Id. Solicitud");
	public DateField dtf_fecha_soliciud = new DateField("Fecha Solicitud");
	
	public ComboBox cb_dependnecia = new ComboBox("Dependencia");
	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxiliar Contable");
	
	public TextField txt_observaciones = new TextField("Motivo de La Solicitud");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private PropertysetItem pitm_solicitud = new PropertysetItem();
	private FieldGroup binder_solicitud;
	
	private final MovimientoImpl movimientoimpl = new MovimientoImpl();
	private final DependenciaImpl dependencia_impl = new DependenciaImpl();
	private final GrupoImpl grupoimpl = new GrupoImpl();
	private final AuxiliarImpl auxiliarimpl = new AuxiliarImpl();
	
	private GridSolicitud grid_solicitud = new GridSolicitud();
	
	public FormSolicitud() {
		
		super(7, 4);
		setSpacing(true);
		setWidth("100%");
		
		pitm_solicitud.addItemProperty("id_solicitud", new ObjectProperty<Integer>(0));
		pitm_solicitud.addItemProperty("fecha_solicitud", new ObjectProperty<Date>(new Date()));
		pitm_solicitud.addItemProperty("dependencia", new ObjectProperty<Dependencia>(new Dependencia()));
		pitm_solicitud.addItemProperty("grupo_contable", new ObjectProperty<GruposContablesModel>(new GruposContablesModel()));
		pitm_solicitud.addItemProperty("auxiliar_contable", new ObjectProperty<AuxiliaresContablesModel>(
				new AuxiliaresContablesModel()));
		pitm_solicitud.addItemProperty("observaciones", new ObjectProperty<String>(""));

		this.binder_solicitud = new FieldGroup(this.pitm_solicitud);
		
		binder_solicitud.bind(this.txt_id_solicitud, "id_solicitud");
		binder_solicitud.bind(this.dtf_fecha_soliciud, "fecha_solicitud");
		binder_solicitud.bind(this.cb_dependnecia, "dependencia");
		binder_solicitud.bind(this.cb_grupo_contable, "grupo_contable");
		binder_solicitud.bind(this.cb_auxiliar_contable, "auxiliar_contable");
		binder_solicitud.bind(this.txt_observaciones, "observaciones");
		binder_solicitud.clear();
		
		this.txt_id_solicitud.setEnabled(false);
		this.txt_id_solicitud.addValidator(new NullValidator("No Nulo", false));
		
		this.dtf_fecha_soliciud.setEnabled(false);
		this.dtf_fecha_soliciud.addValidator(new NullValidator("No Nulo", false));
		
		this.cb_dependnecia.setRequired(true);
		this.cb_dependnecia.addValidator(new NullValidator("No Nulo", false));
		cb_dependnecia.setInputPrompt("Seleccione una Dependencia");
		cb_dependnecia.addValueChangeListener(this);
		this.cb_grupo_contable.setRequired(true);
		this.cb_grupo_contable.addValidator(new NullValidator("No Nulo", false));
		cb_grupo_contable.setInputPrompt("Seleccione un Grupo Contable");
		cb_grupo_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.setRequired(true);
		cb_auxiliar_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.addValidator(new NullValidator("No Nulo", false));
		cb_auxiliar_contable.setInputPrompt("Seleccione un Auxiliar Contable");
		
		this.txt_observaciones.setRequired(true);
		this.txt_observaciones.addValidator(new NullValidator("No Nulo", false));
		
		txt_id_solicitud.setWidth("90%");
		dtf_fecha_soliciud.setWidth("90%");
		cb_dependnecia.setWidth("90%");
		cb_grupo_contable.setWidth("90%");
		cb_auxiliar_contable.setWidth("90%");
		txt_observaciones.setWidth("90%");
		
		fillcbDependencia();
		fillcbGrupoContable();
		buildContent();
		buildId();
		Responsive.makeResponsive(this);
	}
	
	private void buildId() {
		this.txt_id_solicitud.setValue(String.valueOf(movimientoimpl.getId((short)5)));
		this.dtf_fecha_soliciud.setValue(new Date());
	}
	
	private void fillcbDependencia() {
		cb_dependnecia.setNullSelectionAllowed(false);
		
		for (Dependencia dependencia : dependencia_impl.getall()) {
			cb_dependnecia.addItem(dependencia);
			cb_dependnecia.setItemCaption(dependencia, dependencia.getDEP_Nombre_Dependencia());
		}
	}
	private void fillcbGrupoContable() {
		cb_auxiliar_contable.setNullSelectionAllowed(false);
		for (GruposContablesModel grupo_contable : grupoimpl.getalls()) {
			cb_grupo_contable.addItem(grupo_contable);
			cb_grupo_contable.setItemCaption(grupo_contable, grupo_contable.getGRC_Nombre_Grupo_Contable());
		}
	}
	private void fillcbAuxiliarContable(String id_grupo) {
		cb_auxiliar_contable.setNullSelectionAllowed(false);
		for (AuxiliaresContablesModel auxiliar_contable : auxiliarimpl.getreporte(id_grupo)) {
			cb_auxiliar_contable.addItem(auxiliar_contable);
			cb_auxiliar_contable.setItemCaption(auxiliar_contable, auxiliar_contable.getAUC_Nombre_Auxiliar_Contable());
		}
	}
	
	private void buildContent() {
		
		Panel pn_solicitud = new Panel("Solicitud de Movimiento de Activos");
		Panel pn_activos = new Panel("Seleccione la Dependencia, El Grupo y el Auxiliar Contable");
		
		GridLayout gridl_solicitud = new GridLayout(2, 1);
		gridl_solicitud.setSizeFull();
		// gridl_solicitud.setMargin(true);
		gridl_solicitud.addComponent(this.txt_id_solicitud, 0, 0);
		gridl_solicitud.addComponent(this.dtf_fecha_soliciud, 1, 0);
		pn_solicitud.setContent(gridl_solicitud);
		
		this.addComponent(pn_solicitud, 4, 0, 5, 0);
		
		GridLayout gridl_activos = new GridLayout(3, 1);
		gridl_activos.setSizeFull();
		gridl_activos.setMargin(true);
		gridl_activos.addComponent(this.cb_dependnecia, 0, 0);
		gridl_activos.addComponent(this.cb_grupo_contable, 1, 0);
		gridl_activos.addComponent(this.cb_auxiliar_contable, 2, 0);
		pn_activos.setContent(gridl_activos);
		
		this.addComponent(pn_activos, 0, 1, 5, 1);
		
		
	}
	
	public void update() {
		binder_solicitud.clear();
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	public TextField getObservaciones(){
		return this.txt_observaciones;
	}
	
	public boolean validate() {
		
		try {
			this.binder_solicitud.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_dependnecia.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_dependnecia.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_dependnecia.getCaption(), ex.getMessage()));
			}
			try {
				this.cb_grupo_contable.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_grupo_contable.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_grupo_contable.getCaption(), ex.getMessage()));
			}
			try {
				this.cb_auxiliar_contable.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_auxiliar_contable.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_auxiliar_contable.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_observaciones.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(txt_observaciones.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_observaciones.getCaption(), ex.getMessage()));
			}
			
			return false;
		}
	}
	
	public Movimiento getData() {
		Movimiento result = new Movimiento();
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		java.sql.Date fecha_registro =new java.sql.Date(new Date().getTime());
		
		result.setId_dependencia((short)this.cb_dependnecia.getValue());
		result.setId_dependencia_destino(usuario.getId_dependecia());
		result.setId_unidad_organizacional_origen(usuario.getId_unidad_organizacional());
		result.setNro_documento(Long.parseLong(this.txt_id_solicitud.getValue()));
		result.setFecha_movimiento(fecha_registro);
		result.setFecha_registro(fecha_registro);
		result.setUsuario(usuario.getCi());
		result.setObservacion(txt_observaciones.getValue());
		result.setTipo_movimiento((short)5);
		for (Object row : grid_solicitud.getSelectedRows()) {
			ActivoGrid activo = (ActivoGrid) row;
			
			Detalle detalle = new Detalle();
			detalle.setId_activo(activo.getId_activo());
			detalle.setId_unidad_organizacional_origen(usuario.getId_unidad_organizacional());
			detalle.setId_dependencia((short)this.cb_dependnecia.getValue());
			detalle.setObservacion(txt_observaciones.getValue());
			detalle.setNro_documento(Long.parseLong(this.txt_id_solicitud.getValue()));
			detalle.setFecha_registro(fecha_registro);
			detalle.setTipo_movimiento((short)5);
			result.addDetalle(detalle);
		}
		
		return result;
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty() == cb_grupo_contable && this.cb_grupo_contable.getValue() != null) {
			GruposContablesModel grupo = (GruposContablesModel) cb_grupo_contable.getValue();
			fillcbAuxiliarContable(grupo.getGRC_Grupo_Contable());
		}
		if (event.getProperty() == cb_auxiliar_contable && this.cb_auxiliar_contable.getValue() != null) {
			AuxiliaresContablesModel auxiliar = (AuxiliaresContablesModel) cb_auxiliar_contable.getValue();
			buildGrid(auxiliar.getAUC_Auxiliar_Contable());
		}
	}
	
	private void buildGrid(String auc_Auxiliar_Contable) {
		Dependencia dependencia = (Dependencia) cb_dependnecia.getValue();
		GruposContablesModel grupo = (GruposContablesModel) cb_grupo_contable.getValue();
		this.grid_solicitud.update(dependencia.getDEP_Dependencia(),grupo.getGRC_Grupo_Contable(), auc_Auxiliar_Contable);
	}
	
	public Component getgrid_solicitud() {
		return this.grid_solicitud;
	}

	public void clear() {
	
		this.binder_solicitud.clear();
		this.grid_solicitud = new GridSolicitud();
		buildId();
		
	}
}
