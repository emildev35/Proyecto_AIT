package ait.sistemas.proyecto.activos.view.para.tiposact;

	import java.util.ArrayList;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Tipos_Activo;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposactImpl;
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

	public class FormTiposact extends GridLayout implements ValueChangeListener{
		private static final long serialVersionUID = 1L;
		private TextField txt_id_tiposact;
		public TextField txt_nombre_tiposact;

		private List<BarMessage> mensajes;

		private TiposactImpl tiposact_impl = new TiposactImpl();
		private PropertysetItem pitm_Tiposact = new PropertysetItem();
		private FieldGroup binder_Tiposact;

		public FormTiposact() {

			super.setColumns(4);
			super.setRows(3);
			setSpacing(true);
			setMargin(true);
			setWidth("100%");

			this.txt_id_tiposact= new TextField("Codigo:");
			this.txt_nombre_tiposact = new TextField("Nombre de Tipo de Activo: ");
			this.mensajes = new ArrayList<BarMessage>();
			
			pitm_Tiposact.addItemProperty("id_tiposmov", new ObjectProperty<Short>((short)1));
			pitm_Tiposact.addItemProperty("nombre_tiposmov", new ObjectProperty<String>(""));
			

			this.binder_Tiposact = new FieldGroup(this.pitm_Tiposact);

			binder_Tiposact.bind(this.txt_id_tiposact, "id_tiposmov");
			binder_Tiposact.bind(this.txt_nombre_tiposact, "nombre_tiposmov");
			binder_Tiposact.clear();

			this.txt_nombre_tiposact.setRequired(true);
			this.txt_nombre_tiposact.addValidator(new NullValidator("No Nulo", false));
			this.txt_nombre_tiposact.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 60), 3,60,false));
			this.txt_id_tiposact.setEnabled(false);	
			
			txt_id_tiposact.setWidth("90%");
			txt_nombre_tiposact.setWidth("90%");

			updateId();
			buildContent();
			Responsive.makeResponsive(this);
		}
		private void buildContent() {
			setColumnExpandRatio(0, 0.5f);
			setColumnExpandRatio(1, 2);
			addComponent(this.txt_id_tiposact, 0,0);
			addComponent(this.txt_nombre_tiposact, 1,0);

		}
		
		public void update(){
			binder_Tiposact.clear();
		}
		public void updateId(){
			this.txt_id_tiposact.setValue(tiposact_impl.generateId() + "");
		}
		public void enabled(){
			this.txt_nombre_tiposact.setEnabled(false);
		}
		
		public List<BarMessage> getMensajes() {
			return mensajes;
		}
		public void clearMessages(){
			this.mensajes = new ArrayList<BarMessage>();
		}
		public boolean validate(){

			try{
				this.binder_Tiposact.commit();
				this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
				return true;
			}catch(CommitException e){
				try {
					this.txt_nombre_tiposact.validate();
				}catch(EmptyValueException ex){
					this.mensajes.add(new BarMessage(txt_nombre_tiposact.getCaption(), Messages.EMPTY_MESSAGE));
				}catch (InvalidValueException ex) {
					this.mensajes.add(new BarMessage(txt_nombre_tiposact.getCaption(), ex.getMessage()));
				}
					
				return false;
			}		
		}
		public Tipos_Activo getData(){
			Tipos_Activo resul = new Tipos_Activo();
			resul.setTAC_Id_Tipo_Activo(Short.parseShort(this.txt_id_tiposact.getValue()));
			resul.setTAC_Nombre_Tipo_Activo(this.txt_nombre_tiposact.getValue());
			return resul;
		}
		public void setData(Tipos_Activo data){	
			this.txt_id_tiposact.setValue(String.valueOf(data.getTAC_Id_Tipo_Activo()));
			this.txt_nombre_tiposact.setValue(String.valueOf(data.getTAC_Nombre_Tipo_Activo()));
					
		}
		@Override
		public void valueChange(ValueChangeEvent event) {
		}
	}

