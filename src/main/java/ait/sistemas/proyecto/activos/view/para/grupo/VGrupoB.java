package ait.sistemas.proyecto.activos.view.para.grupo;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

import com.vaadin.data.util.PropertysetItem;
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

public class VGrupoB extends VerticalLayout implements View, SelectionListener, ClickListener,
		org.vaadin.dialogs.ConfirmDialog.Listener {
	
	private static final long serialVersionUID = 1L;
	
	private GrupoImpl grupo_impl = new GrupoImpl();
	private GridGrupo grid_grupo;
	private Button btn_limpiar;
	private Button btn_eliminar;
	private FormGrupo frm_grupo;
	private CssLayout hl_errores;
	
	final PropertysetItem pitmUnidad = new PropertysetItem();
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	private List<BarMessage> msgs = new ArrayList<BarMessage>();
	
	public VGrupoB() {
		
		this.frm_grupo = new FormGrupo();
		this.btn_limpiar = new Button("Limpiar");
		this.btn_eliminar = new Button("Eliminar");
		this.btn_eliminar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		this.grid_grupo = new GridGrupo();
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		buildGrid();
		Responsive.makeResponsive(this);
	}
	
	private void buildGrid() {
		this.grid_grupo.addSelectionListener(this);
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Datos a eliminar");
		frmPanel.setContent(this.frm_grupo);
		frmPanel.setStyleName(AitTheme.PANEL_FORM);
		frmPanel.setIcon(FontAwesome.EDIT);
		this.frm_grupo.enabled();
		formContent.setMargin(true);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Grupos Contables registrados");
		gridPanel.setContent(this.grid_grupo);
		gridPanel.setStyleName(AitTheme.PANEL_GRID);
		gridPanel.setIcon(FontAwesome.TABLE);
		formContent.setMargin(true);
		formContent.addComponent(gridPanel);
		formContent.addComponent(frmPanel);
		
		this.frm_grupo.update();
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
		this.btn_eliminar.setStyleName(AitTheme.BTN_SUBMIT);
		btn_eliminar.setIcon(FontAwesome.TRASH_O);
		buttonContent.addComponent(this.btn_eliminar);
		btn_limpiar.addStyleName(AitTheme.BTN_EXIT);
		btn_limpiar.setIcon(FontAwesome.UNDO);
		buttonContent.addComponent(this.btn_limpiar);
		buttonContent.setStyleName(AitTheme.BUTTONS_BAR);
		return buttonContent;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	@Override
	public void select(SelectionEvent event) {
		
		if ((GruposContablesModel) this.grid_grupo.getSelectedRow() != null) {
			this.frm_grupo.setData((GruposContablesModel) this.grid_grupo.getSelectedRow());
			msgs = new ArrayList<BarMessage>();
			msgs.add(new BarMessage("Formulario", Messages.PRESS_BUTTON_DELETE));
			buildMessages(msgs);
		}
	}
	
	private void eliminar() {
		try {
			this.grupo_impl.delete(this.frm_grupo.getData().getGRC_Grupo_Contable());
			this.frm_grupo.update();
			this.grid_grupo.update();
			Notification.show(Messages.SUCCESS_MESSAGE);
		} catch (Exception e) {
			Notification.show(Messages.FOREIGN_RELATION_ERROR(frm_grupo.txt_nombre_grupo.getValue()), Type.ERROR_MESSAGE);
		}
		buildMessages(this.frm_grupo.getMensajes());
		this.frm_grupo.clearMessages();
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_eliminar) {
			
			ConfirmDialog.show(getUI(), "", Messages.CONFIRM_DELETE_MESSAGE, "SI", "NO", this);
		}
		if (event.getButton() == this.btn_limpiar) {
			frm_grupo.update();
		}
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
	public void onClose(ConfirmDialog dialog) {
		if (dialog.isConfirmed()) {
			eliminar();
		} else {
			
		}
	}
	
}
