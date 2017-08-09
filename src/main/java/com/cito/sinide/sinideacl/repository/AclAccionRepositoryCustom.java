package com.cito.sinide.sinideacl.repository;

import java.util.Hashtable;

public interface AclAccionRepositoryCustom {
	public boolean puede(Long idUsuario, String accion, String clase, Long id,
			Hashtable<String, Long> info);
}
