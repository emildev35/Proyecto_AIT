package ait.sistemas.proyecto.activos.view.rrhh.unidad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.model_rrhh.UnidadesOrganizacionalesModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Unidades_Organizacionale;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UnidadImpl;
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

public class FormUnidad extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	private TextField txt_id_unidad;
	public TextField txt_nombre_unidad;
	public ComboBox cb_dependencia;

	private List<BarMessage> mensajes;

	private UnidadImpl unidad_impl = new UnidadImpl();
	private DependenciaImpl dependencia_impl=new DependenciaImpl();
	private PropertysetItem pitm_Unidad = new PropertysetItem();
	private FieldGroup binder_Unidad;

	public FormUnidad() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_unidad = new TextField("Codigo Unidad:");
		this.txt_nombre_unidad = new TextField("Nombre Unidad Organizacional: ");
		this.cb_dependencia = new ComboBox("Dependencia:");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Unidad.addItemProperty("id_unidad", new ObjectProperty<Short>((short)1));
		pitm_Unidad.addItemProperty("nombre_unidad", new ObjectProperty<String>(""));
		pitm_Unidad.addItemProperty("id_dependencia", new ObjectProperty<Short>((short) 1));
		

		this.binder_Unidad = new FieldGroup(this.pitm_Unidad);

		binder_Unidad.bind(this.txt_id_unidad, "id_unidad");
		binder_Unidad.bind(this.txt_nombre_unidad, "nombre_unidad");
		binder_Unidad.bind(this.cb_dependencia, "id_dependencia");
		binder_Unidad.clear();

		this.txt_nombre_unidad.setRequired(true);
		this.txt_nombre_unidad.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_unidad.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.cb_dependencia.setRequired(true);
		this.cb_dependencia.addValidator(new NullValidator("No Nulo", false));
		this.txt_id_unidad.setEnabled(false);	
		
		txt_id_unidad.setWidth("90%");
		txt_nombre_unidad.setWidth("90%");
		cb_dependencia.setWidth("90%");

		updateId();
		fillsigla();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void fillsigla() {
		cb_dependencia.setNullSelectionAllowed(false);
		cb_dependencia.setInputPrompt("Seleccione la Dependencia");
		for (Dependencia dependencia : dependencia_impl.getall())
		{
			cb_dependencia.addItem(dependencia.getDEP_Dependencia());
			cb_dependencia.setItemCaption(dependencia.getDEP_Dependencia(), dependencia.getDEP_Nombre_Dependencia());
		}
	}
	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		setColumnExpandRatio(2, 2);
		addComponent(this.txt_id_unidad, 0,0);
		addComponent(this.cb_dependencia, 1,0);
		addComponent(this.txt_nombre_unidad, 2,0);

	}
	
	public void update(){
		binder_Unidad.clear();
	}
	public void updateId(){
		this.txt_id_unidad.setValue(unidad_impl.generateId() + "");
	}
	public void enabled(){
		this.txt_nombre_unidad.setEnabled(false);
		this.cb_dependencia.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Unidad.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.txt_nombre_unidad.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_unidad.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_unidad.getCaption(), ex.getMessage()));
			}
				
			try {
				this.cb_dependencia.validate();
				}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_dependencia.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_dependencia.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public Unidades_Organizacionale getData(){
		Unidades_Organizacionale resul = new Unidades_Organizacionale();
		resul.setUNO_Unidad_Organizacional(Short.parseShort(this.txt_id_unidad.getValue()));
		resul.setUNO_Nombre_Unidad_Organizacional(this.txt_nombre_unidad.getValue());
		resul.setUNO_Dependencia((Short)this.cb_dependencia.getValue());
		long lnMilis = new Date().getTime();
		resul.setUNO_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(UnidadesOrganizacionalesModel data){	
		this.txt_id_unidad.setValue(String.valueOf(data.getUNO_Unidad_Organizacional()));
		this.txt_nombre_unidad.setValue(String.valueOf(data.getUNO_Nombre_Unidad_Organizacional()));
		this.cb_dependencia.setValue((Short)data.getUNO_Dependencia_ID());
				
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}

