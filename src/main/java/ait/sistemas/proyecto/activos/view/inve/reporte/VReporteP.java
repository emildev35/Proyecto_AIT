package ait.sistemas.proyecto.activos.view.inve.reporte;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.ReporteImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.report.msexcel.SimpleExcel;

import com.vaadin.cdi.CDIView;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@CDIView(value = VReporteP.ID)
public class VReporteP extends VerticalLayout implements View, ClickListener {
	
	public static final String ID = "/act/inv/reporte";
	private static final long serialVersionUID = 1L;
	
	FormReporte frm_reporte = new FormReporte();
	
	private CssLayout hl_errores = new CssLayout();;
	private Button btn_imprimir_word = new Button("Imprimir Word");
	private Button btn_imprimir_excel = new Button("Imprimir Excel");
	private Button btn_imprimir_pdf = new Button("Imprimir PDF");
	
	private ReporteImpl reporteimpl = new ReporteImpl();
	
	public VReporteP() {
		this.btn_imprimir_excel.addClickListener(this);
		this.btn_imprimir_word.addClickListener(this);
		this.btn_imprimir_pdf.addClickListener(this);
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}
	
	private Component buildFormContent() {
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel grid_column = new Panel();
		grid_column.setWidth("100%");
		grid_column.setCaption("Inmuebles registrados");
		formContent.setMargin(true);
		grid_column.setContent(frm_reporte);
		formContent.addComponent(grid_column);
		Responsive.makeResponsive(formContent);
		return formContent;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addStyleName("ait-buttons");
		
		this.btn_imprimir_excel.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_imprimir_excel);
		
		this.btn_imprimir_word.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_imprimir_word);
		
		this.btn_imprimir_pdf.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_imprimir_pdf);
		
		return buttonContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Inventario>>"));
		nav.addComponent(new Label("<strong>Reporte</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
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
		
		if (frm_reporte.validate()) {
			List<String> columns_header = this.frm_reporte.getColumnsNames();
			int[] columns_width = frm_reporte.getColumnssizes();
			String sql = frm_reporte.getSQL();
			String[][] data = reporteimpl.getData(sql, "Reporte_Activos", frm_reporte.getNumColumns());
			if (event.getButton() == this.btn_imprimir_word) {
				
				PdfReport pdf_reporte = new PdfReport();
				try {
					pdf_reporte.getPdf(data, columns_header, columns_width, this.frm_reporte.getTitle());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				File pdfFile = new File(PdfReport.SAVE_PATH);
				
				VerticalLayout vl_pdf = new VerticalLayout();
				Embedded pdf = new Embedded("", new FileResource(pdfFile));
				
				pdf.setMimeType("application/pdf");
				pdf.setType(Embedded.TYPE_BROWSER);
				pdf.setSizeFull();
				vl_pdf.setSizeFull();
				vl_pdf.addComponent(pdf);
				
				Window subWindow = new Window("Reporte Inventario Activos");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);
				subWindow.setContent(vl_pdf);
				
				subWindow.setWidth("90%");
				subWindow.setHeight("90%");
				subWindow.center();
				
				// Open it in the UI
				getUI().addWindow(subWindow);
				
			}
			if (event.getButton() == this.btn_imprimir_pdf) {
			}
			if (event.getButton() == this.btn_imprimir_excel) {
				SimpleExcel simpleExcel = new SimpleExcel();
				simpleExcel.save(data, columns_header, this.frm_reporte.getTitle());
				
			}
		}
		buildMessages(frm_reporte.getMensajes());
	}
	
}
