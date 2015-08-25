package ait.sistemas.proyecto.activos.view.rrhh.inmueble;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.activos.data.model_rrhh.InmuebleModel;
import ait.sistemas.proyecto.activos.data.service.Impl.InmuebleImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.PropertysetItem;
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

@CDIView(value = VInmuebleB.URL)
public class VInmuebleB extends VerticalLayout implements View,
		SelectionListener, ClickListener,
		org.vaadin.dialogs.ConfirmDialog.Listener {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/act/rrhh/inmueble/b";

	private InmuebleImpl inmueble_impl = new InmuebleImpl();
	private GridInmueble grid_inmueble;
	private Button btn_limpiar;
	private Button btn_eliminar;
	private FormInmueble frm_inmueble;
	private CssLayout hl_errores;

	final PropertysetItem pitm_Inmueble = new PropertysetItem();

	public VInmuebleB() {

		this.frm_inmueble = new FormInmueble();
		this.btn_limpiar = new Button("Limpiar");
		this.btn_eliminar = new Button("Eliminar");
		this.btn_eliminar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		this.grid_inmueble = new GridInmueble();
		this.hl_errores = new CssLayout();

		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		buildGrid();
		Responsive.makeResponsive(this);
	}

	private void buildGrid() {
		this.grid_inmueble.addSelectionListener(this);
	}

	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();
		 formContent.setSpacing(true );

		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Datos a eliminar");
		frmPanel.setContent(this.frm_inmueble);
		this.frm_inmueble.enabled();
		 formContent.setMargin(true);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Inmueble Registrados");
		gridPanel.setContent(this.grid_inmueble);
		 formContent.setMargin(true);
		formContent.addComponent(gridPanel);
		formContent.addComponent(frmPanel);

		this.frm_inmueble.update();
		Responsive.makeResponsive(formContent);
		return formContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Recursos Humanos » "));
		nav.addComponent(new Label("Inmueble » "));
		nav.addComponent(new Label("<strong>Eliminar</strong>",
				ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_eliminar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_eliminar);
		this.btn_limpiar.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
		return buttonContent;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public void select(SelectionEvent event) {

		if ((InmuebleModel) this.grid_inmueble.getSelectedRow() != null) {
			this.frm_inmueble.setData((InmuebleModel) this.grid_inmueble
					.getSelectedRow());
		}
	}

	private void eliminar() {
		try {
			this.inmueble_impl.deletes(this.frm_inmueble.getData().getINM_Inmueble());
			this.frm_inmueble.update();
			this.grid_inmueble.update();
			Notification.show(Messages.SUCCESS_MESSAGE);
		} catch (Exception e) {
			Notification.show(Messages.FOREIGN_RELATION_ERROR(frm_inmueble.txt_nombre_inmueble
									.getValue()), Type.ERROR_MESSAGE);
		}
		buildMessages(this.frm_inmueble.getMensajes());
		this.frm_inmueble.clearMessages();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_eliminar) {

			ConfirmDialog.show(getUI(), "", Messages.CONFIRM_DELETE_MESSAGE,
					"SI", "NO", this);
		}
		if (event.getButton() == this.btn_limpiar) {
			frm_inmueble.update();
		}
	}

	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);

		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(new Label(barMessage.getComponetName()
					+ ":" + barMessage.getErrorName()));
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
