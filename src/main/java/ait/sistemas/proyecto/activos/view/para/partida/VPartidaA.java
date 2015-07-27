package ait.sistemas.proyecto.activos.view.para.partida;

	import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.PartidaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

	
	@CDIView(value = VPartidaA.ID)
	public class VPartidaA extends VerticalLayout implements View, ClickListener {

		private static final long serialVersionUID = 1L;
		public static final String ID = "/act/para/partida/a";
		
		private FormPartida frm_partida;
		private CssLayout hl_errores;
		private Button btn_limpiar;
		private Button btn_agregar;
		private GridPartida grid_partida;
		private final PartidaImpl partida_impl = new PartidaImpl();
		

		public VPartidaA() {
			frm_partida= new FormPartida();
			this.btn_limpiar= new Button("Limpiar");
			this.btn_agregar= new Button("Agregar");
			this.btn_agregar.addClickListener(this);
			this.btn_limpiar.addClickListener(this);

			this.grid_partida = new GridPartida();
			this.hl_errores = new CssLayout();
			
			addComponent(buildNavBar());
			addComponent(buildFormContent());
			addComponent(buildButtonBar());
		}
		

		private Component buildButtonBar() {
			CssLayout buttonContent = new CssLayout();
			this.btn_agregar.setStyleName("ait-buttons-btn");
			buttonContent.addComponent(this.btn_agregar);
			this.btn_limpiar.setStyleName("ait-buttons-btn");
			buttonContent.addStyleName("ait-buttons");
			buttonContent.addComponent(this.btn_limpiar);
			return buttonContent;
		}

		private Component buildFormContent() {
					
			VerticalLayout formContent = new VerticalLayout();
			formContent.setSpacing(true	);
			Panel frmPanel = new Panel();
			frmPanel.setWidth("100%");
			frmPanel.setCaption("Datos a registrar");
			frmPanel.setContent(this.frm_partida);
			formContent.setMargin(true);
			formContent.addComponent(frmPanel);
			Panel gridPanel = new Panel();
			gridPanel.setWidth("100%");
			gridPanel.setCaption("Partidas Presupuestarias registradas");
			gridPanel.setContent(this.grid_partida);
			formContent.setMargin(true);
			formContent.addComponent(frmPanel);
			formContent.addComponent(gridPanel);
			Responsive.makeResponsive(formContent);
			return formContent;
		}

		private Component buildNavBar() {
			Panel navPanel = new Panel();
			navPanel.addStyleName("ait-content-nav");
			HorizontalLayout nav = new HorizontalLayout();
			nav.addComponent(new Label("Activos>>"));
			nav.addComponent(new Label("Parametros>>"));
			nav.addComponent(new Label("Partidas Presupuestarias>>"));
			nav.addComponent(new Label("<strong>Agregar</strong>", ContentMode.HTML));
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
				Label lbError = new Label(barMessage.getComponetName()+barMessage.getErrorName());
				lbError.setStyleName(barMessage.getType());
				this.hl_errores.addComponent(lbError);
			}
				
		}

		@Override
		public void buttonClick(ClickEvent event) {
			if (event.getButton() == this.btn_agregar) {
				if(this.frm_partida.validate()){
					this.partida_impl.add(this.frm_partida.getData());
					grid_partida.update();
					this.frm_partida.update();
					this.frm_partida.updateId();
					Notification.show(Messages.SUCCESS_MESSAGE);
				}else{
					Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
				}
				buildMessages(this.frm_partida.getMensajes());
				this.frm_partida.clearMessages();
			}
			if (event.getButton() == this.btn_limpiar) {
				this.frm_partida.update();
				this.frm_partida.updateId();
			}
		}
		

	}