package ait.sistemas.proyecto;

import javax.inject.Inject;
import javax.jms.Session;

import ait.sistemas.proyecto.common.view.MainView;
import ait.sistemas.proyecto.seguridad.component.Auth;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.model.Usuario;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
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


	private static final long serialVersionUID = 1L;

	@Inject
	private CDIViewProvider viewprovider;
	Navigator navigator;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		if(((SessionModel)getUI().getSession().getAttribute("user"))==null){
			SessionModel result = Auth.getDefaultUser();
			getUI().getSession().setAttribute("user", result);
		}
		CssLayout layout = new CssLayout();
		setContent(new MainView(layout));
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(
				layout);
		this.navigator = new Navigator(this, viewDisplay);
		this.navigator.addProvider(viewprovider);
		
	}
	
}
