package ait.sistemas.proyecto.activos.component.model;

import java.io.Serializable;

/**
 * Clase que contiene la informacion de un Activo desde la perspectiva de un 
 * inventario fisico 
 * @author franzemil
 *
 */
public class ActivoInventario implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id_activo;
	private String nombre_activo;
	private String observaciones;
	private String ci_usuario;
	
	public ActivoInventario() {
	}
	
	public ActivoInventario(long id_activo, String nombre_activo, String observaciones, String ci_usuario) {
		this.id_activo = id_activo;
		this.nombre_activo = nombre_activo;
		this.observaciones = observaciones;
		this.ci_usuario = ci_usuario;
	}
	
	public long getId_activo() {
		return id_activo;
	}
	
	public void setId_activo(long id_activo) {
		this.id_activo = id_activo;
	}
	
	public String getNombre_activo() {
		return nombre_activo;
	}
	
	public void setNombre_activo(String nombre_activo) {
		this.nombre_activo = nombre_activo;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public String getCi_usuario() {
		return ci_usuario;
	}
	
	public void setCi_usuario(String ci_usuario) {
		this.ci_usuario = ci_usuario;
	}
	
}
