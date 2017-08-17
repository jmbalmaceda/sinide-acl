package com.cito.sinide.sinideacl;

import java.util.Hashtable;

import javax.transaction.Transactional;

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
public class SinideAclApplicationGetIdsPermisoTests {

	@Autowired
	private AclAccionRepository accionRepository;

	@Before
	@Transactional
	public void contextLoads() {
		// clean
		accionRepository.deleteAll();

		// Cargo las pruebas
		AclAccion accion = null;
		/*
		 * usuario 1 puede VER juridiscción 13
		 */
		accion = crearAccion("VER_JURISDICCION", 1L, 13L, null, null, null, null);
		accionRepository.save(accion);
		
		/*
		 * usuario 1 puede VER juridiscción 14
		 */
		accion = crearAccion("VER_JURISDICCION", 1L, 14L, null, null, null, null);
		accionRepository.save(accion);
		
		/*
		 * usuario 1 puede VER juridiscción 15
		 */
		accion = crearAccion("VER_JURISDICCION", 1L, 15L, null, null, null, null);
		accionRepository.save(accion);
		
		/*
		 * usuario 1 puede VER juridiscción 16
		 */
		accion = crearAccion("VER_JURISDICCION", 1L, 16L, null, null, null, null);
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
	public void testPuede() {
		Hashtable<String, Long> info = null;
		
		info = crearInfo(13L, null, null, null, null);
		accionRepository.getIdsPermiso(1L, "VER_JURISDICCION", "jurisdiccion", info);
	}
}
