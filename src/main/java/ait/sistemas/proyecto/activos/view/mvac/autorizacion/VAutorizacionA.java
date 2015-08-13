package ait.sistemas.proyecto.activos.view.mvac.autorizacion;

import java.sql.Date;
import java.util.List;

import org.eclipse.persistence.internal.dbws.SOAPAttachmentHandler;

import ait.sistemas.proyecto.activos.component.grid.GridDetalleActivo;
import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;
import ait.sistemas.proyecto.activos.data.model.Autorizacion;
import ait.sistemas.proyecto.activos.data.service.Impl.AutorizacionImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.cdi.CDIView;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
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

@CDIView(value = VAutorizacionA.ID)
public class VAutorizacionA extends VerticalLayout implements View, ClickListener, SelectionListener {
	
	private static final long serialVersionUID = 1L;
	public static final String ID = "/mvac/autorizacion/a";
	
	private FormAutorizacion frm_autorizacion = new FormAutorizacion();
	private FormDocumento frm_documento = new FormDocumento();
	private GridDocumentosPendientes grid_documentos;
	private GridDetalleActivo grid_detalle_activo = new GridDetalleActivo();
	
	private CssLayout hl_errores = new CssLayout();
	private Button btn_aprobar = new Button("Aprobar");
	private Button btn_rechazar = new Button("Rechazar");
	private Button btn_salir = new Button("Salir");
	private DocumentoPendiente documento_seleccionado;
	
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	private final AutorizacionImpl autorizacionimpl = new AutorizacionImpl();
	
	public VAutorizacionA() {
		this.btn_aprobar.addClickListener(this);
		this.btn_rechazar.addClickListener(this);
		this.btn_salir.addClickListener(this);
		this.grid_documentos = new GridDocumentosPendientes(session.getId());
		this.grid_documentos.addSelectionListener(this);
		
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_aprobar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_aprobar);
		this.btn_rechazar.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_rechazar);
		this.btn_salir.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_salir);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		formContent.setMargin(true);
		
		Panel pnfrm_documentos = new Panel("Documento");
		Panel pngrid_documentos = new Panel("Documentos Pendientes de Autorizacion");
		Panel pnfrm_autorizacion = new Panel("Autorizacion / Rechazo");
		Panel pngrid_detalle = new Panel("Detalle de Activos");
		
		pnfrm_documentos.setWidth("100%");
		pngrid_documentos.setWidth("100%");
		pnfrm_autorizacion.setWidth("100%");
		pngrid_detalle.setWidth("100%");
		
		pnfrm_documentos.setContent(frm_documento);
		pngrid_documentos.setContent(grid_documentos);
		pnfrm_autorizacion.setContent(frm_autorizacion);
		pngrid_detalle.setContent(grid_detalle_activo);
		
		formContent.addComponent(pngrid_documentos);
		formContent.addComponent(pnfrm_documentos);
		formContent.addComponent(pngrid_detalle);
		formContent.addComponent(pnfrm_autorizacion);
		
		Responsive.makeResponsive(formContent);
		return formContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Movimientos de Activos Fijos>>"));
		nav.addComponent(new Label("Autorizaciones>>"));
	
		navPanel.setContent(nav);
		return navPanel;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		
		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName()));
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_aprobar ||event.getButton() == this.btn_rechazar) {
			if (this.grid_documentos.getSelectedRow() != null) {
				if (this.frm_autorizacion.validate()) {
					java.sql.Date fecha = new java.sql.Date(new java.util.Date().getTime());
					
					Autorizacion autorizacion = new Autorizacion();
					autorizacion.setAUT_CI_Autoriza(session.getCi());
					autorizacion.setAUT_Dependencia(documento_seleccionado.getDependencia_id());
					autorizacion.setAUT_Fecha_Registro(fecha);
//					 (autorizacionimpol.add(this.frm_solicitud.getData())>0)
//					 {
//					 this.frm_solicitud.clear();
//					 Notification.show(Messages.SUCCESS_MESSAGE);
//					 }
//					 else{
//					 Notification.show(Messages.NOT_SUCCESS_MESSAGE,
//					 Type.ERROR_MESSAGE);
//					
//					 }
				} else {
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
			} else {
				Notification.show(Messages.SELECT_MOVIMIENTO, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_autorizacion.getMensajes());
			this.frm_autorizacion.clearMessages();
		}
		if (event.getButton() == this.btn_salir) {
			frm_autorizacion.validate();
			// this.frm_solicitud.updateId();
			
		}
	}
	
	@Override
	public void select(SelectionEvent event) {
		if ((GridDocumentosPendientes) event.getSource() == this.grid_documentos && grid_documentos.getSelectedRow() != null) {
			documento_seleccionado = (DocumentoPendiente) grid_documentos.getSelectedRow();
			frm_documento.fillData(documento_seleccionado);
			grid_detalle_activo.update(documento_seleccionado.getNro_documento(), documento_seleccionado.getDependencia_id());
		}
	}
	
}
