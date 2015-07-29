package ait.sistemas.proyecto.activos.view.mvac.ingreso.form;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormCaracteriticas extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	public TextField txt_codigo_activo = new TextField("Codigo Activo");
	public TextField txt_nombre_activo = new TextField("Nombre Activo");
	public ComboBox cb_proveedor = new ComboBox("Proveedor");
	public TextField txt_marca = new TextField("Marca");
	public TextField txt_numero_serie = new TextField("Numero Serie");
	public TextField txt_numero_garantia = new TextField("Numero Garantia");
	public TextField txt_tiempo_garantia = new TextField("Tiempo Garantia");
	public TextField txt_vencimiento_garantia = new TextField("Venticimiento");
	public TextField txt_numero_ruat = new TextField("N. de RUAT");
	public TextField txt_numero_folio_real = new TextField("N. de Folio Real");
	public TextField txt_numero_poliza_seguro = new TextField("N. de Poliza de Seguro");
	public TextField txt_vencimiento_seguro = new TextField("N. Vencimiento de Seguro");
	public TextField txt_numero_contrato_mantenimiento = new TextField("N. de Contrato de Mantenimiento");
	public TextField txt_vcto_contrato_mantenimientno = new TextField("Vcto de Contrato");
	
	public FormImageUpload frm_imagen = new FormImageUpload();
	
	public FormCaracteriticas() {
		super(7, 7);
		setWidth("100%");
		this.txt_codigo_activo.setWidth("90%");
		this.txt_nombre_activo.setWidth("90%");
		this.cb_proveedor.setWidth("90%");
		this.txt_marca.setWidth("90%");
		this.txt_numero_serie.setWidth("90%");
		this.txt_numero_garantia.setWidth("90%");
		this.txt_tiempo_garantia.setWidth("90%");
		this.txt_vencimiento_garantia.setWidth("90%");
		this.txt_numero_ruat.setWidth("90%");
		this.txt_numero_folio_real.setWidth("90%");
		this.txt_numero_poliza_seguro.setWidth("90%");
		this.txt_vencimiento_seguro.setWidth("90%");
		this.txt_numero_contrato_mantenimiento.setWidth("90%");
		this.txt_vcto_contrato_mantenimientno.setWidth("90%");
		setMargin(true);
		fuildForm();
	}
	
	private void fuildForm() {
		
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 1);
		setColumnExpandRatio(2, 1);
		setColumnExpandRatio(3, 1);
		setColumnExpandRatio(4, 1);
		setColumnExpandRatio(5, 1);
		setColumnExpandRatio(6, 5);
		
		addComponent(this.txt_codigo_activo, 0, 0, 2, 0);
		addComponent(this.txt_nombre_activo, 3, 0, 5, 0);
		
		addComponent(this.cb_proveedor, 0, 1, 5, 1);
		
		addComponent(this.txt_marca, 0, 2, 2, 2);
		addComponent(this.txt_numero_serie, 3, 2, 4, 2);
		
		addComponent(this.txt_numero_garantia, 0, 3, 1, 3);
		
		addComponent(this.txt_tiempo_garantia, 2, 3, 3, 3);
		addComponent(this.txt_vencimiento_garantia, 4, 3, 5, 3);
		
		addComponent(this.txt_numero_ruat, 0, 4, 2, 4);
		addComponent(this.txt_numero_folio_real, 3, 4, 5, 4);
		
		addComponent(this.txt_numero_poliza_seguro, 0, 5, 2, 5);
		addComponent(this.txt_vencimiento_seguro, 3, 5, 5, 5);
		
		addComponent(this.txt_numero_contrato_mantenimiento, 0, 6, 2, 6);
		addComponent(this.txt_vcto_contrato_mantenimientno, 3, 6, 5, 6);
		
		addComponent(this.frm_imagen, 6, 0, 6, 6);
		
	}
}
