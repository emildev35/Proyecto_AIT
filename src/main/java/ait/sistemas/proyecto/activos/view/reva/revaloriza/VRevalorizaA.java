package ait.sistemas.proyecto.activos.view.reva.revaloriza;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
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
import com.vaadin.ui.Alignment;
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

public class VRevalorizaA extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private FormRevaloriza frm_solicitud = new FormRevaloriza(this);
	private CssLayout hl_errores = new CssLayout();
	private Button btn_salir = new Button("Salir");
	private Button btn_guardar = new Button("Agregar");
	
	private MovimientoImpl movimientoimpl = new MovimientoImpl();
	private List<BarMessage> msg = new ArrayList<BarMessage>();
	
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	public VRevalorizaA() {
		this.btn_guardar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
		msg.add(new BarMessage("Formulario", Messages.KEY_ENTER));
		buildMessages(msg);
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		GridLayout btn_grid = new GridLayout(2, 1);
		btn_grid.setResponsive(true);
		btn_grid.setSizeFull();
		this.btn_guardar.setStyleName(AitTheme.BTN_SUBMIT);
		btn_grid.addComponent(this.btn_guardar);
		btn_grid.setComponentAlignment(btn_guardar, Alignment.TOP_CENTER);
		btn_guardar.setIcon(FontAwesome.SAVE);
		this.btn_salir.setStyleName(AitTheme.BTN_EXIT);
		buttonContent.addStyleName("ait-buttons");
		btn_grid.addComponent(this.btn_salir);
		btn_salir.setIcon(FontAwesome.UNDO);
		btn_grid.setComponentAlignment(btn_salir, Alignment.TOP_LEFT);
		buttonContent.addComponent(btn_grid);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		formContent.setMargin(true);
		formContent.addComponent(frm_solicitud);
		Panel pn_grid = new Panel("Activos Fijos Revalorizados");
		pn_grid.setIcon(FontAwesome.TABLE);
		pn_grid.setStyleName(AitTheme.PANEL_GRID);
		pn_grid.setContent(this.frm_solicitud.getGrid());
		formContent.addComponent(pn_grid);
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
	
	void buildMessages(List<BarMessage> mensages) {
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
		if (event.getButton() == this.btn_guardar) {
			if (this.frm_solicitud.validate()) {
				try {
					if (movimientoimpl.addMovimientoReva(this.frm_solicitud.getData()) > 0) {
						this.frm_solicitud.clear();
						Notification.show(Messages.SUCCESS_MESSAGE);
					} else {
						Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(msg);
			buildMessages(this.frm_solicitud.getMensajes());
			this.frm_solicitud.clearMessages();
		}
		if (event.getButton() == this.btn_salir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
			
		}
	}
	
}