package com.cito.sinide.sinideacl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cito.sinide.sinideacl.entity.AclAccion;

public interface AccionRepository extends JpaRepository<AclAccion, Long>{
	List<AclAccion> findByIdUser(Long idUser);
}
