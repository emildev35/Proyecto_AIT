package ait.sistemas.proyecto.activos.view.inve.activosxfun;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.MovimientoReporte;
import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.view.inve.activosxfun.reporte.ReportPdf;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.report.Column;
import ait.sistemas.proyecto.common.report.pdf.movimiento.Acta;
import ait.sistemas.proyecto.common.report.pdf.movimiento.TablaActivos;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VActivosxFunR extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;

	private FormActivosxfun frm_activoxfun= new FormActivosxfun();
	private CssLayout hl_errores= new CssLayout();;
	private Button btn_imprimir = new Button("Imprimir");
	private Button btn_salir = new Button("Salir");
	private ActivoImpl personal_impl = new ActivoImpl();
	private List<BarMessage> msg = new ArrayList<BarMessage>();

	public VActivosxFunR() {
		this.btn_imprimir.addClickListener(this);
		this.btn_salir.addClickListener(this);
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
		msg.add(new BarMessage("Formulario", "Debe llenar los campos con * y campos de seleccion de por C.I. o por Dependencia"));
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

		VerticalLayout vl_form = new VerticalLayout();

		vl_form.addComponent(frm_activoxfun);

		return vl_form;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		navPanel.addStyleName("ait-content-nav");
		HorizontalLayout nav = new HorizontalLayout();
		nav.addComponent(new Label("Activos>>"));
		nav.addComponent(new Label("Inventario>>"));
		nav.addComponent(new Label("Activos por Funcionario>>"));
		nav.addComponent(new Label("<strong>Reporte</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
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

	@SuppressWarnings("deprecation")
	@Override
	public void buttonClick(ClickEvent event) {

		if (event.getButton() == this.btn_imprimir) {
			if (this.frm_activoxfun.validate()) {
				ReportPdf reporte = new ReportPdf();
				List<MovimientoReporte> data_reporte;
				if (this.frm_activoxfun.txt_ci.getValue() != null && !this.frm_activoxfun.txt_ci.getValue().toString().equals("")){
					 data_reporte = personal_impl
								.ActivosbyUsuario(frm_activoxfun.txt_ci.getValue(),new java.sql.Date(this.frm_activoxfun.dt_fecha.getValue().getTime()));
				}
				else{
					data_reporte = personal_impl
							.ActivosbyUsuario(((PersonalModel) frm_activoxfun.cb_funcionario.getValue())
									.getPER_CI_Empleado(),new java.sql.Date(this.frm_activoxfun.dt_fecha.getValue().getTime()));
				}
				
				try {
					reporte.getPdf(getActa(data_reporte), new SimpleDateFormat("dd-MM-yyyy").format(this.frm_activoxfun.dt_fecha.getValue()));
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

				Window subWindow = new Window("Reporte Activos por Funcionario");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setMargin(true);
				subWindow.setContent(vl_pdf);

				subWindow.setWidth("90%");
				subWindow.setHeight("90%");
				subWindow.center();

				// Open it in the UI
				getUI().addWindow(subWindow);

				Notification.show(Messages.SUCCESS_MESSAGE);
			} else {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}
			buildMessages(this.frm_activoxfun.getMensajes());
			this.frm_activoxfun.clearMessages();
		}
		if (event.getButton() == this.btn_salir) {
			UI.getCurrent().getNavigator().navigateTo(HomeView.URL);
		}
	}

	public Acta getActa(List<MovimientoReporte> data) {

		Acta acta = new Acta();
		acta.setDependencia_origen(data.get(0).getDependencia_Origen());
		// acta.setDependencia_destino(data.get(0).getDependencia_Destino());
		acta.setUnidad_origen(data.get(0).getUnidad_organizacional_Origen());
		// acta.setUnidad_destino(data.get(0).getUnidad_organizacional_Destino());
		acta.setCi(data.get(0).getCi());
		acta.setUsuario_origen(data.get(0).getUsuario_Origen());
		// acta.setUsuario_destino(data.get(0).getUsuario_Destino());
		// acta.setNro_acta_entrega(String.valueOf(data.get(0).getCMV_No_Documento()));
		// acta.setFecha(data.get(0).getCMV_Fecha_Registro().toString());

		TablaActivos tabla = new TablaActivos();

		String[][] activos = new String[data.size() * 2][5];
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column("Codigo", 35));
		columns.add(new Column("Fecha Asignacion", 75));
		columns.add(new Column("N° Acta", 35));
		columns.add(new Column("Nombre del Activo", 345));
		columns.add(new Column("Caracteriticas y Componentes", 390));

		// List<Firma> firmas = new ArrayList<Firma>();
		// firmas.add(new Firma("Funcionario Encargado", 50));
		// firmas.add(new Firma("", 50));
		// firmas.add(new Firma("", 50));
		// acta.setFirmas(firmas);
		acta.setColumns(columns);
		int r = 0;
		String oldval = "";
		for (MovimientoReporte movimientoReporte : data) {
			activos[r][0] = String.valueOf(movimientoReporte.getCodigo_Activo());
			activos[r][1] = String.valueOf(movimientoReporte.getFecha_Asignacion());
			activos[r][2] = String.valueOf(movimientoReporte.getNo_Acta());
			activos[r][3] = String.valueOf(movimientoReporte.getNombre_Activo());
			activos[r][4] = String.valueOf(movimientoReporte.getComponentes());

			activos[r + 1][0] = String.valueOf(movimientoReporte.getCodigo_Activo());
			activos[r + 1][1] = String.valueOf(movimientoReporte.getFecha_Asignacion());
			activos[r + 1][2] = String.valueOf(movimientoReporte.getNo_Acta());
			activos[r + 1][3] = String.valueOf(movimientoReporte.getNombre_Activo());
			activos[r + 1][4] = String.valueOf(movimientoReporte.getCaracteristicas());

			if (activos[r][2]==null || activos[r][2].equals("null")){
				activos[r][2] = "";
			}
			
			if (oldval.equals(activos[r][0])) {
				activos[r][0] = "";
				activos[r][1] = "";
				activos[r][2] = "";
				activos[r][3] = "";
			} else {
				oldval = activos[r][0];
			}

			if (oldval.equals(activos[r + 1][0])) {
				activos[r + 1][0] = "";
				activos[r + 1][1] = "";
				activos[r + 1][2] = "";
				activos[r + 1][3] = "";
			} else {
				oldval = activos[r + 1][0];
			}

			r += 2;
		}
		tabla.setData(activos);
		tabla.setRowheigth(15);
		acta.setTb_activos(tabla);
		return acta;
	}
}