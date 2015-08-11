package ait.sistemas.proyecto.activos.view.para.nautorizacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Partidas_Presupuestaria;
import ait.sistemas.proyecto.activos.data.service.Impl.PartidaImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormAutorizacion extends GridLayout {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_partida;
	public TextField txt_nombre_partida;
	
	private List<BarMessage> mensajes;
	
	private PartidaImpl partida_impl = new PartidaImpl();
	private PropertysetItem pitm_Partida = new PropertysetItem();
	private FieldGroup binder_Partida;
	
	public FormAutorizacion() {
		
		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		
		this.txt_id_partida = new TextField("Codigo:");
		this.txt_nombre_partida = new TextField("Nombre de Partidas Presupuestarias: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Partida.addItemProperty("id_partida", new ObjectProperty<Short>((short) 1));
		pitm_Partida.addItemProperty("nombre_partida", new ObjectProperty<String>(""));
		
		this.binder_Partida = new FieldGroup(this.pitm_Partida);
		
		binder_Partida.bind(this.txt_id_partida, "id_partida");
		binder_Partida.bind(this.txt_nombre_partida, "nombre_partida");
		binder_Partida.clear();
		
		this.txt_nombre_partida.setRequired(true);
		this.txt_nombre_partida.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_partida.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 60), 3, 60, false));
		this.txt_id_partida.setEnabled(false);
		
		txt_id_partida.setWidth("90%");
		txt_nombre_partida.setWidth("90%");
		
		updateId();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		addComponent(this.txt_id_partida, 0, 0);
		addComponent(this.txt_nombre_partida, 1, 0);
		
	}
	
	public void update() {
		binder_Partida.clear();
	}
	
	public void updateId() {
		this.txt_id_partida.setValue(partida_impl.generateId() + "");
	}
	
	public void enabled() {
		this.txt_nombre_partida.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			this.binder_Partida.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.txt_nombre_partida.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_partida.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_partida.getCaption(), ex.getMessage()));
			}
			
			return false;
		}
	}
	
	public Partidas_Presupuestaria getData() {
		Partidas_Presupuestaria resul = new Partidas_Presupuestaria();
		resul.setPAP_Partida(Integer.parseInt(this.txt_id_partida.getValue()));
		resul.setPAP_Nombre_Partida(this.txt_nombre_partida.getValue());
		long lnMilis = new Date().getTime();
		resul.setPAP_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	
	public void setData(Partidas_Presupuestaria data) {
		this.txt_id_partida.setValue(String.valueOf(data.getPAP_Partida()));
		this.txt_nombre_partida.setValue(String.valueOf(data.getPAP_Nombre_Partida()));
		
	}
	
}
