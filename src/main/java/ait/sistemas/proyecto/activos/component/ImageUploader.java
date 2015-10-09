package ait.sistemas.proyecto.activos.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import ait.sistemas.proyecto.common.component.PathValues;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

public class ImageUploader implements Receiver, SucceededListener, FailedListener {
	
	private static final long serialVersionUID = 1L;
	public File file;
	private Embedded image = new Embedded();
	
	public OutputStream receiveUpload(String filename, String mimeType) {
		
		FileOutputStream fos = null; // Stream to write to
		if (mimeType.equals("image/jpeg") || mimeType.equals("image/png") || mimeType.equals("image/pjpeg")) {
			try {
				file = new File(PathValues.IMAGE_UPLOAD + filename);
				fos = new FileOutputStream(file);
			} catch (final java.io.FileNotFoundException e) {
				new Notification("No pudo leer la imagen<br/>", e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page
						.getCurrent());
				return null;
			}
		} else {
			Notification.show("Solo se Admiten Imagenes con Formaro jpeg o png", Type.ERROR_MESSAGE);
		}
		return fos;
	}
	
	public void uploadSucceeded(SucceededEvent event) {
		// Show the uploaded file in the image viewer
		this.image.setVisible(true);
		this.image.setSource(new FileResource(file));
		this.image.setWidth("100%");
		this.image.setHeight("100%");
	}
	
	public Embedded getImage() {
		return image;
	}
	
	public void setFile(File img) {
		this.image.setSource(new FileResource(img));
		this.image.setWidth("100%");
		this.image.setHeight("100%");
	}
	@Override
	public void uploadFailed(FailedEvent event) {
	}
}