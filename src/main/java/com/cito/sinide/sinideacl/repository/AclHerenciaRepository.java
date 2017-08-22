package com.cito.sinide.sinideacl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cito.sinide.sinideacl.entity.AclHerencia;

public interface AclHerenciaRepository extends JpaRepository<AclHerencia, Long> {
	public List<AclHerencia> findByAccionHeredado(String accionHeredado);

	public List<AclHerencia> findByAccionPadre(String accionPadre);

	public List<AclHerencia> findByTipoPadreAndAccionHeredadoAndTipoHeredado(String tipoPadre, String accionHeredado,
			String tipoHeredado);
}
