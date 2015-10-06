package ait.sistemas.proyecto.activos.view.mvac.actualiza.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.DatosGeneralesActivos;
import ait.sistemas.proyecto.activos.component.model.TipoCambio;
import ait.sistemas.proyecto.activos.component.session.ActivoSession;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.Fuentes_Financiamiento;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.model.Organismo_Financiador;
import ait.sistemas.proyecto.activos.data.model.Tipos_Activo;
import ait.sistemas.proyecto.activos.data.model_rrhh.InmuebleModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.UbicacionesFisicasModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.FuenteImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.InmuebleImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.OrganismoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoCambioImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposactImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UbicacionImpl;
import ait.sistemas.proyecto.activos.view.mvac.actualiza.VActualizaTabM;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.DateRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormDatosGenerales extends GridLayout implements ClickListener, ValueChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_guardar_datos_generales = new Button("Actualizar Datos Generales");
	private Button btn_guardar = new Button("Actualizar");
	private Button btn_salir = new Button("Salir");
	
	public ComboBox cb_tipo_activo = new ComboBox("Tipo de Activo");
	public TextField txt_codigo_activo = new TextField("Codigo del Activo");
	public TextField txt_nombre_activo = new TextField("Nombre del Activo");
	public DateField dtf_fecha_compra = new DateField("Fecha Compra");
	public TextField txt_valor_compra = new TextField("Valor de la Compra (Bs)", "######,###BS");
	public DateField dtf_fecha_incorporacion = new DateField("Fecha de Incorporacion");
	public TextField txt_tipo_cambio_ufv = new TextField("Tipo Cambio UFV");
	public TextField txt_vida_util = new TextField("Vida Util (Años)");
	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxiliar Contable");
	public ComboBox cb_fuente_financiamiento = new ComboBox("Fuente de Financiamineto");
	public ComboBox cb_organismo_financiador = new ComboBox("Organismo Financiador");
	public ComboBox cb_ubicacion_fisica = new ComboBox("Ubicacion Fisica");
	public ComboBox cb_inmueble = new ComboBox("Inmueble");
	public DateField dtf_fecha_comodato = new DateField("Fecha ComoDato");
	
	final PropertysetItem pitmDatosGenerales = new PropertysetItem();
	private FieldGroup binderDatosGeneraler;
	
	private final TiposactImpl tipoactimpl = new TiposactImpl();
	private final GrupoImpl grupocontableimpl = new GrupoImpl();
	private final AuxiliarImpl auxcontableimpl = new AuxiliarImpl();
	private final FuenteImpl fuente_financiamientoimpl = new FuenteImpl();
	private final OrganismoImpl organismo_financiadorimpl = new OrganismoImpl();
	private final InmuebleImpl inmuebleimpl = new InmuebleImpl();
	private final UbicacionImpl ubicacionimpl = new UbicacionImpl();
	private final ActivoImpl activoimpl = new ActivoImpl();
	private final TipoCambioImpl tipocambioimpl = new TipoCambioImpl();
	
	private SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private ActivoSession sessionactivo;
	VActualizaTabM father;
	
	public FormDatosGenerales(VActualizaTabM father) {
		super(6, 6);
		setWidth("100%");
		setMargin(true);
		setSpacing(true);
		
		this.father = father;
		
		this.cb_tipo_activo.setWidth("100%");
		this.txt_codigo_activo.setWidth("100%");
		this.txt_nombre_activo.setWidth("100%");
		this.dtf_fecha_compra.setWidth("100%");
		this.dtf_fecha_incorporacion.setWidth("100%");
		this.txt_valor_compra.setWidth("100%");
		this.txt_tipo_cambio_ufv.setWidth("100%");
		this.txt_vida_util.setWidth("100%");
		this.cb_grupo_contable.setWidth("100%");
		this.cb_auxiliar_contable.setWidth("100%");
		this.cb_fuente_financiamiento.setWidth("100%");
		this.cb_organismo_financiador.setWidth("100%");
		this.cb_ubicacion_fisica.setWidth("100%");
		this.cb_inmueble.setWidth("100%");
		this.dtf_fecha_comodato.setWidth("100%");
		
		pitmDatosGenerales.addItemProperty("codigo", new ObjectProperty<String>(""));
		pitmDatosGenerales.addItemProperty("tipo_activo", new ObjectProperty<Tipos_Activo>(new Tipos_Activo()));
		pitmDatosGenerales.addItemProperty("nombre_activo", new ObjectProperty<String>(""));
		pitmDatosGenerales.addItemProperty("fecha_compra", new ObjectProperty<Date>(new Date()));
		pitmDatosGenerales.addItemProperty("fecha_incorporacion", new ObjectProperty<Date>(new Date()));
		pitmDatosGenerales.addItemProperty("valor_compra", new ObjectProperty<BigDecimal>(new BigDecimal("0")));
		pitmDatosGenerales.addItemProperty("tipo_cambio_ufv", new ObjectProperty<BigDecimal>(new BigDecimal("0")));
		pitmDatosGenerales.addItemProperty("vida_util", new ObjectProperty<Integer>(0));
		pitmDatosGenerales
				.addItemProperty("grupo_contable", new ObjectProperty<GruposContablesModel>(new GruposContablesModel()));
		pitmDatosGenerales.addItemProperty("auxiliar_contable", new ObjectProperty<AuxiliaresContablesModel>(
				new AuxiliaresContablesModel()));
		pitmDatosGenerales.addItemProperty("fuente_financiamiento", new ObjectProperty<Fuentes_Financiamiento>(
				new Fuentes_Financiamiento()));
		pitmDatosGenerales.addItemProperty("organismo_financiador", new ObjectProperty<Organismo_Financiador>(
				new Organismo_Financiador()));
		pitmDatosGenerales.addItemProperty("ubicacion_fisica", new ObjectProperty<UbicacionesFisicasModel>(
				new UbicacionesFisicasModel()));
		pitmDatosGenerales.addItemProperty("inmueble", new ObjectProperty<InmuebleModel>(new InmuebleModel()));
		pitmDatosGenerales.addItemProperty("fecha_comodato", new ObjectProperty<Date>(new Date()));
		
		this.binderDatosGeneraler = new FieldGroup(pitmDatosGenerales);
		
		binderDatosGeneraler.bind(this.cb_tipo_activo, "tipo_activo");
		binderDatosGeneraler.bind(this.txt_codigo_activo, "codigo");
		binderDatosGeneraler.bind(this.txt_nombre_activo, "nombre_activo");
		binderDatosGeneraler.bind(this.dtf_fecha_compra, "fecha_compra");
		binderDatosGeneraler.bind(this.dtf_fecha_incorporacion, "fecha_incorporacion");
		binderDatosGeneraler.bind(this.txt_valor_compra, "valor_compra");
		binderDatosGeneraler.bind(this.txt_tipo_cambio_ufv, "tipo_cambio_ufv");
		binderDatosGeneraler.bind(this.txt_vida_util, "vida_util");
		binderDatosGeneraler.bind(this.cb_grupo_contable, "grupo_contable");
		binderDatosGeneraler.bind(this.cb_auxiliar_contable, "auxiliar_contable");
		binderDatosGeneraler.bind(this.cb_fuente_financiamiento, "fuente_financiamiento");
		binderDatosGeneraler.bind(this.cb_organismo_financiador, "organismo_financiador");
		binderDatosGeneraler.bind(this.cb_ubicacion_fisica, "ubicacion_fisica");
		binderDatosGeneraler.bind(this.cb_inmueble, "inmueble");
		binderDatosGeneraler.bind(this.dtf_fecha_comodato, "fecha_comodato");
		
		this.cb_tipo_activo.setRequired(true);
		this.cb_tipo_activo.addValidator(new NullValidator("", false));
		this.txt_codigo_activo.setRequired(true);
		this.txt_codigo_activo.addValidator(new NullValidator("", false));
		this.txt_nombre_activo.setRequired(true);
		this.txt_nombre_activo.addValidator(new NullValidator("", false));
		this.dtf_fecha_compra.setRequired(true);
		this.dtf_fecha_compra.addValidator(new NullValidator("", false));
		this.dtf_fecha_compra.addValidator(new DateRangeValidator(Messages.BAD_DATE, new Date(0), new Date(), null));
		this.dtf_fecha_incorporacion.setRequired(true);
		this.dtf_fecha_incorporacion.addValidator(new NullValidator("", false));
		this.txt_valor_compra.setRequired(true);
		this.txt_valor_compra.addValidator(new NullValidator("", false));
		this.txt_tipo_cambio_ufv.setRequired(true);
		this.txt_tipo_cambio_ufv.addValidator(new NullValidator("", false));
		this.txt_vida_util.setRequired(true);
		this.txt_vida_util.addValidator(new NullValidator("", false));
		this.cb_grupo_contable.setRequired(true);
		this.cb_grupo_contable.addValidator(new NullValidator("", false));
		this.cb_auxiliar_contable.setRequired(true);
		this.cb_auxiliar_contable.addValidator(new NullValidator("", false));
		this.cb_fuente_financiamiento.setRequired(true);
		this.cb_fuente_financiamiento.addValidator(new NullValidator("", false));
		this.cb_ubicacion_fisica.setRequired(true);
		this.cb_ubicacion_fisica.addValidator(new NullValidator("", false));
		this.cb_inmueble.setRequired(true);
		this.cb_inmueble.addValidator(new NullValidator("", false));
		
		this.cb_grupo_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.setInputPrompt("Seleccione un Auxiliar Contable");
		this.cb_inmueble.addValueChangeListener(this);
		this.cb_ubicacion_fisica.setInputPrompt("Seleccione una Ubicacion Física");
		this.dtf_fecha_incorporacion.addValueChangeListener(this);
		this.txt_tipo_cambio_ufv.setEnabled(false);
		
		this.txt_codigo_activo.setEnabled(false);
		this.txt_nombre_activo.setEnabled(false);
		
		buildForm();
		Responsive.makeResponsive(this);
		fillActivo();
	}
	
	private void buildForm() {
		clean();
		
		GridLayout gridlActivos = new GridLayout(2, 1);
		gridlActivos.setColumnExpandRatio(0, 0.5f);
		gridlActivos.setColumnExpandRatio(1, 4f);
		gridlActivos.addComponent(txt_codigo_activo, 0, 0);
		gridlActivos.addComponent(txt_nombre_activo, 1, 0);
		gridlActivos.setWidth("100%");
		gridlActivos.setMargin(true);
		gridlActivos.setSpacing(true);
		Panel pnActivos = new Panel("ACTIVO FIJO");
		pnActivos.setContent(gridlActivos);
		pnActivos.setStyleName(AitTheme.PANEL_FORM);
		pnActivos.setIcon(FontAwesome.EDIT);
		
		addComponent(pnActivos, 0, 0, 5, 0);
		
		addComponent(this.cb_tipo_activo, 0, 1, 1, 1);
		
		addComponent(this.dtf_fecha_compra, 2, 1);
		addComponent(this.txt_valor_compra, 3, 1);
		
		addComponent(this.dtf_fecha_incorporacion, 4, 1);
		addComponent(this.txt_tipo_cambio_ufv, 5, 1);
		
		addComponent(this.cb_grupo_contable, 0, 2, 1, 2);
		addComponent(this.cb_auxiliar_contable, 2, 2, 3, 2);
		addComponent(this.txt_vida_util, 4, 2);
		
		addComponent(this.cb_fuente_financiamiento, 0, 3, 1, 3);
		addComponent(this.cb_organismo_financiador, 2, 3, 3, 3);
		
		addComponent(this.cb_inmueble, 0, 4, 1, 4);
		addComponent(this.cb_ubicacion_fisica, 2, 4, 3, 4);
		addComponent(this.dtf_fecha_comodato, 4, 4);
		
		buildcbTipoActivo();
		buildcbGrupoContables();
		buildcbFuenteFinanciamiento();
		buildcbOrganismoFinanciador();
		buildcbInmueble();
		
	}
	
	private void clean() {
		this.binderDatosGeneraler.clear();
		this.txt_tipo_cambio_ufv.setValue("0,0");
		this.txt_valor_compra.setValue("0,0");
		this.txt_vida_util.setValue("0");
	}
	
	private void buildcbTipoActivo() {
		this.cb_tipo_activo.removeAllItems();
		this.cb_tipo_activo.setNullSelectionAllowed(false);
		this.cb_tipo_activo.setInputPrompt("Seleccione un Tipo de Activo");
		for (Tipos_Activo tipo_activo : this.tipoactimpl.getall()) {
			cb_tipo_activo.addItem(tipo_activo);
			cb_tipo_activo.setItemCaption(tipo_activo, tipo_activo.getTAC_Nombre_Tipo_Activo());
		}
	}
	
	private void buildcbGrupoContables() {
		this.cb_grupo_contable.removeAllItems();
		this.cb_grupo_contable.setNullSelectionAllowed(false);
		this.cb_grupo_contable.setInputPrompt("Seleccione un Grupo Contable");
		for (GruposContablesModel grupo_contable : this.grupocontableimpl.getalls()) {
			cb_grupo_contable.addItem(grupo_contable);
			cb_grupo_contable.setItemCaption(grupo_contable, grupo_contable.getGRC_Nombre_Grupo_Contable());
		}
	}
	
	private void buildcbFuenteFinanciamiento() {
		this.cb_fuente_financiamiento.removeAllItems();
		this.cb_fuente_financiamiento.setNullSelectionAllowed(false);
		this.cb_fuente_financiamiento.setInputPrompt("Seleccione una Fuente de Financiamiento");
		for (Fuentes_Financiamiento fuente_financiamiento : this.fuente_financiamientoimpl.getall()) {
			cb_fuente_financiamiento.addItem(fuente_financiamiento);
			cb_fuente_financiamiento.setItemCaption(fuente_financiamiento,
					fuente_financiamiento.getFFI_Nombre_Fuente_Financiamiento());
		}
	}
	
	private void buildcbOrganismoFinanciador() {
		this.cb_organismo_financiador.removeAllItems();
		this.cb_organismo_financiador.setNullSelectionAllowed(false);
		this.cb_organismo_financiador.setInputPrompt("Seleccione un Organismo Financiador");
		for (Organismo_Financiador organismo_financiador : this.organismo_financiadorimpl.getall()) {
			cb_organismo_financiador.addItem(organismo_financiador);
			cb_organismo_financiador.setItemCaption(organismo_financiador,
					organismo_financiador.getORF_Nombre_Organismo_Financiador());
		}
	}
	
	private void buildcbAxiliaresContables(String grc_Grupo_Contable) {
		this.cb_auxiliar_contable.removeAllItems();
		this.cb_auxiliar_contable.setNullSelectionAllowed(false);
		this.cb_auxiliar_contable.setInputPrompt("Seleccione un Auxiliar Contable");
		for (AuxiliaresContablesModel aux_contable : this.auxcontableimpl.getreporte(grc_Grupo_Contable)) {
			cb_auxiliar_contable.addItem(aux_contable);
			cb_auxiliar_contable.setItemCaption(aux_contable, aux_contable.getAUC_Nombre_Auxiliar_Contable());
		}
	}
	
	private void buildcbInmueble() {
		
		this.cb_inmueble.removeAllItems();
		this.cb_inmueble.setNullSelectionAllowed(false);
		this.cb_inmueble.setInputPrompt("Seleccione un Inmueble");
		for (InmuebleModel inmueble : this.inmuebleimpl.getalls(this.session.getId_dependecia())) {
			cb_inmueble.addItem(inmueble);
			cb_inmueble.setItemCaption(inmueble, inmueble.getINM_Nombre_Inmueble());
		}
	}
	
	private void buildcbUbicacionesFisicas(short id_inmueble) {
		this.cb_ubicacion_fisica.removeAllItems();
		this.cb_ubicacion_fisica.setNullSelectionAllowed(false);
		this.cb_ubicacion_fisica.setInputPrompt("Seleccione una Ubicacion Física");
		for (UbicacionesFisicasModel ubicacion_fisica : this.ubicacionimpl.getbyInmueble(id_inmueble)) {
			cb_ubicacion_fisica.addItem(ubicacion_fisica);
			cb_ubicacion_fisica.setItemCaption(ubicacion_fisica, ubicacion_fisica.getUBF_Nombre_Ubicacion_Fisica());
		}
	}
	
	public Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_guardar_datos_generales);
		this.btn_guardar_datos_generales.addStyleName(AitTheme.BTN_SUBMIT);
		this.btn_guardar_datos_generales.setIcon(FontAwesome.EDIT);
		this.btn_guardar_datos_generales.addClickListener(this);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		buttonContent.addComponent(this.btn_guardar);
		this.btn_guardar.addStyleName(AitTheme.BTN_SUBMIT);
		this.btn_guardar.setIcon(FontAwesome.EDIT);
		this.btn_guardar.addClickListener(this);
		buttonContent.addComponent(this.btn_salir);
		this.btn_salir.addStyleName(AitTheme.BTN_EXIT);
		this.btn_salir.setIcon(FontAwesome.UNDO);
		this.btn_salir.addClickListener(this);
		Responsive.makeResponsive(buttonContent);
		return buttonContent;
	}
	
	public Component buildMessages() {
		CssLayout hl_errores = new CssLayout();
		hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		for (BarMessage barMessage : this.mensajes) {
			Label lbError = new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			lbError.setContentMode(ContentMode.HTML);
			hl_errores.addComponent(lbError);
		}
		this.mensajes = new ArrayList<BarMessage>();
		return hl_errores;
	}
	
	public boolean validate() {
		try {
			this.binderDatosGeneraler.commit();
			if(dtf_fecha_incorporacion.getValue().getTime() > dtf_fecha_compra.getValue().getTime()){
					this.mensajes.add(new BarMessage(dtf_fecha_incorporacion.getCaption(), Messages.BAD_FECHA_INCORPORACION));
				return false;
			}
			return true;
			
		} catch (CommitException cme) {
			Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			try {
				this.cb_tipo_activo.validate();
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(cb_tipo_activo.getCaption(), ive.getMessage()));
			}
			
			try {
				this.txt_codigo_activo.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(txt_codigo_activo.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(txt_codigo_activo.getCaption(), ive.getMessage()));
			}
			
			try {
				this.txt_nombre_activo.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(txt_nombre_activo.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(txt_nombre_activo.getCaption(), ive.getMessage()));
			}
			
			try {
				this.dtf_fecha_compra.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(dtf_fecha_compra.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(dtf_fecha_compra.getCaption(), ive.getMessage()));
			}
			
			try {
				this.txt_valor_compra.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(txt_valor_compra.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(txt_valor_compra.getCaption(), ive.getMessage()));
			}
			
			try {
				this.txt_tipo_cambio_ufv.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(txt_tipo_cambio_ufv.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(txt_tipo_cambio_ufv.getCaption(), ive.getMessage()));
			}
			
			try {
				this.txt_vida_util.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(txt_vida_util.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(txt_vida_util.getCaption(), ive.getMessage()));
			}
			
			try {
				this.cb_grupo_contable.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(cb_grupo_contable.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(cb_grupo_contable.getCaption(), ive.getMessage()));
			}
			try {
				this.cb_auxiliar_contable.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(cb_auxiliar_contable.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(cb_auxiliar_contable.getCaption(), ive.getMessage()));
			}
			try {
				this.cb_fuente_financiamiento.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(cb_fuente_financiamiento.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(cb_fuente_financiamiento.getCaption(), ive.getMessage()));
			}
			try {
				this.cb_organismo_financiador.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(cb_organismo_financiador.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(cb_organismo_financiador.getCaption(), ive.getMessage()));
			}
			try {
				this.cb_ubicacion_fisica.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(cb_ubicacion_fisica.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(cb_ubicacion_fisica.getCaption(), ive.getMessage()));
			}
			try {
				this.cb_inmueble.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(cb_inmueble.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(cb_inmueble.getCaption(), ive.getMessage()));
			}
			try {
				this.dtf_fecha_comodato.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(dtf_fecha_comodato.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(dtf_fecha_comodato.getCaption(), ive.getMessage()));
			}
			return false;
			
		}
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		this.mensajes = new ArrayList<BarMessage>();
		if (event.getButton() == this.btn_guardar) {
			if (validate()) {
				update();
				getUI().getSession().setAttribute("activo", null);
				Notification.show(Messages.SUCCESS_MESSAGE);
				
			} else {
				father.addComponent(buildMessages());
			}
		}
		if (event.getButton() == this.btn_guardar_datos_generales) {
			if (validate()) {
				update();
				this.father.tbs_form.setSelectedTab(1);
				
			} else {
				father.addComponent(buildMessages());
			}
		}
		if (event.getButton() == this.btn_salir) {
			
		}
	}
	
	public void update() {
		
		DatosGeneralesActivos datos_generales = new DatosGeneralesActivos();
		datos_generales.setTipo_activo(((Tipos_Activo) cb_tipo_activo.getValue()).getTAC_Id_Tipo_Activo());
		datos_generales.setId_dependencia(session.getId_dependecia());
		datos_generales.setNombre_activo(this.txt_nombre_activo.getValue());
		datos_generales.setId_activo(Long.parseLong(this.txt_codigo_activo.getValue()));
		datos_generales.setFecha_compra(new java.sql.Date(this.dtf_fecha_compra.getValue().getTime()));
		datos_generales.setFecha_incorporacion(new java.sql.Date(this.dtf_fecha_incorporacion.getValue().getTime()));
		datos_generales.setValor(new BigDecimal(txt_valor_compra.getValue()));
		datos_generales.setTipo_cambio_ufv(new BigDecimal(txt_tipo_cambio_ufv.getValue().toString().replace(",", ".")));
		datos_generales.setId_grupo_contable(((GruposContablesModel) cb_grupo_contable.getValue()).getGRC_Grupo_Contable());
		datos_generales.setId_auxiliar_contalbe(((AuxiliaresContablesModel) cb_auxiliar_contable.getValue())
				.getAUC_Auxiliar_Contable());
		datos_generales.setVida_util(Integer.parseInt(txt_vida_util.getValue()));
		datos_generales.setId_fuente_financiamiento(((Fuentes_Financiamiento) cb_fuente_financiamiento.getValue())
				.getFFI_Fuente_Financiamiento());
		datos_generales.setId_organimismo_financiador(((Organismo_Financiador) cb_organismo_financiador.getValue())
				.getORF_Organismo_Financiador());
		datos_generales.setId_ubicacion_fisica(((UbicacionesFisicasModel) cb_ubicacion_fisica.getValue())
				.getUBF_Ubicacion_Fisica());

		if (dtf_fecha_comodato.getValue() != null) {
			datos_generales.setFecha_como_dato(new java.sql.Date(this.dtf_fecha_comodato.getValue().getTime()));
		}
		if (activoimpl.update(datos_generales)) {
			ActivoSession activo_session = new ActivoSession(datos_generales.getId_activo(), datos_generales.getId_dependencia(),
					datos_generales.getNombre_activo());
			UI.getCurrent().getSession().setAttribute("activo", activo_session);
			clean();
		} else {
			Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
		}
		
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (this.cb_grupo_contable.getValue() != null && event.getProperty().getValue() == this.cb_grupo_contable.getValue()) {
			GruposContablesModel grupo_contable = (GruposContablesModel) this.cb_grupo_contable.getValue();
			buildcbAxiliaresContables(grupo_contable.getGRC_Grupo_Contable());
			this.txt_vida_util.setValue(String.valueOf(grupo_contable.getGRC_Vida_Util()));
		}
		if (this.cb_inmueble.getValue() != null && event.getProperty().getValue() == this.cb_inmueble.getValue()) {
			InmuebleModel inmueble = (InmuebleModel) this.cb_inmueble.getValue();
			buildcbUbicacionesFisicas(inmueble.getINM_Inmueble());
		}
		
		if (this.dtf_fecha_incorporacion.getValue() != null
				&& event.getProperty().getValue() == this.dtf_fecha_incorporacion.getValue()) {
			List<TipoCambio> tipo_cambio = this.tipocambioimpl.getTipoCambio(new java.sql.Date(this.dtf_fecha_incorporacion
					.getValue().getTime()));
			
			if (tipo_cambio.size() == 0) {
				this.mensajes.add(new BarMessage("TIPO CAMBIO", Messages.EMPTY_TIPO_CAMBIO));
				father.addComponent(buildMessages());
				this.txt_tipo_cambio_ufv.setEnabled(true);
			} else {
				this.txt_tipo_cambio_ufv.setEnabled(false);
				if (tipo_cambio.get(0).getMoneda().equals("SUS")) {
					this.txt_tipo_cambio_ufv.setValue(tipo_cambio.get(1).getTipo_cambio().toString().replace(".", ","));
				} else {
					this.txt_tipo_cambio_ufv.setValue(tipo_cambio.get(0).getTipo_cambio().toString().replace(".", ","));
				}
			}
		}
	}
	
	private void fillActivo() {
		if (UI.getCurrent().getSession().getAttribute("activo") != null) {
			this.sessionactivo = (ActivoSession) UI.getCurrent().getSession().getAttribute("activo");
			this.txt_codigo_activo.setValue(String.valueOf(sessionactivo.getCodigo()));
			this.txt_nombre_activo.setValue(String.valueOf(sessionactivo.getNombre_activo()));
			
			try {
				// LLenado de los campos del Formulario
				DatosGeneralesActivos datosGenerales = activoimpl.getDatosGenerales(sessionactivo.getCodigo());
				
				cb_tipo_activo.setValue(tipoactimpl.get(datosGenerales.getTipo_activo()));
				cb_grupo_contable.setValue(grupocontableimpl.get(datosGenerales.getId_grupo_contable()));
				cb_auxiliar_contable.setValue(auxcontableimpl.get(datosGenerales.getId_auxiliar_contalbe(),
						datosGenerales.getId_grupo_contable()));
				txt_vida_util.setValue(String.valueOf(datosGenerales.getVida_util()));
				cb_fuente_financiamiento.setValue(fuente_financiamientoimpl.getone(datosGenerales.getId_fuente_financiamiento()));
				cb_organismo_financiador
						.setValue(organismo_financiadorimpl.getone(datosGenerales.getId_organimismo_financiador()));
				txt_valor_compra.setValue(datosGenerales.getValor().toString());
				dtf_fecha_comodato.setValue(datosGenerales.getFecha_como_dato());
				dtf_fecha_compra.setValue(datosGenerales.getFecha_compra());
				dtf_fecha_incorporacion.setValue(datosGenerales.getFecha_incorporacion());
				txt_tipo_cambio_ufv.setValue(datosGenerales.getTipo_cambio_ufv().toString());
				UbicacionesFisicasModel ubicacion = ubicacionimpl.get(datosGenerales.getId_dependencia(),
						datosGenerales.getId_ubicacion_fisica());
				cb_inmueble.setValue(inmuebleimpl.get(datosGenerales.getId_dependencia(), ubicacion.getUBF_Inmueble_ID()));
				cb_ubicacion_fisica.setValue(ubicacion);
			} catch (Exception ex) {
			}
			if(!activoimpl.esModificable(sessionactivo.getCodigo())){
				dtf_fecha_compra.setEnabled(false);
				dtf_fecha_incorporacion.setEnabled(false);
				cb_grupo_contable.setEnabled(false);
				txt_vida_util.setEnabled(false);
				txt_valor_compra.setEnabled(false);
			}
		}
	}
}
