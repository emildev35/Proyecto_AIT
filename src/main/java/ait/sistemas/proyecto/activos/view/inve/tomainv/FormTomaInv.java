package ait.sistemas.proyecto.activos.view.inve.tomainv;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormTomaInv extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private TextField txt_nro_documento = new TextField("Nro. Documento");
	private DateField dtf_fecha_documento = new DateField("Fecha Documento");
	
	private TextField txt_usuario_asignado = new TextField("Nombre Funcionario Asignado");
	private TextField txt_nro_documento_ref = new TextField("Nro. Documento Referencia");
	private DateField dtf_fecha = new DateField("Fecha");
	private TextField txt_codigo_activo = new TextField("Codigo Activo");
	private TextField txt_ci_usuario = new TextField("C.I. Usuario Asignado");
	private TextField txt_nombre_activo_no = new TextField("Nombre de Activo no Asignado");
	
	private TextArea tarea_observacion = new TextArea("Observaciones");
	
	private PropertysetItem pitm_tomainv = new PropertysetItem();
	private FieldGroup binder_tomainv;
	
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	
	private final MovimientoImpl movimientoimpl = new MovimientoImpl();
	
	private Button btn_scaner = new Button("Escanear Codigo de Barras");
	private Button btn_act_previo = new Button("Activo sin Registro Previo");
	
	public FormTomaInv() {
		
		super(6, 5);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		setMargin(true);
		
		pitm_tomainv.addItemProperty("nro_socumento_ref", new ObjectProperty<Integer>(0));
		pitm_tomainv.addItemProperty("fecha_ref", new ObjectProperty<Date>(new Date()));
		pitm_tomainv.addItemProperty("codigo_activo", new ObjectProperty<Integer>(0));
		pitm_tomainv.addItemProperty("ci_funcionario", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("nombre_funcionario", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("nro_docuento", new ObjectProperty<Integer>(0));
		pitm_tomainv.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));
		pitm_tomainv.addItemProperty("obervaciones", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("nombre_activo_no", new ObjectProperty<String>(""));
		
		this.binder_tomainv = new FieldGroup(this.pitm_tomainv);
		
		binder_tomainv.bind(this.txt_nro_documento_ref, "nro_socumento_ref");
		binder_tomainv.bind(this.dtf_fecha_documento, "fecha_ref");
		binder_tomainv.bind(this.txt_codigo_activo, "codigo_activo");
		binder_tomainv.bind(this.txt_ci_usuario, "ci_funcionario");
		binder_tomainv.bind(this.txt_usuario_asignado, "nombre_funcionario");
		binder_tomainv.bind(this.txt_nro_documento, "nro_docuento");
		binder_tomainv.bind(this.dtf_fecha, "fecha");
		binder_tomainv.bind(this.tarea_observacion, "obervaciones");
		binder_tomainv.bind(this.txt_nombre_activo_no, "nombre_activo_no");
		
		this.txt_nro_documento_ref.setRequired(true);
		this.txt_nro_documento_ref.addValidator(new NullValidator("No Nulo", false));
		
		this.tarea_observacion.setRequired(true);
		this.tarea_observacion.addValidator(new NullValidator("No Nulo", false));
		this.tarea_observacion.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 255), 1, 255, false));
		
		txt_nro_documento_ref.setWidth("90%");
		dtf_fecha.setWidth("90%");
		txt_codigo_activo.setWidth("90%");
		txt_ci_usuario.setWidth("100%");
		dtf_fecha_documento.setWidth("100%");
		txt_nro_documento.setWidth("100%");
		tarea_observacion.setWidth("100%");
		txt_usuario_asignado.setWidth("100%");
		txt_nombre_activo_no.setWidth("100%");
		
		update();
		enabled();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	private void buildContent() {
		
		addComponent(this.txt_nro_documento_ref, 0, 0);
		addComponent(this.dtf_fecha, 1, 0);
		addComponent(this.txt_nro_documento, 5, 0);
		
		addComponent(this.txt_ci_usuario, 0, 1, 1, 1);
		addComponent(this.txt_usuario_asignado, 2, 1, 4, 1);
		addComponent(this.dtf_fecha_documento, 5, 1);
		
		addComponent(this.txt_codigo_activo, 0, 2);
		addComponent(this.btn_scaner, 1, 2);
		addComponent(this.btn_act_previo, 2, 2);
		addComponent(this.txt_nombre_activo_no, 3, 2);
		addComponent(this.tarea_observacion, 0, 3, 5, 3);
		
		setComponentAlignment(btn_scaner, Alignment.BOTTOM_CENTER);
		setComponentAlignment(txt_nombre_activo_no, Alignment.BOTTOM_CENTER);
	}
	
	public void update() {
		binder_tomainv.clear();
	}
	
	public void enabled() {
		// this.txt_codigo_activo.setEnabled(false);
		this.dtf_fecha.setEnabled(false);
		this.txt_nro_documento_ref.setEnabled(false);
		this.txt_usuario_asignado.setEnabled(false);
		this.txt_codigo_activo.setValue("");
		this.dtf_fecha.setValue(new Date());
		this.txt_usuario_asignado.setValue(session.getFull_name());
		this.txt_nro_documento_ref.setValue(String.valueOf(this.movimientoimpl.getId((short) 14)));
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		try {
			this.binder_tomainv.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			
			try {
				this.tarea_observacion.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(tarea_observacion.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(tarea_observacion.getCaption(), ex.getMessage()));
			}
			return false;
		}
	}
	
	public Movimiento getData() {
		Movimiento resul = new Movimiento();
		resul.setId_dependencia(session.getId_dependecia());
		resul.setId_unidad_organizacional_origen(session.getId_unidad_organizacional());
		resul.setUsuario(session.getCi());
		resul.setTipo_movimiento((short) 11);
		resul.setId_estado_soporte((short) 1);
		resul.setObservacion(this.tarea_observacion.getValue().toString());
		resul.setFecha_movimiento(new java.sql.Date(new Date().getTime()));
		resul.setFecha_registro(new java.sql.Date(new Date().getTime()));
		return null;
	}
	
	public void clean() {
		this.binder_tomainv.clear();
		enabled();
	}
	
}
