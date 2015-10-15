package ait.sistemas.proyecto.activos.view.rrhh.autorizado;

import ait.sistemas.proyecto.activos.data.model.TipoAutorizacionModel;
import ait.sistemas.proyecto.activos.data.service.Impl.TipoAutorizacionImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridAutorizado extends Grid {
	private TipoAutorizacionImpl tipo_autorizacion_impl = new TipoAutorizacionImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<TipoAutorizacionModel> bean_tipo_autorizacion;
	
	public GridAutorizado() {
		buildGrid();
	}
	
	public void update() {
		this.removeAllColumns();
		bean_tipo_autorizacion = null;
		buildGrid();
	}
	
	public void buildGrid() {
		bean_tipo_autorizacion = new BeanItemContainer<TipoAutorizacionModel>(TipoAutorizacionModel.class,
				this.tipo_autorizacion_impl.getallGrid());
		setContainerDataSource(bean_tipo_autorizacion);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(10);
		
		removeColumn("dependencia_id");
		removeColumn("dependencia_id_transferencia");
		removeColumn("ci");
		removeColumn("nivel_autorizacion_id");
		removeColumn("tipo_movimiento_id");
		removeColumn("usuario_id");
		removeColumn("fecha_registro");
		removeColumn("unidadOrganizacionalId");
		
		setWidth("100%");
		
		setColumnOrder(
				"dependencia",
				"unidadOrganizacional",
				"tipo_movimiento",
				"orden",
				"servidor_publico",
				"dependencia_transferencia"
				);
		
		getColumn("tipo_movimiento").setHeaderCaption("Tipos de Movimiento").setExpandRatio(5);
		getColumn("nivel_autorizacion").setHeaderCaption("Nivel de Autorizacion").setExpandRatio(4);
		getColumn("dependencia").setHeaderCaption("Dependencia").setExpandRatio(1);
		getColumn("orden").setHeaderCaption("Orden").setExpandRatio(1);
		getColumn("servidor_publico").setHeaderCaption("Servidor Publico").setExpandRatio(10);
		getColumn("dependencia_transferencia").setHeaderCaption("Dependencia Transferencia").setExpandRatio(1);
		

		HeaderRow groupRender = this.prependHeaderRow();
		HeaderCell datosMovimiento = groupRender.join(
				groupRender.getCell("dependencia"),
				groupRender.getCell("unidadOrganizacional"),
				groupRender.getCell("tipo_movimiento")

		);
		HeaderCell datosAutoriza = groupRender.join(
				groupRender.getCell("servidor_publico"),
				groupRender.getCell("unidadFuncionario"),
				groupRender.getCell("dependenciaFuncionario")
		);
		datosMovimiento.setText("ORIGEN DEL MOVIMIENTO");
		datosAutoriza.setText("DATOS DEL SERVIDOR PUBLICO QUE AUTORIZA");
				

		Responsive.makeResponsive(this);
	}
}
