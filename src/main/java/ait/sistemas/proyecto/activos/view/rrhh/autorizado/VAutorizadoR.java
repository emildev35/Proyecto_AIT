package ait.sistemas.proyecto.activos.view.rrhh.autorizado;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.TipoAutorizacionModel;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoAutorizacionImpl;
import ait.sistemas.proyecto.activos.view.rrhh.autorizado.reporte.ReportPdf;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.common.view.HomeView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VAutorizadoR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private CssLayout hl_errores = new CssLayout();
	private Button btn_limpiar = new Button("Salir");
	private Button btn_imprimir = new Button("Imprimir");
	private final TipoAutorizacionImpl tipo_autorizacionimpl = new TipoAutorizacionImpl();
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	public VAutorizadoR() {
		this.btn_imprimir.addClickListener(this);
		this.btn_limpiar.addClickListener(this);
		this.hl_errores = new CssLayout();
		mensajes.add(new BarMessage("Formulario", Messages.PRESS_PRINT_BTN));
		buildMessages(this.mensajes);
		addComponent(buildNavBar());
		addComponent(buildButtonBar());
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_imprimir.setStyleName(AitTheme.BTN_PRINT);
		this.btn_imprimir.setIcon(FontAwesome.PRINT);
		buttonContent.addComponent(this.btn_imprimir);
		
		this.btn_limpiar.setStyleName(AitTheme.BTN_EXIT);
		this.btn_limpiar.setIcon(FontAwesome.UNDO);
		buttonContent.addComponent(this.btn_limpiar);
		
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		return buttonContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label(AitView.getNavText(menu), ContentMode.HTML));
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
	
	public String[][] getData() {
		List<TipoAutorizacionModel> autorizaciones = tipo_autorizacionimpl.getall();
		String[][] result = new String[autorizaciones.size()][6];
		int r = 0;
		for (TipoAutorizacionModel tipoAutorizacionModel : autorizaciones) {
			String[] row = { tipoAutorizacionModel.getDependencia(), tipoAutorizacionModel.getTipo_movimiento(),
					tipoAutorizacionModel.getUnidadOrganizacional(), 
					tipoAutorizacionModel.getDependencia_transferencia(),
					String.valueOf(tipoAutorizacionModel.getOrden()),
					tipoAutorizacionModel.getServidor_publico(), tipoAutorizacionModel.getNivel_autorizacion() };
			result[r] = row;
			r++;
		}
		return result;
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_imprimir) {
			ReportPdf reporte = new ReportPdf();
			try {
				
				reporte.getPdf(getData(), "", "");
				
				File pdfFile = new File(reporte.SAVE_PATH);
				
				VerticalLayout vl_pdf = new VerticalLayout();
				Embedded pdf = new Embedded("", new FileResource(pdfFile));
				
				pdf.setMimeType("application/pdf");
				pdf.setType(Embedded.TYPE_BROWSER);
				pdf.setSizeFull();
				vl_pdf.setSizeFull();
				vl_pdf.addComponent(pdf);
				
				Window subWindow = new Window("Reporte de Tipos de Autorizacion");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);
				subWindow.setContent(vl_pdf);
				
				subWindow.setWidth("90%");
				subWindow.setHeight("90%");
				subWindow.center();
				
				getUI().addWindow(subWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		if (event.getButton() == this.btn_limpiar) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}
	
}
