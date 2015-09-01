package ait.sistemas.proyecto.activos.view.reva.actualiza;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.Actualizacion;
import ait.sistemas.proyecto.activos.component.model.TipoCambio;
import ait.sistemas.proyecto.activos.data.model.Cierre_Gestion;
import ait.sistemas.proyecto.activos.data.service.Impl.CierreGestionImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoCambioImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.Responsive;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class FormActualiza extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	public TextField txt_ufvi;
	public TextField txt_ufvf;
	public DateField dtf_fecha;
	public DateField dtf_fecha_ultima_depre;

	private List<BarMessage> mensajes;

	private CierreGestionImpl cierre_gestion_impl = new CierreGestionImpl();
	private TipoCambioImpl tipocambio_impl = new TipoCambioImpl();
	private PropertysetItem pitm_Actualizacion = new PropertysetItem();
	private FieldGroup binder_Actualizacion;

	public FormActualiza() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.dtf_fecha = new DateField("Fecha:");
		this.txt_ufvf = new TextField("Tipo de Cambio UFV");
		this.dtf_fecha_ultima_depre = new DateField("Fecha Ultima Depreciacion:");
		this.txt_ufvi = new TextField("Tipo de Cambio UFV");
		this.mensajes = new ArrayList<BarMessage>();

		pitm_Actualizacion.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));
		pitm_Actualizacion.addItemProperty("ufvf", new ObjectProperty<String>(""));
		pitm_Actualizacion.addItemProperty("fecha_ultima_depre", new ObjectProperty<Date>(new Date()));
		pitm_Actualizacion.addItemProperty("ufvi", new ObjectProperty<String>(""));

		this.binder_Actualizacion = new FieldGroup(this.pitm_Actualizacion);

		binder_Actualizacion.bind(this.dtf_fecha, "fecha");
		binder_Actualizacion.bind(this.txt_ufvf, "ufvf");
		binder_Actualizacion.bind(this.dtf_fecha_ultima_depre, "fecha_ultima_depre");
		binder_Actualizacion.bind(this.txt_ufvi, "ufvi");
		binder_Actualizacion.clear();

		this.txt_ufvf.setEnabled(false);
		this.dtf_fecha_ultima_depre.setEnabled(false);
		this.txt_ufvi.setEnabled(false);
		this.dtf_fecha.setEnabled(false);
//		this.dtf_fecha.addValueChangeListener(this);

		txt_ufvf.setWidth("90%");
		dtf_fecha.setWidth("90%");
		txt_ufvi.setWidth("90%");
		dtf_fecha_ultima_depre.setWidth("90%");

		setData((Cierre_Gestion) cierre_gestion_impl.getall());
		buildContent();
		Responsive.makeResponsive(this);
	}

	private void buildContent() {

		Panel pn_valor_actual = new Panel(
				"Registre la Fecha a la cual desea Actualizar y Depreciar el Valor de los Activos Fijos");
		Panel pn_ultimo_valor = new Panel("Datos de la ultima Actualizacion y Depreciacion");

		GridLayout grid_valor = new GridLayout(2, 1);
		grid_valor.setSizeFull();
		grid_valor.setMargin(true);
		grid_valor.addComponent(this.dtf_fecha, 0, 0);
		grid_valor.addComponent(this.txt_ufvf, 1, 0);
		pn_valor_actual.setContent(grid_valor);
		this.addComponent(pn_valor_actual, 0, 0, 1, 0);

		GridLayout grid_ultimo = new GridLayout(2, 1);
		grid_ultimo.setSizeFull();
		grid_ultimo.setMargin(true);
		grid_ultimo.addComponent(this.dtf_fecha_ultima_depre, 0, 0);
		grid_ultimo.addComponent(this.txt_ufvi, 1, 0);
		pn_ultimo_valor.setContent(grid_ultimo);
		this.addComponent(pn_ultimo_valor, 0, 1, 1, 1);

	}

	public void update() {
		this.txt_ufvf.clear();
		this.dtf_fecha.clear();
	}

	public List<BarMessage> getMensajes() {
		return mensajes;
	}

	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}

	public boolean validate() {
		if (dtf_fecha_ultima_depre.getValue().getTime() < dtf_fecha.getValue().getTime()) {

			try {
				this.binder_Actualizacion.commit();
				this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
				return true;
			} catch (CommitException e) {
				try {
					this.dtf_fecha.validate();
				} catch (EmptyValueException ex) {
					this.mensajes.add(new BarMessage(dtf_fecha.getCaption(), Messages.EMPTY_MESSAGE));
				} catch (InvalidValueException ex) {
					this.mensajes.add(new BarMessage(dtf_fecha.getCaption(), ex.getMessage()));
				}

				return false;
			}
		}else{
			this.mensajes.add(new BarMessage(dtf_fecha.getCaption(), Messages.INVALIT_DATE));
			return false;
		}
	}

	public Actualizacion getData() {
		Actualizacion resul = new Actualizacion();
		resul.setUfvf(Double.parseDouble(this.txt_ufvf.getValue().replace(",", ".")));
		resul.setFecha(new java.sql.Date(this.dtf_fecha.getValue().getTime()));
		return resul;
	}

	public void setData(Cierre_Gestion data) {
		
		this.txt_ufvi.setValue(String.valueOf(data.getCGE_Tipo_Cambio_UFV()));
		this.dtf_fecha_ultima_depre.setValue(data.getCGE_Fecha_Cierre_Gestion());
		this.dtf_fecha.setValue(data.getCGE_Fecha());
		TipoCambio tipo_cambio = this.tipocambio_impl.getTipoCambioUFV(data.getCGE_Fecha());

		this.txt_ufvf.setValue(tipo_cambio.getTipo_cambio().toString().replace(".", ","));
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
//		if (this.dtf_fecha.getValue() != null && event.getProperty().getValue() == this.dtf_fecha.getValue()) {
//			List<TipoCambio> tipo_cambio = this.tipocambio_impl.getTipoCambioUFV(new java.sql.Date(this.dtf_fecha
//					.getValue().getTime()));
//
//			this.txt_ufvf.setValue(tipo_cambio.get(0).getTipo_cambio().toString().replace(".", ","));
//		}
	}
}
