package ait.sistemas.proyecto.activos.view.para.tiposmov;

	import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ait.sistemas.proyecto.activos.data.model.Tipos_Movimiento;
import ait.sistemas.proyecto.activos.data.service.Impl.TiposmovImpl;
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

	public class FormTiposmov extends GridLayout implements ValueChangeListener{
		private static final long serialVersionUID = 1L;
		private TextField txt_id_tiposmov;
		public TextField txt_nombre_tiposmov;
		private TextField txt_sigla_tiposmov;

		private List<BarMessage> mensajes;

		private TiposmovImpl tiposmov_impl = new TiposmovImpl();
		private PropertysetItem pitm_Tiposmov = new PropertysetItem();
		private FieldGroup binder_Tiposmov;

		public FormTiposmov() {

			super.setColumns(4);
			super.setRows(3);
			setSpacing(true);
			setMargin(true);
			setWidth("100%");

			this.txt_id_tiposmov = new TextField("Codigo:");
			this.txt_nombre_tiposmov = new TextField("Nombre de Tipo de Movimiento: ");
			this.txt_sigla_tiposmov = new TextField("Sigla: ");
			this.mensajes = new ArrayList<BarMessage>();
			
			pitm_Tiposmov.addItemProperty("id_tiposmov", new ObjectProperty<Short>((short)1));
			pitm_Tiposmov.addItemProperty("nombre_tiposmov", new ObjectProperty<String>(""));
			pitm_Tiposmov.addItemProperty("sigla_tiposmov", new ObjectProperty<String>(""));
			

			this.binder_Tiposmov = new FieldGroup(this.pitm_Tiposmov);

			binder_Tiposmov.bind(this.txt_id_tiposmov, "id_tiposmov");
			binder_Tiposmov.bind(this.txt_nombre_tiposmov, "nombre_tiposmov");
			binder_Tiposmov.bind(this.txt_sigla_tiposmov, "sigla_tiposmov");
			binder_Tiposmov.clear();

			this.txt_nombre_tiposmov.setRequired(true);
			this.txt_nombre_tiposmov.addValidator(new NullValidator("No Nulo", false));
			this.txt_nombre_tiposmov.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(3, 60), 3,60,false));
			this.txt_sigla_tiposmov.setRequired(true);
			this.txt_sigla_tiposmov.addValidator(new NullValidator("No Nulo", false));
			this.txt_sigla_tiposmov.addValidator(new StringLengthValidator(Messages.STRING_LENGTH_MESSAGE(2, 9), 2,9,false));
			this.txt_id_tiposmov.setEnabled(false);	
			
			txt_id_tiposmov.setWidth("90%");
			txt_nombre_tiposmov.setWidth("90%");
			txt_sigla_tiposmov.setWidth("90%");

			updateId();
			buildContent();
			Responsive.makeResponsive(this);
		}
		private void buildContent() {
			setColumnExpandRatio(0, 0.5f);
			setColumnExpandRatio(1, 2);
			setColumnExpandRatio(2, 0.5f);
			addComponent(this.txt_id_tiposmov, 0,0);
			addComponent(this.txt_nombre_tiposmov, 1,0);
			addComponent(this.txt_sigla_tiposmov, 2,0);

		}
		
		public void update(){
			binder_Tiposmov.clear();
		}
		public void updateId(){
			this.txt_id_tiposmov.setValue(tiposmov_impl.generateId() + "");
		}
		public void enabled(){
			this.txt_nombre_tiposmov.setEnabled(false);
		}
		
		public List<BarMessage> getMensajes() {
			return mensajes;
		}
		public void clearMessages(){
			this.mensajes = new ArrayList<BarMessage>();
		}
		public boolean validate(){

			try{
				this.binder_Tiposmov.commit();
				this.mensajes.add(new BarMessage("Formulario", Messages.SUCCESS_MESSAGE, "success"));
				return true;
			}catch(CommitException e){
				try {
					this.txt_nombre_tiposmov.validate();
				}catch(EmptyValueException ex){
					this.mensajes.add(new BarMessage(txt_nombre_tiposmov.getCaption(), Messages.EMPTY_MESSAGE));
				}catch (InvalidValueException ex) {
					this.mensajes.add(new BarMessage(txt_nombre_tiposmov.getCaption(), ex.getMessage()));
				}
					
				try {
					this.txt_sigla_tiposmov.validate();
					}catch(EmptyValueException ex){
					this.mensajes.add(new BarMessage(txt_sigla_tiposmov.getCaption(), Messages.EMPTY_MESSAGE));
				}catch (InvalidValueException ex) {
					this.mensajes.add(new BarMessage(txt_sigla_tiposmov.getCaption(), ex.getMessage()));
				}
				return false;
			}		
		}
		public Tipos_Movimiento getData(){
			Tipos_Movimiento resul = new Tipos_Movimiento();
			resul.setTMV_Tipo_Movimiento(Short.parseShort(this.txt_id_tiposmov.getValue()));
			resul.setTMV_Nombre_Tipo_Movimiento(this.txt_nombre_tiposmov.getValue());
			resul.setTMV_Sigla_Tipo_Movimiento(this.txt_sigla_tiposmov.getValue());
			long lnMilis = new Date().getTime();
			resul.setTMV_Fecha_Registro(new java.sql.Date(lnMilis));
			return resul;
		}
		public void setData(Tipos_Movimiento data){	
			this.txt_id_tiposmov.setValue(String.valueOf(data.getTMV_Tipo_Movimiento()));
			this.txt_nombre_tiposmov.setValue(String.valueOf(data.getTMV_Nombre_Tipo_Movimiento()));
			this.txt_sigla_tiposmov.setValue(String.valueOf(data.getTMV_Sigla_Tipo_Movimiento()));
					
		}
		@Override
		public void valueChange(ValueChangeEvent event) {
		}
	}

