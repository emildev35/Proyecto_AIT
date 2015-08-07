package ait.sistemas.proyecto.activos.view.mvac.ingreso;

import ait.sistemas.proyecto.activos.view.mvac.ingreso.form.FormCaracteriticas;
import ait.sistemas.proyecto.activos.view.mvac.ingreso.form.FormComponentes;
import ait.sistemas.proyecto.activos.view.mvac.ingreso.form.FormDatosGenerales;
import ait.sistemas.proyecto.activos.view.mvac.ingreso.form.FormDocumentos;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@CDIView(value=VActivoA.ID)
public class VActivoA extends VerticalLayout implements View{

	private static final long serialVersionUID = 1L;
	public static final String ID = "/mvac/ingreso/a";
	
	private FormDatosGenerales frm_datos_generales = new FormDatosGenerales(this);
	private FormCaracteriticas frm_caracteristicas = new FormCaracteriticas(this);
	private FormComponentes frm_componentes = new FormComponentes(this);
	private FormDocumentos frm_documentos = new FormDocumentos(this);
	public TabSheet tbs_form;
	
	public VActivoA() {
		setWidth("100%");
		addComponent(buildFormContent());
	}
	
	private Component buildFormContent() {
		tbs_form = new TabSheet();
		
		VerticalLayout vl_generales = new VerticalLayout();
		vl_generales.addComponents(this.frm_datos_generales, this.frm_datos_generales.buildButtonBar());
		tbs_form.addTab(vl_generales, "Datos Generales");
		
		VerticalLayout vl_caracteristicas = new VerticalLayout();
		vl_caracteristicas.addComponents(this.frm_caracteristicas, this.frm_caracteristicas.buildButtonBar());
		tbs_form.addTab(vl_caracteristicas, "Caracteristicas");
		
		VerticalLayout vl_componentes = new VerticalLayout();
		vl_componentes.addComponents(this.frm_componentes, this.frm_componentes.buildButtonBar());
		tbs_form.addTab(vl_componentes, "Componentes");
		
		VerticalLayout vl_documentos = new VerticalLayout();
		vl_documentos.addComponents(this.frm_documentos, this.frm_documentos.buildButtonBar());
		tbs_form.addTab(vl_documentos, "Documentos");
		
		tbs_form.addSelectedTabChangeListener(frm_caracteristicas);
		tbs_form.addSelectedTabChangeListener(frm_componentes);
		tbs_form.addSelectedTabChangeListener(frm_documentos);
		return tbs_form;
	}
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
