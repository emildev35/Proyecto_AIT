package ait.sistemas.proyecto.activos.view.reva.cierrege;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.TipoCambio;
import ait.sistemas.proyecto.activos.data.model.Cierre_Gestion;
import ait.sistemas.proyecto.activos.data.service.Impl.CierreGestionImpl;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoCambioImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;
import ait.sistemas.proyecto.common.theme.AitTheme;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

public class FormCierreGe extends GridLayout implements ValueChangeListener {
	private static final long serialVersionUID = 1L;
	public TextField txt_ufvi;
	public DateField dtf_fecha;

	private List<BarMessage> mensajes;

	private CierreGestionImpl cierre_gestion_impl = new CierreGestionImpl();
	private TipoCambioImpl tipocambio_impl = new TipoCambioImpl();
	private PropertysetItem pitm_Actualizacion = new PropertysetItem();
	private FieldGroup binder_Actualizacion;

	public FormCierreGe() {

		super.setColumns(4);
		super.setRows(3);
		setSpacing(true);
		setMargin(true);
		setWidth("100%");

		this.dtf_fecha = new DateField("Fecha:");
		this.txt_ufvi = new TextField("Tipo de Cambio UFV");
		this.mensajes = new ArrayList<BarMessage>();

		pitm_Actualizacion.addItemProperty("fecha", new ObjectProperty<Date>(new Date()));
		pitm_Actualizacion.addItemProperty("ufvi", new ObjectProperty<String>(""));

		this.binder_Actualizacion = new FieldGroup(this.pitm_Actualizacion);

		binder_Actualizacion.bind(this.dtf_fecha, "fecha");
		binder_Actualizacion.bind(this.txt_ufvi, "ufvi");
		binder_Actualizacion.clear();

		this.txt_ufvi.setEnabled(false);
		this.dtf_fecha.setEnabled(false);
//		this.dtf_fecha.addValueChangeListener(this);

		dtf_fecha.setWidth("90%");
		txt_ufvi.setWidth("90%");

		setData((Cierre_Gestion) cierre_gestion_impl.getall());
		buildContent();
		Responsive.makeResponsive(this);
	}

	private void buildContent() {

		Panel pn_valor_actual = new Panel(
				"Fecha de Cierre de Gestion");
		
		pn_valor_actual.setStyleName(AitTheme.PANEL_PRINT);
		pn_valor_actual.setIcon(FontAwesome.PRINT);
		
		
		GridLayout grid_valor = new GridLayout(2, 1);
		grid_valor.setSizeFull();
		grid_valor.setMargin(true);
		grid_valor.addComponent(this.dtf_fecha, 0, 0);
		grid_valor.addComponent(this.txt_ufvi, 1, 0);
		pn_valor_actual.setContent(grid_valor);
		this.addComponent(pn_valor_actual, 0, 0, 1, 0);

	}

	public void update() {
		binder_Actualizacion.clear();
	}

	public List<BarMessage> getMensajes() {
		return mensajes;
	}

	public void clearMessages() {
		this.mensajes = new ArrayList<BarMessage>();
	}

	public boolean validate() {
		if (new Date().getTime() >= dtf_fecha.getValue().getTime()) {
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

	public Cierre_Gestion getData() {
		Cierre_Gestion resul = new Cierre_Gestion();
		resul.setCGE_Tipo_Cambio_UFV(new BigDecimal(this.txt_ufvi.getValue().replace(",", ".")));
		resul.setCGE_Fecha_Cierre_Gestion(new java.sql.Date(this.dtf_fecha.getValue().getTime()));
		return resul;
	}

	public void setData(Cierre_Gestion data) {
		
		this.dtf_fecha.setValue(data.getCGE_Fecha());
		
		TipoCambio tipo_cambio = this.tipocambio_impl.getTipoCambioUFV(data.getCGE_Fecha());

		this.txt_ufvi.setValue(tipo_cambio.getTipo_cambio().toString().replace(".", ","));
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
	}
}
