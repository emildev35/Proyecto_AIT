package ait.sistemas.proyecto.seguridad.view.usua.usuario;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.UsuarioGridModel;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

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

public class VUsuarioB extends VerticalLayout implements View, ClickListener,
		SelectionListener, org.vaadin.dialogs.ConfirmDialog.Listener {

	private static final long serialVersionUID = 1L;

	private Button btn_submit = new Button("Eliminar");
	private Button btn_limpiar = new Button("Limpiar");
	private CssLayout hl_errores = new CssLayout();
	private FormUsuario frmUsuario;
	private UsuarioImpl usuarioimpl = new UsuarioImpl();
	private GridUsuario grid_usuario = new GridUsuario();

	public VUsuarioB() {
		this.frmUsuario = new FormUsuario();
		this.frmUsuario.cbPersonal.setEnabled(false);
		this.frmUsuario.txtCI.setEnabled(false);
		this.frmUsuario.txtIdenticadorUsuario.setEnabled(false);

		this.grid_usuario.addSelectionListener(this);

		addComponent(buildNavBar());
		addComponent(builFormContent());
		addComponent(buildButtonBar());
	}

	private Component builFormContent() {

		final VerticalLayout vlfrmContent = new VerticalLayout();
		vlfrmContent.setMargin(true);
		Panel pnfrmOpcionPerfil = new Panel(
				"Formulario de Elminacion de Usuarios");
		pnfrmOpcionPerfil.setContent(this.frmUsuario);

		Panel pngridOpcionPerfil = new Panel("Grid de Usuarios");
		pngridOpcionPerfil.setContent(this.grid_usuario);
		vlfrmContent.addComponent(pngridOpcionPerfil);
		vlfrmContent.addComponent(pnfrmOpcionPerfil);
		return vlfrmContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Seguridad » "));
		nav.addComponent(new Label("Usuarios » "));
		nav.addComponent(new Label("<strong>Eliminar Usuarios</strong>",
				ContentMode.HTML));
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

	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);

		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(barMessage.getComponetName() + ":"
					+ barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public void eliminar() {
		try {
			this.usuarioimpl.delete(this.frmUsuario.getDataUsuario()
					.getUSU_Id_Usuario());
			Notification.show(Messages.SUCCESS_MESSAGE);
			this.grid_usuario.update();
			this.frmUsuario.cbPersonal.removeAllItems();
			this.frmUsuario.clear();
		} catch (Exception e) {
			Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
		}

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {
			if (this.frmUsuario.cbPersonal.getValue() != null) {
				ConfirmDialog
						.show(getUI(),
								("Eliminar " + this.frmUsuario.cbPersonal
										.getValue().toString()),
								(Messages
										.CONFIRM_DIALOG_DELETE_MESSAGE(this.frmUsuario.cbPersonal
												.getValue().toString())), "Si",
								"No", this);

				buildMessages(this.frmUsuario.getMessages());
			}
		}
		if (event.getButton() == this.btn_limpiar) {
			this.frmUsuario.clear();
		}

	}

	@Override
	public void select(SelectionEvent event) {
		if (this.grid_usuario.getSelectedRow() != null) {
			this.frmUsuario.setDataUsuario((UsuarioGridModel) this.grid_usuario
					.getSelectedRow());
		}
	}

	@Override
	public void onClose(ConfirmDialog dialog) {
		if (dialog.isConfirmed()) {
			eliminar();
		}
	}
}
