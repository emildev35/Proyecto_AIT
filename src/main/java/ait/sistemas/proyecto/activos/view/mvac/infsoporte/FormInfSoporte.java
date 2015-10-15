package ait.sistemas.proyecto.activos.view.mvac.infsoporte;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.grid.GridDetalleActivo;
import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.component.model.SolicitudSoporte;
import ait.sistemas.proyecto.activos.data.model.EstadoSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.EstadoSoporteImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormInfSoporte extends GridLayout implements SelectionListener {
	
	private static final long serialVersionUID = 1L;
	
	private GridSolSoporte grid_solsoporte = new GridSolSoporte();
	private GridDetalleActivo grid_detalle_activo = new GridDetalleActivo();
	private TextField txt_nombre_sistema = new TextField("Nombre del Sistema Aplicativo");
	private TextArea tarea_descripcion = new TextArea("Descripcion del Problema Reportado");
	private TextField txt_nro_informe = new TextField("Nro. Informe");
	private DateField dtf_fecha = new DateField("Fecha");
	private ComboBox cb_estado_soporte = new ComboBox("Estado Soporte");
	private TextField txt_nombre_tecnico = new TextField("Nombre del Tecnico que Realiza el Soporte");
	private TextArea tarea_descripcion_tarea = new TextArea("Descripcion del Trabajo Realizado	");
	
	private PropertysetItem pitm_infsoporte = new PropertysetItem();
	private FieldGroup binder_infsoporte;
	
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	private final MovimientoImpl movimientoImpl = new MovimientoImpl();
	private final EstadoSoporteImpl estado_soporteimpl = new EstadoSoporteImpl();
	private SolicitudSoporte sol_soporte;
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	public FormInfSoporte() {
		super(6, 4);
		this.grid_solsoporte.update(session.getId_dependecia());
		this.tarea_descripcion.setRows(2);
		this.tarea_descripcion_tarea.setRows(2);
		
		this.txt_nombre_sistema.setWidth("100%");
		this.tarea_descripcion.setWidth("100%");
		this.txt_nro_informe.setWidth("100%");
		this.dtf_fecha.setWidth("100%");
		this.cb_estado_soporte.setWidth("100%");
		this.txt_nombre_tecnico.setWidth("100%");
		this.tarea_descripcion_tarea.setWidth("100%");
		
		this.grid_solsoporte.addSelectionListener(this);
		
		this.pitm_infsoporte.addItemProperty("descripcion", new ObjectProperty<String>(""));
		this.pitm_infsoporte.addItemProperty("id_informe", new ObjectProperty<String>(""));
		this.pitm_infsoporte.addItemProperty("descripcion_trabajo", new ObjectProperty<String>(""));
		this.pitm_infsoporte.addItemProperty("estado_soporte", new ObjectProperty<EstadoSoporte>(new EstadoSoporte()));
		
		
		binder_infsoporte = new FieldGroup(pitm_infsoporte);
		binder_infsoporte.bind(this.tarea_descripcion, "descripcion");
		binder_infsoporte.bind(this.txt_nro_informe, "id_informe");
		binder_infsoporte.bind(this.tarea_descripcion_tarea, "descripcion_trabajo");
		binder_infsoporte.bind(this.cb_estado_soporte, "estado_soporte");
		
		this.tarea_descripcion.setRequired(true);
		this.tarea_descripcion.addValidator(new NullValidator("", false));
		this.txt_nro_informe.setRequired(true);
		this.txt_nro_informe.addValidator(new NullValidator("", false));
		this.tarea_descripcion_tarea.setRequired(true);
		this.tarea_descripcion_tarea.addValidator(new NullValidator("", false));
		this.cb_estado_soporte.setRequired(true);
		this.cb_estado_soporte.addValidator(new NullValidator("", false));
		
		setWidth("100%");
		setMargin(true);
		setSpacing(true);
		buildFormContent();
		fillcbEstadoSoporte();
		buildId();
		
		Responsive.makeResponsive(this);
	}
	
	private void buildFormContent() {
		GridLayout gridl_datos_soporte = new GridLayout(3, 2);
		GridLayout gridl_descripcion = new GridLayout(4, 2);
		GridLayout gridl_informe_soporte = new GridLayout(1, 2);
		
		gridl_datos_soporte.setResponsive(true);
		gridl_datos_soporte.setWidth("100%");
		gridl_datos_soporte.setMargin(true);
		gridl_datos_soporte.setSpacing(true);
		gridl_descripcion.setResponsive(true);
		gridl_descripcion.setWidth("100%");
		gridl_descripcion.setMargin(true);
		gridl_descripcion.setSpacing(true);
		gridl_informe_soporte.setResponsive(true);
		gridl_informe_soporte.setWidth("100%");
		gridl_informe_soporte.setMargin(true);
		gridl_informe_soporte.setSpacing(true);
		
		Panel pn_solsoporte = new Panel("SELECCIONE UNA SOLICITUD DE SOPORTE TECNICO");
		pn_solsoporte.setContent(grid_solsoporte);
		
		Panel pn_datos_soporte = new Panel("DATOS DEL SOPORTE SOLICITADO");
		pn_datos_soporte.setContent(gridl_datos_soporte);
		gridl_datos_soporte.addComponent(this.txt_nombre_sistema, 0, 0);
		gridl_datos_soporte.addComponent(tarea_descripcion, 1, 0, 2, 0);
		gridl_datos_soporte.addComponent(grid_detalle_activo, 0, 1, 2, 1);
		
		Panel pn_descripcion = new Panel("DESCRIPCION DEL SOPORTE SOLICITADO");
		pn_descripcion.setContent(gridl_descripcion);
		gridl_descripcion.addComponent(txt_nombre_tecnico, 0, 0, 2, 0);
		gridl_descripcion.addComponent(tarea_descripcion_tarea, 0, 1, 2, 1);
		gridl_descripcion.addComponent(cb_estado_soporte, 3, 1);
		
		Panel pn_informe_soporte = new Panel("INFORME SOPORTE");
		pn_informe_soporte.setContent(gridl_informe_soporte);
		gridl_informe_soporte.addComponent(txt_nro_informe, 0, 0);
		gridl_informe_soporte.addComponent(dtf_fecha, 0, 1);
		
		addComponent(pn_solsoporte, 0, 0, 5, 0);
		addComponent(pn_datos_soporte, 0, 1, 4, 2);
		addComponent(pn_informe_soporte, 5, 2);
		addComponent(pn_descripcion, 0, 3, 5, 3);
	}
	
	private void buildId() {
		this.binder_infsoporte.clear();
		this.dtf_fecha.setValue(new Date());
		this.dtf_fecha.setEnabled(false);
		this.txt_nro_informe.setValue(String.valueOf(movimientoImpl.getId((short) 12)));
		this.txt_nro_informe.setEnabled(false);
		this.txt_nombre_tecnico.setValue(session.getFull_name());
		this.txt_nombre_tecnico.setEnabled(false);
		this.txt_nombre_sistema.setEnabled(false);
		this.tarea_descripcion.setEnabled(false);
	}
	
	@Override
	public void select(SelectionEvent event) {
		if ((SolicitudSoporte)grid_solsoporte.getSelectedRow() != null) {
			this.sol_soporte = (SolicitudSoporte) grid_solsoporte.getSelectedRow();
			this.grid_detalle_activo.update(sol_soporte.getNro_documento(), sol_soporte.getDependencia(),
					sol_soporte.getTipo_movimiento());
			this.txt_nombre_sistema.setValue(sol_soporte.getNombre_sistema());
			this.tarea_descripcion.setValue(sol_soporte.getDescripcion());
		}
	}
	private void fillcbEstadoSoporte() {
		cb_estado_soporte.setInputPrompt("Seleccione un Estado");
		cb_estado_soporte.setNullSelectionAllowed(false);
		for (EstadoSoporte estado_soporte : estado_soporteimpl.getall()) {
			cb_estado_soporte.addItem(estado_soporte);
			cb_estado_soporte.setItemCaption(estado_soporte, estado_soporte.getNombre());
		}
	}
	public boolean validate() {
		try{
			this.binder_infsoporte.commit();
			return true;
		}catch(CommitException ex){
			try{
				this.tarea_descripcion.validate();
			}catch(EmptyValueException eva){
				this.mensajes.add(new BarMessage(this.tarea_descripcion.getCaption(),Messages.EMPTY_MESSAGE));
			}
			try{
				this.txt_nro_informe.validate();
			}catch(EmptyValueException eva){
				this.mensajes.add(new BarMessage(this.txt_nro_informe.getCaption(),Messages.EMPTY_MESSAGE));
			}
			try{
				this.tarea_descripcion_tarea.validate();
			}catch(EmptyValueException eva){
				this.mensajes.add(new BarMessage(this.tarea_descripcion_tarea.getCaption(),Messages.EMPTY_MESSAGE));
			}
			try{
				this.cb_estado_soporte.validate();
			}catch(EmptyValueException eva){
				this.mensajes.add(new BarMessage(this.cb_estado_soporte.getCaption(),Messages.EMPTY_MESSAGE));
			}
			return false;
		}

	}
	
	public void clean() {
		this.grid_solsoporte.update(session.getId_dependecia());
		buildId();
	}
	
	public List<BarMessage> getMensajes() {
		return this.mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	public Movimiento getData() {
		Movimiento resul = new Movimiento();
		resul.setNro_documento(Long.parseLong(txt_nro_informe.getValue()));
		resul.setId_dependencia(session.getId_dependecia());
		resul.setId_unidad_organizacional_origen(session.getId_unidad_organizacional());
		resul.setUsuario(session.getCi());
		resul.setTipo_movimiento((short) 12);
		resul.setObservacion(this.tarea_descripcion.getValue().toString());
		resul.setId_estado_soporte(((EstadoSoporte) cb_estado_soporte.getValue()).getId());
		resul.setFecha_movimiento(new java.sql.Timestamp(new Date().getTime()));
		resul.setFecha_registro(new java.sql.Timestamp(new Date().getTime()));
		resul.setNro_documento_referencia(String.valueOf(this.sol_soporte.getNro_documento()));
		resul.setTipo_movimiento_referencia(sol_soporte.getTipo_movimiento());
		resul.setFecha_nro_referencia(sol_soporte.getFecha());
		return resul;
	}
}
