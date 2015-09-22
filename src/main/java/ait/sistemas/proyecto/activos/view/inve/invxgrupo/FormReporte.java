package ait.sistemas.proyecto.activos.view.inve.invxgrupo;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class FormReporte extends GridLayout {
	private static final long serialVersionUID = 1L;
	
	private static final short ALL = 0;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	public ComboBox cb_dependencia = new ComboBox("Dependencia");
	
	private final DependenciaImpl dependenciaimpl = new DependenciaImpl();
	
	public FormReporte() {
		super(4, 2);
		setMargin(true);
		setSpacing(true);
		
		cb_dependencia.setWidth("100%");
		
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
		
		addComponent(pn_form, 0, 0, 1, 0);
		
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
	
}
