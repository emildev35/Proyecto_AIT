package ait.sistemas.proyecto.activos.view.mvac.copiaactivo;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Notification.Type;

public class FormCopiaActivo extends GridLayout implements ValueChangeListener, TextChangeListener,ClickListener {
	private static final long serialVersionUID = 1L;
	
	public TextField txt_codigo = new TextField("Codigo"); 

	public ComboBox cb_grupo_contable = new ComboBox("Grupo Contable");
	public ComboBox cb_auxiliar_contable = new ComboBox("Auxiliar Contable");
	
	public TextField txt_no_copias = new TextField("No Copias"); 
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private PropertysetItem pitm_copia = new PropertysetItem();
	private FieldGroup binder_copia;
	
	private final GrupoImpl grupoimpl = new GrupoImpl();
	private final AuxiliarImpl auxiliarimpl = new AuxiliarImpl();
	private final ActivoImpl activo_impl=new ActivoImpl();
	
	public GridActivos grid_activos = new GridActivos();
	Panel pn_activos;
	Panel pn_gc_ac;
	public FormCopiaActivo() {
		
		super(6, 5);
		setSpacing(true);
		setWidth("100%");
		setMargin(true);
		pitm_copia.addItemProperty("codigo", new ObjectProperty<String>(""));
		pitm_copia.addItemProperty("grupo_contable", new ObjectProperty<GruposContablesModel>(new GruposContablesModel()));
		pitm_copia.addItemProperty("auxiliar_contable", new ObjectProperty<AuxiliaresContablesModel>(
				new AuxiliaresContablesModel()));
		pitm_copia.addItemProperty("copias", new ObjectProperty<String>(""));
		
		this.binder_copia = new FieldGroup(this.pitm_copia);
		
		binder_copia.bind(this.txt_codigo, "codigo");
		binder_copia.bind(this.cb_grupo_contable, "grupo_contable");
		binder_copia.bind(this.cb_auxiliar_contable, "auxiliar_contable");
		binder_copia.bind(this.txt_no_copias, "copias");
		binder_copia.clear();
		
		this.txt_codigo.addTextChangeListener(this);
//		this.txt_codigo.addValidator(new NullValidator("No Nulo", false));
		this.txt_no_copias.setRequired(true);
		this.txt_no_copias.addValidator(new NullValidator("", false));
		
//		this.cb_grupo_contable.setRequired(true);
		this.cb_grupo_contable.addValidator(new NullValidator("", false));
		cb_grupo_contable.setInputPrompt("Seleccione un Grupo Contable");
		cb_grupo_contable.addValueChangeListener(this);
//		this.cb_auxiliar_contable.setRequired(true);
		cb_auxiliar_contable.addValueChangeListener(this);
		this.cb_auxiliar_contable.addValidator(new NullValidator("", false));
		cb_auxiliar_contable.setInputPrompt("Seleccione un Auxiliar Contable");
		
		txt_codigo.setWidth("60%");
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
		pn_activos = new Panel("Codigo Activo");
		pn_activos.setIcon(FontAwesome.SAVE);
		pn_activos.setStyleName(AitTheme.PANEL_FORM);
		pn_activos.setWidth("37%");
		GridLayout gridl_solicitud = new GridLayout(1, 1);
		gridl_solicitud.setSizeFull();
		gridl_solicitud.setColumnExpandRatio(0, 0);
		// gridl_solicitud.setMargin(true);
		gridl_solicitud.addComponent(this.txt_codigo, 0, 0);
		pn_activos.setContent(gridl_solicitud);
		
		this.addComponent(pn_activos, 0, 1, 1, 1);
		
		pn_gc_ac = new Panel("Seleccione un Grupo y Auxiliar Contable");
		pn_gc_ac.setIcon(FontAwesome.SAVE);
		pn_gc_ac.setStyleName(AitTheme.PANEL_FORM);
		GridLayout gridl_activos = new GridLayout(2, 1);
		gridl_activos.setSizeFull();
//		gridl_activos.setMargin(true);
		gridl_activos.addComponent(this.cb_grupo_contable, 0, 0);
		gridl_activos.addComponent(this.cb_auxiliar_contable, 1, 0);
		pn_gc_ac.setContent(gridl_activos);
		
		pn_activos.addClickListener(this);
		pn_gc_ac.addClickListener(this);
		
		this.addComponent(pn_gc_ac, 2, 1, 5, 1);
		
	}
	
	public void update() {
		binder_copia.clear();
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		if (txt_codigo.getValue() != null && !txt_codigo.getValue().equals("")) {
			try {
				this.txt_no_copias.validate();
				return true;
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.txt_no_copias.getCaption(), Messages.EMPTY_MESSAGE));
			}
			return false;
		}
		try {
			this.binder_copia.commit();
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
				this.txt_no_copias.validate();
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage(this.txt_no_copias.getCaption(), Messages.EMPTY_MESSAGE));
			}
			return false;
		}
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
		this.grid_activos.update(grupo.getGRC_Grupo_Contable(), auc_Auxiliar_Contable);
	}
	
	public Component getgrid_activos() {
		return this.grid_activos;
	}

	public void clear() {
	
		this.binder_copia.clear();
		this.grid_activos = new GridActivos();
		
	}

	@Override
	public void textChange(TextChangeEvent event) {
		if (event.getComponent() == txt_codigo) {

			if (event.getComponent().toString().length() > 0) {
				try {
					ActivoGrid activo= activo_impl.getActivoOne(Long.valueOf(event.getText()));
					if ( activo != null )
						{
						this.grid_activos.updateActivo(event.getText());
						}
					else{
						Notification.show(Messages.ACTIVO_NO_ENCONTRADO_DB, Type.ERROR_MESSAGE);
						clearMessages();
//						this.grid_activos =new GridActivos();
//						this.mensajes.add(new BarMessage(this.txt_codigo.getCaption(), Messages.ACTIVO_NO_ENCONTRADO_DB));
					}
					
				} catch (NumberFormatException ex) {
					
				}
			}
		}
	}

	@Override
	public void click(ClickEvent event) {
		if (event.getSource() == pn_activos) {
			this.cb_auxiliar_contable.setEnabled(false);
			this.cb_grupo_contable.setEnabled(false);
			this.txt_codigo.setEnabled(true);
		}
		
		if (event.getSource() == pn_gc_ac) {
			this.cb_auxiliar_contable.setEnabled(true);
			this.cb_grupo_contable.setEnabled(true);
			this.txt_codigo.setEnabled(false);
		}
	}
}
