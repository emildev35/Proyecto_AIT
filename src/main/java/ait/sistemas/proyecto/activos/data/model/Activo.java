package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Time;


/**
 * The persistent class for the Activos database table.
 * 
 */
@Entity
@Table(name="Activos")
@NamedQuery(name="Activo.findAll", query="SELECT a FROM Activo a")
public class Activo implements Serializable {
	private static final long serialVersionUID = 1L;

	private short ACT_Dependencia;
	
	@Id
	private long ACT_Codigo_Activo;

	private BigDecimal ACT_Actualizacion_Acumulada;

	private BigDecimal ACT_Actualizacion_Gestion;

	private String ACT_Auxiliar_Contable;

	private String ACT_CI_Empleado_Asignado;

	private BigDecimal ACT_Depreciacion_Acumulada;

	private BigDecimal ACT_Depreciacion_Gestion;

	private Time ACT_Fecha_Asignacion;

	private Time ACT_Fecha_Baja;

	private Time ACT_Fecha_Comodato;

	private Time ACT_Fecha_Compra;

	private Time ACT_Fecha_Incorporacion;

	private Time ACT_Fecha_Registro;

	private Time ACT_Fecha_Ultima_Depreciacion;

	private Time ACT_Fecha_Ultima_Revalorizacion;

	private Time ACT_Fecha_Vencimiento_Garantia;

	private Time ACT_Fecha_Vencimiento_Mantenimiento;

	private Time ACT_Fecha_Vencimiento_Seguro;

	private int ACT_Fuente_Financiamiento;

	private String ACT_Grupo_Contable;

	private String ACT_Marca;

	private String ACT_NIT_Proveedor;

	private long ACT_No_Acta_Entrega;

	private long ACT_No_Comprobante_Gasto;

	private String ACT_No_Contrato_Mantenimiento;

	private String ACT_No_Folio_Real;

	private String ACT_No_Garantia;

	private long ACT_No_Informe_Baja;

	private String ACT_No_Poliza_Seguro;

	private String ACT_No_RUAT;

	private String ACT_No_Serie;

	private String ACT_Nombre_Activo;

	private short ACT_Organismo_Financiador;

	private short ACT_Tipo_Activo;

	private BigDecimal ACT_Tipo_Cambio_Dolar;

	private BigDecimal ACT_Tipo_Cambio_UFV;

	private int ACT_Ubicacion_Fisica_Activo;

	private String ACT_Ubicacion_Imagen;

	private BigDecimal ACT_Valor_Compra;

	private BigDecimal ACT_Valor_Neto;

	private int ACT_Vida_Util;

	public Activo() {
	}

	public short getACT_Dependencia() {
		return this.ACT_Dependencia;
	}
	public void setACT_Dependencia(short ACT_Dependencia) {
		this.ACT_Dependencia = ACT_Dependencia;
	}
	public long getACT_Codigo_Activo() {
		return this.ACT_Codigo_Activo;
	}
	public void setACT_Codigo_Activo(long ACT_Codigo_Activo) {
		this.ACT_Codigo_Activo = ACT_Codigo_Activo;
	}

	public BigDecimal getACT_Actualizacion_Acumulada() {
		return this.ACT_Actualizacion_Acumulada;
	}

	public void setACT_Actualizacion_Acumulada(BigDecimal ACT_Actualizacion_Acumulada) {
		this.ACT_Actualizacion_Acumulada = ACT_Actualizacion_Acumulada;
	}

	public BigDecimal getACT_Actualizacion_Gestion() {
		return this.ACT_Actualizacion_Gestion;
	}

	public void setACT_Actualizacion_Gestion(BigDecimal ACT_Actualizacion_Gestion) {
		this.ACT_Actualizacion_Gestion = ACT_Actualizacion_Gestion;
	}

	public String getACT_Auxiliar_Contable() {
		return this.ACT_Auxiliar_Contable;
	}

	public void setACT_Auxiliar_Contable(String ACT_Auxiliar_Contable) {
		this.ACT_Auxiliar_Contable = ACT_Auxiliar_Contable;
	}

	public String getACT_CI_Empleado_Asignado() {
		return this.ACT_CI_Empleado_Asignado;
	}

	public void setACT_CI_Empleado_Asignado(String ACT_CI_Empleado_Asignado) {
		this.ACT_CI_Empleado_Asignado = ACT_CI_Empleado_Asignado;
	}

	public BigDecimal getACT_Depreciacion_Acumulada() {
		return this.ACT_Depreciacion_Acumulada;
	}

	public void setACT_Depreciacion_Acumulada(BigDecimal ACT_Depreciacion_Acumulada) {
		this.ACT_Depreciacion_Acumulada = ACT_Depreciacion_Acumulada;
	}

	public BigDecimal getACT_Depreciacion_Gestion() {
		return this.ACT_Depreciacion_Gestion;
	}

	public void setACT_Depreciacion_Gestion(BigDecimal ACT_Depreciacion_Gestion) {
		this.ACT_Depreciacion_Gestion = ACT_Depreciacion_Gestion;
	}

