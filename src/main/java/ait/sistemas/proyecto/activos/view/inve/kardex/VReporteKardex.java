package ait.sistemas.proyecto.activos.view.inve.kardex;

	import java.math.BigDecimal;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

	@CDIView(value = VReporteKardex.ID)
	public class VReporteKardex extends VerticalLayout implements View, ClickListener {

		private static final long serialVersionUID = 1L;
		public static final String ID = "/act/inve/kardex/reporte";

		private Button btn_imprimir;
		private FormReporte frmReporte = new FormReporte();
		private String[][] data;
		int r = 0;
		private final ActivoImpl activo_impl = new ActivoImpl();
		private CssLayout hl_errores = new CssLayout();
		public VReporteKardex() {

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
			nav.addComponent(new Label("Activos » "));
			nav.addComponent(new Label("Inventarios » "));
			nav.addComponent(new Label("Kardex » "));
			nav.addComponent(new Label("<strong>Reporte</strong>", ContentMode.HTML));
			navPanel.setContent(nav);
			return navPanel;
		}

		@Override
		public void enter(ViewChangeEvent event) {
			// TODO Auto-generated method stub

		}
		public String[][] getData() {
			
			List<ActivosModel> lista = activo_impl.getall((BigDecimal) this.frmReporte.cb_Activos.getValue());
			
			this.data = new String[lista.size()][6];
			this.r = 0;
			for (ActivosModel activos : lista) {
				String fullname_empelado = String.format("%s %s %s",
						activos.getACT_Nombre_Empleado(),
						activos.getACT_APaterno_Empleado(),
						activos.getACT_AMaterno_Empleado()
						);
				String[] row = { 
						activos.getACT_Codigo_Activo(), 
						activos.getACT_Nombre_Activo(),
						activos.getACT_No_Serie(),
						activos.getACT_Grupo_Contable(),
						activos.getACT_Auxiliar_Contable(),
					String.valueOf(activos.getACT_Vida_Util()),
						activos.getACT_Partidas_Presupuestarias(),
						activos.getACT_Fuente_Financiamiento(),
						activos.getACT_Organismo_Financiador(),
						activos.getACT_Nombre_Proveedor(),
						activos.getACT_Marca(),
					String.valueOf(activos.getACT_Fecha_Compra()),
					String.valueOf(	activos.getACT_Tipo_Cambio()),
					String.valueOf(activos.getACT_Valor()),
					String.valueOf(activos.getACT_No_Comprobante_Gasto()),
						activos.getACT_No_Folio_Real(),
						activos.getACT_No_RUAT(),
						activos.getACT_No_Poliza_Seguro(),
						activos.getACT_No_Contrato_Mantenimiento(),
					String.valueOf(activos.getACT_Fecha_Vencimiento_Mantenimiento()),
						activos.getACT_Inmueble(),
						activos.getACT_Ubicacion_Fisica_Activo(),
						String.valueOf(activos.getACT_Fecha_Vencimiento_Seguro()),
						fullname_empelado };
				this.data[r] = row;
				this.r++;
			}
			return data;
		}
	
		private void buildMessages(List<BarMessage> mensages) {
			this.hl_errores.removeAllComponents();
			hl_errores.addStyleName("ait-error-bar");
			this.addComponent(this.hl_errores);
			
			for (BarMessage barMessage : mensages) {
				Label lbError = new Label(new Label(barMessage.getComponetName()+":"+barMessage.getErrorName()));
				lbError.setStyleName(barMessage.getType());
				this.hl_errores.addComponent(lbError);
			}
				
		}
		@Override
		public void buttonClick(ClickEvent event) {
			if (this.frmReporte.validate()) {
//				ReportPdf reporte = new ReportPdf();
//				try {
//					reporte.getPdf(getData());
//					File pdfFile = new File(ReportPdf.SAVE_PATH);
//
//					VerticalLayout vl_pdf = new VerticalLayout();
//					Embedded pdf = new Embedded("", new FileResource(pdfFile));
//					pdf.setMimeType("application/pdf");
//					pdf.setType(Embedded.TYPE_BROWSER);
//					pdf.setSizeFull();
//					vl_pdf.setSizeFull();
//					vl_pdf.addComponent(pdf);
//
//					Window subWindow = new Window("Reporte Kardex");
//					VerticalLayout subContent = new VerticalLayout();
//					subContent.setMargin(true);
//					subWindow.setContent(vl_pdf);
//
//					subWindow.setWidth("90%");
//					subWindow.setHeight("90%");
//					subWindow.center();
//
//					// Open it in the UI
//					getUI().addWindow(subWindow);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
			buildMessages(this.frmReporte.getMessage());
		}
		

	}

