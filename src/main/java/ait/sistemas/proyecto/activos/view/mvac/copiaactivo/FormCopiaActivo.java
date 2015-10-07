package ait.sistemas.proyecto.activos.view.mvac.copiaactivo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class FormCopiaActivo extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	
	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxiliar Contable");
	
	public TextField txt_no_copias = new TextField("No Copias"); 
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private PropertysetItem pitm_solicitud = new PropertysetItem();
	private FieldGroup binder_solicitud;
	
	private final GrupoImpl grupoimpl = new GrupoImpl();
	private final AuxiliarImpl auxiliarimpl = new AuxiliarImpl();
	
	public GridActivos grid_solicitud = new GridActivos();
	
	public FormCopiaActivo() {
		
		super(6, 5);
		setSpacing(true);
		setWidth("100%");
		setMargin(true);
		pitm_solicitud.addItemProperty("grupo_contable", new ObjectProperty<GruposContablesModel>(new GruposContablesModel()));
		pitm_solicitud.addItemProperty("auxiliar_contable", new ObjectProperty<AuxiliaresContablesModel>(
				new AuxiliaresContablesModel()));
		pitm_solicitud.addItemProperty("copias", new ObjectProperty<String>(""));
		
		this.binder_solicitud = new FieldGroup(this.pitm_solicitud);
		
		binder_solicitud.bind(this.cb_grupo_contable, "grupo_contable");
		binder_solicitud.bind(this.cb_auxiliar_contable, "auxiliar_contable");
		binder_solicitud.bind(this.txt_no_copias, "copias");
		binder_solicitud.clear();
		
		this.txt_no_copias.setRequired(true);
		this.txt_no_copias.addValidator(new NullValidator("No Nulo", false));
		
		this.cb_grupo_contable.setRequired(true);
		this.cb_grupo_contable.addValidator(new NullValidator("No Nulo", false));
		cb_grupo_contable.setInputPrompt("Seleccione un Grupo Contable");
		cb_grupo_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.setRequired(true);
		cb_auxiliar_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.addValidator(new NullValidator("No Nulo", false));
		cb_auxiliar_contable.setInputPrompt("Seleccione un Auxiliar Contable");
		
		txt_no_copias.setWidth("90%");
		cb_grupo_contable.setWidth("90%");
		cb_auxiliar_contable.setWidth("90%");
		
		fillcbGrupoContable();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	private void fillcbGrupoContable() {
		cb_grupo_contable.setNullSelectionAllowed(false);
		
		for (GruposContablesModel grupo_contable : grupoimpl.getalls()) {
			cb_grupo_contable.addItem(grupo_contable);
			cb_grupo_contable.setItemCaption(grupo_contable, grupo_contable.getGRC_Nombre_Grupo_Contable());
		}
	}
	
	private void fillcbAuxiliarContable(String id_grupo) {
		cb_auxiliar_contable.removeAllItems();
		cb_auxiliar_contable.setNullSelectionAllowed(false);
		for (AuxiliaresContablesModel auxiliar_contable : auxiliarimpl.getreporte(id_grupo)) {
			cb_auxiliar_contable.addItem(auxiliar_contable);
			cb_auxiliar_contable.setItemCaption(auxiliar_contable, auxiliar_contable.getAUC_Nombre_Auxiliar_Contable());
		}
	}
	
	private void buildContent() {
		
//		Panel pn_solicitud = new Panel("Copias a Realiar del Activo");
		Panel pn_activos = new Panel("Seleccione un Grupo y Auxiliar Contable");
		pn_activos.setIcon(FontAwesome.SAVE);
		pn_activos.setStyleName(AitTheme.PANEL_FORM);
		GridLayout gridl_activos = new GridLayout(2, 1);
		gridl_activos.setSizeFull();
		gridl_activos.setMargin(true);
		gridl_activos.addComponent(this.cb_grupo_contable, 0, 0);
		gridl_activos.addComponent(this.cb_auxiliar_contable, 1, 0);
		pn_activos.setContent(gridl_activos);
		
		this.addComponent(pn_activos, 0, 1, 5, 1);

//		GridLayout gridl_solicitud = new GridLayout(1, 1);
//		gridl_solicitud.setSizeFull();
//		// gridl_solicitud.setMargin(true);
//		gridl_solicitud.addComponent(this.txt_no_copias, 0, 0);
//		pn_solicitud.setContent(gridl_solicitud);
//		
//		this.addComponent(pn_solicitud, 0, 4, 1, 4);
		
	}
	
	public void update() {
		binder_solicitud.clear();
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			binder_solicitud.commit();
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
	
	public ActivosModel setData() {
		ActivosModel resul = new ActivosModel();
		resul.setACT_Codigo_Activo(resul.getACT_Codigo_Activo());
		return resul;
	}
//	public void setData(ActivosModel data){	
//		data.getACT_Codigo_Activo().setValue(String.valueOf(data.getACT_Codigo_Activo()));
//		this.txt_nombre_ciudad.setValue(String.valueOf(data.getCIU_Nombre_Ciudad()));
//		this.txt_sigla_ciudad.setValue(String.valueOf(data.getCIU_Sigla()));
//				
//	}
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty() == cb_grupo_contable && this.cb_grupo_contable.getValue() != null) {
			GruposContablesModel grupo = (GruposContablesModel) cb_grupo_contable.getValue();
			fillcbAuxiliarContable(grupo.getGRC_Grupo_Contable());
		}
		if (event.getProperty() == cb_auxiliar_contable && this.cb_auxiliar_contable.getValue() != null) {
			AuxiliaresContablesModel auxiliar = (AuxiliaresContablesModel) cb_auxiliar_contable.getValue();
			buildGrid(auxiliar.getAUC_Auxiliar_Contable());
		}
	}
	
	private void buildGrid(String auc_Auxiliar_Contable) {
		GruposContablesModel grupo = (GruposContablesModel) cb_grupo_contable.getValue();
		this.grid_solicitud.update(grupo.getGRC_Grupo_Contable(), auc_Auxiliar_Contable);
	}
	
	public Component getgrid_solicitud() {
		return this.grid_solicitud;
	}

	public void clear() {
	
		this.binder_solicitud.clear();
		this.grid_solicitud = new GridActivos();
		
	}
}
