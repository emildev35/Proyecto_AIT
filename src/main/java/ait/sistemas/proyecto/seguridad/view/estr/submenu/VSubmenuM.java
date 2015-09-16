package ait.sistemas.proyecto.seguridad.view.estr.submenu;

import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
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

public class VSubmenuM extends VerticalLayout implements View, ValueChangeListener, ClickListener, SelectionListener{

	private static final long serialVersionUID = 1L;
	
	private Button btn_submit;
	private Button btn_limpiar;
	private FormSubMenu frm_subMenu;
	private GridSubMenu grid_subMenu;
	private CssLayout hl_errores;
	private MenuImpl menu;
	public VSubmenuM() {
		
		this.frm_subMenu = new FormSubMenu();
		this.menu = new MenuImpl();
		this.btn_submit = new Button("Guardar");
		this.btn_limpiar = new Button("Limpiar");
		this.hl_errores = new CssLayout();
		this.grid_subMenu = new GridSubMenu();
		this.grid_subMenu.addSelectionListener(this);
		
		this.frm_subMenu.cbMenus.addValueChangeListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true	);
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Formulario Menu");
		frmPanel.setContent(this.frm_subMenu);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("GRid Menu");
		gridPanel.setContent(this.grid_subMenu);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		formContent.addComponent(gridPanel);
		Responsive.makeResponsive(formContent);
		return formContent;
		
	}
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Seguridad » "));
		nav.addComponent(new Label("Estructura del Sistema » "));
		nav.addComponent(new Label("Sub-Menu » "));
		nav.addComponent(new Label("<strong>Modificar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_submit);
		this.btn_submit.addStyleName("ait-buttons-btn");
		this.btn_submit.addClickListener(this);
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
		this.btn_limpiar.addStyleName("ait-buttons-btn");
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
			Label lbError = new Label(new Label(barMessage.getComponetName()+":"+barMessage.getErrorName()));
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
	
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {
			if(this.frm_subMenu.validate()){
				this.menu.updatesubmenu(this.frm_subMenu.getData());
				this.frm_subMenu.update();
				this.grid_subMenu.update(frm_subMenu.getMenu());
				Notification.show(Messages.SUCCESS_MESSAGE);
			}else{
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_subMenu.getMensajes());
			this.frm_subMenu.clearMessages();
		}
		if (event.getButton() == this.btn_limpiar) {
			
		}	
	}
	@Override
	public void select(SelectionEvent event) {
		if(this.grid_subMenu.getSelectedRow()!=null){
		this.frm_subMenu.setData((Arbol_menus)this.grid_subMenu.getSelectedRow());
		}
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
		grid_subMenu.update(frm_subMenu.getMenu());
	}

}