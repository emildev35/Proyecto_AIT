package ait.sistemas.proyecto.activos.view.para.partida;

	import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import ait.sistemas.proyecto.activos.data.model.Partidas_Presupuestaria;
import ait.sistemas.proyecto.activos.data.service.Impl.PartidaImpl;
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

	@CDIView(value = VPartidaB.URL)
	public class VPartidaB extends VerticalLayout implements View,
			SelectionListener, ClickListener, org.vaadin.dialogs.ConfirmDialog.Listener {

		private static final long serialVersionUID = 1L;
		public static final String URL = "/act/para/partida/b";

		private PartidaImpl partida_impl = new PartidaImpl();
		private GridPartida grid_partida;
		private Button btn_limpiar;
		private Button btn_eliminar;
		private FormPartida frm_partida;
		private CssLayout hl_errores;
		
		final PropertysetItem pitm_Partida = new PropertysetItem();

		public VPartidaB() {

			this.frm_partida= new FormPartida();
			this.btn_limpiar= new Button("Limpiar");
			this.btn_eliminar= new Button("Eliminar");
			this.btn_eliminar.addClickListener(this);
			this.btn_limpiar.addClickListener(this);
			this.grid_partida = new GridPartida();
			this.hl_errores = new CssLayout();
			
			addComponent(buildNavBar());
			addComponent(buildFormContent());
			addComponent(buildButtonBar());
			buildGrid();
			Responsive.makeResponsive(this);
		}
		private void buildGrid() {
			this.grid_partida.addSelectionListener(this);
		}
		private Component buildFormContent() {

			VerticalLayout formContent = new VerticalLayout();
			formContent.setSpacing(true	);				
					
			Panel frmPanel = new Panel();
			frmPanel.setWidth("100%");
			frmPanel.setCaption("Datos a eliminar");
			frmPanel.setContent(this.frm_partida);
			this.frm_partida.enabled();
			formContent.setMargin(true);
			Panel gridPanel = new Panel();
			gridPanel.setWidth("100%");
			gridPanel.setCaption("Partidas Presupuestarias registrados");
			gridPanel.setContent(this.grid_partida);
			formContent.setMargin(true);
			formContent.addComponent(gridPanel);
			formContent.addComponent(frmPanel);
			
			this.frm_partida.update();
			Responsive.makeResponsive(formContent);
			return formContent;
		}

		private Component buildNavBar() {
			Panel navPanel = new Panel();
			HorizontalLayout nav = new HorizontalLayout();
			nav.addStyleName("ait-content-nav");
			nav.addComponent(new Label("Activos » "));
			nav.addComponent(new Label("Parametros » "));
			nav.addComponent(new Label("Partidas Presupuestarias » "));
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

			if ((Partidas_Presupuestaria)this.grid_partida.getSelectedRow() != null) {
				this.frm_partida.setData((Partidas_Presupuestaria)this.grid_partida.getSelectedRow());	
			}
		}
		private void  eliminar() {
			try {
			this.partida_impl.deletes(this.frm_partida.getData().getPAP_Partida());
			this.frm_partida.update();
			this.grid_partida.update();
			Notification.show(Messages.SUCCESS_MESSAGE);
		} catch (Exception e) {
			Notification.show(Messages.FOREIGN_RELATION_ERROR(frm_partida.txt_nombre_partida
					.getValue()), Type.ERROR_MESSAGE);
		}
			buildMessages(this.frm_partida.getMensajes());
			this.frm_partida.clearMessages();
		}
		@Override
		public void buttonClick(ClickEvent event) {
			if (event.getButton() == this.btn_eliminar) {

				ConfirmDialog.show(getUI(),"", Messages.CONFIRM_DELETE_MESSAGE, "SI", "NO", this); 
			}
			if (event.getButton() == this.btn_limpiar) {
				this.frm_partida.update();
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

