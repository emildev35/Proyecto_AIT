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
	
	private FormDatosGenerales frm_datos_generales = new FormDatosGenerales();
	private FormCaracteriticas frm_caracteristicas = new FormCaracteriticas();
	private FormComponentes frm_componentes = new FormComponentes();
	private FormDocumentos frm_documentos = new FormDocumentos();
	
	public VActivoA() {
		setWidth("100%");
		addComponent(buildFormContent());
	}
	
	private Component buildFormContent() {
		final TabSheet tbs_form = new TabSheet();
		tbs_form.addTab(frm_datos_generales, "Datos Generales");
		tbs_form.addTab(frm_caracteristicas, "Caracteristicas");
		tbs_form.addTab(frm_componentes, "Componentes");
		tbs_form.addTab(frm_documentos, "Documentos");
		return tbs_form;
	}
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
