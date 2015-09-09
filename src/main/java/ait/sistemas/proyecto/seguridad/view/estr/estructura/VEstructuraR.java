package ait.sistemas.proyecto.seguridad.view.estr.estructura;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

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

public class VEstructuraR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_imprimir;
	private FormReporte frmReporte;
	private String[][] data;
	int r = 0;
	private final MenuImpl menuiml = new MenuImpl();
	
	public VEstructuraR() {
		
		this.frmReporte = new FormReporte();
		this.btn_imprimir = new Button("Imprimir");
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_imprimir);
		this.btn_imprimir.addStyleName("ait-buttons-btn");
		this.btn_imprimir.addClickListener(this);
		buttonContent.addStyleName("ait-buttons");
		
		Responsive.makeResponsive(buttonContent);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Formulario de Impresion");
		frmPanel.setContent(this.frmReporte);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Responsive.makeResponsive(formContent);
		return formContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Seguridad » "));
		nav.addComponent(new Label("Estructura del Sistema » "));
		nav.addComponent(new Label("Opcion » "));
		nav.addComponent(new Label("<strong>Agregar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	public void buildrow(List<Arbol_menus> lista, int nivel) {
		String preNombre = "";
		for (int i = 1; i < nivel; i++) {
			preNombre += "    ";
		}
		for (Arbol_menus menu : lista) {
			String[] row = { String.valueOf(menu.getAME_Id_Identificador()), String.valueOf(menu.getAME_Id_Menus()),
					preNombre + menu.getAME_Nombre(), String.valueOf(this.r), menu.getAME_Programa() };
			this.data[this.r] = row;
			this.r++;
			List<Arbol_menus> hijos = this.menuiml.getallMenu(menu.getAME_Id_Identificador());
			if (hijos.size() > 0) {
				buildrow(hijos, (nivel + 1));
			}
			
		}
	}
	
	public String[][] getData() {
		int rowNumber = this.menuiml.CountItemSubmenu((long) this.frmReporte.cbSusSistema.getValue());
		rowNumber = (rowNumber < 0) ? 0 : rowNumber;
		this.data = new String[rowNumber][5];
		this.r = 0;
		buildrow(this.menuiml.getallMenu((long) this.frmReporte.cbSusSistema.getValue()), 1);
		return data;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
		ReportPdf reporte = new ReportPdf();
		try {
			if (this.frmReporte.cbSusSistema.getValue() != null) {
				reporte.getPdf(getData(), this.frmReporte.cbSusSistema.getItemCaption(this.frmReporte.cbSusSistema.getValue()));
				this.r = 0;
				File pdfFile = new File(ReportPdf.SAVE_PATH);
				VerticalLayout vl_pdf = new VerticalLayout();
				Embedded pdf = new Embedded("", new FileResource(pdfFile));
				pdf.setMimeType("application/pdf");
				pdf.setType(Embedded.TYPE_BROWSER);
				pdf.setSizeFull();
				vl_pdf.setSizeFull();
				vl_pdf.addComponent(pdf);
				
				Window subWindow = new Window("Reporte Kardex");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);
				subWindow.setContent(vl_pdf);
				
				subWindow.setWidth("90%");
				subWindow.setHeight("90%");
				subWindow.center();
				
				// Open it in the UI
				getUI().addWindow(subWindow);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
