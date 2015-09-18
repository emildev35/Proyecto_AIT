package ait.sistemas.proyecto.common.component;

public class BarMessage {

	private String componetName;
	private String errorName;
	private String type;
	
	public BarMessage() {
	}
	public BarMessage(String component, String error){
		this.componetName = component;
		this.errorName = error;
		this.type = "failure";
	}
	public BarMessage(String component, String error, String type){
		this.componetName = component;
		this.errorName = error;
		this.type = type;
	}
	public void setComponetName(String componetName) {
		this.componetName = componetName;
	}
	public String getComponetName() {
		return componetName;
	}
	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	public String getErrorName() {
		return errorName;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}
