package ait.sistemas.proyecto.activos.view.mvac.actualiza;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.session.ActivoSession;
import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.model.AuxiliaresContablesModel;
import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.AuxiliarImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * Formulario de Seleccion de Activos para su posterior Modificacion contiene
 * los siguientes Campos: ----------------------------------------- Codigo |
 * TextField GrupoContable ----------------------------------------- ComboBox
 * Auxiliar | Contable ComboBox -----------------------------------------
 * GridActivos | GridActActivo -----------------------------------------
 * 
 * @author franzemil
 *
 */
public class FormSeleccion extends GridLayout implements ValueChangeListener {
	
	private static final long serialVersionUID = 1L;
	
	private TextField txt_codigoActivo = new TextField("Codigo");
	private ComboBox cb_grupoContable = new ComboBox("Grupo Contable");
	private ComboBox cb_auxiliarContable = new ComboBox("Auxiliar Contable");
	
	private GridActActivo grid_actActivo = new GridActActivo();
	
	private PropertysetItem pitm_actActivo = new PropertysetItem();
	private FieldGroup binder_actActivo;
	
	private final GrupoImpl grupoimpl = new GrupoImpl();
	private final AuxiliarImpl auxiliarimpl = new AuxiliarImpl();
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	
	public FormSeleccion() {
		super(6, 2);
		
		this.pitm_actActivo.addItemProperty("grupo", new ObjectProperty<GruposContablesModel>(new GruposContablesModel()));
		this.pitm_actActivo.addItemProperty("auxiliar", new ObjectProperty<String>(""));
		
		binder_actActivo = new FieldGroup(pitm_actActivo);
		binder_actActivo.bind(this.cb_grupoContable, "grupo");
		binder_actActivo.bind(this.cb_auxiliarContable, "auxiliar");
		
		txt_codigoActivo.setWidth("75px");
		cb_grupoContable.setWidth("100%");
		cb_auxiliarContable.setWidth("100%");
		
		this.txt_codigoActivo.setRequired(true);
		this.txt_codigoActivo.addValidator(new NullValidator("", false));
		
		this.cb_grupoContable.setRequired(true);
		this.cb_grupoContable.addValidator(new NullValidator("", false));
		this.cb_auxiliarContable.setRequired(true);
		this.cb_auxiliarContable.addValidator(new NullValidator("", false));
		
		this.cb_grupoContable.addValueChangeListener(this);
		this.cb_auxiliarContable.addValueChangeListener(this);
		setWidth("100%");
		setMargin(true);
		setSpacing(true);
		buildFormContent();
		fillcbGrupoContable();
		buildId();
		Responsive.makeResponsive(this);
	}
	
	private void fillcbGrupoContable() {
		this.cb_grupoContable.setNullSelectionAllowed(false);
		this.cb_grupoContable.setInputPrompt("SELECCIONE UN GRUPO CONTABLE");
		this.cb_auxiliarContable.setInputPrompt("SELECCIONE UN AUXILIAR CONTABLE");
		for (GruposContablesModel grupo : grupoimpl.getalls()) {
			this.cb_grupoContable.addItem(grupo);
			this.cb_grupoContable.setItemCaption(grupo, grupo.getGRC_Nombre_Grupo_Contable());
		}
	}
	
	private void fillcbAuxiliarContable(GruposContablesModel grupo) {
		cb_auxiliarContable.removeAllItems();
		this.cb_auxiliarContable.setNullSelectionAllowed(false);
		this.cb_auxiliarContable.setInputPrompt("SELECCIONE UN AUXILIAR CONTABLE");
		for (AuxiliaresContablesModel auxiliar : auxiliarimpl.getreporte(grupo.getGRC_Grupo_Contable())) {
			this.cb_auxiliarContable.addItem(auxiliar);
			this.cb_auxiliarContable.setItemCaption(auxiliar, auxiliar.getAUC_Nombre_Auxiliar_Contable());
		}
	}
	
