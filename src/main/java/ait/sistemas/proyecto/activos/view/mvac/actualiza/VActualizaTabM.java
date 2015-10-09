package ait.sistemas.proyecto.activos.view.mvac.actualiza;

import java.util.List;

import ait.sistemas.proyecto.activos.component.session.ActivoSession;
import ait.sistemas.proyecto.activos.view.mvac.actualiza.form.FormCaracteriticas;
import ait.sistemas.proyecto.activos.view.mvac.actualiza.form.FormComponentes;
import ait.sistemas.proyecto.activos.view.mvac.actualiza.form.FormDatosGenerales;
import ait.sistemas.proyecto.activos.view.mvac.actualiza.form.FormDocumentos;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.view.AitView;
import ait.sistemas.proyecto.seguridad.data.model.Arbol_menus;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class VActualizaTabM extends VerticalLayout implements View {
	
	public static final String ID = "/activos/mvac/actualiza/VActualizaTabM";
	private static final long serialVersionUID = 1L;
	
	private FormDatosGenerales frm_datos_generales = new FormDatosGenerales(this);
	private FormCaracteriticas frm_caracteristicas = new FormCaracteriticas(this);
	private FormComponentes frm_componentes = new FormComponentes(this);
	private FormDocumentos frm_documentos = new FormDocumentos(this);
	public TabSheet tbs_form;
	private final Arbol_menus menu = (Arbol_menus) UI.getCurrent().getSession().getAttribute("nav");
	
	private CssLayout hl_errores =  new CssLayout();
	
	public VActualizaTabM() {
		setWidth("100%");
		addComponent(buildNavBar());
		addComponent(buildFormContent());
		addComponent(hl_errores);
	}
	
	private Component buildFormContent() {
		tbs_form = new TabSheet();
		tbs_form.setStyleName("framed centered-tabs");
		
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
	
	private Component buildNavBar() {
		Panel navPanel = new Panel();
		HorizontalLayout nav = new HorizontalLayout();
		nav.addStyleName("ait-content-nav");
		nav.addComponent(new Label(AitView.getNavText(menu), ContentMode.HTML));
		navPanel.setContent(nav);
		return navPanel;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if((ActivoSession)UI.getCurrent().getSession().getAttribute("activo")==null){
			UI.getCurrent().getNavigator().navigateTo(VActualizaM.URL);
		}
		
	}
	
	public void buildMessages(List<BarMessage> mensajes) {
		hl_errores.removeAllComponents();
		hl_errores.addStyleName("ait-error-bar");
		for (BarMessage barMessage : mensajes) {
			Label lbError = new Label(barMessage.getComponetName() + ":" + barMessage.getErrorName(), ContentMode.HTML);
			lbError.setStyleName(barMessage.getType());
			hl_errores.addComponent(lbError);
		}
	}
	
}