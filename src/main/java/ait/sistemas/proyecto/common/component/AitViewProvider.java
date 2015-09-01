package ait.sistemas.proyecto.common.component;

import java.util.StringTokenizer;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

public class AitViewProvider implements ViewProvider{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	@SuppressWarnings("rawtypes")
	public View getView(String viewName) {
		System.out.print(viewName);
		String[] result = viewName.split("/");
		
		String ruta_view = "ait.sistemas.proyecto."+ result[1] + ".view";
		for (int i = 0; i < result.length; i++) {
			if(i > 1){
				ruta_view += "." + result[i];
			}
		}
		System.out.print(ruta_view);
		Class view;
		try {
			view = Class.forName(ruta_view);
			try {
				return (View) view.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
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
		return viewAndParameters;
	}
	
}
