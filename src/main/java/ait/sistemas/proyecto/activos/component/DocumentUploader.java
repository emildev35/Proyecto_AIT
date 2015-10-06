package ait.sistemas.proyecto.activos.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import ait.sistemas.proyecto.common.component.PathValues;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;

public class DocumentUploader implements Receiver {
	
	private static final long serialVersionUID = 1L;
	public File file;
	
	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null; // Stream to write to
		try {
			file = new File(PathValues.DOCUMENT_PATH + filename);
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {

			return null;
		}
		return fos;
	}
	public String getFile() {
		if(file==null){
			return "";
		}	
		return file.getAbsolutePath().equals(null)?"":file.getAbsolutePath();
	}
	
	
}