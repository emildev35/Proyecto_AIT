package ait.sistemas.proyecto.activos.view.mvac.ingreso.form;

import ait.sistemas.proyecto.activos.component.ImageUploader;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

public class FormImageUpload extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	
	Embedded image = new Embedded();
	ImageUploader reciver = new ImageUploader();
	Upload upload = new Upload("Seleccione una Imagen", reciver);
	
	public FormImageUpload() {
		upload.addSucceededListener(reciver);
		upload.setButtonCaption("Cargar Imagen");
		addComponent(upload);
		addComponent(reciver.getImage());
		Responsive.makeResponsive(this);
	}
	
	public String getFile() {
		if(reciver.file==null){
			return "";
		}
		return reciver.file.getAbsolutePath().equals(null)?"":reciver.file.getAbsolutePath();
	}
}
