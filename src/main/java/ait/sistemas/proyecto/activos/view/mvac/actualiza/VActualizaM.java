package ait.sistemas.proyecto.activos.view.mvac.actualiza;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.session.ActivoSession;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.common.view.HomeView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
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

/**
 * Vista que contiene la pantalla para la seleccion del un Activo para su
 * posterior modificacion mediante el almacenamiento en la Session con el con el
 * nombre de activo
 * 
 * @author franzemil
 *
 */
public class VActualizaM extends VerticalLayout implements View, ClickListener, SelectionListener, TextChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private FormSeleccion frm_seleccion;
	private CssLayout hl_errores;
	private Button btn_actualizar = new Button("Actualizar");
	private Button btn_salir = new Button("Salir");
	
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	private List<BarMessage> msgs = new ArrayList<BarMessage>();
	
	public VActualizaM() {
		
		this.frm_seleccion = new FormSeleccion();
		
		this.btn_actualizar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		
		this.hl_errores = new CssLayout();
		
		this.frm_seleccion.getGrid().addSelectionListener(this);
		this.frm_seleccion.getCodigo().addTextChangeListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		
		msgs.add(new BarMessage("Formulario", "Seleccion un Grupo o Digite el Codigo del Activo"));
		buildMessages(msgs);
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		
		formContent.addComponent(this.frm_seleccion);
		return formContent;
		
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label(AitView.getNavText(menu)));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		
		this.btn_actualizar.setStyleName(AitTheme.BTN_SUBMIT);
		btn_actualizar.setIcon(FontAwesome.EDIT);
		buttonContent.addComponent(this.btn_actualizar);
		
		this.btn_salir.setStyleName(AitTheme.BTN_EXIT);
		btn_salir.setIcon(FontAwesome.UNDO);
		buttonContent.addComponent(this.btn_salir);
		
		Responsive.makeResponsive(buttonContent);
		buttonContent.setStyleName(AitTheme.BUTTONS_BAR);
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
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_actualizar) {
			if (this.frm_seleccion.validate()) {
				ActivoSession activoSession = this.frm_seleccion.getActivo();
				if (activoSession != null) {
					UI.getCurrent().getSession().setAttribute("activo", activoSession);
					UI.getCurrent().getNavigator().navigateTo(VActualizaTabM.ID);
				}
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_seleccion.getMensajes());
			this.frm_seleccion.clearMessages();
		}
		if (event.getButton() == this.btn_salir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}
	
	/**
	 * Verificando si se ha seleccionado un objeto del grid para mostrar el
	 * mensaje de ayuda
	 */
	@Override
	public void select(SelectionEvent event) {
		msgs = new ArrayList<BarMessage>();
		msgs.add(new BarMessage("Formulario", "Presione el Boton de Actualizar para Modificar los Datos del Activo Seleccionado"));
		buildMessages(msgs);
	}
	
	/**
	 * Verificando si se ha modificado el Campo de Codigo del Formulario para
	 * mostar el mensaje de ayuda
	 */
	@Override
	public void textChange(TextChangeEvent event) {
		msgs = new ArrayList<BarMessage>();
		msgs.add(new BarMessage("Formulario", "Presione el Boton de Actualizar para Modificar los Datos del Activo Seleccionado"));
		buildMessages(msgs);
	}
	
}
