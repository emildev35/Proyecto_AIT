package ait.sistemas.proyecto.activos.component.model;

import java.sql.Date;

public class CmovimientoDocumento {
	
	private short id_dependencia;
	private String nro_documento_referencia;
	private String nombre_documento;
	private String direccion_documento;
	private Date fecha_nro_referencia;
	
	public CmovimientoDocumento() {
	}

	public short getId_dependencia() {
		return id_dependencia;
	}

	public void setId_dependencia(short id_dependencia) {
		this.id_dependencia = id_dependencia;
	}

	public String getNro_documento_referencia() {
		return nro_documento_referencia;
	}

	public void setNro_documento_referencia(String nro_documento_referencia) {
		this.nro_documento_referencia = nro_documento_referencia;
	}

	public String getNombre_documento() {
		return nombre_documento;
	}

	public void setNombre_documento(String nombre_documento) {
		this.nombre_documento = nombre_documento;
	}

	public String getDireccion_documento() {
		return direccion_documento;
	}

	public void setDireccion_documento(String direccion_documento) {
		this.direccion_documento = direccion_documento;
	}

	public Date getFecha_nro_referencia() {
		return fecha_nro_referencia;
	}

	public void setFecha_nro_referencia(Date fecha_nro_referencia) {
		this.fecha_nro_referencia = fecha_nro_referencia;
	}
	
	

	
}
