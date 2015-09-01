package ait.sistemas.proyecto.activos.view.reva.resumenact;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Cierre_Gestion;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.CierreGestionImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class FormReporte extends GridLayout implements ValueChangeListener{
	
	
	private static final long serialVersionUID = 1L;

	
	public ComboBox cb_Dependencia;
	public TextField txt_ufvi;
	public DateField dtf_fecha_ultima_depre;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	final private DependenciaImpl dependencia_impl = new DependenciaImpl();
	final private CierreGestionImpl cierre_gestion_impl = new CierreGestionImpl(); 
	final PropertysetItem pitm_Actualizacion = new PropertysetItem();
	private FieldGroup binder_Actualizacion;
	
	
	public FormReporte() {
		setColumns(1);
		setRows(3);
		setWidth("40%");
		setMargin(true);
		setSpacing(true);
		
		this.cb_Dependencia = new ComboBox("Dependencia");
		this.dtf_fecha_ultima_depre = new DateField("Fecha Ultima Depreciacion:");
		this.txt_ufvi = new TextField("Tipo de Cambio UFV");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Actualizacion.addItemProperty("dependencia", new ObjectProperty<Short>((short)1));
		pitm_Actualizacion.addItemProperty("fecha_ultima_depre", new ObjectProperty<Date>(new Date()));
		pitm_Actualizacion.addItemProperty("ufvi", new ObjectProperty<String>(""));
		
		this.binder_Actualizacion = new FieldGroup(pitm_Actualizacion);
		
		this.binder_Actualizacion.bind(this.cb_Dependencia, "dependencia");
		binder_Actualizacion.bind(this.dtf_fecha_ultima_depre, "fecha_ultima_depre");
		binder_Actualizacion.bind(this.txt_ufvi, "ufvi");
		this.binder_Actualizacion.clear();
		
		this.cb_Dependencia.addValidator(new NullValidator("", false));
		this.txt_ufvi.setEnabled(false);
		this.dtf_fecha_ultima_depre.setEnabled(false);
		
		setData((Cierre_Gestion) cierre_gestion_impl.getall());
		fillcbGrupo();
		buildContent();
	}
	
	public void init(){
		update();
	}
	/**
	 * Actualizacion de los Campos
	 */
	public void update(){
		this.binder_Actualizacion.clear();
		this.cb_Dependencia.setValue((String)(""));
	}
	/**
	 * Llenado del Combo Box 
	 */
	private void fillcbGrupo(){
		cb_Dependencia.setNullSelectionAllowed(false);
		cb_Dependencia.setInputPrompt("Seleccione una Dependencia");
		for (Dependencia dependencia : dependencia_impl.getall())
		{
			cb_Dependencia.addItem(dependencia.getDEP_Dependencia());
			cb_Dependencia.setItemCaption(dependencia.getDEP_Dependencia(), dependencia.getDEP_Nombre_Dependencia());
		}
//		short a = 0;
//		cb_Dependencia.addItem(a);
//		cb_Dependencia.setItemCaption(a, "Todas las Dependencias");
	}
	private void buildContent() {
		
		this.cb_Dependencia.setWidth("100%");
		
		Panel pn_valor_actual = new Panel(
				"Seleccione la Dependencia");
		Panel pn_ultimo_valor = new Panel("Datos de la ultima Actualizacion y Depreciacion");

		GridLayout grid_valor = new GridLayout(1, 1);
		grid_valor.setSizeFull();
		grid_valor.setMargin(true);
		grid_valor.addComponent(this.cb_Dependencia, 0, 0);
		pn_valor_actual.setContent(grid_valor);
		this.addComponent(pn_valor_actual, 0, 0);

		GridLayout grid_ultimo = new GridLayout(2, 1);
		grid_ultimo.setSizeFull();
		grid_ultimo.setMargin(true);
		grid_ultimo.addComponent(this.dtf_fecha_ultima_depre, 0, 0);
		grid_ultimo.addComponent(this.txt_ufvi, 1, 0);
		pn_ultimo_valor.setContent(grid_ultimo);
		this.addComponent(pn_ultimo_valor, 0, 1);

	}
	public boolean validate(){
		try{
			this.binder_Actualizacion.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
		try{
			this.cb_Dependencia.validate();
		}catch(Exception ex){
			this.mensajes.add(new BarMessage(this.cb_Dependencia.getCaption(), Messages.EMPTY_MESSAGE));
		}
		
		return false;
		}
	}
	public List<BarMessage> getMessage(){
		return this.mensajes;
	}
	public void setData(Cierre_Gestion data) {
		this.txt_ufvi.setValue(String.valueOf(data.getCGE_Tipo_Cambio_UFV()));
		this.dtf_fecha_ultima_depre.setValue(data.getCGE_Fecha_Cierre_Gestion());

	}
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}