	private void buildFormContent() {
		setColumnExpandRatio(0, 0.1f);
		setColumnExpandRatio(1, 1f);
		setColumnExpandRatio(2, 1f);
		setColumnExpandRatio(3, 1f);
		setColumnExpandRatio(4, 1f);
		setColumnExpandRatio(5, 1f);
		
		GridLayout gridl_codigo = new GridLayout(1, 1);
		GridLayout gridl_grupo = new GridLayout(2, 1);
		
		gridl_codigo.setResponsive(true);
		gridl_codigo.setWidth("100%");
		gridl_codigo.setMargin(true);
		gridl_codigo.setSpacing(true);
		
		gridl_grupo.setResponsive(true);
		gridl_grupo.setWidth("100%");
		gridl_grupo.setMargin(true);
		gridl_grupo.setSpacing(true);
		
		Panel pn_codigo = new Panel("SELECCION POR CODIGO DE ACTIVO:");
		pn_codigo.setResponsive(true);
		pn_codigo.setStyleName(AitTheme.PANEL_FORM);
		pn_codigo.setIcon(FontAwesome.EDIT);
		pn_codigo.setContent(gridl_codigo);
		
		Panel pn_contables = new Panel("SELECCION POR GRUPO Y AUXILIAR CONTABLE:");
		pn_contables.setResponsive(true);
		pn_contables.setStyleName(AitTheme.PANEL_FORM);
		pn_contables.setIcon(FontAwesome.EDIT);
		pn_contables.setContent(gridl_grupo);
		
		gridl_codigo.addComponent(txt_codigoActivo);
		
		gridl_grupo.addComponent(cb_grupoContable, 0, 0);
		gridl_grupo.addComponent(cb_auxiliarContable, 1, 0);
		
		Panel pn_grid = new Panel("SELECCIONE EL ACTIVO FIJO QUE DESEA ACTUALIZAR");
		pn_grid.setResponsive(true);
		pn_grid.setStyleName(AitTheme.PANEL_GRID);
		pn_grid.setIcon(FontAwesome.TABLE);
		pn_grid.setContent(grid_actActivo);
		
		addComponent(pn_codigo, 0, 0, 1, 0);
		addComponent(pn_contables, 2, 0, 5, 0);
		addComponent(pn_grid, 0, 1, 5, 1);
	}
	
	private void buildId() {
		binder_actActivo.clear();
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty() == cb_grupoContable && cb_grupoContable.getValue() != null) {
			fillcbAuxiliarContable((GruposContablesModel) cb_grupoContable.getValue());
		}
		if (event.getProperty() == cb_auxiliarContable && cb_auxiliarContable.getValue() != null) {
			this.grid_actActivo.builGrid(
					((AuxiliaresContablesModel) cb_auxiliarContable.getValue()).getAUC_Auxiliar_Contable(),
					((AuxiliaresContablesModel) cb_auxiliarContable.getValue()).getAUC_Grupo_Contable_ID()
					);
		}
	}
	
	public boolean validate() {
		
		if (this.grid_actActivo.getSelectedRow() != null || this.txt_codigoActivo.getValue() != null
				&& !this.txt_codigoActivo.getValue().equals("")) {
			return true;
		}
		this.mensajes.add(new BarMessage("Formulario", "Debe Seleccionar un Activo del Grid o Digitar el Codigo de uno"));
		return false;
		
	}
	
	public void clean() {
		buildId();
	}
	
	public List<BarMessage> getMensajes() {
		return this.mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	/**
	 * Retorna el Grid
	 * 
	 * @return
	 */
	public GridActActivo getGrid() {
		return this.grid_actActivo;
	}
	
	/**
	 * Retorna del Campo de txt_codigoActivo
	 * 
	 * @return
	 */
	public TextField getCodigo() {
		return this.txt_codigoActivo;
	}
	
	/**
	 * Retorna el Activo Seleccionado
	 * 
	 * @return
	 */
	public ActivoSession getActivo() {
		ActivoSession activo = new ActivoSession();
		if (txt_codigoActivo.getValue() != null && !txt_codigoActivo.getValue().equals("")) {
			final ActivoImpl activoimpl = new ActivoImpl();
			try {
				ActivoGrid result = activoimpl.getone(Long.parseLong(txt_codigoActivo.getValue().toString()),
						session.getId_dependecia());
				activo.setCodigo(result.getId_activo());
				activo.setNombre_activo(result.getNombre());
				activo.setDependencia(session.getId_dependecia());
			} catch (Exception ex) {
				this.mensajes.add(new BarMessage("Formulario", "Codigo de Activo No Valido"));
				return null;
			}
		} else {
			ActivosModel selectrow = (ActivosModel) grid_actActivo.getSelectedRow();
			activo.setCodigo(Long.parseLong(selectrow.getACT_Codigo_Activo()));
			activo.setNombre_activo(selectrow.getACT_Nombre_Activo());
			activo.setDependencia(selectrow.getACT_Dependencia_ID());
		}
		return activo;
	}
}
