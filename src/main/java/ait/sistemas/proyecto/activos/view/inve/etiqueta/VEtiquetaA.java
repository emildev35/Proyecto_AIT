package ait.sistemas.proyecto.activos.view.inve.etiqueta;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.view.inve.inventario.FormReporte;
import ait.sistemas.proyecto.activos.view.inve.inventario.ReportPdf;
import ait.sistemas.proyecto.common.component.BarMessage;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class VEtiquetaA extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;

	private Button btn_imprimir;
	private Button btn_salir;
	
	private FormEtiqueta frmEtiqueta = new FormEtiqueta();
	
	int r = 0;
	private String[][] data;
	private final ActivoImpl activo_impl = new ActivoImpl();
	private CssLayout hl_errores = new CssLayout();

	public VEtiquetaA() {

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
		frmPanel.setContent(this.frmEtiqueta);
		formContent.setMargin(true);
		formContent.addComponent(frmPanel);
		Responsive.makeResponsive(formContent);
		return formContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Inventarios » "));
		nav.addComponent(new Label("Inventario de Activos » "));
		nav.addComponent(new Label("<strong>Reporte</strong>", ContentMode.HTML));
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
//			ReportPdf reporte = new ReportPdf();
//			try {
//				short a =0;
//				if ( (Short)this.frmEtiqueta.cb_Dependencia.getValue() == a) {
//					// int [][] datas = activo_impl.getProvedoreCuidad();
//					 reporte.getPdf(getDatosALL(),
//								this.frmEtiqueta.cb_Dependencia.getItemCaption(this.frmEtiqueta.cb_Dependencia.getValue()));
//				} else {
//					reporte.getPdf(getDatos(),
//							this.frmEtiqueta.cb_Dependencia.getItemCaption(this.frmEtiqueta.cb_Dependencia.getValue()));
//				}
//				File pdfFile = new File(ReportPdf.SAVE_PATH);
//
//				VerticalLayout vl_pdf = new VerticalLayout();
//				Embedded pdf = new Embedded("", new FileResource(pdfFile));
//
//				pdf.setMimeType("application/pdf");
//				pdf.setType(Embedded.TYPE_BROWSER);
//				pdf.setSizeFull();
//				vl_pdf.setSizeFull();
//				vl_pdf.addComponent(pdf);
//
//				Window subWindow = new Window("Reporte Inventario Activos");
//				VerticalLayout subContent = new VerticalLayout();
//				subContent.setMargin(true);
//				subWindow.setContent(vl_pdf);
//
//				subWindow.setWidth("90%");
//				subWindow.setHeight("90%");
//				subWindow.center();
//
//				// Open it in the UI
//				getUI().addWindow(subWindow);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		buildMessages(this.frmEtiqueta.getMessage());
	}
}
