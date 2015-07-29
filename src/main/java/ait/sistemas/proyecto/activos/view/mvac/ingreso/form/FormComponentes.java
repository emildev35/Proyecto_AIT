package ait.sistemas.proyecto.activos.view.mvac.ingreso.form;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickListener;

public class FormComponentes extends GridLayout implements ClickListener {
	
	private static final long serialVersionUID = 1L;
	
	private Button btn_limpiar;
	private Button btn_agregar;
	
	public TextField txt_codigo_activo = new TextField("Codigo Activo");
	public TextField txt_nombre_activo = new TextField("Nombre Activo");
	public TextField txt_nombre_componente = new TextField("Nombre Componente");
	public TextField txt_caracteristica_componente = new TextField("Caracterisitca Componente");
	public Grid grid_componente = new Grid();

	
	public FormComponentes() {
		super(3, 3);
		setWidth("100%");
		setSpacing(true);
		setMargin(true);
		
		this.btn_limpiar= new Button("Limpiar");
		this.btn_agregar= new Button("Agregar");
		this.btn_agregar.addClickListener(this);
		this.btn_limpiar.addClickListener(this);

		
		this.txt_codigo_activo.setWidth("90%");
		this.txt_nombre_activo.setWidth("90%");
		this.txt_nombre_componente.setWidth("90%");
		this.txt_caracteristica_componente.setWidth("90%");
		buildForm();
	}


	private void buildForm() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 2);
		addComponent(this.txt_codigo_activo, 0, 0);
		addComponent(this.txt_nombre_activo, 1, 0, 2, 0);
		addComponent(this.txt_nombre_componente, 0, 1);
		addComponent(this.txt_caracteristica_componente, 1, 1, 2, 1);
		addComponent(this.grid_componente, 0, 2, 2, 2);
	}

	public Component buildButtonBar() {
		CssLayout buttonContent = new CssLayout();
		this.btn_agregar.setStyleName("ait-buttons-btn");
		buttonContent.addComponent(this.btn_agregar);
		this.btn_limpiar.setStyleName("ait-buttons-btn");
		buttonContent.addStyleName("ait-buttons");
		buttonContent.addComponent(this.btn_limpiar);
		return buttonContent;
	}
	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Notification.show("Componetes");
	}
	
	
}
