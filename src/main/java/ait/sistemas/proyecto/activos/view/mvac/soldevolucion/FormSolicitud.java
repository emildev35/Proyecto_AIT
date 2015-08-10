package ait.sistemas.proyecto.activos.view.mvac.soldevolucion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.component.model.Asignacion;
import ait.sistemas.proyecto.activos.component.model.Detalle;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;
import ait.sistemas.proyecto.activos.view.mvac.solactivo.GridSolicitud;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.seguridad.component.model.SessionModel;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class FormSolicitud extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	private TextField txt_id_solicitud = new TextField("Id. Solicitud");
	public DateField dtf_fecha_soliciud = new DateField("Fecha Solicitud");
	
	private List<BarMessage> mensajes;
	
	private PropertysetItem pitm_solicitud = new PropertysetItem();
	private FieldGroup binder_solicitud;
	private final MovimientoImpl movimiento_impl = new MovimientoImpl();
	private GridSolicitud grid_solicitud = new GridSolicitud();
	public FormSolicitud() {
		
		super(6, 2);
		setSpacing(true);
		setWidth("100%");
		
		pitm_solicitud.addItemProperty("id_solicitud", new ObjectProperty<Integer>(0));
		pitm_solicitud.addItemProperty("fecha_solicitud", new ObjectProperty<Date>(new Date()));
		
		this.binder_solicitud = new FieldGroup(this.pitm_solicitud);
		
		binder_solicitud.bind(this.txt_id_solicitud, "id_solicitud");
		binder_solicitud.bind(this.dtf_fecha_soliciud, "fecha_solicitud");
		binder_solicitud.clear();
		
		this.txt_id_solicitud.setEnabled(false);
		this.txt_id_solicitud.addValidator(new NullValidator("No Nulo", false));
		
		this.dtf_fecha_soliciud.setEnabled(false);
		this.dtf_fecha_soliciud.addValidator(new NullValidator("No Nulo", false));
		
		
		txt_id_solicitud.setWidth("90%");
		dtf_fecha_soliciud.setWidth("90%");
		
		updateId();
		buildContent();
		buildId();
		Responsive.makeResponsive(this);
	}
	
	private void buildId() {
		this.txt_id_solicitud.setValue(String.valueOf(movimiento_impl.getId()));
		this.dtf_fecha_soliciud.setValue(new Date());
	}
	
	private void buildContent() {
		
		Panel pn_solicitud = new Panel("Solicitud de Devolucion de Activos");
		
		GridLayout gridl_solicitud = new GridLayout(2, 1);
		gridl_solicitud.setSizeFull();
		gridl_solicitud.setMargin(true);
		gridl_solicitud.addComponent(this.txt_id_solicitud, 0, 0);
		gridl_solicitud.addComponent(this.dtf_fecha_soliciud, 1, 0);
		pn_solicitud.setContent(gridl_solicitud);
		
		this.addComponent(pn_solicitud, 4, 0, 5, 0);
		
		GridLayout gridl_activos = new GridLayout(2, 1);
		gridl_activos.setSizeFull();
		gridl_activos.setMargin(true);
		
	}
	
	public void update() {
		binder_solicitud.clear();
	}
	
	public void updateId() {
		this.txt_id_solicitud.setValue(movimiento_impl.getId() + "");
	}
	
	public List<BarMessage> getMensajes() {
		return mensajes;
	}
	
	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}
	
	public boolean validate() {
		
		try {
			this.binder_solicitud.commit();
			this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
			return true;
		} catch (CommitException e) {
			
			return false;
		}
	}
	public Asignacion getData() {
		Asignacion result = new Asignacion();
		SessionModel usuario = (SessionModel) UI.getCurrent().getSession().getAttribute("user");
		java.sql.Date fecha_registro =new java.sql.Date(new Date().getTime());
		
		result.setId_dependencia(usuario.getId_dependecia());
		result.setId_unidad_organizacional_origen(usuario.getId_unidad_organizacional());
		result.setNro_documento(Long.parseLong(this.txt_id_solicitud.getValue()));
		result.setFecha_movimiento(fecha_registro);
		result.setFecha_registro(fecha_registro);
		result.setUsuario(usuario.getCi());
		result.setObservacion("");
		for (Object row : grid_solicitud.getSelectedRows()) {
			ActivoGrid activo = (ActivoGrid) row;
			Detalle detalle = new Detalle();
			detalle.setId_activo(activo.getId_activo());
			detalle.setId_unidad_organizacional_origen(usuario.getId_unidad_organizacional());
			detalle.setId_dependencia(usuario.getId_dependecia());
			detalle.setObservacion("");
			detalle.setNro_documento(Long.parseLong(this.txt_id_solicitud.getValue()));
			detalle.setFecha_registro(fecha_registro);
			result.addDetalle(detalle);
		}
		return result;
		}
	
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
	public void clear() {
		
		this.binder_solicitud.clear();
		buildId();
		this.grid_solicitud = new GridSolicitud();
		
	}
}
