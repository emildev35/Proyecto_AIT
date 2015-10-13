package ait.sistemas.proyecto.seguridad.view.estr.opcion;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.common.view.HomeView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VOpcionB extends VerticalLayout implements View, ValueChangeListener, ClickListener, SelectionListener,
		org.vaadin.dialogs.ConfirmDialog.Listener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_submit;
	private Button btn_limpiar;
	private FormOpcion frm_opcion;
	private GridOpcion grid_opcion;
	private CssLayout hl_errores;
	private MenuImpl menu;
	
	private final Arbol_menus menuNav = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	public VOpcionB() {
		
		this.frm_opcion = new FormOpcion();
		this.menu = new MenuImpl();
		this.btn_submit = new Button("Eliminar");
		this.btn_limpiar = new Button("Salir");
		this.hl_errores = new CssLayout();
		this.grid_opcion = new GridOpcion();
		this.grid_opcion.addSelectionListener(this);
		
		this.frm_opcion.cb_icons.setEnabled(false);
		this.frm_opcion.txt_nombre_menu.setEnabled(false);
		this.frm_opcion.txt_nombre_programa.setEnabled(false);
		
		this.frm_opcion.cbSubMenus.addValueChangeListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Formulario Opcion");
		frmPanel.setStyleName(AitTheme.PANEL_FORM);
		frmPanel.setIcon(FontAwesome.EDIT);
		this.frm_opcion.init();
		frmPanel.setContent(this.frm_opcion);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Panel gridPanel = new Panel();
		gridPanel.setStyleName(AitTheme.PANEL_GRID);
		gridPanel.setIcon(FontAwesome.TABLE);
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Grid Opcion");
		gridPanel.setContent(this.grid_opcion);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		formContent.addComponent(gridPanel);
		Responsive.makeResponsive(formContent);
		return formContent;
		
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName(AitTheme.NAV_BAR);
		nav.addComponent(new Label(AitView.getNavText(menuNav), ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_submit);
		this.btn_submit.addStyleName(AitTheme.BTN_SUBMIT);
		this.btn_submit.setIcon(FontAwesome.TRASH_O);
		this.btn_submit.addClickListener(this);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		buttonContent.addComponent(this.btn_limpiar);
		this.btn_limpiar.addStyleName(AitTheme.BTN_EXIT);
		this.btn_limpiar.setIcon(FontAwesome.UNDO);
		this.btn_limpiar.addClickListener(this);
		Responsive.makeResponsive(buttonContent);
		return buttonContent;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		
		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(new Label(barMessage.getComponetName() + " : " + barMessage.getErrorName()));
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {
			ConfirmDialog.show(getUI(), ("Eliminar " + this.frm_opcion.getData().getAME_Nombre()),
					("Esta Uds Seguro que Desea Eliminar el Sub-Sistema" + this.frm_opcion.getData().getAME_Nombre()), "Si",
					"No", this);
		}
		if (event.getButton() == this.btn_limpiar) {
			this.frm_opcion.clearMessages();
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}
	
	@Override
	public void select(SelectionEvent event) {
		if ((Arbol_menus) this.grid_opcion.getSelectedRow() != null) {
			this.frm_opcion.setData((Arbol_menus) this.grid_opcion.getSelectedRow());
		}
	}
	
	@Override
	public void onClose(ConfirmDialog dialog) {
		if (dialog.isConfirmed()) {
			eliminar();
		} else {
			
		}
	}
	
	private void eliminar() {
		buildMessages(menu.delete(this.frm_opcion.getData().getAME_Id_Identificador()));
		this.frm_opcion.update();
		this.grid_opcion.update(frm_opcion.getSubMenu());
		this.frm_opcion.clearMessages();
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		grid_opcion.update(frm_opcion.getSubMenu());
	}
	
}