package ait.sistemas.proyecto.activos.view.para.fuente;

	import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Fuentes_Financiamiento;
import ait.sistemas.proyecto.activos.data.service.Impl.FuenteImpl;
import ait.sistemas.proyecto.common.component.BarMessage;
import ait.sistemas.proyecto.common.component.Messages;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Responsive;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

	public class FormFuente extends GridLayout implements ValueChangeListener{
		private static final long serialVersionUID = 1L;
		private TextField txt_id_fuente;
		public TextField txt_nombre_fuente;

		private List<BarMessage> mensajes;

		private FuenteImpl fuente_impl = new FuenteImpl();
		private PropertysetItem pitm_Fuente = new PropertysetItem();
		private FieldGroup binder_Fuente;

		public FormFuente() {

			super.setColumns(4);
			super.setRows(3);
			setSpacing(true);
			setMargin(true);
			setWidth("100%");

			this.txt_id_fuente= new TextField("Codigo Fuente:");
			this.txt_nombre_fuente = new TextField("Nombre Fuente de Financiamiento: ");
			this.mensajes = new ArrayList<BarMessage>();
			
			pitm_Fuente.addItemProperty("id_fuente", new ObjectProperty<Integer>((int)1));
			pitm_Fuente.addItemProperty("nombre_fuente", new ObjectProperty<String>(""));
			

			this.binder_Fuente = new FieldGroup(this.pitm_Fuente);

			binder_Fuente.bind(this.txt_id_fuente, "id_fuente");
			binder_Fuente.bind(this.txt_nombre_fuente, "nombre_fuente");
			binder_Fuente.clear();

			this.txt_nombre_fuente.setRequired(true);
			this.txt_nombre_fuente.addValidator(new NullValidator("No Nulo", false));
			this.txt_nombre_fuente.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 60), 3,60,false));
			this.txt_id_fuente.setEnabled(false);	
			
			txt_id_fuente.setWidth("90%");
			txt_nombre_fuente.setWidth("90%");

			updateId();
			buildContent();
			Responsive.makeResponsive(this);
		}
		private void buildContent() {
			setColumnExpandRatio(0, 0.5f);
			setColumnExpandRatio(1, 2);
			addComponent(this.txt_id_fuente, 0,0);
			addComponent(this.txt_nombre_fuente, 1,0);

		}
		
		public void update(){
			binder_Fuente.clear();
		}
		public void updateId(){
			this.txt_id_fuente.setValue(fuente_impl.generateId() + "");
		}
		public void enabled(){
			this.txt_nombre_fuente.setEnabled(false);
		}
		
		public List<BarMessage> getMensajes() {
			return mensajes;
		}
		public void clearMessages(){
			this.mensajes = new ArrayList<BarMessage>();
		}
		public boolean validate(){

			try{
				this.binder_Fuente.commit();
				this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
				return true;
			}catch(CommitException e){
				try {
					this.txt_nombre_fuente.validate();
				}catch(EmptyValueException ex){
					this.mensajes.add(new BarMessage(txt_nombre_fuente.getCaption(), Messages.EMPTY_MESSAGE));
				}catch (InvalidValueException ex) {
					this.mensajes.add(new BarMessage(txt_nombre_fuente.getCaption(), ex.getMessage()));
				}
					
				return false;
			}		
		}
		public Fuentes_Financiamiento getData(){
			Fuentes_Financiamiento resul = new Fuentes_Financiamiento();
			resul.setFFI_Fuente_Financiamiento(Integer.parseInt(this.txt_id_fuente.getValue()));
			resul.setFFI_Nombre_Fuente_Financiamiento(this.txt_nombre_fuente.getValue());
			long lnMilis = new Date().getTime();
			resul.setFFI_Fecha_Registro(new java.sql.Date(lnMilis));
			return resul;
		}
		public void setData(Fuentes_Financiamiento data){	
			this.txt_id_fuente.setValue(String.valueOf(data.getFFI_Fuente_Financiamiento()));
			this.txt_nombre_fuente.setValue(String.valueOf(data.getFFI_Nombre_Fuente_Financiamiento()));
					
		}
		@Override
		public void valueChange(ValueChangeEvent event) {
		}
	}

