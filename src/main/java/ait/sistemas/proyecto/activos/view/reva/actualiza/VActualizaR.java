package ait.sistemas.proyecto.activos.view.reva.actualiza;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActualizacionImpl;
import ait.sistemas.proyecto.activos.view.reva.actualiza.reporte.FormReporte;
import ait.sistemas.proyecto.activos.view.reva.actualiza.reporte.ReportPdf;
import ait.sistemas.proyecto.common.component.BarMessage;

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

public class VActualizaR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_imprimir;
	private FormReporte frmReporte = new FormReporte();
	int r = 0;
	
	private final ActualizacionImpl actualizacion_impl = new ActualizacionImpl();
	private CssLayout hl_errores = new CssLayout();
	
	public VActualizaR() {
		
		this.btn_imprimir = new Button("Imprimir Actualizacion");
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
		formContent.setMargin(true);
		formContent.addComponent(frmReporte);
		Responsive.makeResponsive(formContent);
		return formContent;
	}
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Revalorizacion Depreciacion » "));
		nav.addComponent(new Label("Actualizacion » "));
		nav.addComponent(new Label("<strong>Reporte</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	
	public String[][] getDatos() {
		
		List<ActivosModel> lista = actualizacion_impl.getActualizacion((Short) this.frmReporte.cb_Dependencia.getValue());
		
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
			String[] row = { 
					activo.getACT_Dependencia(), 
					activo.getACT_Grupo_Contable(),
					activo.getACT_Auxiliar_Contable(),
					activo.getACT_Codigo_Activo(), 
					activo.getACT_Nombre_Activo(), 
					String.valueOf(activo.getACT_Fecha_Compra()),
					valor_str_compra, 
					String.valueOf(activo.getACT_Vida_Util()), 
					str_acrualizacion_GAn, 
					str_depreciacion_GAn,
					str_acrualizacion_GA, 
					str_depreciacion_GA, str_CA, str_DAA, valor_str };
			
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
		if (this.frmReporte.validate()) {
			ReportPdf reporte = new ReportPdf();
			try {
				// short a =0;
				// if ( (Short)this.frmReporte.cb_Dependencia.getValue() == a) {
				// // int [][] datas = activo_impl.getProvedoreCuidad();
				// reporte.getPdf(getDatosALL(),
				// this.frmReporte.cb_Dependencia.getItemCaption(this.frmReporte.cb_Dependencia.getValue()));
				// } else {
				reporte.getPdf(getDatos(),
						this.frmReporte.cb_Dependencia.getItemCaption(this.frmReporte.cb_Dependencia.getValue()));
				// }
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
}
