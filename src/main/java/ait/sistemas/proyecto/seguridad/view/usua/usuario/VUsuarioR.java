package ait.sistemas.proyecto.seguridad.view.usua.usuario;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ait.sistemas.proyecto.seguridad.component.model.UsuarioGridModel;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;
import ait.sistemas.proyecto.seguridad.view.usua.usuario.reporte.PdfUsuario;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Responsive;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VUsuarioR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_submit = new Button("Imprimir");
	private Button btn_limpiar = new Button("Salir");
	
	private FormUsuario frmUsuario;
	private UsuarioImpl usuarioimpl = new UsuarioImpl();
	
	public VUsuarioR() {
		addComponent(buildNavBar());
		addComponent(buildButtonBar());
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Seguridad » "));
		nav.addComponent(new Label("Usuarios » "));
		nav.addComponent(new Label("<strong>Reporte de Usuarios</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_submit);
		this.btn_submit.addStyleName("ait-buttons-btn");
		this.btn_submit.addClickListener(this);
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
		this.btn_limpiar.addStyleName("ait-buttons-btn");
		this.btn_limpiar.addClickListener(this);
		Responsive.makeResponsive(buttonContent);
		return buttonContent;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {
			
			PdfUsuario reporte = new PdfUsuario();
			List<UsuarioGridModel> usuarios = usuarioimpl.getGridData();
			String[][] data = new String[usuarios.size()][5];
			int r = 0;
			for (UsuarioGridModel usuario : usuarioimpl.getGridData()) {
				data[r][0] = usuario.getDependencia();
				data[r][1] = usuario.getUnidad_organizacional();
				data[r][2] = usuario.getId();
				data[r][3] = usuario.getCI();
				data[r][4] = usuario.getFullName();
				r++;
			}
			
			try {
				reporte.getPdf(data);
				File pdfFile = new File(PdfUsuario.SAVE_PATH);
				
				VerticalLayout vl_pdf = new VerticalLayout();
				Embedded pdf = new Embedded("", new FileResource(pdfFile));
				pdf.setMimeType("application/pdf");
				pdf.setType(Embedded.TYPE_BROWSER);
				pdf.setSizeFull();
				vl_pdf.setSizeFull();
				vl_pdf.addComponent(pdf);
				
				Window subWindow = new Window("Reporte  de Usuarios del Sistema");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);
				subWindow.setContent(vl_pdf);
				
				subWindow.setWidth("90%");
				subWindow.setHeight("90%");
				subWindow.center();
				
				// Open it in the UI
				getUI().addWindow(subWindow);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
		if (event.getButton() == this.btn_limpiar) {
			this.frmUsuario.clear();
		}
		
	}
}
