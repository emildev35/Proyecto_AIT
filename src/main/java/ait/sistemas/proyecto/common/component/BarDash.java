package ait.sistemas.proyecto.common.component;

import ait.sistemas.proyecto.common.widget.aitclock.AitClock;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Image;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Componente que propociona un Barra en la parte superior de la 
 * pantalla en la cual se encuentran componentes como:
 * 	Logo AIT
 * 	Reloj
 * @author franzemil
 *
 */
public class BarDash extends CustomComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	private Label nav_modulo;
	private Label nav_menu;
	private Label nav_submenu;
	private Label nav_opcion;
	private Label lbDependencia;*/
	private Image logo_ait;
	private static final String STYLE_VISIBLE = "valo-menu-visible";

	public BarDash() {

		addStyleName("ait-bar");
		/*
		this.nav_modulo = new Label("Modulo");
		this.nav_menu = new Label("Menu");
		this.nav_submenu = new Label("Sub-Menu");
		this.nav_opcion = new Label("Opcion");
		this.lbDependencia = new Label("Dependencia : General La Paz");*/
		ThemeResource resource = new ThemeResource("logos/Logo_AIT.png");
		this.logo_ait = new Image("", resource);
		Responsive.makeResponsive(this);
		setCompositionRoot(buildComponents());
	}

	/**
	 * Este metodo proporciona el contenedor padre del Componente			
	 * @return Component
	 */
	private Component buildComponents() {
		CssLayout mainContent = new CssLayout();
		mainContent.addStyleName("ait-bar-main");
		buildNavigation();
		mainContent.addComponent(buildToggleButton());
		mainContent.addComponent(buildLogo());
		mainContent.addComponent(buildNavigation());
		mainContent.addComponent(buildClock());

		return mainContent;
	}

	/**
	 * Se encarga de generar el Logo de la Institucion
	 * @return
	 */
	private Component buildLogo() {
		CssLayout logoContent = new CssLayout();
		this.logo_ait.setStyleName("ait-bar-logo");
		logoContent.addComponent(this.logo_ait);
		/**
		 * Componente de la Dependencia
		 */
		/*
		 * logoContent.addComponent(this.lbDependencia);
		 */
		// Responsive.makeResponsive(this);
		return logoContent;
	}

	private Component buildNavigation() {
		CssLayout navigationContent = new CssLayout();
		/**
		 * Componentes de Navegacion
		 */
		/*
		 * navigationContent.addStyleName("ait-bar-navitagion");
		 * navigationContent.addComponent(this.nav_modulo);
		 * navigationContent.addComponent(this.nav_menu);
		 * navigationContent.addComponent(this.nav_submenu);
		 * navigationContent.addComponent(this.nav_opcion);
		 */
		Responsive.makeResponsive(navigationContent);
		return navigationContent;
	}

	/**
	 * Se encarga de Generar el Boton Toggle que se muestra en pantallas
	 * con una baja definicion
	 * @return
	 */
	private Component buildToggleButton() {

		@SuppressWarnings("serial")
		Button valoMenuToggleButton = new Button("Menu", new ClickListener() {

			MenuDash menu = new MenuDash();

			@Override
			public void buttonClick(final ClickEvent event) {
				menu.show(STYLE_VISIBLE);
			}
		});
		valoMenuToggleButton.setIcon(FontAwesome.LIST);
		valoMenuToggleButton.addStyleName("ait-menu-toggle");
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		valoMenuToggleButton.addStyleName(ValoTheme.BUTTON_SMALL);
		Responsive.makeResponsive(valoMenuToggleButton);
		return valoMenuToggleButton;
	}
	/**
	 * Se encarga de generar el componente que contiene la fecha y hora
	 * @return
	 */
	private Component buildClock() {

		CssLayout clockContent = new CssLayout();
		clockContent.addStyleName("ait-bar-clock");
		clockContent.addComponent(new AitClock());
		return clockContent;
	}

}
