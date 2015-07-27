package ait.sistemas.proyecto.activos.view.para.tiposmov;

	import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Tipos_Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposmovImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

	import com.vaadin.cdi.CDIView;
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


	@CDIView(value=VTiposmovM.ID)
	public class VTiposmovM extends VerticalLayout implements View, ClickListener, SelectionListener{

		private static final long serialVersionUID = 1L;
		public static final String ID = "/act/para/tiposmov/m";
		
		private FormTiposmov frm_tiposmov;
		private CssLayout hl_errores;
		private Button btn_limpiar;
		private Button btn_modificar;
		private GridTiposmov grid_tiposmov;
		private final TiposmovImpl tiposmov_impl = new TiposmovImpl();
		
		
		public VTiposmovM() {
			
			this.frm_tiposmov= new FormTiposmov();
			this.btn_limpiar= new Button("Limpiar");
			this.btn_modificar= new Button("Modificar");
			this.btn_modificar.addClickListener(this);
			this.btn_limpiar.addClickListener(this);
		
			this.grid_tiposmov = new GridTiposmov();
			this.grid_tiposmov.addSelectionListener(this);
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
			frmPanel.setCaption("Datos a modificar");
			frmPanel.setContent(this.frm_tiposmov);
			formContent.setMargin(true);
			formContent.addComponent(frmPanel);
			Panel gridPanel = new Panel();
			gridPanel.setWidth("100%");
			gridPanel.setCaption("Tipos de Movimientos Registrados");
			gridPanel.setContent(this.grid_tiposmov);
			formContent.setMargin(true);
			formContent.addComponent(gridPanel);
			formContent.addComponent(frmPanel);
			this.frm_tiposmov.update();
			Responsive.makeResponsive(formContent);
			return formContent;
			
		}
		private Component buildNavBar() {
			Panel navPanel = new Panel();
			HorizontalLayout nav = new HorizontalLayout();
			nav.addStyleName("ait-content-nav");
			nav.addComponent(new Label("Activos>>"));
			nav.addComponent(new Label("Parametros>>"));
			nav.addComponent(new Label("Tipos de Movimientos>>"));
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

			if ((Tipos_Movimiento)this.grid_tiposmov.getSelectedRow() != null) {
				this.frm_tiposmov.setData((Tipos_Movimiento)this.grid_tiposmov.getSelectedRow());	
			}		
		}
		@Override
		public void buttonClick(ClickEvent event) {
			if (event.getButton() == this.btn_modificar) {
				if(this.frm_tiposmov.validate()){
					this.tiposmov_impl.update(this.frm_tiposmov.getData());
					this.frm_tiposmov.update();
					this.grid_tiposmov.update();
					Notification.show(Messages.SUCCESS_MESSAGE);
				}else{
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
				buildMessages(this.frm_tiposmov.getMensajes());
				this.frm_tiposmov.clearMessages();
			}
			if (event.getButton() == this.btn_limpiar) {
				frm_tiposmov.update();
			}	
		}
		

	}
