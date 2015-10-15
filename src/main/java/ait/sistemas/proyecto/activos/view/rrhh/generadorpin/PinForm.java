package ait.sistemas.proyecto.activos.view.rrhh.generadorpin;

import java.util.Date;

import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class PinForm extends VerticalLayout {
	
	private static final long serialVersionUID = 1L;
	private TextField txt_pin = new TextField("PIN");
	private Label lb = new Label(Messages.PIN_MESSAGES);
	private Label lb2 = new Label(Messages.PIN_MESSAGES_2);
	
	public PinForm() {
		setSizeFull();
		setMargin(true);
		setSpacing(true);
		txt_pin.setEnabled(false);
		addComponent(txt_pin);
		setComponentAlignment(txt_pin, Alignment.MIDDLE_CENTER);
		addComponent(lb);
		setComponentAlignment(lb, Alignment.MIDDLE_CENTER);
		addComponent(lb2);
		setComponentAlignment(lb2, Alignment.MIDDLE_CENTER);
	}
	
	public void updatePin() {
		try {
			String pin = String.valueOf(new Date().getTime() / 100);
			int pos = 1 + (int) Math.round(Math.random() * (pin.length() - 1));
			this.txt_pin.setValue(pin.substring(pos - 1, pos) + pin.substring(pin.length() - 4, pin.length()));
		} catch (Exception e) {
			UI.getCurrent().getPage().reload();
		}
	}
	
	public String getCode() {
		return this.txt_pin.getValue().toString();
	}
}
