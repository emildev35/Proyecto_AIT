package ait.sistemas.proyecto.activos.view.inve.inventario;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
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
import com.vaadin.ui.GridLayout;

public class FormReporte extends GridLayout implements ValueChangeListener{
	
	
	private static final long serialVersionUID = 1L;

	
	public ComboBox cb_Dependencia;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	final private DependenciaImpl dependencia_impl = new DependenciaImpl();
	final PropertysetItem pitm_Inventario = new PropertysetItem();
	private FieldGroup binder_Inventario;
	
	
	public FormReporte() {
		setColumns(1);
		setRows(3);
		setWidth("40%");
		setMargin(true);
		setSpacing(true);
		
		this.cb_Dependencia = new ComboBox("Elija una Dependencia");
		
		pitm_Inventario.addItemProperty("dependencia", new ObjectProperty<Short>((short)1));
		
		this.binder_Inventario = new FieldGroup(pitm_Inventario);
		
		this.binder_Inventario.bind(this.cb_Dependencia, "dependencia");
		this.binder_Inventario.clear();
		
		this.cb_Dependencia.addValidator(new NullValidator("", false));
		
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
		this.binder_Inventario.clear();
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
		String a = "ALL";
		cb_Dependencia.addItem(a);
		cb_Dependencia.setItemCaption(a, "Todas las Dependencias");
	}
	private void buildContent() {
		
		this.cb_Dependencia.setWidth("100%");
		
		setColumnExpandRatio(0, 1);
		
		addComponent(this.cb_Dependencia, 0,0);

	}
	public boolean validate(){
		try{
			this.binder_Inventario.commit();
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
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}

