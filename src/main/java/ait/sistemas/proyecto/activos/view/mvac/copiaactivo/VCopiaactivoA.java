package ait.sistemas.proyecto.activos.view.mvac.copiaactivo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.common.view.HomeView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
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

public class VCopiaactivoA extends VerticalLayout implements View, ClickListener, SelectionListener {
	
	private static final long serialVersionUID = 1L;
	
	private FormCopiaActivo frm_copia = new FormCopiaActivo();
	private CssLayout hl_errores= new CssLayout();
	private Button btn_salir= new Button("Salir");
	private Button btn_copias = new Button("Generar Copias");
	private ActivoImpl activo_impl = new ActivoImpl();
	private final Arbol_menus menu = (Arbol_menus)UI.getCurrent().getSession().getAttribute("nav");
	private List<BarMessage> msg = new ArrayList<BarMessage>();
	
	private ActivoGrid activo;
	public VCopiaactivoA() {
		this.btn_copias.addClickListener(this);
		this.btn_salir.addClickListener(this);
		this.frm_copia.grid_solicitud.addSelectionListener(this);
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		buildGrid();
		Responsive.makeResponsive(this);
		msg.add(new BarMessage("Formulario", Messages.REQUIED_FIELDS));
		buildMessages(msg);
	}
	private void buildGrid() {
	}
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		GridLayout btn_grid = new GridLayout(2, 1);
		btn_grid.setResponsive(true);
		btn_grid.setSizeFull();
		this.btn_copias.setStyleName(AitTheme.BTN_SUBMIT);
		btn_grid.addComponent(this.btn_copias);
		btn_grid.setComponentAlignment(btn_copias, Alignment.TOP_CENTER);
		btn_copias.setIcon(FontAwesome.SAVE);
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
		formContent.addComponent(frm_copia);
		Panel gridPanel = new Panel("Seleccione el Activo del cual desea obtener copias");
//		gridPanel.setWidth("100%");
		gridPanel.setIcon(FontAwesome.TABLE);
		gridPanel.setStyleName(AitTheme.PANEL_GRID);
		gridPanel.setContent(this.frm_copia.getgrid_solicitud());
		formContent.addComponent(gridPanel);
		Panel Panelcopia = new Panel("Copias a Realizar del Activo");
		Panelcopia.setWidth("17%");
		Panelcopia.setIcon(FontAwesome.SAVE);
		Panelcopia.setStyleName(AitTheme.PANEL_FORM);
		 GridLayout gridl_res = new GridLayout(2, 1);
			gridl_res.setSizeFull();
			gridl_res.setColumnExpandRatio(0, 0);
			gridl_res.setMargin(true);
			gridl_res.addComponent(this.frm_copia.txt_no_copias, 0,0);
			Panelcopia.setContent(gridl_res);
		formContent.addComponent(Panelcopia);
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
		if (event.getButton() == this.btn_copias) {
			if (this.frm_copia.validate()) {
//				Object row = grid_activos.setSelectionMode(SelectionMode.SINGLE);
				
				if (activo_impl.addCopiaActivo(new BigDecimal(this.activo.getId_activo()),Integer.parseInt(frm_copia.txt_no_copias.getValue()))>0) {
					
					this.frm_copia.clear();
					
					Notification.show(Messages.SUCCESS_MESSAGE);
				}
				else{
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_copia.getMensajes());
			this.frm_copia.clearMessages();
		}
		else {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}

	@Override
	public void select(SelectionEvent event) {
		if ((ActivoGrid)frm_copia.grid_solicitud.getSelectedRow() != null) {
			this.activo  = (ActivoGrid)this.frm_copia.grid_solicitud.getSelectedRow();
		}
	}
	
}