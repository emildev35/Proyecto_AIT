package ait.sistemas.proyecto.activos.view.para.partida;

import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Partidas_Presupuestaria;
import ait.sistemas.proyecto.activos.data.service.Impl.PartidaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

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
import com.vaadin.ui.VerticalLayout;

public class VPartidaM extends VerticalLayout implements View, ClickListener, SelectionListener {

	private static final long serialVersionUID = 1L;

	private FormPartida frm_partida;
	private CssLayout hl_errores;
	private Button btn_limpiar;
	private Button btn_modificar;
	private GridPartida grid_partida;
	private final PartidaImpl partida_impl = new PartidaImpl();

	public VPartidaM() {

		this.frm_partida = new FormPartida();
		this.btn_limpiar = new Button("Limpiar");
		this.btn_modificar = new Button("Modificar");
		this.btn_modificar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);

		this.grid_partida = new GridPartida();
		this.grid_partida.addSelectionListener(this);
		this.hl_errores = new CssLayout();

		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}

	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Datos a modificar");
		frmPanel.setContent(this.frm_partida);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Partidas Presupuestarias Registrados");
		gridPanel.setContent(this.grid_partida);
		formContent.setMargin(true);
		formContent.addComponent(gridPanel);
		formContent.addComponent(frmPanel);
		this.frm_partida.update();
		Responsive.makeResponsive(formContent);
		return formContent;

	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Parametros>>"));
		nav.addComponent(new Label("Partidas Presupuestarias>>"));
		nav.addComponent(new Label("<strong>Modificar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		btn_modificar.setStyleName(AitTheme.BTN_SUBMIT);
		btn_modificar.setIcon(FontAwesome.SAVE);
		buttonContent.addComponent(this.btn_modificar);
		btn_limpiar.setStyleName(AitTheme.BTN_EXIT);
		btn_limpiar.setIcon(FontAwesome.TRASH_O);
		buttonContent.addStyleName("ait-buttons");
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

		if ((Partidas_Presupuestaria) this.grid_partida.getSelectedRow() != null) {
			this.frm_partida.setData((Partidas_Presupuestaria) this.grid_partida.getSelectedRow());
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_modificar) {
			if (this.frm_partida.validate()) {
				this.partida_impl.update(this.frm_partida.getData());
				this.frm_partida.update();
				this.grid_partida.update();
				Notification.show(Messages.SUCCESS_MESSAGE);
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_partida.getMensajes());
			this.frm_partida.clearMessages();
		}
		if (event.getButton() == this.btn_limpiar) {
			this.frm_partida.update();
		}
	}

}
