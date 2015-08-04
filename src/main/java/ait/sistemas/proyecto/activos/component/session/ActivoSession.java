package ait.sistemas.proyecto.activos.component.session;

public class ActivoSession {
	private long codigo;
	private short dependencia;
	private String nombre_activo;
	
	public ActivoSession() {
		// TODO Auto-generated constructor stub
	}
	
	public ActivoSession(long codigo, short dependencia, String nombre_activo) {
		this.codigo = codigo;
		this.dependencia = dependencia;
		this.nombre_activo = nombre_activo;
	}
	
	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public short getDependencia() {
		return dependencia;
	}
	
	public void setDependencia(short dependencia) {
		this.dependencia = dependencia;
	}
	public void setNombre_activo(String nombre_activo) {
		this.nombre_activo = nombre_activo;
	}
	public String getNombre_activo() {
		return nombre_activo;
	}
	
}
