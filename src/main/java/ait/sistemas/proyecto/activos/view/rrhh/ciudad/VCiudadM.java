package ait.sistemas.proyecto.activos.view.rrhh.ciudad;

import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Ciudade;
import ait.sistemas.proyecto.activos.data.service.Impl.CiudadImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
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

@CDIView(value = VCiudadM.URL)
public class VCiudadM extends VerticalLayout implements View,
		SelectionListener, ClickListener {

	private static final long serialVersionUID = 1L;
	public static final String URL = "/act/rrhh/ciudad/m";

	private final CiudadImpl ciudad_impl = new CiudadImpl();
	private GridCiudad grid_ciudad;
	private FormCiudad frm_ciudad;
	private Button btn_modificar;
	private Button btn_limpiar;
	private CssLayout hl_errores;
	
	final PropertysetItem pitmCiudad = new PropertysetItem();

	public VCiudadM() {

		frm_ciudad= new FormCiudad();
		this.btn_limpiar= new Button("Limpiar");
		this.btn_modificar= new Button("Modificar");
		this.btn_modificar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);

		this.grid_ciudad = new GridCiudad();
		this.grid_ciudad.addSelectionListener(this);
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
	}
		
	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true	);	
		
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Grid Ciudad");
		gridPanel.setContent(this.grid_ciudad);
		formContent.setMargin(true);
				
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Formulario Ciudad");
		frmPanel.setContent(this.frm_ciudad);
		formContent.setMargin(true);
		
		formContent.addComponent(gridPanel);
		formContent.addComponent(frmPanel);
		Responsive.makeResponsive(formContent);
		return formContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Recursos Humanos » "));
		nav.addComponent(new Label("Ciudad » "));
		nav.addComponent(new Label("<strong>Modificar</strong>",
				ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addStyleName("ait-buttons");

		buttonContent.addComponent(this.btn_modificar);
		buttonContent.addComponent(this.btn_limpiar);
		return buttonContent;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public void select(SelectionEvent event) {

		if ((Ciudade)this.grid_ciudad.getSelectedRow() != null) {
			this.frm_ciudad.setData((Ciudade)this.grid_ciudad.getSelectedRow());	
		}
	}
	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		
		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(new Label(barMessage.getComponetName()+":"+barMessage.getErrorName()));
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	
	}
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_modificar) {
			if(this.frm_ciudad.validate()){
				this.ciudad_impl.update(this.frm_ciudad.getData());
				this.frm_ciudad.update();
				this.frm_ciudad.updateId();
				this.grid_ciudad.update();
				Notification.show(Messages.SUCCESS_MESSAGE);
			}else{
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_ciudad.getMensajes());
			this.frm_ciudad.clearMessages();
		}
		if (event.getButton() == this.btn_limpiar) {
			this.frm_ciudad.update();
			this.frm_ciudad.updateId();
		}
	}

}

