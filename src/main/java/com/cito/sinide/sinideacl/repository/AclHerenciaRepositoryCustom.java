package com.cito.sinide.sinideacl.repository;

import com.cito.sinide.sinideacl.entity.AclHerencia;

public interface AclHerenciaRepositoryCustom {
	public AclHerencia findByPermiso_claseAndPermiso_permiso(String clase, String permiso);
}
