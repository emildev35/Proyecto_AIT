package ait.sistemas.proyecto.activos.view.para.grupo;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VGrupoM extends VerticalLayout implements View, ClickListener, SelectionListener {
	
	private static final long serialVersionUID = 1L;
	
	private FormGrupo frm_grupo;
	private CssLayout hl_errores;
	private Button btn_limpiar;
	private Button btn_modificar;
	private GridGrupo grid_grupo;
	private final GrupoImpl grupo_impl = new GrupoImpl();
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	private List<BarMessage> msgs = new ArrayList<BarMessage>();
	
	public VGrupoM() {
		
		this.frm_grupo = new FormGrupo();
		this.btn_limpiar = new Button("Limpiar");
		this.btn_modificar = new Button("Modificar");
		this.btn_modificar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		this.frm_grupo.txt_id_grupo.setEnabled(false);
		this.grid_grupo = new GridGrupo();
		this.grid_grupo.addSelectionListener(this);
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel frmPanel = new Panel();
		frmPanel.setStyleName(AitTheme.PANEL_FORM);
		frmPanel.setIcon(FontAwesome.EDIT);
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Datos a modificar");
		frmPanel.setContent(this.frm_grupo);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Panel gridPanel = new Panel();
		gridPanel.setStyleName(AitTheme.PANEL_GRID);
		gridPanel.setIcon(FontAwesome.TABLE);
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Grupos Contables Registrados");
		gridPanel.setContent(this.grid_grupo);
		formContent.setMargin(true);
		formContent.addComponent(gridPanel);
		formContent.addComponent(frmPanel);
		Responsive.makeResponsive(formContent);
		return formContent;
		
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label(AitView.getNavText(menu), ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_modificar.setStyleName(AitTheme.BTN_SUBMIT);
		btn_modificar.setIcon(FontAwesome.EDIT);
		buttonContent.addComponent(this.btn_modificar);
		this.btn_limpiar.setStyleName("ait-buttons-btn");
		btn_limpiar.addStyleName(AitTheme.BTN_EXIT);
		btn_limpiar.setIcon(FontAwesome.UNDO);
		buttonContent.setStyleName(AitTheme.BUTTONS_BAR);
		buttonContent.addComponent(this.btn_limpiar);
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
			Label lbError = new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	}
	
	@Override
	public void select(SelectionEvent event) {
		
		if ((GruposContablesModel) this.grid_grupo.getSelectedRow() != null) {
			this.frm_grupo.setData((GruposContablesModel) this.grid_grupo.getSelectedRow());
			msgs = new ArrayList<BarMessage>();
			msgs.add(new BarMessage("Formulario", Messages.PRESS_BUTTON_UPDATE));
			buildMessages(msgs);
		}
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_modificar) {
			if (this.frm_grupo.validate()) {
				this.grupo_impl.update(this.frm_grupo.getData());
				this.frm_grupo.update();
				this.grid_grupo.update();
				Notification.show(Messages.SUCCESS_MESSAGE);
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_grupo.getMensajes());
			this.frm_grupo.clearMessages();
		}
		if (event.getButton() == this.btn_limpiar) {
			frm_grupo.update();
		}
	}
	
}
