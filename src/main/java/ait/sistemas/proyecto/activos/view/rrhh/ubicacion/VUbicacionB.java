package ait.sistemas.proyecto.activos.view.rrhh.ubicacion;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.activos.data.model_rrhh.UbicacionesFisicasModel;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UbicacionImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


public class VUbicacionB extends VerticalLayout implements View,
		SelectionListener, ClickListener, org.vaadin.dialogs.ConfirmDialog.Listener {

	private static final long serialVersionUID = 1L;

	private UbicacionImpl ubicacion_impl = new UbicacionImpl();
	private DependenciaImpl dependencia_impl = new DependenciaImpl();
	private GridUbicacion grid_ubicacion;
	private Button btn_limpiar;
	private Button btn_eliminar;
	private FormUbicacion frm_ubicacion;
	private CssLayout hl_errores;
	
	final PropertysetItem pitm_Ubicacion = new PropertysetItem();

	public VUbicacionB() {

		this.frm_ubicacion= new FormUbicacion();
		this.btn_limpiar= new Button("Limpiar");
		this.btn_eliminar= new Button("Eliminar");
		this.btn_eliminar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		this.grid_ubicacion = new GridUbicacion();
		this.hl_errores = new CssLayout();
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		buildGrid();
		Responsive.makeResponsive(this);
	}
	private void buildGrid() {
		this.grid_ubicacion.addSelectionListener(this);
	}
	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true	);				
				
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Datos a eliminar");
		frmPanel.setContent(this.frm_ubicacion);
		this.frm_ubicacion.enabled();
		formContent.setMargin(true);
		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Ubicaciones Fisicas registradas");
		gridPanel.setContent(this.grid_ubicacion);
		formContent.setMargin(true);
		formContent.addComponent(gridPanel);
		formContent.addComponent(frmPanel);
		
		this.frm_ubicacion.update();
		Responsive.makeResponsive(formContent);
		return formContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Recursos Humanos » "));
		nav.addComponent(new Label("Unidades Oraganizacionales » "));
		nav.addComponent(new Label("<strong>Eliminar</strong>",
				ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_eliminar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_eliminar);
		this.btn_limpiar.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
		return buttonContent;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public void select(SelectionEvent event) {

		if ((UbicacionesFisicasModel)this.grid_ubicacion.getSelectedRow() != null) {
			this.frm_ubicacion.setData((UbicacionesFisicasModel)this.grid_ubicacion.getSelectedRow());	
		}
	}
	private void  eliminar() {
		try {
			SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
			 short dependencia = dependencia_impl.getdependencia_ID(usuario.getDependecia());
		this.ubicacion_impl.deletes(dependencia,this.frm_ubicacion.getData().getUBF_Ubicacion_Fisica());
		this.frm_ubicacion.update();
		this.grid_ubicacion.update();
		Notification.show(Messages.SUCCESS_MESSAGE);
	} catch (Exception e) {
		Notification.show(Messages.FOREIGN_RELATION_ERROR(frm_ubicacion.txt_nombre_ubicacion
				.getValue()), Type.ERROR_MESSAGE);
	}
		buildMessages(this.frm_ubicacion.getMensajes());
		this.frm_ubicacion.clearMessages();
	}
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_eliminar) {

			ConfirmDialog.show(getUI(),"", Messages.CONFIRM_DELETE_MESSAGE, "SI", "NO", this); 
		}
		if (event.getButton() == this.btn_limpiar) {
			frm_ubicacion.update();
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
	public void onClose(ConfirmDialog dialog) {
		if(dialog.isConfirmed()){
			eliminar();
		}else{
			
		}
	}

}

