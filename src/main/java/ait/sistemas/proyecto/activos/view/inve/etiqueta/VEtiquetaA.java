package ait.sistemas.proyecto.activos.view.inve.etiqueta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.CodeBar;

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

/**
 * Vista de Impresion de Etiquetas
 * @author franzemil
 *
 */
public class VEtiquetaA extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;

	private Button btn_imprimir = new Button("Imprimir");
	private Button btn_salir = new Button("Salir");
	
	private FormEtiqueta frmEtiqueta = new FormEtiqueta();
	
	int r = 0;
	private CssLayout hl_errores = new CssLayout();

	public VEtiquetaA() {


		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());

	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addStyleName("ait-buttons");
		this.btn_imprimir.addStyleName("ait-buttons-btn");
		this.btn_imprimir.addClickListener(this);
		this.btn_salir.addStyleName("ait-buttons-btn");
		this.btn_salir.addClickListener(this);
		buttonContent.addComponent(this.btn_imprimir);
		buttonContent.addComponent(this.btn_salir);
		Responsive.makeResponsive(buttonContent);
		return buttonContent;
	}

	private Component buildFormContent() {
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel frmPanel = new Panel();
		frmPanel.setWidth("100%");
		frmPanel.setCaption("Formulario de Impresion");
		frmPanel.setContent(this.frmEtiqueta);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		
		
		Panel frmgrid = new Panel();
		frmgrid.setWidth("100%");
		frmgrid.setCaption("SELECCIONE LOS ACTIVOS PARA LA IMPRESION DE LAS ETIQUETAS");
		frmgrid.setContent(this.frmEtiqueta.getGrid());
		formContent.addComponent(frmgrid);
		
		Responsive.makeResponsive(formContent);
		return formContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Inventarios » "));
		nav.addComponent(new Label("Etiquetas» "));
		nav.addComponent(new Label("<strong>Generar</strong>", ContentMode.HTML));
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

	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
		if (this.frmEtiqueta.validate()) {
			
			List<CodeBar> etiquetas = frmEtiqueta.getActivos();
			
			EtiquetaPdf reporte = new EtiquetaPdf();
			try {
				reporte.getPdf(etiquetas);
				File pdfFile = new File(EtiquetaPdf.SAVE_PATH);

				VerticalLayout vl_pdf = new VerticalLayout();
				Embedded pdf = new Embedded("", new FileResource(pdfFile));

				pdf.setMimeType("application/pdf");
				pdf.setType(Embedded.TYPE_BROWSER);
				pdf.setSizeFull();
				vl_pdf.setSizeFull();
				vl_pdf.addComponent(pdf);

				Window subWindow = new Window("ETIQUETAS DE ACTIVOS");
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
		buildMessages(this.frmEtiqueta.getMessage());
	}
}
