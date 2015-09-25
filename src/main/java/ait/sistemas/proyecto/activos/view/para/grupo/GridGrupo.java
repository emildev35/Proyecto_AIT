package ait.sistemas.proyecto.activos.view.para.grupo;

import ait.sistemas.proyecto.activos.data.model.GruposContablesModel;
import ait.sistemas.proyecto.activos.data.service.Impl.GrupoImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Responsive;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;

/**
 * Grid de Grupos Contables
 * @author franzemil
 *
 */
public class GridGrupo extends Grid {
	
	private GrupoImpl grupo_impl = new GrupoImpl();
	private static final long serialVersionUID = 1L;
	private BeanItemContainer<GruposContablesModel> bean_grupo;
	
	public GridGrupo() {
		buildGrid();
	}
	
	public void update() {
		this.removeAllColumns();
		bean_grupo = null;
		buildGrid();
	}
	
	public void buildGrid() {
		bean_grupo = new BeanItemContainer<GruposContablesModel>(GruposContablesModel.class);
		bean_grupo.addAll(grupo_impl.getalls());
		setContainerDataSource(bean_grupo);
		setHeightMode(HeightMode.ROW);
		setHeightByRows(5);
		setWidth("100%");
		
		removeColumn("GRC_Fecha_Registro");
		removeColumn("GRC_Partida_ID");
		
		setColumnOrder("GRC_Grupo_Contable", "GRC_Nombre_Grupo_Contable", "GRC_Partida", "GRC_Vida_Util", "GRC_Coeficiente");
		
		Grid.Column id_grupoColumn = this.getColumn("GRC_Grupo_Contable");
		Grid.Column nombre_grupoColumn = this.getColumn("GRC_Nombre_Grupo_Contable");
		Grid.Column vida_utilColumn = this.getColumn("GRC_Vida_Util");
		Grid.Column coeficienteColumn = this.getColumn("GRC_Coeficiente");
		Grid.Column id_partidaColumn = this.getColumn("GRC_Partida");
		
		id_grupoColumn.setHeaderCaption("Codigo de Grupo").setExpandRatio(1);
		nombre_grupoColumn.setHeaderCaption("Nombre del Grupo Contable").setExpandRatio(4);
		vida_utilColumn.setHeaderCaption("Vida Util").setExpandRatio(4);
		coeficienteColumn.setHeaderCaption("Coeficiente").setExpandRatio(4);
		id_partidaColumn.setHeaderCaption("Partida Presupuestaria").setExpandRatio(4);
		
		sort("GRC_Grupo_Contable", SortDirection.ASCENDING);
		Responsive.makeResponsive(this);
	}
}
