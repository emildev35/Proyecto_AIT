package ait.sistemas.proyecto.activos.view.para.auxiliar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.Auxiliares_Contable;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class FormAuxiliar extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	public ComboBox cb_grupo;
	public TextField txt_id_auxiliar;
	public TextField txt_nombre_auxiliar;

	private List<BarMessage> mensajes;

	private GrupoImpl grupo_impl=new GrupoImpl();
	private PropertysetItem pitm_Auxiliar = new PropertysetItem();
	private FieldGroup binder_Axuliar;

	public FormAuxiliar() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.cb_grupo = new ComboBox("Gupos Contables:");
		this.txt_id_auxiliar = new TextField("Codigo Auxiliar:");
		this.txt_nombre_auxiliar = new TextField("Nombre Auxiliar Contable: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Auxiliar.addItemProperty("id_grupo", new ObjectProperty<String>(""));
		pitm_Auxiliar.addItemProperty("id_auxiliar", new ObjectProperty<String>(""));
		pitm_Auxiliar.addItemProperty("nombre_auxiliar", new ObjectProperty<String>(""));
		

		this.binder_Axuliar = new FieldGroup(this.pitm_Auxiliar);

		binder_Axuliar.bind(this.cb_grupo, "id_grupo");
		binder_Axuliar.bind(this.txt_id_auxiliar, "id_auxiliar");
		binder_Axuliar.bind(this.txt_nombre_auxiliar, "nombre_auxiliar");
		binder_Axuliar.clear();

		this.cb_grupo.setRequired(true);
		this.cb_grupo.addValidator(new NullValidator("No Nulo", false));
		this.txt_id_auxiliar.setRequired(true);
		this.txt_id_auxiliar.addValidator(new NullValidator("No Nulo", false));
		this.txt_id_auxiliar.addValidator(new RegexpValidator("[0-9]{1,2}", "Campo solo de numeros no mayor a 99"));
		this.txt_nombre_auxiliar.setRequired(true);
		this.txt_nombre_auxiliar.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_auxiliar.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		
		cb_grupo.setWidth("90%");
		txt_id_auxiliar.setWidth("90%");
		txt_nombre_auxiliar.setWidth("90%");

		fillsigla();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void fillsigla() {
		cb_grupo.setNullSelectionAllowed(false);
		cb_grupo.setInputPrompt("Seleccione el Grupo Contable");
		for (GruposContablesModel partida : grupo_impl.getalls())
		{
			cb_grupo.addItem(partida.getGRC_Grupo_Contable());
			cb_grupo.setItemCaption(partida.getGRC_Grupo_Contable(), partida.getGRC_Nombre_Grupo_Contable());
		}
	}
	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 1);
		addComponent(this.cb_grupo, 0,0);
		addComponent(this.txt_id_auxiliar, 0,1);
		addComponent(this.txt_nombre_auxiliar, 1,1);

	}
	
	public void update(){
		binder_Axuliar.clear();
	}

	public void enabled(){
		this.cb_grupo.setEnabled(false);
		this.txt_id_auxiliar.setEnabled(false);
		this.txt_nombre_auxiliar.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Axuliar.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.cb_grupo.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_grupo.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_grupo.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_id_auxiliar.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_id_auxiliar.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_id_auxiliar.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_nombre_auxiliar.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_auxiliar.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_auxiliar.getCaption(), ex.getMessage()));
			}
			
			return false;
		}		
	}
	public Auxiliares_Contable getData(){
		Auxiliares_Contable resul = new Auxiliares_Contable();
		resul.setAUC_Grupo_Contable((String)this.cb_grupo.getValue());
		resul.setAUC_Auxiliar_Contable(this.txt_id_auxiliar.getValue());
		resul.setAUC_Nombre_Auxiliar_Contable(this.txt_nombre_auxiliar.getValue());
		long lnMilis = new Date().getTime();
		resul.setAUC_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(AuxiliaresContablesModel data){	
		this.cb_grupo.setValue((String)data.getAUC_Grupo_Contable_ID());
		this.txt_id_auxiliar.setValue(String.valueOf(data.getAUC_Auxiliar_Contable()));
		this.txt_nombre_auxiliar.setValue(String.valueOf(data.getAUC_Nombre_Auxiliar_Contable()));
				
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}

