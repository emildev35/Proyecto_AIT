package ait.sistemas.proyecto.activos.component.model;

public class Componente {
	private String nombre;
	private String caracteristica;
	
	public Componente() {
	}
	
	public Componente(String nombre, String caracteritica) {
		this.nombre = nombre;
		this.caracteristica = caracteritica;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCaracteritica() {
		return caracteristica;
	}
	
	public void setCaracteritica(String caracteritica) {
		this.caracteristica = caracteritica;
	}
	
}
