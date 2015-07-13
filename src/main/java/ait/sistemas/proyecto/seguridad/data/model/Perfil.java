package ait.sistemas.proyecto.seguridad.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;


/**
 * The persistent class for the Perfiles database table.
 * 
 */
@Entity
@Table(name="Perfiles")
@NamedQuery(name="Perfil.findAll", query="SELECT p FROM Perfil p")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int PRF_Id_Perfil;

	private Date PRF_Fecha_Registro;

	private String PRF_Nombre_Perfil;

	//bi-directional many-to-one association to OpcionesXPerfil
	@OneToMany(mappedBy="perfil")
	private List<OpcionesXPerfil> opcionesXperfils;

	//bi-directional many-to-one association to Permiso
	@OneToMany(mappedBy="perfil")
	private List<Permiso> permisos;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="perfil")
	private List<Usuario> usuarios;

	public Perfil() {
	}

	public int getPRF_Id_Perfil() {
		return this.PRF_Id_Perfil;
	}

	public void setPRF_Id_Perfil(int PRF_Id_Perfil) {
		this.PRF_Id_Perfil = PRF_Id_Perfil;
	}

	public Date getPRF_Fecha_Registro() {
		return this.PRF_Fecha_Registro;
	}

	public void setPRF_Fecha_Registro(Date PRF_Fecha_Registro) {
		this.PRF_Fecha_Registro = PRF_Fecha_Registro;
	}

	public String getPRF_Nombre_Perfil() {
		return this.PRF_Nombre_Perfil;
	}

	public void setPRF_Nombre_Perfil(String PRF_Nombre_Perfil) {
		this.PRF_Nombre_Perfil = PRF_Nombre_Perfil;
	}

	public List<OpcionesXPerfil> getOpcionesXperfils() {
		return this.opcionesXperfils;
	}

	public void setOpcionesXperfils(List<OpcionesXPerfil> opcionesXperfils) {
		this.opcionesXperfils = opcionesXperfils;
	}

	public OpcionesXPerfil addOpcionesXperfil(OpcionesXPerfil opcionesXperfil) {
		getOpcionesXperfils().add(opcionesXperfil);
		opcionesXperfil.setPerfil(this);

		return opcionesXperfil;
	}

	public OpcionesXPerfil removeOpcionesXperfil(OpcionesXPerfil opcionesXperfil) {
		getOpcionesXperfils().remove(opcionesXperfil);
		opcionesXperfil.setPerfil(null);

		return opcionesXperfil;
	}

	public List<Permiso> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	public Permiso addPermiso(Permiso permiso) {
		getPermisos().add(permiso);
		permiso.setPerfil(this);

		return permiso;
	}

	public Permiso removePermiso(Permiso permiso) {
		getPermisos().remove(permiso);
		permiso.setPerfil(null);

		return permiso;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setPerfil(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setPerfil(null);

		return usuario;
	}

}