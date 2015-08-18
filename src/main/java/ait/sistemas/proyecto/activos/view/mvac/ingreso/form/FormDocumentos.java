package ait.sistemas.proyecto.activos.view.mvac.ingreso.form;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.DocumentUploader;
import ait.sistemas.proyecto.activos.component.model.Documento;
import ait.sistemas.proyecto.activos.component.session.ActivoSession;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.view.mvac.ingreso.VActivoA;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;

public class FormDocumentos extends GridLayout implements ClickListener, SelectedTabChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_salir = new Button("Salir");
	private Button btn_guardar = new Button("Guardar");
	private Button btn_agregar = new Button("Agregar Componente");
	private Button btn_eliminar = new Button("Eliminar Componente");
	
	public TextField txt_codigo_activo = new TextField("Codigo Activo");
	public TextField txt_nombre_activo = new TextField("Nombre Activo");
	public TextField txt_nombre_documento = new TextField("Nombre Documento");
	
	private DocumentUploader reciver = new DocumentUploader();
	public Upload field_ubicacion_documento = new Upload("Direccion Documento", reciver);
	public Grid grid_documentos = new Grid();
	
	final PropertysetItem pitmDocumentos = new PropertysetItem();
	private FieldGroup bindeDocumentos;
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private final ActivoImpl activoimpl = new ActivoImpl();
	
	List<Documento> documentos = new ArrayList<Documento>();
	ActivoSession sessionactivo;
	
	VActivoA father;
	
	public FormDocumentos(VActivoA father) {
		super(3, 3);
		setWidth("100%");
		setSpacing(true);
		setMargin(true);
		this.txt_codigo_activo.setWidth("90%");
		this.txt_nombre_activo.setWidth("90%");
		this.field_ubicacion_documento.setWidth("90%");
		this.field_ubicacion_documento.setWidth("90%");
		this.field_ubicacion_documento.setButtonCaption("Subir Documento");
		
		pitmDocumentos.addItemProperty("nombre", new ObjectProperty<String>(""));
		pitmDocumentos.addItemProperty("id", new ObjectProperty<String>(""));
		pitmDocumentos.addItemProperty("nombre_activo", new ObjectProperty<String>(""));
		
		bindeDocumentos = new FieldGroup(pitmDocumentos);
		
		bindeDocumentos.bind(this.txt_nombre_documento, "nombre");
		bindeDocumentos.bind(this.txt_nombre_activo, "id");
		bindeDocumentos.bind(this.txt_codigo_activo, "nombre_activo");
		
		this.txt_codigo_activo.setEnabled(false);
		this.txt_nombre_activo.setEnabled(false);
		
		this.btn_agregar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		this.btn_guardar.addClickListener(this);
		this.btn_eliminar.addClickListener(this);
		
		buildForm();
		buildGrid();
		
		this.father = father;
	}
	
	private void buildForm() {
		setSpacing(true);
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 2);
		
		Panel pn_activo = new Panel();
		GridLayout grid_activo = new GridLayout(2, 1);
		grid_activo.setWidth("100%");
		grid_activo.setMargin(true);
		grid_activo.setColumnExpandRatio(0, 1);
		grid_activo.setColumnExpandRatio(1, 3);
		grid_activo.addComponent(this.txt_codigo_activo, 0, 0);
		grid_activo.addComponent(this.txt_nombre_activo, 1, 0);
		pn_activo.setContent(grid_activo);
		addComponent(pn_activo, 0, 0, 2, 0);
		
		Panel pn_documentos = new Panel();
		GridLayout gridl_documentos = new GridLayout(2, 2);
		gridl_documentos.setWidth("100%");
		gridl_documentos.setMargin(true);
		gridl_documentos.setSpacing(true);
		gridl_documentos.setColumnExpandRatio(0, 1);
		gridl_documentos.setColumnExpandRatio(1, 2);
		gridl_documentos.addComponent(this.txt_nombre_documento, 0, 0);
		gridl_documentos.addComponent(this.field_ubicacion_documento, 1, 0);
		gridl_documentos.addComponent(this.grid_documentos, 0, 1, 1, 1);
		pn_documentos.setContent(gridl_documentos);
		addComponent(pn_documentos, 0, 1, 2, 2);
	}
	
	public Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_agregar.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_agregar);
		this.btn_eliminar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_eliminar);
		this.btn_guardar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_guardar);
		this.btn_salir.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_salir);
		return buttonContent;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_agregar) {
			if (validate()) {
				if (!this.reciver.getFile().equals("")) {
					this.documentos.add(new Documento(this.txt_nombre_documento.getValue(), this.reciver.getFile()));
				} else {
					Notification.show("Debe Presionar el Boton de " + this.field_ubicacion_documento.getButtonCaption());
				}
			} else {
				Notification.show("Debe Presionar el Boton de " + this.field_ubicacion_documento.getButtonCaption());
			}
			buildGrid();
		}
		if (event.getButton() == this.btn_salir) {
		}
		if (event.getButton() == this.btn_guardar) {
			if (validate() && this.documentos.size() > 0) {
				if (activoimpl.addDocumentos(documentos, sessionactivo)) {
					Notification.show(Messages.SUCCESS_MESSAGE);
					this.bindeDocumentos.clear();
				} else {
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
				this.documentos = new ArrayList<Documento>();
				buildGrid();
			}
		}
		if (event.getButton() == this.btn_eliminar) {
			
			for (Object elemnt : this.grid_documentos.getSelectedRows()) {
				this.documentos.remove((Documento) elemnt);
			}
			buildGrid();
		}
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
			this.bindeDocumentos.commit();
			return true;
		} catch (CommitException cme) {
			Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			return false;
		}
	}
	
	private void buildGrid() {
		BeanItemContainer<Documento> bean_documentos = new BeanItemContainer<Documento>(Documento.class, this.documentos);
		grid_documentos.setContainerDataSource(bean_documentos);
		grid_documentos.setHeightMode(HeightMode.ROW);
		grid_documentos.setHeightByRows(7);
		grid_documentos.setWidth("100%");
		grid_documentos.setSelectionMode(SelectionMode.MULTI);
		
		// Grid.Column caracteritica_column =
		// this.grid_documentos.getColumn("Nombre");
		// Grid.Column nombre_column =
		// this.grid_documentos.getColumn("Caracteristica");
		//
		// caracteritica_column.setHeaderCaption("Caracteristica").setExpandRatio(2);
		// nombre_column.setHeaderCaption("Nombre Componente").setExpandRatio(1);
		Responsive.makeResponsive(this);
	}
	
	@Override
	public void selectedTabChange(SelectedTabChangeEvent event) {
		fillActivo();
	}
	
	private void fillActivo() {
		if (UI.getCurrent().getSession().getAttribute("activo") != null) {
			this.sessionactivo = (ActivoSession) UI.getCurrent().getSession().getAttribute("activo");
			this.txt_codigo_activo.setValue(String.valueOf(sessionactivo.getCodigo()));
			this.txt_nombre_activo.setValue(String.valueOf(sessionactivo.getNombre_activo()));
		}
		
	}
	
}