package ait.sistemas.proyecto.activos.view.reva.revaloriza;//la carpeta actual

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class FormRevalorizaP extends GridLayout {
	
	
	//extends --> herencia 
	//GridLayout --> es una tabla donde se maneja posiciones
	//implement --> esqueleto. implementa los metodos para una interfaz 
	//ValueChangeListener --> llena el otro combo al elegir un combo box
	private static final long serialVersionUID = 1L;
	//serialVersionUID = serial para 
	
	public DateField dt_fecha;
	public TextField txt_no_resolucion;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	//los List siempre se declaran asi 
	//BarMessage --> clase para mandar:			 componente  mensaje   tipo
	final PropertysetItem pitm_Revaloriza = new PropertysetItem();
	private FieldGroup binder_Revaloriza;
	
	//final-->posicion especifica no se vuelve a crear para procesar mas rapido y usar menos memoria
	//PropertysetItem --> para vincular los datos para que validate todos y no uno por uno. lo lleva todo a un tipo de objeto
	//FieldGroup --> trabajan juntos lo lleva el tipo de objeto al componente
	
	public FormRevalorizaP() {
		setColumns(5); //columnas que aceptara el grid en las posiciones
		setRows(3); //filas que aceptara el grid
		setWidth("100%"); //ancho de todo el grid
		setMargin(true); //espacio de los cuatro costados
		setSpacing(true); //distancia de componente a componente
		
		this.dt_fecha = new DateField("Fecha:");
		this.txt_no_resolucion= new TextField("No. Resolucion");
		
		pitm_Revaloriza.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));
		pitm_Revaloriza.addItemProperty("resolucion", new ObjectProperty<String>(""));

		this.binder_Revaloriza = new FieldGroup(pitm_Revaloriza);
		//instanciando FieldGroup mandandole PropertysetItem
		this.binder_Revaloriza.bind(this.dt_fecha, "fecha");
		this.binder_Revaloriza.bind(this.txt_no_resolucion,"resolucion");
		this.binder_Revaloriza.clear();
		
		this.txt_no_resolucion.setRequired(true);
		this.dt_fecha.setRequired(true);
		this.txt_no_resolucion.addValidator(new NullValidator("", false));
		//new NullValidator("", false) por es una clase de vaadin --> no da errores pero tampoco deja registrar
//		this.dt_fecha.setEnabled(false);
		
		this.dt_fecha.setWidth("40%");
		this.txt_no_resolucion.setWidth("90%");
		
		buildContent();
		Responsive.makeResponsive(this);
	}

	/**
	 * Actualizacion de los Campos
	 */
	public void update(){
		this.binder_Revaloriza.clear();
		//this.cb_Dependencia.setValue((String)(""));
	}
	/**
	 * Llenado del Combo Box 
	 */
	private void buildContent() {
		
		Panel pn_res = new Panel("Registre el Número de Resolución Revalorización");
		Panel pn_dep = new Panel("Seleccione una Dependencia");
		Panel pn_fecha = new Panel("Registre Fecha Elaboracion Reporte");
		pn_res.setIcon(FontAwesome.PRINT);
		pn_res.setStyleName(AitTheme.PANEL_PRINT);
		pn_dep.setIcon(FontAwesome.PRINT);
		pn_dep.setStyleName(AitTheme.PANEL_PRINT);
		pn_fecha.setIcon(FontAwesome.PRINT);
		pn_fecha.setStyleName(AitTheme.PANEL_PRINT);
		
		GridLayout gridl_res = new GridLayout(2, 1);
		gridl_res.setSizeFull();
		gridl_res.setColumnExpandRatio(0, 0);
		gridl_res.addComponent(this.txt_no_resolucion, 0,0);
		pn_res.setContent(gridl_res);
		
		this.addComponent(pn_res, 0, 0, 1, 0);
	
		GridLayout gridl_fecha = new GridLayout(2, 1);
		gridl_fecha.setSizeFull();
		gridl_fecha.setColumnExpandRatio(0, 0);
		gridl_fecha.addComponent(this.dt_fecha, 0,0);
		pn_fecha.setContent(gridl_fecha);
		
		this.addComponent(pn_fecha, 0, 2, 1, 2);
		
	}
	public boolean validate(){
		try {
			binder_Revaloriza.commit();
			return true;
		}
			catch (CommitException ex) {
				Map<Field<?>, InvalidValueException> invalid_fields = ex.getInvalidFields();
				Iterator<Field<?>> it = invalid_fields.keySet().iterator();
				while (it.hasNext()) {
					Field<?> key = (Field<?>) it.next();
					mensajes.add(new BarMessage(key.getCaption(),
							invalid_fields.get(key).getMessage() == "" ? Messages.EMPTY_MESSAGE : invalid_fields.get(key)
									.getMessage()));
				}
				return false;
			}
	}
	
	public List<BarMessage> getMessage(){
		return this.mensajes;
	}

	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public Movimiento getData() {
		Movimiento resul = new Movimiento();
		resul.setNro_documento_referencia(this.txt_no_resolucion.getValue().replace(",", "."));
		resul.setFecha_nro_referencia(new java.sql.Date(this.dt_fecha.getValue().getTime()));
		return resul;
	}
}
