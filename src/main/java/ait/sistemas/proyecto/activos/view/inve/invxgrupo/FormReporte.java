package ait.sistemas.proyecto.activos.view.inve.invxgrupo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.NoResultException;

import ait.sistemas.proyecto.activos.data.model.Cierre_Gestion;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.CierreGestionImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoCambioImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FormReporte extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	
	private static final short ALL = 0;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	public ComboBox cb_dependencia = new ComboBox("Dependencia");
	
	public TextField txt_ufvi = new TextField("Tipo Cambio");
	public DateField dtf_fecha_ultima_depre = new DateField("Fecha de Elaboracion");
	
	private final DependenciaImpl dependenciaimpl = new DependenciaImpl();
	
	private final CierreGestionImpl cierreGestionImpl = new CierreGestionImpl();
	private final TipoCambioImpl tipoCambioImpl = new TipoCambioImpl();
	
	public FormReporte() {
		super(4, 2);
		setMargin(true);
		setSpacing(true);
		
		cb_dependencia.setWidth("100%");
		
		dtf_fecha_ultima_depre.addValueChangeListener(this);
		buildcbDependencia();
		setWidth("100%");
		setData((Cierre_Gestion) cierreGestionImpl.getall());
		buildContent();
	}
	
	private void buildContent() {
		
		Panel pn_ultimo_valor = new Panel("Datos de la ultima Actualizacion y Depreciacion");
		GridLayout grid_dependencia = new GridLayout();
		grid_dependencia.setWidth("100%");
		grid_dependencia.setMargin(true);
		grid_dependencia.setSpacing(true);
		
		grid_dependencia.addComponent(cb_dependencia);
		VerticalLayout vl_form = new VerticalLayout();
		Panel pn_form = new Panel("SELECCIONE UNA DEPENDENCIA :  " + Messages.REQUIED_FIELDS);
		pn_form.setIcon(FontAwesome.EDIT);
		pn_form.setStyleName(AitTheme.PANEL_FORM);
		pn_form.setContent(grid_dependencia);
		vl_form.setMargin(true);
		vl_form.addComponent(pn_form);
		
		GridLayout grid_ultimo = new GridLayout(2, 1);
		grid_ultimo.setSizeFull();
		grid_ultimo.setMargin(true);
		grid_ultimo.addComponent(this.dtf_fecha_ultima_depre, 0, 0);
		grid_ultimo.addComponent(this.txt_ufvi, 1, 0);
		pn_ultimo_valor.setContent(grid_ultimo);
		pn_ultimo_valor.setStyleName(AitTheme.PANEL_PRINT);
		pn_ultimo_valor.setIcon(FontAwesome.PRINT);
		
		addComponent(pn_form, 0, 0, 1, 0);
		addComponent(pn_ultimo_valor, 0, 1, 1, 1);
		
	}
	
	private void buildcbDependencia() {
		this.cb_dependencia.removeAllItems();
		this.cb_dependencia.setInputPrompt("Seleccione un Dependencia");
		this.cb_dependencia.setNullSelectionAllowed(false);
		for (Dependencia dependencia : dependenciaimpl.getall()) {
			this.cb_dependencia.addItem(dependencia.getDEP_Dependencia());
			this.cb_dependencia.setItemCaption(dependencia.getDEP_Dependencia(), dependencia.getDEP_Nombre_Dependencia());
		}
		cb_dependencia.addItem(ALL);
		cb_dependencia.setItemCaption(ALL, "Todas las Dependencias");
	}
	
	public boolean validate() {
		
		if (cb_dependencia.getValue() != null)
			return true;
		mensajes.add(new BarMessage("Dependencia", Messages.EMPTY_MESSAGE));
		return false;
	}
	
	public void clean() {
		buildcbDependencia();
	}
	
	public List<BarMessage> getMensajes() {
		return this.mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public short getDependencia() {
		if ((short) cb_dependencia.getValue() == ALL)
			return 0;
		return (short) this.cb_dependencia.getValue();
	}
	
	public void setData(Cierre_Gestion data) {
		txt_ufvi.setEnabled(false);
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(data.getCGE_Fecha_Cierre_Gestion().getTime());
		calendar.add(Calendar.YEAR, 1);
		this.txt_ufvi.setValue(String.valueOf(data.getCGE_Tipo_Cambio_UFV()));
		this.dtf_fecha_ultima_depre.setValue(calendar.getTime());
		calendar.add(Calendar.YEAR, -1);
		dtf_fecha_ultima_depre.setRangeStart(calendar.getTime());
		dtf_fecha_ultima_depre.setRangeEnd(new Date());
		
	}
	
	public String getFecha() {
		return new SimpleDateFormat("yyyy-MM-dd").format(dtf_fecha_ultima_depre.getValue()) + "T00:00:00";
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty() == dtf_fecha_ultima_depre && dtf_fecha_ultima_depre.getValue() != null) {
			try {
				txt_ufvi.setValue(tipoCambioImpl.getTipoCambioUFV(new java.sql.Date(dtf_fecha_ultima_depre.getValue().getTime()))
						.getTipo_cambio().toString().replace(".", ","));
			} catch (NoResultException ex) {
				Notification.show(Messages.TIPO_CAMBIO);
			}
		}
	}
	
}
