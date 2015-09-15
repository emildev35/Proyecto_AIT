package ait.sistemas.proyecto.activos.view.para.grupo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.model.Grupos_Contable;
import ait.sistemas.proyecto.activos.data.model.Partidas_Presupuestaria;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.PartidaImpl;
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
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormGrupo extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	public TextField txt_id_grupo;
	public TextField txt_nombre_grupo;
	public TextField txt_vida_util;
	public TextField txt_coeficiente;
	public ComboBox cb_partida;

	private List<BarMessage> mensajes;

	private PartidaImpl partida_impl=new PartidaImpl();
	private GrupoImpl grupo_impl = new GrupoImpl();
	private PropertysetItem pitm_Grupo = new PropertysetItem();
	private FieldGroup binder_Grupo;

	public FormGrupo() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_grupo = new TextField("Codigo Grupo:");
		this.txt_nombre_grupo = new TextField("Nombre Grupo Contable: ");
		this.txt_vida_util = new TextField("Vida Util: ");
		this.txt_coeficiente = new TextField("Coeficiente: ");
		this.cb_partida = new ComboBox("Partida Presupuestaria:");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Grupo.addItemProperty("id_grupo", new ObjectProperty<String>(""));
		pitm_Grupo.addItemProperty("nombre_grupo", new ObjectProperty<String>(""));
		pitm_Grupo.addItemProperty("vida_util", new ObjectProperty<String>(""));
		pitm_Grupo.addItemProperty("coeficiente", new ObjectProperty<String>(""));
		pitm_Grupo.addItemProperty("id_partida", new ObjectProperty<Integer>((int) 1));
		

		this.binder_Grupo = new FieldGroup(this.pitm_Grupo);

		binder_Grupo.bind(this.txt_id_grupo, "id_grupo");
		binder_Grupo.bind(this.txt_nombre_grupo, "nombre_grupo");
		binder_Grupo.bind(this.txt_vida_util, "vida_util");
		binder_Grupo.bind(this.txt_coeficiente, "coeficiente");
		binder_Grupo.bind(this.cb_partida, "id_partida");
		binder_Grupo.clear();

		this.txt_id_grupo.setRequired(true);
		this.txt_id_grupo.addValidator(new NullValidator("No Nulo", false));
		this.txt_id_grupo.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 2), 1,2,false));
		this.txt_id_grupo.setEnabled(false);
		this.txt_nombre_grupo.setRequired(true);
		this.txt_nombre_grupo.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_grupo.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_vida_util.setRequired(true);
		this.txt_vida_util.addValidator(new NullValidator("No Nulo", false));
		this.txt_vida_util.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 5), 1,5,false));
		this.txt_coeficiente.setRequired(true);
		this.txt_coeficiente.addValidator(new NullValidator("No Nulo", false));
		this.txt_coeficiente.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 5), 1,5,false));
		this.cb_partida.setRequired(true);
		this.cb_partida.addValidator(new NullValidator("No Nulo", false));
		
		txt_id_grupo.setWidth("90%");
		txt_nombre_grupo.setWidth("90%");
		txt_vida_util.setWidth("90%");
		txt_coeficiente.setWidth("90%");
		cb_partida.setWidth("90%");

		updateId();
		fillsigla();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void updateId() {
		this.txt_id_grupo.setValue(grupo_impl.getId() + "");
		
	}
	private void fillsigla() {
		cb_partida.setNullSelectionAllowed(false);
		cb_partida.setInputPrompt("Seleccione la Partida Presupuestaria");
		for (Partidas_Presupuestaria partida : partida_impl.getall())
		{
			cb_partida.addItem(partida.getPAP_Partida());
			cb_partida.setItemCaption(partida.getPAP_Partida(), partida.getPAP_Nombre_Partida());
		}
	}
	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		setColumnExpandRatio(2, 2);
		setColumnExpandRatio(3, 0.5f);
		addComponent(this.txt_id_grupo, 0,0);
		addComponent(this.cb_partida, 1,0);
		addComponent(this.txt_nombre_grupo, 2,0);
		addComponent(this.txt_vida_util, 3,0);
		addComponent(this.txt_coeficiente, 3,1);
	}
	
	public void update(){
		binder_Grupo.clear();
	}

	public void enabled(){
		this.txt_id_grupo.setEnabled(false);
		this.txt_nombre_grupo.setEnabled(false);
		this.txt_vida_util.setEnabled(false);
		this.txt_coeficiente.setEnabled(false);
		this.cb_partida.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Grupo.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.txt_id_grupo.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_id_grupo.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_id_grupo.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_nombre_grupo.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_grupo.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_grupo.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_vida_util.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_vida_util.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_vida_util.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_coeficiente.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_coeficiente.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_coeficiente.getCaption(), ex.getMessage()));
			}
			try {
				this.cb_partida.validate();
				}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_partida.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_partida.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public Grupos_Contable getData(){
		Grupos_Contable resul = new Grupos_Contable();
		resul.setGRC_Grupo_Contable(this.txt_id_grupo.getValue());
		resul.setGRC_Nombre_Grupo_Contable(this.txt_nombre_grupo.getValue());
		resul.setGRC_Vida_Util(Short.parseShort(this.txt_vida_util.getValue()));
		resul.setGRC_Coeficiente(new Double(this.txt_coeficiente.getValue()));
		resul.setGRC_Partida((Integer)this.cb_partida.getValue());
		long lnMilis = new Date().getTime();
		resul.setGRC_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(GruposContablesModel data){	
		this.txt_id_grupo.setValue(String.valueOf(data.getGRC_Grupo_Contable()));
		this.txt_nombre_grupo.setValue(String.valueOf(data.getGRC_Nombre_Grupo_Contable()));
		this.txt_vida_util.setValue(String.valueOf(data.getGRC_Vida_Util()));
		this.txt_coeficiente.setValue(String.valueOf(data.getGRC_Coeficiente()));
		this.cb_partida.setValue((Integer)data.getGRC_Partida_ID());
				
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}

