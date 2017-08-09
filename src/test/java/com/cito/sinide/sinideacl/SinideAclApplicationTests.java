package com.cito.sinide.sinideacl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cito.sinide.sinideacl.entity.AclAccion;
import com.cito.sinide.sinideacl.repository.AclAccionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SinideAclApplicationTests {
	@Autowired
	private AclAccionRepository accionRepository;
	
	@Before
	public void contextLoads() {
		// clean
		accionRepository.deleteAll();
		
		// Cargo las pruebas
		AclAccion accion = null;
		accion = crearAccion("VER", 1L, 13L, null, null, null, null);
		accionRepository.save(accion);
		accion = crearAccion("CREAR_TITULACION", 1L, 13L, null, null, null, null);
		accionRepository.save(accion);
		accion = crearAccion("TOMAR_TITULACION", 1L, 14L, 2L, 111L, null, null);
		accionRepository.save(accion);
	}
	
	AclAccion crearAccion(String accionStr, Long usuario, Long idJurisdiccion, Long idNivelServicio, Long idUnidadServicio, Long idSeccion, Long idSeccionCurricular){
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
	
	@Test
	public void testPuede(){
		Assert.assertTrue(accionRepository.puede(1L, "VER", "jurisdiccion", 13L, null));
		Assert.assertFalse(accionRepository.puede(1L, "VER", "jurisdiccion", 14L, null));
		Assert.assertTrue(accionRepository.puede(1L, "CREAR_TITULACION", "jurisdiccion", 13L, null));
		Assert.assertFalse(accionRepository.puede(1L, "CREAR_TITULACION", "jurisdiccion", 14L, null));
		Assert.assertTrue(accionRepository.puede(1L, "TOMAR_TITULACION", "jurisdiccion", 14L, null));
		Assert.assertFalse(accionRepository.puede(1L, "TOMAR_TITULACION", "jurisdiccion", 13L, null));
		Assert.assertFalse(accionRepository.puede(1L, "TOMAR_ASISTENCIA", "jurisdiccion", null, null));
	}
}
