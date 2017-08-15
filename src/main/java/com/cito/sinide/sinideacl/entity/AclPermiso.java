package com.cito.sinide.sinideacl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acl_permiso")
public class AclPermiso {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "clase")
	private String clase;
	
	@Column(name = "permiso")
	private String permiso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getPermiso() {
		return permiso;
	}

	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	
	@Override
	public boolean equals(Object permisoComparacion) {
		String permisoComp = ((AclPermiso)permisoComparacion).getPermiso();
		String claseComp = ((AclPermiso)permisoComparacion).getClase();
		if (permisoComp.equals(permiso) && claseComp.equals(clase))
			return true;
		return false;
	}
}
