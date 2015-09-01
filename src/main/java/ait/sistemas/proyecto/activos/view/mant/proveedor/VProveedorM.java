package ait.sistemas.proyecto.activos.view.mant.proveedor;

import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ProveedoresModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ProveedorImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

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


public class VProveedorM extends VerticalLayout implements View, ClickListener, SelectionListener{

	private static final long serialVersionUID = 1L;
	
	
	private FormProveedor frm_proveedor;
	private CssLayout hl_errores;
	private Button btn_limpiar;
	private Button btn_modificar;
	private GridProveedor grid_proveedor;
	private final ProveedorImpl proveedor_impl = new ProveedorImpl();
	
	final PropertysetItem pitm_Proveedor = new PropertysetItem();
	
	public VProveedorM() {
		
		this.frm_proveedor= new FormProveedor();
		this.btn_limpiar= new Button("Limpiar");
		this.btn_modificar= new Button("Modificar");
		this.btn_modificar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
	
		this.grid_proveedor = new GridProveedor();
		this.grid_proveedor.addSelectionListener(this);
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true	);
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Datos a modificar:");
		frmPanel.setContent(this.frm_proveedor);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Seleccione el Proveedor para modificar sus datos:");
		gridPanel.setContent(this.grid_proveedor);
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
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Mantenimiento>>"));
		nav.addComponent(new Label("Proveedor>>"));
		nav.addComponent(new Label("<strong>Modificar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_modificar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_modificar);
		this.btn_limpiar.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
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
			Label lbError = new Label(barMessage.getComponetName()+":"+barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
	
	}
	@Override
	public void select(SelectionEvent event) {

		if ((ProveedoresModel)this.grid_proveedor.getSelectedRow() != null) {
			this.frm_proveedor.setData((ProveedoresModel)this.grid_proveedor.getSelectedRow());	
		}		
	}
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_modificar) {
			if(this.frm_proveedor.validate()){
				this.proveedor_impl.update(this.frm_proveedor.getData());
				this.frm_proveedor.update();
				this.grid_proveedor.update();
				Notification.show(Messages.SUCCESS_MESSAGE);
			}else{
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_proveedor.getMensajes());
			this.frm_proveedor.clearMessages();
		}
		if (event.getButton() == this.btn_limpiar) {
			frm_proveedor.update();
		}	
	}
	

}
