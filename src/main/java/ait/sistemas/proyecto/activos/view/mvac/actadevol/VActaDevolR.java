package ait.sistemas.proyecto.activos.view.mvac.actadevol;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.component.model.MovimientoReporte;
import ait.sistemas.proyecto.activos.data.service.Impl.ActasImpl;
import ait.sistemas.proyecto.activos.view.mvac.devolucion.reporte.ReportPdf;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.report.Column;
import ait.sistemas.proyecto.common.report.pdf.movimiento.Acta;
import ait.sistemas.proyecto.common.report.pdf.movimiento.Firma;
import ait.sistemas.proyecto.common.report.pdf.movimiento.TablaActivos;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.common.view.HomeView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VActaDevolR extends VerticalLayout implements View, ClickListener,SelectionListener {

	private static final long serialVersionUID = 1L;

	private FormReporte frm_asignacio = new FormReporte();
	private CssLayout hl_errores = new CssLayout();;
	private Button btn_imprimir = new Button("Imprimir");
	private Button btn_salir = new Button("Salir");
	private ActasImpl acta_impl = new ActasImpl();
	private List<BarMessage> msg = new ArrayList<BarMessage>();
	private final Arbol_menus menu = (Arbol_menus)UI.getCurrent().getSession().getAttribute("nav");
//	private GridActaEntrega grid_Acta = new GridActaEntrega();
	private Movimiento movimiento;
	
	public VActaDevolR() {
		this.btn_imprimir.addClickListener(this);
		this.btn_salir.addClickListener(this);
		this.frm_asignacio.grid_Acta.addSelectionListener(this);
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
		msg.add(new BarMessage("Formulario",
				"Debe llenar los campos de seleccion de por C.I. o por Dependencia"));
		buildMessages(msg);
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_imprimir.setStyleName(AitTheme.BTN_PRINT);
		buttonContent.addComponent(this.btn_imprimir);
		btn_imprimir.setIcon(FontAwesome.PRINT);
		this.btn_salir.setStyleName(AitTheme.BTN_EXIT);
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_salir);
		btn_salir.setIcon(FontAwesome.UNDO);
		return buttonContent;
	}

	private Component buildFormContent() {

		VerticalLayout vl_form = new VerticalLayout();

		vl_form.addComponent(frm_asignacio);

		return vl_form;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label(AitView.getNavText(menu), ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

	public void buildMessages(List<BarMessage> mensages) {
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

		if (event.getButton() == this.btn_imprimir) {
			if (this.frm_asignacio.validate()) {
			ReportPdf reporte = new ReportPdf();
			List<MovimientoReporte> data_reporte = acta_impl.ReporteActa(String.valueOf(this.movimiento.getNro_documento()+1),
					(short) 4);
			try {
				reporte.getPdf(getActa(data_reporte));
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
			File pdfFile = new File(ReportPdf.SAVE_PATH);

			VerticalLayout vl_pdf = new VerticalLayout();
			Embedded pdf = new Embedded("", new FileResource(pdfFile));
			pdf.setMimeType("application/pdf");
			pdf.setType(Embedded.TYPE_BROWSER);
			pdf.setSizeFull();
			vl_pdf.setSizeFull();
			vl_pdf.addComponent(pdf);

			Window subWindow = new Window("Reporte Acta Asignacion");
			VerticalLayout subContent = new VerticalLayout();
			subContent.setMargin(true);
			subWindow.setContent(vl_pdf);

			subWindow.setWidth("90%");
			subWindow.setHeight("90%");
			subWindow.center();

			// Open it in the UI
			getUI().addWindow(subWindow);
		} else {
			Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
		}
		}
		
		if (event.getButton() == this.btn_salir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}

	public Acta getActa(List<MovimientoReporte> data) {

		Acta acta = new Acta();
		acta.setDependencia_origen(data.get(0).getDependencia_Origen());
		acta.setDependencia_destino(data.get(0).getDependencia_Destino());
		acta.setUnidad_origen(data.get(0).getUnidad_organizacional_Origen());
		acta.setUnidad_destino(data.get(0).getUnidad_organizacional_Destino());
		acta.setUsuario_origen(data.get(0).getUsuario_Origen());
		acta.setUsuario_destino(data.get(0).getUsuario_Destino());
		acta.setNro_acta_entrega(String.valueOf(data.get(0).getCMV_No_Documento()));
		acta.setFecha(data.get(0).getCMV_Fecha_Registro().toString());

		TablaActivos tabla = new TablaActivos();

		String[][] activos = new String[data.size()*2][3];
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column("Codigo", 30));
		columns.add(new Column("Nombre del Activo", 300));
		columns.add(new Column("Caracteriticas y Componentes", 375));

		List<Firma> firmas = new ArrayList<Firma>();
		firmas.add(new Firma("Funcionario Encargado", 50));
		firmas.add(new Firma("", 50));
		firmas.add(new Firma("", 50));
		acta.setFirmas(firmas);
		acta.setColumns(columns);
		int r = 0;
		String oldval = "";
		for (MovimientoReporte movimientoReporte : data) {
			activos[r][0] = String.valueOf(movimientoReporte.getCodigo_Activo());
			activos[r][1] = String.valueOf(movimientoReporte.getNombre_Activo());
			activos[r][2] = String.valueOf(movimientoReporte.getComponentes());
			
			activos[r+1][0] = String.valueOf(movimientoReporte.getCodigo_Activo());
			activos[r+1][1] = String.valueOf(movimientoReporte.getNombre_Activo());
			activos[r+1][2] = String.valueOf(movimientoReporte.getCaracteristicas());

			if (oldval.equals(activos[r][0])) {
				activos[r][0] = "";
				activos[r][1] = "";
			}else{
				oldval = activos[r][0];
			}
			
			if (oldval.equals(activos[r+1][0])) {
				activos[r+1][0] = "";
				activos[r+1][1] = "";
			}else{
				oldval = activos[r+1][0];
			}

			r+=2;
		}
		tabla.setData(activos);
		tabla.setRowheigth(15);
		acta.setTb_activos(tabla);
		return acta;
	}

	@Override
	public void select(SelectionEvent event) {
	
			if ((Movimiento)frm_asignacio.grid_Acta.getSelectedRow() != null) {
				this.movimiento  = (Movimiento)this.frm_asignacio.grid_Acta.getSelectedRow();
			}
			
			msg = new ArrayList<BarMessage>();
			msg.add(new BarMessage("Boton", Messages.PRESS_PRINT_BTN));
			buildMessages(msg);
	}
}