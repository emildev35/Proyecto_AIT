package ait.sistemas.proyecto.activos.view.mvac.solsoporte;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.model.TipoSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoSoporteImpl;
import ait.sistemas.proyecto.activos.view.mvac.solmantenimiento.GridSolmantenimiento;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormSolsoporte extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private TextField txt_nombre_solicitante = new TextField("Nombre Usuario Solicitante");
	private TextField txt_nro_solicitud = new TextField("Nro. Solicitud");
	private DateField dtf_fecha = new DateField("Fecha");
	private TextField txt_hora = new TextField("Hora");
	private ComboBox cb_tipo_soporte = new ComboBox("Tipo de Soporte Utilizado");
	private ComboBox cb_nombre_sistema = new ComboBox("Nombre del Sistema Aplicativo");
	private GridSolmantenimiento grid_activos_asignados = new GridSolmantenimiento();
	private TextArea tarea_descripcion = new TextArea("Descripcion del Problema Reportado");
	
	private PropertysetItem pitm_Grupo = new PropertysetItem();
	private FieldGroup binder_soporte;
	
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	
	private final MenuImpl menuimpl = new MenuImpl();
	private final TipoSoporteImpl tipo_soporteimpl = new TipoSoporteImpl();
	private final MovimientoImpl movimientoimpl = new MovimientoImpl();
	
	public FormSolsoporte() {
		
		super.setColumns(6);
		super.setRows(4);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		
		grid_activos_asignados.setHeightMode(HeightMode.ROW);
		grid_activos_asignados.setHeightByRows(5);
		
		pitm_Grupo.addItemProperty("nro_solicitud", new ObjectProperty<Integer>(0));
		pitm_Grupo.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));
		pitm_Grupo.addItemProperty("hora", new ObjectProperty<String>(new SimpleDateFormat("H:m").format(new Date())));
		pitm_Grupo.addItemProperty("tipo_soporte", new ObjectProperty<TipoSoporte>(new TipoSoporte()));
		pitm_Grupo.addItemProperty("nombre_sistema", new ObjectProperty<Arbol_menus>(new Arbol_menus()));
		pitm_Grupo.addItemProperty("descripcion", new ObjectProperty<String>(""));
		
		this.binder_soporte = new FieldGroup(this.pitm_Grupo);
		
		binder_soporte.bind(this.txt_nro_solicitud, "nro_solicitud");
		binder_soporte.bind(this.dtf_fecha, "fecha");
		binder_soporte.bind(this.txt_hora, "hora");
		binder_soporte.bind(this.cb_tipo_soporte, "tipo_soporte");
		binder_soporte.bind(this.cb_nombre_sistema, "nombre_sistema");
		binder_soporte.bind(this.tarea_descripcion, "descripcion");
		
		this.txt_nro_solicitud.setRequired(true);
		this.txt_nro_solicitud.addValidator(new NullValidator("No Nulo", false));
		
		this.cb_tipo_soporte.setRequired(true);
		this.cb_tipo_soporte.addValidator(new NullValidator("No Nulo", false));
		
		this.tarea_descripcion.setRequired(true);
		this.tarea_descripcion.addValidator(new NullValidator("No Nulo", false));
		this.tarea_descripcion.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 255), 1, 255, false));
		
		txt_nro_solicitud.setWidth("90%");
		dtf_fecha.setWidth("90%");
		txt_hora.setWidth("90%");
		cb_tipo_soporte.setWidth("100%");
		cb_nombre_sistema.setWidth("100%");
		
		tarea_descripcion.setWidth("100%");
		txt_nombre_solicitante.setWidth("100%");
		
		update();
		enabled();
		fillcbTipoSoporte();
		fillcbSistemasAplicativos();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	private void fillcbTipoSoporte() {
		cb_tipo_soporte.setNullSelectionAllowed(false);
		cb_tipo_soporte.setInputPrompt("Seleccione un Tipo de Soporte");
		for (TipoSoporte tipo_soporte : tipo_soporteimpl.getall()) {
			cb_tipo_soporte.addItem(tipo_soporte);
			cb_tipo_soporte.setItemCaption(tipo_soporte, tipo_soporte.getNombre());
		}
	}
	
	private void fillcbSistemasAplicativos() {
		cb_nombre_sistema.setNullSelectionAllowed(false);
		cb_nombre_sistema.setInputPrompt("Seleccione un Sistema Aplicativo");
		for (Arbol_menus menu : menuimpl.getallSubsistema()) {
			cb_nombre_sistema.addItem(menu);
			cb_nombre_sistema.setItemCaption(menu, menu.getAME_Nombre());
		}
	}
	
	private void buildContent() {
		GridLayout grid_soporte = new GridLayout(3, 1);
		grid_soporte.addComponent(this.txt_nro_solicitud, 0, 0);
		grid_soporte.addComponent(this.dtf_fecha, 1, 0);
		grid_soporte.addComponent(this.txt_hora, 2, 0);
		grid_soporte.setWidth("100%");
		grid_soporte.setMargin(true);
		grid_soporte.setSpacing(true);
		
		GridLayout grid_selecccion = new GridLayout(2, 1);
		grid_selecccion.addComponent(this.cb_tipo_soporte, 0, 0);
		grid_selecccion.addComponent(this.cb_nombre_sistema, 1, 0);
		grid_selecccion.setWidth("100%");
		grid_selecccion.setMargin(true);
		grid_selecccion.setSpacing(true);
		
		Panel pn_soporte = new Panel("SOLICITUD DE SOPORTE TECNICO");
		pn_soporte.setContent(grid_soporte);
		Panel pn_seleccion = new Panel("SELECCION DEL TIPO DE SOPORTE Y LA APLICACION");
		pn_seleccion.setContent(grid_selecccion);
		Panel pn_grid = new Panel("SELECCION DE ACTIVOS FIJOS ASIGNADOS: Seleccione el (los) Activos para el Servicio de Soporte");
		pn_grid.setContent(this.grid_activos_asignados);
		
		addComponent(this.txt_nombre_solicitante, 0, 0, 2, 0);
		addComponent(pn_soporte, 3, 0, 5, 0);
		addComponent(pn_seleccion, 0, 1, 5, 1);
		addComponent(pn_grid, 0, 2, 5, 2);
		addComponent(tarea_descripcion, 0, 3, 5, 3);
		
	}
	
	public void update() {
		binder_soporte.clear();
	}
	
	public void enabled() {
		this.txt_hora.setEnabled(false);
		this.dtf_fecha.setEnabled(false);
		this.txt_nro_solicitud.setEnabled(false);
		this.txt_nombre_solicitante.setEnabled(false);
		this.txt_hora.setValue(new SimpleDateFormat("H:mm:ss").format(new Date()));
		this.dtf_fecha.setValue(new Date());
		this.txt_nombre_solicitante.setValue(session.getFull_name());
		this.grid_activos_asignados.update(session.getCi());
		this.txt_nro_solicitud.setValue(String.valueOf(this.movimientoimpl.getId((short) 11)));
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		if(grid_activos_asignados.getSelectedRows().size() < 1){
			this.mensajes.add(new BarMessage("GRID", Messages.EMPTY_GRID));
			return false;
		}
		try {
			this.binder_soporte.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_tipo_soporte.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_tipo_soporte.getCaption(), Messages.EMPTY_MESSAGE));
			}
			
			try {
				this.tarea_descripcion.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(tarea_descripcion.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(tarea_descripcion.getCaption(), ex.getMessage()));
			}
			return false;
		}
	}
	
	public Movimiento getData() {
		Movimiento resul = new Movimiento();
		resul.setNro_documento(Long.parseLong(txt_nro_solicitud.getValue()));
		resul.setTipo_soporte(((TipoSoporte) cb_tipo_soporte.getValue()).getId());
		resul.setId_dependencia(session.getId_dependecia());
		resul.setId_unidad_organizacional_origen(session.getId_unidad_organizacional());
		resul.setUsuario(session.getCi());
		resul.setTipo_movimiento((short) 11);
		resul.setId_estado_soporte((short)1);
		resul.setObservacion(this.tarea_descripcion.getValue().toString());
		if (cb_nombre_sistema.getValue() != null) {
			resul.setId_subsistema(((Arbol_menus) cb_nombre_sistema.getValue()).getAME_Id_Identificador());
		}
		resul.setFecha_movimiento(new java.sql.Date(new Date().getTime()));
		resul.setFecha_registro(new java.sql.Date(new Date().getTime()));
		for (Object row : grid_activos_asignados.getSelectedRows()) {
			ActivoGrid activo = (ActivoGrid) row;
			Detalle detalle = new Detalle();
			detalle.setNro_documento(resul.getNro_documento());
			detalle.setId_activo(activo.getId_activo());
			detalle.setFecha_registro(resul.getFecha_registro());
			detalle.setId_dependencia(resul.getId_dependencia());
			detalle.setId_unidad_organizacional_origen(resul.getId_unidad_organizacional_origen());
			detalle.setTipo_movimiento(resul.getTipo_movimiento());
			resul.addDetalle(detalle);
		}
		return resul;
	}

	public void clean() {
		this.binder_soporte.clear();
		enabled();
		
	}
	
}
