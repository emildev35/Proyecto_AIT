package ait.sistemas.proyecto.seguridad.view.usua.perfil;

import java.util.Date;

import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.data.model.Perfil;
import ait.sistemas.proyecto.seguridad.data.service.Impl.PerfilImpl;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("deprecation")
public class VPerfilA extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 1L;
	private TextField txt_id_perfil;
	private DateField fecha_registro;
	private TextField nombre_perfil;
	private PerfilImpl perfilimpl;

	private Form form;

	public VPerfilA() {

		this.perfilimpl = new PerfilImpl();

		this.txt_id_perfil = new TextField("Id. Perfil: ");
		this.txt_id_perfil.setEnabled(false);

		this.fecha_registro = new DateField("Fecha Creacion: ");

		this.fecha_registro.setEnabled(false);

		this.nombre_perfil = new TextField("Nombre del Peril: ");
		this.nombre_perfil.setRequired(true);
		this.nombre_perfil.addValidator(new StringLengthValidator("Error el tamaño del campo debe ser entre 3 y 15 caracteres", 3,
				15, false));
		this.nombre_perfil.addValidator(new NullValidator("El campo no debe ser nulo", false));
		
		updateId();
		updateDate();
		addComponent(buildNavBar());
		addComponent(buildForm());
		addComponent(buildButtonBar());
	}
	private void updateId(){
		this.txt_id_perfil.setValue(perfilimpl.generateId() + "");
	}
	private void updateDate(){
		this.fecha_registro.setValue(new Date());
	}

	private Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		buttonContent.addStyleName("ait-buttons");
		
		Button btnagregar = new Button("Agregar");
		btnagregar.addClickListener(this);
		Button btnlimpiar = new Button("Limpiar");
		btnlimpiar.addClickListener(this);
		buttonContent.addComponent(btnagregar);
		buttonContent.addComponent(btnlimpiar);
		return buttonContent;
	}

	private Component buildForm() {
		// FormLayout formContent = new FormLayout();
		this.form = new Form();
		form.addField("nombre", this.nombre_perfil);
		form.addField("id_perfil", this.txt_id_perfil);
		form.addField("fecha_creacion", this.fecha_registro);
		return form;
	}

	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label("Activos » "));
		nav.addComponent(new Label("Usuarios » "));
		nav.addComponent(new Label("Perfil » "));
		nav.addComponent(new Label("<strong>Agregar</strong>", ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}


	@Override
	public void buttonClick(ClickEvent event) {
		try {
			
			this.nombre_perfil.validate();

			Perfil perfil = new Perfil();
			perfil.setPRF_Id_Perfil(Integer.parseInt(this.txt_id_perfil.getValue()));
			perfil.setPRF_Nombre_Perfil(this.nombre_perfil.getValue().toString());
			long lnMilis = new Date().getTime();
			perfil.setPRF_Fecha_Registro(new java.sql.Date(lnMilis));
			Perfil resultado = perfilimpl.add(perfil);
			if(resultado == null){
				Notification.show(Messages.NOT_SUCCESS_MESSAGE, Type.ERROR_MESSAGE);
			}else{
				Notification.show(Messages.SUCCESS_MESSAGE);
				this.form.clear();
				updateId();
				updateDate();
			}
		} catch (InvalidValueException e) {
			Notification.show(e.getMessage(), Type.ERROR_MESSAGE);
			form.setValidationVisible(false);
		}
		
	}

}
