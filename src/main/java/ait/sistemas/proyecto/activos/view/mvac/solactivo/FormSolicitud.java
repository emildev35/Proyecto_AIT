package ait.sistemas.proyecto.activos.view.mvac.solactivo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.InmuebleImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
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
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormSolicitud extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_solicitud = new TextField("Id. Solicitud");
	public DateField dtf_fecha_soliciud = new DateField("Fecha Solicitud");
	
	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxiliar Contable");
	
	private List<BarMessage> mensajes;
	
	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private InmuebleImpl inmueble_impl = new InmuebleImpl();
	private PropertysetItem pitm_solicitud = new PropertysetItem();
	private FieldGroup binder_solicitud;
	private final MovimientoImpl movimientoimpl = new MovimientoImpl();
	
	public FormSolicitud() {
		
		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		
		pitm_solicitud.addItemProperty("id_solicitud", new ObjectProperty<Integer>(0));
		pitm_solicitud.addItemProperty("fecha_solicitud", new ObjectProperty<Date>(new Date()));
		pitm_solicitud.addItemProperty("grupo_contable", new ObjectProperty<Short>((short) 1));
		pitm_solicitud.addItemProperty("auxiliar_contable", new ObjectProperty<String>(""));
		
		this.binder_solicitud = new FieldGroup(this.pitm_solicitud);
		
		binder_solicitud.bind(this.txt_id_solicitud, "id_solicitud");
		binder_solicitud.bind(this.dtf_fecha_soliciud, "fecha_solicitud");
		binder_solicitud.bind(this.cb_grupo_contable, "grupo_contable");
		binder_solicitud.bind(this.cb_auxiliar_contable, "auxiliar_contable");
		binder_solicitud.clear();
		
		this.txt_id_solicitud.setEnabled(false);
		this.txt_id_solicitud.addValidator(new NullValidator("No Nulo", false));
		
		this.dtf_fecha_soliciud.setEnabled(false);
		this.dtf_fecha_soliciud.addValidator(new NullValidator("No Nulo", false));
		
		this.cb_grupo_contable.setRequired(true);
		this.cb_grupo_contable.addValidator(new NullValidator("No Nulo", false));
		cb_grupo_contable.setInputPrompt("Seleccione un Grupo Contable");
		this.cb_auxiliar_contable.setRequired(true);
		this.cb_auxiliar_contable.addValidator(new NullValidator("No Nulo", false));
		cb_auxiliar_contable.setInputPrompt("Seleccione un Auxiliar Contable");
		txt_id_solicitud.setWidth("90%");
		dtf_fecha_soliciud.setWidth("90%");
		cb_grupo_contable.setWidth("90%");
		cb_auxiliar_contable.setWidth("90%");
		
		updateId();
		fillcbGrupoContable();
		buildContent();
		buildId();
		Responsive.makeResponsive(this);
	}
	
	private void buildId() {
		this.txt_id_solicitud.setValue(String.valueOf(movimientoimpl.getId()));
		this.dtf_fecha_soliciud.setValue(new Date());
	}
	
	private void fillcbGrupoContable() {
		cb_grupo_contable.setNullSelectionAllowed(false);
		
		for (Ciudade ciudad : dependencia_impl.getallsigla()) {
			cb_grupo_contable.addItem(ciudad.getCIU_Ciudad());
			cb_grupo_contable.setItemCaption(ciudad.getCIU_Ciudad(), ciudad.getCIU_Nombre_Ciudad());
		}
	}
	
	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		setColumnExpandRatio(2, 1);
		addComponent(this.txt_id_solicitud, 0, 0);
		addComponent(this.dtf_fecha_soliciud, 1, 0);
		addComponent(this.cb_grupo_contable, 1, 1);
		addComponent(this.cb_auxiliar_contable, 2, 1);
		
	}
	
	public void update() {
		binder_solicitud.clear();
	}
	
	public void updateId() {
		this.txt_id_solicitud.setValue(inmueble_impl.generateId() + "");
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			this.binder_solicitud.commit();
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
	
	// public Inmueble getData(){
	// Inmueble resul = new Inmueble();
	// SessionModel usuario =
	// (SessionModel)UI.getCurrent().getSession().getAttribute("user");
	//
	// short dependencia =
	// dependencia_impl.getdependencia_ID(usuario.getDependecia());
	// resul.setINM_Dependencia(dependencia);
	// resul.setINM_Inmueble(Short.parseShort(this.txt_id_inmueble.getValue()));
	// resul.setINM_Nombre_Inmueble(this.txt_nombre_inmueble.getValue());
	// resul.setINM_Domicilio_Inmueble(this.txt_domicilio.getValue());
	// resul.setINM_Ciudad((short)this.cb_ciudad.getValue());
	// long lnMilis = new Date().getTime();
	// resul.setINM_Fecha_Registro(new java.sql.Date(lnMilis));
	// return resul;
	// }
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		// updateId();
	}
}
