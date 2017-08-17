package com.cito.sinide.sinideacl;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cito.sinide.sinideacl.entity.AclAccionClase;
import com.cito.sinide.sinideacl.repository.AclAccionClaseRepository;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SinideAclApplicationGetPermisosClaseTests {

	@Autowired
	AclAccionClaseRepository accionClaseRepository;

	@Before
	@Transactional
	public void contextLoads() {
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

	private AclAccionClase crearAccionClase(String accionStr, String clase) {
		AclAccionClase accion = new AclAccionClase();
		accion.setAccion(accionStr);
		accion.setClase(clase);
		return accion;
	}

	@Test
	public void testPuede() {
		Assert.assertTrue(accionClaseRepository.findByClase("unidadServicio").size() > 0);

		System.out.println(accionClaseRepository.findByClase("unidadServicio").size());

		Assert.assertTrue(
				accionClaseRepository.findByClaseAndAccion("unidadServicio", "VER_UNIDAD_SERVICIO").size() == 1);

		Assert.assertTrue(accionClaseRepository.findByClase("jurisdiccion").size() > 0);

		System.out.println(accionClaseRepository.findByClase("jurisdiccion").size());

		Assert.assertTrue(accionClaseRepository.findByClaseAndAccion("jurisdiccion", "VER_JURISDICCION").size() == 1);
	}
}
