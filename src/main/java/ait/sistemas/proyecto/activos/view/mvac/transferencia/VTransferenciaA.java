package ait.sistemas.proyecto.activos.view.mvac.transferencia;

import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.ActasImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

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

public class VTransferenciaA extends VerticalLayout implements View, ClickListener,
		SelectionListener {

	private static final long serialVersionUID = 1L;

	private FormTransferencia frm_transferencia;
	private CssLayout hl_errores;
	private Button btn_asignacion;
	private Button btn_imprimir;
	private GridSolTransferencia grid_transferencia;
	private GridDetalle grid_Detalle = new  GridDetalle();
	private ActasImpl acta_impl = new ActasImpl();
	private Movimiento data;

	public VTransferenciaA() {

		this.frm_transferencia = new FormTransferencia();
		this.btn_imprimir = new Button("Imprimir");
		this.btn_asignacion = new Button("Generar Transferencia");
		this.btn_asignacion.addClickListener(this);
		this.btn_imprimir.addClickListener(this);
		this.grid_transferencia = new GridSolTransferencia();
		this.grid_transferencia.addSelectionListener(this);
		this.hl_errores = new CssLayout();

		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}

	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();
		
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Solicitudes Pendientes de Transferencia");
		gridPanel.setContent(this.grid_transferencia);
	//	formContent.setMargin(true);
		Panel grid2Panel = new Panel();
		grid2Panel.setWidth("100%");
		grid2Panel.setCaption("Detalle de Activos");
		grid2Panel.setContent(this.grid_Detalle);
		//formContent.setMargin(true);
		
		formContent.addComponent(gridPanel);
		formContent.addComponent(this.frm_transferencia);
		formContent.addComponent(grid2Panel);
		Responsive.makeResponsive(formContent);
		return formContent;

	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos >>"));
		nav.addComponent(new Label("Movimiento de Activos >>"));
		nav.addComponent(new Label("Transferencia de Activos Fijos >>"));
		nav.addComponent(new Label("<strong>Agregar</strong>",ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_asignacion.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_asignacion);
		this.btn_imprimir.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_imprimir);
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
			Label lbError = new Label(barMessage.getComponetName() + ":"+ barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}

	}

	@Override
	public void select(SelectionEvent event) {

		if ((Movimiento) this.grid_transferencia.getSelectedRow() != null) {
			this.frm_transferencia.setData((Movimiento) this.grid_transferencia.getSelectedRow());
			
			data =(Movimiento)this.grid_transferencia.getSelectedRow();
			grid_Detalle.update(data.getNro_documento(),data.getId_dependencia(), data.getTipo_movimiento());
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_asignacion) {
			if (this.frm_transferencia.validate()) {
				this.acta_impl.addActaTransferencia(this.frm_transferencia.getData());
				this.grid_Detalle.vaciar();
				this.frm_transferencia.update();
				this.frm_transferencia.buidId();
				this.grid_transferencia.update();
				Notification.show(Messages.SUCCESS_MESSAGE);
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE,
						Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_transferencia.getMensajes());
			this.frm_transferencia.clearMessages();
		}
		if (event.getButton() == this.btn_imprimir) {
		}
	}

}
