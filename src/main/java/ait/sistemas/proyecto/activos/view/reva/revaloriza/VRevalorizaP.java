package ait.sistemas.proyecto.activos.view.reva.revaloriza;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
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

public class VRevalorizaP extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;

	private Button btn_revalorizar = new Button("Revalorizar");
	private Button btn_salir = new Button("Salir");
	private FormRevalorizaP frm_revaloriza_p = new FormRevalorizaP();
	private final ActivoImpl activo_impl = new ActivoImpl();
	private CssLayout hl_errores = new CssLayout();
	private List<BarMessage> msg = new ArrayList<BarMessage>();
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	public VRevalorizaP() {

		this.btn_revalorizar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
		msg.add(new BarMessage("Formulario", Messages.REQUIED_FIELDS));
		buildMessages(msg);
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		GridLayout btn_grid = new GridLayout(2, 1);
		btn_grid.setResponsive(true);
		btn_grid.setSizeFull();
		this.btn_revalorizar.setStyleName(AitTheme.BTN_SUBMIT);
		btn_grid.addComponent(this.btn_revalorizar);
		btn_grid.setComponentAlignment(btn_revalorizar, Alignment.TOP_CENTER);
		btn_revalorizar.setIcon(FontAwesome.SAVE);
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
		 Panel frmPanel = new Panel("Formulario de Revalorizacion de Activos ");
		 frmPanel.setIcon(FontAwesome.SAVE);
		 frmPanel.setStyleName(AitTheme.PANEL_FORM);
		 frmPanel.setWidth("40%");
		 frmPanel.setCaption("Formulario de Impresion");
		 GridLayout gridl_res = new GridLayout(2, 1);
			gridl_res.setSizeFull();
			gridl_res.setColumnExpandRatio(0, 0);
			gridl_res.addComponent(this.frm_revaloriza_p.dt_fecha, 0,0);
			gridl_res.addComponent(this.frm_revaloriza_p.txt_no_resolucion, 1,0);
			frmPanel.setContent(gridl_res);
		 formContent.addComponent(frmPanel);
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
		if (event.getButton() == this.btn_revalorizar) {
			this.frm_revaloriza_p.clearMessages();
			if (activo_impl.getResol(frm_revaloriza_p.txt_no_resolucion.getValue()) == 1){
			if (this.frm_revaloriza_p.validate()) {
				if (this.activo_impl.RevalorizaActivos(this.frm_revaloriza_p.getData()) > 0){
				this.frm_revaloriza_p.update();
				this.frm_revaloriza_p.clearMessages();
				Notification.show(Messages.SUCCESS_MESSAGE);
				} else {
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
			
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			}
			else{
				Notification.show(Messages.NO_EXISTE_RESOL, Type.ERROR_MESSAGE);
//				msg.add(new BarMessage("Formulario", Messages.NO_EXISTE_RESOL));
//				buildMessages(msg);
			}
//			buildMessages(this.frm_revaloriza_p.getMessage());
			
		}else{
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}
}
