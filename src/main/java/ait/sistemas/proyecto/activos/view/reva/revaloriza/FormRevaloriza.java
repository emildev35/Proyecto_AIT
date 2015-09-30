package ait.sistemas.proyecto.activos.view.reva.revaloriza;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormRevaloriza extends GridLayout  {
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
	
	private  MovimientoImpl movimientoimpl = new MovimientoImpl();
	private GridRevaloriza grid_solicitud = new GridRevaloriza();
	
	public FormRevaloriza() {
		
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
//		this.txt_no_doc.addValidator(new NullValidator("No Nulo", false));
		this.txt_resolucion.setRequired(true);
		this.txt_resolucion.addValidator(new NullValidator("No Nulo", false));
		
		this.dtf_fecha_resol.setRequired(true);
//		this.dtf_fecha_doc.addValidator(new NullValidator("No Nulo", false));
		
		this.txtcodigo.setRequired(true);
		this.txtcodigo.addValidator(new NullValidator("No Nulo", false));
		this.txtnombre_activo.setRequired(true);
		this.txtnombre_activo.addValidator(new NullValidator("No Nulo", false));
		this.txtnuevo_valor.setRequired(true);
		this.txtnuevo_valor.addValidator(new NullValidator("No Nulo", false));
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
		
		buildId();
		buildContent();
		Responsive.makeResponsive(this);
	}
	
	private void buildId() {
		this.txt_no_doc.setValue(String.valueOf(movimientoimpl.getId((short)15)));
		this.dtf_fecha_doc.setValue(new Date());
		this.dtf_fecha_resol.setValue(new Date());
	}
	
	private void buildContent() {
		
		Panel pn_doc = new Panel("Documento de Revalorizaion");
//		Panel pn_resol = new Panel("Resolucion de Revalorizacion");
		Panel pn_registro = new Panel("Registre los datos del Activo Fijo Revalorizado");
		
		GridLayout gridl_doc = new GridLayout(2, 1);
		gridl_doc.setSizeFull();
		// gridl_solicitud.setMargin(true);
		gridl_doc.addComponent(this.txt_no_doc, 0, 0);
		gridl_doc.addComponent(this.dtf_fecha_doc, 1, 0);
		pn_doc.setContent(gridl_doc);
		
		this.addComponent(pn_doc, 4, 0, 5, 0);
		
		GridLayout gridl_activos = new GridLayout(2, 1);
		gridl_activos.setSizeFull();
		gridl_activos.setMargin(true);
		gridl_activos.addComponent(this.txtcodigo, 0, 0);
		gridl_activos.addComponent(this.txtnombre_activo, 1, 0);
		pn_registro.setContent(gridl_activos);
		
		this.addComponent(pn_registro, 0, 1, 5, 1);
		
	}
	
	public void update() {
		binder_revaloriza.clear();
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			this.binder_revaloriza.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			return false;
		}
	}
	
	public Movimiento getData() {
		Movimiento result = new Movimiento();
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		java.sql.Date fecha_registro =new java.sql.Date(new Date().getTime());
		
		result.setId_dependencia(usuario.getId_dependecia());
		result.setId_unidad_organizacional_origen(usuario.getId_unidad_organizacional());
		result.setNro_documento(Long.parseLong(this.txt_no_doc.getValue()));
		result.setFecha_movimiento(fecha_registro);
		result.setFecha_registro(fecha_registro);
		result.setUsuario(usuario.getCi());
		result.setObservacion("");
		result.setTipo_movimiento((short)1);
		for (Object row : grid_solicitud.getSelectedRows()) {
			ActivoGrid activo = (ActivoGrid) row;
			
			Detalle detalle = new Detalle();
			detalle.setId_activo(activo.getId_activo());
			detalle.setId_unidad_organizacional_origen(usuario.getId_unidad_organizacional());
			detalle.setId_dependencia(usuario.getId_dependecia());
			detalle.setObservacion("");
			detalle.setNro_documento(Long.parseLong(this.txt_no_doc.getValue()));
			detalle.setFecha_registro(fecha_registro);
			detalle.setTipo_movimiento((short)1);
			result.addDetalle(detalle);
		}
		
		return result;
	}
	
	public Component getgrid_solicitud() {
		return this.grid_solicitud;
	}

	public void clear() {
	
		this.binder_revaloriza.clear();
		buildId();
		this.grid_solicitud = new GridRevaloriza();
		
	}
}
