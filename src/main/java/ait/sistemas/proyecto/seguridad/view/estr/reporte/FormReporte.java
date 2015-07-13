package ait.sistemas.proyecto.seguridad.view.estr.reporte;

import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;
import ait.sistemas.proyecto.seguridad.data.service.Impl.MenuImpl;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;

public class FormReporte extends GridLayout{
	
	
	private static final long serialVersionUID = 1L;

	
	public ComboBox cbSusSistema;
	
	
	final private MenuImpl menuimpl = new MenuImpl();
	
	final PropertysetItem pitmSubSistema = new PropertysetItem();
	private FieldGroup binderSubMenu;
	
	
	public FormReporte() {
		setColumns(1);
		setRows(1);
		setMargin(true);
		setSpacing(true);
		
		this.cbSusSistema = new ComboBox("Elija Sub Sistema");
		
		pitmSubSistema.addItemProperty("subsistema", new ObjectProperty<Long>((long)1));
		
		this.binderSubMenu = new FieldGroup(pitmSubSistema);
		
		this.binderSubMenu.bind(this.cbSusSistema, "subsistema");
		
		fillSubsistema();
		buildContent();
	}
	
	public void init(){
		update();
	}
	/**
	 * Actualizacion de los Campos
	 */
	public void update(){
		this.binderSubMenu.clear();
		this.cbSusSistema.setValue((long)1);

	}
	/**
	 * Llenado del Combo Box Subsistema
	 */
	private void fillSubsistema(){
		cbSusSistema.setNullSelectionAllowed(false);
		cbSusSistema.setInputPrompt("Seleccione un SubSistema");
		for (Arbol_menus subSistema : menuimpl.getallSubsistema())
		{
			cbSusSistema.addItem(subSistema.getAME_Id_Identificador());
			cbSusSistema.setItemCaption(subSistema.getAME_Id_Identificador(), subSistema.getAME_Nombre());
		}
	}
	private void buildContent() {

		addComponent(this.cbSusSistema, 0,0);

	}
}
