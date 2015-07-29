package ait.sistemas.proyecto.activos.component.model;

public class Componente {
	private String nombre;
	private String caracteritica;
	
	public Componente() {
	}
	
	public Componente(String nombre, String caracteritica) {
		this.nombre = nombre;
		this.caracteritica = caracteritica;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCaracteritica() {
		return caracteritica;
	}
	
	public void setCaracteritica(String caracteritica) {
		this.caracteritica = caracteritica;
	}
	
}
