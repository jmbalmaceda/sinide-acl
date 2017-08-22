package com.cito.sinide.sinideacl;

import java.util.Hashtable;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cito.sinide.sinideacl.entity.AclAccion;
import com.cito.sinide.sinideacl.entity.AclHerencia;
import com.cito.sinide.sinideacl.repository.AclAccionRepository;
import com.cito.sinide.sinideacl.repository.AclHerenciaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SinideAclApplicationHerenciaTests {
	@Autowired
	private AclHerenciaRepository aclHerenciaRepository;

	@Autowired
	private AclAccionRepository accionRepository;

	@Before
	@Transactional
	public void contextLoads() {
		// clean
		aclHerenciaRepository.deleteAll();

		AclHerencia herencia = new AclHerencia();
		herencia.setAccionPadre("TOMAR_TITULACION_UNIDAD_SERVICIO");
		herencia.setAccionHeredado("VER_TITULACION_JURISDICCION");
		aclHerenciaRepository.save(herencia);

		AclHerencia herencia2 = new AclHerencia();
		herencia2.setAccionPadre("VER_TITULACION_JURISDICCION");
		herencia2.setAccionHeredado("VER_INFORMACION_INSTITUCIONAL");
		aclHerenciaRepository.save(herencia2);

		accionRepository.deleteAll();

		// Cargo las pruebas
		AclAccion accion = null;
		/*
		 * usuario 1 puede VER juridiscción 13
		 */
		accion = crearAccion("TOMAR_TITULACION_UNIDAD_SERVICIO", 1L, 13L, null, null, null, null);
		accionRepository.save(accion);

	}

	private AclAccion crearAccion(String accionStr, Long usuario, Long idJurisdiccion, Long idNivelServicio,
			Long idUnidadServicio, Long idSeccion, Long idSeccionCurricular) {
		AclAccion accion = new AclAccion();
		accion.setAccion(accionStr);
		accion.setIdUsuario(usuario);
		accion.setIdJurisdiccion(idJurisdiccion);
		accion.setIdNivelServicio(idNivelServicio);
		accion.setIdUnidadServicio(idUnidadServicio);
		accion.setIdSeccion(idSeccion);
		accion.setIdSeccionCurricular(idSeccionCurricular);
		return accion;
	}

	private Hashtable<String, Long> crearInfo(Long idJurisdiccion, Long idNivelServicio, Long idUnidadServicio,
			Long idSeccion, Long idSeccionCurricular) {
		Hashtable<String, Long> hash = new Hashtable<>();
		if (idJurisdiccion != null)
			hash.put("jurisdiccion", idJurisdiccion);
		if (idNivelServicio != null)
			hash.put("nivelServicio", idNivelServicio);
		if (idUnidadServicio != null)
			hash.put("unidadServicio", idUnidadServicio);
		if (idSeccion != null)
			hash.put("seccion", idSeccion);
		if (idSeccionCurricular != null)
			hash.put("seccionCurricular", idSeccionCurricular);
		return hash;
	}

	@Test
	public void testPuede() {
		Hashtable<String, Long> info = null;

		info = crearInfo(13L, null, null, null, null);
		Assert.assertTrue(accionRepository.puede(1L, "VER_INFORMACION_INSTITUCIONAL", "jurisdiccion", 13L, info));

		info = crearInfo(13L, 2L, 14L, null, null);
		Assert.assertTrue(accionRepository.puede(1L, "VER_INFORMACION_INSTITUCIONAL", "unidadServicio", 14L, info));

		info = crearInfo(10L, 2L, 14L, null, null);
		Assert.assertFalse(accionRepository.puede(1L, "VER_INFORMACION_INSTITUCIONAL", "unidadServicio", 14L, info));
	}
}
