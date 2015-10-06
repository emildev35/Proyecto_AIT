package ait.sistemas.proyecto.activos.view.reva.cierrege;

import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.CierreGestionImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.common.view.HomeView;
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
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VCierreGeP extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private FormCierreGe frm_Cierre;
	private CssLayout hl_errores;
	private Button btn_cierre;
	private Button btn_salir = new Button("Salir");
	private CierreGestionImpl cierre_gestion_impl = new CierreGestionImpl();

	
	private final Arbol_menus menu = (Arbol_menus)UI.getCurrent().getSession().getAttribute("nav");
	
	public VCierreGeP() {
		frm_Cierre = new FormCierreGe();
		this.btn_cierre = new Button("Cierre de Gestion");
		this.btn_cierre.addClickListener(this);
		
		this.btn_salir.addClickListener(this);
		
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_cierre.setStyleName(AitTheme.BTN_SUBMIT);
		btn_cierre.setIcon(FontAwesome.SAVE);
		btn_salir.setStyleName(AitTheme.BTN_EXIT);
		btn_salir.setIcon(FontAwesome.UNDO);
		buttonContent.addComponent(btn_cierre);
		buttonContent.addComponent(btn_salir);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		formContent.setMargin(true);
		formContent.addComponent(this.frm_Cierre);
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
			Label lbError = new Label(barMessage.getComponetName() + barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_cierre) {
			if (this.frm_Cierre.validate()) {
				this.cierre_gestion_impl.Cierre(this.frm_Cierre.getData());
				this.frm_Cierre.update();
				Notification.show(Messages.SUCCESS_MESSAGE);
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_Cierre.getMensajes());
			this.frm_Cierre.clearMessages();
		}else{
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}
	
}