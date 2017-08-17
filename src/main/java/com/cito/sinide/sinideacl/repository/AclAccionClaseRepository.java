package com.cito.sinide.sinideacl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cito.sinide.sinideacl.entity.AclAccionClase;

public interface AclAccionClaseRepository extends JpaRepository<AclAccionClase, Long> {
	public List<AclAccionClase> findByClase(String clase);
	
	public List<AclAccionClase> findByClaseAndAccion(String clase, String accion);

}
