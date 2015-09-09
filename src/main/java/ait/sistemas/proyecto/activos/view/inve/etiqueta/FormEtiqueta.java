package ait.sistemas.proyecto.activos.view.inve.etiqueta;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.Auxiliares_Contable;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;

/**
 * Formulario que Para la impresion de Etiquetas de Activos contiene los
 * siguientes campos: ComboBox de Grupos Contables ComboBox de Auxiliares
 * Contables ComboBox de Activos
 * 
 * @author franzemil
 *
 */
public class FormEtiqueta extends GridLayout implements ValueChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private static final int ALL = 0;
	
	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxliar Contable");
	public ComboBox cb_activos = new ComboBox("Activo");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	final private GrupoImpl grupoimpl = new GrupoImpl();
	final private AuxiliarImpl auxiliarimpl = new AuxiliarImpl();
	final private ActivoImpl activoimpl = new ActivoImpl();
	
	final PropertysetItem pitm_Inventario = new PropertysetItem();
	private FieldGroup binder_etiqueta;
	
	public FormEtiqueta() {
		super(1, 3);
		setWidth("100%");
		setMargin(true);
		setSpacing(true);
		
		pitm_Inventario.addItemProperty("grupo_contable", new ObjectProperty<GruposContablesModel>(new GruposContablesModel()));
		pitm_Inventario.addItemProperty("auxiliar_contable", new ObjectProperty<Auxiliares_Contable>(new Auxiliares_Contable()));
		pitm_Inventario.addItemProperty("activo", new ObjectProperty<ActivosModel>(new ActivosModel()));
		
		this.binder_etiqueta = new FieldGroup(pitm_Inventario);
		
		this.binder_etiqueta.bind(this.cb_grupo_contable, "grupo_contable");
		this.binder_etiqueta.bind(this.cb_auxiliar_contable, "auxiliar_contable");
		this.binder_etiqueta.bind(this.cb_activos, "activo");
		
		this.cb_grupo_contable.addValidator(new NullValidator("", false));
		this.cb_auxiliar_contable.addValidator(new NullValidator("", false));
		this.cb_activos.addValidator(new NullValidator("", false));
		
		this.cb_grupo_contable.setWidth("100%");
		this.cb_auxiliar_contable.setWidth("100%");
		this.cb_activos.setWidth("100%");
		
		this.binder_etiqueta.clear();
		
		this.cb_grupo_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.addValueChangeListener(this);
		
		fillcbGrupo();
		buildContent();
	}
	
	public void init() {
		update();
	}
	
	/**
	 * Actulizacion de los Campo del Formulario
	 */
	public void update() {
		this.binder_etiqueta.clear();
	}
	
	/**
	 * Llenado del Combo Box Grupo Contable
	 */
	private void fillcbGrupo() {
		cb_grupo_contable.removeAllItems();
		cb_grupo_contable.setNullSelectionAllowed(false);
		cb_grupo_contable.setInputPrompt("Seleccione un Grupo Contable");
		for (GruposContablesModel grupo_contable : grupoimpl.getalls()) {
			cb_grupo_contable.addItem(grupo_contable);
			cb_grupo_contable.setItemCaption(grupo_contable, grupo_contable.getGRC_Nombre_Grupo_Contable());
		}
	}
	
	/**
	 * Añade los Componentes al Form
	 */
	private void buildContent() {
		
		addComponent(this.cb_grupo_contable, 0, 0);
		addComponent(this.cb_auxiliar_contable, 0, 1);
		addComponent(this.cb_activos, 0, 2);
		
	}
	
	/**
	 * Valida todos los Campo y alamacena los mensajes dentro la variable
	 * mensajes
	 * 
	 * @return boolean
	 */
	public boolean validate() {
		try {
			this.binder_etiqueta.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			try {
				this.cb_grupo_contable.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_grupo_contable.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_auxiliar_contable.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_auxiliar_contable.getCaption(), Messages.EMPTY_MESSAGE));
			}
			try {
				this.cb_activos.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.cb_activos.getCaption(), Messages.EMPTY_MESSAGE));
			}
			
			return false;
		}
	}
	
	public List<BarMessage> getMessage() {
		return this.mensajes;
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty().getValue() == cb_grupo_contable.getValue() && cb_grupo_contable.getValue() != null) {
			fillcbAuxiliarContable(((GruposContablesModel)cb_grupo_contable.getValue()).getGRC_Grupo_Contable());
		}
		
		if (event.getProperty().getValue() == cb_auxiliar_contable.getValue() && cb_auxiliar_contable.getValue() != null) {
			
		}
	}

	/**
	 * Se encarga de llenar los datos del ComboBox cb_auxiliar_contable
	 * mediante el Id del Grupo Contable
	 * @param grupo_contable Id del Grupo Auxilaar
	 */
	private void fillcbAuxiliarContable(String grupo_contable) {
		cb_auxiliar_contable.removeAllItems();
		cb_auxiliar_contable.setNullSelectionAllowed(false);
		cb_auxiliar_contable.setInputPrompt("Seleccione un Auxiliar Contable");
		for (AuxiliaresContablesModel auxiliar_contable : auxiliarimpl.getreporte(grupo_contable)) {
			cb_auxiliar_contable.addItem(auxiliar_contable);
			cb_auxiliar_contable.setItemCaption(auxiliar_contable, auxiliar_contable.getAUC_Nombre_Auxiliar_Contable());
		}
	}
}
