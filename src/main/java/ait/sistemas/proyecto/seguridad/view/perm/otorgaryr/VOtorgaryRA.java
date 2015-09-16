package ait.sistemas.proyecto.seguridad.view.perm.otorgaryr;

import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

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

public class VOtorgaryRA extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;


	private FormOtorgar frm_otorgar;
	private Button btn_submit;
	private Button btn_limpiar;
	private CssLayout hl_errores = new CssLayout();
	private final UsuarioImpl usuarioimpl = new UsuarioImpl();

	public VOtorgaryRA() {
		this.frm_otorgar = new FormOtorgar();
		this.btn_submit = new Button("Guardar");
		this.btn_limpiar = new Button("Limpiar");

		addComponent(buildNavBar());
		addComponent(builFormContent());
		addComponent(buildButtonBar());
	}

	private Component builFormContent() {
		final VerticalLayout vlfrmContent = new VerticalLayout();
		vlfrmContent.setMargin(true);

		Panel pnfrmOpcionPerfil = new Panel("Formulario de Permisos");
		pnfrmOpcionPerfil.setContent(this.frm_otorgar);

		Panel pngridOpcionPerfil = new Panel("Grid de Opciones");
		pngridOpcionPerfil.setContent(this.frm_otorgar.grid_otorgar);

		vlfrmContent.addComponent(pnfrmOpcionPerfil);
		vlfrmContent.addComponent(pngridOpcionPerfil);
		return vlfrmContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Seguridad » "));
		nav.addComponent(new Label("Permisos » "));
		nav.addComponent(new Label("<strong>Otorgar y Revocar Permisos</strong>", ContentMode.HTML));
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

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {
			if (this.frm_otorgar.validate()) {
				java.sql.Date fechaRegistro = new java.sql.Date(
						new Date().getTime());
				try {
					usuarioimpl.otortgarPermisos(this.frm_otorgar.getUsuario(),
							this.frm_otorgar.getPermisos(),this.frm_otorgar.getIdPadre(), fechaRegistro);
				} catch (Exception e) {
					Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
				}
			}
			buildMessages(this.frm_otorgar.getMensajes());
			this.frm_otorgar.clearMessages();
		}
	}
}
