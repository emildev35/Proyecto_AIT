package ait.sistemas.proyecto.seguridad.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the Usuarios database table.
 * 
 */
@Entity
@Table(name="Usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String USU_Id_Usuario;

	private String USU_CI_Empleado;

	private String USU_Contrasena;

	private Time USU_Fecha_Alta;

	private Time USU_Fecha_Baja;

	//bi-directional many-to-one association to Log_Auditoria
	@OneToMany(mappedBy="usuario")
	private List<Log_Auditoria> logAuditorias;

	//bi-directional many-to-one association to Permiso
	@OneToMany(mappedBy="usuario")
	private List<Permiso> permisos;

	//bi-directional many-to-one association to Perfile
	@ManyToOne
	@JoinColumn(name="USU_Id_Perfil")
	private Perfil perfil;

	public Usuario() {
	}

	public String getUSU_Id_Usuario() {
		return this.USU_Id_Usuario;
	}

	public void setUSU_Id_Usuario(String USU_Id_Usuario) {
		this.USU_Id_Usuario = USU_Id_Usuario;
	}

	public String getUSU_CI_Empleado() {
		return this.USU_CI_Empleado;
	}

	public void setUSU_CI_Empleado(String USU_CI_Empleado) {
		this.USU_CI_Empleado = USU_CI_Empleado;
	}

	public String getUSU_Contrasena() {
		return this.USU_Contrasena;
	}

	public void setUSU_Contrasena(String USU_Contrasena) {
		this.USU_Contrasena = USU_Contrasena;
	}

	public Time getUSU_Fecha_Alta() {
		return this.USU_Fecha_Alta;
	}

	public void setUSU_Fecha_Alta(Time USU_Fecha_Alta) {
		this.USU_Fecha_Alta = USU_Fecha_Alta;
	}

	public Time getUSU_Fecha_Baja() {
		return this.USU_Fecha_Baja;
	}

	public void setUSU_Fecha_Baja(Time USU_Fecha_Baja) {
		this.USU_Fecha_Baja = USU_Fecha_Baja;
	}

	public List<Log_Auditoria> getLogAuditorias() {
		return this.logAuditorias;
	}

	public void setLogAuditorias(List<Log_Auditoria> logAuditorias) {
		this.logAuditorias = logAuditorias;
	}

	public Log_Auditoria addLogAuditoria(Log_Auditoria logAuditoria) {
		getLogAuditorias().add(logAuditoria);
		logAuditoria.setUsuario(this);

		return logAuditoria;
	}

	public Log_Auditoria removeLogAuditoria(Log_Auditoria logAuditoria) {
		getLogAuditorias().remove(logAuditoria);
		logAuditoria.setUsuario(null);

		return logAuditoria;
	}

	public List<Permiso> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	public Permiso addPermiso(Permiso permiso) {
		getPermisos().add(permiso);
		permiso.setUsuario(this);

		return permiso;
	}

	public Permiso removePermiso(Permiso permiso) {
		getPermisos().remove(permiso);
		permiso.setUsuario(null);

		return permiso;
	}

	public Perfil getPerfile() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}