package ait.sistemas.proyecto.activos.view.para.motivobaja;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Motivo_Baja;
import ait.sistemas.proyecto.activos.data.service.Impl.MotivobajaImpl;
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

public class FormMotivobaja extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_motivo_baja;
	public TextField txt_nombre_motivo_baja;

	private List<BarMessage> mensajes;

	private MotivobajaImpl motivo_impl = new MotivobajaImpl();
	private PropertysetItem pitm_Motivo = new PropertysetItem();
	private FieldGroup binder_Motivo;

	public FormMotivobaja() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.txt_id_motivo_baja = new TextField("Codigo Motivo:");
		this.txt_nombre_motivo_baja = new TextField("Nombre de Motivo de Baja: ");
		this.mensajes = new ArrayList<BarMessage>();

		pitm_Motivo.addItemProperty("id_motivo_baja", new ObjectProperty<Short>((short) 1));
		pitm_Motivo.addItemProperty("nombre_motivo_baja", new ObjectProperty<String>(""));

		this.binder_Motivo = new FieldGroup(this.pitm_Motivo);

		binder_Motivo.bind(this.txt_id_motivo_baja, "id_motivo_baja");
		binder_Motivo.bind(this.txt_nombre_motivo_baja, "nombre_motivo_baja");
		binder_Motivo.clear();

		this.txt_nombre_motivo_baja.setRequired(true);
		this.txt_nombre_motivo_baja.addValidator(new NullValidator("No Nulo", false));
		this.txt_nombre_motivo_baja.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 50), 3,
				50, false));
		this.txt_id_motivo_baja.setEnabled(false);

		txt_id_motivo_baja.setWidth("90%");
		txt_nombre_motivo_baja.setWidth("90%");

		updateId();
		buildContent();
		Responsive.makeResponsive(this);
	}

	private void buildContent() {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		addComponent(this.txt_id_motivo_baja, 0, 0);
		addComponent(this.txt_nombre_motivo_baja, 1, 0);

	}

	public void update() {
		binder_Motivo.clear();
	}

	public void updateId() {
		this.txt_id_motivo_baja.setValue(motivo_impl.generateId() + "");
	}

	public void enabled() {
		this.txt_nombre_motivo_baja.setEnabled(false);
	}

	public List<BarMessage> getMensajes() {
		return mensajes;
	}

	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}

	public boolean validate() {

		try {
			this.binder_Motivo.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.txt_nombre_motivo_baja.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_motivo_baja.getCaption(), Messages.EMPTY_MESSAGE));
			} catch (InvalidValueException ex) {
				this.mensajes.add(new BarMessage(txt_nombre_motivo_baja.getCaption(), ex.getMessage()));
			}

			return false;
		}
	}

	public Motivo_Baja getData() {
		Motivo_Baja resul = new Motivo_Baja();
		resul.setMBA_Motivo_Baja(Short.parseShort(this.txt_id_motivo_baja.getValue()));
		resul.setMBA_Descripcion(this.txt_nombre_motivo_baja.getValue());
		return resul;
	}

	public void setData(Motivo_Baja data) {
		this.txt_id_motivo_baja.setValue(String.valueOf(data.getMBA_Motivo_Baja()));
		this.txt_nombre_motivo_baja.setValue(String.valueOf(data.getMBA_Descripcion()));

	}

	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}
