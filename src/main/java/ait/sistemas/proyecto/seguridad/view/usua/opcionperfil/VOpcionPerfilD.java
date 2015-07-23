package ait.sistemas.proyecto.seguridad.view.usua.opcionperfil;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.PermisoPerfil;
import ait.sistemas.proyecto.seguridad.data.service.Impl.PerfilImpl;

import com.vaadin.cdi.CDIView;
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

@CDIView(value = VOpcionPerfilD.ID)
public class VOpcionPerfilD extends VerticalLayout implements View,
		ClickListener, org.vaadin.dialogs.ConfirmDialog.Listener {

	private static final long serialVersionUID = 1L;

	public static final String ID = "/seg/usua/opcionperfil/d";

	private FormEliminacion frmOpcionPeril;
	private Button btn_submit;
	private Button btn_limpiar;
	private CssLayout hl_errores = new CssLayout();
	private final PerfilImpl perfilimpl = new PerfilImpl();

	public VOpcionPerfilD() {
		this.frmOpcionPeril = new FormEliminacion();
		this.btn_submit = new Button("Eliminar");
		this.btn_limpiar = new Button("Limpiar");

		addComponent(buildNavBar());
		addComponent(builFormContent());
		addComponent(buildButtonBar());
	}

	private Component builFormContent() {
		final VerticalLayout vlfrmContent = new VerticalLayout();
		vlfrmContent.setMargin(true);

		Panel pnfrmOpcionPerfil = new Panel(
				"Formulario de Eliminacion de Opcion por Perfil");
		pnfrmOpcionPerfil.setContent(this.frmOpcionPeril);

		Panel pngridOpcionPerfil = new Panel("Grid de Opciones");
		pngridOpcionPerfil.setContent(this.frmOpcionPeril.gridOpcionPerfil);

		vlfrmContent.addComponent(pnfrmOpcionPerfil);
		vlfrmContent.addComponent(pngridOpcionPerfil);
		return vlfrmContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Seguridad » "));
		nav.addComponent(new Label("Usuarios » "));
		nav.addComponent(new Label("Perfiles » "));
		nav.addComponent(new Label("<strong>Eliminar Opciones</strong>",
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

	@Override
	public void enter(ViewChangeEvent event) {

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

	public void eliminar() {
		int result = 0;
		Notification.show(String.valueOf(this.frmOpcionPeril.getListMenu().size()));
		result = this.perfilimpl.elminarPermisos(this.frmOpcionPeril.getListMenu());
		this.frmOpcionPeril.update();
		if (result > 0) {
			Notification.show(Messages.SUCCESS_MESSAGE);
		} else {
			Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
		}

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (this.frmOpcionPeril.validate()) {
			if (event.getButton() == this.btn_submit) {
				List<PermisoPerfil> data_delete = this.frmOpcionPeril
						.getListMenu();
				ConfirmDialog.show(getUI(), "Eliminar", Messages
						.MANY_CONFIRM_DELETE_MESSAGE(data_delete.size(),
								"Permisos"), "Si", "No", this);
			}
		}
		buildMessages(this.frmOpcionPeril.getMensajes());
	}

	@Override
	public void onClose(ConfirmDialog arg0) {
		// TODO Auto-generated method stub
		if (arg0.isConfirmed()) {
			eliminar();
		}
	}
}