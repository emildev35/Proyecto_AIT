package ait.sistemas.proyecto.seguridad.component;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;

public class ComboBoxIcons extends ComboBox {

	private static final long serialVersionUID = 1L;

	public ComboBoxIcons() {
	}

	public ComboBoxIcons(String caption) {
		setCaption(caption);
		setNullSelectionAllowed(false);
		setInputPrompt("Seleccione un Icono");
		
		for (FontAwesome icon : FontAwesome.values()) {
			addItem(icon.toString());
			setItemIcon(icon.toString(), icon);
		}

	}

}
