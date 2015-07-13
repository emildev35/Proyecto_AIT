package ait.sistemas.proyecto;

import javax.inject.Inject;

import ait.sistemas.proyecto.common.view.MainView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

/**
 *
 */
/**
 * @author franzemil
 *
 */
@Theme("ait-theme")
@CDIUI("")
@Widgetset("ait.sistemas.proyecto.AitWidgetset")
public class AitUI extends UI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CDIViewProvider viewprovider;
	Navigator navigator;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		CssLayout layout = new CssLayout();
		setContent(new MainView(layout));
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(
				layout);
		this.navigator = new Navigator(this, viewDisplay);
		this.navigator.addProvider(viewprovider);
	}
}
