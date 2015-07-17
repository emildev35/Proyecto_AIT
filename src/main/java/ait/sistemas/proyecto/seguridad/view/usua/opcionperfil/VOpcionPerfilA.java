package ait.sistemas.proyecto.seguridad.view.usua.opcionperfil;

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
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@CDIView(value=VOpcionPerfilA.ID)
public class VOpcionPerfilA extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;

	public static final String ID = "/seg/usua/opcionperfil/a";

	private FormOpcionPerfil frmOpcionPeril;
	private Button btn_submit;
	private Button btn_limpiar;
	
	private final PerfilImpl perfilimpl = new PerfilImpl();
	
	public VOpcionPerfilA() {
		this.frmOpcionPeril = new FormOpcionPerfil();
		this.btn_submit = new Button("Guardar");
		this.btn_limpiar = new Button("Limpiar");
		
		addComponent(buildNavBar());
		addComponent(builFormContent());
		addComponent(buildButtonBar());
	}

	private Component builFormContent() {
		final VerticalLayout vlfrmContent = new VerticalLayout();
		vlfrmContent.setMargin(true);
		
		Panel pnfrmOpcionPerfil = new Panel("Formulario de Registro de Opcion por Perfil");
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
		nav.addComponent(new Label("<strong>Agregar Opciones</strong>", ContentMode.HTML));
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

	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton() == this.btn_submit){
			this.perfilimpl.otortgarPermisos(this.frmOpcionPeril.getIdPerfil(), this.frmOpcionPeril.getListMenu());
		}
	}
}
