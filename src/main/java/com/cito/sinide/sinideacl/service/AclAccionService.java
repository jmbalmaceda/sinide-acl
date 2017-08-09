package com.cito.sinide.sinideacl.service;

import java.util.Hashtable;

public interface AclAccionService {
	public boolean puede(Long idUsuario, String accion, String clase, Long id, Hashtable<String, Long> info);
}
