package ait.sistemas.proyecto.activos.view.inve.kardex;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VKardexR extends VerticalLayout implements View, ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_imprimir;
	private FormKardex frmReporte = new FormKardex();
	int r = 0;
	private final ActivoImpl activo_impl = new ActivoImpl();
	private CssLayout hl_errores = new CssLayout();
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	public VKardexR() {
		
		this.btn_imprimir = new Button("Imprimir");
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		
	}
	
	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_imprimir);
		this.btn_imprimir.addStyleName(AitTheme.BTN_PRINT);
		btn_imprimir.setIcon(FontAwesome.PRINT);
		this.btn_imprimir.addClickListener(this);
		buttonContent.addStyleName("ait-buttons");
		
		Responsive.makeResponsive(buttonContent);
		return buttonContent;
	}
	
	private Component buildFormContent() {
		VerticalLayout formContent = new VerticalLayout();
		addComponent(frmReporte);
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
	
	public KardexElement[][][] getData(List<ActivosModel> lista) throws SQLException {
		
		this.r = 0;
		
		KardexElement[][][] kardex_elements = new KardexElement[lista.size()][8][7];
		
		for (ActivosModel activos : lista) {
			activos.setACT_Actualizacion_Acumulada_Gestion_Anterior(new BigDecimal("0"));
			activos.setACT_Actualizacion_Acumulada(new BigDecimal("0"));
			activos.setACT_Depresiacion_Acumulada(new BigDecimal("0"));
			
			// activos.setACT_Valor_Neto(new BigDecimal("1"));
			
			if (activos.getACT_Fecha_Baja() == null && activos.getACT_No_Resolucion_Baja() == null) {
				
				activos.Actualizar(this.frmReporte.dtf_fechaElaboracion.getValue());
			}
			
			kardex_elements[r][0][0] = new KardexElement();
			kardex_elements[r][0][0].setAncho(75);
			kardex_elements[r][0][0].setTitulo("Codigo");
			kardex_elements[r][0][0].setContenido(activos.getACT_Codigo_Activo());
			
			kardex_elements[r][0][1] = new KardexElement();
			kardex_elements[r][0][1].setAncho(525);
			kardex_elements[r][0][1].setTitulo("Descripcion");
			kardex_elements[r][0][1].setContenido(activos.getACT_Nombre_Activo());
			
			kardex_elements[r][0][2] = new KardexElement();
			kardex_elements[r][0][2].setAncho(125);
			kardex_elements[r][0][2].setTitulo("Serie");
			kardex_elements[r][0][2].setContenido(activos.getACT_No_Serie());
			
			kardex_elements[r][1][0] = new KardexElement();
			kardex_elements[r][1][0].setAncho(340);
			kardex_elements[r][1][0].setTitulo("Grupo Contable");
			kardex_elements[r][1][0].setContenido(activos.getACT_Grupo_Contable());
			
			kardex_elements[r][1][1] = new KardexElement();
			kardex_elements[r][1][1].setAncho(340);
			kardex_elements[r][1][1].setTitulo("Auxiliar Contable");
			kardex_elements[r][1][1].setContenido(activos.getACT_Auxiliar_Contable());
			
			kardex_elements[r][1][2] = new KardexElement();
			kardex_elements[r][1][2].setAncho(45);
			kardex_elements[r][1][2].setTitulo("Vida Util");
			kardex_elements[r][1][2].setContenido(String.valueOf(activos.getACT_Vida_Util()));
			
			kardex_elements[r][2][0] = new KardexElement();
			kardex_elements[r][2][0].setAncho(200);
			kardex_elements[r][2][0].setTitulo("Partida");
			kardex_elements[r][2][0].setContenido(activos.getACT_Partidas_Presupuestarias());
			
			kardex_elements[r][2][1] = new KardexElement();
			kardex_elements[r][2][1].setAncho(200);
			kardex_elements[r][2][1].setTitulo("Fuente Financimiento");
			kardex_elements[r][2][1].setContenido(activos.getACT_Fuente_Financiamiento());
			
			kardex_elements[r][2][2] = new KardexElement();
			kardex_elements[r][2][2].setAncho(200);
			kardex_elements[r][2][2].setTitulo("Organismo Financiador");
			kardex_elements[r][2][2].setContenido(activos.getACT_Organismo_Financiador());
			
			kardex_elements[r][2][3] = new KardexElement();
			kardex_elements[r][2][3].setAncho(125);
			kardex_elements[r][2][3].setTitulo("N. Comprobante de Gasto");
			kardex_elements[r][2][3].setContenido(String.valueOf(activos.getACT_No_Comprobante_Gasto()));
			
			kardex_elements[r][3][0] = new KardexElement();
			kardex_elements[r][3][0].setAncho(200);
			kardex_elements[r][3][0].setTitulo("Proveedor");
			kardex_elements[r][3][0].setContenido(activos.getACT_Nombre_Proveedor());
			
			kardex_elements[r][3][1] = new KardexElement();
			kardex_elements[r][3][1].setAncho(185);
			kardex_elements[r][3][1].setTitulo("Marca");
			kardex_elements[r][3][1].setContenido(activos.getACT_Marca());
			
			kardex_elements[r][3][2] = new KardexElement();
			kardex_elements[r][3][2].setAncho(60);
			kardex_elements[r][3][2].setTitulo("F. Compra");
			kardex_elements[r][3][2].setContenido(new SimpleDateFormat("dd-MM-yyyy").format(activos.getACT_Fecha_Compra()));
			
			kardex_elements[r][3][3] = new KardexElement();
			kardex_elements[r][3][3].setAncho(75);
			kardex_elements[r][3][3].setTitulo("Valor Compra (Bs)");
			kardex_elements[r][3][3].setContenido(String.valueOf(activos.getACT_Valor_Compra()));
			
			kardex_elements[r][3][4] = new KardexElement();
			kardex_elements[r][3][4].setAncho(65);
			kardex_elements[r][3][4].setTitulo("F. Incorporacion");
			kardex_elements[r][3][4].setContenido(new SimpleDateFormat("dd-MM-yyyy").format(activos.getACT_Fecha_Incorporacion()));
			
			kardex_elements[r][3][5] = new KardexElement();
			kardex_elements[r][3][5].setAncho(70);
			kardex_elements[r][3][5].setTitulo("Tipo Cambio ($u$)");
			kardex_elements[r][3][5].setContenido(String.valueOf(activos.getACT_Tipo_Cambio_Dolar()));
			
			kardex_elements[r][3][6] = new KardexElement();
			kardex_elements[r][3][6].setAncho(70);
			kardex_elements[r][3][6].setTitulo("Tipo Cambio (UFV)");
			kardex_elements[r][3][6].setContenido(String.valueOf(activos.getACT_Tipo_Cambio_UFV()));
			
			kardex_elements[r][4][0] = new KardexElement();
			kardex_elements[r][4][0].setAncho(50);
			kardex_elements[r][4][0].setTitulo("N.Folio Real");
			kardex_elements[r][4][0].setContenido(activos.getACT_No_Folio_Real());
			
			kardex_elements[r][4][1] = new KardexElement();
			kardex_elements[r][4][1].setAncho(50);
			kardex_elements[r][4][1].setTitulo("N. Ruat");
			kardex_elements[r][4][1].setContenido(activos.getACT_No_RUAT());
			
			kardex_elements[r][4][2] = new KardexElement();
			kardex_elements[r][4][2].setAncho(80);
			kardex_elements[r][4][2].setTitulo("N. Poliza de Seguro");
			kardex_elements[r][4][2].setContenido(activos.getACT_No_Poliza_Seguro());
			
			kardex_elements[r][4][3] = new KardexElement();
			kardex_elements[r][4][3].setAncho(90);
			kardex_elements[r][4][3].setTitulo("Vencimiento Seguro");
			if (activos.getACT_Fecha_Vencimiento_Mantenimiento() != null) {
				kardex_elements[r][4][3].setContenido(new SimpleDateFormat("dd-MM-yyyy").format(activos
						.getACT_Fecha_Vencimiento_Seguro()));
			} else {
				kardex_elements[r][4][3].setContenido("");
			}
			
			kardex_elements[r][4][4] = new KardexElement();
			kardex_elements[r][4][4].setAncho(100);
			kardex_elements[r][4][4].setTitulo("N. Contrato Mantenimiento");
			kardex_elements[r][4][4].setContenido(activos.getACT_No_Contrato_Mantenimiento());
			
			kardex_elements[r][4][5] = new KardexElement();
			kardex_elements[r][4][5].setAncho(80);
			kardex_elements[r][4][5].setTitulo("Contrato Mantenimiento");
			if (activos.getACT_Fecha_Vencimiento_Mantenimiento() != null) {
				kardex_elements[r][4][5].setContenido(new SimpleDateFormat("dd-MM-yyyy").format(activos
						.getACT_Fecha_Vencimiento_Mantenimiento()));
			} else {
				kardex_elements[r][4][5].setContenido("");
			}
			
			kardex_elements[r][4][6] = new KardexElement();
			kardex_elements[r][4][6].setAncho(100);
			kardex_elements[r][4][6].setTitulo("Vcto. Contrato Mantenimiento");
			if (activos.getACT_Fecha_Vencimiento_Mantenimiento() != null) {
				kardex_elements[r][4][6].setContenido(new SimpleDateFormat("dd-MM-yyyy").format(activos
						.getACT_Fecha_Vencimiento_Mantenimiento() == null));
			} else {
				kardex_elements[r][4][6].setContenido("");
			}
			kardex_elements[r][5][0] = new KardexElement();
			kardex_elements[r][5][0].setAncho(200);
			kardex_elements[r][5][0].setTitulo("Inmueble");
			kardex_elements[r][5][0].setContenido(activos.getACT_Inmueble());
			
			kardex_elements[r][5][1] = new KardexElement();
			kardex_elements[r][5][1].setAncho(200);
			kardex_elements[r][5][1].setTitulo("Ubicacion Fisica");
			kardex_elements[r][5][1].setContenido(activos.getACT_Ubicacion_Fisica_Activo());
			
			kardex_elements[r][5][2] = new KardexElement();
			kardex_elements[r][5][2].setAncho(200);
			kardex_elements[r][5][2].setTitulo("En Custodio del Servidor Publico");
			kardex_elements[r][5][2].setContenido(activos.getACT_Nombre_Empleado() + " " + activos.getACT_APaterno_Empleado()
					+ " " + activos.getACT_AMaterno_Empleado());
			
			kardex_elements[r][6][0] = new KardexElement();
			kardex_elements[r][6][0].setAncho(130);
			kardex_elements[r][6][0].setTitulo("Actualización");
			kardex_elements[r][6][0].setContenido(String.valueOf(activos.getACT_Actualizacion_Acumulada().floatValue()));
			
			kardex_elements[r][6][1] = new KardexElement();
			kardex_elements[r][6][1].setAncho(100);
			kardex_elements[r][6][1].setTitulo("Depreciación");
			kardex_elements[r][6][1].setContenido(String.valueOf(activos.getACT_Depresiacion_Acumulada().floatValue()));
			
			kardex_elements[r][6][2] = new KardexElement();
			kardex_elements[r][6][2].setAncho(70);
			kardex_elements[r][6][2].setTitulo("Vida Util Remanente");
			kardex_elements[r][6][2].setContenido(String.valueOf(activos.getACT_Vida_Util_Remanente()));
			
			kardex_elements[r][6][3] = new KardexElement();
			kardex_elements[r][6][3].setAncho(100);
			kardex_elements[r][6][3].setTitulo("Valor del bien a la Fecha");
			kardex_elements[r][6][3].setContenido(String.valueOf(activos.getACT_Valor_Neto().floatValue()));
			r++;
			
		}
		return kardex_elements;
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
		if (this.frmReporte.validate()) {
			ReportPdf reporte = new ReportPdf();
			try {
				SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
				String a = "ALL";
				if (frmReporte.getActivo().equals(a)) {
					List<ActivosModel> data = activo_impl.activos_by_auxiliar(frmReporte.cb_Auxiliar.getValue().toString());
					reporte.getPdfKardexGeneratorMulti(getData(data), frmReporte.cb_Activos.getValue(),
							session.getId_dependecia(),
							new SimpleDateFormat("dd-MM-yyy").format(frmReporte.dtf_fechaElaboracion.getValue()));
				} else {
					List<ActivosModel> data = activo_impl.getall(Long.parseLong(frmReporte.getActivo()));
					reporte.getPdf(getData(data)[0], frmReporte.cb_Activos.getValue(), session.getId_dependecia(),
							new SimpleDateFormat("dd-MM-yyy").format(frmReporte.dtf_fechaElaboracion.getValue()));
				}
				File pdfFile = new File(reporte.SAVE_PATH);
				
				VerticalLayout vl_pdf = new VerticalLayout();
				Embedded pdf = new Embedded("", new FileResource(pdfFile));
				pdf.setMimeType("application/pdf");
				pdf.setType(Embedded.TYPE_BROWSER);
				pdf.setSizeFull();
				vl_pdf.setSizeFull();
				vl_pdf.addComponent(pdf);
				
				Window subWindow = new Window("Reporte Kardex");
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
			} catch (SQLException e) {
				e.printStackTrace();
				Notification.show(Messages.TIPO_CAMBIO, Type.ERROR_MESSAGE);
			}
		}
		buildMessages(this.frmReporte.getMessage());
	}
	
}
