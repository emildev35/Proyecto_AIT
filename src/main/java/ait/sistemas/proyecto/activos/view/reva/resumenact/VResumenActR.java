package ait.sistemas.proyecto.activos.view.reva.resumenact;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActualizacionImpl;
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

public class VResumenActR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_imprimir;
	private Button btn_excel = new Button("Exportar Excel");
	private Button btnSalir = new Button("Salir");
	private FormResumenact frmReporte = new FormResumenact();
	int r = 0;
	private final ActualizacionImpl actualizacion_impl = new ActualizacionImpl();
	private CssLayout hl_errores = new CssLayout();
	
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	public VResumenActR() {
		
		this.btn_imprimir = new Button("Imprimir Actualizacion");
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(btn_imprimir);
		buttonContent.addComponent(btn_excel);
		buttonContent.addComponent(btnSalir);
		
		btn_imprimir.addStyleName(AitTheme.BTN_PRINT);
		btnSalir.addStyleName(AitTheme.BTN_EXIT);
		btn_imprimir.setIcon(FontAwesome.PRINT);
		btnSalir.setIcon(FontAwesome.UNDO);
		btn_excel.setIcon(FontAwesome.FILE_EXCEL_O);
		btn_excel.setStyleName(AitTheme.BTN_EXCEL);
		
		btn_imprimir.addClickListener(this);
		btnSalir.addClickListener(this);
		btn_excel.addClickListener(this);
		
		buttonContent.addStyleName(AitTheme.BUTTONS_BAR);
		Responsive.makeResponsive(buttonContent);
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
	
	public String[][] getDatos(short idDependencia, String fechaElaboracion) {
		List<ActivosModel> activos = actualizacion_impl.getResumenActualizacion(idDependencia, fechaElaboracion);
		
		if (idDependencia == FormResumenact.ALL) {
			List<ActivosModel> general = actualizacion_impl.getResumenGeneralActualizacion(fechaElaboracion);
			
			String[][] result = new String[(activos.size() + general.size())][11];
			for (int i = 0; i < activos.size(); i++) {
				result[i][0] = String.valueOf(activos.get(i).getACT_Dependencia());
				result[i][1] = String.valueOf(activos.get(i).getACT_Grupo_Contable());
				result[i][2] = String.valueOf(activos.get(i).getACT_Codigo_Activo());
				result[i][3] = String.valueOf(activos.get(i).getACT_Valor_Compra());
				result[i][4] = String.valueOf(activos.get(i).getACT_Actualizacion_Acumulada_Gestion_Anterior());
				result[i][5] = String.valueOf(activos.get(i).getACT_Depreciacion_Acumulada_Gestion_Anterior());
				result[i][6] = String.valueOf(activos.get(i).getACT_Actualizacion_Gestion_Actual());
				result[i][7] = String.valueOf(activos.get(i).getACT_Depreciacion_Gestion_Actual());
				result[i][8] = String.valueOf(activos.get(i).getACT_CA());
				result[i][9] = String.valueOf(activos.get(i).getACT_DAA());
				result[i][10] = String.valueOf(activos.get(i).getACT_Valor_Neto());
			}
			
			for (int i = activos.size(); i < result.length; i++) {
				result[i][0] = String.valueOf(general.get(i - activos.size()).getACT_Dependencia());
				result[i][1] = String.valueOf(general.get(i - activos.size()).getACT_Grupo_Contable());
				result[i][2] = String.valueOf(general.get(i - activos.size()).getACT_Codigo_Activo());
				result[i][3] = String.valueOf(general.get(i - activos.size()).getACT_Valor_Compra());
				result[i][4] = String.valueOf(general.get(i - activos.size()).getACT_Actualizacion_Acumulada_Gestion_Anterior());
				result[i][5] = String.valueOf(general.get(i - activos.size()).getACT_Depreciacion_Acumulada_Gestion_Anterior());
				result[i][6] = String.valueOf(general.get(i - activos.size()).getACT_Actualizacion_Gestion_Actual());
				result[i][7] = String.valueOf(general.get(i - activos.size()).getACT_Depreciacion_Gestion_Actual());
				result[i][8] = String.valueOf(general.get(i - activos.size()).getACT_CA());
				result[i][9] = String.valueOf(general.get(i - activos.size()).getACT_DAA());
				result[i][10] = String.valueOf(general.get(i - activos.size()).getACT_Valor_Neto());
			}
			return result;
			
		} else {
			
			String[][] result = new String[activos.size()][11];
			
			for (int i = 0; i < activos.size(); i++) {
				result[i][0] = String.valueOf(activos.get(i).getACT_Dependencia());
				result[i][1] = String.valueOf(activos.get(i).getACT_Grupo_Contable());
				result[i][2] = String.valueOf(activos.get(i).getACT_Codigo_Activo());
				result[i][3] = String.valueOf(activos.get(i).getACT_Valor_Compra());
				result[i][4] = String.valueOf(activos.get(i).getACT_Actualizacion_Acumulada_Gestion_Anterior());
				result[i][5] = String.valueOf(activos.get(i).getACT_Depreciacion_Acumulada_Gestion_Anterior());
				result[i][6] = String.valueOf(activos.get(i).getACT_Actualizacion_Gestion_Actual());
				result[i][7] = String.valueOf(activos.get(i).getACT_Depreciacion_Gestion_Actual());
				result[i][8] = String.valueOf(activos.get(i).getACT_CA());
				result[i][9] = String.valueOf(activos.get(i).getACT_DAA());
				result[i][10] = String.valueOf(activos.get(i).getACT_Valor_Neto());
			}
			return result;
		}
		
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
		
		if (event.getButton() == btnSalir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		} else if (event.getButton() == btn_excel) {
			List<String> columnsHeaders = new ArrayList<String>();
			columnsHeaders.add("Dependencia");
			columnsHeaders.add("Grupo Contable");
			columnsHeaders.add("Cantidad");
			columnsHeaders.add("Valor de Compra");
			columnsHeaders.add("Valor Actualizado Gestion Anterior");
			columnsHeaders.add("Depreciacion Actualizada Gestion Anterior");
			columnsHeaders.add("Actualizacion Gestion Actual");
			columnsHeaders.add("Depreciacion Gestion Actual");
			columnsHeaders.add("Actualizacion Acumulada");
			columnsHeaders.add("Depreciacion Acumulada");
			columnsHeaders.add("Valor Neto");
			SimpleExcel simpleExcel = new SimpleExcel();
			simpleExcel.save(getDatos(
				(short) frmReporte.cb_Dependencia.getValue(),
				new SimpleDateFormat("yyyy-MM-dd").format(frmReporte.dtf_fecha_ultima_depre.getValue()).toString() + "T00:00:00"),
				columnsHeaders,
				"Resumen Actualizacion de Activos"
			);
			
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
		} else {
			if (this.frmReporte.validate()) {
				ReportPdf reporte = new ReportPdf();
				try {
					reporte.getPdf(
							getDatos((short) frmReporte.cb_Dependencia.getValue(),
									new SimpleDateFormat("yyyy-MM-dd").format(frmReporte.dtf_fecha_ultima_depre.getValue())
											+ "T00:00:00"),
							new SimpleDateFormat("dd-MM-yyyy").format(frmReporte.dtf_fecha_ultima_depre.getValue()));
					File pdfFile = new File(ReportPdf.SAVE_PATH);
					
					VerticalLayout vl_pdf = new VerticalLayout();
					Embedded pdf = new Embedded("", new FileResource(pdfFile));
					
					pdf.setMimeType("application/pdf");
					pdf.setType(Embedded.TYPE_BROWSER);
					pdf.setSizeFull();
					vl_pdf.setSizeFull();
					vl_pdf.addComponent(pdf);
					
					Window subWindow = new Window("Reporte Resumen Actualizacion");
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
			buildMessages(this.frmReporte.getMessage());
			
		}
	}
	
}
