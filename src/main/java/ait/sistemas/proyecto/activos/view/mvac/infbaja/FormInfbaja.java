package ait.sistemas.proyecto.activos.view.mvac.infbaja;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
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
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormInfbaja extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	private TextField txt_resolucion = new TextField("Num. Resolucion");
	public DateField dtf_fecha_resol = new DateField("Fecha");

	private TextField txt_num_inf_baja = new TextField("Num. Informe");
	public DateField dtf_fecha_inf = new DateField("Fecha");
	
	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxiliar Contable");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private PropertysetItem pitm_informe = new PropertysetItem();
	private FieldGroup binder_informe;
	
	private final MovimientoImpl movimientoimpl = new MovimientoImpl();
	private final GrupoImpl grupoimpl = new GrupoImpl();
	private final AuxiliarImpl auxiliarimpl = new AuxiliarImpl();
	
	private GridInfbaja grid_solicitud = new GridInfbaja();
	
	public FormInfbaja() {
		
		super(6, 3);
		setSpacing(true);
		setWidth("100%");
		
		pitm_informe.addItemProperty("id_resolucion", new ObjectProperty<String>(""));
		pitm_informe.addItemProperty("fecha_resolucion", new ObjectProperty<Date>(new Date()));
		pitm_informe.addItemProperty("id_informe", new ObjectProperty<Integer>(0));
		pitm_informe.addItemProperty("fecha_informe", new ObjectProperty<Date>(new Date()));
		pitm_informe.addItemProperty("grupo_contable", new ObjectProperty<GruposContablesModel>(new GruposContablesModel()));
		pitm_informe.addItemProperty("auxiliar_contable", new ObjectProperty<AuxiliaresContablesModel>(
				new AuxiliaresContablesModel()));
		
		this.binder_informe = new FieldGroup(this.pitm_informe);
		
		binder_informe.bind(this.txt_resolucion, "id_resolucion");
		binder_informe.bind(this.dtf_fecha_resol, "fecha_resolucion");
		binder_informe.bind(this.txt_num_inf_baja, "id_informe");
		binder_informe.bind(this.dtf_fecha_inf, "fecha_informe");
		binder_informe.bind(this.cb_grupo_contable, "grupo_contable");
		binder_informe.bind(this.cb_auxiliar_contable, "auxiliar_contable");
		binder_informe.clear();
		
		this.txt_resolucion.addValidator(new NullValidator("No Nulo", false));

		this.txt_num_inf_baja.setEnabled(false);
		this.txt_num_inf_baja.addValidator(new NullValidator("No Nulo", false));
		
		this.dtf_fecha_resol.addValidator(new NullValidator("No Nulo", false));

		this.dtf_fecha_inf.setEnabled(false);
		this.dtf_fecha_inf.addValidator(new NullValidator("No Nulo", false));
		
		this.cb_grupo_contable.setRequired(true);
		this.cb_grupo_contable.addValidator(new NullValidator("No Nulo", false));
		cb_grupo_contable.setInputPrompt("Seleccione un Grupo Contable");
		cb_grupo_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.setRequired(true);
		cb_auxiliar_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.addValidator(new NullValidator("No Nulo", false));
		cb_auxiliar_contable.setInputPrompt("Seleccione un Auxiliar Contable");
		
		txt_resolucion.setWidth("90%");
		dtf_fecha_resol.setWidth("90%");
		txt_num_inf_baja.setWidth("90%");
		dtf_fecha_inf.setWidth("90%");
		cb_grupo_contable.setWidth("90%");
		cb_auxiliar_contable.setWidth("90%");
		
		fillcbGrupoContable();
		buildContent();
		buildId();
		Responsive.makeResponsive(this);
	}
	
	private void buildId() {
		this.dtf_fecha_resol.setValue(new Date());
		this.txt_num_inf_baja.setValue(String.valueOf(movimientoimpl.getId((short)6)));
		this.dtf_fecha_inf.setValue(new Date());
	}
	
	private void fillcbGrupoContable() {
		cb_grupo_contable.setNullSelectionAllowed(false);
		for (GruposContablesModel grupo_contable : grupoimpl.getalls()) {
			cb_grupo_contable.addItem(grupo_contable);
			cb_grupo_contable.setItemCaption(grupo_contable, grupo_contable.getGRC_Nombre_Grupo_Contable());
		}
	}
	
	private void fillcbAuxiliarContable(String id_grupo) {
		cb_auxiliar_contable.removeAllItems();
		cb_auxiliar_contable.setNullSelectionAllowed(false);
		for (AuxiliaresContablesModel auxiliar_contable : auxiliarimpl.getreporte(id_grupo)) {
			cb_auxiliar_contable.addItem(auxiliar_contable);
			cb_auxiliar_contable.setItemCaption(auxiliar_contable, auxiliar_contable.getAUC_Nombre_Auxiliar_Contable());
		}
	}
	
	private void buildContent() {
		
		Panel pn_resolucion = new Panel("Resolucion de Bajas");
		Panel pn_solicitud = new Panel("Informe de Baja de Activos");
		Panel pn_activos = new Panel("Seleccione un Grupo y Auxiliar Contable");
		
		GridLayout gridl_resolucion = new GridLayout(2, 1);
		gridl_resolucion.setSizeFull();
		// gridl_solicitud.setMargin(true);
		gridl_resolucion.addComponent(this.txt_resolucion, 0, 0);
		gridl_resolucion.addComponent(this.dtf_fecha_resol, 1, 0);
		pn_resolucion.setContent(gridl_resolucion);
		this.addComponent(pn_resolucion, 0, 0, 1, 0);

		GridLayout gridl_solicitud = new GridLayout(2, 1);
		gridl_solicitud.setSizeFull();
		// gridl_solicitud.setMargin(true);
		gridl_solicitud.addComponent(this.txt_num_inf_baja, 0, 0);
		gridl_solicitud.addComponent(this.dtf_fecha_inf, 1, 0);
		pn_solicitud.setContent(gridl_solicitud);
		
		this.addComponent(pn_solicitud, 4, 0, 5, 0);
		
		GridLayout gridl_activos = new GridLayout(2, 1);
		gridl_activos.setSizeFull();
		gridl_activos.setMargin(true);
		gridl_activos.addComponent(this.cb_grupo_contable, 0, 0);
		gridl_activos.addComponent(this.cb_auxiliar_contable, 1, 0);
		pn_activos.setContent(gridl_activos);
		
		this.addComponent(pn_activos, 0, 1, 5, 1);
		
	}
	
	public void update() {
		binder_informe.clear();
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			this.binder_informe.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
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
			
			return false;
		}
	}
	
	public Movimiento getData() {
		Movimiento result = new Movimiento();
		
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		java.sql.Timestamp fecha_registro =new java.sql.Timestamp(new Date().getTime());
		
		result.setId_dependencia(usuario.getId_dependecia());
		result.setIdUnidadOrganizacional(usuario.getId_unidad_organizacional());
		result.setNro_documento(Long.parseLong(this.txt_num_inf_baja.getValue()));
		result.setFecha_movimiento(fecha_registro);
		result.setFecha_registro(fecha_registro);
		result.setFecha_nro_referencia(new java.sql.Date(dtf_fecha_resol.getValue().getTime()));
		result.setNro_documento_referencia(this.txt_resolucion.getValue());
		result.setUsuario(usuario.getCi());
		result.setObservacion("");
		result.setTipo_movimiento((short)7);
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
		GruposContablesModel grupo = (GruposContablesModel) cb_grupo_contable.getValue();
		this.grid_solicitud.update(grupo.getGRC_Grupo_Contable(), auc_Auxiliar_Contable);
	}
	
	public GridInfbaja getgrid_solicitud() {
		return this.grid_solicitud;
	}

	public void clear() {
	
		this.binder_informe.clear();
		buildId();
		this.grid_solicitud = new GridInfbaja();
		
	}
}
