package ait.sistemas.proyecto.activos.view.mvac.solactivos;

import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class VSolactivosA extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private FormSolactivos frm_solicitud;
	private CssLayout hl_errores;
	private Button btn_limpiar = new Button("Salir");
	private Button btn_agregar = new Button("Solicitar Activo");
	private Button btn_anular = new Button("Anular Seleccon de Activos");
	private Button btnSolicitar = new Button("Guardar Solicitud");
	
	private final Arbol_menus menu = (Arbol_menus)UI.getCurrent().getSession().getAttribute("nav");
	private  MovimientoImpl movimientoimpl = new MovimientoImpl();
	
	public VSolactivosA() {
		frm_solicitud = new FormSolactivos();
		this.btn_agregar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		this.btn_anular.addClickListener(this);
		this.btnSolicitar.addClickListener(this);
		
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		
		this.btn_agregar.setStyleName(AitTheme.BTN_SUBMIT);
		this.btn_agregar.setIcon(FontAwesome.SAVE);
		this.btn_anular.setStyleName(AitTheme.BTN_PRINT);
		this.btn_anular.setIcon(FontAwesome.TRASH_O);
		this.btnSolicitar.setStyleName(AitTheme.BTN_SUBMIT);
		this.btnSolicitar.setIcon(FontAwesome.SAVE);
		this.btn_limpiar.setStyleName(AitTheme.BTN_EXIT);
		this.btn_limpiar.setIcon(FontAwesome.UNDO);

		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		
		buttonContent.addComponent(this.btn_agregar);
		buttonContent.addComponent(this.btn_limpiar);
		buttonContent.addComponent(this.btn_anular);
		buttonContent.addComponent(this.btnSolicitar);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);

		Panel gridPanel = new Panel("Activos Fijos Disponibles : Selecciona los Activos");
		gridPanel.setStyleName(AitTheme.PANEL_GRID);
		gridPanel.setIcon(FontAwesome.TABLE);
		gridPanel.setWidth("100%");
		gridPanel.setCaption("ACTIVOS FIJOS DISPONIBLE: Seleccione los Activos Fijos Requeridos");
		gridPanel.setContent(this.frm_solicitud.getgrid_solicitud());

		Panel gridSolicitados = new Panel("ACTIVOS FIJOS SOLICITADOS");
		gridSolicitados.setStyleName(AitTheme.PANEL_GRID);
		gridSolicitados.setIcon(FontAwesome.TABLE);
		gridSolicitados.setWidth("100%");
		gridSolicitados.setCaption("ACTIVOS SOLICITADOS");
		gridSolicitados.setContent(this.frm_solicitud.getgridSolicitados());

		formContent.setMargin(true);
		formContent.addComponent(frm_solicitud);
		formContent.addComponent(gridPanel);
		formContent.addComponent(gridSolicitados);
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
	
	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		
		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName()));
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_agregar) {
			frm_solicitud.addActivo();
		}
		if (event.getButton() == this.btn_limpiar) {
			
		}
		if(event.getButton() == btn_anular){
			frm_solicitud.deleteActivo();
			
		}
		if (event.getButton() == btnSolicitar) {
			if (this.frm_solicitud.validate()) {
				if (movimientoimpl.addMovimiento(this.frm_solicitud.getData())>0) {
					this.frm_solicitud.clear();
					Notification.show(Messages.SUCCESS_MESSAGE);
				}
				else{
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_solicitud.getMensajes());
			this.frm_solicitud.clearMessages();
		}
	}
	
}