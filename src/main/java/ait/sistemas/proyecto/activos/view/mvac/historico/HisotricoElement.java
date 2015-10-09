package ait.sistemas.proyecto.activos.view.mvac.historico;

public class HisotricoElement {

	private String titulo;
	private int ancho;
	private String contenido;
	private int fila;
	private int columna;
	public HisotricoElement() {
	}
	public HisotricoElement(String titulo, int ancho, String contenido, int fila,
			int columna) {
		super();
		this.titulo = titulo;
		this.ancho = ancho;
		this.contenido = contenido;
		this.fila = fila;
		this.columna = columna;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	
}
