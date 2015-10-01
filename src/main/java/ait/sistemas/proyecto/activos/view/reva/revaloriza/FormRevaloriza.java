package ait.sistemas.proyecto.activos.view.reva.revaloriza;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.ActivoInventario;
import ait.sistemas.proyecto.activos.component.model.CmovimientoDocumento;
import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class FormRevaloriza extends GridLayout implements TextChangeListener {
	private static final long serialVersionUID = 1L;
	private TextField txt_no_doc = new TextField("No. Documento");
	public DateField dtf_fecha_doc = new DateField("Fecha");

	private TextField txt_resolucion = new TextField("No. Resolucion");
	public DateField dtf_fecha_resol = new DateField("Fecha");

	public TextField txtcodigo = new TextField("Codigo:");
	public TextField txtnombre_activo = new TextField("Nombre del Activo:");
	public TextField txtnuevo_valor = new TextField("Nuevo Valor:");
	public TextField txtnueva_vida_util = new TextField("Nueva Vida Util:");

	private List<BarMessage> mensajes = new ArrayList<BarMessage>();

	private PropertysetItem pitm_revaloriza = new PropertysetItem();
	private FieldGroup binder_revaloriza;

	private MovimientoImpl movimientoimpl = new MovimientoImpl();
	private final ActivoImpl activoimpl = new ActivoImpl();
	private GridRevaloriza grid_revaloriza = new GridRevaloriza();

	private List<ActivoInventario> activos_invetariados = new ArrayList<ActivoInventario>();
	private VerticalLayout layout_errores;
	private final SessionModel session = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
	public CmovimientoDocumento resol;

	public FormRevaloriza(VerticalLayout layout) {

		super(6, 3);
		setSpacing(true);
		setWidth("100%");
		setMargin(true);
		pitm_revaloriza.addItemProperty("id_resolucion", new ObjectProperty<String>(""));
		pitm_revaloriza.addItemProperty("fecha_resolucion", new ObjectProperty<Date>(new Date()));
		pitm_revaloriza.addItemProperty("id_informe", new ObjectProperty<Integer>(0));
		pitm_revaloriza.addItemProperty("fecha_informe", new ObjectProperty<String>(""));
		pitm_revaloriza.addItemProperty("codigo", new ObjectProperty<String>(""));
		pitm_revaloriza.addItemProperty("nombre_activo", new ObjectProperty<String>(""));
		pitm_revaloriza.addItemProperty("nuevo_valor", new ObjectProperty<String>(""));
		pitm_revaloriza.addItemProperty("nueva_vida_util", new ObjectProperty<String>(""));

		this.binder_revaloriza = new FieldGroup(this.pitm_revaloriza);

		binder_revaloriza.bind(this.txt_resolucion, "id_resolucion");
		binder_revaloriza.bind(this.dtf_fecha_resol, "fecha_resolucion");
		binder_revaloriza.bind(this.txt_no_doc, "id_informe");
		binder_revaloriza.bind(this.dtf_fecha_doc, "fecha_informe");
		binder_revaloriza.bind(this.txtcodigo, "codigo");
		binder_revaloriza.bind(this.txtnombre_activo, "nombre_activo");
		binder_revaloriza.bind(this.txtnuevo_valor, "nuevo_valor");
		binder_revaloriza.bind(this.txtnueva_vida_util, "nueva_vida_util");
		binder_revaloriza.clear();

		this.txt_no_doc.setEnabled(false);
		this.dtf_fecha_doc.setEnabled(false);
		// this.txt_no_doc.addValidator(new NullValidator("No Nulo", false));
		this.txt_resolucion.setRequired(true);
		this.txt_resolucion.addValidator(new NullValidator("No Nulo", false));

		this.dtf_fecha_resol.setRequired(true);
		// this.dtf_fecha_doc.addValidator(new NullValidator("No Nulo", false));
		this.txtcodigo.setEnabled(false);
		this.txtcodigo.addTextChangeListener(this);
		this.txtcodigo.setRequired(true);
		this.txtcodigo.addValidator(new NullValidator("No Nulo", false));
		this.txtnombre_activo.setEnabled(false);
		this.txtnombre_activo.setRequired(true);
		this.txtnombre_activo.addValidator(new NullValidator("No Nulo", false));
		this.txtnuevo_valor.setEnabled(false);
		this.txtnuevo_valor.setRequired(true);
		this.txtnuevo_valor.addValidator(new NullValidator("No Nulo", false));
		this.txtnueva_vida_util.setEnabled(false);
		this.txtnueva_vida_util.setRequired(true);
		this.txtnueva_vida_util.addValidator(new NullValidator("No Nulo", false));

		txt_resolucion.setWidth("90%");
		dtf_fecha_resol.setWidth("90%");
		txt_no_doc.setWidth("90%");
		dtf_fecha_doc.setWidth("90%");
		txtcodigo.setWidth("90%");
		txtnombre_activo.setWidth("90%");
		txtnuevo_valor.setWidth("90%");
		txtnueva_vida_util.setWidth("90%");

		this.layout_errores = layout;
		buildId();
		buildContent();
		Responsive.makeResponsive(this);
	}

	private void buildId() {
		this.txt_no_doc.setValue(String.valueOf(movimientoimpl.getId((short) 15)));
		this.dtf_fecha_doc.setValue(new Date());
		this.dtf_fecha_resol.setValue(new Date());
	}

	private void buildContent() {

		Panel pn_doc = new Panel("Documento de Revalorizaion");
		Panel pn_resol = new Panel("Resolucion de Revalorizacion");
		Panel pn_registro = new Panel("Registre los datos del Activo Fijo Revalorizado");
		pn_registro.setIcon(FontAwesome.EDIT);
		pn_registro.setStyleName(AitTheme.PANEL_FORM);

		GridLayout gridl_doc = new GridLayout(2, 1);
		gridl_doc.setSizeFull();
		// gridl_solicitud.setMargin(true);
		gridl_doc.setColumnExpandRatio(0, 0.2f);
		gridl_doc.setColumnExpandRatio(1, 0.5f);
		gridl_doc.addComponent(this.txt_no_doc, 0, 0);
		gridl_doc.addComponent(this.dtf_fecha_doc, 1, 0);
		pn_doc.setContent(gridl_doc);

		this.addComponent(pn_doc, 0, 0, 1, 0);

		GridLayout gridl_res = new GridLayout(2, 1);
		gridl_res.setSizeFull();
		// gridl_solicitud.setMargin(true);
		gridl_res.setColumnExpandRatio(0, 1);
		gridl_res.setColumnExpandRatio(1, 0.5f);
		gridl_res.addComponent(this.txt_resolucion, 0, 0);
		gridl_res.addComponent(this.dtf_fecha_resol, 1, 0);
		pn_resol.setContent(gridl_res);

		this.addComponent(pn_resol, 4, 0, 5, 0);

		GridLayout gridl_activos = new GridLayout(4, 1);
		gridl_activos.setSizeFull();
		gridl_activos.setMargin(true);
		gridl_activos.setColumnExpandRatio(0, 0.1f);
		gridl_activos.setColumnExpandRatio(1, 1);
		gridl_activos.setColumnExpandRatio(2, 0.2f);
		gridl_activos.setColumnExpandRatio(3, 0.2f);
		gridl_activos.addComponent(this.txtcodigo, 0, 0);
		gridl_activos.addComponent(this.txtnombre_activo, 1, 0);
		gridl_activos.addComponent(this.txtnuevo_valor, 2, 0);
		gridl_activos.addComponent(this.txtnueva_vida_util, 3, 0);
		pn_registro.setContent(gridl_activos);

		this.addComponent(pn_registro, 0, 1, 5, 1);

		gridl_res.addShortcutListener(new ShortcutListener("Key Enter", KeyCode.ENTER, new int[] {}) {
			private static final long serialVersionUID = 1L;
			@Override
			public void handleAction(Object sender, Object target) {

				if (!txt_resolucion.getValue().equals(null) && !dtf_fecha_resol.getValue().equals(null)) {
					if (resol == null) {
						resol = new CmovimientoDocumento(txt_resolucion.getValue(), new java.sql.Date(dtf_fecha_resol
								.getValue().getTime()));
						txt_resolucion.setValue("");
						dtf_fecha_resol.setValue(null);
						clearMessages();
						mensajes.add(new BarMessage("Formulario", Messages.LLENAR_NUEVAMENTE));

					} else {
						if (resol.equals(new CmovimientoDocumento(txt_resolucion.getValue(), new java.sql.Date(
								dtf_fecha_resol.getValue().getTime())))) {
							txtcodigo.setEnabled(true);
							txtnuevo_valor.setEnabled(true);
							txtnueva_vida_util.setEnabled(true);
							clearMessages();
							mensajes.add(new BarMessage("Formulario", Messages.KEY_ENTER));
						} else {
							resol = null;
							txt_resolucion.setValue("");
							dtf_fecha_resol.setValue(null);
							clearMessages();
							mensajes.add(new BarMessage("Formulario", Messages.CAMPOS_DSITINTOS));
							// TODO vaciar y mostrar mensaje
						}
					}

				} 
				else{
					mensajes.add(new BarMessage("Formulario", Messages.KEY_ENTER));
					mensajes.add(new BarMessage("Formulario", Messages.LLENAR_CAMPOS_RES));
				}
			}
		});

		gridl_activos.addShortcutListener(new ShortcutListener("Key Enter", KeyCode.ENTER, new int[] {}) {
			private static final long serialVersionUID = 1L;

			@Override
			public void handleAction(Object sender, Object target) {

				try {

					binder_revaloriza.commit();
					ActivoInventario activo = new ActivoInventario(Long.parseLong(txtcodigo.getValue().replace(".", "")
							.replace("'", "")), txtnombre_activo.getValue(), new BigDecimal(txtnuevo_valor.getValue()),
							Integer.parseInt(txtnueva_vida_util.getValue()));
					activos_invetariados.add(activo);
					grid_revaloriza.buildGrid(activos_invetariados);
				} catch (CommitException ex) {
					Map<Field<?>, InvalidValueException> invalid_fields = ex.getInvalidFields();
					Iterator<Field<?>> it = invalid_fields.keySet().iterator();
					while (it.hasNext()) {
						Field<?> key = (Field<?>) it.next();
						mensajes.add(new BarMessage(key.getCaption(),
								invalid_fields.get(key).getMessage() == "" ? Messages.EMPTY_MESSAGE : invalid_fields
										.get(key).getMessage()));
					}
				}
				((VRevalorizaA) layout_errores).buildMessages(mensajes);
				mensajes = new ArrayList<BarMessage>();
				txtcodigo.setValue("");
				txtnombre_activo.setValue("");
				txtnuevo_valor.setValue("");
				txtnueva_vida_util.setValue("");
			}
		});

	}

	public void update() {
		binder_revaloriza.clear();
		this.dtf_fecha_doc.setValue(new Date());
		this.dtf_fecha_resol.setValue(new Date());
	}

	public List<BarMessage> getMensajes() {
		return mensajes;
	}

	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}

	public boolean validate() {
		this.mensajes = new ArrayList<BarMessage>();
		if (this.activos_invetariados.size() > 0) {
			return true;
		}
		this.mensajes.add(new BarMessage("Grid Activos Revalorizados", Messages.EMPTY_GRID));
		return false;
	}

	public Movimiento getData() {
		Movimiento result = new Movimiento();
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		java.sql.Date fecha_registro = new java.sql.Date(new Date().getTime());
		result.setId_dependencia(usuario.getId_dependecia());
		result.setId_unidad_organizacional_origen(usuario.getId_unidad_organizacional());
		result.setNro_documento(Long.parseLong(this.txt_no_doc.getValue()));
		result.setFecha_movimiento(fecha_registro);
		result.setFecha_registro(fecha_registro);
		result.setUsuario(usuario.getCi());
		result.setFecha_nro_referencia(new java.sql.Date(dtf_fecha_resol.getValue().getTime()));
		result.setNro_documento_referencia(this.txt_resolucion.getValue());
		result.setObservacion("");
		result.setTipo_movimiento((short) 15);
		for (ActivoInventario row : activos_invetariados) {

			Detalle detalle = new Detalle();
			detalle.setId_activo(row.getCodigo_activo());
			detalle.setId_unidad_organizacional_origen(usuario.getId_unidad_organizacional());
			detalle.setId_dependencia(usuario.getId_dependecia());
			detalle.setObservacion("");
			detalle.setNuevo_valor(row.getNuevo_valor());
			detalle.setNueva_vida_util(row.getNueva_vida_util());
			detalle.setNro_documento(Long.parseLong(this.txt_no_doc.getValue()));
			detalle.setFecha_registro(fecha_registro);
			detalle.setTipo_movimiento((short) 15);
			result.addDetalle(detalle);
		}

		return result;
	}

	/**
	 * Limpia los Campos del Formulario
	 */
	public void clear() {

		this.binder_revaloriza.clear();
		buildId();
		this.grid_revaloriza.clean();
		this.activos_invetariados = new ArrayList<ActivoInventario>();
	}

	/**
	 * Retorna el Grid de Activos Inventariados
	 * 
	 * @return
	 */
	public Grid getGrid() {
		txtcodigo.setValue("");
		txtnombre_activo.setValue("");
		txtnuevo_valor.setValue("");
		txtnueva_vida_util.setValue("");
		return this.grid_revaloriza;

	}

	@Override
	public void textChange(TextChangeEvent event) {

		if (event.getComponent() == txtcodigo) {

			if (event.getComponent().toString().length() > 0) {
				try {
					ActivoGrid activo = activoimpl.getone(Long.parseLong(event.getText()), session.getId_dependecia());
					if (activo != null) {
						txtnombre_activo.setValue(activo.getNombre());
					}
				} catch (NumberFormatException ex) {
				}
			}
		}
	}
}
