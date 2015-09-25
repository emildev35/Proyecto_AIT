package ait.sistemas.proyecto.activos.view.para.organismo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Organismo_Financiador;
import ait.sistemas.proyecto.activos.data.service.Impl.OrganismoImpl;
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
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormOrganismo extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_organismo;
	public TextField txt_nombre_organismo;
	
	private List<BarMessage> mensajes;
	
	private OrganismoImpl organismo_impl = new OrganismoImpl();
	private PropertysetItem pitm_Organismo = new PropertysetItem();
	private FieldGroup binder_Organismo;
	
	public FormOrganismo() {
		
		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");
		
		this.txt_id_organismo = new TextField("Codigo:");
		this.txt_nombre_organismo = new TextField("Nombre Organismo Financiadoro: ");
		this.mensajes = new ArrayList<BarMessage>();
		
		pitm_Organismo.addItemProperty("id_organismo", new ObjectProperty<Short>((short) 1));
		pitm_Organismo.addItemProperty("nombre_organismo", new ObjectProperty<String>(""));
		
		this.binder_Organismo = new FieldGroup(this.pitm_Organismo);
		
		binder_Organismo.bind(this.txt_id_organismo, "id_organismo");
		binder_Organismo.bind(this.txt_nombre_organismo, "nombre_organismo");
		binder_Organismo.clear();
		
		this.txt_nombre_organismo.setRequired(true);
		this.txt_nombre_organismo.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_organismo.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 80), 3, 80, false));
		this.txt_id_organismo.setEnabled(false);
		
		txt_id_organismo.setWidth("40%");
		txt_nombre_organismo.setWidth("80%");
		
		updateId();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	private void buildContent() {
		txt_id_organismo.setValue("0");
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		addComponent(this.txt_id_organismo, 0, 0);
		addComponent(this.txt_nombre_organismo, 1, 0);
		
	}
	
	public void update() {
		binder_Organismo.clear();
		txt_id_organismo.setValue("0");
	}
	
	public void updateId() {
		this.txt_id_organismo.setValue(organismo_impl.generateId() + "");
	}
	
	public void enabled() {
		this.txt_nombre_organismo.setEnabled(false);
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			this.binder_Organismo.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.txt_nombre_organismo.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_organismo.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_organismo.getCaption(), ex.getMessage()));
			}
			
			return false;
		}
	}
	
	public Organismo_Financiador getData() {
		Organismo_Financiador resul = new Organismo_Financiador();
		resul.setORF_Organismo_Financiador(Short.parseShort(this.txt_id_organismo.getValue()));
		resul.setORF_Nombre_Organismo_Financiador(this.txt_nombre_organismo.getValue());
		long lnMilis = new Date().getTime();
		resul.setORF_Fecha_Registro(new java.sql.Date(lnMilis));
		return resul;
	}
	
	public void setData(Organismo_Financiador data) {
		this.txt_id_organismo.setValue(String.valueOf(data.getORF_Organismo_Financiador()));
		this.txt_nombre_organismo.setValue(String.valueOf(data.getORF_Nombre_Organismo_Financiador()));
		
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}
