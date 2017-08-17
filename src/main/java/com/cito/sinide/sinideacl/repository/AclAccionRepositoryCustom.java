package com.cito.sinide.sinideacl.repository;

import java.util.Hashtable;
import java.util.List;

public interface AclAccionRepositoryCustom {
	public boolean puede(Long idUsuario, String accion, String clase, Long id, Hashtable<String, Long> info);

	public List<String> getPermisos(Long idUsuario, String clase, Long id, Hashtable<String, Long> inf);

	public List<Long> getIdsPermiso(Long idUsuario, String accion, String clase, Hashtable<String, Long> info);
}
