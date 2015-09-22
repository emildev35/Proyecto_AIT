package ait.sistemas.proyecto.activos.view.inve.tomainv;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.ActivoInventario;
import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.model_rrhh.Personal;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.PersonalImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class FormTomaInv extends GridLayout implements TextChangeListener, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private TextField txt_nro_documento = new TextField("Nro. Documento");
	private DateField dtf_fecha_documento = new DateField("Fecha Documento");
	
	private TextField txt_usuario_asignado = new TextField("Nombre Funcionario Asignado");
	
	private TextField txt_nro_documento_ref = new TextField("Nro. Doc. Referencia");
	private DateField dtf_fecha_ref = new DateField("Fecha Doc. Referencia");
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
	
	private final PersonalImpl personalimpl = new PersonalImpl();
	private final GridInventario grid_inventario = new GridInventario();
	private final ActivoImpl activoimpl = new ActivoImpl();
	
	private List<ActivoInventario> activos_invetariados = new ArrayList<ActivoInventario>();
	
	private VerticalLayout layout_errores;
	
	public FormTomaInv(VerticalLayout layout) {
		
		super(6, 5);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		setMargin(true);
		
		pitm_tomainv.addItemProperty("nro_socumento_ref", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("fecha_ref", new ObjectProperty<Date>(new Date()));
		pitm_tomainv.addItemProperty("codigo_activo", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("ci_funcionario", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("nombre_funcionario", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("nro_docuento", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));
		pitm_tomainv.addItemProperty("obervaciones", new ObjectProperty<String>(""));
		pitm_tomainv.addItemProperty("nombre_activo_no", new ObjectProperty<String>(""));
		
		this.binder_tomainv = new FieldGroup(this.pitm_tomainv);
		
		binder_tomainv.bind(this.txt_nro_documento_ref, "nro_socumento_ref");
		binder_tomainv.bind(this.dtf_fecha_ref, "fecha_ref");
		binder_tomainv.bind(this.txt_codigo_activo, "codigo_activo");
		binder_tomainv.bind(this.txt_ci_usuario, "ci_funcionario");
		binder_tomainv.bind(this.txt_usuario_asignado, "nombre_funcionario");
		binder_tomainv.bind(this.txt_nro_documento, "nro_docuento");
		binder_tomainv.bind(this.dtf_fecha_documento, "fecha");
		binder_tomainv.bind(this.tarea_observacion, "obervaciones");
		binder_tomainv.bind(this.txt_nombre_activo_no, "nombre_activo_no");
		
		this.txt_nro_documento_ref.setRequired(true);
		this.txt_usuario_asignado.setRequired(true);
		this.txt_codigo_activo.setRequired(true);
		this.txt_nombre_activo_no.setRequired(true);
		this.txt_ci_usuario.setRequired(true);
		this.txt_nro_documento.setRequired(true);
		this.dtf_fecha_ref.setRequired(true);
		this.dtf_fecha_documento.setRequired(true);
		
		txt_nro_documento_ref.setWidth("100%");
		dtf_fecha_ref.setWidth("100%");
		txt_codigo_activo.setWidth("100%");
		txt_ci_usuario.setWidth("100%");
		dtf_fecha_documento.setWidth("100%");
		txt_nro_documento.setWidth("100%");
		tarea_observacion.setWidth("100%");
		txt_usuario_asignado.setWidth("100%");
		txt_nombre_activo_no.setWidth("100%");
		
		this.txt_ci_usuario.addTextChangeListener(this);
		this.txt_codigo_activo.addTextChangeListener(this);
		
		tarea_observacion.setRows(2);
		
		this.btn_scaner.addClickListener(this);
		this.btn_act_previo.addClickListener(this);
		
		this.layout_errores = layout;
		update();
		enabled();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	private void buildContent() {
		this.addShortcutListener(new ShortcutListener("Key Enter", KeyCode.ENTER, new int[] {}) {
			private static final long serialVersionUID = 1L;
			
			@Override
			public void handleAction(Object sender, Object target) {
				
				try {
					binder_tomainv.commit();
					ActivoInventario activo = new ActivoInventario(Long.parseLong(txt_codigo_activo.getValue().replace(".", "")
							.replace("'", "")), txt_nombre_activo_no.getValue(), tarea_observacion.getValue(), txt_ci_usuario
							.getValue());
					activos_invetariados.add(activo);
					grid_inventario.buildGrid(activos_invetariados);
				} catch (CommitException ex) {
					Map<Field<?>, InvalidValueException> invalid_fields = ex.getInvalidFields();
					Iterator<Field<?>> it = invalid_fields.keySet().iterator();
					while (it.hasNext()) {
						Field<?> key = (Field<?>) it.next();
						mensajes.add(new BarMessage(key.getCaption(),
								invalid_fields.get(key).getMessage() == "" ? Messages.EMPTY_MESSAGE : invalid_fields.get(key)
										.getMessage()));
					}
				}
				((VTomaInvP) layout_errores).buildMessages(mensajes);
				mensajes = new ArrayList<BarMessage>();
				txt_codigo_activo.setValue("");
			}
		});
		
		addComponent(this.txt_nro_documento_ref, 0, 0);
		addComponent(this.dtf_fecha_ref, 1, 0);
		addComponent(this.txt_nro_documento, 5, 0);
		
		addComponent(this.txt_ci_usuario, 0, 1);
		addComponent(this.txt_usuario_asignado, 1, 1, 2, 1);
		
		addComponent(this.dtf_fecha_documento, 5, 1);
		
		addComponent(this.tarea_observacion, 0, 2, 5, 2);
		
		addComponent(this.txt_codigo_activo, 0, 3);
		addComponent(this.btn_scaner, 1, 3);
		addComponent(this.btn_act_previo, 2, 3);
		addComponent(this.txt_nombre_activo_no, 3, 3, 5, 3);
		setComponentAlignment(btn_scaner, Alignment.BOTTOM_CENTER);
		setComponentAlignment(btn_act_previo, Alignment.BOTTOM_CENTER);
	}
	
	public void update() {
		binder_tomainv.clear();
		this.dtf_fecha_documento.setValue(new Date());
		this.txt_nro_documento_ref.focus();
	}
	
	public void enabled() {
		// this.txt_codigo_activo.setEnabled(false);
		this.dtf_fecha_documento.setEnabled(false);
		this.txt_nro_documento.setEnabled(false);
		this.txt_usuario_asignado.setEnabled(false);
		this.txt_nombre_activo_no.setEnabled(false);
		this.dtf_fecha_documento.setValue(new Date());
		this.txt_usuario_asignado.setValue(session.getFull_name());
		this.txt_nro_documento.setValue(String.valueOf(this.movimientoimpl.getId((short) 13)));
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		this.mensajes = new ArrayList<BarMessage>();
		if (this.activos_invetariados.size() > 0) {
			return true;
		}
		this.mensajes.add(new BarMessage("Grid Inventario", Messages.EMPTY_GRID));
		return false;
	}
	
	public Movimiento getData() {
		Movimiento resul = new Movimiento();
		resul.setNro_documento(Long.parseLong(txt_nro_documento.getValue().replace(".", "")));
		resul.setId_dependencia(session.getId_dependecia());
		resul.setId_unidad_organizacional_origen(session.getId_unidad_organizacional());
		resul.setTipo_movimiento((short) 13);
		resul.setObservacion("");
		resul.setNro_documento_referencia(txt_nro_documento_ref.getValue());
		resul.setFecha_nro_referencia(new java.sql.Date(dtf_fecha_ref.getValue().getTime()));
		resul.setFecha_movimiento(new java.sql.Date(new Date().getTime()));
		resul.setUsuario(txt_ci_usuario.getValue());
		resul.setFecha_registro(new java.sql.Date(new Date().getTime()));
		
		for (ActivoInventario activoInventario : activos_invetariados) {
			Detalle detalle = new Detalle();
			detalle.setId_activo(activoInventario.getCodigo_activo());
			detalle.setNro_documento(resul.getNro_documento());
			detalle.setFecha_registro(resul.getFecha_registro());
			detalle.setId_dependencia(resul.getId_dependencia());
			detalle.setId_unidad_organizacional_origen(resul.getId_unidad_organizacional_origen());
			detalle.setNombre_activo(activoInventario.getNombre_activo());
			detalle.setTipo_movimiento(resul.getTipo_movimiento());
			detalle.setObservacion(activoInventario.getObservacion());
			resul.addDetalle(detalle);
		}
		return resul;
	}
	
	public void clean() {
		
		this.binder_tomainv.clear();
		this.grid_inventario.clean();
		enabled();
	}
	
	public Grid getGrid() {
		return this.grid_inventario;
	}
	
	@Override
	public void textChange(TextChangeEvent event) {
		if (event.getText().toString().length() >= 5) {
			Personal personal = personalimpl.getbyCI(event.getText().toString());
			if (personal != null) {
				String nombre_usuario = personal.getPER_Apellido_Paterno() + " " + personal.getPER_Apellido_Materno() + " "
						+ personal.getPER_Nombres();
				txt_usuario_asignado.setValue(nombre_usuario);
			}
		}
		if (event.getComponent() == txt_codigo_activo) {
			if (event.getComponent().toString().length() >= 5) {
				try {
					String codigo = event.getText().substring(event.getText().length() - 4, event.getText().length());
					ActivoGrid activo = activoimpl.getone(Long.parseLong(codigo), session.getId_dependecia());
					if (activo != null) {
						txt_nombre_activo_no.setValue(activo.getNombre());
					}
				} catch (NumberFormatException ex) {
				} catch (StringIndexOutOfBoundsException e) {
				}
			} else {
				if (event.getComponent().toString().length() > 0) {
					try {
						ActivoGrid activo = activoimpl.getone(Long.parseLong(event.getText()), session.getId_dependecia());
						if (activo != null) {
							txt_nombre_activo_no.setValue(activo.getNombre());
						}
					} catch (NumberFormatException ex) {
					}
				}
			}
		}
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == btn_scaner) {
			this.txt_codigo_activo.setEnabled(true);
			this.txt_nombre_activo_no.setEnabled(false);
			this.txt_nombre_activo_no.setValue("");
			this.txt_codigo_activo.focus();
		}
		if (event.getButton() == btn_act_previo) {
			this.txt_codigo_activo.setEnabled(false);
			this.txt_nombre_activo_no.setEnabled(true);
			this.txt_codigo_activo.setValue(String.valueOf(activoimpl.getIdAcivo(session.getId_dependecia())));
			this.txt_nombre_activo_no.setValue("");
			txt_nombre_activo_no.focus();
		}
	}
	
}
