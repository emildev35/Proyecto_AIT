package ait.sistemas.proyecto.activos.view.para.tiposact;

	import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.activos.data.model.Tipos_Activo;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposactImpl;
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

	@CDIView(value = VTiposactB.URL)
	public class VTiposactB extends VerticalLayout implements View,
			SelectionListener, ClickListener, org.vaadin.dialogs.ConfirmDialog.Listener {

		private static final long serialVersionUID = 1L;
		public static final String URL = "/act/para/tiposact/b";

		private TiposactImpl tipos_impl = new TiposactImpl();
		private GridTiposact grid_tiposact;
		private Button btn_limpiar;
		private Button btn_eliminar;
		private FormTiposact frm_tiposact;
		private CssLayout hl_errores;
		
		final PropertysetItem pitm_Tiposact = new PropertysetItem();

		public VTiposactB() {

			this.frm_tiposact= new FormTiposact();
			this.btn_limpiar= new Button("Limpiar");
			this.btn_eliminar= new Button("Eliminar");
			this.btn_eliminar.addClickListener(this);
			this.btn_limpiar.addClickListener(this);
			this.grid_tiposact = new GridTiposact();
			this.hl_errores = new CssLayout();
			
			addComponent(buildNavBar());
			addComponent(buildFormContent());
			addComponent(buildButtonBar());
			buildGrid();
			Responsive.makeResponsive(this);
		}
		private void buildGrid() {
			this.grid_tiposact.addSelectionListener(this);
		}
		private Component buildFormContent() {

			VerticalLayout formContent = new VerticalLayout();
			formContent.setSpacing(true	);				
					
			Panel frmPanel = new Panel();
			frmPanel.setWidth("100%");
			frmPanel.setCaption("Datos a eliminar");
			frmPanel.setContent(this.frm_tiposact);
			this.frm_tiposact.enabled();
			formContent.setMargin(true);
			Panel gridPanel = new Panel();
			gridPanel.setWidth("100%");
			gridPanel.setCaption("Tipos de Activos registrados");
			gridPanel.setContent(this.grid_tiposact);
			formContent.setMargin(true);
			formContent.addComponent(gridPanel);
			formContent.addComponent(frmPanel);
			
			this.frm_tiposact.update();
			Responsive.makeResponsive(formContent);
			return formContent;
		}

		private Component buildNavBar() {
			Panel navPanel = new Panel();
			HorizontalLayout nav = new HorizontalLayout();
			nav.addStyleName("ait-content-nav");
			nav.addComponent(new Label("Activos » "));
			nav.addComponent(new Label("Parametros » "));
			nav.addComponent(new Label("Tipos de Activos » "));
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

			if ((Tipos_Activo)this.grid_tiposact.getSelectedRow() != null) {
				this.frm_tiposact.setData((Tipos_Activo)this.grid_tiposact.getSelectedRow());	
			}
		}
		private void  eliminar() {
			try {
			this.tipos_impl.deletes(this.frm_tiposact.getData().getTAC_Id_Tipo_Activo());
			this.frm_tiposact.update();
			this.grid_tiposact.update();
			Notification.show(Messages.SUCCESS_MESSAGE);
		} catch (Exception e) {
			Notification.show(Messages.FOREIGN_RELATION_ERROR(frm_tiposact.txt_nombre_tiposact
					.getValue()), Type.ERROR_MESSAGE);
		}
			buildMessages(this.frm_tiposact.getMensajes());
			this.frm_tiposact.clearMessages();
		}
		@Override
		public void buttonClick(ClickEvent event) {
			if (event.getButton() == this.btn_eliminar) {

				ConfirmDialog.show(getUI(),"", Messages.CONFIRM_DELETE_MESSAGE, "SI", "NO", this); 
			}
			if (event.getButton() == this.btn_limpiar) {
				this.frm_tiposact.update();
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

