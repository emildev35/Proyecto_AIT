package ait.sistemas.proyecto.common.component;

/**
 * Clase encargada de almacenar los mensajes de Error, Succes de las Pantallas
 * 
 * @author franzemil
 *
 */
public class BarMessage {
	
	/**
	 * Nombre del Componente que genero el Error
	 */
	private String componetName;
	/**
	 * Mensaje de error
	 */
	private String errorName;
	/**
	 * Tipo de Error : failure, success
	 */
	private String type;
	
	public BarMessage() {
	}
	
	public BarMessage(String component, String error) {
		this.componetName = component;
		this.errorName = error;
		this.type = "failure";
	}
	
	public BarMessage(String component, String error, String type) {
		this.componetName = component;
		this.errorName = error;
		this.type = type;
	}
	
	public void setComponetName(String componetName) {
		this.componetName = componetName + " ";
	}
	
	public String getComponetName() {
		return componetName;
	}
	
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	
	public String getErrorName() {
		return " " + errorName;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
