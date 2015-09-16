package ait.sistemas.proyecto.activos.view.inve.reporte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.ReporteImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.util.StringHelper;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

public class FormReporte extends GridLayout implements ValueChangeListener, TextChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	ComboBox cb_dependencia = new ComboBox("Seleccione Dependencia");
	TwinColSelect tw_campos = new TwinColSelect();
	Table tb_vista_previa = new Table();
	TextField txt_nombre_reporte = new TextField("Nombre del Reporte");
	
	final ReporteImpl reporteimpl = new ReporteImpl();
	final List<String> columns = reporteimpl.getColumnName("Reporte_Activos");
	final DependenciaImpl dependenciaimpl = new DependenciaImpl();
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	public FormReporte() {
		
		super(3, 4);
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		tw_campos.setNullSelectionAllowed(false);
		tw_campos.setMultiSelect(true);
		tw_campos.setLeftColumnCaption("Campos de Activos");
		tw_campos.setRightColumnCaption("Columnas del Reporte");
		tw_campos.addValueChangeListener(this);
		txt_nombre_reporte.addTextChangeListener(this);
		tb_vista_previa.setSizeFull();
		txt_nombre_reporte.setWidth("70%");
		cb_dependencia.setWidth("60%");
		// tb_vista_previa.setColumnReorderingAllowed(true);
		tb_vista_previa.setResponsive(true);
		tb_vista_previa.setColumnReorderingAllowed(true);
		tb_vista_previa.setWidth("100%");
		tb_vista_previa.setHeight("40px");
		tw_campos.setWidth("100%");
		for (String col : columns) {
			tw_campos.addItem(col);
			tw_campos.setItemCaption(col, col.replace("_", " ").replace("ACT", ""));
		}
		buildFormContent();
		buildcbDependencia();
		Responsive.makeResponsive(this);
		
	}
	
	private void buildcbDependencia() {
		cb_dependencia.setInputPrompt("Seleccione una Dependencia");
		cb_dependencia.setInvalidAllowed(false);
		for (Dependencia dependencia : dependenciaimpl.getall()) {
			cb_dependencia.addItem(dependencia);
			cb_dependencia.setItemCaption(dependencia, dependencia.getDEP_Nombre_Dependencia());
		}
		
	}
	
	private void buildFormContent() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 4.5f);
		setColumnExpandRatio(2, 1);
		addComponent(cb_dependencia, 0, 0, 1, 0);
		addComponent(this.tw_campos, 1, 1);
		addComponent(txt_nombre_reporte, 0, 2, 1, 2);
		addComponent(tb_vista_previa, 0, 3, 2, 3);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void valueChange(ValueChangeEvent event) {
		Set<String> select_columns = (Set<String>) tw_campos.getValue();
		for (String col : columns) {
			String property = col;
			tb_vista_previa.removeContainerProperty(property);
		}
		for (String vl : select_columns) {
			tb_vista_previa.addContainerProperty(vl, String.class, "", vl.replace("_", " ").replace("ACT", ""), null, null);
		}
	}
	
	@Override
	public void textChange(TextChangeEvent event) {
		tb_vista_previa.setCaption(this.txt_nombre_reporte.getValue().toUpperCase());
	}
	
	@SuppressWarnings("unchecked")
	public boolean validate() {
		this.mensajes = new ArrayList<BarMessage>();
		List<String> data = new ArrayList<String>((Collection<? extends String>) this.tb_vista_previa.getContainerPropertyIds());
		if (cb_dependencia.getValue() == null) {
			this.mensajes.add(new BarMessage(cb_dependencia.getCaption(), Messages.EMPTY_MESSAGE));
			return false;
		}
		if (txt_nombre_reporte.getValue() != null && !txt_nombre_reporte.getValue().equals("") && tw_campos.getValue() != null
				&& data.size() > 0) {
			
			return true;
		}
		this.mensajes.add(new BarMessage("Formulario", Messages.REPORT_MESSAGE));
		return false;
	}
	
	public List<BarMessage> getMensajes() {
		
		return this.mensajes;
	}
	
	public List<String> getColumnsNames() {
		List<String> result = new ArrayList<String>();
		String[] data = tb_vista_previa.getColumnHeaders();
		for (int i = 0; i < data.length; i++) {
			result.add(data[i]);
		}
		return result;
	}
	
	public String getSQL() {
		String[] data = tb_vista_previa.getColumnHeaders();
		for (int i = 0; i < data.length; i++) {
			data[i] = "ACT" + data[i].replace(" ", "_");
		}
		
		String result = StringHelper.implode(data, ",");
		System.out.println(result);
		return result;
	}
	
	public int[] getColumnssizes() {
		List<String> data = new ArrayList<String>();
		String[] array = tb_vista_previa.getColumnHeaders();
		for (int i = 0; i < array.length; i++) {
			data.add("ACT" + array[i].replace(" ", "_"));
		}
		
		int[] ints = new int[data.size()];
		int r = 0;
		int twidth = 0;
		for (String string : data) {
			twidth += tb_vista_previa.getColumnWidth(string);
		}
		for (String string : data) {
			if (tb_vista_previa.getColumnWidth(string) < 0) {
				ints[r] = 500 / data.size();
				System.out.println(ints[r]);
				r++;
				continue;
			}
			ints[r] = (int) (tb_vista_previa.getColumnWidth(string) * 500 / twidth);
			System.out.println(ints[r]);
			r++;
		}
		return ints;
	}
	
	public int getNumColumns() {
		return tb_vista_previa.getContainerPropertyIds().size();
	}
	
	public String getTitle() {
		return this.txt_nombre_reporte.getValue().toUpperCase();
	}
	
	public String getDependencia() {
		if (cb_dependencia.getValue() != null) {
			return ((Dependencia) cb_dependencia.getValue()).getDEP_Nombre_Dependencia();
		}
		return "";
	}
}
