package com.cito.sinide.sinideacl;

import java.util.Hashtable;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cito.sinide.sinideacl.entity.AclAccion;
import com.cito.sinide.sinideacl.entity.AclAccionClase;
import com.cito.sinide.sinideacl.entity.AclHerencia;
import com.cito.sinide.sinideacl.repository.AclAccionClaseRepository;
import com.cito.sinide.sinideacl.repository.AclAccionRepository;
import com.cito.sinide.sinideacl.repository.AclHerenciaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SinideAclApplicationGetPermisosTests {
	@Autowired
	private AclHerenciaRepository aclHerenciaRepository;

	@Autowired
	private AclAccionRepository accionRepository;

	@Autowired
	AclAccionClaseRepository accionClaseRepository;

	@Before
	@Transactional
	public void contextLoads() {
		// clean
		aclHerenciaRepository.deleteAll();

		AclHerencia herencia = new AclHerencia();
		herencia.setAccionPadre("VER_TITULACION_JURISDICCION");
		herencia.setAccionHeredado("TOMAR_TITULACION_UNIDAD_SERVICIO");
		aclHerenciaRepository.save(herencia);

		AclHerencia herencia2 = new AclHerencia();
		herencia2.setAccionPadre("VER_JURISDICCION");
		herencia2.setAccionHeredado("VER_INFORMACION_INSTITUCIONAL");
		aclHerenciaRepository.save(herencia2);

		AclHerencia herencia3 = new AclHerencia();
		herencia3.setAccionPadre("VER_JURISDICCION");
		herencia3.setAccionHeredado("VER_UNIDAD_SERVICIO");
		aclHerenciaRepository.save(herencia3);

		accionRepository.deleteAll();

		// Cargo las pruebas
		AclAccion accion = null;
		/*
		 * usuario 1 puede VER juridiscción 13
		 */
		accion = crearAccion("VER_TITULACION_JURISDICCION", 1L, 13L, null, null, null, null);
		accionRepository.save(accion);

		accion = crearAccion("VER_JURISDICCION", 1L, 14L, null, null, null, null);
		accionRepository.save(accion);

		accion = crearAccion("VER_UNIDAD_SERVICIO", 1L, 13L, 2L, 14L, null, null);
		accionRepository.save(accion);

		AclAccionClase accionClase = null;

		accionClase = crearAccionClase("VER_INFORMACION_INSTITUCIONAL", "unidadServicio");
		accionClaseRepository.save(accionClase);

		accionClase = crearAccionClase("TOMAR_TITULACION_UNIDAD_SERVICIO", "unidadServicio");
		accionClaseRepository.save(accionClase);

		accionClase = crearAccionClase("VER_TITULACION_JURISDICCION", "jurisdiccion");
		accionClaseRepository.save(accionClase);

		accionClase = crearAccionClase("VER_UNIDAD_SERVICIO", "unidadServicio");
		accionClaseRepository.save(accionClase);

		accionClase = crearAccionClase("VER_JURISDICCION", "jurisdiccion");
		accionClaseRepository.save(accionClase);
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

	private AclAccionClase crearAccionClase(String accionStr, String clase) {
		AclAccionClase accion = new AclAccionClase();
		accion.setAccion(accionStr);
		accion.setClase(clase);
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

		info = crearInfo(13L, 2L, 14L, null, null);
		List<String> permisos = accionRepository.getPermisos(1L, "unidadServicio", 14L, info);

		System.out.println(permisos);
	}
}