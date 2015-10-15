package ait.sistemas.proyecto.activos.view.mvac.asignacion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.MovimientoReporte;
import ait.sistemas.proyecto.activos.component.model.SolicitudGrid;
import ait.sistemas.proyecto.activos.data.service.Impl.ActasImpl;
import ait.sistemas.proyecto.activos.view.mvac.asignacion.reporte.ReportPdf;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.report.Column;
import ait.sistemas.proyecto.common.report.pdf.movimiento.Acta;
import ait.sistemas.proyecto.common.report.pdf.movimiento.Firma;
import ait.sistemas.proyecto.common.report.pdf.movimiento.TablaActivos;

import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
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
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VAsignacionA extends VerticalLayout implements View, ClickListener, SelectionListener {

	private static final long serialVersionUID = 1L;

	private FormAsignacion frm_asignacion;
	private CssLayout hl_errores;
	private Button btn_asignacion;
	private Button btn_imprimir;
	private GridSolasignacion grid_asignacion;
	private GridDetalle grid_Detalle = new GridDetalle();
	private ActasImpl acta_impl = new ActasImpl();
	private SolicitudGrid data;
	// private MovimientoReporte data_reporte;
	int r = 0;

	public VAsignacionA() {

		this.frm_asignacion = new FormAsignacion();
		this.btn_imprimir = new Button("Imprimir");
		this.btn_asignacion = new Button("Asignar Activos");
		this.btn_asignacion.addClickListener(this);
		this.btn_imprimir.addClickListener(this);
		this.grid_asignacion = new GridSolasignacion();
		this.grid_asignacion.addSelectionListener(this);
		this.hl_errores = new CssLayout();

		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
	}

	private Component buildFormContent() {

		VerticalLayout formContent = new VerticalLayout();

		Panel gridPanel = new Panel();
		gridPanel.setWidth("100%");
		gridPanel.setCaption("Solicitudes Pendientes de Asignacio");
		gridPanel.setContent(this.grid_asignacion);
		// formContent.setMargin(true);
		Panel grid2Panel = new Panel();
		grid2Panel.setWidth("100%");
		grid2Panel.setCaption("Activos Fijos Solicitados");
		grid2Panel.setContent(this.grid_Detalle);
		// formContent.setMargin(true);

		formContent.addComponent(gridPanel);
		formContent.addComponent(this.frm_asignacion);
		formContent.addComponent(grid2Panel);
		Responsive.makeResponsive(formContent);
		return formContent;

	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos >>"));
		nav.addComponent(new Label("Movimiento de Activos >>"));
		nav.addComponent(new Label("Asignacion >>"));
		nav.addComponent(new Label("<strong>Agregar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_asignacion.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_asignacion);
		this.btn_imprimir.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_imprimir);
		Responsive.makeResponsive(buttonContent);
		return buttonContent;
	}

	@Override
	public void enter(ViewChangeEvent event) {

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

	@Override
	public void select(SelectionEvent event) {

		if ((SolicitudGrid) this.grid_asignacion.getSelectedRow() != null) {
			this.frm_asignacion.setData((SolicitudGrid) this.grid_asignacion.getSelectedRow());

			data = (SolicitudGrid) this.grid_asignacion.getSelectedRow();
			grid_Detalle.update(data.getNro_documento(), data.getId_dependencia(), data.getTipo_movimiento());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_asignacion) {
			if (this.frm_asignacion.validate()) {
				this.acta_impl.addActaAsignacion(this.frm_asignacion.getData());
				this.grid_Detalle.vaciar();
				this.frm_asignacion.update();
				this.frm_asignacion.buidId();
				this.grid_asignacion.update();
				Notification.show(Messages.SUCCESS_MESSAGE);
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_asignacion.getMensajes());
			this.frm_asignacion.clearMessages();
		}
		if (event.getButton() == this.btn_imprimir) {
			ReportPdf reporte = new ReportPdf();
			List<MovimientoReporte> data_reporte = acta_impl.ReporteActa(frm_asignacion.txt_no_acta.getValue(),
					(short) 2);
			try {
				reporte.getPdf(getActa(data_reporte));
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}
			File pdfFile = new File(reporte.SAVE_PATH);

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
}
