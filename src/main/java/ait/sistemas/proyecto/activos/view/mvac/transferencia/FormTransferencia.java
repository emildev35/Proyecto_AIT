package ait.sistemas.proyecto.activos.view.mvac.transferencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
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
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormTransferencia extends GridLayout implements ValueChangeListener{
	private static final long serialVersionUID = 1L;
	public TextField txt_no_solicitud;
	public DateField dtf_fecha;
	public TextField txt_nombre_solicitante;
	public TextField txt_no_acta;
	public DateField dtf_fecha_acta;
	public TextField txt_dependencia_origen;
	public TextField txt_dependencia_destino;

	private List<BarMessage> mensajes;
	private final MovimientoImpl movimiento_impl = new MovimientoImpl();
	private PropertysetItem pitm_Transferencia = new PropertysetItem();
	private FieldGroup binder_Transferencia;

	public FormTransferencia() {

		super.setColumns(9);
		super.setRows(7);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_no_solicitud = new TextField("N° Solicitud:");
		this.dtf_fecha = new DateField("Fecha:");
		this.txt_dependencia_origen = new TextField("Dependencia Origen:");
		this.txt_dependencia_destino = new TextField("Dependencia Destino:");
		this.txt_nombre_solicitante = new TextField("Solicitante:");
		this.txt_no_acta = new TextField("Numero Acta:"); 
		this.dtf_fecha_acta = new DateField("Fecha Acta:");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Transferencia.addItemProperty("no_solicitud", new ObjectProperty<String>(""));
		pitm_Transferencia.addItemProperty("fecha", new ObjectProperty<String>(""));
		pitm_Transferencia.addItemProperty("dependencia_origen", new ObjectProperty<String>(""));
		pitm_Transferencia.addItemProperty("dependencia_destino", new ObjectProperty<String>(""));
		pitm_Transferencia.addItemProperty("nombre_solicitante", new ObjectProperty<String>(""));
		pitm_Transferencia.addItemProperty("no_acta", new ObjectProperty<String>(""));
		pitm_Transferencia.addItemProperty("fecha_acta", new ObjectProperty<String>(""));
		

		this.binder_Transferencia = new FieldGroup(this.pitm_Transferencia);

		binder_Transferencia.bind(this.txt_no_solicitud, "no_solicitud");
		binder_Transferencia.bind(this.dtf_fecha, "fecha");
		binder_Transferencia.bind(this.txt_dependencia_origen, "dependencia_origen");
		binder_Transferencia.bind(this.txt_dependencia_destino, "dependencia_destino");
		binder_Transferencia.bind(this.txt_nombre_solicitante, "nombre_solicitante");
		binder_Transferencia.bind(this.txt_no_acta, "no_acta");
		binder_Transferencia.bind(this.dtf_fecha_acta, "fecha_acta");
		binder_Transferencia.clear();

		this.txt_no_solicitud.setEnabled(false);
		this.txt_no_solicitud.setRequired(true);
		this.txt_no_solicitud.addValidator(new NullValidator("No Nulo", false));
		this.txt_no_solicitud.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 10), 1,10,false));
		this.txt_dependencia_origen.setEnabled(false);
		this.txt_dependencia_destino.setEnabled(false);
		this.txt_dependencia_origen.setRequired(true);
		this.txt_dependencia_destino.setRequired(true);
		this.txt_nombre_solicitante.setEnabled(false);
		this.txt_nombre_solicitante.setRequired(true);
		this.txt_nombre_solicitante.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_solicitante.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,50,false));
		this.txt_no_acta.setEnabled(false);
		this.txt_no_acta.setRequired(true);
		this.txt_no_acta.addValidator(new NullValidator("No Nulo", false));
		this.txt_no_acta.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(1, 10), 1,10,false));
		this.dtf_fecha.setRequired(true);
		this.dtf_fecha.setEnabled(false);
		this.dtf_fecha_acta.setRequired(true);
		this.dtf_fecha_acta.setEnabled(false);
		
		txt_no_solicitud.setWidth("90%");
		txt_dependencia_origen.setWidth("90%");
		txt_dependencia_destino.setWidth("90%");
		txt_nombre_solicitante.setWidth("90%");
		dtf_fecha.setWidth("90%");
		txt_no_acta.setWidth("90%");
		dtf_fecha_acta.setWidth("90%");

		buildContent();
		buidId();
		Responsive.makeResponsive(this);
	}
	public void buidId() {
		this.txt_no_acta.setValue(String.valueOf(movimiento_impl.getId((short)6)));
		this.dtf_fecha_acta.setValue(new Date());
	}
	public void update(){
		binder_Transferencia.clear();
	}
	private void buildContent() {
		Panel pn_solicitud = new Panel("Solicitud de Activos");
		Panel pn_acta = new Panel("Acta de Entrega");
		
		GridLayout gridl_solicitud = new GridLayout(2, 3);
		gridl_solicitud.setSizeFull();
		// gridl_solicitud.setMargin(true);
		gridl_solicitud.addComponent(this.txt_no_solicitud, 0, 0);
		gridl_solicitud.addComponent(this.dtf_fecha, 1, 0);
		gridl_solicitud.addComponent(this.txt_dependencia_origen, 0, 1);
		gridl_solicitud.addComponent(this.txt_dependencia_destino, 1, 1);
		gridl_solicitud.addComponent(this.txt_nombre_solicitante, 0, 2);
		pn_solicitud.setContent(gridl_solicitud);
		
		this.addComponent(pn_solicitud, 0, 5, 6, 6);

		GridLayout gridl_resolucion = new GridLayout(2, 1);
		gridl_resolucion.setSizeFull();
		// gridl_solicitud.setMargin(true);
		gridl_resolucion.addComponent(this.txt_no_acta, 0, 0);
		gridl_resolucion.addComponent(this.dtf_fecha_acta, 1, 0);
		pn_acta.setContent(gridl_resolucion);
		this.addComponent(pn_acta, 7, 5, 8, 5);

	}
	

	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	public void clearMessages(){
		this.mensajes = new ArrayList<BarMessage>();
	}
	public boolean validate(){

		try{
			this.binder_Transferencia.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		}catch(CommitException e){
	
			try {
				this.txt_no_solicitud.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_no_solicitud.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_no_solicitud.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_nombre_solicitante.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_nombre_solicitante.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_solicitante.getCaption(), ex.getMessage()));
			}
			try {
				this.txt_no_acta.validate();
			}catch(EmptyValueException ex){
				this.mensajes.add(new BarMessage(txt_no_acta.getCaption(), Messages.EMPTY_MESSAGE));
			}catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_no_acta.getCaption(), ex.getMessage()));
			}
			
			return false;
		}		
	}
	public Movimiento getData(){
		Movimiento resul = new Movimiento();
		resul.setNro_documento(Long.parseLong( this.txt_no_solicitud.getValue()));
		resul.setNo_acta(Long.parseLong( this.txt_no_acta.getValue()));
		resul.setFecha_acta(new java.sql.Date( new Date().getTime()));
		resul.setTipo_movimiento((short)5);
		resul.setTipo_movimiento_nuevo((short)6);
		return resul;
	}
	public void setData(Movimiento data){	
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		
		this.txt_no_solicitud.setValue(String.valueOf(data.getNro_documento()));
		this.dtf_fecha.setValue(new Date(data.getFecha_registro().getTime()));
		this.txt_nombre_solicitante.setValue(String.valueOf(data.getSolicitante()));
		this.txt_dependencia_origen.setValue(data.getDependencia());
		this.txt_dependencia_destino.setValue(usuario.getDependecia());
	}
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}

