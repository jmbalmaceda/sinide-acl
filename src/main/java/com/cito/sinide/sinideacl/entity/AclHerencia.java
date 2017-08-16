package com.cito.sinide.sinideacl.entity;

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
	@Column(name = "id_herencia")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idHerencia;

	@Column(name = "permiso_padre")
	private String permisoPadre;

	@Column(name = "permiso_heredado")
	private String permisoHeredado;

	public Long getIdHerencia() {
		return idHerencia;
	}

	public void setIdHerencia(Long idHerencia) {
		this.idHerencia = idHerencia;
	}

	public String getPermisoPadre() {
		return permisoPadre;
	}

	public void setPermisoPadre(String permisoPadre) {
		this.permisoPadre = permisoPadre;
	}

	public String getPermisoHeredado() {
		return permisoHeredado;
	}

	public void setPermisoHeredado(String permisoHeredado) {
		this.permisoHeredado = permisoHeredado;
	}

}
