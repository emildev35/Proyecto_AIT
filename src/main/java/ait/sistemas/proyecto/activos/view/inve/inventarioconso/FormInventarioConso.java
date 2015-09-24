package ait.sistemas.proyecto.activos.view.inve.inventarioconso;//la carpeta actual

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ait.sistemas.proyecto.activos.data.model.Fecha_Depreciacion;
import ait.sistemas.proyecto.activos.data.service.Impl.FechaDepreciacionImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;

public class FormInventarioConso extends GridLayout {
	
	
	//extends --> herencia 
	//GridLayout --> es una tabla donde se maneja posiciones
	//implement --> esqueleto. implementa los metodos para una interfaz 
	//ValueChangeListener --> llena el otro combo al elegir un combo box
	private static final long serialVersionUID = 1L;
	//serialVersionUID = serial para 
	
	public DateField dt_fecha;
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	//los List siempre se declaran asi 
	//BarMessage --> clase para mandar:			 componente  mensaje   tipo
	final private FechaDepreciacionImpl fecha_depreciacion_impl = new FechaDepreciacionImpl();
	final PropertysetItem pitm_Inventario = new PropertysetItem();
	private FieldGroup binder_Inventario;
	
	//final-->posicion especifica no se vuelve a crear para procesar mas rapido y usar menos memoria
	//PropertysetItem --> para vincular los datos para que validate todos y no uno por uno. lo lleva todo a un tipo de objeto
	//FieldGroup --> trabajan juntos lo lleva el tipo de objeto al componente
	
	public FormInventarioConso() {
		setColumns(2); //columnas que aceptara el grid en las posiciones
		setRows(3); //filas que aceptara el grid
		setWidth("100%"); //ancho de todo el grid
		setMargin(true); //espacio de los cuatro costados
		setSpacing(true); //distancia de componente a componente
		
		this.dt_fecha = new DateField("Fecha:");
		
		pitm_Inventario.addItemProperty("dependencia", new ObjectProperty<Short>((short)1));//el combo va tener un objeto de tipo short 
		pitm_Inventario.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));

		this.binder_Inventario = new FieldGroup(pitm_Inventario);
		//instanciando FieldGroup mandandole PropertysetItem
		this.binder_Inventario.bind(this.dt_fecha, "fecha");
		this.binder_Inventario.clear();
		
		//new NullValidator("", false) por es una clase de vaadin --> no da errores pero tampoco deja registrar
		this.dt_fecha.setEnabled(false);
		
		fillfecha((Fecha_Depreciacion) fecha_depreciacion_impl.getFechaDep());
		buildContent();
	}
	
	private void fillfecha(Fecha_Depreciacion data) {
		
		this.dt_fecha.setValue(data.getFDE_Fecha_Depreciacion());
	}

	/**
	 * Actualizacion de los Campos
	 */
	public void update(){
		this.binder_Inventario.clear();
		//this.cb_Dependencia.setValue((String)(""));
	}
	/**
	 * Llenado del Combo Box 
	 */
	private void buildContent() {
		
		this.dt_fecha.setWidth("25%");
		
//		setColumnExpandRatio(0, 0.5f);
		
		addComponent(this.dt_fecha, 0,0);

	}
	public boolean validate(){
		try {
			binder_Inventario.commit();
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
		this.mensajes.clear();
	}
}

