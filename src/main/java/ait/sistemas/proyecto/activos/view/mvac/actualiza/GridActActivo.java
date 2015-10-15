package ait.sistemas.proyecto.activos.view.mvac.actualiza;

import ait.sistemas.proyecto.activos.data.model.ActivosModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ActivoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

/*
 * Grid de Activos que cuenta con las Columnas 
 * -----------------------------------------------------------
 * Grupo Contable | Auxiliar Contable | Codigo | Nombre Activo
 * -----------------------------------------------------------
 */
public class GridActActivo extends Grid {
	
	private static final long serialVersionUID = 1L;
	private final ActivoImpl activoimpl = new ActivoImpl();
	private BeanItemContainer<ActivosModel> bean_activos;
	
	public GridActActivo() {
		setWidth("100%");
		setResponsive(true);
		this.bean_activos = new BeanItemContainer<ActivosModel>(ActivosModel.class);
		setContainerDataSource(bean_activos);
		
		removeColumn("ACT_Nombre_Empleado");
		removeColumn("ACT_APaterno_Empleado");
		removeColumn("ACT_AMaterno_Empleado");
		removeColumn("ACT_Inmueble");
		removeColumn("ACT_NIT_Proveedor");
		removeColumn("ACT_Fecha_Asignacion");
		removeColumn("ACT_Fecha_Baja");
		removeColumn("ACT_Fecha_Comodato");
		removeColumn("ACT_Fecha_Compra");
		removeColumn("ACT_Fecha_Incorporacion");
		removeColumn("ACT_Fecha_Registro");
		removeColumn("ACT_Fecha_Vencimiento_Garantia");
		removeColumn("ACT_Fecha_Vencimiento_Mantenimiento");
		removeColumn("ACT_Fecha_Vencimiento_Seguro");
		removeColumn("ACT_Fuente_Financiamiento_ID");
		removeColumn("ACT_Fuente_Financiamiento");
		removeColumn("ACT_Grupo_Contable_ID");
		removeColumn("ACT_Tipo_Cambio_Dolar");
		removeColumn("ACT_Tipo_Cambio_UFV");
		removeColumn("ACT_Actualizacion_Acumulada_Gestion_Anterior");
		removeColumn("ACT_Actualizacion_Acumulada");
		removeColumn("ACT_Depresiacion_Acumulada");
		removeColumn("ACT_Depreciacion_Acumulada_Gestion_Anterior");
		removeColumn("ACT_DAA");
		removeColumn("ACT_CA");
		removeColumn("ACT_Auxiliar_Contable_ID");
		removeColumn("ACT_CI_Empleado_Asignado");
		removeColumn("ACT_Ubicacion_Fisica_Activo_ID");
		removeColumn("ACT_Ubicacion_Fisica_Activo");
		removeColumn("ACT_Ubicacion_Imagen");
		removeColumn("ACT_Valor_Compra");
		removeColumn("ACT_Valor_Neto");
		removeColumn("ACT_Vida_Util");
		removeColumn("ACT_Dependencia_Codigo_Activo");
		removeColumn("ACT_Dependencia_ID");
		removeColumn("ACT_Dependencia");
		removeColumn("ACT_No_Comprobante_Gasto");
		removeColumn("ACT_No_Contrato_Mantenimiento");
		removeColumn("ACT_No_Folio_Real");
		removeColumn("ACT_No_Garantia");
		removeColumn("ACT_No_Informe_Baja");
		removeColumn("ACT_No_Poliza_Seguro");
		removeColumn("ACT_No_RUAT");
		removeColumn("ACT_No_Serie");
		removeColumn("ACT_Tipo_Activo");
		removeColumn("ACT_Marca");
		removeColumn("ACT_Organismo_Financiador_ID");
		removeColumn("ACT_Organismo_Financiador");
		removeColumn("ACT_No_Acta_Entrega");
		removeColumn("ACT_Partidas_Presupuestarias");
		removeColumn("ACT_Motivo_Baja");
		removeColumn("ACT_No_Resolucion_Baja");
		removeColumn("ACT_Nombre_Proveedor");
		removeColumn("ACT_Vida_Residual");
		removeColumn("ACT_Actualizacion_Gestion_Actual");
		removeColumn("ACT_Depreciacion_Gestion_Actual");
		removeColumn("ACT_Fecha_Ultima_Depreciacion");
		removeColumn("ACT_Fecha_Ultima_Revalorizacion");
		removeColumn("ACT_Grupo_Contable");
		removeColumn("ACT_Auxiliar_Contable");
		builGrid("0", "0");
		
		HeaderRow filterRow = this.appendHeaderRow();
		
		// Set up a filter for all columns
		for (final Object pid : this.getContainerDataSource().getContainerPropertyIds()) {
			HeaderCell cell = filterRow.getCell(pid);
			TextField filterField = new TextField();
			filterField.setSizeFull();
			try {
				cell.setComponent(filterField);
				filterField.addTextChangeListener(new TextChangeListener() {
					
					private static final long serialVersionUID = 1L;
					
					@Override
					public void textChange(TextChangeEvent event) {
						bean_activos.removeContainerFilters(pid);
						
						if (!event.getText().isEmpty())
							bean_activos.addContainerFilter(new SimpleStringFilter(pid, event.getText(), true, false));
					}
				});
			} catch (Exception ex) {
			}
		}
		
	}
	
	/**
	 * Vacia el Grid
	 */
	public void update() {
		bean_activos = null;
		builGrid("0", "0");
	}
	
	/**
	 * Modifica los objetos del Grid tomando como parametro el codigo del
	 * auxliar contable
	 * 
	 * @param auxiliar
	 */
	public void builGrid(String auxiliar, String grupoContable) {
		this.bean_activos = new BeanItemContainer<ActivosModel>(ActivosModel.class, activoimpl.activos_by_auxiliar(auxiliar,
				grupoContable));
		
		setContainerDataSource(bean_activos);
		
		getColumn("ACT_Codigo_Activo").setHeaderCaption("CODIGO").setExpandRatio(1);
		getColumn("ACT_Nombre_Activo").setHeaderCaption("NOMBRE ACTIVO").setExpandRatio(7);
		
		setColumnOrder("ACT_Codigo_Activo", "ACT_Nombre_Activo");
		// Create a header row to hold column filters
		
	}
	
}
