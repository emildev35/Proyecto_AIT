package ait.sistemas.proyecto.activos.view.mant.soporte;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.TipoSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoSoporteImpl;
import ait.sistemas.proyecto.activos.view.mvac.solmantenimiento.GridMantenimiento;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

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

public class FormSoporte extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private TextField txt_nombre_solicitante = new TextField("Nombre Usuario Solicitante");
	private TextField txt_nro_solicitud = new TextField("Nro. Solicitud");
	private DateField dtf_fecha = new DateField("Fecha");
	private TextField txt_hora = new TextField("Hora");
	private ComboBox cb_tipo_soporte = new ComboBox("Tipo de Soporte Utilizado");
	private ComboBox cb_nombre_sistema = new ComboBox("Nombre del Sistema Aplicativo");
	private GridMantenimiento grid_activos_asignados = new GridMantenimiento();
	private TextArea tarea_descripcion = new TextArea("Descripcion del Problema Reportado");
	
	private PropertysetItem pitm_Grupo = new PropertysetItem();
	private FieldGroup binder_Grupo;
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	private final MenuImpl menuimpl = new MenuImpl();
	private final TipoSoporteImpl tipo_soporteimpl = new TipoSoporteImpl();
	
	public FormSoporte() {
		
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
		
		this.binder_Grupo = new FieldGroup(this.pitm_Grupo);
		
		binder_Grupo.bind(this.txt_nro_solicitud, "nro_solicitud");
		binder_Grupo.bind(this.dtf_fecha, "fecha");
		binder_Grupo.bind(this.txt_hora, "hora");
		binder_Grupo.bind(this.cb_tipo_soporte, "tipo_soporte");
		binder_Grupo.bind(this.cb_nombre_sistema, "nombre_sistema");
		binder_Grupo.bind(this.tarea_descripcion, "descripcion");
		
		
		this.txt_nro_solicitud.setRequired(true);
		this.txt_nro_solicitud.addValidator(new NullValidator("No Nulo", false));
		this.txt_nro_solicitud.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 2), 1, 2, false));
		
		this.cb_tipo_soporte.setRequired(true);
		this.cb_tipo_soporte.addValidator(new NullValidator("No Nulo", false));
		
		this.cb_nombre_sistema.setRequired(true);
		this.cb_nombre_sistema.addValidator(new NullValidator("No Nulo", false));
		
		this.tarea_descripcion.setRequired(true);
		this.tarea_descripcion.addValidator(new NullValidator("No Nulo", false));
		this.tarea_descripcion.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 5), 1, 5, false));
		
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
		binder_Grupo.clear();
	}
	
	public void enabled() {
		this.txt_hora.setEnabled(false);
		this.dtf_fecha.setEnabled(false);
		this.txt_nro_solicitud.setEnabled(false);
		this.txt_nombre_solicitante.setEnabled(false);
		this.txt_hora.setValue(new SimpleDateFormat("H:m").format(new Date()));
		this.dtf_fecha.setValue(new Date());
		this.txt_nombre_solicitante.setValue(session.getFull_name());
		this.grid_activos_asignados.update(session.getCi());
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			this.binder_Grupo.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			// try {
			// this.txt_id_grupo.validate();
			// } catch (EmptyValueException ex) {
			// this.mensajes.add(new BarMessage(txt_id_grupo.getCaption(),
			// Messages.EMPTY_MESSAGE));
			// } catch (InvalidValueException ex) {
			// this.mensajes.add(new BarMessage(txt_id_grupo.getCaption(),
			// ex.getMessage()));
			// }
			// try {
			// this.txt_nombre_grupo.validate();
			// } catch (EmptyValueException ex) {
			// this.mensajes.add(new BarMessage(txt_nombre_grupo.getCaption(),
			// Messages.EMPTY_MESSAGE));
			// } catch (InvalidValueException ex) {
			// this.mensajes.add(new BarMessage(txt_nombre_grupo.getCaption(),
			// ex.getMessage()));
			// }
			// try {
			// this.txt_vida_util.validate();
			// } catch (EmptyValueException ex) {
			// this.mensajes.add(new BarMessage(txt_vida_util.getCaption(),
			// Messages.EMPTY_MESSAGE));
			// } catch (InvalidValueException ex) {
			// this.mensajes.add(new BarMessage(txt_vida_util.getCaption(),
			// ex.getMessage()));
			// }
			// try {
			// this.txt_coeficiente.validate();
			// } catch (EmptyValueException ex) {
			// this.mensajes.add(new BarMessage(txt_coeficiente.getCaption(),
			// Messages.EMPTY_MESSAGE));
			// } catch (InvalidValueException ex) {
			// this.mensajes.add(new BarMessage(txt_coeficiente.getCaption(),
			// ex.getMessage()));
			// }
			// try {
			// this.cb_partida.validate();
			// } catch (EmptyValueException ex) {
			// this.mensajes.add(new BarMessage(cb_partida.getCaption(),
			// Messages.EMPTY_MESSAGE));
			// } catch (InvalidValueException ex) {
			// this.mensajes.add(new BarMessage(cb_partida.getCaption(),
			// ex.getMessage()));
			// }
			return false;
		}
	}
	
	// public Grupos_Contable getData() {
	// Grupos_Contable resul = new Grupos_Contable();
	// resul.setGRC_Grupo_Contable(this.txt_id_grupo.getValue());
	// resul.setGRC_Nombre_Grupo_Contable(this.txt_nombre_grupo.getValue());
	// resul.setGRC_Vida_Util(Short.parseShort(this.txt_vida_util.getValue()));
	// resul.setGRC_Coeficiente(new Double(this.txt_coeficiente.getValue()));
	// resul.setGRC_Partida((Integer) this.cb_partida.getValue());
	// long lnMilis = new Date().getTime();
	// resul.setGRC_Fecha_Registro(new java.sql.Date(lnMilis));
	// return resul;
	// }
	//
	// public void setData(GruposContablesModel data) {
	// this.txt_id_grupo.setValue(String.valueOf(data.getGRC_Grupo_Contable()));
	// this.txt_nombre_grupo.setValue(String.valueOf(data.getGRC_Nombre_Grupo_Contable()));
	// this.txt_vida_util.setValue(String.valueOf(data.getGRC_Vida_Util()));
	// this.txt_coeficiente.setValue(String.valueOf(data.getGRC_Coeficiente()));
	// this.cb_partida.setValue((Integer) data.getGRC_Partida_ID());
	//
	// }
	//
	// @Override
	// public void valueChange(ValueChangeEvent event) {
	// }
}
