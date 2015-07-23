package ait.sistemas.proyecto.seguridad.view.usua.opcionperfil;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.PermisoPerfil;
import ait.sistemas.proyecto.seguridad.data.model.Perfil;
import ait.sistemas.proyecto.seguridad.data.service.Impl.PerfilImpl;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;

public class FormEliminacion extends GridLayout implements ValueChangeListener {

	private static final long serialVersionUID = 1L;

	private ComboBox cb_perfiles;

	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	private final PerfilImpl perfilimpl = new PerfilImpl();

	public List<PermisoPerfil> permisos_perfil;

	public Grid gridOpcionPerfil;

	final PropertysetItem pitmOpcionPerfil = new PropertysetItem();
	private FieldGroup binderOpcionPerfil;

	public FormEliminacion() {
		super(1, 1);
		setMargin(true);
		setWidth("100%");

		this.cb_perfiles = new ComboBox("Perfil");
		this.permisos_perfil = new ArrayList<PermisoPerfil>();
		this.gridOpcionPerfil = new Grid();

		cb_perfiles.setInputPrompt("Seleccione un Perfil");
		cb_perfiles.setRequired(true);
		cb_perfiles.addValidator(new NullValidator("", false));

		pitmOpcionPerfil.addItemProperty("perfil", new ObjectProperty<Integer>(0));
	
		this.binderOpcionPerfil = new FieldGroup(this.pitmOpcionPerfil);

		binderOpcionPerfil.bind(this.cb_perfiles, "perfil");

		this.cb_perfiles.setWidth("90%");
		this.cb_perfiles.addValueChangeListener(this);
		fillcb_perfiles();
		buildForm();
		Responsive.makeResponsive(this);
	}

	private void buildForm() {
		this.binderOpcionPerfil.clear();
		addComponent(this.cb_perfiles, 0, 0);
	}

	private void buildGrid(int id_perfil) {
		this.gridOpcionPerfil.setWidth("100%");
		this.gridOpcionPerfil.setSelectionMode(SelectionMode.NONE);
		this.permisos_perfil = perfilimpl.getPermisos(id_perfil);
		BeanItemContainer<PermisoPerfil> beanOpcionPerfil = new BeanItemContainer<PermisoPerfil>(
				PermisoPerfil.class);
		beanOpcionPerfil.addAll(this.permisos_perfil);
		this.gridOpcionPerfil.setContainerDataSource(beanOpcionPerfil);
		this.gridOpcionPerfil.setSelectionMode(SelectionMode.MULTI);

	}

	private void fillcb_perfiles() {
		cb_perfiles.removeAllItems();
		cb_perfiles.setNullSelectionAllowed(false);
		for (Perfil perfil : perfilimpl.getall()) {
			cb_perfiles.addItem(perfil.getPRF_Id_Perfil());
			cb_perfiles.setItemCaption(perfil.getPRF_Id_Perfil(),
					perfil.getPRF_Nombre_Perfil());
		}
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty().getValue() != null) {
			buildGrid((Integer) this.cb_perfiles.getValue());
		}
	}

	public List<PermisoPerfil> getListMenu() {
		this.permisos_perfil = new ArrayList<PermisoPerfil>();
		for (Object row : this.gridOpcionPerfil.getSelectedRows()) {
			this.permisos_perfil.add((PermisoPerfil)row);
		}
		return this.permisos_perfil;
	}

	public int getIdPerfil() {
		return (Integer) this.cb_perfiles.getValue();
	}

	public void clean() {
		this.permisos_perfil.clear();
		buildForm();
	}

	public List<BarMessage> getMensajes() {
		return mensajes;
	}

	public boolean validate() {
		try {
			this.binderOpcionPerfil.commit();
			this.mensajes.add(new BarMessage("Fomulario",
					Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_perfiles.validate();
			} catch (EmptyValueException ex) {
				this.mensajes.add(new BarMessage(cb_perfiles.getCaption(),
						Messages.EMPTY_MESSAGE));
			}
			return false;
		}
	}

	public void update() {
		buildGrid((Integer) this.cb_perfiles.getValue());
	}

}
