package ait.sistemas.proyecto.activos.view.rrhh.dependencia;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
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

@CDIView(value = VDependenciaB.URL)
public class VDependenciaB extends VerticalLayout implements View,
		SelectionListener, ClickListener,
		org.vaadin.dialogs.ConfirmDialog.Listener {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/act/rrhh/dependencia/b";

	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private GridDependencia grid_dependencia;
	private Button btn_limpiar;
	private Button btn_eliminar;
	private FormDependencia frm_dependencia;
	private CssLayout hl_errores;

	final PropertysetItem pitmDependencia = new PropertysetItem();

	public VDependenciaB() {

		this.frm_dependencia = new FormDependencia();
		this.btn_limpiar = new Button("Limpiar");
		this.btn_eliminar = new Button("Eliminar");
		this.btn_eliminar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		this.grid_dependencia = new GridDependencia();
		this.hl_errores = new CssLayout();

		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		buildGrid();
		Responsive.makeResponsive(this);
	}

	private void buildGrid() {
		this.grid_dependencia.addSelectionListener(this);
	}

	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();
		// formContent.setSpacing(true );

		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Datos a eliminar");
		frmPanel.setContent(this.frm_dependencia);
		this.frm_dependencia.enabled();
		// formContent.setMargin(true);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Dependencias Registradas");
		gridPanel.setContent(this.grid_dependencia);
		// formContent.setMargin(true);
		formContent.addComponent(gridPanel);
		formContent.addComponent(frmPanel);

		this.frm_dependencia.update();
		Responsive.makeResponsive(formContent);
		return formContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Recursos Humanos » "));
		nav.addComponent(new Label("Dependencia » "));
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

		if ((Dependencia) this.grid_dependencia.getSelectedRow() != null) {
			this.frm_dependencia.setData((Dependencia) this.grid_dependencia
					.getSelectedRow());
		}
	}

	private void eliminar() {
		try {
			this.dependencia_impl.deletes(this.frm_dependencia.getData()
					.getDEP_Dependencia());
			this.frm_dependencia.update();
			this.grid_dependencia.update();
			Notification.show(Messages.SUCCESS_MESSAGE);
		} catch (Exception e) {
			Notification.show(Messages.FOREIGN_RELATION_ERROR(frm_dependencia.txt_nombre_dependencia
									.getValue()), Type.ERROR_MESSAGE);
		}
		buildMessages(this.frm_dependencia.getMensajes());
		this.frm_dependencia.clearMessages();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_eliminar) {

			ConfirmDialog.show(getUI(), "", Messages.CONFIRM_DELETE_MESSAGE,
					"SI", "NO", this);
		}
		if (event.getButton() == this.btn_limpiar) {
			frm_dependencia.update();
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
		// TODO Auto-generated method stub
		if (dialog.isConfirmed()) {
			eliminar();
		} else {

		}
	}

}
