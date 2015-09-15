package ait.sistemas.proyecto.common.component;

import ait.sistemas.proyecto.seguridad.component.model.SessionModel;
import ait.sistemas.proyecto.seguridad.data.service.Impl.UsuarioImpl;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.ui.UI;

public class AitViewProvider implements ViewProvider {
	
	private static final long serialVersionUID = 1L;
	private final UsuarioImpl usuarioimpl = new UsuarioImpl();
	private final SessionModel session = (SessionModel)UI.getCurrent().getSession().getAttribute("user");
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
		if(viewAndParameters==null || viewAndParameters.equals("null"))
			return "";
		
//		if(!usuarioimpl.checkpermission(session.getId(), viewAndParameters)){
//			return "";
//		}
		return viewAndParameters;
	}
	
}
