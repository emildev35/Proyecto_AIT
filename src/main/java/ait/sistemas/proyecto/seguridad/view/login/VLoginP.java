package ait.sistemas.proyecto.seguridad.view.login;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.common.view.HomeView;
import ait.sistemas.proyecto.seguridad.component.Auth;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VLoginP extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private LoginForm frm_login = new LoginForm();
	private Button btn_submit = new Button("Login");
	private Button btn_limpiar = new Button("Limpiar");
	private CssLayout hl_errores = new CssLayout();
	
	private final UsuarioImpl usuarioimpl = new UsuarioImpl();
	
	Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	public VLoginP() {
		setWidth("100%");
		this.btn_submit.addClickListener(this);
		
		/**
		 * Evento de Boton Enter para el Login
		 */
		addShortcutListener(new ShortcutListener("ENTER", KeyCode.ENTER, new int[] {}) {
			
			private static final long serialVersionUID = 1L;
			
			@Override
			public void handleAction(Object sender, Object target) {
				btn_submit.click();
			}
		});
		addComponent(buildNavBar());
		addComponent(buildButtonBar());
		addComponent(buildForm());
	}
	
	private Component buildForm() {
		final VerticalLayout vl_content = new VerticalLayout();
		vl_content.setMargin(true);
		Panel pn_frm = new Panel("REGISTRE SU INFORMACION PARA SU AUTENTICACION");
		pn_frm.setStyleName(AitTheme.PANEL_FORM);
		pn_frm.setIcon(FontAwesome.USER);
		pn_frm.setContent(this.frm_login);
		vl_content.addComponent(pn_frm);
		return vl_content;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		navPanel.setStyleName(AitTheme.NAV_BAR);
		nav.addComponent(new Label(AitView.getNavText(menu), ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_submit);
		buttonContent.addComponent(this.btn_limpiar);
		
		this.btn_submit.addStyleName(AitTheme.BTN_SUBMIT);
		this.btn_submit.setIcon(FontAwesome.USER);
		this.btn_limpiar.addStyleName(AitTheme.BTN_EXIT);
		this.btn_limpiar.setIcon(FontAwesome.UNDO);
		
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		Responsive.makeResponsive(buttonContent);
		this.btn_submit.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		return buttonContent;
	}
	
	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		
		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {
			if (this.frm_login.validate()) {
				SessionModel result;
				if (this.frm_login.isNew()) {
					this.usuarioimpl.addPassword(this.frm_login.getUsuario(), this.frm_login.getPassword());
					try {
						result = Auth.login(this.frm_login.getUsuario(), this.frm_login.getPassword());
						if (result != null) {
							UI.getCurrent().getSession().setAttribute("user", result);
							UI.getCurrent().getPage().reload();
							UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
						} else {
							Notification.show(Messages.LOGIN_ERROR, Type.ERROR_MESSAGE);
						}
					} catch (NoResultException e) {
						Notification.show(Messages.LOGIN_ERROR, Type.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (SQLException e) {
						Notification.show(Messages.LOGIN_ERROR, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}
				} else {
					try {
						result = Auth.login(this.frm_login.getUsuario(), this.frm_login.getPassword());
						if (result != null) {
							UI.getCurrent().getSession().setAttribute("user", result);
							UI.getCurrent().getPage().reload();
							UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
						} else {
							Notification.show(Messages.LOGIN_ERROR, Type.ERROR_MESSAGE);
						}
					} catch (NoResultException e) {
						Notification.show(Messages.LOGIN_ERROR, Type.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (SQLException e) {
						Notification.show(Messages.LOGIN_ERROR, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
			buildMessages(this.frm_login.getMessages());
		}
		if (event.getButton() == this.btn_limpiar) {
			Notification.show(((SessionModel) getUI().getSession().getAttribute("user")).getId());
		}
	}
}
