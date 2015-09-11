package ait.sistemas.proyecto.common.report.pdf.movimiento;

public class Firma {
	
	private String nombre_usuario;
	private float alto;
	
	public Firma(String nombre_usuario, float alto) {
		this.nombre_usuario = nombre_usuario;
		this.alto = alto;
	}
	
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public float getAlto() {
		return alto;
	}
	public void setAlto(float alto) {
		this.alto = alto;
	}

	

}
