package ait.sistemas.proyecto.activos.view.mant.proveedor;

import ait.sistemas.proyecto.activos.data.model.ProveedoresModel;
import ait.sistemas.proyecto.activos.data.service.Impl.ProveedorImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

public class GridProveedor extends Grid{

	private ProveedorImpl proveedor_impl = new ProveedorImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<ProveedoresModel> bean_proveedor;

	public GridProveedor() {
		buildGrid();
	}
	public void update() {
		this.removeAllColumns();
		bean_proveedor = null;
		buildGrid();
	}
	public void buildGrid(){
		bean_proveedor = new BeanItemContainer<ProveedoresModel>(ProveedoresModel.class);
		bean_proveedor.addAll(proveedor_impl.getalls());
		setContainerDataSource(bean_proveedor);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		
		removeColumn("PRV_Dependencia_ID");
		removeColumn("PRV_Dependencia");
		removeColumn("PRV_Ciudad_ID");
		removeColumn("PRV_Fecha_Registro");	
		removeColumn("PRV_Dependencia_NIT");	
		
		setWidth("100%");

		setColumnOrder("PRV_NIT", "PRV_Nombre","PRV_Ciudad",
				"PRV_Domicilio","PRV_Nombre_Contacto","PRV_Telefono","PRV_Celular_Contacto");
		
		Grid.Column NITColumn = this.getColumn("PRV_NIT");
		Grid.Column nombre_proveedorColumn = this.getColumn("PRV_Nombre");
		Grid.Column id_ciudadColumn = this.getColumn("PRV_Ciudad");
		Grid.Column domicilioColumn = this.getColumn("PRV_Domicilio");
		Grid.Column nombre_contactoColumn = this.getColumn("PRV_Nombre_Contacto");
		Grid.Column telefonoColumn = this.getColumn("PRV_Telefono");
		Grid.Column celularColumn = this.getColumn("PRV_Celular_Contacto");

		NITColumn.setHeaderCaption("NIT");
		nombre_proveedorColumn.setHeaderCaption("Nombre Proveedor");
		id_ciudadColumn.setHeaderCaption("Ciudad");
		domicilioColumn.setHeaderCaption("Domicilio");
		nombre_contactoColumn.setHeaderCaption("Nombre Contacto");
		telefonoColumn.setHeaderCaption("Telefono");
		celularColumn.setHeaderCaption("Celular");
		Responsive.makeResponsive(this);
	}
}

