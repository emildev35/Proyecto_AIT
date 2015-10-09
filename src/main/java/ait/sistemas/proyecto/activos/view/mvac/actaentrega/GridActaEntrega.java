package ait.sistemas.proyecto.activos.view.mvac.actaentrega;

import java.util.Locale;

import ait.sistemas.proyecto.activos.component.model.Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.MovimientoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.renderers.DateRenderer;

public class GridActaEntrega extends Grid{

	private MovimientoImpl movimiento_impl = new MovimientoImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<Movimiento> bean_Movimiento;

	public GridActaEntrega() {
		bean_Movimiento = new BeanItemContainer<Movimiento>(Movimiento.class);
		setContainerDataSource(bean_Movimiento);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(9);
		setWidth("100%");
		removeColumn("detalles");
		removeColumn("id_dependencia");
		removeColumn("id_unidad_organizacional_origen");
		removeColumn("fecha_registro");
		removeColumn("nro_documento_referencia");
		removeColumn("fecha_nro_referencia");
		removeColumn("observacion");
		removeColumn("tipo_movimiento");
		removeColumn("usuario");
		removeColumn("no_acta");
		removeColumn("fecha_acta");
		removeColumn("id_estado_soporte");
		removeColumn("id_subsistema");
		removeColumn("tipo_soporte");
		removeColumn("tipo_movimiento_referencia");
		removeColumn("dependencia");
		removeColumn ("dependencia_destino");
		removeColumn ("id_dependencia_destino");
		removeColumn ("tipo_movimiento_nuevo");
		removeColumn ("solicitante");
		
		setColumnOrder("fecha_movimiento","nro_documento");
		getColumn("fecha_movimiento").setHeaderCaption("Fecha");
		getColumn("nro_documento").setHeaderCaption("No Acta");
		
	}
	public void update(String ci_funcionario) {
		this.removeAllColumns();
		bean_Movimiento = null;
		buildGrid(ci_funcionario);
	}
	public void buildGrid(String ci_funcionario){
		bean_Movimiento = new BeanItemContainer<Movimiento>(Movimiento.class);
		bean_Movimiento.addAll(movimiento_impl.getActaEbyFuncionario(ci_funcionario, (short)2));
		setContainerDataSource(bean_Movimiento);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(8);
		setWidth("100%");
		
		
		removeColumn("detalles");
		removeColumn("id_dependencia");
		removeColumn("id_unidad_organizacional_origen");
		removeColumn("fecha_registro");
		removeColumn("nro_documento_referencia");
		removeColumn("fecha_nro_referencia");
		removeColumn("observacion");
		removeColumn("tipo_movimiento");
		removeColumn("usuario");
		removeColumn("no_acta");
		removeColumn("fecha_acta");
		removeColumn("id_estado_soporte");
		removeColumn("id_subsistema");
		removeColumn("tipo_soporte");
		removeColumn("tipo_movimiento_referencia");
		removeColumn("dependencia");
		removeColumn ("dependencia_destino");
		removeColumn ("id_dependencia_destino");
		removeColumn ("tipo_movimiento_nuevo");
		removeColumn ("solicitante");
		
		setColumnOrder("fecha_movimiento","nro_documento");
//		getColumn("nro_documento").setHeaderCaption("Fecha");
//		getColumn("fecha_movimiento").setHeaderCaption("No Acta");
		
		Grid.Column fechaColumn = this.getColumn("fecha_movimiento");
		Grid.Column no_solicitudColumn = this.getColumn("nro_documento");

		fechaColumn.setHeaderCaption("Fecha");
		fechaColumn.setRenderer(new DateRenderer("%1$te de %1$tB,  %1$tY",
				new Locale("es", "BO")));
		no_solicitudColumn.setHeaderCaption("No Acta");
		
		Responsive.makeResponsive(this);
	}
}

