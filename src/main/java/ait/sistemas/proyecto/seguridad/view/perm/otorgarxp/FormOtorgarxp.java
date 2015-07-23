package ait.sistemas.proyecto.seguridad.view.perm.otorgarxp;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.PermisoPerfil;
import ait.sistemas.proyecto.seguridad.component.model.UsuarioGridModel;
import ait.sistemas.proyecto.seguridad.data.model.Perfil;
import ait.sistemas.proyecto.seguridad.data.service.Impl.PerfilImpl;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;

public class FormOtorgarxp extends GridLayout implements ValueChangeListener {

	private static final long serialVersionUID = 1L;

	public ComboBox cb_funcionario = new ComboBox("Usuarios");
	public ComboBox cb_perfiles = new ComboBox("Perfiles");
	public Grid grid_permisos = new Grid();

	final PropertysetItem pitmOtorgar = new PropertysetItem();
	private FieldGroup binderOtorgar;
	private List<BarMessage> mesagges = new ArrayList<BarMessage>();
	private PerfilImpl perfilimpl = new PerfilImpl();
	private UsuarioImpl usuarioimpl = new UsuarioImpl();
	private List<PermisoPerfil> permisos = new ArrayList<PermisoPerfil>();

	public FormOtorgarxp() {
		super(1, 3);
		setWidth("100%");
		setMargin(true);
		this.cb_funcionario.setWidth("50%");
		this.cb_perfiles.setWidth("50%");
		this.grid_permisos.setWidth("70%");

		pitmOtorgar.addItemProperty("usuario", new ObjectProperty<String>(""));
		pitmOtorgar.addItemProperty("perfil", new ObjectProperty<Integer>(0));

		this.binderOtorgar = new FieldGroup(this.pitmOtorgar);

		binderOtorgar.bind(this.cb_funcionario, "usuario");
		binderOtorgar.bind(this.cb_perfiles, "perfil");
		this.cb_perfiles.addValueChangeListener(this);

		this.cb_funcionario.setRequired(true);
		this.cb_funcionario.addValidator(new NullValidator(
				Messages.EMPTY_MESSAGE, false));
		this.cb_perfiles.setRequired(true);
		this.cb_perfiles.addValidator(new NullValidator(Messages.EMPTY_MESSAGE,
				false));
		buildForm();
		Responsive.makeResponsive(this);
	}

	private void buildForm() {
		this.binderOtorgar.clear();
		fillcbUsuario();
		fillcbPerfil();
		addComponent(this.cb_funcionario, 0, 0);
		addComponent(this.cb_perfiles, 0, 1);
		addComponent(this.grid_permisos, 0, 2);
	}

	public void fillcbUsuario() {
		this.cb_funcionario.setInputPrompt("Seleccione un Usuario");
		this.cb_funcionario.removeAllItems();
		this.cb_funcionario.setNullSelectionAllowed(false);
		for (UsuarioGridModel usuario : usuarioimpl.getGridData()) {
			this.cb_funcionario.addItem(usuario.getId());
			this.cb_funcionario.setItemCaption(usuario.getId(),
					usuario.getFullName());
			this.cb_funcionario.setItemIcon(usuario.getId(), FontAwesome.USER);
		}
	}

	public void fillcbPerfil() {
		this.cb_perfiles.setInputPrompt("Seleccione un Usuario");
		this.cb_perfiles.removeAllItems();
		this.cb_perfiles.setNullSelectionAllowed(false);
		for (Perfil perfil : perfilimpl.getall()) {
			this.cb_perfiles.addItem(perfil.getPRF_Id_Perfil());
			this.cb_perfiles.setItemCaption(perfil.getPRF_Id_Perfil(),
					perfil.getPRF_Nombre_Perfil());
		}
	}

	public boolean validate() {
		this.mesagges = new ArrayList<BarMessage>();
		try {
			this.binderOtorgar.commit();
			this.mesagges.add(new BarMessage("Form", Messages.SUCCESS_MESSAGE,
					"success"));
			return true;
		} catch (CommitException e) {
			this.mesagges.add(new BarMessage("Form",
					Messages.NOT_SUCCESS_MESSAGE));
			try {
				this.cb_funcionario.validate();
			} catch (EmptyValueException e2) {
				this.mesagges.add(new BarMessage(cb_funcionario.getCaption(),
						Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_perfiles.validate();
			} catch (EmptyValueException e2) {
				this.mesagges.add(new BarMessage(cb_perfiles.getCaption(),
						Messages.EMPTY_MESSAGE));
			}
			return false;
		}

	}

	public List<BarMessage> getMessages() {
		return this.mesagges;
	}

	public void clear() {
		this.binderOtorgar.clear();
		this.grid_permisos.removeAllColumns();
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty().getValue() == this.cb_perfiles.getValue()
				&& this.cb_perfiles.getValue() != null) {
			buildGrid((int) this.cb_perfiles.getValue());
		}
	}

	private void buildGrid(int id_perfil) {

		this.permisos = (List<PermisoPerfil>) this.perfilimpl
				.getPermisos(id_perfil);
		if (permisos.size() > 0) {
			BeanItemContainer<PermisoPerfil> bean_permisos = new BeanItemContainer<PermisoPerfil>(
					PermisoPerfil.class, this.permisos);
			this.grid_permisos.setContainerDataSource(bean_permisos);
			Responsive.makeResponsive(this.grid_permisos);
			this.grid_permisos.removeColumn("icono");
			this.grid_permisos.removeColumn("id_perfil");
			this.grid_permisos.removeColumn("identificador");
		}
	}

	public int getPerfil() {
		return (int) this.cb_perfiles.getValue();
	}
	public String getUsuario() {
		return this.cb_funcionario.getValue().toString();
	}
	public List<PermisoPerfil> getPermisos() {
		return this.permisos;
	}

}
