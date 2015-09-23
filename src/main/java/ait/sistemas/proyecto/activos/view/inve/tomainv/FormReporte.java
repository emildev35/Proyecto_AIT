package ait.sistemas.proyecto.activos.view.inve.tomainv;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Formulario del Reporte de Inventario Fisico
 * esta clase continen:
 * Dependencia 				: ComboBox
 * No. Documento Referencia : TextField
 * Fecha 					: TextField
 * @author franzemil
 *
 */
public class FormReporte extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	private static final short ALL = 0;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	public ComboBox cb_dependencia = new ComboBox("Dependencia");
	public TextField txt_no_resolucion = new TextField("No. Documento Referencia");
	public DateField dtf_fecha = new DateField("Fecha");
	
	private final DependenciaImpl dependenciaimpl = new DependenciaImpl();
	
	public FormReporte() {
		super(4, 2);
		setMargin(true);
		setSpacing(true);
		
		cb_dependencia.setWidth("100%");
		txt_no_resolucion.setWidth("100%");
		dtf_fecha.setWidth("100%");
		
		buildcbDependencia();
		setWidth("100%");
		buildContent();
	}
	
	private void buildContent() {
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
		
		GridLayout grid_ref = new GridLayout(2, 1);
		grid_ref.setWidth("100%");
		grid_ref.setMargin(true);
		grid_ref.setSpacing(true);
		grid_ref.addComponent(txt_no_resolucion, 0, 0);
		grid_ref.addComponent(dtf_fecha, 1, 0);
		Panel pn_ref = new Panel("SELEECIONE EL DOCUMENTO DE REFERENCIA DEL INVENTARIO FISICO :  " + Messages.REQUIED_FIELDS);
		pn_ref.setIcon(FontAwesome.EDIT);
		pn_ref.setStyleName(AitTheme.PANEL_FORM);
		pn_ref.setContent(grid_ref);
		vl_form.addComponent(pn_ref);
		
		addComponent(pn_form, 0, 0, 1, 0);
		addComponent(pn_ref, 0, 1, 1, 1);
		
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
		
		if (cb_dependencia.getValue() != null || (txt_no_resolucion.getValue() != null && dtf_fecha.getValue() != null))
			return true;
		mensajes.add(new BarMessage("Form", "Datos Incompletos"));
		return false;
	}
	/**
	 * Vacio todos los valores de los componentes
	 */
	public void clean() {
		buildcbDependencia();
		txt_no_resolucion.setValue("");
		dtf_fecha.setValue(null);
	}
	/**
	 * Retorna la lista de Mensajes del Formulario
	 * @return
	 */
	public List<BarMessage> getMensajes() {
		return this.mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}

	public Date getFechaRef() {
		if(dtf_fecha.getValue()==null)
			return null;
		return new Date(dtf_fecha.getValue().getTime());
	}

	public short getDependencia() {
		if((short)cb_dependencia.getValue()==ALL)
			return 0;
		return (short)this.cb_dependencia.getValue();
	}

	public String getDocRef() {
	
		return txt_no_resolucion.getValue();
	}
	
}
