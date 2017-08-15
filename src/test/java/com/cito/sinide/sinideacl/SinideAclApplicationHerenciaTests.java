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

		/////////////////////////////
		// Permisos  Jurisdicción //
		///////////////////////////

		AclPermiso ver_informacion_institucional_jurisdiccion = new AclPermiso();
		ver_informacion_institucional_jurisdiccion.setClase("Jurisdiccion");
		ver_informacion_institucional_jurisdiccion.setPermiso("VER_INFORMACION_INSTITUCIONAL");
		
		aclPermisoRepository.saveAndFlush(ver_informacion_institucional_jurisdiccion);
		
		AclPermiso administration_jurisdiccion = new AclPermiso();
		administration_jurisdiccion.setClase("Jurisdiccion");
		administration_jurisdiccion.setPermiso("ADMINISTRATION");
		
		aclPermisoRepository.saveAndFlush(administration_jurisdiccion);
		
		AclPermiso modificar_informacion_institucional_jurisdiccion = new AclPermiso();
		modificar_informacion_institucional_jurisdiccion.setClase("Jurisdiccion");
		modificar_informacion_institucional_jurisdiccion.setPermiso("MODIFICAR_INFORMACION_INSTITUCIONAL");
		
		aclPermisoRepository.saveAndFlush(modificar_informacion_institucional_jurisdiccion);
		
		AclPermiso crear_titulo_jurisdiccion = new AclPermiso();
		crear_titulo_jurisdiccion.setClase("Jurisdiccion");
		crear_titulo_jurisdiccion.setPermiso("CREAR_TITULO");
		
		aclPermisoRepository.saveAndFlush(crear_titulo_jurisdiccion);
		
		AclPermiso ver_titulo_jurisdiccion = new AclPermiso();
		ver_titulo_jurisdiccion.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion.setPermiso("VER_TITULO");
		
		aclPermisoRepository.saveAndFlush(ver_titulo_jurisdiccion);
		
		AclPermiso confirmar_titulo_jurisdiccion = new AclPermiso();
		confirmar_titulo_jurisdiccion.setClase("Jurisdiccion");
		confirmar_titulo_jurisdiccion.setPermiso("CONFIRMAR_TITULO");
		
		aclPermisoRepository.saveAndFlush(confirmar_titulo_jurisdiccion);
		
		AclPermiso borrar_titulo_jurisdiccion = new AclPermiso();
		borrar_titulo_jurisdiccion.setClase("Jurisdiccion");
		borrar_titulo_jurisdiccion.setPermiso("BORRAR_TITULO");
		
		aclPermisoRepository.saveAndFlush(borrar_titulo_jurisdiccion);
		
		AclPermiso modificar_titulo_jurisdiccion = new AclPermiso();
		modificar_titulo_jurisdiccion.setClase("Jurisdiccion");
		modificar_titulo_jurisdiccion.setPermiso("MODIFICAR_TITULO");
		
		aclPermisoRepository.saveAndFlush(modificar_titulo_jurisdiccion);
		
		AclPermiso desconfirmar_titulo_jurisdiccion = new AclPermiso();
		desconfirmar_titulo_jurisdiccion.setClase("Jurisdiccion");
		desconfirmar_titulo_jurisdiccion.setPermiso("DESCONFIRMAR_TITULO");
	
		aclPermisoRepository.saveAndFlush(desconfirmar_titulo_jurisdiccion);
		
		AclPermiso borrar_inscripcion_jurisdiccion = new AclPermiso();
		borrar_inscripcion_jurisdiccion.setClase("Jurisdiccion");
		borrar_inscripcion_jurisdiccion.setPermiso("BORRAR_INSCRIPCION");
		
		aclPermisoRepository.saveAndFlush(borrar_inscripcion_jurisdiccion);
		
		AclPermiso agregar_inscripcion_jurisdiccion = new AclPermiso();
		agregar_inscripcion_jurisdiccion.setClase("Jurisdiccion");
		agregar_inscripcion_jurisdiccion.setPermiso("AGREGAR_INSCRIPCION");
		
		aclPermisoRepository.saveAndFlush(agregar_inscripcion_jurisdiccion);
		
		AclPermiso modificar_inscripcion_jurisdiccion = new AclPermiso();
		modificar_inscripcion_jurisdiccion.setClase("Jurisdiccion");
		modificar_inscripcion_jurisdiccion.setPermiso("MODIFICAR_INSCRIPCION");
		
		aclPermisoRepository.saveAndFlush(modificar_inscripcion_jurisdiccion);
		
		AclPermiso ver_inscripcion_jurisdiccion = new AclPermiso();
		ver_inscripcion_jurisdiccion.setClase("Jurisdiccion");
		ver_inscripcion_jurisdiccion.setPermiso("VER_INSCRIPCIONES");
		
		aclPermisoRepository.saveAndFlush(ver_inscripcion_jurisdiccion);
		
		AclPermiso vincular_titulo_unidad_servicio_jurisdiccion = new AclPermiso();
		vincular_titulo_unidad_servicio_jurisdiccion.setClase("Jurisdiccion");
		vincular_titulo_unidad_servicio_jurisdiccion.setPermiso("VINCULAR_TITULO_UNIDAD_SERVICIO");
		
		aclPermisoRepository.saveAndFlush(vincular_titulo_unidad_servicio_jurisdiccion);
		
		AclPermiso ver_titulos_unidad_servicio_jurisdiccion = new AclPermiso();
		ver_titulos_unidad_servicio_jurisdiccion.setClase("Jurisdiccion");
		ver_titulos_unidad_servicio_jurisdiccion.setPermiso("VER_TITULOS_UNIDAD_SERVICIO");
		
		aclPermisoRepository.saveAndFlush(ver_titulos_unidad_servicio_jurisdiccion);
		
		AclPermiso modificar_titulos_unidad_servicio_jurisdiccion = new AclPermiso();
		modificar_titulos_unidad_servicio_jurisdiccion.setClase("Jurisdiccion");
		modificar_titulos_unidad_servicio_jurisdiccion.setPermiso("MODIFICAR_TITULOS_UNIDAD_SERVICIO");
		
		aclPermisoRepository.saveAndFlush(modificar_titulos_unidad_servicio_jurisdiccion);
		
		AclPermiso confirmar_titulo_unidad_servicio_jurisdiccion = new AclPermiso();
		confirmar_titulo_unidad_servicio_jurisdiccion.setClase("Jurisdiccion");
		confirmar_titulo_unidad_servicio_jurisdiccion.setPermiso("CONFIRMAR_TITULO_UNIDAD_SERVICIO");
		
		aclPermisoRepository.saveAndFlush(confirmar_titulo_unidad_servicio_jurisdiccion);
		
		AclPermiso ver_datos_alumno_jurisdiccion = new AclPermiso();
		ver_datos_alumno_jurisdiccion.setClase("Jurisdiccion");
		ver_datos_alumno_jurisdiccion.setPermiso("VER_DATOS_ALUMNO");
		
		aclPermisoRepository.saveAndFlush(ver_datos_alumno_jurisdiccion);
		
		AclPermiso mofidicar_datos_alumno_jurisdiccion = new AclPermiso();
		mofidicar_datos_alumno_jurisdiccion.setClase("Jurisdiccion");
		mofidicar_datos_alumno_jurisdiccion.setPermiso("MODIFICAR_DATOS_ALUMNO");
		
		aclPermisoRepository.saveAndFlush(mofidicar_datos_alumno_jurisdiccion);
		
		AclPermiso modificar_organizacion_cursada_jurisdiccion = new AclPermiso();
		modificar_organizacion_cursada_jurisdiccion.setClase("Jurisdiccion");
		modificar_organizacion_cursada_jurisdiccion.setPermiso("MODIFICAR_ORGANIZACION_CURSADA");

		aclPermisoRepository.saveAndFlush(modificar_organizacion_cursada_jurisdiccion);
		
		AclPermiso ver_organizacion_cursada_jurisdiccion = new AclPermiso();
		ver_organizacion_cursada_jurisdiccion.setClase("Jurisdiccion");
		ver_organizacion_cursada_jurisdiccion.setPermiso("VER_ORGANIZACION_CURSADA");

		aclPermisoRepository.saveAndFlush(ver_organizacion_cursada_jurisdiccion);
		
		////////////////////////////////
		// Permisos  Unidad Servicio //
		//////////////////////////////
		
		AclPermiso administrador_uservicio = new AclPermiso();
		administrador_uservicio.setClase("UnidadServicio");
		administrador_uservicio.setPermiso("ADMINISTRATION");

		aclPermisoRepository.saveAndFlush(administrador_uservicio);

		AclPermiso ver_titulo_uservicio = new AclPermiso();
		ver_titulo_uservicio.setClase("UnidadServicio");
		ver_titulo_uservicio.setPermiso("VER_TITULO");

		aclPermisoRepository.saveAndFlush(ver_titulo_uservicio);
	
		AclPermiso confirmar_titulo_uservicio = new AclPermiso();
		confirmar_titulo_uservicio.setClase("UnidadServicio");
		confirmar_titulo_uservicio.setPermiso("CONFIRMAR_TITULO");

		aclPermisoRepository.saveAndFlush(confirmar_titulo_uservicio);

		AclPermiso modificar_titulo_uservicio = new AclPermiso();
		modificar_titulo_uservicio.setClase("UnidadServicio");
		modificar_titulo_uservicio.setPermiso("MODIFICAR_TITULO");

		aclPermisoRepository.saveAndFlush(modificar_titulo_uservicio);

		AclPermiso desvincular_titulo_uservicio = new AclPermiso();
		desvincular_titulo_uservicio.setClase("UnidadServicio");
		desvincular_titulo_uservicio.setPermiso("DESVINCULAR_TITULO");

		aclPermisoRepository.saveAndFlush(desvincular_titulo_uservicio);

		AclPermiso vincular_titulo_uservicio = new AclPermiso();
		vincular_titulo_uservicio.setClase("UnidadServicio");
		vincular_titulo_uservicio.setPermiso("VINCULAR_TITULO");

		aclPermisoRepository.saveAndFlush(vincular_titulo_uservicio);

		AclPermiso ver_informacion_institucional_uservicio = new AclPermiso();
		ver_informacion_institucional_uservicio.setClase("UnidadServicio");
		ver_informacion_institucional_uservicio.setPermiso("VER_INFORMACION_INSTITUCIONAL");

		aclPermisoRepository.saveAndFlush(ver_informacion_institucional_uservicio);
	
		AclPermiso modificar_informacion_institucional_uservicio = new AclPermiso();
		modificar_informacion_institucional_uservicio.setClase("UnidadServicio");
		modificar_informacion_institucional_uservicio.setPermiso("MODIFICAR_INFORMACION_INSTITUCIONAL");

		aclPermisoRepository.saveAndFlush(modificar_informacion_institucional_uservicio);

		AclPermiso ver_organizacion_cursada_uservicio = new AclPermiso();
		ver_organizacion_cursada_uservicio.setClase("UnidadServicio");
		ver_organizacion_cursada_uservicio.setPermiso("VER_ORGANIZACION_CURSADA");

		aclPermisoRepository.saveAndFlush(ver_organizacion_cursada_uservicio);
		
		AclPermiso borrar_organizacion_cursada_uservicio = new AclPermiso();
		borrar_organizacion_cursada_uservicio.setClase("UnidadServicio");
		borrar_organizacion_cursada_uservicio.setPermiso("BORRAR_ORGANIZACION_CURSADA");

		aclPermisoRepository.saveAndFlush(borrar_organizacion_cursada_uservicio);
	
		AclPermiso modificar_organizacion_cursada_uservicio = new AclPermiso();
		modificar_organizacion_cursada_uservicio.setClase("UnidadServicio");
		modificar_organizacion_cursada_uservicio.setPermiso("MODIFICAR_ORGANIZACION_CURSADA");

		aclPermisoRepository.saveAndFlush(modificar_organizacion_cursada_uservicio);
		
		AclPermiso borrar_inscripcion_uservicio = new AclPermiso();
		borrar_inscripcion_uservicio.setClase("UnidadServicio");
		borrar_inscripcion_uservicio.setPermiso("BORRAR_INSCRIPCION");

		aclPermisoRepository.saveAndFlush(borrar_inscripcion_uservicio);

		AclPermiso agregar_inscripcion_uservicio = new AclPermiso();
		agregar_inscripcion_uservicio.setClase("UnidadServicio");
		agregar_inscripcion_uservicio.setPermiso("AGREGAR_INSCRIPCION");

		aclPermisoRepository.saveAndFlush(agregar_inscripcion_uservicio);
		
		AclPermiso modificar_inscripcion_uservicio = new AclPermiso();
		modificar_inscripcion_uservicio.setClase("UnidadServicio");
		modificar_inscripcion_uservicio.setPermiso("MODIFICAR_INSCRIPCION");

		aclPermisoRepository.saveAndFlush(modificar_inscripcion_uservicio);
		
		AclPermiso ver_inscripcion_uservicio = new AclPermiso();
		ver_inscripcion_uservicio.setClase("UnidadServicio");
		ver_inscripcion_uservicio.setPermiso("VER_INSCRIPCIONES");

		aclPermisoRepository.saveAndFlush(ver_inscripcion_uservicio);

		AclPermiso modificar_datos_alumno_uservicio = new AclPermiso();
		modificar_datos_alumno_uservicio.setClase("UnidadServicio");
		modificar_datos_alumno_uservicio.setPermiso("MODIFICAR_DATOS_ALUMNO");

		aclPermisoRepository.saveAndFlush(modificar_datos_alumno_uservicio);

		AclPermiso ver_datos_alumno_uservicio = new AclPermiso();
		ver_datos_alumno_uservicio.setClase("UnidadServicio");
		ver_datos_alumno_uservicio.setPermiso("VER_DATOS_ALUMNO");

		aclPermisoRepository.saveAndFlush(ver_datos_alumno_uservicio);

		////////////////////////////////
		// Permisos  Sección //
		//////////////////////////////
		
		
		
		///////////////////////////////////
		// Permisos  Sección Curricular //
		/////////////////////////////////
		
		
		
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
	}
}
