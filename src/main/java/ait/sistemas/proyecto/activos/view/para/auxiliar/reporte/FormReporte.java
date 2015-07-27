package ait.sistemas.proyecto.activos.view.para.auxiliar.reporte;



import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
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

	
	public ComboBox cb_grupo;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	final private GrupoImpl grupo_impl = new GrupoImpl();
	
	final PropertysetItem pitm_Auxiliar = new PropertysetItem();
	private FieldGroup binder_Auxiliar;
	
	
	public FormReporte() {
		setColumns(1);
		setRows(1);
		setWidth("40%");
		setMargin(true);
		setSpacing(true);
		
		this.cb_grupo = new ComboBox("Elija el Grupo Contable");
		
		pitm_Auxiliar.addItemProperty("auxiliar", new ObjectProperty<Long>((long)1));
		
		this.binder_Auxiliar = new FieldGroup(pitm_Auxiliar);
		
		this.binder_Auxiliar.bind(this.cb_grupo, "auxiliar");
		
		this.cb_grupo.addValidator(new NullValidator("", false));
		
		fillGrupo();
		buildContent();
	}
	
	public void init(){
		update();
	}
	/**
	 * Actualizacion de los Campos
	 */
	public void update(){
		this.binder_Auxiliar.clear();
		this.cb_grupo.setValue((long)1);

	}
	/**
	 * Llenado del Combo Box 
	 */
	private void fillGrupo(){
		cb_grupo.setNullSelectionAllowed(false);
		cb_grupo.setInputPrompt("Seleccione un Grupo Contable");
		for (GruposContablesModel grupo : grupo_impl.getalls())
		{
			cb_grupo.addItem(grupo.getGRC_Grupo_Contable());
			cb_grupo.setItemCaption(grupo.getGRC_Grupo_Contable(), grupo.getGRC_Nombre_Grupo_Contable());
		}
	}
	private void buildContent() {
		this.binder_Auxiliar.clear();
		this.cb_grupo.setWidth("100%");
		addComponent(this.cb_grupo, 0,0);

	}
	public boolean validate(){
		try{
			this.cb_grupo.validate();
			return true;
		}catch(Exception e){
			this.mensajes.add(new BarMessage(this.cb_grupo.getCaption(), Messages.EMPTY_MESSAGE));
			return false;
		}
	}
	public List<BarMessage> getMessage(){
		return this.mensajes;
	}
}
