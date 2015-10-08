package ait.sistemas.proyecto.activos.view.inve.activosxgrupo;//la carpeta actual

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ait.sistemas.proyecto.activos.data.model.Cierre_Gestion;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.service.Impl.CierreGestionImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;

/**
 * Formulario de Inventario de Activos por Grupo Contable
 * 
 * @author franzemil
 *
 */
public class FormInventario extends GridLayout {
	
	private static final long serialVersionUID = 1L;
	
	public ComboBox cb_Dependencia = new ComboBox("Dependencia");
	public ComboBox cbGrupoContable = new ComboBox("Grupo Contable");
	public DateField dt_fecha = new DateField("Fecha");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	final private DependenciaImpl dependencia_impl = new DependenciaImpl();
	final private CierreGestionImpl fecha_depreciacion_impl = new CierreGestionImpl();
	private final GrupoImpl grupoimpl = new GrupoImpl();
	final PropertysetItem pitm_Inventario = new PropertysetItem();
	private FieldGroup binder_Inventario;
	
	public FormInventario() {
		super(3, 3);
		setWidth("100%");
		setMargin(true);
		setSpacing(true);
		
		pitm_Inventario.addItemProperty("dependencia", new ObjectProperty<Short>((short) 1));
		pitm_Inventario.addItemProperty("grupoContable", new ObjectProperty<String>(""));
		pitm_Inventario.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));
		
		this.binder_Inventario = new FieldGroup(pitm_Inventario);
		this.binder_Inventario.bind(this.cb_Dependencia, "dependencia");
		this.binder_Inventario.bind(this.cbGrupoContable, "grupoContable");
		this.binder_Inventario.bind(this.dt_fecha, "fecha");
		this.binder_Inventario.clear();
		
		this.cb_Dependencia.setRequired(true);
		this.cb_Dependencia.addValidator(new NullValidator("", false));
		
		this.cbGrupoContable.setRequired(true);
		this.cbGrupoContable.addValidator(new NullValidator("", false));
		
		fillfecha((Cierre_Gestion) fecha_depreciacion_impl.getall());
		fillcbGrupo();
		fillcbDependencia();
		buildContent();
	}
	
	private void fillcbDependencia() {
		cb_Dependencia.setNullSelectionAllowed(false);
		cb_Dependencia.setInputPrompt("Seleccione una Dependencia");
		for (Dependencia dependencia : dependencia_impl.getall()) {
			cb_Dependencia.addItem(dependencia.getDEP_Dependencia());
			cb_Dependencia.setItemCaption(dependencia.getDEP_Dependencia(), dependencia.getDEP_Nombre_Dependencia());
		}
		short a = 0;
		cb_Dependencia.addItem(a);
		cb_Dependencia.setItemCaption(a, Messages.ALL_DEP);
	}
	
	private void fillfecha(Cierre_Gestion data) {
		this.dt_fecha.setValue(data.getCGE_Fecha_Cierre_Gestion());
	}
	
	/**
	 * Actualizacion de los Campos
	 */
	public void update() {
		this.binder_Inventario.clear();
	}
	
	/**
	 * Llenado del Combo Box de Grupo Contable
	 */
	private void fillcbGrupo() {
		cbGrupoContable.setNullSelectionAllowed(false);
		cbGrupoContable.setInputPrompt("Seleccione Grupo Contable");
		for (GruposContablesModel grupo : grupoimpl.getalls()) {
			cbGrupoContable.addItem(grupo.getGRC_Grupo_Contable());
			cbGrupoContable.setItemCaption(grupo.getGRC_Grupo_Contable(), grupo.getGRC_Nombre_Grupo_Contable());
		}
	}
	
	private void buildContent() {
		
		this.cb_Dependencia.setWidth("100%");
		this.dt_fecha.setWidth("100%");
		this.cbGrupoContable.setWidth("100%");
		
		setColumnExpandRatio(0, 1);
		setColumnExpandRatio(1, 3);
		setColumnExpandRatio(2, 2);
		
		addComponent(this.dt_fecha, 0, 0);
		addComponent(cbGrupoContable, 1, 0);
		addComponent(this.cb_Dependencia, 1, 1);
		
	}
	
	public boolean validate() {
		try {
			binder_Inventario.commit();
			return true;
		} catch (CommitException ex) {
			Map<Field<?>, InvalidValueException> invalid_fields = ex.getInvalidFields();
			Iterator<Field<?>> it = invalid_fields.keySet().iterator();
			while (it.hasNext()) {
				Field<?> key = (Field<?>) it.next();
				mensajes.add(new BarMessage(key.getCaption(), invalid_fields.get(key).getMessage() == "" ? Messages.EMPTY_MESSAGE
						: invalid_fields.get(key).getMessage()));
			}
			return false;
		}
	}
	
	public List<BarMessage> getMessage() {
		return this.mensajes;
	}
	
	public void clearMessages() {
		this.mensajes.clear();
	}
}
