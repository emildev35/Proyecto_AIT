package ait.sistemas.proyecto.activos.view.reva.actualiza;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActualizacionImpl;
import ait.sistemas.proyecto.activos.view.reva.actualiza.reporte.FormReporte;
import ait.sistemas.proyecto.activos.view.reva.actualiza.reporte.ReportPdf;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.report.msexcel.SimpleExcel;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.common.view.HomeView;
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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VActualizaR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_imprimir;
	private Button btn_excel = new Button("Exportar Excel");
	private Button btn_salir = new Button("Salir");
	private FormReporte frmReporte = new FormReporte();
	int r = 0;
	
	private final ActualizacionImpl actualizacion_impl = new ActualizacionImpl();
	private CssLayout hl_errores = new CssLayout();
	
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	public VActualizaR() {
		
		this.btn_imprimir = new Button("Imprimir Actualizacion");
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		
		btn_imprimir.addClickListener(this);
		btn_salir.addClickListener(this);
		btn_excel.addClickListener(this);
		
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_imprimir.setStyleName(AitTheme.BTN_PRINT);
		btn_imprimir.setIcon(FontAwesome.PRINT);
		btn_salir.setStyleName(AitTheme.BTN_EXIT);
		btn_salir.setIcon(FontAwesome.UNDO);
		btn_excel.setIcon(FontAwesome.FILE_EXCEL_O);
		btn_excel.setStyleName(AitTheme.BTN_EXCEL);
		buttonContent.addComponent(btn_imprimir);
		buttonContent.addComponent(btn_excel);
		buttonContent.addComponent(btn_salir);
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		formContent.setMargin(true);
		formContent.addComponent(frmReporte);
		Responsive.makeResponsive(formContent);
		return formContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label(AitView.getNavText(menu), ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	public String[][] getDatos() {
		List<ActivosModel> lista = new ArrayList<ActivosModel>();
		if ((Short) this.frmReporte.cb_Dependencia.getValue() == 0) {
			lista = actualizacion_impl.getActualizacion((short) 0,
					new SimpleDateFormat("yyyy-dd-MM").format(frmReporte.dtf_fecha_ultima_depre.getValue()));
		} else {
			lista = actualizacion_impl.getActualizacion((short) this.frmReporte.cb_Dependencia.getValue(), new SimpleDateFormat(
					"yyyy-MM-ddT00:00:00").format(frmReporte.dtf_fecha_ultima_depre.getValue()));
		}
		String[][] data = new String[lista.size()][5];
		r = 0;
		for (ActivosModel activo : lista) {
			
			double d_valor = activo.getACT_Valor_Neto() == null ? 0 : activo.getACT_Valor_Neto().doubleValue();
			String valor_str = String.valueOf(d_valor);
			
			double d_valor_compra = activo.getACT_Valor_Compra() == null ? 0 : activo.getACT_Valor_Compra().doubleValue();
			String valor_str_compra = String.valueOf(d_valor_compra);
			
			double valor_actualizado_GAn = activo.getACT_Actualizacion_Acumulada_Gestion_Anterior() == null ? 0 : activo
					.getACT_Actualizacion_Acumulada_Gestion_Anterior().doubleValue();
			String str_acrualizacion_GAn = String.valueOf(valor_actualizado_GAn);
			
			double depreciacion_GAn = activo.getACT_Depreciacion_Acumulada_Gestion_Anterior() == null ? 0 : activo
					.getACT_Depreciacion_Acumulada_Gestion_Anterior().doubleValue();
			String str_depreciacion_GAn = String.valueOf(depreciacion_GAn);
			
			double valor_actualizado_GA = activo.getACT_Actualizacion_Acumulada() == null ? 0 : activo
					.getACT_Actualizacion_Acumulada().doubleValue();
			String str_acrualizacion_GA = String.valueOf(valor_actualizado_GA);
			
			double depreciacion_GA = activo.getACT_Depresiacion_Acumulada() == null ? 0 : activo.getACT_Depresiacion_Acumulada()
					.doubleValue();
			String str_depreciacion_GA = String.valueOf(depreciacion_GA);
			
			double valor_CA = activo.getACT_CA() == null ? 0 : activo.getACT_CA().doubleValue();
			String str_CA = String.valueOf(valor_CA);
			
			double valor_DAA = activo.getACT_DAA() == null ? 0 : activo.getACT_DAA().doubleValue();
			String str_DAA = String.valueOf(valor_DAA);
			String[] row = { activo.getACT_Dependencia(), 
					activo.getACT_Grupo_Contable(),
					activo.getACT_Auxiliar_Contable(),
					activo.getACT_Codigo_Activo(),
					activo.getACT_Nombre_Activo(),
					String.valueOf(activo.getACT_Vida_Util()),
					activo.getACT_Fecha_Compra().toString(), 
					valor_str_compra,
					str_acrualizacion_GAn,
					str_depreciacion_GAn,
					str_acrualizacion_GA, 
					str_depreciacion_GA, 
					str_CA,
					str_DAA,
					valor_str };
			
			data[r] = row;
			r++;
		}
		return data;
	}
	
	private void buildMessages(List<BarMessage> mensages) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		
		for (BarMessage barMessage : mensages) {
			Label lbError = new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName());
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == btn_excel) {
						List<String> columnHeaders = new ArrayList<String>();
						columnHeaders.add("Dependencia");
						columnHeaders.add("Grupo Contable");
						columnHeaders.add("Auxiliar Contable");
						columnHeaders.add("Codigo");
						columnHeaders.add("Nombre del Activo");
						columnHeaders.add("Vida Util");
						columnHeaders.add("Fecha Compra");
						columnHeaders.add("Valor Compra");
						columnHeaders.add("Valor Actualizado Gestion Anterior");
						columnHeaders.add("Depreciacion Actualizada Gestion Anterior");
						columnHeaders.add("Actualizacion Gestion Actual");
						columnHeaders.add("Depreciacion Gestion Actual");
						columnHeaders.add("Actualizacion Acumulada");
						columnHeaders.add("Depreciacion Acumulada");
						columnHeaders.add("Valor Neto");
			SimpleExcel simpleExcel = new SimpleExcel();
			simpleExcel.save(getDatos(), columnHeaders, "Reporte de Actualizacion de Activos");
			
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
			
		} else if (event.getButton() == btn_imprimir) {
			
			if (event.getButton() == btn_salir) {
				UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
			} else {
				if (this.frmReporte.validate()) {
					ReportPdf reporte = new ReportPdf();
					try {
						reporte.getPdf(getDatos(),
								new SimpleDateFormat("yyyy-MM-dd").format(this.frmReporte.dtf_fecha_ultima_depre.getValue()));
						File pdfFile = new File(ReportPdf.SAVE_PATH);
						
						VerticalLayout vl_pdf = new VerticalLayout();
						Embedded pdf = new Embedded("", new FileResource(pdfFile));
						
						pdf.setMimeType("application/pdf");
						pdf.setType(Embedded.TYPE_BROWSER);
						pdf.setSizeFull();
						vl_pdf.setSizeFull();
						vl_pdf.addComponent(pdf);
						
						Window subWindow = new Window("Reporte Actualizacion");
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
				buildMessages(this.frmReporte.getMessage());
			}
		} else if (event.getButton() == btn_salir) {
			
		}
	}
}
