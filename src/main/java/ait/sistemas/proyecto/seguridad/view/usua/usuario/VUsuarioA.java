package ait.sistemas.proyecto.seguridad.view.usua.usuario;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@CDIView(value=VUsuarioA.ID)
public class VUsuarioA extends VerticalLayout implements View{


	private static final long serialVersionUID = 1L;
	public static final String ID = "/seg/usua/usuario/a";
	
	
	public VUsuarioA() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
