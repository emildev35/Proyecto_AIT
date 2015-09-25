package ait.sistemas.proyecto.activos.view.inve.reporte;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ait.sistemas.proyecto.activos.data.service.Impl.ReporteImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.report.msexcel.SimpleExcel;
import ait.sistemas.proyecto.common.report.msword.SimpleWord;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VReporteVariosR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	FormReporte frm_reporte = new FormReporte();
	
	private CssLayout hl_errores = new CssLayout();;
	private Button btn_imprimir_word = new Button("Imprimir Word");
	private Button btn_imprimir_excel = new Button("Imprimir Excel");
	private Button btn_imprimir_pdf = new Button("Imprimir PDF");
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	private ReporteImpl reporteimpl = new ReporteImpl();
	
	public VReporteVariosR() {
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
		grid_column.setStyleName(AitTheme.PANEL_PRINT);
		grid_column.setIcon(FontAwesome.PRINT);
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
		GridLayout gridButtons = new GridLayout(3, 1);
		gridButtons.setWidth("100%");
		
		Responsive.makeResponsive(gridButtons);
		Responsive.makeResponsive(buttonContent);
		
		buttonContent.addStyleName("ait-buttons");
		
		this.btn_imprimir_excel.setStyleName(AitTheme.BTN_EXCEL);
		this.btn_imprimir_excel.setIcon(FontAwesome.FILE_EXCEL_O);
		gridButtons.addComponent(this.btn_imprimir_excel);
		
		this.btn_imprimir_word.setStyleName(AitTheme.BTN_WORD);
		this.btn_imprimir_word.setIcon(FontAwesome.FILE_WORD_O);
		gridButtons.addComponent(this.btn_imprimir_word);
		
		this.btn_imprimir_pdf.setStyleName(AitTheme.BTN_PDF);
		this.btn_imprimir_pdf.setIcon(FontAwesome.FILE_PDF_O);
		gridButtons.addComponent(this.btn_imprimir_pdf);
		buttonContent.addComponent(gridButtons);
		return buttonContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label(AitView.getNavText(menu), ContentMode.HTML));
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
			String sql = frm_reporte.getSQL();
			int[] columns_width = frm_reporte.getColumnssizes();
			String[][] data = reporteimpl.getData(sql, "Reporte_Activos", frm_reporte.getNumColumns(),
					frm_reporte.getDependencia());
			if (event.getButton() == this.btn_imprimir_pdf) {
				
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
			if (event.getButton() == this.btn_imprimir_word) {
				
				SimpleWord simpleWord = new SimpleWord();
				simpleWord.save(data, columns_header, this.frm_reporte.getTitle());
				
				File pdfFile = new File(simpleWord.SAVE_PATH);
				
				VerticalLayout vl_pdf = new VerticalLayout();
				Embedded pdf = new Embedded("", new FileResource(pdfFile));
				
				pdf.setMimeType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
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
			if (event.getButton() == this.btn_imprimir_excel) {
				SimpleExcel simpleExcel = new SimpleExcel();
				simpleExcel.save(data, columns_header, this.frm_reporte.getTitle());
				
				File pdfFile = new File(simpleExcel.SAVE_PATH);
				
				VerticalLayout vl_pdf = new VerticalLayout();
				Embedded pdf = new Embedded("", new FileResource(pdfFile));
				
				pdf.setMimeType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
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
		}
		buildMessages(frm_reporte.getMensajes());
	}
	
}
