package ait.sistemas.proyecto.activos.view.mvac.devolucion;

import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.ActasImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.cdi.CDIView;
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

@CDIView(value = VDevolucionA.ID)
public class VDevolucionA extends VerticalLayout implements View, ClickListener,
		SelectionListener {

	private static final long serialVersionUID = 1L;
	public static final String ID = "/act/mvac/devolucion/a";

	private FormDevolucion frm_asignacion;
	private CssLayout hl_errores;
	private Button btn_asignacion;
	private Button btn_imprimir;
	private GridSoldevolucion grid_asignacion;
	private GridDetalle grid_Detalle = new  GridDetalle();
	private ActasImpl acta_impl = new ActasImpl();
	private Movimiento data;

	public VDevolucionA() {

		this.frm_asignacion = new FormDevolucion();
		this.btn_imprimir = new Button("Imprimir");
		this.btn_asignacion = new Button("Generar Activos");
		this.btn_asignacion.addClickListener(this);
		this.btn_imprimir.addClickListener(this);
		this.grid_asignacion = new GridSoldevolucion();
		this.grid_asignacion.addSelectionListener(this);
		this.hl_errores = new CssLayout();

		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}

	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();
		
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Solicitudes de Devoluciones");
		gridPanel.setContent(this.grid_asignacion);
	//	formContent.setMargin(true);
		Panel grid2Panel = new Panel();
		grid2Panel.setWidth("100%");
		grid2Panel.setCaption("Detalle de activos para devolucion");
		grid2Panel.setContent(this.grid_Detalle);
		//formContent.setMargin(true);
		
		formContent.addComponent(gridPanel);
		formContent.addComponent(this.frm_asignacion);
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
		nav.addComponent(new Label("Devolucion >>"));
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

		if ((Movimiento) this.grid_asignacion.getSelectedRow() != null) {
			this.frm_asignacion.setData((Movimiento) 
			this.grid_asignacion.getSelectedRow());
			
			data =(Movimiento)this.grid_asignacion.getSelectedRow();
			grid_Detalle.update(data.getNro_documento(),data.getId_dependencia(),data.getTipo_movimiento());
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_asignacion) {
			if (this.frm_asignacion.validate()) {
				this.acta_impl.addActaDevolucion(this.frm_asignacion.getData());
				this.grid_Detalle.vaciar();
				this.frm_asignacion.update();
				this.frm_asignacion.buidId();
				this.grid_asignacion.update();
				Notification.show(Messages.SUCCESS_MESSAGE);
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE,
						Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_asignacion.getMensajes());
			this.frm_asignacion.clearMessages();
		}
		if (event.getButton() == this.btn_imprimir) {
		}
	}

}
