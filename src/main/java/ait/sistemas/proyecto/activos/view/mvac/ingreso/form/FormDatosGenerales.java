package ait.sistemas.proyecto.activos.view.mvac.ingreso.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.Fuentes_Financiamiento;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.model.Organismo_Financiador;
import ait.sistemas.proyecto.activos.data.model.Tipos_Activo;
import ait.sistemas.proyecto.activos.data.model_rrhh.InmuebleModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.UbicacionesFisicasModel;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.FuenteImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.InmuebleImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.OrganismoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposactImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UbicacionImpl;
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
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FormDatosGenerales extends GridLayout implements ClickListener, ValueChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_guardar_datos_generales = new Button("Guardar Datos Generales");
	private Button btn_guardar = new Button("Guardar");
	private Button btn_salir = new Button("Salir");
	
	public ComboBox cb_tipo_activo = new ComboBox("Tipo de Activo");
	public TextField txt_codigo_activo = new TextField("Codigo del Activo");
	public TextField txt_nombre_activo = new TextField("Nombre del Activo");
	public DateField dtf_fecha_compra = new DateField("Fecha Compra");
	public TextField txt_valor_compra = new TextField("Valor de la Compra (Bs)", "######,###BS");
	public TextField txt_tipo_cambio = new TextField("Tipo Cambio");
	public TextField txt_vida_util = new TextField("Vida Util (Años)");
	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxiliar Contable");
	public ComboBox cb_fuente_financiamiento = new ComboBox("Fuente de Financiamineto");
	public ComboBox cb_organismo_financiador = new ComboBox("Organismo Financiador");
	public ComboBox cb_ubicacion_fisica = new ComboBox("Ubicacion Fisica");
	public ComboBox cb_inmueble = new ComboBox("Inmueble");
	public DateField dtf_fecha_incorparacion = new DateField("Fecha Como Dato");
	
	final PropertysetItem pitmDatosGenerales = new PropertysetItem();
	private FieldGroup binderDatosGeneraler;
	
	private final TiposactImpl tipoactimpl = new TiposactImpl();
	private final GrupoImpl grupocontableimpl = new GrupoImpl();
	private final AuxiliarImpl auxcontableimpl = new AuxiliarImpl();
	private final FuenteImpl fuente_financiamientoimpl = new FuenteImpl();
	private final OrganismoImpl organismo_financiadorimpl = new OrganismoImpl();
	private final InmuebleImpl inmuebleimpl = new InmuebleImpl();
	private final UbicacionImpl ubicacionimpl = new UbicacionImpl();
	
	private SessionModel session = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	VerticalLayout father;
	
	public FormDatosGenerales(VerticalLayout father) {
		super(5, 6);
		setWidth("100%");
		setMargin(true);
		
		this.father = father;
		
		this.cb_tipo_activo.setWidth("90%");
		this.txt_codigo_activo.setWidth("90%");
		this.txt_nombre_activo.setWidth("90%");
		this.dtf_fecha_compra.setWidth("90%");
		this.txt_valor_compra.setWidth("90%");
		this.txt_tipo_cambio.setWidth("90%");
		this.txt_vida_util.setWidth("90%");
		this.cb_grupo_contable.setWidth("90%");
		this.cb_auxiliar_contable.setWidth("90%");
		this.cb_fuente_financiamiento.setWidth("90%");
		this.cb_organismo_financiador.setWidth("90%");
		this.cb_ubicacion_fisica.setWidth("90%");
		this.cb_inmueble.setWidth("90%");
		this.dtf_fecha_incorparacion.setWidth("90%");
		
		pitmDatosGenerales.addItemProperty("codigo", new ObjectProperty<String>(""));
		pitmDatosGenerales.addItemProperty("tipo_activo", new ObjectProperty<Short>((short) 0));
		pitmDatosGenerales.addItemProperty("nombre_activo", new ObjectProperty<String>(""));
		pitmDatosGenerales.addItemProperty("fecha_compra", new ObjectProperty<Date>(new Date()));
		pitmDatosGenerales.addItemProperty("valor_compra", new ObjectProperty<Double>((double) 0));
		pitmDatosGenerales.addItemProperty("tipo_cambio", new ObjectProperty<Double>((double) 0));
		pitmDatosGenerales.addItemProperty("vida_util", new ObjectProperty<Integer>(0));
		pitmDatosGenerales.addItemProperty("grupo_contable", new ObjectProperty<GruposContablesModel>(
				new GruposContablesModel()));
		pitmDatosGenerales.addItemProperty("auxiliar_contable", new ObjectProperty<AuxiliaresContablesModel>(
				new AuxiliaresContablesModel()));
		pitmDatosGenerales.addItemProperty("fuente_financiamiento",
				new ObjectProperty<Fuentes_Financiamiento>(new Fuentes_Financiamiento()));
		pitmDatosGenerales.addItemProperty("organismo_financiador", new ObjectProperty<Short>((short) 0));
		pitmDatosGenerales.addItemProperty("ubicacion_fisica", new ObjectProperty<Short>((short) 0));
		pitmDatosGenerales.addItemProperty("inmueble", new ObjectProperty<Short>((short) 0));
		pitmDatosGenerales.addItemProperty("fecha_incorporacion", new ObjectProperty<Date>(new Date()));
		
		this.binderDatosGeneraler = new FieldGroup(pitmDatosGenerales);
		
		binderDatosGeneraler.bind(this.cb_tipo_activo, "tipo_activo");
		binderDatosGeneraler.bind(this.txt_codigo_activo, "codigo");
		binderDatosGeneraler.bind(this.txt_nombre_activo, "nombre_activo");
		binderDatosGeneraler.bind(this.dtf_fecha_compra, "fecha_compra");
		binderDatosGeneraler.bind(this.txt_valor_compra, "valor_compra");
		binderDatosGeneraler.bind(this.txt_tipo_cambio, "tipo_cambio");
		binderDatosGeneraler.bind(this.txt_vida_util, "vida_util");
		binderDatosGeneraler.bind(this.cb_grupo_contable, "grupo_contable");
		binderDatosGeneraler.bind(this.cb_auxiliar_contable, "auxiliar_contable");
		binderDatosGeneraler.bind(this.cb_fuente_financiamiento, "fuente_financiamiento");
		binderDatosGeneraler.bind(this.cb_organismo_financiador, "organismo_financiador");
		binderDatosGeneraler.bind(this.cb_ubicacion_fisica, "ubicacion_fisica");
		binderDatosGeneraler.bind(this.cb_inmueble, "inmueble");
		binderDatosGeneraler.bind(this.dtf_fecha_incorparacion, "fecha_incorporacion");
		
		this.cb_tipo_activo.setRequired(true);
		this.cb_tipo_activo.addValidator(new NullValidator("", false));
		this.txt_codigo_activo.setRequired(true);
		this.txt_codigo_activo.addValidator(new NullValidator("", false));
		this.txt_nombre_activo.setRequired(true);
		this.txt_nombre_activo.addValidator(new NullValidator("", false));
		this.dtf_fecha_compra.setRequired(true);
		this.dtf_fecha_compra.addValidator(new NullValidator("", false));
		this.txt_valor_compra.setRequired(true);
		this.txt_valor_compra.addValidator(new NullValidator("", false));
		this.txt_tipo_cambio.setRequired(true);
		this.txt_tipo_cambio.addValidator(new NullValidator("", false));
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
		
		buildForm();
		Responsive.makeResponsive(this);
	}
	
	private void buildForm() {
		clean();
		
		addComponent(this.cb_tipo_activo, 0, 0, 1, 0);
		addComponent(this.txt_codigo_activo, 2, 0);
		
		addComponent(this.txt_nombre_activo, 0, 1, 4, 1);
		
		addComponent(this.dtf_fecha_compra, 0, 2);
		addComponent(this.txt_valor_compra, 1, 2);
		addComponent(this.txt_tipo_cambio, 2, 2);
		
		addComponent(this.cb_grupo_contable, 0, 3, 1, 3);
		addComponent(this.cb_auxiliar_contable, 2, 3, 3, 3);
		addComponent(this.txt_vida_util, 4, 3);
		
		addComponent(this.cb_fuente_financiamiento, 0, 4, 1, 4);
		addComponent(this.cb_organismo_financiador, 2, 4, 3, 4);
		
		addComponent(this.cb_ubicacion_fisica, 0, 5, 1, 5);
		addComponent(this.cb_inmueble, 2, 5, 3, 5);
		addComponent(this.dtf_fecha_incorparacion, 4, 5);
		buildcbTipoActivo();
		buildcbGrupoContables();
		buildcbFuenteFinanciamiento();
		buildcbOrganismoFinanciador();
		buildcbInmueble(); 
		buildcbUbicacionesFisicas();
		
	}
	
	private void clean(){
		this.binderDatosGeneraler.clear();
		this.txt_tipo_cambio.setValue("0,0");
		this.txt_valor_compra.setValue("0,0");
		this.txt_vida_util.setValue("0");
	}
	private void buildcbTipoActivo() {
		this.cb_tipo_activo.removeAllItems();
		this.cb_tipo_activo.setNullSelectionAllowed(false);
		this.cb_tipo_activo.setInputPrompt("Seleccione un Tipo de Activo");
		for (Tipos_Activo tipo_activo : this.tipoactimpl.getall()) {
			cb_tipo_activo.addItem(tipo_activo.getTAC_Id_Tipo_Activo());
			cb_tipo_activo.setItemCaption(tipo_activo.getTAC_Id_Tipo_Activo(),
					tipo_activo.getTAC_Nombre_Tipo_Activo());
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
			cb_inmueble.setItemCaption(inmueble,
					inmueble.getINM_Nombre_Inmueble());
		}
	}
	private void buildcbUbicacionesFisicas() {
		this.cb_ubicacion_fisica.removeAllItems();
		this.cb_ubicacion_fisica.setNullSelectionAllowed(false);
		this.cb_ubicacion_fisica.setInputPrompt("Seleccione una Ubicacion Fisicica");
		for (UbicacionesFisicasModel ubicacion_fisica : this.ubicacionimpl.getalls(this.session.getId_dependecia())) {
			cb_ubicacion_fisica.addItem(ubicacion_fisica);
			cb_ubicacion_fisica.setItemCaption(ubicacion_fisica,
					ubicacion_fisica.getUBF_Nombre_Ubicacion_Fisica());
		}
	}
	public Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_guardar_datos_generales);
		this.btn_guardar_datos_generales.addStyleName("ait-buttons-btn");
		this.btn_guardar_datos_generales.addClickListener(this);
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_guardar);
		this.btn_guardar.addStyleName("ait-buttons-btn");
		this.btn_guardar.addClickListener(this);
		buttonContent.addComponent(this.btn_salir);
		this.btn_salir.addStyleName("ait-buttons-btn");
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
			hl_errores.addComponent(lbError);
		}
		return hl_errores;
	}
	
	public boolean validate() {
		try {
			this.binderDatosGeneraler.commit();
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
				this.txt_tipo_cambio.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(txt_tipo_cambio.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(txt_tipo_cambio.getCaption(), ive.getMessage()));
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
				this.mensajes.add(new BarMessage(cb_fuente_financiamiento.getCaption(),
						Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(cb_fuente_financiamiento.getCaption(), ive.getMessage()));
			}
			try {
				this.cb_organismo_financiador.validate();
			} catch (EmptyValueException eve) {
				this.mensajes.add(new BarMessage(cb_organismo_financiador.getCaption(),
						Messages.EMPTY_MESSAGE));
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
				this.dtf_fecha_incorparacion.validate();
			} catch (EmptyValueException eve) {
				this.mensajes
						.add(new BarMessage(dtf_fecha_incorparacion.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ive) {
				this.mensajes.add(new BarMessage(dtf_fecha_incorparacion.getCaption(), ive.getMessage()));
			}
			return false;
			
		}
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_guardar) {
			this.mensajes = new ArrayList<BarMessage>();
			if (validate()) {
				
			} else {
				father.addComponent(buildMessages());
			}
		}
		if (event.getButton() == this.btn_guardar_datos_generales) {
			
		}
		if (event.getButton() == this.btn_salir) {
			
		}
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (this.cb_grupo_contable.getValue() != null
				&& event.getProperty().getValue() == this.cb_grupo_contable.getValue()) {
			GruposContablesModel grupo_contable = (GruposContablesModel) this.cb_grupo_contable.getValue();
			buildcbAxiliaresContables(grupo_contable.getGRC_Grupo_Contable());
			this.txt_vida_util.setValue(String.valueOf(grupo_contable.getGRC_Vida_Util()));
		}
	}
	
	
}
