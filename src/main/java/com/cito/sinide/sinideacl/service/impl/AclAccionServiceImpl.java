package com.cito.sinide.sinideacl.service.impl;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;

import com.cito.sinide.sinideacl.repository.AclAccionRepository;
import com.cito.sinide.sinideacl.service.AclAccionService;

public class AclAccionServiceImpl implements AclAccionService {
	@Autowired
	private AclAccionRepository accionRepository;
	
	@Override
	public boolean puede(Long idUsuario, String accion, String clase, Long id,
			Hashtable<String, Long> info) {
		
		
		
		return false;
	}

}
