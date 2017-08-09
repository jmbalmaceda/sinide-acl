package com.cito.sinide.sinideacl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cito.sinide.sinideacl.entity.Accion;
import com.cito.sinide.sinideacl.repository.AccionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SinideAclApplicationTests {
	@Autowired
	private AccionRepository accionRepository;
	
	@Before
	public void contextLoads() {
		// Elimino todas las que puedan existir
		accionRepository.deleteAll();
		// Cargo las pruebas
		Accion accion = new Accion();
		accion.setAccion("VER");
		accion.setIdJurisdiccion(13l);
		
		accionRepository.save(accion);
	}

	@Test
	public void testInsert(){
		List<Accion> all = accionRepository.findAll();
		Assert.assertNotNull(all);
		System.out.println(all.size());
	}
	
	@Test
	public void testInsert2(){
		List<Accion> all = accionRepository.findAll();
		Assert.assertNotNull(all);
		System.out.println(all.size());
	}
}
