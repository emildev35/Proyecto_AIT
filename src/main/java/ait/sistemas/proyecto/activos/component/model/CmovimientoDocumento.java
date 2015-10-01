package ait.sistemas.proyecto.activos.component.model;

import java.sql.Date;

public class CmovimientoDocumento {
	
	private short id_dependencia;
	private String nro_documento_referencia;
	private String nombre_documento;
	private String direccion_documento;
	private Date fecha_nro_referencia;
	private short tipo_movimiento;
	private long no_documento;
	
	public CmovimientoDocumento() {
	}
	public CmovimientoDocumento(String no_referencia, Date fecha_no_referencia) {
		this.nro_documento_referencia=no_referencia;
		this.fecha_nro_referencia = fecha_no_referencia;
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

	public short getTipo_movimiento() {
		return tipo_movimiento;
	}

	public void setTipo_movimiento(short tipo_movimiento) {
		this.tipo_movimiento = tipo_movimiento;
	}

	public long getNo_documento() {
		return no_documento;
	}

	public void setNo_documento(long no_documento) {
		this.no_documento = no_documento;
	}
	
	@Override
	public boolean equals(Object obj) {
		CmovimientoDocumento nmov = (CmovimientoDocumento)obj;
		if(this.nro_documento_referencia.equals(nmov.getNro_documento_referencia()) && this.fecha_nro_referencia.equals(nmov.getFecha_nro_referencia())){
			return true;
		}
		return false;
	}
}
