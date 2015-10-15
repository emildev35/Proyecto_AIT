package ait.sistemas.proyecto.activos.view.rrhh.generadorpin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.PinModel;
import ait.sistemas.proyecto.activos.data.service.Impl.PinImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VGeneradorpinP extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_agregar;
	private CssLayout hl_errores = new CssLayout();
	private Button btnSalir = new Button("Salir");
	private GridTipoAutorizacion gridTipoAutorizacion;
	private PinForm frm_pin = new PinForm();
	private TextField txt_nombre_servidor = new TextField("Servidor Publico");
	private TextField txtDependencia = new TextField("Dependencia");
	private TextField txtUnidadOrganizacional = new TextField("Unidad Orgnanizacional");
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	private final PinImpl pinimpl = new PinImpl();
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private final Arbol_menus menu = (Arbol_menus)UI.getCurrent().getSession().getAttribute("nav");
	
	public VGeneradorpinP() {
		this.btn_agregar = new Button("Generar PIN");
		this.btn_agregar.addClickListener(this);
		
		this.gridTipoAutorizacion = new GridTipoAutorizacion(session.getId());
		this.txt_nombre_servidor.setEnabled(false);
		this.txtDependencia.setEnabled(false);
		this.txtUnidadOrganizacional.setEnabled(false);
		this.txt_nombre_servidor.setWidth("100%");
		this.txtDependencia.setWidth("100%");
		this.txtUnidadOrganizacional.setWidth("100%");
		this.txt_nombre_servidor.setValue(session.getFull_name());
		this.txtDependencia.setValue(session.getDependecia());
		this.txtUnidadOrganizacional.setValue(session.getUnidad());
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_agregar.setStyleName(AitTheme.BTN_SUBMIT);
		this.btn_agregar.setIcon(FontAwesome.SAVE);
		buttonContent.addComponent(this.btn_agregar);
		this.btnSalir.setStyleName(AitTheme.BTN_EXIT);
		this.btnSalir.setIcon(FontAwesome.UNDO);
		buttonContent.addComponent(this.btnSalir);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		return buttonContent;
	}
	
	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		
		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		
		Panel frmPanel = new Panel();
		frmPanel.setStyleName(AitTheme.PANEL_FORM);
		frmPanel.setIcon(FontAwesome.EDIT);
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Servidor con Atribuciones de Autorizacion");
		
		
		GridLayout gridlFunionario = new GridLayout(3, 1);
		gridlFunionario.addComponent(txtDependencia, 0, 0);
		gridlFunionario.addComponent(txtUnidadOrganizacional, 1, 0);
		gridlFunionario.addComponent(txt_nombre_servidor, 2, 0);
		
		gridlFunionario.setMargin(true);
		gridlFunionario.setSpacing(true);
		gridlFunionario.setWidth("100%");
		frmPanel.setContent(gridlFunionario);
		
		
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Documentos que Puede Autorizar");
		gridPanel.setContent(this.gridTipoAutorizacion);
		gridPanel.setStyleName(AitTheme.PANEL_GRID);
		gridPanel.setIcon(FontAwesome.TABLE);
		formContent.setMargin(true);
		
		Panel gridpin = new Panel();
		gridpin.setStyleName(AitTheme.PANEL_FORM);
		gridpin.setIcon(FontAwesome.TABLE);
		gridpin.setWidth("100%");
		gridpin.setCaption("N. PIN");
		gridpin.setContent(this.frm_pin);
		formContent.setMargin(true);
		
		formContent.addComponent(frmPanel);
		formContent.addComponent(gridPanel);
		formContent.addComponent(gridpin);
		Responsive.makeResponsive(formContent);
		return formContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label(AitView.getNavText(menu), ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		this.mensajes.clear();
		if (event.getButton() == this.btn_agregar) {
			this.frm_pin.updatePin();
			PinModel pin = new PinModel();
			pin.setCi(session.getCi());
			pin.setDependencia_id(session.getId_dependecia());
			pin.setFecha_generacion(new java.sql.Date(new Date().getTime()));
			pin.setFecha_registro(new java.sql.Date(new Date().getTime()));
			pin.setPin(frm_pin.getCode());
			pin.setUnidad_organizacional_id(session.getId_unidad_organizacional());
			pin.setUsuario_id(session.getId());
			if (pinimpl.addPIN(pin)) {
				this.mensajes.add(new BarMessage("Fomulario", Messages.SUCCESS_MESSAGE, "success"));
			} else {
				this.mensajes.add(new BarMessage("Fomulario", Messages.NOT_SUCCESS_MESSAGE));
			}
			
			buildMessages(this.mensajes);
		}
	}
}