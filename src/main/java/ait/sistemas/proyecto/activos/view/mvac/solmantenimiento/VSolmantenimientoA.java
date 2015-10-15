package ait.sistemas.proyecto.activos.view.mvac.solmantenimiento;

import java.sql.SQLException;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.MantenimientoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

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

public class VSolmantenimientoA extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private FormSolmantenimiento frm_mantenimiento = new FormSolmantenimiento();
	Button btn_generar_solicitud = new Button("Generar Solicitud");
	Button btn_imprimir = new Button("Imprimir");
	Button btn_salir = new Button("Salir");
	CssLayout hl_errores = new CssLayout();
	private  MantenimientoImpl mantenimientoimpl = new MantenimientoImpl();
	
	public VSolmantenimientoA() {
		this.btn_imprimir.addClickListener(this);
		this.btn_generar_solicitud.addClickListener(this);
		this.btn_salir.addClickListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_generar_solicitud.setStyleName(AitTheme.BTN_SUBMIT);
		buttonContent.addComponent(this.btn_generar_solicitud);
		this.btn_imprimir.setStyleName(AitTheme.BTN_PRINT);
		buttonContent.addComponent(this.btn_imprimir);
		this.btn_salir.setStyleName(AitTheme.BTN_EXIT);
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_salir);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel gridPanel = new Panel("Activos Fijos Asignados : Selecciona los Activos");
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Activos Asignados");
		gridPanel.setContent(frm_mantenimiento.getgrid_solicitud());
		formContent.setMargin(true);
		formContent.addComponent(frm_mantenimiento);
		formContent.addComponent(gridPanel);
		Responsive.makeResponsive(formContent);
		return formContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Movimiento de Activos>>"));
		nav.addComponent(new Label("Solicitud Mantenimiento>>"));
		nav.addComponent(new Label("<strong>Agregar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
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
		if (event.getButton() == this.btn_imprimir) {
			frm_mantenimiento.update();
		}
		if (event.getButton() == this.btn_generar_solicitud) {
			if (this.frm_mantenimiento.validate()) {
				 Movimiento data = this.frm_mantenimiento.getData();
				try {
					if (mantenimientoimpl.addMantenimiento(data)) {
						this.frm_mantenimiento.clear();
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
			buildMessages(this.frm_mantenimiento.getMensajes());
			this.frm_mantenimiento.clearMessages();
			frm_mantenimiento.update();
		}
		if (event.getButton() == this.btn_salir) {
			frm_mantenimiento.update();
		}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}
	
}
