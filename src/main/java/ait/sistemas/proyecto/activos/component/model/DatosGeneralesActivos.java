package ait.sistemas.proyecto.activos.component.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;




@SqlResultSetMapping(name = "datos-generales", entities = { @EntityResult(entityClass = DatosGeneralesActivos.class, fields = {
	@FieldResult(name = "id_activo", column = "ACT_Codigo_Activo"),
	@FieldResult(name = "id_dependencia", column = "ACT_Dependencia"),
	@FieldResult(name = "nombre_activo", column = "ACT_Nombre_Activo"),
	@FieldResult(name = "tipo_activo", column = "ACT_Tipo_Activo"),
	@FieldResult(name = "fecha_compra", column = "ACT_Fecha_Compra"),
	@FieldResult(name = "fecha_incorporacion", column = "ACT_Fecha_Incorporacion"),
	@FieldResult(name = "valor", column = "ACT_Valor_Compra"),
	@FieldResult(name = "tipo_cambio_ufv", column = "ACT_Tipo_Cambio_UFV"),
	@FieldResult(name = "tipo_cambio_dolar", column = "ACT_Tipo_Cambio_Dolar"),
	@FieldResult(name = "id_grupo_contable", column = "ACT_Grupo_Contable"),
	@FieldResult(name = "id_auxiliar_contalbe", column = "ACT_Auxiliar_Contable"),
	@FieldResult(name = "vida_util", column = "ACT_Vida_Util"),
	@FieldResult(name = "id_fuente_financiamiento", column = "ACT_Fuente_Financiamiento"),
	@FieldResult(name = "id_organimismo_financiador", column = "ACT_Organismo_Financiador"),
	@FieldResult(name = "id_ubicacion_fisica", column = "ACT_Ubicacion_Fisica_Activo"),
	@FieldResult(name = "fecha_como_dato", column = "ACT_Fecha_Comodato")
	})})
@Entity
public class DatosGeneralesActivos {
	@Id
	private long id_activo;
	private short id_dependencia;
	private String nombre_activo;
	private short tipo_activo;
	private Date fecha_compra;
	private Date fecha_incorporacion;
	private BigDecimal valor;
	private BigDecimal tipo_cambio_ufv;
	private BigDecimal tipo_cambio_dolar;
	private String id_grupo_contable;
	private String id_auxiliar_contalbe;
	private int vida_util;
	private int id_fuente_financiamiento;
	private short id_organimismo_financiador;
	private int id_ubicacion_fisica;
	private Date fecha_como_dato;
	
	public DatosGeneralesActivos() {
	}


	public DatosGeneralesActivos(long id_activo, short id_dependencia, String nombre_activo, short tipo_activo,
			Date fecha_compra, Date fecha_incorporacion, BigDecimal valor, BigDecimal tipo_cambio_ufv,
			BigDecimal tipo_cambio_dolar, String id_grupo_contable, String id_auxiliar_contalbe, int vida_util,
			int id_fuente_financiamiento, short id_organimismo_financiador, int id_ubicacion_fisica, Date fecha_como_dato) {
		this.id_activo = id_activo;
		this.id_dependencia = id_dependencia;
		this.nombre_activo = nombre_activo;
		this.tipo_activo = tipo_activo;
		this.fecha_compra = fecha_compra;
		this.fecha_incorporacion = fecha_incorporacion;
		this.valor = valor;
		this.tipo_cambio_ufv = tipo_cambio_ufv;
		this.tipo_cambio_dolar = tipo_cambio_dolar;
		this.id_grupo_contable = id_grupo_contable;
		this.id_auxiliar_contalbe = id_auxiliar_contalbe;
		this.vida_util = vida_util;
		this.id_fuente_financiamiento = id_fuente_financiamiento;
		this.id_organimismo_financiador = id_organimismo_financiador;
		this.id_ubicacion_fisica = id_ubicacion_fisica;
		this.fecha_como_dato = fecha_como_dato;
	}


	public long getId_activo() {
		return id_activo;
	}

	public void setId_activo(long id_activo) {
		this.id_activo = id_activo;
	}

	public short getId_dependencia() {
		return id_dependencia;
	}

	public void setId_dependencia(short id_dependencia) {
		this.id_dependencia = id_dependencia;
	}

	public String getNombre_activo() {
		return nombre_activo;
	}

	public void setNombre_activo(String nombre_activo) {
		this.nombre_activo = nombre_activo;
	}

	public short getTipo_activo() {
		return tipo_activo;
	}

	public void setTipo_activo(short tipo_activo) {
		this.tipo_activo = tipo_activo;
	}

	public Date getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public Date getFecha_incorporacion() {
		return fecha_incorporacion;
	}


	public void setFecha_incorporacion(Date fecha_incorporacion) {
		this.fecha_incorporacion = fecha_incorporacion;
	}


	public BigDecimal getTipo_cambio_ufv() {
		return tipo_cambio_ufv;
	}


	public void setTipo_cambio_ufv(BigDecimal tipo_cambio_ufv) {
		this.tipo_cambio_ufv = tipo_cambio_ufv;
	}


	public BigDecimal getTipo_cambio_dolar() {
		return tipo_cambio_dolar;
	}


	public void setTipo_cambio_dolar(BigDecimal tipo_cambio_dolar) {
		this.tipo_cambio_dolar = tipo_cambio_dolar;
	}


	public String getId_grupo_contable() {
		return id_grupo_contable;
	}

	public void setId_grupo_contable(String id_grupo_contable) {
		this.id_grupo_contable = id_grupo_contable;
	}

	public String getId_auxiliar_contalbe() {
		return id_auxiliar_contalbe;
	}

	public void setId_auxiliar_contalbe(String id_auxiliar_contalbe) {
		this.id_auxiliar_contalbe = id_auxiliar_contalbe;
	}

	public int getVida_util() {
		return vida_util;
	}

	public void setVida_util(int vida_util) {
		this.vida_util = vida_util;
	}

	public int getId_fuente_financiamiento() {
		return id_fuente_financiamiento;
	}

	public void setId_fuente_financiamiento(int id_fuente_financiamiento) {
		this.id_fuente_financiamiento = id_fuente_financiamiento;
	}

	public short getId_organimismo_financiador() {
		return id_organimismo_financiador;
	}

	public void setId_organimismo_financiador(short id_organimismo_financiador) {
		this.id_organimismo_financiador = id_organimismo_financiador;
	}

	public int getId_ubicacion_fisica() {
		return id_ubicacion_fisica;
	}

	public void setId_ubicacion_fisica(int id_ubicacion_fisica) {
		this.id_ubicacion_fisica = id_ubicacion_fisica;
	}

	public Date getFecha_como_dato() {
		return fecha_como_dato;
	}

	public void setFecha_como_dato(Date fecha_como_dato) {
		this.fecha_como_dato = fecha_como_dato;
	}
	
}
