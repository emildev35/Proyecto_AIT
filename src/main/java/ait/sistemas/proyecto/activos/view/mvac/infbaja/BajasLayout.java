package ait.sistemas.proyecto.activos.view.mvac.infbaja;

import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.model.ActivoGrid;
import ait.sistemas.proyecto.activos.data.model.Motivo_Baja;
import ait.sistemas.proyecto.activos.data.service.Impl.MotivobajaImpl;

import com.google.gwt.cell.client.EditTextCell;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class BajasLayout extends GridLayout implements ClickListener {

	private static final long serialVersionUID = 1L;
	List<ActivoGrid> activos = new ArrayList<ActivoGrid>();
	final MotivobajaImpl motivobajaimpl = new MotivobajaImpl();

	public BajasLayout() {
		setWidth("100%");
		setColumns(5);
		setRows(1);
		Responsive.makeResponsive(this);
	}

	public void CargarDatos(List<ActivoGrid> activos) {
		setColumnExpandRatio(0, 0.5f);
		setColumnExpandRatio(1, 2);
		setColumnExpandRatio(2, 2);
		setColumnExpandRatio(3, 2);
		setColumnExpandRatio(4, 1);
		int r = this.getRows() - 1;
		setMargin(true);
		setSpacing(true);
		for (ActivoGrid activoGrid : activos) {
			addComponent(new TextField("Id", String.valueOf(activoGrid.getId_activo())), 0, r);
			addComponent(new TextField("Nombre Activo", activoGrid.getNombre()), 1, r);
			addComponent(getcombo(), 2, r);
			addComponent(new TextArea("Observaciones"), 3, r);
			addComponent(new Button("Quitar", this), 4, r);
			getComponent(0, r).setEnabled(false);
			getComponent(1, r).setEnabled(false);

			r++;
			setRows(r + 2);
		}
	}

	public ComboBox getcombo() {
		ComboBox cb_motivos = new ComboBox("Seleccione Motivo");
		cb_motivos.setInputPrompt("Seleccion el Motivo");
		cb_motivos.setNullSelectionAllowed(false);
		for (Motivo_Baja motivo_baja : motivobajaimpl.getall()) {
			cb_motivos.addItem(motivo_baja);
			cb_motivos.setItemCaption(motivo_baja, motivo_baja.getMBA_Descripcion());
		}
		return cb_motivos;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		removeComponent(0, getComponentArea(event.getButton()).getRow1());
		removeComponent(1, getComponentArea(event.getButton()).getRow1());
		removeComponent(2, getComponentArea(event.getButton()).getRow1());
		removeComponent(3, getComponentArea(event.getButton()).getRow1());
		removeComponent(4, getComponentArea(event.getButton()).getRow1());
	}

	public long getIdActivo(int r) {
		return Long.parseLong(((TextField) getComponent(0, r)).getValue());
	}

	public short getMotivo(int r) {
		return ((Motivo_Baja) ((ComboBox) getComponent(2, r)).getValue()).getMBA_Motivo_Baja();
	}

	public String getObservacion(int r) {
		return ((TextArea) getComponent(3, r)).getValue().toString();
	}

}
