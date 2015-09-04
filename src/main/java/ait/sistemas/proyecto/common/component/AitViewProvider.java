package ait.sistemas.proyecto.common.component;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

public class AitViewProvider implements ViewProvider {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	@SuppressWarnings("rawtypes")
	public View getView(String viewName) {
		System.out.print(viewName);
		Class view;
		String[] result = viewName.split("/");
		try {
			String ruta_view = "ait.sistemas.proyecto." + result[1] + ".view";
			for (int i = 0; i < result.length; i++) {
				if (i > 1) {
					ruta_view += "." + result[i];
				}
			}
			System.out.print(ruta_view);
			
			view = Class.forName(ruta_view);
			try {
				return (View) view.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}catch (ArrayIndexOutOfBoundsException ex){
			try {
				view = Class.forName("ait.sistemas.proyecto.common.view.HomeView");
				return (View) view.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			try {
				view = Class.forName("ait.sistemas.proyecto.common.view.HomeView");
				return (View) view.newInstance();
			} catch (ClassNotFoundException e2) {
				e2.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public String getViewName(String viewAndParameters) {
		if(viewAndParameters==null)
			return "";
		return viewAndParameters;
	}
	
}
