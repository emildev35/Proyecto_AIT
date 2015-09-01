package ait.sistemas.proyecto.common.view;



import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends VerticalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String URL = "";

	public HomeView() {
		this.addComponent(new Label("mainss"));
	}
	
	@Override
	public void enter(ViewChangeEvent event) {

	}

}
