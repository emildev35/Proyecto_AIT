package ait.sistemas.proyecto.activos.data.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import ait.sistemas.proyecto.activos.data.service.Impl.TipoCambioImpl;

@SqlResultSetMapping(name = "mapeo-activo", entities = { @EntityResult(entityClass = ActivosModel.class, fields = {
		@FieldResult(name = "ACT_Dependencia_Codigo_Activo", column = "ACT_Dependencia_Codigo_Activo"),
		@FieldResult(name = "ACT_Dependencia", column = "ACT_Dependencia"),
		@FieldResult(name = "ACT_Codigo_Activo", column = "ACT_Codigo_Activo"),
		@FieldResult(name = "ACT_Auxiliar_Contable_ID", column = "ACT_Auxiliar_Contable_ID"),
		@FieldResult(name = "ACT_Auxiliar_Contable", column = "ACT_Auxiliar_Contable"),
		@FieldResult(name = "ACT_CI_Empleado_Asignado", column = "ACT_CI_Empleado_Asignado"),
		@FieldResult(name = "ACT_Fecha_Asignacion", column = "ACT_Fecha_Asignacion"),
		@FieldResult(name = "ACT_Fecha_Baja", column = "ACT_Fecha_Baja"),
		@FieldResult(name = "ACT_Fecha_Comodato", column = "ACT_Fecha_Comodato"),
		@FieldResult(name = "ACT_Fecha_Compra", column = "ACT_Fecha_Compra"),
		@FieldResult(name = "ACT_Fecha_Incorporacion", column = "ACT_Fecha_Incorporacion"),
		@FieldResult(name = "ACT_Fecha_Registro", column = "ACT_Fecha_Registro"),
		@FieldResult(name = "ACT_Fecha_Vencimiento_Garantia", column = "ACT_Fecha_Vencimiento_Garantia"),
		@FieldResult(name = "ACT_Fecha_Vencimiento_Mantenimiento", column = "ACT_Fecha_Vencimiento_Mantenimiento"),
		@FieldResult(name = "ACT_Fecha_Vencimiento_Seguro", column = "ACT_Fecha_Vencimiento_Seguro"),
		@FieldResult(name = "ACT_Fecha_Ultima_Revaloracion", column = "ACT_Fecha_Ultima_Revaloracion"),
		@FieldResult(name = "ACT_Fecha_Ultima_Depreciacion", column = "ACT_Fecha_Ultima_Depreciacion"),
		@FieldResult(name = "ACT_Fuente_Financiamiento_ID", column = "ACT_Fuente_Financiamiento_ID"),
		@FieldResult(name = "ACT_Fuente_Financiamiento", column = "ACT_Fuente_Financiamiento"),
		@FieldResult(name = "ACT_Grupo_Contable_ID", column = "ACT_Grupo_Contable_ID"),
		@FieldResult(name = "ACT_Grupo_Contable", column = "ACT_Grupo_Contable"),
		@FieldResult(name = "ACT_No_Acta_Entrega", column = "ACT_No_Acta_Entrega"),
		@FieldResult(name = "ACT_No_Comprobante_Gasto", column = "ACT_No_Comprobante_Gasto"),
		@FieldResult(name = "ACT_No_Contrato_Mantenimiento", column = "ACT_No_Contrato_Mantenimiento"),
		@FieldResult(name = "ACT_No_Folio_Real", column = "ACT_No_Folio_Real"),
		@FieldResult(name = "ACT_No_Garantia", column = "ACT_No_Garantia"),
		@FieldResult(name = "ACT_No_Informe_Baja", column = "ACT_No_Informe_Baja"),
		@FieldResult(name = "ACT_No_Resolucion_Baja", column = "ACT_No_Resolucion_Baja"),
		@FieldResult(name = "ACT_No_Poliza_Seguro", column = "ACT_No_Poliza_Seguro"),
		@FieldResult(name = "ACT_No_RUAT", column = "ACT_No_RUAT"),
		@FieldResult(name = "ACT_No_Serie", column = "ACT_No_Serie"),
		@FieldResult(name = "ACT_Nombre_Activo", column = "ACT_Nombre_Activo"),
		@FieldResult(name = "ACT_Organismo_Financiador_ID", column = "ACT_Organismo_Financiador_ID"),
		@FieldResult(name = "ACT_Organismo_Financiador", column = "ACT_Organismo_Financiador"),
		@FieldResult(name = "ACT_NIT_Proveedor", column = "ACT_NIT_Proveedor"),
		@FieldResult(name = "ACT_Nombre_Proveedor", column = "ACT_Nombre_Proveedor"),
		@FieldResult(name = "ACT_Tipo_Activo", column = "ACT_Tipo_Activo"),
		@FieldResult(name = "ACT_Tipo_Cambio_Dolar", column = "ACT_Tipo_Cambio_Dolar"),
		@FieldResult(name = "ACT_Tipo_Cambio_UFV", column = "ACT_Tipo_Cambio_UFV"),
		@FieldResult(name = "ACT_Actualizacion_Acumulada_Gestion_Anterior", column = "ACT_Actualizacion_Acumulada_Gestion_Anterior"),
		@FieldResult(name = "	", column = "ACT_Actualizacion_Acumulada"),
		@FieldResult(name = "ACT_Depreciacion_Acumulada_Gestion_Anterior", column = "ACT_Depreciacion_Acumulada_Gestion_Anterior"),
		@FieldResult(name = "ACT_Depresiacion_Acumulada", column = "ACT_Depresiacion_Acumulada"),
		@FieldResult(name = "ACT_CA", column = "ACT_CA"), @FieldResult(name = "ACT_DAA", column = "ACT_DAA"),
		@FieldResult(name = "ACT_Ubicacion_Fisica_Activo_ID", column = "ACT_Ubicacion_Fisica_Activo_ID"),
		@FieldResult(name = "ACT_Ubicacion_Fisica_Activo", column = "ACT_Ubicacion_Fisica_Activo"),
		@FieldResult(name = "ACT_Ubicacion_Imagen", column = "ACT_Ubicacion_Imagen"),
		@FieldResult(name = "ACT_Valor_Compra", column = "ACT_Valor_Compra"),
		@FieldResult(name = "ACT_Valor_Neto", column = "ACT_Valor_Neto"),
		@FieldResult(name = "ACT_Vida_Util", column = "ACT_Vida_Util"), @FieldResult(name = "ACT_Marca", column = "ACT_Marca"),
		@FieldResult(name = "ACT_Vida_Residual", column = "ACT_Vida_Residual"),
		@FieldResult(name = "ACT_Marca", column = "ACT_Marca"),
		@FieldResult(name = "ACT_Motivo_Baja", column = "ACT_Motivo_Baja"),
		@FieldResult(name = "ACT_Partidas_Presupuestarias", column = "ACT_Partidas_Presupuestarias"),
		@FieldResult(name = "ACT_Nombre_Empleado", column = "ACT_Nombre_Empleado"),
		@FieldResult(name = "ACT_APaterno_Empleado", column = "ACT_APaterno_Empleado"),
		@FieldResult(name = "ACT_AMaterno_Empleado", column = "ACT_AMaterno_Empleado"),
		@FieldResult(name = "ACT_Inmueble", column = "ACT_Inmueble")

}) })
@Entity
public class ActivosModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String ACT_Dependencia_Codigo_Activo;
	private short ACT_Dependencia_ID;
	private String ACT_Dependencia;
	private String ACT_Codigo_Activo;
	private String ACT_Auxiliar_Contable_ID;
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
	private Date ACT_Fecha_Ultima_Revalorizacion;
	private Date ACT_Fecha_Ultima_Depreciacion;
	private int ACT_Fuente_Financiamiento_ID;
	private String ACT_Fuente_Financiamiento;
	private String ACT_Grupo_Contable_ID;
	private String ACT_Grupo_Contable;
	private long ACT_No_Acta_Entrega;
	private long ACT_No_Comprobante_Gasto;
	private String ACT_No_Contrato_Mantenimiento;
	private String ACT_No_Folio_Real;
	private String ACT_No_Garantia;
	private long ACT_No_Informe_Baja;
	private String ACT_No_Resolucion_Baja;
	private String ACT_No_Poliza_Seguro;
	private String ACT_No_RUAT;
	private String ACT_No_Serie;
	private String ACT_Nombre_Activo;
	private short ACT_Organismo_Financiador_ID;
	private String ACT_Organismo_Financiador;
	private String ACT_NIT_Proveedor;
	private String ACT_Nombre_Proveedor;
	private short ACT_Tipo_Activo;
	private BigDecimal ACT_Tipo_Cambio_Dolar;
	private BigDecimal ACT_Tipo_Cambio_UFV;
	private BigDecimal ACT_Actualizacion_Acumulada_Gestion_Anterior;
	private BigDecimal ACT_Actualizacion_Acumulada;
	private BigDecimal ACT_Depresiacion_Acumulada;
	private BigDecimal ACT_Depreciacion_Acumulada_Gestion_Anterior;
	private BigDecimal ACT_Actualizacion_Gestion_Actual;
	private BigDecimal ACT_Depreciacion_Gestion_Actual;
	private BigDecimal ACT_DAA;
	private BigDecimal ACT_CA;
	private int ACT_Ubicacion_Fisica_Activo_ID;
	private String ACT_Ubicacion_Fisica_Activo;
	private String ACT_Ubicacion_Imagen;
	private BigDecimal ACT_Valor_Compra;
	private BigDecimal ACT_Valor_Neto;
	private int ACT_Vida_Util;
	private int ACT_Vida_Residual;
	private String ACT_Marca;
	private String ACT_Motivo_Baja;
	private String ACT_Partidas_Presupuestarias;
	private String ACT_Nombre_Empleado;
	private String ACT_APaterno_Empleado;
	private String ACT_AMaterno_Empleado;
	private String ACT_Inmueble;
	
	public ActivosModel() {
	}
	
	public String getACT_Dependencia_Codigo_Activo() {
		return ACT_Dependencia_Codigo_Activo;
	}
	
	public void setACT_Dependencia_Codigo_Activo(String aCT_Dependencia_Codigo_Activo) {
		ACT_Dependencia_Codigo_Activo = aCT_Dependencia_Codigo_Activo;
	}
	
	public short getACT_Dependencia_ID() {
		return this.ACT_Dependencia_ID;
	}
	
	public void setACT_Dependencia_ID(short ACT_Dependencia_ID) {
		this.ACT_Dependencia_ID = ACT_Dependencia_ID;
	}
	
	public String getACT_Dependencia() {
		return ACT_Dependencia;
	}
	
	public void setACT_Dependencia(String aCT_Dependencia) {
		ACT_Dependencia = aCT_Dependencia;
	}
	
	public String getACT_Codigo_Activo() {
		return this.ACT_Codigo_Activo;
	}
	
	public void setACT_Codigo_Activo(String ACT_Codigo_Activo) {
		this.ACT_Codigo_Activo = ACT_Codigo_Activo;
	}
	
	public String getACT_Auxiliar_Contable_ID() {
		return ACT_Auxiliar_Contable_ID;
	}
	
	public void setACT_Auxiliar_Contable_ID(String aCT_Auxiliar_Contable_ID) {
		ACT_Auxiliar_Contable_ID = aCT_Auxiliar_Contable_ID;
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
	
	public int getACT_Fuente_Financiamiento_ID() {
		return this.ACT_Fuente_Financiamiento_ID;
	}
	
	public void setACT_Fuente_Financiamiento_ID(int ACT_Fuente_Financiamiento_ID) {
		this.ACT_Fuente_Financiamiento_ID = ACT_Fuente_Financiamiento_ID;
	}
	
	public String getACT_Fuente_Financiamiento() {
		return ACT_Fuente_Financiamiento;
	}
	
	public void setACT_Fuente_Financiamiento(String aCT_Fuente_Financiamiento) {
		ACT_Fuente_Financiamiento = aCT_Fuente_Financiamiento;
	}
	
	public String getACT_Grupo_Contable_ID() {
		return ACT_Grupo_Contable_ID;
	}
	
	public void setACT_Grupo_Contable_ID(String aCT_Grupo_Contable_ID) {
		ACT_Grupo_Contable_ID = aCT_Grupo_Contable_ID;
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
	
	
	public Date getACT_Fecha_Ultima_Revalorizacion() {
		return ACT_Fecha_Ultima_Revalorizacion;
	}

	public void setACT_Fecha_Ultima_Revalorizacion(Date aCT_Fecha_Ultima_Revalorizacion) {
		ACT_Fecha_Ultima_Revalorizacion = aCT_Fecha_Ultima_Revalorizacion;
	}

	public Date getACT_Fecha_Ultima_Depreciacion() {
		return ACT_Fecha_Ultima_Depreciacion;
	}

	public void setACT_Fecha_Ultima_Depreciacion(Date aCT_Fecha_Ultima_Depreciacion) {
		ACT_Fecha_Ultima_Depreciacion = aCT_Fecha_Ultima_Depreciacion;
	}

	public BigDecimal getACT_Actualizacion_Gestion_Actual() {
		return ACT_Actualizacion_Gestion_Actual;
	}

	public void setACT_Actualizacion_Gestion_Actual(BigDecimal aCT_Actualizacion_Gestion_Actual) {
		ACT_Actualizacion_Gestion_Actual = aCT_Actualizacion_Gestion_Actual;
	}

	public BigDecimal getACT_Depreciacion_Gestion_Actual() {
		return ACT_Depreciacion_Gestion_Actual;
	}

	public void setACT_Depreciacion_Gestion_Actual(BigDecimal aCT_Depreciacion_Gestion_Actual) {
		ACT_Depreciacion_Gestion_Actual = aCT_Depreciacion_Gestion_Actual;
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
	
	public String getACT_No_Resolucion_Baja() {
		return ACT_No_Resolucion_Baja;
	}
	
	public void setACT_No_Resolucion_Baja(String aCT_No_Resolucion_Baja) {
		ACT_No_Resolucion_Baja = aCT_No_Resolucion_Baja;
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
	
	public short getACT_Organismo_Financiador_ID() {
		return this.ACT_Organismo_Financiador_ID;
	}
	
	public void setACT_Organismo_Financiador_ID(short ACT_Organismo_Financiador_ID) {
		this.ACT_Organismo_Financiador_ID = ACT_Organismo_Financiador_ID;
	}
	
	public String getACT_Organismo_Financiador() {
		return ACT_Organismo_Financiador;
	}
	
	public void setACT_Organismo_Financiador(String aCT_Organismo_Financiador) {
		ACT_Organismo_Financiador = aCT_Organismo_Financiador;
	}
	
	public String getACT_NIT_Proveedor() {
		return this.ACT_NIT_Proveedor;
	}
	
	public void setACT_NIT_Proveedor(String ACT_NIT_Proveedor) {
		this.ACT_NIT_Proveedor = ACT_NIT_Proveedor;
	}
	
	public String getACT_Nombre_Proveedor() {
		return ACT_Nombre_Proveedor;
	}
	
	public void setACT_Nombre_Proveedor(String aCT_Nombre_Proveedor) {
		ACT_Nombre_Proveedor = aCT_Nombre_Proveedor;
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
	
	public BigDecimal getACT_Actualizacion_Acumulada_Gestion_Anterior() {
		return ACT_Actualizacion_Acumulada_Gestion_Anterior;
	}
	
	public void setACT_Actualizacion_Acumulada_Gestion_Anterior(BigDecimal aCT_Actualizacion_Acumulada_Gestion_Anterior) {
		ACT_Actualizacion_Acumulada_Gestion_Anterior = aCT_Actualizacion_Acumulada_Gestion_Anterior;
	}
	
	public BigDecimal getACT_Actualizacion_Acumulada() {
		return ACT_Actualizacion_Acumulada;
	}
	
	public void setACT_Actualizacion_Acumulada(BigDecimal aCT_Actualizacion_Acumulada) {
		ACT_Actualizacion_Acumulada = aCT_Actualizacion_Acumulada;
	}
	
	public BigDecimal getACT_Depreciacion_Acumulada_Gestion_Anterior() {
		return ACT_Depreciacion_Acumulada_Gestion_Anterior;
	}
	
	public void setACT_Depreciacion_Acumulada_Gestion_Anterior(BigDecimal aCT_Depreciacion_Acumulada_Gestion_Anterior) {
		ACT_Depreciacion_Acumulada_Gestion_Anterior = aCT_Depreciacion_Acumulada_Gestion_Anterior;
	}
	
	public BigDecimal getACT_Depresiacion_Acumulada() {
		return ACT_Depresiacion_Acumulada;
	}
	
	public void setACT_Depresiacion_Acumulada(BigDecimal aCT_Depresiacion_Acumulada) {
		ACT_Depresiacion_Acumulada = aCT_Depresiacion_Acumulada;
	}
	
	public BigDecimal getACT_DAA() {
		return ACT_DAA;
	}
	
	public void setACT_DAA(BigDecimal aCT_DAA) {
		ACT_DAA = aCT_DAA;
	}
	
	public BigDecimal getACT_CA() {
		return ACT_CA;
	}
	
	public void setACT_CA(BigDecimal aCT_CA) {
		ACT_CA = aCT_CA;
	}
	
	public int getACT_Ubicacion_Fisica_Activo_ID() {
		return this.ACT_Ubicacion_Fisica_Activo_ID;
	}
	
	public void setACT_Ubicacion_Fisica_Activo_ID(int ACT_Ubicacion_Fisica_Activo_ID) {
		this.ACT_Ubicacion_Fisica_Activo_ID = ACT_Ubicacion_Fisica_Activo_ID;
	}
	
	public String getACT_Ubicacion_Fisica_Activo() {
		return ACT_Ubicacion_Fisica_Activo;
	}
	
	public void setACT_Ubicacion_Fisica_Activo(String aCT_Ubicacion_Fisica_Activo) {
		ACT_Ubicacion_Fisica_Activo = aCT_Ubicacion_Fisica_Activo;
	}
	
	public String getACT_Ubicacion_Imagen() {
		return this.ACT_Ubicacion_Imagen;
	}
	
	public void setACT_Ubicacion_Imagen(String ACT_Ubicacion_Imagen) {
		this.ACT_Ubicacion_Imagen = ACT_Ubicacion_Imagen;
	}
	
	public BigDecimal getACT_Valor_Compra() {
		return this.ACT_Valor_Compra;
	}
	
	public void setACT_Valor_Compra(BigDecimal ACT_Valor_Compra) {
		this.ACT_Valor_Compra = ACT_Valor_Compra;
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
	
	public int getACT_Vida_Residual() {
		return ACT_Vida_Residual;
	}
	
	public void setACT_Vida_Residual(int aCT_Vida_Residual) {
		ACT_Vida_Residual = aCT_Vida_Residual;
	}
	
	public String getACT_Marca() {
		return ACT_Marca;
	}
	
	public void setACT_Marca(String aCT_Marca) {
		ACT_Marca = aCT_Marca;
	}
	
	public String getACT_Motivo_Baja() {
		return ACT_Motivo_Baja;
	}
	
	public void setACT_Motivo_Baja(String aCT_Motivo_Baja) {
		ACT_Motivo_Baja = aCT_Motivo_Baja;
	}
	
	public String getACT_Partidas_Presupuestarias() {
		return ACT_Partidas_Presupuestarias;
	}
	
	public void setACT_Partidas_Presupuestarias(String aCT_Partidas_Presupuestarias) {
		ACT_Partidas_Presupuestarias = aCT_Partidas_Presupuestarias;
	}
	
	public String getACT_Nombre_Empleado() {
		return ACT_Nombre_Empleado;
	}
	
	public void setACT_Nombre_Empleado(String aCT_Nombre_Empleado) {
		ACT_Nombre_Empleado = aCT_Nombre_Empleado;
	}
	
	public String getACT_APaterno_Empleado() {
		return ACT_APaterno_Empleado;
	}
	
	public void setACT_APaterno_Empleado(String aCT_APaterno_Empleado) {
		ACT_APaterno_Empleado = aCT_APaterno_Empleado;
	}
	
	public String getACT_AMaterno_Empleado() {
		return ACT_AMaterno_Empleado;
	}
	
	public void setACT_AMaterno_Empleado(String aCT_AMaterno_Empleado) {
		ACT_AMaterno_Empleado = aCT_AMaterno_Empleado;
	}
	
	public String getACT_Inmueble() {
		return ACT_Inmueble;
	}
	
	public void setACT_Inmueble(String aCT_Inmueble) {
		ACT_Inmueble = aCT_Inmueble;
	}
	
	
	public boolean Actualizar(java.util.Date fecha_actual) {
		
		if (fecha_actual.getTime() < this.ACT_Fecha_Incorporacion.getTime()) {
			
			this.ACT_Actualizacion_Acumulada = new BigDecimal(0);
			this.ACT_Depresiacion_Acumulada = new BigDecimal(0);
			this.ACT_Valor_Neto = new BigDecimal(0);
			return false;
		}
		
		TipoCambioImpl tipocambioimpl = new TipoCambioImpl();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.ACT_Fecha_Incorporacion);
		calendar.add(Calendar.YEAR, this.ACT_Vida_Util);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		java.util.Date fecha_total_depresiacion = calendar.getTime();
		
		if (fecha_total_depresiacion.getTime() < fecha_actual.getTime()) {
			fecha_actual = fecha_total_depresiacion;
		}
		double CAI = Double.parseDouble(this.ACT_Valor_Compra.toString());
		double ufvi = Double.parseDouble(tipocambioimpl.getTipoCambioUFV(this.ACT_Fecha_Incorporacion).getTipo_cambio()
				.toString());
		double ufvf = Double.parseDouble(tipocambioimpl.getTipoCambioUFV(new Date(fecha_actual.getTime())).getTipo_cambio()
				.toString());
		double AG = CAI * ((ufvf / ufvi) - 1);
		double CA = AG + CAI;
		
//		double DAI = 0;
		double division = ufvf / ufvi;
		
		BigDecimal bd = new BigDecimal(division);
		
		division = bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		
//		double ADA = DAI * ((ufvf / ufvi) - 1);
		
		/**
		 * Tiempo en dias desde la Fecha de Incorporacion hasta la Fecha de
		 * Actual o Fecha de Depresiacion Total
		 */
		long diff = 0;
		if (fecha_total_depresiacion.getTime() < fecha_actual.getTime()) {
			diff = fecha_total_depresiacion.getTime() - ACT_Fecha_Incorporacion.getTime();
		} else {
			diff = fecha_actual.getTime() - ACT_Fecha_Incorporacion.getTime();
		}
		long dias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		/**
		 * Tiempo en dias desde la Fecha de Incorporacion hasta la Fecha de
		 * Depresiacion Total
		 */
		long diff_totales = 0;
		diff_totales = fecha_total_depresiacion.getTime() - ACT_Fecha_Incorporacion.getTime();
		long dias_totales = TimeUnit.DAYS.convert(diff_totales, TimeUnit.MILLISECONDS);
		
		CAI = CA;
		double DG = CAI * dias / dias_totales;
		
		double DA = 0 + DG;
		if (CA == DA) {
			DA--;
		}
		
		double NETO = CA - DA;
		
		@SuppressWarnings("deprecation")
		int vida_util_remanente = (this.ACT_Fecha_Incorporacion.getYear() + this.ACT_Vida_Util) - fecha_actual.getYear();
		this.ACT_Vida_Residual = vida_util_remanente < 0 ? 0 : vida_util_remanente;
		this.ACT_Actualizacion_Acumulada = new BigDecimal(CA).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.ACT_Depresiacion_Acumulada = new BigDecimal(DA).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.ACT_Valor_Neto = new BigDecimal(NETO).setScale(2, BigDecimal.ROUND_HALF_UP);
		return true;
	}
}