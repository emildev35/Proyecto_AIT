package ait.sistemas.proyecto.activos.view.inve.reporte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import ait.sistemas.proyecto.activos.data.service.Impl.ReporteImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.util.StringHelper;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;

public class FormReporte extends GridLayout implements ValueChangeListener, TextChangeListener {
	
	private static final long serialVersionUID = 1L;
	TwinColSelect tw_campos = new TwinColSelect();
	Table tb_vista_previa = new Table();
	TextField txt_nombre_reporte = new TextField("Nombre del Reporte");
	final ReporteImpl reporteimpl = new ReporteImpl();
	final List<String> columns = reporteimpl.getColumnName("Reporte_Activos");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	public FormReporte() {
		
		super(2, 2);
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		tw_campos.setNullSelectionAllowed(false);
		tw_campos.setMultiSelect(true);
		tw_campos.setLeftColumnCaption("Columnas del Reporte");
		tw_campos.setRightColumnCaption("Campos de Activos");
		tw_campos.addValueChangeListener(this);
		txt_nombre_reporte.addTextChangeListener(this);
		tb_vista_previa.setSizeFull();
		txt_nombre_reporte.setWidth("90%");
		// tb_vista_previa.setColumnReorderingAllowed(true);
		tb_vista_previa.setResponsive(true);
		tb_vista_previa.setWidth("100%");
		tw_campos.setWidth("100%");
		for (String col : columns) {
			tw_campos.addItem(col);
			tw_campos.setItemCaption(col, col.replace("_", " ").replace("ACT", ""));
		}
		buildFormContent();
		Responsive.makeResponsive(this);
		
	}
	
	private void buildFormContent() {
		setColumnExpandRatio(0, 1.9f);
		setColumnExpandRatio(1, 2f);
		addComponent(txt_nombre_reporte, 0, 0);
		addComponent(this.tw_campos, 0, 1);
		addComponent(tb_vista_previa, 1, 0, 1, 1);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void valueChange(ValueChangeEvent event) {
		Set<String> select_columns = (Set<String>) tw_campos.getValue();
		
		for (String col : columns) {
			tb_vista_previa.removeContainerProperty(col.replace("_", " ").replace("ACT", ""));
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
		List<String> data = new ArrayList<String>((Collection<? extends String>) this.tb_vista_previa.getContainerPropertyIds());
		if (txt_nombre_reporte.getValue() != null && !txt_nombre_reporte.getValue().equals("") && tw_campos.getValue() != null
				&& data.size() > 0) {
			return true;
		}
		return false;
	}
	
	public List<BarMessage> getMensajes() {
		this.mensajes.add(new BarMessage("Formulario", Messages.REPORT_MESSAGE));
		return this.mensajes;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getColumnsNames() {
		List<String> data = new ArrayList<String>((Collection<? extends String>) this.tb_vista_previa.getContainerPropertyIds());
		List<String> result = new ArrayList<String>();
		for (String string : data) {
			result.add(string.replace("_", " ").replace("ACT", ""));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String getSQL() {
		List<String> data = new ArrayList<String>((Collection<? extends String>) this.tb_vista_previa.getContainerPropertyIds());
		return StringHelper.implode(data, ",");
	}
	
	@SuppressWarnings("unchecked")
	public int[] getColumnssizes() {
		List<String> data = new ArrayList<String>((Collection<? extends String>) this.tb_vista_previa.getContainerPropertyIds());
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
}
