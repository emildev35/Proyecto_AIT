package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the Activos database table.
 * 
 */
@Entity
@Table(name = "Activos")
@NamedQuery(name = "Activos.findAll", query = "SELECT a FROM Activos a")
public class Activos implements Serializable {
	private static final long serialVersionUID = 1L;
	private short ACT_Dependencia;

	@Id
	private String ACT_Codigo_Activo;

	private String ACT_Auxiliar_Contable;

	private String ACT_CI_Empleado_Asignado;

	private Date ACT_Fecha_Asignacion;

	private Date ACT_Fecha_Baja;

	private Date ACT_Fecha_Comodato;

	private Date ACT_Fecha_Compra;

	private Date ACT_Fecha_Incorporacion;

	private Date ACT_Fecha_Registro;

	private Date ACT_Fecha_Vencimiento_Garantia;

	private Date ACT_Fecha_Vencimiento_Mantenimiento;

	private Date ACT_Fecha_Vencimiento_Seguro;

	private int ACT_Fuente_Financiamiento;

	private String ACT_Grupo_Contable;

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

	private String ACT_NIT_Proveedor;

	private short ACT_Tipo_Activo;

	private BigDecimal ACT_Tipo_Cambio_Dolar;
	private BigDecimal ACT_Tipo_Cambio_UFV;
	private BigDecimal ACT_Actualizacion_Acumulada;
	private BigDecimal ACT_Depresiacion_Acumulada;
	private BigDecimal ACT_Valor_Gestion_Anterior;

	private int ACT_Ubicacion_Fisica_Activo;

	private String ACT_Ubicacion_Imagen;

	private BigDecimal ACT_Valor;
	private BigDecimal ACT_Valor_Neto;

	private int ACT_Vida_Util;

	private String ACT_Marca;

	public Activos() {
	}

	public short getACT_Dependencia() {
		return this.ACT_Dependencia;
	}

	public void setACT_Dependencia(short ACT_Dependencia) {
		this.ACT_Dependencia = ACT_Dependencia;
	}

	public String getACT_Codigo_Activo() {
		return this.ACT_Codigo_Activo;
	}

	public void setACT_Codigo_Activo(String ACT_Codigo_Activo) {
		this.ACT_Codigo_Activo = ACT_Codigo_Activo;
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

	public Date getACT_Fecha_Asignacion() {
		return this.ACT_Fecha_Asignacion;
	}

	public void setACT_Fecha_Asignacion(Date ACT_Fecha_Asignacion) {
		this.ACT_Fecha_Asignacion = ACT_Fecha_Asignacion;
	}

	public Date getACT_Fecha_Baja() {
		return this.ACT_Fecha_Baja;
	}

	public void setACT_Fecha_Baja(Date ACT_Fecha_Baja) {
		this.ACT_Fecha_Baja = ACT_Fecha_Baja;
	}

	public Date getACT_Fecha_Comodato() {
		return this.ACT_Fecha_Comodato;
	}

	public void setACT_Fecha_Comodato(Date ACT_Fecha_Comodato) {
		this.ACT_Fecha_Comodato = ACT_Fecha_Comodato;
	}

	public Date getACT_Fecha_Compra() {
		return this.ACT_Fecha_Compra;
	}

	public void setACT_Fecha_Compra(Date ACT_Fecha_Compra) {
		this.ACT_Fecha_Compra = ACT_Fecha_Compra;
	}

	public Date getACT_Fecha_Incorporacion() {
		return this.ACT_Fecha_Incorporacion;
	}

	public void setACT_Fecha_Incorporacion(Date ACT_Fecha_Incorporacion) {
		this.ACT_Fecha_Incorporacion = ACT_Fecha_Incorporacion;
	}

	public Date getACT_Fecha_Registro() {
		return this.ACT_Fecha_Registro;
	}

	public void setACT_Fecha_Registro(Date ACT_Fecha_Registro) {
		this.ACT_Fecha_Registro = ACT_Fecha_Registro;
	}

	public Date getACT_Fecha_Vencimiento_Garantia() {
		return this.ACT_Fecha_Vencimiento_Garantia;
	}

	public void setACT_Fecha_Vencimiento_Garantia(Date ACT_Fecha_Vencimiento_Garantia) {
		this.ACT_Fecha_Vencimiento_Garantia = ACT_Fecha_Vencimiento_Garantia;
	}

	public Date getACT_Fecha_Vencimiento_Mantenimiento() {
		return this.ACT_Fecha_Vencimiento_Mantenimiento;
	}

	public void setACT_Fecha_Vencimiento_Mantenimiento(Date ACT_Fecha_Vencimiento_Mantenimiento) {
		this.ACT_Fecha_Vencimiento_Mantenimiento = ACT_Fecha_Vencimiento_Mantenimiento;
	}

	public Date getACT_Fecha_Vencimiento_Seguro() {
		return this.ACT_Fecha_Vencimiento_Seguro;
	}

	public void setACT_Fecha_Vencimiento_Seguro(Date ACT_Fecha_Vencimiento_Seguro) {
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

	public String getACT_NIT_Proveedor() {
		return this.ACT_NIT_Proveedor;
	}

	public void setACT_NIT_Proveedor(String ACT_NIT_Proveedor) {
		this.ACT_NIT_Proveedor = ACT_NIT_Proveedor;
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
		return ACT_Tipo_Cambio_UFV;
	}

	public void setACT_Tipo_Cambio_UFV(BigDecimal aCT_Tipo_Cambio_UFV) {
		ACT_Tipo_Cambio_UFV = aCT_Tipo_Cambio_UFV;
	}

	public BigDecimal getACT_Actualizacion_Acumulada() {
		return ACT_Actualizacion_Acumulada;
	}

	public void setACT_Actualizacion_Acumulada(BigDecimal aCT_Actualizacion_Acumulada) {
		ACT_Actualizacion_Acumulada = aCT_Actualizacion_Acumulada;
	}

	public BigDecimal getACT_Depresiacion_Acumulada() {
		return ACT_Depresiacion_Acumulada;
	}

	public void setACT_Depresiacion_Acumulada(BigDecimal aCT_Depresiacion_Acumulada) {
		ACT_Depresiacion_Acumulada = aCT_Depresiacion_Acumulada;
	}

	public BigDecimal getACT_Valor_Gestion_Anterior() {
		return ACT_Valor_Gestion_Anterior;
	}

	public void setACT_Valor_Gestion_Anterior(BigDecimal aCT_Valor_Gestion_Anterior) {
		ACT_Valor_Gestion_Anterior = aCT_Valor_Gestion_Anterior;
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
		return this.ACT_Valor;
	}

	public void setACT_Valor(BigDecimal ACT_Valor) {
		this.ACT_Valor = ACT_Valor;
	}

	public BigDecimal getACT_Valor_Neto() {
		return ACT_Valor_Neto;
	}

	public void setACT_Valor_Neto(BigDecimal aCT_Valor_Neto) {
		ACT_Valor_Neto = aCT_Valor_Neto;
	}

	public int getACT_Vida_Util() {
		return this.ACT_Vida_Util;
	}

	public void setACT_Vida_Util(int ACT_Vida_Util) {
		this.ACT_Vida_Util = ACT_Vida_Util;
	}

	public String getACT_Marca() {
		return ACT_Marca;
	}

	public void setACT_Marca(String aCT_Marca) {
		ACT_Marca = aCT_Marca;
	}

}