	public Time getACT_Fecha_Asignacion() {
		return this.ACT_Fecha_Asignacion;
	}

	public void setACT_Fecha_Asignacion(Time ACT_Fecha_Asignacion) {
		this.ACT_Fecha_Asignacion = ACT_Fecha_Asignacion;
	}

	public Time getACT_Fecha_Baja() {
		return this.ACT_Fecha_Baja;
	}

	public void setACT_Fecha_Baja(Time ACT_Fecha_Baja) {
		this.ACT_Fecha_Baja = ACT_Fecha_Baja;
	}

	public Time getACT_Fecha_Comodato() {
		return this.ACT_Fecha_Comodato;
	}

	public void setACT_Fecha_Comodato(Time ACT_Fecha_Comodato) {
		this.ACT_Fecha_Comodato = ACT_Fecha_Comodato;
	}

	public Time getACT_Fecha_Compra() {
		return this.ACT_Fecha_Compra;
	}

	public void setACT_Fecha_Compra(Time ACT_Fecha_Compra) {
		this.ACT_Fecha_Compra = ACT_Fecha_Compra;
	}

	public Time getACT_Fecha_Incorporacion() {
		return this.ACT_Fecha_Incorporacion;
	}

	public void setACT_Fecha_Incorporacion(Time ACT_Fecha_Incorporacion) {
		this.ACT_Fecha_Incorporacion = ACT_Fecha_Incorporacion;
	}

	public Time getACT_Fecha_Registro() {
		return this.ACT_Fecha_Registro;
	}

	public void setACT_Fecha_Registro(Time ACT_Fecha_Registro) {
		this.ACT_Fecha_Registro = ACT_Fecha_Registro;
	}

	public Time getACT_Fecha_Ultima_Depreciacion() {
		return this.ACT_Fecha_Ultima_Depreciacion;
	}

	public void setACT_Fecha_Ultima_Depreciacion(Time ACT_Fecha_Ultima_Depreciacion) {
		this.ACT_Fecha_Ultima_Depreciacion = ACT_Fecha_Ultima_Depreciacion;
	}

	public Time getACT_Fecha_Ultima_Revalorizacion() {
		return this.ACT_Fecha_Ultima_Revalorizacion;
	}

	public void setACT_Fecha_Ultima_Revalorizacion(Time ACT_Fecha_Ultima_Revalorizacion) {
		this.ACT_Fecha_Ultima_Revalorizacion = ACT_Fecha_Ultima_Revalorizacion;
	}

	public Time getACT_Fecha_Vencimiento_Garantia() {
		return this.ACT_Fecha_Vencimiento_Garantia;
	}

	public void setACT_Fecha_Vencimiento_Garantia(Time ACT_Fecha_Vencimiento_Garantia) {
		this.ACT_Fecha_Vencimiento_Garantia = ACT_Fecha_Vencimiento_Garantia;
	}

	public Time getACT_Fecha_Vencimiento_Mantenimiento() {
		return this.ACT_Fecha_Vencimiento_Mantenimiento;
	}

	public void setACT_Fecha_Vencimiento_Mantenimiento(Time ACT_Fecha_Vencimiento_Mantenimiento) {
		this.ACT_Fecha_Vencimiento_Mantenimiento = ACT_Fecha_Vencimiento_Mantenimiento;
	}

	public Time getACT_Fecha_Vencimiento_Seguro() {
		return this.ACT_Fecha_Vencimiento_Seguro;
	}

	public void setACT_Fecha_Vencimiento_Seguro(Time ACT_Fecha_Vencimiento_Seguro) {
		this.ACT_Fecha_Vencimiento_Seguro = ACT_Fecha_Vencimiento_Seguro;
	}

	public int getACT_Fuente_Financiamiento() {
		return this.ACT_Fuente_Financiamiento;
	}

	public void setACT_Fuente_Financiamiento(int ACT_Fuente_Financiamiento) {
		this.ACT_Fuente_Financiamiento = ACT_Fuente_Financiamiento;
	}

	public String getACT_Grupo_Contable() {
		return this.ACT_Grupo_Contable;
	}

	public void setACT_Grupo_Contable(String ACT_Grupo_Contable) {
		this.ACT_Grupo_Contable = ACT_Grupo_Contable;
	}

	public String getACT_Marca() {
		return this.ACT_Marca;
	}

	public void setACT_Marca(String ACT_Marca) {
		this.ACT_Marca = ACT_Marca;
	}

	public String getACT_NIT_Proveedor() {
		return this.ACT_NIT_Proveedor;
	}

	public void setACT_NIT_Proveedor(String ACT_NIT_Proveedor) {
		this.ACT_NIT_Proveedor = ACT_NIT_Proveedor;
	}

	public long getACT_No_Acta_Entrega() {
		return this.ACT_No_Acta_Entrega;
	}

	public void setACT_No_Acta_Entrega(long ACT_No_Acta_Entrega) {
		this.ACT_No_Acta_Entrega = ACT_No_Acta_Entrega;
	}

	public long getACT_No_Comprobante_Gasto() {
		return this.ACT_No_Comprobante_Gasto;
	}

