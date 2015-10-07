package ait.sistemas.proyecto.activos.view.mvac.actualiza.form;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Componente;
import ait.sistemas.proyecto.activos.component.session.ActivoSession;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.ComponenteImpl;
import ait.sistemas.proyecto.activos.view.mvac.actualiza.VActualizaTabM;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.FontAwesome;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormComponentes extends GridLayout implements ClickListener, SelectedTabChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_salir = new Button("Salir");
	private Button btn_guardar = new Button("Actualizar");
	private Button btn_agregar = new Button("Agregar Componente");
	private Button btn_eliminar = new Button("Eliminar Componente");
	
	public TextField txt_codigo_activo = new TextField("Codigo Activo");
	public TextField txt_nombre_activo = new TextField("Nombre Activo");
	public TextField txt_nombre_componente = new TextField("Nombre Componente");
	public TextField txt_caracteristica_componente = new TextField("Caracterisitca Componente");
	public Grid grid_componente = new Grid();
	
	final PropertysetItem pitmComponentes = new PropertysetItem();
	private FieldGroup binderCaracteristicas;
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private final ActivoImpl activoimpl = new ActivoImpl();
	private final ComponenteImpl componenteimpl = new ComponenteImpl();
	
	List<Componente> componentes = new ArrayList<Componente>();
	
	ActivoSession sessionactivo;
	
	VActualizaTabM father;
	
	public FormComponentes(VActualizaTabM father) {
		super(3, 3);
		setWidth("100%");
		setSpacing(true);
		setMargin(true);
		
		pitmComponentes.addItemProperty("nombre", new ObjectProperty<String>(""));
		pitmComponentes.addItemProperty("caracteristica", new ObjectProperty<String>(""));
		pitmComponentes.addItemProperty("id", new ObjectProperty<String>(""));
		pitmComponentes.addItemProperty("nombre_activo", new ObjectProperty<String>(""));
		
		binderCaracteristicas = new FieldGroup(pitmComponentes);
		
		binderCaracteristicas.bind(this.txt_nombre_componente, "nombre");
		binderCaracteristicas.bind(this.txt_caracteristica_componente, "caracteristica");
		binderCaracteristicas.bind(this.txt_nombre_activo, "id");
		binderCaracteristicas.bind(this.txt_codigo_activo, "nombre_activo");
		
		this.txt_codigo_activo.setRequired(true);
		this.txt_caracteristica_componente.setRequired(true);
		
		this.btn_agregar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		this.btn_guardar.addClickListener(this);
		this.btn_eliminar.addClickListener(this);
		
		this.txt_codigo_activo.setEnabled(false);
		this.txt_nombre_activo.setEnabled(false);
		this.txt_codigo_activo.setWidth("90%");
		this.txt_nombre_activo.setWidth("100%");
		this.txt_nombre_componente.setWidth("90%");
		this.txt_caracteristica_componente.setWidth("90%");
		buildForm();
		buildGrid();
		this.father = father;
	}
	
	private void buildForm() {
		setSpacing(true);
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 2);
		
		Panel pn_activo = new Panel("ACTIVO FIJO");
		GridLayout grid_activo = new GridLayout(2, 1);
		grid_activo.setWidth("100%");
		grid_activo.setMargin(true);
		grid_activo.setColumnExpandRatio(0, 1);
		grid_activo.setColumnExpandRatio(1, 3);
		grid_activo.addComponent(this.txt_codigo_activo, 0, 0);
		grid_activo.addComponent(this.txt_nombre_activo, 1, 0);
		grid_activo.setColumnExpandRatio(0, 1);
		grid_activo.setColumnExpandRatio(1, 4);
		pn_activo.setContent(grid_activo);
		
		Panel pn_componentes = new Panel("AGREGE Y MODIFIQUE COMPONENTES");
		GridLayout gridl_componentes = new GridLayout(2, 2);
		gridl_componentes.setWidth("100%");
		gridl_componentes.setMargin(true);
		gridl_componentes.setSpacing(true);
		gridl_componentes.setColumnExpandRatio(0, 1);
		gridl_componentes.setColumnExpandRatio(1, 2);
		gridl_componentes.addComponent(this.txt_nombre_componente, 0, 0);
		gridl_componentes.addComponent(this.txt_caracteristica_componente, 1, 0);
		gridl_componentes.addComponent(this.grid_componente, 0, 1, 1, 1);
		pn_componentes.setContent(gridl_componentes);
		
		pn_activo.setStyleName(AitTheme.PANEL_FORM);
		pn_activo.setIcon(FontAwesome.EDIT);
		pn_componentes.setStyleName(AitTheme.PANEL_FORM);
		pn_componentes.setIcon(FontAwesome.EDIT);
		
		addComponent(pn_componentes, 0, 1, 2, 2);
		addComponent(pn_activo, 0, 0, 2, 0);
	}
	
	public Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_agregar.setStyleName(AitTheme.BTN_SUBMIT);
		this.btn_agregar.setIcon(FontAwesome.SAVE);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		buttonContent.addComponent(this.btn_agregar);
		this.btn_eliminar.setStyleName(AitTheme.BTN_EXIT);
		this.btn_eliminar.setIcon(FontAwesome.TRASH_O);
		buttonContent.addComponent(this.btn_eliminar);
		this.btn_guardar.setStyleName(AitTheme.BTN_SUBMIT);
		this.btn_guardar.setIcon(FontAwesome.SAVE);
		buttonContent.addComponent(this.btn_guardar);
		this.btn_salir.setStyleName(AitTheme.BTN_EXIT);
		this.btn_salir.setIcon(FontAwesome.UNDO);
		buttonContent.addComponent(this.btn_salir);
		return buttonContent;
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_agregar) {
			if (validate()) {
				this.componentes.add(new Componente(this.txt_nombre_componente.getValue(), this.txt_caracteristica_componente
						.getValue()));
			} else {
				this.mensajes.add(new BarMessage("Fomulario", "Debe Llenar los Datos del Componente"));
			}
			buildGrid();
		}
		if (event.getButton() == this.btn_salir) {
		}
		if (event.getButton() == this.btn_guardar) {
			// if (this.componentes.size() > 0) {
			if (activoimpl.addComponentes(componentes, sessionactivo)) {
				Notification.show(Messages.SUCCESS_MESSAGE);
				this.father.tbs_form.setSelectedTab(3);
			}
			this.componentes = new ArrayList<Componente>();
			buildGrid();
			// }
			// else{
			// this.mensajes.add(new BarMessage("Fomulario",
			// "Debe Tener al Menos un Componente en el Grid"));
			// }
		}
		if (event.getButton() == this.btn_eliminar) {
			
			for (Object elemnt : this.grid_componente.getSelectedRows()) {
				this.componentes.remove((Componente) elemnt);
			}
			buildGrid();
		}
		father.addComponent(buildMessages());
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
			return false;
		}
	}
	
	private void buildGrid() {
		BeanItemContainer<Componente> bean_componentes = new BeanItemContainer<Componente>(Componente.class, this.componentes);
		grid_componente.setContainerDataSource(bean_componentes);
		grid_componente.setHeightMode(HeightMode.ROW);
		grid_componente.setHeightByRows(7);
		grid_componente.setWidth("100%");
		grid_componente.setSelectionMode(SelectionMode.MULTI);
		
		// Grid.Column caracteritica_column =
		// this.grid_componente.getColumn("Nombre");
		// Grid.Column nombre_column =
		// this.grid_componente.getColumn("Caracteristica");
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
			this.componentes = this.componenteimpl.getComponentes(sessionactivo.getCodigo());
			buildGrid();
			this.btn_agregar.setEnabled(true);
			this.btn_eliminar.setEnabled(true);
			this.btn_guardar.setEnabled(true);
		} else {
			this.btn_agregar.setEnabled(false);
			this.btn_eliminar.setEnabled(false);
			this.btn_guardar.setEnabled(false);
		}
	}
	
}
