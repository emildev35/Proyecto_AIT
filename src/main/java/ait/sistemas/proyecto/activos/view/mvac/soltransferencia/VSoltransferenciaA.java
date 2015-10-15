package ait.sistemas.proyecto.activos.view.mvac.soltransferencia;

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
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class VSoltransferenciaA extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private FormSoltransferencia frm_solicitud;
	private CssLayout hl_errores;
	private Button btn_limpiar= new Button("Limpiar");
	private Button btn_agregar = new Button("Generar Solicitud");
	Button btn_salir = new Button("Salir");
	private final Arbol_menus menu = (Arbol_menus)UI.getCurrent().getSession().getAttribute("nav");
	private final MovimientoImpl movimientoimpl = new MovimientoImpl();
	
	public VSoltransferenciaA() {
		frm_solicitud = new FormSoltransferencia();
		this.btn_agregar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_agregar.setStyleName(AitTheme.BTN_SUBMIT);
		this.btn_agregar.setIcon(FontAwesome.SAVE);
		buttonContent.addComponent(this.btn_agregar);
		this.btn_limpiar.setStyleName(AitTheme.BTN_PRINT);
		this.btn_limpiar.setIcon(FontAwesome.TRASH_O);
		buttonContent.addComponent(this.btn_limpiar);
		this.btn_salir.setStyleName(AitTheme.BTN_EXIT);
		this.btn_salir.setIcon(FontAwesome.UNDO);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		buttonContent.addComponent(this.btn_salir);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel gridPanel = new Panel("Activos Fijos Disponibles : Seleccione los Activos");
		gridPanel.setWidth("100%");
		gridPanel.setIcon(FontAwesome.TABLE);
		gridPanel.setStyleName(AitTheme.PANEL_GRID);
		gridPanel.setContent(this.frm_solicitud.getgrid_solicitud());
		formContent.setMargin(true);
		formContent.addComponent(frm_solicitud);
		formContent.addComponent(gridPanel);
		Panel pnMorivo = new Panel("Motivo");
		pnMorivo.setWidth("100%");
		pnMorivo.setIcon(FontAwesome.SAVE);
		pnMorivo.setStyleName(AitTheme.PANEL_FORM);
		GridLayout gridl_motivo = new GridLayout(2, 1);
		gridl_motivo.setSizeFull();
		gridl_motivo.setColumnExpandRatio(0, 0);
		gridl_motivo.setMargin(true);
		gridl_motivo.addComponent(this.frm_solicitud.txt_observaciones, 0,0);
		pnMorivo.setContent(gridl_motivo);
		formContent.addComponent(pnMorivo);
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
			Label lbError = new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_agregar) {
			if (this.frm_solicitud.validate()) {
				if (movimientoimpl.addMovimientoTransferencia(this.frm_solicitud.getData())>0) {
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
		if (event.getButton() == this.btn_limpiar) {
			frm_solicitud.update();
			// this.frm_solicitud.updateId();
			
		}
		if (event.getButton() == this.btn_salir) {
			frm_solicitud.update();
		}
	}
	
}