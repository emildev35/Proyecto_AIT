package ait.sistemas.proyecto.activos.view.rrhh.ubicacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model_rrhh.InmuebleModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.UbicacionesFisicasModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Ubicaciones_Fisica;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.InmuebleImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UbicacionImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

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
import com.vaadin.ui.UI;

public class FormUbicacion extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	private TextField txt_id_ubicacion;
	public TextField txt_nombre_ubicacion;
	public ComboBox cb_inmueble;

	private List<BarMessage> mensajes;

	private UbicacionImpl ubicacion_impl = new UbicacionImpl();
	private InmuebleImpl inmueble_impl=new InmuebleImpl();
	private DependenciaImpl dependencia_impl = new DependenciaImpl(); 
	private PropertysetItem pitm_Ubicacion = new PropertysetItem();
	private FieldGroup binder_Ubicacion;

	public FormUbicacion() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_ubicacion = new TextField("Codigo:");
		this.txt_nombre_ubicacion = new TextField("Nombre de la Ubicacion Fisica: ");
		this.cb_inmueble = new ComboBox("Inmueble:");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Ubicacion.addItemProperty("id_ubicacion", new ObjectProperty<Integer>((int)1));
		pitm_Ubicacion.addItemProperty("nombre_ubicacion", new ObjectProperty<String>(""));
		pitm_Ubicacion.addItemProperty("id_inmueble", new ObjectProperty<Short>((short) 1));
		

		this.binder_Ubicacion = new FieldGroup(this.pitm_Ubicacion);

		binder_Ubicacion.bind(this.txt_id_ubicacion, "id_ubicacion");
		binder_Ubicacion.bind(this.txt_nombre_ubicacion, "nombre_ubicacion");
		binder_Ubicacion.bind(this.cb_inmueble, "id_inmueble");
		binder_Ubicacion.clear();

		this.txt_nombre_ubicacion.setRequired(true);
		this.txt_nombre_ubicacion.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_ubicacion.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.cb_inmueble.setRequired(true);
		this.cb_inmueble.addValidator(new NullValidator("No Nulo", false));
		this.txt_id_ubicacion.setEnabled(false);	
		
		txt_id_ubicacion.setWidth("90%");
		txt_nombre_ubicacion.setWidth("90%");
		cb_inmueble.setWidth("90%");

		updateId();
		fillcbInmuelbe();
		buildContent();
		Responsive.makeResponsive(this);
	}
	private void fillcbInmuelbe() {
		cb_inmueble.setNullSelectionAllowed(false);
		cb_inmueble.setInputPrompt("Seleccione el Inmueble");
		SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
		
		 short dependencia = dependencia_impl.getdependencia_ID(usuario.getDependecia());
		for (InmuebleModel inmueble : inmueble_impl.getalls(dependencia))
		{
			cb_inmueble.addItem(inmueble.getINM_Inmueble());
			cb_inmueble.setItemCaption(inmueble.getINM_Inmueble(), inmueble.getINM_Nombre_Inmueble());
		}
	}
	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		setColumnExpandRatio(2, 2);
		addComponent(this.txt_id_ubicacion, 0,0);
		addComponent(this.txt_nombre_ubicacion, 1,0);
		addComponent(this.cb_inmueble, 2,0);

	}
	
	public void update(){
		binder_Ubicacion.clear();
	}
	public void updateId(){
		SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
		 short dependencia = dependencia_impl.getdependencia_ID(usuario.getDependecia());
		this.txt_id_ubicacion.setValue(ubicacion_impl.generateId(dependencia) + "");
	}
	public void enabled(){
		this.txt_nombre_ubicacion.setEnabled(false);
		this.cb_inmueble.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Ubicacion.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
			try {
				this.txt_nombre_ubicacion.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_ubicacion.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_ubicacion.getCaption(), ex.getMessage()));
			}
				
			try {
				this.cb_inmueble.validate();
				}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(cb_inmueble.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(cb_inmueble.getCaption(), ex.getMessage()));
			}
			return false;
		}		
	}
	public Ubicaciones_Fisica getData(){
		Ubicaciones_Fisica resul = new Ubicaciones_Fisica();
		SessionModel usuario = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
		 short dependencia = dependencia_impl.getdependencia_ID(usuario.getDependecia());
		resul.setUBF_Dependencia(dependencia);
		resul.setUBF_Ubicacion_Fisica(Integer.parseInt(this.txt_id_ubicacion.getValue()));
		resul.setUBF_Nombre_Ubicacion_Fisica(this.txt_nombre_ubicacion.getValue());
		resul.setUBF_Inmueble((Short)this.cb_inmueble.getValue());
		long lnMilis = new Date().getTime();
		resul.setUBF_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	public void setData(UbicacionesFisicasModel data){	
		this.txt_id_ubicacion.setValue(String.valueOf(data.getUBF_Ubicacion_Fisica()));
		this.txt_nombre_ubicacion.setValue(String.valueOf(data.getUBF_Nombre_Ubicacion_Fisica()));
		this.cb_inmueble.setValue((Short)data.getUBF_Inmueble_ID());
				
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}

