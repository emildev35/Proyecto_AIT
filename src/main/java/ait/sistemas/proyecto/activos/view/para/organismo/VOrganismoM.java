package ait.sistemas.proyecto.activos.view.para.organismo;

	import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Fuentes_Financiamiento;
import ait.sistemas.proyecto.activos.data.model.Organismo_Financiador;
import ait.sistemas.proyecto.activos.data.service.Impl.FuenteImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.OrganismoImpl;
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


	@CDIView(value=VOrganismoM.ID)
	public class VOrganismoM extends VerticalLayout implements View, ClickListener, SelectionListener{

		private static final long serialVersionUID = 1L;
		public static final String ID = "/act/para/organismo/m";
		
		private FormOrganismo frm_organismo;
		private CssLayout hl_errores;
		private Button btn_limpiar;
		private Button btn_modificar;
		private GridOrganismo grid_organismo;
		private final OrganismoImpl organismo_impl = new OrganismoImpl();
		
		
		public VOrganismoM() {
			
			this.frm_organismo= new FormOrganismo();
			this.btn_limpiar= new Button("Limpiar");
			this.btn_modificar= new Button("Modificar");
			this.btn_modificar.addClickListener(this);
			this.btn_limpiar.addClickListener(this);
		
			this.grid_organismo = new GridOrganismo();
			this.grid_organismo.addSelectionListener(this);
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
			frmPanel.setContent(this.frm_organismo);
			formContent.setMargin(true);
			formContent.addComponent(frmPanel);
			Panel gridPanel = new Panel();
			gridPanel.setWidth("100%");
			gridPanel.setCaption("Organismos Financiadores registrados");
			gridPanel.setContent(this.grid_organismo);
			formContent.setMargin(true);
			formContent.addComponent(gridPanel);
			formContent.addComponent(frmPanel);
			this.frm_organismo.update();
			Responsive.makeResponsive(formContent);
			return formContent;
			
		}
		private Component buildNavBar() {
			Panel navPanel = new Panel();
			HorizontalLayout nav = new HorizontalLayout();
			nav.addStyleName("ait-content-nav");
			nav.addComponent(new Label("Activos>>"));
			nav.addComponent(new Label("Parametros>>"));
			nav.addComponent(new Label("Organismo Financiador>>"));
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

			if ((Organismo_Financiador)this.grid_organismo.getSelectedRow() != null) {
				this.frm_organismo.setData((Organismo_Financiador)this.grid_organismo.getSelectedRow());	
			}		
		}
		@Override
		public void buttonClick(ClickEvent event) {
			if (event.getButton() == this.btn_modificar) {
				if(this.frm_organismo.validate()){
					this.organismo_impl.update(this.frm_organismo.getData());
					this.frm_organismo.update();
					this.grid_organismo.update();
					Notification.show(Messages.SUCCESS_MESSAGE);
				}else{
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
				buildMessages(this.frm_organismo.getMensajes());
				this.frm_organismo.clearMessages();
			}
			if (event.getButton() == this.btn_limpiar) {
				this.frm_organismo.update();
			}	
		}
		

	}
