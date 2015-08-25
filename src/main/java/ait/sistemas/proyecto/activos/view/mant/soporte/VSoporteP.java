package ait.sistemas.proyecto.activos.view.mant.soporte;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@CDIView(value=VSoporteP.ID)
public class VSoporteP extends VerticalLayout implements View{
	
	private static final long serialVersionUID = 1L;
	public static final String ID = "/act/mant/soporte";
	
	private FormSoporte frm_soporte = new FormSoporte();
	
	public VSoporteP() {
		addComponent(frm_soporte);
	}
	@Override
	public void enter(ViewChangeEvent event) {
		
	}
	
}
