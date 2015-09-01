package ait.sistemas.proyecto;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import ait.sistemas.proyecto.common.component.AitViewProvider;
import ait.sistemas.proyecto.common.view.MainView;
import ait.sistemas.proyecto.seguridad.component.Auth;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

/**
 * @author franzemil
 *
 */
@Theme("ait-theme")
@Widgetset("ait.sistemas.proyecto.AitWidgetset")
public class AitUI extends UI {
	
	private static final long serialVersionUID = 1L;
	
	private AitViewProvider viewprovider = new AitViewProvider();
	Navigator navigator;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		// Comentado para motivo de pruebas
		/*
		 * if(((SessionModel)getUI().getSession().getAttribute("user"))==null){
		 * SessionModel result = Auth.getDefaultUser();
		 * getUI().getSession().setAttribute("user", result); }
		 */
		if (((SessionModel) getUI().getSession().getAttribute("user")) == null) {
			SessionModel result;
			try {
				result = Auth.login("KI-VASQ-IM", "3Enero199$");
				getUI().getSession().setAttribute("user", result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		CssLayout layout = new CssLayout();
		setContent(new MainView(layout));
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(layout);
		this.navigator = new Navigator(this, viewDisplay);
		this.navigator.addProvider(viewprovider);
		
	}
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = AitUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
    }
	
}
