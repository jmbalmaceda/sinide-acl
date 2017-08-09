package com.cito.sinide.sinideacl;

import java.util.Hashtable;

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
		/*
		 * usuario 1 puede VER juridiscción 13
		 */
		accion = crearAccion("VER", 1L, 13L, null, null, null, null);
		accionRepository.save(accion);
		/*
		 * usuario 1 puede CREAR_TITULACION en la jurisdiccion 13
		 */
		accion = crearAccion("CREAR_TITULACION", 1L, 13L, null, null, null, null);
		accionRepository.save(accion);
		/*
		 * usuario 1 puede TOMAR_TITULACION en la jurisdiccion 14 nivelSevicio 2
		 */
		accion = crearAccion("TOMAR_TITULACION", 1L, 14L, 2L, null, null, null);
		accionRepository.save(accion);
		/*
		 * usuario 2 puede TOMAT_ASISTENCIA en la jurisdiccion 14
		 */
		accion = crearAccion("TOMAR_ASISTENCIA", 2L, 14L, null, null, null, null);
		accionRepository.save(accion);
	}
	
	private AclAccion crearAccion(String accionStr, Long usuario, Long idJurisdiccion, Long idNivelServicio, Long idUnidadServicio, Long idSeccion, Long idSeccionCurricular){
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
	
	private Hashtable<String, Long> crearInfo(Long idJurisdiccion, Long idNivelServicio, Long idUnidadServicio, Long idSeccion, Long idSeccionCurricular){
		Hashtable<String, Long> hash = new Hashtable<>();
		if (idJurisdiccion!=null)
			hash.put("jurisdiccion", idJurisdiccion);
		if (idNivelServicio!=null)
			hash.put("nivelServicio", idNivelServicio);
		if (idUnidadServicio!=null)
			hash.put("unidadServicio", idUnidadServicio);
		if (idSeccion!=null)
			hash.put("seccion", idSeccion);
		if (idSeccionCurricular!=null)
			hash.put("seccionCurricular", idSeccionCurricular);
		return hash ;
	}
	
	@Test
	public void testPuede(){
		Hashtable<String, Long> info = null;
		/*
		 * ¿el usuario 1 puede VER la jurisdiccion 13? Si
		 */
		info = crearInfo(13L, null, null, null, null);
		Assert.assertTrue(accionRepository.puede(1L, "VER", "jurisdiccion", 13L, info));
		/*
		 * ¿el usuario 1 puede VER la jurisdicción 14? No
		 */
		info = crearInfo(13L, null, null, null, null);
		Assert.assertFalse(accionRepository.puede(1L, "VER", "jurisdiccion", 14L, info));
		/*
		 * ¿el usuario 1 puede CREAR_TITULACION en jurisdiccion 13? Si
		 */
		info = crearInfo(13L, null, null, null, null);
		Assert.assertTrue(accionRepository.puede(1L, "CREAR_TITULACION", "jurisdiccion", 13L, info));
		/*
		 * ¿el usuario 1 puede CREAR_TITULACION en jurisdiccion 14? No
		 */
		info = crearInfo(14L, null, null, null, null);
		Assert.assertFalse(accionRepository.puede(1L, "CREAR_TITULACION", "jurisdiccion", 14L, info));
		/*
		 * ¿el usuario 1 puede TOMAR_TITULACION en el nivelServicio 2 de la jurisdiccion 14? Si
		 */
		info = crearInfo(14L, 2L, null, null, null);
		Assert.assertTrue(accionRepository.puede(1L, "TOMAR_TITULACION", "nivelServicio", 2L, info));
		/*
		 * ¿el usuario 1 puede TOMAR_TITULACION en el nivelServicio 3 de la jurisdiccion 14? No
		 */
		info = crearInfo(14L, 3L, null, null, null);
		Assert.assertFalse(accionRepository.puede(1L, "TOMAR_TITULACION", "nivelServicio", 3L, info));
		/*
		 * ¿el usuario 1 puede TOMAR_TITULACION en el nivelServicio 2 de la jurisdiccion 13? No
		 */
		info = crearInfo(13L, 2L, null, null, null);
		Assert.assertFalse(accionRepository.puede(1L, "TOMAR_TITULACION", "nivelServicio", 2L, info));
		/*
		 * ¿el usuario 1 puede TOMAR_ASISTENCIA en la jurisdiccion 15? No
		 */
		info = crearInfo(15L, null, null, null, null);
		Assert.assertFalse(accionRepository.puede(1L, "TOMAR_ASISTENCIA", "jurisdiccion", 15L, info));
		/*
		 * ¿el usuario 2 puede TOMAR_ASISTENCIA en el nivelServicio 99 de la jurisdiccion 14? Si,
		 * porque puede TOMAR_ASISTENCIA en la jurisdicción 14
		 */
		info = crearInfo(14L, 99L, null, null, null);
		Assert.assertTrue(accionRepository.puede(2L, "TOMAR_ASISTENCIA", "nivelServicio", 99L, info));
		/*
		 * ¿el usuario 2 pude TOMAR_ASISTENCIA en el nivelServicio 99 de la jurisdiccion 13? No
		 */
		info = crearInfo(13L, 99L, null, null, null);
		Assert.assertFalse(accionRepository.puede(2L, "TOMAR_ASISTENCIA", "nivelServicio", 99L, info));
	}
}