	public void setACT_No_Comprobante_Gasto(long ACT_No_Comprobante_Gasto) {
		this.ACT_No_Comprobante_Gasto = ACT_No_Comprobante_Gasto;
	}

	public String getACT_No_Contrato_Mantenimiento() {
		return this.ACT_No_Contrato_Mantenimiento;
	}

	public void setACT_No_Contrato_Mantenimiento(String ACT_No_Contrato_Mantenimiento) {
		this.ACT_No_Contrato_Mantenimiento = ACT_No_Contrato_Mantenimiento;
	}

	public String getACT_No_Folio_Real() {
		return this.ACT_No_Folio_Real;
	}

	public void setACT_No_Folio_Real(String ACT_No_Folio_Real) {
		this.ACT_No_Folio_Real = ACT_No_Folio_Real;
	}

	public String getACT_No_Garantia() {
		return this.ACT_No_Garantia;
	}

	public void setACT_No_Garantia(String ACT_No_Garantia) {
		this.ACT_No_Garantia = ACT_No_Garantia;
	}

	public long getACT_No_Informe_Baja() {
		return this.ACT_No_Informe_Baja;
	}

	public void setACT_No_Informe_Baja(long ACT_No_Informe_Baja) {
		this.ACT_No_Informe_Baja = ACT_No_Informe_Baja;
	}

	public String getACT_No_Poliza_Seguro() {
		return this.ACT_No_Poliza_Seguro;
	}

	public void setACT_No_Poliza_Seguro(String ACT_No_Poliza_Seguro) {
		this.ACT_No_Poliza_Seguro = ACT_No_Poliza_Seguro;
	}

	public String getACT_No_RUAT() {
		return this.ACT_No_RUAT;
	}

	public void setACT_No_RUAT(String ACT_No_RUAT) {
		this.ACT_No_RUAT = ACT_No_RUAT;
	}

	public String getACT_No_Serie() {
		return this.ACT_No_Serie;
	}

	public void setACT_No_Serie(String ACT_No_Serie) {
		this.ACT_No_Serie = ACT_No_Serie;
	}

	public String getACT_Nombre_Activo() {
		return this.ACT_Nombre_Activo;
	}

	public void setACT_Nombre_Activo(String ACT_Nombre_Activo) {
		this.ACT_Nombre_Activo = ACT_Nombre_Activo;
	}

	public short getACT_Organismo_Financiador() {
		return this.ACT_Organismo_Financiador;
	}

	public void setACT_Organismo_Financiador(short ACT_Organismo_Financiador) {
		this.ACT_Organismo_Financiador = ACT_Organismo_Financiador;
	}

	public short getACT_Tipo_Activo() {
		return this.ACT_Tipo_Activo;
	}

	public void setACT_Tipo_Activo(short ACT_Tipo_Activo) {
		this.ACT_Tipo_Activo = ACT_Tipo_Activo;
	}

	public BigDecimal getACT_Tipo_Cambio_Dolar() {
		return this.ACT_Tipo_Cambio_Dolar;
	}

	public void setACT_Tipo_Cambio_Dolar(BigDecimal ACT_Tipo_Cambio_Dolar) {
		this.ACT_Tipo_Cambio_Dolar = ACT_Tipo_Cambio_Dolar;
	}

	public BigDecimal getACT_Tipo_Cambio_UFV() {
		return this.ACT_Tipo_Cambio_UFV;
	}

	public void setACT_Tipo_Cambio_UFV(BigDecimal ACT_Tipo_Cambio_UFV) {
		this.ACT_Tipo_Cambio_UFV = ACT_Tipo_Cambio_UFV;
	}

	public int getACT_Ubicacion_Fisica_Activo() {
		return this.ACT_Ubicacion_Fisica_Activo;
	}

	public void setACT_Ubicacion_Fisica_Activo(int ACT_Ubicacion_Fisica_Activo) {
		this.ACT_Ubicacion_Fisica_Activo = ACT_Ubicacion_Fisica_Activo;
	}

	public String getACT_Ubicacion_Imagen() {
		return this.ACT_Ubicacion_Imagen;
	}

	public void setACT_Ubicacion_Imagen(String ACT_Ubicacion_Imagen) {
		this.ACT_Ubicacion_Imagen = ACT_Ubicacion_Imagen;
	}

	public BigDecimal getACT_Valor() {
		return this.ACT_Valor_Compra;
	}

	public void setACT_Valor(BigDecimal ACT_Valor) {
		this.ACT_Valor_Compra = ACT_Valor;
	}

	public BigDecimal getACT_Valor_Neto() {
		return this.ACT_Valor_Neto;
	}

	public void setACT_Valor_Neto(BigDecimal ACT_Valor_Neto) {
		this.ACT_Valor_Neto = ACT_Valor_Neto;
	}

	public int getACT_Vida_Util() {
		return this.ACT_Vida_Util;
	}

	public void setACT_Vida_Util(int ACT_Vida_Util) {
		this.ACT_Vida_Util = ACT_Vida_Util;
	}

}