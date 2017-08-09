package com.cito.sinide.sinideacl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cito.sinide.sinideacl.entity.Accion;

public interface AccionRepository extends JpaRepository<Accion, Long>{
	List<Accion> findByIdUser(Long idUser);
}
