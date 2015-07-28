package ait.sistemas.proyecto.activos.view.mvac.ingreso.form;

import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class FormDatosGenerales extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	public ComboBox cb_tipo_activo = new ComboBox("Tipo de Activo");
	public TextField txt_codigo_activo = new TextField("Codigo del Activo");
	public TextField txt_nombre_activo = new TextField("Nombre del Activo");
	public DateField dtf_fecha_compra = new DateField("Fecha Compra");
	public TextField txt_valor_compra = new TextField("Valor de la Compra (Bs)", "######,###BS");
	public TextField txt_tipo_cambio = new TextField("Tipo Cambio");
	public TextField txt_vida_util = new TextField("Vida Util (Años)");
	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxiliar Contable");
	public ComboBox cb_fuente_financiamiento = new ComboBox("Fuente de Financiamineto");
	public ComboBox cb_organismo_financiador = new ComboBox("Organismo Financiador");
	public ComboBox cb_ubicacion_fisica = new ComboBox("Ubicacion Fisica");
	public ComboBox cb_inmueble = new ComboBox("Inmueble");
	public DateField dtf_fecha_comodato = new DateField("Fecha Como Dato");
	
	public FormDatosGenerales() {
		super(5, 6);
		setWidth("100%");
		setMargin(true);
		this.cb_tipo_activo.setWidth("90%");
		this.txt_codigo_activo.setWidth("90%");
		this.txt_nombre_activo.setWidth("90%");
		this.dtf_fecha_compra.setWidth("90%");
		this.txt_valor_compra.setWidth("90%");
		this.txt_tipo_cambio.setWidth("90%");
		this.txt_vida_util.setWidth("90%");
		this.cb_grupo_contable.setWidth("90%");
		this.cb_auxiliar_contable.setWidth("90%");
		this.cb_fuente_financiamiento.setWidth("90%");
		this.cb_organismo_financiador.setWidth("90%");
		this.cb_ubicacion_fisica.setWidth("90%");
		this.cb_inmueble.setWidth("90%");
		this.dtf_fecha_comodato.setWidth("90%");
		
		
		this.cb_tipo_activo.setRequired(true);
		this.cb_tipo_activo.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.txt_codigo_activo.setRequired(true);
		this.txt_codigo_activo.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.txt_nombre_activo.setRequired(true);
		this.txt_nombre_activo.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.dtf_fecha_compra.setRequired(true);
		this.dtf_fecha_compra.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.txt_valor_compra.setRequired(true);
		this.txt_valor_compra.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.txt_tipo_cambio.setRequired(true);
		this.txt_tipo_cambio.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.txt_vida_util.setRequired(true);
		this.txt_vida_util.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.cb_grupo_contable.setRequired(true);
		this.cb_grupo_contable.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.cb_auxiliar_contable.setRequired(true);
		this.cb_auxiliar_contable.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.cb_fuente_financiamiento.setRequired(true);
		this.cb_fuente_financiamiento.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.cb_ubicacion_fisica.setRequired(true);
		this.cb_ubicacion_fisica.setRequiredError(Messages.EMPTY_MESSAGE);
		
		this.cb_inmueble.setRequired(true);
		this.cb_inmueble.setRequiredError(Messages.EMPTY_MESSAGE);
		
		buildForm();
		Responsive.makeResponsive(this);
	}
	
	private void buildForm() {
		addComponent(this.cb_tipo_activo, 0, 0, 1, 0);
		addComponent(this.txt_codigo_activo, 2, 0);
		
		addComponent(this.txt_nombre_activo, 0, 1, 4, 1);
		
		addComponent(this.dtf_fecha_compra, 0, 2);
		addComponent(this.txt_valor_compra, 1, 2);
		addComponent(this.txt_tipo_cambio, 2, 2);
		addComponent(this.txt_vida_util, 3, 2);
		
		addComponent(this.cb_grupo_contable, 0, 3, 1, 3);
		addComponent(this.cb_auxiliar_contable, 2, 3, 3, 3);
		
		addComponent(this.cb_fuente_financiamiento, 0, 4, 1, 4);
		addComponent(this.cb_organismo_financiador, 2, 4, 3, 4);
		
		addComponent(this.cb_ubicacion_fisica, 0, 5, 1, 5);
		addComponent(this.cb_inmueble, 2, 5, 3, 5);
		addComponent(this.dtf_fecha_comodato, 4, 5);
		
	}
	
}
