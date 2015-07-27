package ait.sistemas.proyecto.common.view;

import ait.sistemas.proyecto.common.component.BarDash;
import ait.sistemas.proyecto.common.component.MenuDash;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;


public class MainView extends CssLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuDash menu = new MenuDash();

	@SuppressWarnings("unused")
	public MainView(Component mainContent) {
		setSizeFull();
		//addStyleName("main-view");
		addComponent(new BarDash());
		HorizontalLayout hor = new HorizontalLayout();
		hor.setWidth("100%");
		hor.setStyleName("main-content");
		hor.addComponent(menu);
		addComponent(hor);
		CssLayout content = new CssLayout();
		hor.addComponent(mainContent);
		hor.setExpandRatio(menu, 1);
		hor.setExpandRatio(mainContent, 5f);
		mainContent.addStyleName("ait-content");
		mainContent.setSizeFull();
		Responsive.makeResponsive(this);
	}

}