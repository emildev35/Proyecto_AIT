package ait.sistemas.proyecto.seguridad.view.login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.seguridad.component.Auth;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

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
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@CDIView(value = VLoginP.ID)
public class VLoginP extends VerticalLayout implements View, ClickListener {

	public static final String ID = "/seg/login";
	private static final long serialVersionUID = 1L;

	private LoginForm frm_login = new LoginForm();
	private Button btn_submit = new Button("Login");
	private Button btn_limpiar = new Button("Limpiar");
	private CssLayout hl_errores = new CssLayout();

	private final UsuarioImpl usuarioimpl = new UsuarioImpl();
	
	
	public VLoginP() {
		setWidth("100%");
		this.btn_submit.addClickListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildButtonBar());
		addComponent(buildForm());
	}

	private Component buildForm() {
		final VerticalLayout vl_content = new VerticalLayout();
		vl_content.setMargin(true);
		Panel pn_frm = new Panel();
		pn_frm.setContent(this.frm_login);
		vl_content.addComponent(pn_frm);
		return vl_content;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Seguridad » "));
		nav.addComponent(new Label("<strong>Login</strong>", ContentMode.HTML));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {
			if (this.frm_login.validate()) {
				if (this.frm_login.isNew()) {
					this.usuarioimpl.addPassword(this.frm_login.getUsuario(), this.frm_login.getPassword());
				}else{
					SessionModel result = Auth.login(this.frm_login.getUsuario(), this.frm_login.getPassword());
					if(result!=null){
						Notification.show("Encontrado");
						getUI().getSession().setAttribute("username", this.frm_login.getUsuario());
					}else{
						Notification.show("PasswordNoValido");
					}
				}
			}
			buildMessages(this.frm_login.getMessages());
		}
		if(event.getButton()==this.btn_limpiar){
			Notification.show(getUI().getSession().getAttribute("username").toString());
		}
	}
}
