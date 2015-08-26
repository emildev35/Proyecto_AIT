package ait.sistemas.proyecto.activos.view.para.estadosop;

import java.util.List;

import ait.sistemas.proyecto.activos.data.model.EstadoSoporte;
import ait.sistemas.proyecto.activos.data.service.Impl.EstadoSoporteImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.cdi.CDIView;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
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
import com.vaadin.ui.VerticalLayout;

@CDIView(value = VEstadoSoporteM.URL)
public class VEstadoSoporteM extends VerticalLayout implements View,
		SelectionListener, ClickListener {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/act/para/estadosoporte/m";

	private final EstadoSoporteImpl estado_soporteimpl = new EstadoSoporteImpl();
	private GridEstadoSoporte grid_estado_soporte;
	private FormEstadoSoporte frm_estado_soporte;
	private Button btn_modificar;
	private Button btn_limpiar;
	private CssLayout hl_errores;
	
	public VEstadoSoporteM() {

		frm_estado_soporte= new FormEstadoSoporte();
		this.btn_limpiar= new Button("Limpiar");
		this.btn_modificar= new Button("Modificar");
		this.btn_modificar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);

		this.grid_estado_soporte = new GridEstadoSoporte();
		this.grid_estado_soporte.addSelectionListener(this);
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
		
	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true	);	
		
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("TIPO DE SOPORTE A MODIFICAR");
		gridPanel.setContent(this.grid_estado_soporte);
		formContent.setMargin(true);
				
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("TIPOS DE SOPORTE REGISTRADOS");
		frmPanel.setContent(this.frm_estado_soporte);
		formContent.setMargin(true);
		
		formContent.addComponent(gridPanel);
		formContent.addComponent(frmPanel);
		this.frm_estado_soporte.update();
		Responsive.makeResponsive(formContent);
		return formContent;
	}
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Parametros » "));
		nav.addComponent(new Label("Estado de Soporte » "));
		nav.addComponent(new Label("<strong>Modificar</strong>",
				ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_modificar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_modificar);
		this.btn_limpiar.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
		Responsive.makeResponsive(buttonContent);
		return buttonContent;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public void select(SelectionEvent event) {

		if ((EstadoSoporte)this.grid_estado_soporte.getSelectedRow() != null) {
			this.frm_estado_soporte.setData((EstadoSoporte)this.grid_estado_soporte.getSelectedRow());	
		}
	}
	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		
		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(new Label(barMessage.getComponetName()+":"+barMessage.getErrorName()));
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	
	}
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_modificar) {
			if(this.frm_estado_soporte.validate()){
				if (this.estado_soporteimpl.modificar(this.frm_estado_soporte.getData())>0) {
					grid_estado_soporte.update();
					this.frm_estado_soporte.update();
					Notification.show(Messages.SUCCESS_MESSAGE);
				}
				else{
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
			}else{
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_estado_soporte.getMensajes());
			this.frm_estado_soporte.clearMessages();
		}
		if (event.getButton() == this.btn_limpiar) {
			this.frm_estado_soporte.update();
		}
	}

}

