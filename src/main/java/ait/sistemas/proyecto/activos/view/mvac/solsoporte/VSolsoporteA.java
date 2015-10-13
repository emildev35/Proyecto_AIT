package ait.sistemas.proyecto.activos.view.mvac.solsoporte;

import java.sql.SQLException;
import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.SoporteImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.HomeView;

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

public class VSolsoporteA extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_guardar = new Button("GENERAR SOLICITUD");
	private Button btn_salir = new Button("SALIR");
	
	private CssLayout hl_errores = new CssLayout();
	private FormSolsoporte frm_soporte = new FormSolsoporte();
	
	private final SoporteImpl soporteimpl = new SoporteImpl();
	
	public VSolsoporteA() {
		
		this.btn_guardar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
	}
	
	private Component buildFormContent() {
		
		return this.frm_soporte;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Mantenimiento>>"));
		nav.addComponent(new Label("<strong>Solicitud de Soporte</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_guardar.setStyleName(AitTheme.BTN_SUBMIT);
		this.btn_guardar.setIcon(FontAwesome.SAVE);
		buttonContent.addComponent(this.btn_guardar);
		this.btn_salir.setStyleName(AitTheme.BTN_EXIT);
		this.btn_salir.setIcon(FontAwesome.UNDO);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		buttonContent.addComponent(this.btn_salir);
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
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_guardar) {
			if (this.frm_soporte.validate()) {
				try {
					if (soporteimpl.add(frm_soporte.getData())>0) {
						Notification.show(Messages.SUCCESS_MESSAGE);
						frm_soporte.clean();
					}else{
						Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			buildMessages(this.frm_soporte.getMensajes());
			this.frm_soporte.clearMessages();
		}
		if (event.getButton() == this.btn_salir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
