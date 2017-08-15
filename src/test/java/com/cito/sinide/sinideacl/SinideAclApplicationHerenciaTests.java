package com.cito.sinide.sinideacl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cito.sinide.sinideacl.entity.AclHerencia;
import com.cito.sinide.sinideacl.entity.AclPermiso;
import com.cito.sinide.sinideacl.repository.AclHerenciaRepository;
import com.cito.sinide.sinideacl.repository.AclPermisoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SinideAclApplicationHerenciaTests {
	@Autowired
	private AclHerenciaRepository aclHerenciaRepository;

	@Autowired
	private AclPermisoRepository aclPermisoRepository;

	@Before
	@Transactional
	public void contextLoads() {
		// clean
		aclHerenciaRepository.deleteAll();

		// Cargo las pruebas
		AclHerencia herencia = new AclHerencia();

		AclPermiso permiso = new AclPermiso();
		permiso.setClase("Jurisdiccion");
		permiso.setPermiso("READ");

		AclPermiso permisoHeredado = new AclPermiso();
		permisoHeredado.setClase("UnidadServicio");
		permisoHeredado.setPermiso("READ");

		aclPermisoRepository.saveAndFlush(permiso);

		herencia.setPermisoPadre(permiso);

		List<AclPermiso> list = new ArrayList<AclPermiso>();
		list.add(permisoHeredado);

		herencia.setPermisosHeredados(list);

		aclHerenciaRepository.saveAndFlush(herencia);

		Assert.assertTrue(aclHerenciaRepository.findAll().get(0).getPermisosHeredados().get(0).getClase()
				.equals("UnidadServicio"));
	}

	@Test
	public void testPuede() {
		System.out.println("Cargo bien todo");
	}
}
