package ait.sistemas.proyecto.activos.view.inve.tomainv;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.InventarioImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.HomeView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VTomaInvP extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_guardar = new Button("GENERAR SOLICITUD");
	private Button btn_salir = new Button("SALIR");
	
	private CssLayout hl_errores = new CssLayout();
	private FormTomaInv frm_tomainv = new FormTomaInv(this);
	
	private final InventarioImpl inventarioimpl = new InventarioImpl();
	
	private List<BarMessage> msg = new ArrayList<BarMessage>();
	
	public VTomaInvP() {
		
		this.btn_guardar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
		msg.add(new BarMessage("Formulario", Messages.KEY_ENTER));
		buildMessages(msg);
	}
	
	private Component buildFormContent() {
		VerticalLayout vl_form = new VerticalLayout();
		Panel pn_form = new Panel("TOMA DE INVENTARIO FISICO  :  " + Messages.REQUIED_FIELDS);
		pn_form.setIcon(FontAwesome.EDIT);
		pn_form.setStyleName(AitTheme.PANEL_FORM);
		
		pn_form.setContent(this.frm_tomainv);
		vl_form.setMargin(true);
		vl_form.addComponent(pn_form);
		
		Panel pn_grid = new Panel("ACTIVOS INVENTARIADOS");
		pn_grid.setIcon(FontAwesome.TABLE);
		pn_grid.setStyleName(AitTheme.PANEL_GRID);
		pn_grid.setContent(this.frm_tomainv.getGrid());
		vl_form.addComponent(pn_grid);
		return vl_form;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Inventario>>"));
		nav.addComponent(new Label("<strong>Toma de Inventario Fisico</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
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
	
	public void buildMessages(List<BarMessage> mensages) {
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
			if (this.frm_tomainv.validate()) {
				try {
					if (inventarioimpl.add(frm_tomainv.getData()) > 0) {
						Notification.show(Messages.SUCCESS_MESSAGE);
						frm_tomainv.clean();
					} else {
						Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
				buildMessages(msg);
			buildMessages(this.frm_tomainv.getMensajes());
			this.frm_tomainv.clearMessages();
		}
		if (event.getButton() == this.btn_salir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
