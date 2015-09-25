package ait.sistemas.proyecto.activos.view.inve.inventarionomi;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.HomeView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
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

public class VInventarioNomiR extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;

	private Button btn_imprimir = new Button("Imprimir");
	private Button btn_salir = new Button("Salir");
	private FormInventarioNomi frmReporte = new FormInventarioNomi();
	int r = 0;
	private final ActivoImpl activo_impl = new ActivoImpl();
	private CssLayout hl_errores = new CssLayout();
	private List<BarMessage> msg = new ArrayList<BarMessage>();

	public VInventarioNomiR() {

		this.btn_imprimir.addClickListener(this);
		this.btn_salir.addClickListener(this);
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
		msg.add(new BarMessage("Formulario", Messages.REQUIED_FIELDS));
		buildMessages(msg);
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		GridLayout btn_grid = new GridLayout(2, 1);
		btn_grid.setResponsive(true);
		btn_grid.setSizeFull();
		this.btn_imprimir.setStyleName(AitTheme.BTN_PRINT);
		btn_grid.addComponent(this.btn_imprimir);
		btn_grid.setComponentAlignment(btn_imprimir, Alignment.TOP_CENTER);
		btn_imprimir.setIcon(FontAwesome.PRINT);
		this.btn_salir.setStyleName(AitTheme.BTN_EXIT);
		buttonContent.addStyleName("ait-buttons");
		btn_grid.addComponent(this.btn_salir);
		btn_salir.setIcon(FontAwesome.UNDO);
		btn_grid.setComponentAlignment(btn_salir, Alignment.TOP_LEFT);
		buttonContent.addComponent(btn_grid);
		return buttonContent;
	}

	private Component buildFormContent() {
		VerticalLayout formContent = new VerticalLayout();
		formContent.setSpacing(true);
		Panel frmPanel = new Panel("Formulario de Impresion");
		// los campos requeridos
		frmPanel.setIcon(FontAwesome.PRINT);
		frmPanel.setStyleName(AitTheme.PANEL_PRINT);
		// frmPanel.setWidth("100%");
		// frmPanel.setCaption("Formulario de Impresion");
		frmPanel.setContent(this.frmReporte);
		formContent.addComponent(frmPanel);
		return formContent;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Inventarios » "));
		nav.addComponent(new Label("Inventario Nominal de Activos » "));
		nav.addComponent(new Label("<strong>Reporte</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	// public String[][] getData() {
	//
	// List<ActivosModel> lista = activo_impl
	// .activos_by_dependencia((Short)
	// this.frmReporte.cb_Dependencia.getValue());
	//
	// this.data = new String[lista.size()][2];
	// this.r = 0;
	// for (ActivosModel activo : lista) {
	// String[] row = { activo.getACT_Grupo_Contable(),
	// activo.getACT_Auxiliar_Contable() };
	// this.data[r] = row;
	// this.r++;
	// }
	// return data;
	// }

	public String[][] getDatos() {

		List<ActivosModel> lista = activo_impl.ActivosNominal_by_dependencia((Short) this.frmReporte.cb_Dependencia
				.getValue(), new java.sql.Date(this.frmReporte.dt_fecha.getValue().getTime()));

		String[][] data = new String[lista.size()][5];
		r = 0;
		for (ActivosModel activo : lista) {
			String[] row = { activo.getACT_Dependencia(), activo.getACT_Grupo_Contable(),
					activo.getACT_Auxiliar_Contable(), activo.getACT_Codigo_Activo(), activo.getACT_No_Serie(),
					activo.getACT_Nombre_Activo(), String.valueOf(activo.getACT_Valor_Compra()),
					String.valueOf(activo.getACT_Valor_Neto()), activo.getACT_No_Resolucion_Baja(),
					String.valueOf(activo.getACT_Fecha_Baja()), String.valueOf(activo.getACT_Vida_Residual()) };

			data[r] = row;
			r++;
		}
		return data;
	}

	public String[][] getDatosALL() {

		List<ActivosModel> lista = activo_impl.getActivosNominalbyFechaCompra(new java.sql.Date(
				this.frmReporte.dt_fecha.getValue().getTime()));

		String[][] data = new String[lista.size()][5];
		r = 0;
		for (ActivosModel activo : lista) {
			String[] row = { activo.getACT_Dependencia(), activo.getACT_Grupo_Contable(),
					activo.getACT_Auxiliar_Contable(), activo.getACT_Codigo_Activo(), activo.getACT_No_Serie(),
					activo.getACT_Nombre_Activo(), String.valueOf(activo.getACT_Valor_Compra()),
					String.valueOf(activo.getACT_Valor_Neto()), activo.getACT_No_Resolucion_Baja(),
					String.valueOf(activo.getACT_Fecha_Baja()), String.valueOf(activo.getACT_Vida_Residual()) };

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
			Label lbError = new Label(new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName()));
			lbError.setStyleName(barMessage.getType());
			this.hl_errores.addComponent(lbError);
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
		this.frmReporte.clearMessages();
		if (event.getButton() == this.btn_imprimir) {
			if (this.frmReporte.validate()) {
				ReportPdf reporte = new ReportPdf();
				try {
					short a = 0;
					if ((Short) this.frmReporte.cb_Dependencia.getValue() == a) {
						// int [][] datas = activo_impl.getProvedoreCuidad();
						reporte.getPdf(getDatosALL(), this.frmReporte.cb_Dependencia
								.getItemCaption(this.frmReporte.cb_Dependencia.getValue()), new SimpleDateFormat(
								"dd-MM-yyyy").format(this.frmReporte.dt_fecha.getValue()));
					} else {
						reporte.getPdf(getDatos(), this.frmReporte.cb_Dependencia
								.getItemCaption(this.frmReporte.cb_Dependencia.getValue()), new SimpleDateFormat(
								"dd-MM-yyyy").format(this.frmReporte.dt_fecha.getValue()));
					}
					File pdfFile = new File(reporte.SAVE_PATH);

					VerticalLayout vl_pdf = new VerticalLayout();
					Embedded pdf = new Embedded("", new FileResource(pdfFile));

					pdf.setMimeType("application/pdf");
					pdf.setType(Embedded.TYPE_BROWSER);
					pdf.setSizeFull();
					vl_pdf.setSizeFull();
					vl_pdf.addComponent(pdf);

					Window subWindow = new Window("Reporte Inventario Nominal Activos");
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
		if (event.getButton() == this.btn_salir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}
}
