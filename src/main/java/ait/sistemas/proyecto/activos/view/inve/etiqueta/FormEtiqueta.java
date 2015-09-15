package ait.sistemas.proyecto.activos.view.inve.etiqueta;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Dependencia;
import ait.sistemas.proyecto.activos.data.model_rrhh.PersonalModel;
import ait.sistemas.proyecto.activos.data.model_rrhh.Unidades_Organizacionale;
import ait.sistemas.proyecto.activos.data.service.Impl.DependenciaImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.PersonalImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.UnidadImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.CodeBar;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;

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
	
	public ComboBox cb_dependencia = new ComboBox("Dependencia");
	public ComboBox cb_unidad_organizacional = new ComboBox("Unidad Organizacional");
	public ComboBox cb_usuario = new ComboBox("Personal");
	
	private List<BarMessage> mensajes = new ArrayList<BarMessage>();
	
	final private DependenciaImpl dependenciaimpl = new DependenciaImpl();
	final private UnidadImpl unidadimpl = new UnidadImpl();
	final private PersonalImpl personalimpl = new PersonalImpl();
	
	private GridActivos grid_activos = new GridActivos();
	
	SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	
	public FormEtiqueta() {
		super(2, 3);
		setWidth("100%");
		setMargin(true);
		setSpacing(true);
		
		this.cb_dependencia.addValidator(new NullValidator("", false));
		this.cb_unidad_organizacional.addValidator(new NullValidator("", false));
		this.cb_usuario.addValidator(new NullValidator("", false));
		this.cb_dependencia.setWidth("100%");
		this.cb_unidad_organizacional.setWidth("100%");
		this.cb_usuario.setWidth("100%");
		
		this.cb_dependencia.addValueChangeListener(this);
		this.cb_unidad_organizacional.addValueChangeListener(this);
		this.cb_usuario.addValueChangeListener(this);
		
		cb_unidad_organizacional.setInputPrompt("Seleccione una Unidad Organizacional");
		cb_usuario.setInputPrompt("Seleccione el Personal");
		
		fillcbDependencia();
		buildContent();
	}
	
	/**
	 * Llenado del Combo Box de Dependencia
	 */
	private void fillcbDependencia() {
		cb_dependencia.removeAllItems();
		cb_dependencia.setNullSelectionAllowed(false);
		cb_dependencia.setInputPrompt("Seleccione una Dependencia");
		for (Dependencia dependencia : dependenciaimpl.getall()) {
			cb_dependencia.addItem(dependencia);
			cb_dependencia.setItemCaption(dependencia, dependencia.getDEP_Nombre_Dependencia());
		}
	}
	
	/**
	 * Añade los Componentes al Form
	 */
	private void buildContent() {
		
		addComponent(this.cb_dependencia, 0, 0);
		addComponent(this.cb_unidad_organizacional, 0, 1);
		addComponent(this.cb_usuario, 0, 2);
		
	}
	
	/**
	 * Valida todos los Campo y alamacena los mensajes dentro la variable
	 * mensajes
	 * 
	 * @return boolean
	 */
	public boolean validate() {
		if (this.grid_activos.getSelectedRows().size() > 0) {
			return true;
		} else {
			
			this.mensajes.add(new BarMessage("Formulario", Messages.EMPTY_GRID));
			
			return false;
		}
	}
	/**
	 * Retorna los mensajes que se generaron dentro de Formulario
	 * @return
	 */
	public List<BarMessage> getMessage() {
		return this.mensajes;
	}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (event.getProperty().getValue() == cb_dependencia.getValue() && cb_dependencia.getValue() != null) {
			fillcbUnidades(((Dependencia) cb_dependencia.getValue()).getDEP_Dependencia());
			grid_activos.buildGrid(((Dependencia) cb_dependencia.getValue()).getDEP_Dependencia());
		}
		
		if (event.getProperty().getValue() == cb_unidad_organizacional.getValue() && cb_unidad_organizacional.getValue() != null) {
			fillcbPersonal(((Unidades_Organizacionale) cb_unidad_organizacional.getValue()).getUNO_Unidad_Organizacional());
			grid_activos.buildGrid(((Unidades_Organizacionale) cb_unidad_organizacional.getValue()).getUNO_Dependencia(),
					((Unidades_Organizacionale) cb_unidad_organizacional.getValue()).getUNO_Unidad_Organizacional());
		}
		if (event.getProperty().getValue() == cb_usuario.getValue() && cb_usuario.getValue() != null) {
			grid_activos.buildGrid(((PersonalModel) cb_usuario.getValue()).getPER_CI_Empleado());
		}
	}
	
	/**
	 * Se encarga de llenar los datos del ComboBox cb_usuario mediante el Id del
	 * Auxiliar Contable
	 * 
	 * @param AuxiliaresContablesModel
	 *            Auxiliar Contable
	 */
	private void fillcbPersonal(short unidad_organizacional) {
		cb_usuario.removeAllItems();
		cb_usuario.setNullSelectionAllowed(false);
		cb_usuario.setInputPrompt("Seleccione el Personal");
		for (PersonalModel personal : personalimpl.getbyUnidad(unidad_organizacional)) {
			cb_usuario.addItem(personal);
			cb_usuario.setItemCaption(personal, personal.getPER_Apellido_Paterno() + " " + personal.getPER_Apellido_Materno()
					+ " " + personal.getPER_Nombres());
		}
		
	}
	
	/**
	 * Se encarga de llenar los datos del ComboBox cb_unidad_organizacional
	 * mediante el Id de la Dependencia
	 * 
	 * @param dependencia
	 *            Id de la Dependencia
	 */
	private void fillcbUnidades(short id_dependencia) {
		cb_unidad_organizacional.removeAllItems();
		cb_unidad_organizacional.setNullSelectionAllowed(false);
		cb_unidad_organizacional.setInputPrompt("Seleccione una Unidad Organizacional");
		for (Unidades_Organizacionale unidad : unidadimpl.getunidad(id_dependencia)) {
			cb_unidad_organizacional.addItem(unidad);
			cb_unidad_organizacional.setItemCaption(unidad, unidad.getUNO_Nombre_Unidad_Organizacional());
		}
	}
	/**
	 * Retorna el Grid de los Activos
	 * @return
	 */
	public GridActivos getGrid() {
		return this.grid_activos;
	}
	/**
	 * Retorna los codigos generados en base a los Activos que se seleccionaron
	 * en el Grid de Activos
	 * @return
	 */
	public List<CodeBar> getActivos() {
		List<CodeBar> result = new ArrayList<CodeBar>();
		for (Object row : grid_activos.getSelectedRows()) {
			if (row != null) {
				ActivosModel activo = (ActivosModel) row;
				CodeBar codigo = new CodeBar();
				codigo.setAuxiliar_contable(activo.getACT_Auxiliar_Contable_ID());
				codigo.setGrupo_contable(activo.getACT_Grupo_Contable_ID());
				codigo.setCodigo(activo.getACT_Codigo_Activo());
				codigo.setNombre(activo.getACT_Nombre_Activo());
				result.add(codigo);
			}
		}
		return result;
	}
}
