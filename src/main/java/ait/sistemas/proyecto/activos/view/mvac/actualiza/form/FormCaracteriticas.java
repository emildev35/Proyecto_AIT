package ait.sistemas.proyecto.activos.view.mvac.actualiza.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.CaracteristicasActivo;
import ait.sistemas.proyecto.activos.component.session.ActivoSession;
import ait.sistemas.proyecto.activos.data.model.ProveedoresModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.ProveedorImpl;
import ait.sistemas.proyecto.activos.view.mvac.actualiza.VActualizaM;
import ait.sistemas.proyecto.activos.view.mvac.actualiza.VActualizaTabM;
import ait.sistemas.proyecto.activos.view.mvac.ingreso.form.FormImageUpload;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.FontAwesome;
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
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormCaracteriticas extends GridLayout implements ClickListener, SelectedTabChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_guardar_caracteristicas = new Button("Actualizar Caracteristicas");
	private Button btn_guardar = new Button("Actualizar");
	private Button btn_salir = new Button("Salir");
	private Button btn_seleccion = new Button("Seleccion");
	
	public TextField txt_codigo_activo = new TextField("Codigo");
	public TextField txt_nombre_activo = new TextField("Nombre Activo");
	public ComboBox cb_proveedor = new ComboBox("Proveedor");
	public TextField txt_marca = new TextField("Marca");
	public TextField txt_numero_serie = new TextField("Numero Serie");
	public TextField txt_numero_garantia = new TextField("Numero Garantia");
	public TextField txt_tiempo_garantia = new TextField("Tiempo Garantia");
	public DateField dtf_vencimiento_garantia = new DateField("Fecha de Vencimiento");
	public TextField txt_numero_ruat = new TextField("N. de RUAT");
	public TextField txt_numero_folio_real = new TextField("N. de Folio Real");
	public TextField txt_numero_poliza_seguro = new TextField("N. de Poliza de Seguro");
	public DateField dtf_vencimiento_seguro = new DateField("Fecha de  Vcto de Seguro");
	public TextField txt_numero_contrato_mantenimiento = new TextField("N. de Contrato de Mantenimiento");
	public DateField dtf_vcto_contrato_mantenimientno = new DateField("Fecha de Vcto de Contrato");
	public TextField nroLicencia = new TextField("N. Licencia");
	public DateField dtfVctoLicencia = new DateField("Vcto. Licencia");
	
	public TextField txt_nombre_imagen = new TextField();
	
	private GridLayout grid_activo_fijo = new GridLayout(6, 1);
	private GridLayout grid_caracteristica = new GridLayout(6, 7);
	
	private GridLayout grid_imagen = new GridLayout(1, 1);
	
	public FormImageUpload frm_imagen = new FormImageUpload();
	
	final PropertysetItem pitmCaracteristicas = new PropertysetItem();
	private FieldGroup binderCaracteristicas;
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private final ActivoImpl activoimpl = new ActivoImpl();
	private final ProveedorImpl proveedorimpl = new ProveedorImpl();
	private SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	
	ActivoSession sessionactivo;
	
	VActualizaTabM father;
	
	private CaracteristicasActivo caracteristicaActivo;
	
	public FormCaracteriticas(VActualizaTabM father) {
		super(7, 8);
		setWidth("100%");
		setSpacing(true);
		setMargin(true);
		this.txt_codigo_activo.setWidth("90%");
		this.txt_nombre_activo.setWidth("100%");
		this.cb_proveedor.setWidth("90%");
		this.txt_marca.setWidth("90%");
		this.txt_numero_serie.setWidth("90%");
		this.txt_numero_garantia.setWidth("90%");
		this.txt_tiempo_garantia.setWidth("90%");
		this.dtf_vencimiento_garantia.setWidth("90%");
		this.txt_numero_ruat.setWidth("90%");
		this.txt_numero_folio_real.setWidth("90%");
		this.txt_numero_poliza_seguro.setWidth("90%");
		this.dtf_vencimiento_seguro.setWidth("90%");
		this.txt_numero_contrato_mantenimiento.setWidth("90%");
		this.dtf_vcto_contrato_mantenimientno.setWidth("90%");
		this.nroLicencia.setWidth("90%");
		this.dtfVctoLicencia.setWidth("90%");
		
		pitmCaracteristicas.addItemProperty("codigo", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("proveedor", new ObjectProperty<ProveedoresModel>(new ProveedoresModel()));
		pitmCaracteristicas.addItemProperty("marca", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("numero_serie", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("numero_garantia", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("tiempo_garantia", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("vencimiento_garantia", new ObjectProperty<Date>(new Date()));
		pitmCaracteristicas.addItemProperty("numero_ruat", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("numero_folio_real", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("numero_poliza_seguro", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("vencimiento_seguro", new ObjectProperty<Date>(new Date()));
		pitmCaracteristicas.addItemProperty("numero_contrato_mantenumiento", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("vencimiento_contrato_mantenimiento", new ObjectProperty<Date>(new Date()));
		pitmCaracteristicas.addItemProperty("nombre_imagen", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("numero_licencia", new ObjectProperty<String>(""));
		pitmCaracteristicas.addItemProperty("vencimiento_licencia", new ObjectProperty<Date>(new Date()));
		
		this.binderCaracteristicas = new FieldGroup(pitmCaracteristicas);
		
		this.binderCaracteristicas.bind(this.txt_codigo_activo, "codigo");
		this.binderCaracteristicas.bind(this.cb_proveedor, "proveedor");
		this.binderCaracteristicas.bind(this.txt_marca, "marca");
		this.binderCaracteristicas.bind(this.txt_numero_serie, "numero_serie");
		this.binderCaracteristicas.bind(this.txt_numero_garantia, "numero_garantia");
		this.binderCaracteristicas.bind(this.txt_tiempo_garantia, "tiempo_garantia");
		this.binderCaracteristicas.bind(this.dtf_vencimiento_garantia, "vencimiento_garantia");
		this.binderCaracteristicas.bind(this.txt_numero_ruat, "numero_ruat");
		this.binderCaracteristicas.bind(this.txt_numero_folio_real, "numero_folio_real");
		this.binderCaracteristicas.bind(this.txt_numero_poliza_seguro, "numero_poliza_seguro");
		this.binderCaracteristicas.bind(this.dtf_vencimiento_seguro, "vencimiento_seguro");
		this.binderCaracteristicas.bind(this.txt_numero_contrato_mantenimiento, "numero_contrato_mantenumiento");
		this.binderCaracteristicas.bind(this.dtf_vcto_contrato_mantenimientno, "vencimiento_contrato_mantenimiento");
		this.binderCaracteristicas.bind(this.txt_nombre_imagen, "nombre_imagen");
		
		this.txt_codigo_activo.setEnabled(false);
		this.txt_nombre_activo.setEnabled(false);
		
		this.father = father;
		
		fuildForm();
		buildcbProveedor();
		fillActivo();
	}
	
	private void fuildForm() {
		clean();
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		setColumnExpandRatio(3, 1);
		setColumnExpandRatio(4, 1);
		setColumnExpandRatio(5, 1);
		setColumnExpandRatio(6, 5);
		
		grid_activo_fijo.addComponent(this.txt_codigo_activo, 0, 0);
		grid_activo_fijo.addComponent(this.txt_nombre_activo, 1, 0, 5, 0);
		
		final Panel pn_activo = new Panel("IDENTIFACOR DEL ACTIVO");
		grid_activo_fijo.setSizeFull();
		grid_activo_fijo.setMargin(true);
		pn_activo.setContent(grid_activo_fijo);
		addComponent(pn_activo, 0, 0, 5, 0);
		
		grid_caracteristica.addComponent(this.cb_proveedor, 0, 0, 5, 0);
		
		grid_caracteristica.addComponent(this.txt_marca, 0, 1, 2, 1);
		grid_caracteristica.addComponent(this.txt_numero_serie, 3, 1, 4, 1);
		
		grid_caracteristica.addComponent(this.txt_numero_garantia, 0, 2, 1, 2);
		grid_caracteristica.addComponent(this.txt_tiempo_garantia, 2, 2, 3, 2);
		grid_caracteristica.addComponent(this.dtf_vencimiento_garantia, 4, 2, 5, 2);
		
		grid_caracteristica.addComponent(this.txt_numero_ruat, 0, 3, 2, 3);
		grid_caracteristica.addComponent(this.txt_numero_folio_real, 3, 3, 5, 3);
		
		grid_caracteristica.addComponent(this.txt_numero_poliza_seguro, 0, 4, 2, 4);
		grid_caracteristica.addComponent(this.dtf_vencimiento_seguro, 3, 4, 5, 4);
		
		grid_caracteristica.addComponent(this.txt_numero_contrato_mantenimiento, 0, 5, 2, 5);
		grid_caracteristica.addComponent(this.dtf_vcto_contrato_mantenimientno, 3, 5, 5, 5);
		
		grid_caracteristica.addComponent(this.nroLicencia, 0, 6, 2, 6);
		grid_caracteristica.addComponent(this.dtfVctoLicencia, 3, 6, 5, 6);
		
		final Panel pn_caracteristicas = new Panel("EDITE LAS CARACTERISTICAS DE LOS ACTIVOS");
		grid_caracteristica.setSizeFull();
		grid_caracteristica.setMargin(true);
		pn_caracteristicas.setContent(grid_caracteristica);
		addComponent(pn_caracteristicas, 0, 1, 5, 5);
		
		grid_imagen.addComponent(this.frm_imagen, 0, 0);
		
		final Panel pn_imagen = new Panel("SELECCIONES LA FOTGRAFIA DEL ACTIVO");
		pn_imagen.setContent(grid_imagen);
		grid_imagen.setMargin(true);
		grid_imagen.setSizeFull();
		pn_imagen.setSizeFull();
		
		pn_imagen.setStyleName(AitTheme.PANEL_FORM);
		pn_imagen.setIcon(FontAwesome.IMAGE);
		
		pn_activo.setStyleName(AitTheme.PANEL_FORM);
		pn_activo.setIcon(FontAwesome.EDIT);
		
		pn_caracteristicas.setStyleName(AitTheme.PANEL_FORM);
		pn_caracteristicas.setIcon(FontAwesome.IMAGE);
		addComponent(pn_imagen, 6, 0, 6, 6);
		
	}
	
	private void clean() {
		this.binderCaracteristicas.clear();
	}
	
	public void fillActivo() {
		if (UI.getCurrent().getSession().getAttribute("activo") != null) {
			this.sessionactivo = (ActivoSession) UI.getCurrent().getSession().getAttribute("activo");
			this.txt_codigo_activo.setValue(String.valueOf(sessionactivo.getCodigo()));
			this.txt_nombre_activo.setValue(String.valueOf(sessionactivo.getNombre_activo()));
			
			caracteristicaActivo = activoimpl.getCaracteristicas(sessionactivo.getCodigo());
			this.txt_numero_serie.setValue(caracteristicaActivo.getNumero_serie() == null ? "" : caracteristicaActivo
					.getNumero_serie());
			this.txt_numero_ruat.setValue(caracteristicaActivo.getNumero_ruat() == null ? "" : caracteristicaActivo
					.getNumero_ruat());
			this.txt_numero_contrato_mantenimiento.setValue(caracteristicaActivo.getNumero_contrato_mantenimiento() == null ? ""
					: caracteristicaActivo.getNumero_contrato_mantenimiento());
			this.txt_numero_folio_real.setValue(caracteristicaActivo.getNumero_folio_real() == null ? "" : caracteristicaActivo
					.getNumero_folio_real());
			this.txt_numero_garantia.setValue(caracteristicaActivo.getNumero_garantia() == null ? "" : caracteristicaActivo
					.getNumero_garantia());
			this.txt_numero_poliza_seguro.setValue(caracteristicaActivo.getNumero_poliza_seguro() == null ? ""
					: caracteristicaActivo.getNumero_poliza_seguro());
			this.txt_marca.setValue(caracteristicaActivo.getMarca() == null ? "" : caracteristicaActivo.getMarca());
			this.dtf_vcto_contrato_mantenimientno.setValue(caracteristicaActivo.getVencimiento_contrato_mantenumiento());
			this.dtf_vencimiento_garantia.setValue(caracteristicaActivo.getVencimiento_garantia());
			this.dtf_vencimiento_seguro.setValue(caracteristicaActivo.getVencimiento_seguro());
			this.dtfVctoLicencia.setValue(caracteristicaActivo.getVencimientoLicencia());
			this.cb_proveedor.setValue(caracteristicaActivo.getNit_proveedor());
			UI.getCurrent().getSession().setAttribute("proveedor", proveedorimpl.get(caracteristicaActivo.getNit_proveedor()));
			if (caracteristicaActivo.getUbicacion_imagen() != null) {
				this.frm_imagen.setImage(caracteristicaActivo.getUbicacion_imagen());
			}
			this.nroLicencia.setValue(caracteristicaActivo.getNumeroLicencia() == null ? "" : caracteristicaActivo
					.getNumeroLicencia());
			this.btn_guardar.setEnabled(true);
			this.btn_guardar_caracteristicas.setEnabled(true);
			this.btn_guardar.setEnabled(true);
		} else {
			this.mensajes.add(new BarMessage("Formulario", Messages.ACTIVO_NO_ENCONTRADO));
			this.btn_guardar.setEnabled(false);
			this.btn_guardar_caracteristicas.setEnabled(false);
			this.btn_guardar.setEnabled(false);
		}
	}
	
	private void buildcbProveedor() {
		this.cb_proveedor.removeAllItems();
		this.cb_proveedor.setInputPrompt("Seleccion un Proveedor");
		this.cb_proveedor.setNullSelectionAllowed(false);
		for (ProveedoresModel proveedor : proveedorimpl.getByDependencia(session.getId_dependecia())) {
			this.cb_proveedor.addItem(proveedor.getPRV_NIT());
			this.cb_proveedor.setItemCaption(proveedor.getPRV_NIT(), proveedor.getPRV_Nombre());
		}
	}
	
	public Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_guardar_caracteristicas);
		this.btn_guardar_caracteristicas.addStyleName(AitTheme.BTN_SUBMIT);
		this.btn_salir.setIcon(FontAwesome.SAVE);
		this.btn_guardar_caracteristicas.addClickListener(this);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		buttonContent.addComponent(this.btn_guardar);
		this.btn_guardar.addStyleName(AitTheme.BTN_SUBMIT);
		this.btn_salir.setIcon(FontAwesome.SAVE);
		this.btn_guardar.addClickListener(this);
		buttonContent.addComponent(this.btn_seleccion);
		this.btn_seleccion.addStyleName(AitTheme.BTN_SUBMIT);
		this.btn_seleccion.setIcon(FontAwesome.SEARCH);
		this.btn_seleccion.addClickListener(this);
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
			hl_errores.addComponent(lbError);
		}
		return hl_errores;
	}
	
	public boolean validate() {
		try {
			this.binderCaracteristicas.commit();
			return true;
		} catch (CommitException cme) {
			Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			return false;
			
		}
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		this.mensajes = new ArrayList<BarMessage>();
		if (event.getButton() == this.btn_guardar) {
			if (validate()) {
				save();
				clean();
			} else {
				father.buildMessages(this.mensajes);
			}
		}
		if (event.getButton() == this.btn_guardar_caracteristicas) {
			if (validate()) {
				save();
				this.father.tbs_form.setSelectedTab(2);
			} else {
				father.buildMessages(this.mensajes);
			}
		}
		if (event.getButton() == this.btn_salir) {
			
			UI.getCurrent().getNavigator().navigateTo(VActualizaM.URL);
		}
		if (event.getButton() == this.btn_seleccion) {

			UI.getCurrent().getNavigator().navigateTo(VActualizaM.URL);
		}
	}
	
	public void save() {
		CaracteristicasActivo caracteristicas = new CaracteristicasActivo();
		caracteristicas.setCodigo(this.txt_codigo_activo.getValue());
		caracteristicas.setDependencia(this.sessionactivo.getDependencia());
		caracteristicas.setMarca(this.txt_marca.getValue());
		caracteristicas.setNit_proveedor(this.cb_proveedor.getValue().toString());
		caracteristicas.setNumero_contrato_mantenimiento(this.txt_numero_contrato_mantenimiento.getValue());
		caracteristicas.setNumero_folio_real(this.txt_numero_folio_real.getValue());
		caracteristicas.setNumero_garantia(txt_numero_garantia.getValue());
		caracteristicas.setNumero_poliza_seguro(txt_numero_poliza_seguro.getValue());
		caracteristicas.setNumero_ruat(txt_numero_ruat.getValue());
		caracteristicas.setNumero_serie(txt_numero_serie.getValue());
		caracteristicas.setNumeroLicencia(nroLicencia.getValue());
		if (dtf_vencimiento_garantia.getValue() != null) {
			caracteristicas.setVencimiento_garantia(new java.sql.Date(dtf_vencimiento_garantia.getValue().getTime()));
		}
		if (dtf_vcto_contrato_mantenimientno.getValue() != null) {
			caracteristicas.setVencimiento_contrato_mantenumiento(new java.sql.Date(dtf_vcto_contrato_mantenimientno.getValue()
					.getTime()));
		}
		if (dtf_vencimiento_seguro.getValue() != null) {
			caracteristicas.setVencimiento_seguro(new java.sql.Date(dtf_vencimiento_seguro.getValue().getTime()));
		}
		if (dtfVctoLicencia.getValue() != null) {
			caracteristicas.setVencimientoLicencia(new java.sql.Date(dtfVctoLicencia.getValue().getTime()));
		}
		caracteristicas.setUbicacion_imagen(this.frm_imagen.getFile());
		
		if (activoimpl.addCaracteristica(caracteristicas)) {
			Notification.show(Messages.SUCCESS_MESSAGE);
			
		} else {
			Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
		this.cb_proveedor.setValue((ProveedoresModel) UI.getCurrent().getSession().getAttribute("proveedor"));
	}
}
