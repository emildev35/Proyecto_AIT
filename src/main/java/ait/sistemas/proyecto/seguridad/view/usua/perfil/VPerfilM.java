package ait.sistemas.proyecto.seguridad.view.usua.perfil;

import java.util.Date;
import java.util.Locale;

import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.data.model.Perfil;
import ait.sistemas.proyecto.seguridad.data.service.Impl.PerfilImpl;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;

@SuppressWarnings({ "unused", "deprecation" })
public class VPerfilM extends VerticalLayout implements View,
		SelectionListener, ClickListener {

	private static final long serialVersionUID = 1L;

	private Table tblPerfiles;
	private Form frmPerfil;
	private PerfilImpl perfil_impl;
	private TextField txt_id_perfil;
	private DateField dtf_fecha_creacion;
	private TextField txt_nombre_perfil;
	private Grid grid_perfiles;
	private FieldGroup fgrpForm;
	private Button btn_submit;
	private Button btn_limpiar;
	private Perfil perfil;
	private CssLayout hl_errores;
	
	final PropertysetItem pitmPerfil = new PropertysetItem();
	private FieldGroup binderPerfil;

	public VPerfilM() {

		this.txt_id_perfil = new TextField("Id. Perfil");

		this.txt_nombre_perfil = new TextField("Nombre Pefil");

		this.dtf_fecha_creacion = new DateField("Fecha Creacion");

		this.btn_submit = new Button("Guardar");
		this.btn_submit.addClickListener(this);
		this.btn_limpiar = new Button("Limpiar");
		this.btn_limpiar.addClickListener(this);
		this.perfil_impl = new PerfilImpl();
		this.perfil = new Perfil();
		this.grid_perfiles = new Grid();
		this.hl_errores = new CssLayout();

		pitmPerfil.addItemProperty("id_perfil", new ObjectProperty<Integer>(0));
		pitmPerfil.addItemProperty("nombre_perfil", new ObjectProperty<String>(
				""));
		pitmPerfil.addItemProperty("fecha_creacion", new ObjectProperty<Date>(
				new Date()));

		binderPerfil = new FieldGroup(pitmPerfil);
		binderPerfil.bind(this.txt_id_perfil, "id_perfil");
		binderPerfil.bind(this.txt_nombre_perfil, "nombre_perfil");
		binderPerfil.bind(this.dtf_fecha_creacion, "fecha_creacion");

		this.txt_id_perfil.setEnabled(false);
		this.txt_nombre_perfil.addValidator(new StringLengthValidator(Messages
				.STRING_LENGTH_MESSAGE(3, 50), 3, 50, false));
		this.txt_nombre_perfil.setRequired(true);
		this.dtf_fecha_creacion.setEnabled(false);

		buildGridPerfil();
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(buildButtonBar());
		Responsive.makeResponsive(this);
	}

	public void buildGridPerfil() {
		grid_perfiles.removeAllColumns();
		BeanItemContainer<Perfil> bean_perfil = new BeanItemContainer<Perfil>(
				Perfil.class, this.perfil_impl.getall());
		this.grid_perfiles.setContainerDataSource(bean_perfil);
		this.grid_perfiles.setHeightMode(HeightMode.ROW);
		this.grid_perfiles.setHeightByRows(5);
		this.grid_perfiles.removeColumn("permisos");
		this.grid_perfiles.removeColumn("usuarios");
		this.grid_perfiles.removeColumn("opcionesXperfils");

		this.grid_perfiles.setWidth("85%");
		this.grid_perfiles.setHeight("10%");
		this.grid_perfiles.setColumnOrder("PRF_Id_Perfil", "PRF_Nombre_Perfil",
				"PRF_Fecha_Registro");

		Grid.Column id_perfilColumn = this.grid_perfiles
				.getColumn("PRF_Id_Perfil");
		Grid.Column nombre_perfilColumn = this.grid_perfiles
				.getColumn("PRF_Nombre_Perfil");
		Grid.Column fecha_perfilColumn = this.grid_perfiles
				.getColumn("PRF_Fecha_Registro");
		
		id_perfilColumn.setHeaderCaption("Id. Perfil");
		nombre_perfilColumn.setHeaderCaption("Nombre Perfil");
		fecha_perfilColumn.setHeaderCaption("Fecha Registro");
		fecha_perfilColumn.setRenderer(new DateRenderer("%1$tB %1$te, %1$tY",
				new Locale("es", "BO")));

		Responsive.makeResponsive(this.grid_perfiles);
		this.grid_perfiles.addSelectionListener(this);
	}

	private Component buildFormContent() {

		VerticalLayout vl_content = new VerticalLayout();
		vl_content.setMargin(true);
		FormLayout frmEditarPerfil = new FormLayout();
		frmEditarPerfil.addComponent(this.txt_id_perfil);
		frmEditarPerfil.addComponent(this.txt_nombre_perfil);
		frmEditarPerfil.addComponent(this.dtf_fecha_creacion);

		vl_content.addComponent(frmEditarPerfil);
		vl_content.addComponent(this.grid_perfiles);
		vl_content.setExpandRatio(this.grid_perfiles, 2);
		vl_content.setExpandRatio(frmEditarPerfil, 5);
		return vl_content;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Usuarios » "));
		nav.addComponent(new Label("Perfil » "));
		nav.addComponent(new Label("<strong>Modificar</strong>",
				ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addComponent(this.btn_submit);
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
		return buttonContent;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public void select(com.vaadin.event.SelectionEvent event) {

		if ((Perfil)this.grid_perfiles.getSelectedRow() != null) {
			this.perfil = (Perfil)this.grid_perfiles.getSelectedRow();
			this.txt_id_perfil.setValue(perfil.getPRF_Id_Perfil() + "");
			this.txt_nombre_perfil.setValue(perfil.getPRF_Nombre_Perfil());
			this.dtf_fecha_creacion.setValue(perfil.getPRF_Fecha_Registro());
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == this.btn_submit) {

			try {
				binderPerfil.commit();
				this.perfil.setPRF_Nombre_Perfil(this.txt_nombre_perfil.getValue());
				Perfil result = this.perfil_impl.update(this.perfil);
				if (result != null) {
					Notification.show(Messages.SUCCESS_MESSAGE);
					buildGridPerfil();
				
				} else {
					Notification.show(Messages.NOT_SUCCESS_MESSAGE,
							Type.ERROR_MESSAGE);
				}

			} catch (CommitException e) {
				Notification.show(Messages.NOT_SUCCESS_MESSAGE,
						Type.ERROR_MESSAGE);
			}
			try {
				this.txt_nombre_perfil.validate();
			} catch (InvalidValueException e) {
				buildError("Nombre Perfil : " + e.getMessage());
			}
			binderPerfil.clear();
		}
		if (event.getButton() == this.btn_limpiar) {
			binderPerfil.clear();
		}
	}

	private void buildError(String strmessage) {
		this.hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		this.addComponent(this.hl_errores);
		Label lbError = new Label(new Label(strmessage));
		this.hl_errores.addComponent(lbError);
		
	}

}
