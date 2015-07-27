package ait.sistemas.proyecto.activos.view.rrhh.personal.reporte;



import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;

public class FormReporte extends GridLayout{
	
	
	private static final long serialVersionUID = 1L;

	
	public ComboBox cbDependencia;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	final private DependenciaImpl dependencia_impl = new DependenciaImpl();
	
	final PropertysetItem pitmPersonal = new PropertysetItem();
	private FieldGroup binderPersonal;
	
	
	public FormReporte() {
		setColumns(1);
		setRows(1);
		setWidth("40%");
		setMargin(true);
		setSpacing(true);
		
		this.cbDependencia = new ComboBox("Elija la Dependencia");
		
		pitmPersonal.addItemProperty("personal", new ObjectProperty<Long>((long)1));
		
		this.binderPersonal = new FieldGroup(pitmPersonal);
		
		this.binderPersonal.bind(this.cbDependencia, "personal");
		
		this.cbDependencia.addValidator(new NullValidator("", false));
		
		fillSubsistema();
		buildContent();
	}
	
	public void init(){
		update();
	}
	/**
	 * Actualizacion de los Campos
	 */
	public void update(){
		this.binderPersonal.clear();
		this.cbDependencia.setValue((long)1);

	}
	/**
	 * Llenado del Combo Box 
	 */
	private void fillSubsistema(){
		cbDependencia.setNullSelectionAllowed(false);
		cbDependencia.setInputPrompt("Seleccione una Dependencia");
		for (Dependencia dependencia : dependencia_impl.getall())
		{
			cbDependencia.addItem(dependencia.getDEP_Dependencia());
			cbDependencia.setItemCaption(dependencia.getDEP_Dependencia(), dependencia.getDEP_Nombre_Dependencia());
		}
	}
	private void buildContent() {
		this.binderPersonal.clear();
		this.cbDependencia.setWidth("100%");
		addComponent(this.cbDependencia, 0,0);

	}
	public boolean validate(){
		try{
			this.cbDependencia.validate();
			return true;
		}catch(Exception e){
			this.mensajes.add(new BarMessage(this.cbDependencia.getCaption(), Messages.EMPTY_MESSAGE));
			return false;
		}
	}
	public List<BarMessage> getMessage(){
		return this.mensajes;
	}
}
