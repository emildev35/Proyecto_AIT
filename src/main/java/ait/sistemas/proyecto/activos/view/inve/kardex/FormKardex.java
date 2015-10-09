package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class FormKardex extends GridLayout implements ValueChangeListener, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	public TextField txt_codigoActivo = new TextField("Codigo Activo");
	public DateField dtf_fechaElaboracion = new DateField("Fecha Elaboracion");
	
	public ComboBox cb_Grupo;
	public ComboBox cb_Auxiliar;
	public ComboBox cb_Activos;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	final private GrupoImpl grupo_impl = new GrupoImpl();
	final private AuxiliarImpl auxiliar_impl = new AuxiliarImpl();
	final private ActivoImpl activo_impl = new ActivoImpl();
	final PropertysetItem pitm_Kardex = new PropertysetItem();
	private FieldGroup binder_Kardex;
	
	Panel pnCodigo;
	Panel pnCombos;
	
	public FormKardex() {
		super(3, 4);
		setWidth("100%");
		setMargin(true);
		setSpacing(true);
		
		this.cb_Grupo = new ComboBox("Elija un Grupo Contable");
		this.cb_Grupo.addValueChangeListener(this);
		this.cb_Auxiliar = new ComboBox("Elija un Auxiliar Contable");
		this.cb_Auxiliar.addValueChangeListener(this);
		this.cb_Activos = new ComboBox("Elija un Activo");
		
		pitm_Kardex.addItemProperty("grupo_contable", new ObjectProperty<String>(""));
		pitm_Kardex.addItemProperty("auxiliar_contable", new ObjectProperty<String>(""));
		pitm_Kardex.addItemProperty("activos", new ObjectProperty<String>(""));
		
		this.binder_Kardex = new FieldGroup(pitm_Kardex);
		
		this.binder_Kardex.bind(this.cb_Grupo, "grupo_contable");
		this.binder_Kardex.bind(this.cb_Auxiliar, "auxiliar_contable");
		this.binder_Kardex.bind(this.cb_Activos, "activos");
		this.binder_Kardex.clear();
		
		this.cb_Grupo.addValidator(new NullValidator("", false));
		this.cb_Auxiliar.addValidator(new NullValidator("", false));
		this.cb_Activos.addValidator(new NullValidator("", false));
		
		this.dtf_fechaElaboracion.setValue(new Date());
		fillcbGrupo();
		buildContent();
	}
	
	public void init() {
		update();
	}
	
	/**
	 * Actualizacion de los Campos
	 */
	public void update() {
		this.binder_Kardex.clear();
		this.cb_Grupo.setValue((String) (""));
		this.cb_Auxiliar.setValue((String) (""));
		this.cb_Activos.setValue((String) (""));
		this.dtf_fechaElaboracion.setValue(new Date());
	}
	
	/**
	 * Llenado del Combo Box
	 */
	private void fillcbGrupo() {
		cb_Grupo.setNullSelectionAllowed(false);
		cb_Grupo.setInputPrompt("Seleccione un Grupo Contable");
		for (GruposContablesModel grupo : grupo_impl.getalls()) {
			cb_Grupo.addItem(grupo.getGRC_Grupo_Contable());
			cb_Grupo.setItemCaption(grupo.getGRC_Grupo_Contable(), grupo.getGRC_Nombre_Grupo_Contable());
		}
	}
	
	private void fillcbAuxiliar(String id_grupo) {
		cb_Auxiliar.removeAllItems();
		cb_Auxiliar.setNullSelectionAllowed(false);
		cb_Auxiliar.setInputPrompt("Seleccione el Auxiliar Contable");
		for (AuxiliaresContablesModel auxiliar : auxiliar_impl.getreporte(id_grupo)) {
			cb_Auxiliar.addItem(auxiliar.getAUC_Auxiliar_Contable());
			cb_Auxiliar.setItemCaption(auxiliar.getAUC_Auxiliar_Contable(), auxiliar.getAUC_Nombre_Auxiliar_Contable());
		}
	}
	
	private void fillcbAcivo(String id_auxiliar, String grupoContable) {
		cb_Activos.removeAllItems();
		cb_Activos.setNullSelectionAllowed(false);
		cb_Activos.setInputPrompt("Seleccione el Activo");
		for (ActivosModel activo : activo_impl.activos_by_auxiliar(id_auxiliar, grupoContable)) {
			cb_Activos.addItem(activo.getACT_Codigo_Activo());
			cb_Activos.setItemCaption(activo.getACT_Codigo_Activo(), activo.getACT_Nombre_Activo());
		}
		String a = "ALL";
		cb_Activos.addItem(a);
		cb_Activos.setItemCaption(a, "Todos los Activos");
	}
	
	private void buildContent() {
		
		this.cb_Grupo.setWidth("100%");
		this.cb_Auxiliar.setWidth("100%");
		this.cb_Activos.setWidth("100%");
		
		setColumnExpandRatio(0, 0.3f);
		setColumnExpandRatio(1, 1f);
		setColumnExpandRatio(2, 1f);
		
		GridLayout gridlFecha = new GridLayout(1, 1);
		gridlFecha.setWidth("100%");
		GridLayout gridlCodigo = new GridLayout(1, 1);
		gridlCodigo.setWidth("100%");
		gridlCodigo.setHeight("100%");
		GridLayout gridlCombos = new GridLayout(1, 3);
		gridlCombos.setWidth("100%");
		gridlCodigo.setMargin(true);
		gridlFecha.setMargin(true);
		gridlCombos.setMargin(true);
		
		Panel pnFecha = new Panel("FECHA ELABORACION");
		pnFecha.setContent(gridlFecha);
		pnFecha.setStyleName(AitTheme.PANEL_PRINT);
		pnFecha.setIcon(FontAwesome.EDIT);
		pnCodigo = new Panel("SELECCION POR CODIGO");
		pnCodigo.setContent(gridlCodigo);
		pnCodigo.setStyleName(AitTheme.PANEL_PRINT);
		pnCodigo.setIcon(FontAwesome.EDIT);
		pnCombos = new Panel("SELECCION POR GRUPO Y AUXILIAR CONTABLE");
		pnCombos.setContent(gridlCombos);
		pnCombos.setStyleName(AitTheme.PANEL_PRINT);
		pnCombos.setIcon(FontAwesome.EDIT);
		
		pnCodigo.addClickListener(this);
		pnCombos.addClickListener(this);
		
		gridlFecha.addComponent(dtf_fechaElaboracion, 0, 0);
		gridlCodigo.addComponent(txt_codigoActivo, 0, 0);
		gridlCombos.addComponent(this.cb_Grupo, 0, 0);
		gridlCombos.addComponent(this.cb_Auxiliar, 0, 1);
		gridlCombos.addComponent(this.cb_Activos, 0, 2);
		
		addComponent(pnFecha, 0, 0);
		addComponent(pnCodigo, 1, 0);
		addComponent(pnCombos, 1, 1, 1, 3);
		
	}
	
	public boolean validate() {
		if (txt_codigoActivo.getValue() != null) {
			return true;
		}
		try {
			this.binder_Kardex.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_Grupo.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_Grupo.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_Auxiliar.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_Auxiliar.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_Activos.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_Activos.getCaption(), Messages.EMPTY_MESSAGE));
			}
			return false;
		}
	}
	
	public List<BarMessage> getMessage() {
		return this.mensajes;
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		// updateId();
		if (event.getProperty().getValue() == this.cb_Grupo.getValue() && (String) this.cb_Grupo.getValue() != null) {
			fillcbAuxiliar((String) this.cb_Grupo.getValue());
		}
		if (event.getProperty().getValue() == this.cb_Auxiliar.getValue() && (String) this.cb_Auxiliar.getValue() != null) {
			fillcbAcivo((String) this.cb_Auxiliar.getValue(), (String)this.cb_Grupo.getValue());
		}
	}
	
	public String getActivo() {
		if (txt_codigoActivo.getValue() != null && !txt_codigoActivo.getValue().equals("")) {
			return this.txt_codigoActivo.getValue().toString();
		}
		return cb_Activos.getValue().toString();
	}
	
	@Override
	public void click(ClickEvent event) {
		
		if (event.getSource() == pnCodigo) {
			this.cb_Activos.setEnabled(false);
			this.cb_Auxiliar.setEnabled(false);
			this.cb_Grupo.setEnabled(false);
			this.txt_codigoActivo.setEnabled(true);
		}
		
		if (event.getSource() == pnCombos) {
			this.cb_Activos.setEnabled(true);
			this.cb_Auxiliar.setEnabled(true);
			this.cb_Grupo.setEnabled(true);
			this.txt_codigoActivo.setEnabled(false);
		}
		
	}
}
