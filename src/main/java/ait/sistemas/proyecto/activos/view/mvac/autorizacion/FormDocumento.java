package ait.sistemas.proyecto.activos.view.mvac.autorizacion;

import ait.sistemas.proyecto.activos.component.model.DocumentoPendiente;

import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormDocumento extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	private TextField txt_tipo_movimiento = new TextField("Tipo de Movimiento");
	private TextField txt_nro_documento = new TextField("Número de Documento");
	private DateField dtf_fecha = new DateField("Fecha");
	private TextField txt_solicitante = new TextField("Solicitante");
	
	public FormDocumento() {
		super(3, 2);
		setWidth("100%");
		setMargin(true);
		
		txt_nro_documento.setWidth("90%");
		txt_tipo_movimiento.setWidth("90%");
		dtf_fecha.setWidth("90%");
		txt_solicitante.setWidth("90%");
		
		txt_nro_documento.setEnabled(false);
		txt_tipo_movimiento.setEnabled(false);
		dtf_fecha.setEnabled(false);
		txt_solicitante.setEnabled(false);
		
		setColumnExpandRatio(0, 2);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		addComponent(this.txt_tipo_movimiento, 0, 0);
		addComponent(this.txt_nro_documento, 1, 0);
		addComponent(this.dtf_fecha, 2, 0);
		addComponent(this.txt_solicitante, 0, 1, 1, 1);
	}

	public void fillData(DocumentoPendiente documento_seleccionado) {
		this.txt_nro_documento.setValue(String.valueOf(documento_seleccionado.getNro_documento()));
		this.txt_tipo_movimiento.setValue(documento_seleccionado.getTipo_movimiento());
		this.dtf_fecha.setValue(documento_seleccionado.getFecha_movimiento());
		this.txt_solicitante.setValue(documento_seleccionado.getNombre_solicitante());
	}

	public void clear() {
		this.txt_nro_documento.clear();
		this.txt_nro_documento.clear();
		this.dtf_fecha.clear();
		this.txt_solicitante.clear();
	}
}
