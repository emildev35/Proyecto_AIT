package ait.sistemas.proyecto.activos.view.reva.resumenact;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.ActualizacionImpl;
import ait.sistemas.proyecto.common.component.BarMessage;

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
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@CDIView(value = VReporteResumenAct.ID)
public class VReporteResumenAct extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;
	public static final String ID = "/act/reva/resumenact/r";

	private Button btn_imprimir;
	private FormReporte frmReporte = new FormReporte();
	int r = 0;
	private String[][] data;
	private final ActivoImpl activo_impl = new ActivoImpl();
	private final ActualizacionImpl actualizacion_impl = new ActualizacionImpl();
	private CssLayout hl_errores = new CssLayout();

	public VReporteResumenAct() {

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
		nav.addComponent(new Label("Resumen Actualizacion y Depreciacion de Activos » "));
		nav.addComponent(new Label("<strong>Reporte</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	public String[][] getData() {

		List<ActivosModel> lista = activo_impl
				.activos_by_dependencia((Short) this.frmReporte.cb_Dependencia.getValue());

		this.data = new String[lista.size()][2];
		this.r = 0;
		for (ActivosModel activo : lista) {
			String[] row = { activo.getACT_Grupo_Contable(), activo.getACT_Auxiliar_Contable() };
			this.data[r] = row;
			this.r++;
		}
		return data;
	}

	public String[][] getDatos() {

		List<ActivosModel> lista = actualizacion_impl
				.getResumenActualizacion((Short) this.frmReporte.cb_Dependencia.getValue());

		String[][] data = new String[lista.size()][5];
		r = 0;
		for (ActivosModel activo : lista) {
			String[] row = { 
					activo.getACT_Dependencia(), 
					" ", 
					" ", 
					activo.getACT_Dependencia_Codigo_Activo(), 
					activo.getACT_Grupo_Contable(),
					String.valueOf(activo.getACT_Codigo_Activo()) ,
					String.valueOf(activo.getACT_Valor()), 
					String.valueOf(activo.getACT_Vida_Util()), 
					String.valueOf(activo.getACT_Actualizacion_Acumulada_Gestion_Anterior()), 
					String.valueOf(activo.getACT_Depreciacion_Acumulada_Gestion_Anterior()),
					String.valueOf(activo.getACT_Actualizacion_Acumulada()),
					String.valueOf(activo.getACT_Depresiacion_Acumulada()),
					String.valueOf(activo.getACT_CA()),
					String.valueOf(activo.getACT_DAA()),
					String.valueOf(activo.getACT_Valor_Neto())
					};
			
			data[r] = row;
			r++;
		}
		return data;
	}
//	public String[][] getDatosALL() {
//		
//		List<ActivosModel> lista = activo_impl.getactivos();
//		
//		String[][] data = new String[lista.size()][5];
//		r = 0;
//		for (ActivosModel activo : lista) {
//			String[] row = { activo.getACT_Dependencia(), activo.getACT_Grupo_Contable(), activo.getACT_Auxiliar_Contable(), activo.getACT_Codigo_Activo(), activo.getACT_No_Serie(), activo.getACT_Nombre_Activo(),
//					String.valueOf(activo.getACT_Valor()), String.valueOf(activo.getACT_Valor_Neto()) };
//			
//			data[r] = row;
//			r++;
//		}
//		return data;
//	}

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
//				short a =0;
//				if ( (Short)this.frmReporte.cb_Dependencia.getValue() == a) {
//					// int [][] datas = activo_impl.getProvedoreCuidad();
//					 reporte.getPdf(getDatosALL(),
//								this.frmReporte.cb_Dependencia.getItemCaption(this.frmReporte.cb_Dependencia.getValue()));
//				} else {
					reporte.getPdf(getDatos(),
							this.frmReporte.cb_Dependencia.getItemCaption(this.frmReporte.cb_Dependencia.getValue()));
//				}
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

				// Open it in the UI
				getUI().addWindow(subWindow);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		buildMessages(this.frmReporte.getMessage());
	}
}
