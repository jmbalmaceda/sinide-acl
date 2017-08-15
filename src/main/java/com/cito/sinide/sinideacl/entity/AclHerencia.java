package com.cito.sinide.sinideacl.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "acl_herencia_permisos")
public class AclHerencia {
	@Id
	@Column(name="id_herencia")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idHerencia;
	
	@ManyToOne
	@JoinColumn(name = "permiso_padre", referencedColumnName = "id", nullable = false)
	private AclPermiso permisoPadre;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "aclHerencia", orphanRemoval = true)
	private List<AclPermiso> permisosHeredados;
	
	public Long getIdHerencia() {
		return idHerencia;
	}

	public void setIdHerencia(Long idHerencia) {
		this.idHerencia = idHerencia;
	}
	
	public AclPermiso getPermisoPadre() {
		return permisoPadre;
	}

	public void setPermisoPadre(AclPermiso permisoPadre) {
		this.permisoPadre = permisoPadre;
	}

	public List<AclPermiso> getPermisosHeredados() {
		return permisosHeredados;
	}

	public void setPermisosHeredados(List<AclPermiso> permisosHeredados) {
		for(AclPermiso permiso : permisosHeredados)
			permiso.setAclHerencia(this);
		this.permisosHeredados = permisosHeredados;
	}
}
