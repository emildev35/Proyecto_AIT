package ait.sistemas.proyecto.activos.view.mant.orden;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.grid.GridDetalleActivo;
import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;
import ait.sistemas.proyecto.activos.component.model.Movimiento;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormOrdenSalida extends GridLayout implements SelectionListener {
	private static final long serialVersionUID = 1L;
	
	private GridSolMantenimientoAprobadas grid_solmanapr = new GridSolMantenimientoAprobadas();
	private GridDetalleActivo grid_detalle_activo = new GridDetalleActivo();
	private TextField txt_dependencia = new TextField("Dependencia");
	private TextField txt_unidad_organizacional = new TextField("Unidad Organizacional");
	private TextField txt_solicitante = new TextField("Solicitante");
	private TextField txt_nro_solicitud = new TextField("Nro. Solicitud");
	private DateField dtf_fecha_solicitud = new DateField("Fecha Solicitud");
	
	private TextField txt_nro_informe = new TextField("Nro. de Orden");
	private DateField dtf_fecha = new DateField("Fecha");
	
	private ComboBox cb_proveedor = new ComboBox("Proveedor");
	
	private PropertysetItem pitm_infsoporte = new PropertysetItem();
	private FieldGroup binder_infsoporte;
	
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	private final MovimientoImpl movimientoImpl = new MovimientoImpl();
	private final EstadoSoporteImpl estado_soporteimpl = new EstadoSoporteImpl();
	private DocumentoPendiente sol_mantenimiento;
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	public FormOrdenSalida() {
		super(6, 5);
		this.grid_solmanapr.update(session.getId_dependecia());
		
		this.txt_dependencia.setWidth("100%");
		this.txt_solicitante.setWidth("100%");
		this.txt_nro_informe.setWidth("100%");
		this.dtf_fecha.setWidth("100%");
		this.cb_proveedor.setWidth("100%");
		
		this.grid_solmanapr.addSelectionListener(this);
		
		this.pitm_infsoporte.addItemProperty("descripcion", new ObjectProperty<String>(""));
		this.pitm_infsoporte.addItemProperty("id_informe", new ObjectProperty<String>(""));
		this.pitm_infsoporte.addItemProperty("descripcion_trabajo", new ObjectProperty<String>(""));
		this.pitm_infsoporte.addItemProperty("estado_soporte", new ObjectProperty<EstadoSoporte>(new EstadoSoporte()));
		
		binder_infsoporte = new FieldGroup(pitm_infsoporte);
		binder_infsoporte.bind(this.txt_solicitante, "descripcion");
		binder_infsoporte.bind(this.txt_nro_informe, "id_informe");
		binder_infsoporte.bind(this.cb_proveedor, "estado_soporte");
		
		this.txt_solicitante.setRequired(true);
		this.txt_solicitante.addValidator(new NullValidator("", false));
		this.txt_nro_informe.setRequired(true);
		this.txt_nro_informe.addValidator(new NullValidator("", false));
		this.cb_proveedor.setRequired(true);
		this.cb_proveedor.addValidator(new NullValidator("", false));
		
		setWidth("100%");
		setMargin(true);
		setSpacing(true);
		buildFormContent();
		fillcbProveedor();
		buildId();
		
		Responsive.makeResponsive(this);
	}
	
	private void buildFormContent() {
		GridLayout gridl_detalle_mantenimiento = new GridLayout(4, 2);
		GridLayout gridl_detalle_activos = new GridLayout(1, 1);
		GridLayout gridl_informe_soporte = new GridLayout(1, 2);
		
		gridl_detalle_mantenimiento.setResponsive(true);
		gridl_detalle_mantenimiento.setWidth("100%");
		gridl_detalle_mantenimiento.setMargin(true);
		gridl_detalle_mantenimiento.setSpacing(true);
		gridl_detalle_activos.setResponsive(true);
		gridl_detalle_activos.setWidth("100%");
		gridl_informe_soporte.setResponsive(true);
		gridl_informe_soporte.setWidth("100%");
		gridl_informe_soporte.setMargin(true);
		gridl_informe_soporte.setSpacing(true);
		
		Panel pn_solsoporte = new Panel("SELECCIONE UNA SOLICITUD DE SOPORTE TECNICO");
		pn_solsoporte.setContent(grid_solmanapr);
		
		Panel pn_datos_soporte = new Panel("DATOS DE LA SOLICITUD DE MANTENIMIENTO");
		pn_datos_soporte.setContent(gridl_detalle_mantenimiento);
		gridl_detalle_mantenimiento.addComponent(txt_nro_solicitud, 0, 0);
		gridl_detalle_mantenimiento.addComponent(dtf_fecha_solicitud, 1, 0);
		gridl_detalle_mantenimiento.addComponent(txt_solicitante, 2, 0, 3, 0);
		gridl_detalle_mantenimiento.addComponent(txt_dependencia, 0, 1, 1, 1);
		gridl_detalle_mantenimiento.addComponent(txt_unidad_organizacional, 2, 1, 3, 1);
		
		Panel pn_descripcion = new Panel("DESCRIPCION DEL SOPORTE SOLICITADO");
		pn_descripcion.setContent(gridl_detalle_activos);
		gridl_detalle_activos.addComponent(grid_detalle_activo, 0, 0);
		
		Panel pn_informe_soporte = new Panel("INFORME SOPORTE");
		pn_informe_soporte.setContent(gridl_informe_soporte);
		gridl_informe_soporte.addComponent(txt_nro_informe, 0, 0);
		gridl_informe_soporte.addComponent(dtf_fecha, 0, 1);
		
		addComponent(pn_solsoporte, 0, 0, 5, 0);
		addComponent(pn_datos_soporte, 0, 1, 4, 2);
		addComponent(pn_informe_soporte, 5, 2);
		addComponent(cb_proveedor, 1, 3, 3, 3);
		addComponent(pn_descripcion, 0, 4, 5, 4);
	}
	
	private void buildId() {
		this.binder_infsoporte.clear();
		this.dtf_fecha.setValue(new Date());
		this.dtf_fecha.setEnabled(false);
		this.txt_nro_informe.setValue(String.valueOf(movimientoImpl.getId((short) 9)));
		this.txt_nro_informe.setEnabled(false);
		this.txt_dependencia.setEnabled(false);
		this.txt_solicitante.setEnabled(false);
	}
	
	@Override
	public void select(SelectionEvent event) {
		if ((DocumentoPendiente) grid_solmanapr.getSelectedRow() != null) {
			this.sol_mantenimiento = (DocumentoPendiente) grid_solmanapr.getSelectedRow();
			this.grid_detalle_activo.update(sol_mantenimiento.getNro_documento(), sol_mantenimiento.getDependencia_id(),
					sol_mantenimiento.getTipo_movimiento_id());
			this.txt_dependencia.setValue(sol_mantenimiento.getDependencia());
			this.txt_solicitante.setValue(sol_mantenimiento.getNombre_solicitante());
			this.txt_unidad_organizacional.setValue(sol_mantenimiento.getUnidad_organizacional());
			this.txt_nro_solicitud.setValue(String.valueOf(sol_mantenimiento.getNro_documento()));
			this.dtf_fecha_solicitud.setValue(sol_mantenimiento.getFecha_movimiento());
		}
	}
	
	private void fillcbProveedor() {
		cb_proveedor.setInputPrompt("Seleccione un Proveedor");
		cb_proveedor.setNullSelectionAllowed(false);
		for (EstadoSoporte estado_soporte : estado_soporteimpl.getall()) {
			cb_proveedor.addItem(estado_soporte);
			cb_proveedor.setItemCaption(estado_soporte, estado_soporte.getNombre());
		}
	}
	
	public boolean validate() {
		try {
			this.binder_infsoporte.commit();
			return true;
		} catch (CommitException ex) {
			try {
				this.txt_solicitante.validate();
			} catch (EmptyValueException eva) {
				this.mensajes.add(new BarMessage(this.txt_solicitante.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.txt_nro_informe.validate();
			} catch (EmptyValueException eva) {
				this.mensajes.add(new BarMessage(this.txt_nro_informe.getCaption(), Messages.EMPTY_MESSAGE));
			}
			
			try {
				this.cb_proveedor.validate();
			} catch (EmptyValueException eva) {
				this.mensajes.add(new BarMessage(this.cb_proveedor.getCaption(), Messages.EMPTY_MESSAGE));
			}
			return false;
		}
		
	}
	
	public void clean() {
		this.grid_solmanapr.update(session.getId_dependecia());
		buildId();
	}
	
	public List<BarMessage> getMensajes() {
		return this.mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public Movimiento getData() {
		// Movimiento resul = new Movimiento();
		// resul.setNro_documento(Long.parseLong(txt_nro_informe.getValue()));
		// resul.setId_dependencia(session.getId_dependecia());
		// resul.setId_unidad_organizacional_origen(session.getId_unidad_organizacional());
		// resul.setUsuario(session.getCi());
		// resul.setTipo_movimiento((short) 12);
		// resul.setObservacion(this.txt_solicitante.getValue().toString());
		// resul.setId_estado_soporte(((EstadoSoporte) s.getValue()).getId());
		// resul.setFecha_movimiento(new java.sql.Date(new Date().getTime()));
		// resul.setFecha_registro(new java.sql.Date(new Date().getTime()));
		// resul.setNro_documento_referencia(String.valueOf(this.sol_mantenimiento.getNro_documento()));
		// resul.setTipo_movimiento_referencia(sol_mantenimiento.getTipo_movimiento());
		// resul.setFecha_nro_referencia(sol_mantenimiento.getFecha());
		// return resul;
		return null;
	}
}
