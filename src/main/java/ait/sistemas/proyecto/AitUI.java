package ait.sistemas.proyecto;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import ait.sistemas.proyecto.common.component.AitViewProvider;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.view.MainView;
import ait.sistemas.proyecto.seguridad.component.Auth;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

/**
 * @author franzemil
 *
 */
@Theme("ait-theme")
@Title("Autoridad de Inpugnacion Tributaria")
@Widgetset("ait.sistemas.proyecto.AitWidgetset")
public class AitUI extends UI {
	
	private static final long serialVersionUID = 1L;
	
	Navigator navigator;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		
		// if (((SessionModel) getUI().getSession().getAttribute("user")) ==
		// null) {
		// SessionModel result;
		// try {
		// result = Auth.login("KI-VASQ-IM", "3Enero199$");
		// getUI().getSession().setAttribute("user", result);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		/**
		 * Cuenta de Invitado
		 */
		if (((SessionModel) getUI().getSession().getAttribute("user")) == null) {
			SessionModel result = Auth.getDefaultUser();
			getUI().getSession().setAttribute("user", result);
		}
		if (((Arbol_menus) getUI().getSession().getAttribute("nav")) == null) {
			getUI().getSession().setAttribute("nav", null);
		}
		AitViewProvider viewprovider = new AitViewProvider();
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
		
		@Override
		protected void servletInitialized() throws ServletException {
			super.servletInitialized();
			getService().setSystemMessagesProvider(new SystemMessagesProvider() {
				private static final long serialVersionUID = 1L;
				
				@Override
				public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
					CustomizedSystemMessages messages = new CustomizedSystemMessages();
					messages.setCommunicationErrorCaption("Comm Err");
					messages.setSessionExpiredMessage(Messages.TIMEOUT_SESSION);
					messages.setCommunicationErrorMessage("This is bad.");
					messages.setCommunicationErrorNotificationEnabled(true);
					messages.setCommunicationErrorURL("http://vaadin.com/");
					return messages;
				}
			});
		}
	}
	
}
