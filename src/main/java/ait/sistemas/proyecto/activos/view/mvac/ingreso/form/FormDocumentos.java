package ait.sistemas.proyecto.activos.view.mvac.ingreso.form;

import ait.sistemas.proyecto.activos.component.DocumentUploader;

import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;

public class FormDocumentos extends GridLayout {

	
	private static final long serialVersionUID = 1L;
	
	public TextField txt_codigo_activo = new TextField("Codigo Activo");
	public TextField txt_nombre_activo = new TextField("Nombre Activo");
	public TextField txt_nombre_documento = new TextField("Nombre Documento");
	private DocumentUploader reciver = new DocumentUploader();
	public Upload field_ubicacion_documento = new Upload("Direccion Documento", reciver);
	public Grid grid_documentos = new Grid();

	
	public FormDocumentos() {
		super(3, 3);
		setWidth("100%");
		setSpacing(true);
		setMargin(true);
		this.txt_codigo_activo.setWidth("90%");
		this.txt_nombre_activo.setWidth("90%");
		this.field_ubicacion_documento.setWidth("90%");
		this.field_ubicacion_documento.setWidth("90%");
		buildForm();
	}


	private void buildForm() {
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 2);
		addComponent(this.txt_codigo_activo, 0, 0);
		addComponent(this.txt_nombre_activo, 1, 0, 2, 0);
		addComponent(this.txt_nombre_documento, 0, 1);
		addComponent(this.field_ubicacion_documento, 1, 1, 2, 1);
		addComponent(this.grid_documentos, 0, 2, 2, 2);
	}
	
}
