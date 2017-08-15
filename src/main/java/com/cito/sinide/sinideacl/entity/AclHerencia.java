package com.cito.sinide.sinideacl.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acl_herencia_permisos")
public class AclHerencia {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "permiso")
	private AclPermiso permiso;
	
	@Column(name = "herencia")
	private List<AclPermiso> permisosHeredados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AclPermiso getPermiso() {
		return permiso;
	}

	public void setPermiso(AclPermiso permiso) {
		this.permiso = permiso;
	}

	public List<AclPermiso> getPermisosHeredados() {
		return permisosHeredados;
	}

	public void setPermisosHeredados(List<AclPermiso> permisosHeredados) {
		this.permisosHeredados = permisosHeredados;
	}
}
