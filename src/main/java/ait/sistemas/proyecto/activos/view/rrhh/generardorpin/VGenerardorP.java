package ait.sistemas.proyecto.activos.view.rrhh.generardorpin;

import java.util.Date;

import ait.sistemas.proyecto.activos.data.model.PinModel;
import ait.sistemas.proyecto.activos.data.service.Impl.PinImpl;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VGenerardorP extends VerticalLayout implements View, ClickListener{

	private static final long serialVersionUID = 1L;
	
	private Button btn_agregar;
	private GridTipoAutorizacion gridTipoAutorizacion;
	private PinForm frm_pin = new PinForm();
	private TextField txt_nombre_servidor = new TextField("Servidor Publico");
	private final SessionModel session = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
	private final PinImpl pinimpl = new PinImpl();
	public VGenerardorP() {
		this.btn_agregar= new Button("Agregar");
		this.btn_agregar.addClickListener(this);

		this.gridTipoAutorizacion = new GridTipoAutorizacion(session.getId());
		this.txt_nombre_servidor.setEnabled(false);
		this.txt_nombre_servidor.setWidth("100%");
		this.txt_nombre_servidor.setValue(session.getFull_name());
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
	

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_agregar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_agregar);
		buttonContent.addStyleName("ait-buttons");
		return buttonContent;
	}

	private Component buildFormContent() {
				
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true	);
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Nombre del Servidor Publico");
		frmPanel.setContent(txt_nombre_servidor);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Inmuebles registrados");
		gridPanel.setContent(this.gridTipoAutorizacion);
		formContent.setMargin(true);
		
		Panel gridpin = new Panel();
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
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Recursos Humanos>>"));
		nav.addComponent(new Label("Inmuebles>>"));
		nav.addComponent(new Label("<strong>Agregar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}



	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_agregar) {
			PinModel pin = new PinModel();
			pin.setCi(session.getCi());
			pin.setDependencia_id(session.getId_dependecia());
			pin.setFecha_generacion(new java.sql.Date(new Date().getTime()));
			pin.setFecha_registro(new java.sql.Date(new Date().getTime()));
			pin.setPin(frm_pin.getCode());
			pin.setUnidad_organizacional_id(session.getId_unidad_organizacional());
			pin.setUsuario_id(session.getId());
			if(pinimpl.addPIN(pin)){
				Notification.show(Messages.SUCCESS_MESSAGE);
				frm_pin.updatePin();
			}else{
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
		}
	}
}