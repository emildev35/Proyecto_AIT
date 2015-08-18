package ait.sistemas.proyecto.activos.view.mvac.actbaja;

	import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.component.DocumentUploader;
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
import com.vaadin.ui.Upload;

	public class FormActbaja extends GridLayout implements ValueChangeListener{
		private static final long serialVersionUID = 1L;
		private TextField txt_documento;
		private DocumentUploader reciver = new DocumentUploader();
		public Upload field_ubicacion_documento = new Upload("Direccion Documento", reciver);
		

		private List<BarMessage> mensajes;

		private PropertysetItem pitm_Actualizacion_Baja = new PropertysetItem();
		private FieldGroup binder_Actualizacion_Baja;

		public FormActbaja() {

			super.setColumns(4);
			super.setRows(3);
			setSpacing(true);
			setMargin(true);
			setWidth("100%");

			this.txt_documento= new TextField("Nombre del Documento");
			this.mensajes = new ArrayList<BarMessage>();
			
			pitm_Actualizacion_Baja.addItemProperty("documento", new ObjectProperty<String>(("")));
			pitm_Actualizacion_Baja.addItemProperty("ubicacion_documento", new ObjectProperty<String>(""));
			

			this.binder_Actualizacion_Baja = new FieldGroup(this.pitm_Actualizacion_Baja);

			binder_Actualizacion_Baja.bind(this.txt_documento, "documento");
			binder_Actualizacion_Baja.clear();

			this.txt_documento.setRequired(true);
			this.txt_documento.addValidator(new NullValidator("No Nulo", false));
			this.txt_documento.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 60), 3,60,false));
			
			txt_documento.setWidth("90%");
			this.field_ubicacion_documento.setWidth("90%");
			this.field_ubicacion_documento.setWidth("90%");
			this.field_ubicacion_documento.setButtonCaption("Adjuntar");
			buildContent();
			Responsive.makeResponsive(this);
		}
		private void buildContent() {
			setColumnExpandRatio(0, 2);
			setColumnExpandRatio(1, 2);
			addComponent(this.txt_documento, 0,0);
			addComponent(this.field_ubicacion_documento, 1,0);

		}
		
		public void update(){
			binder_Actualizacion_Baja.clear();
		}
		
		public List<BarMessage> getMensajes() {
			return mensajes;
		}
		public void clearMessages(){
			this.mensajes = new ArrayList<BarMessage>();
		}
		public boolean validate(){

			try{
				this.binder_Actualizacion_Baja.commit();
				this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
				return true;
			}catch(CommitException e){
				try {
					this.txt_documento.validate();
				}catch(EmptyValueException ex){
					this.mensajes.add(new BarMessage(txt_documento.getCaption(), Messages.EMPTY_MESSAGE));
				}catch (InvalidValueException ex) {
					this.mensajes.add(new BarMessage(txt_documento.getCaption(), ex.getMessage()));
				}
				
					if(this.reciver.getFile().equals("")){
						this.mensajes.add(new BarMessage(field_ubicacion_documento.getCaption(), Messages.EMPTY_MESSAGE));
					}
					
				return false;
			}		
		}
		
		@Override
		public void valueChange(ValueChangeEvent event) {
		}

		public String getDireccionDocumento() {
			return reciver.getFile();
		}
		public String getNombreDocumento() {
			return this.txt_documento.getValue().toString();
		}
	}

