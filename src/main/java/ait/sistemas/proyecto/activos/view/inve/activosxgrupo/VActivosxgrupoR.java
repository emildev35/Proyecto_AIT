package ait.sistemas.proyecto.activos.view.inve.activosxgrupo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.report.msexcel.SimpleExcel;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.HomeView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
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

public class VActivosxgrupoR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_imprimir = new Button("Imprimir");
	private Button btn_salir = new Button("Salir");
	private Button btn_imprimir_excel = new Button("Generar Excel");
	
	private FormInventario frmReporte = new FormInventario();
	int r = 0;
	private final ActivoImpl activo_impl = new ActivoImpl();
	private CssLayout hl_errores = new CssLayout();
	private List<BarMessage> msg = new ArrayList<BarMessage>();
	
	public VActivosxgrupoR() {
		
		this.btn_imprimir.addClickListener(this);
		this.btn_salir.addClickListener(this);
		this.btn_imprimir_excel.addClickListener(this);
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
		msg.add(new BarMessage("Formulario", Messages.REQUIED_FIELDS));
		buildMessages(msg);
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		GridLayout btn_grid = new GridLayout(3, 1);
		btn_grid.setResponsive(true);
		btn_grid.setSizeFull();
		this.btn_imprimir.setStyleName(AitTheme.BTN_PRINT);
		btn_grid.addComponent(this.btn_imprimir);
		btn_grid.setComponentAlignment(btn_imprimir, Alignment.TOP_CENTER);
		this.btn_imprimir_excel.setStyleName(AitTheme.BTN_EXCEL);
		this.btn_imprimir_excel.setIcon(FontAwesome.FILE_EXCEL_O);
		btn_grid.addComponent(this.btn_imprimir_excel);
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
		Panel frmPanel = new Panel("Formulario de Impresion " + Messages.REQUIED_FIELDS);
		frmPanel.setIcon(FontAwesome.PRINT);
		frmPanel.setStyleName(AitTheme.PANEL_FORM);
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
		nav.addComponent(new Label("Inventario de Activos » "));
		nav.addComponent(new Label("<strong>Reporte</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
	public String[][] getDatos() {
		
		List<ActivosModel> lista = activo_impl.activosByGrupoDependencia((Short) this.frmReporte.cb_Dependencia.getValue(),
				this.frmReporte.cbGrupoContable.getValue().toString(), new java.sql.Date(this.frmReporte.dt_fecha.getValue()
						.getTime()));
		String[][] data = new String[lista.size()][5];
		r = 0;
		for (ActivosModel activo : lista) {
			String[] row = { activo.getACT_Dependencia(), activo.getACT_Grupo_Contable(), activo.getACT_Auxiliar_Contable(),
					activo.getACT_Codigo_Activo(), "_" + activo.getACT_No_Serie(), activo.getACT_Nombre_Activo(),
					activo.getACT_Fecha_Incorporacion().toString(),
					String.valueOf(activo.getACT_Valor_Compra()), String.valueOf(activo.getACT_CA()),
					String.valueOf(activo.getACT_DAA()), String.valueOf(activo.getACT_Valor_Neto()) };
			
			data[r] = row;
			r++;
		}
		return data;
	}
	
	public String[][] getDatosALL() {
		
		List<ActivosModel> lista = activo_impl.getActivosbyFechaCompra(new java.sql.Date(this.frmReporte.dt_fecha.getValue()
				.getTime()));
		
		String[][] data = new String[lista.size()][5];
		r = 0;
		for (ActivosModel activo : lista) {
			String[] row = { activo.getACT_Dependencia(), activo.getACT_Grupo_Contable(), activo.getACT_Auxiliar_Contable(),
					activo.getACT_Codigo_Activo(), "_" + activo.getACT_No_Serie(), activo.getACT_Nombre_Activo(),
					activo.getACT_Fecha_Incorporacion().toString(),
					String.valueOf(activo.getACT_Valor_Compra()), String.valueOf(activo.getACT_CA()),
					String.valueOf(activo.getACT_DAA()), String.valueOf(activo.getACT_Valor_Neto()) };
			
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
					reporte.getPdf(getDatos(),
							this.frmReporte.cb_Dependencia.getItemCaption(this.frmReporte.cb_Dependencia.getValue()),
							new SimpleDateFormat("dd-MM-yyyy").format(this.frmReporte.dt_fecha.getValue()),
							this.frmReporte.cbGrupoContable.getItemCaption(this.frmReporte.cbGrupoContable.getValue())
							);
					File pdfFile = new File(reporte.SAVE_PATH);
					
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
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			buildMessages(this.frmReporte.getMessage());
		}
		if (event.getButton() == this.btn_salir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
		if (event.getButton() == this.btn_imprimir_excel) {
			SimpleExcel simpleExcel = new SimpleExcel();
			
			List<String> columnsHeadres = new ArrayList<String>();
			columnsHeadres.add("Dependencia");
			columnsHeadres.add("Grupo Contable");
			columnsHeadres.add("Auxiliar Contable");
			columnsHeadres.add("Codigo Activo");
			columnsHeadres.add("Serie");
			columnsHeadres.add("Nombre Activo");
			columnsHeadres.add("Fecha Incorporacion");
			columnsHeadres.add("Valor de Compra");
			columnsHeadres.add("Actualizacion Acumulada");
			columnsHeadres.add("Depreciacion Acumulada");
			columnsHeadres.add("Valor Neto");
			simpleExcel.save(getDatos(), columnsHeadres, "ACTIVOS POR GRUPO");
			
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
}