package com.cito.sinide.sinideacl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

		////////////////////////////
		// Herencia Jurisdiccion //
		//////////////////////////

		AclPermiso read_jurisdiccion_ver_info_inst = new AclPermiso();
		read_jurisdiccion_ver_info_inst.setClase("Jurisdiccion");
		read_jurisdiccion_ver_info_inst.setPermiso("READ");

		List<AclPermiso> h1 = new ArrayList<>();
		h1.add(read_jurisdiccion_ver_info_inst);

		AclPermiso ver_informacion_institucional_jurisdiccion = new AclPermiso();
		ver_informacion_institucional_jurisdiccion.setClase("Jurisdiccion");
		ver_informacion_institucional_jurisdiccion.setPermiso("VER_INFORMACION_INSTITUCIONAL");
		aclPermisoRepository.saveAndFlush(ver_informacion_institucional_jurisdiccion);

		AclHerencia ver_informacion_institucional_jurisdiccion_herencia = new AclHerencia();
		ver_informacion_institucional_jurisdiccion_herencia.setPermisoPadre(ver_informacion_institucional_jurisdiccion);
		ver_informacion_institucional_jurisdiccion_herencia.setPermisosHeredados(h1);
		aclHerenciaRepository.saveAndFlush(ver_informacion_institucional_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_admin = new AclPermiso();
		read_jurisdiccion_admin.setClase("Jurisdiccion");
		read_jurisdiccion_admin.setPermiso("READ");

		List<AclPermiso> adminJur = new ArrayList<>();
		adminJur.add(read_jurisdiccion_admin);

		AclPermiso administration_jurisdiccion = new AclPermiso();
		administration_jurisdiccion.setClase("Jurisdiccion");
		administration_jurisdiccion.setPermiso("ADMINISTRATION");
		aclPermisoRepository.saveAndFlush(administration_jurisdiccion);

		AclHerencia administracion_jurisdiccion_herencia = new AclHerencia();
		administracion_jurisdiccion_herencia.setPermisoPadre(administration_jurisdiccion);
		administracion_jurisdiccion_herencia.setPermisosHeredados(adminJur);
		aclHerenciaRepository.saveAndFlush(administracion_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_mod_info_inst = new AclPermiso();
		read_jurisdiccion_mod_info_inst.setClase("Jurisdiccion");
		read_jurisdiccion_mod_info_inst.setPermiso("READ");

		List<AclPermiso> modInfoInstJur = new ArrayList<>();
		modInfoInstJur.add(read_jurisdiccion_mod_info_inst);

		AclPermiso modificar_informacion_institucional_jurisdiccion = new AclPermiso();
		modificar_informacion_institucional_jurisdiccion.setClase("Jurisdiccion");
		modificar_informacion_institucional_jurisdiccion.setPermiso("MODIFICAR_INFORMACION_INSTITUCIONAL");
		aclPermisoRepository.saveAndFlush(modificar_informacion_institucional_jurisdiccion);

		AclHerencia modificar_informacion_institucional_jurisdiccion_herencia = new AclHerencia();
		modificar_informacion_institucional_jurisdiccion_herencia
				.setPermisoPadre(modificar_informacion_institucional_jurisdiccion);
		modificar_informacion_institucional_jurisdiccion_herencia.setPermisosHeredados(modInfoInstJur);
		aclHerenciaRepository.saveAndFlush(modificar_informacion_institucional_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_cr_tit = new AclPermiso();
		read_jurisdiccion_cr_tit.setClase("Jurisdiccion");
		read_jurisdiccion_cr_tit.setPermiso("READ");

		List<AclPermiso> crTitJur = new ArrayList<>();
		crTitJur.add(read_jurisdiccion_cr_tit);

		AclPermiso crear_titulo_jurisdiccion = new AclPermiso();
		crear_titulo_jurisdiccion.setClase("Jurisdiccion");
		crear_titulo_jurisdiccion.setPermiso("CREAR_TITULO");
		aclPermisoRepository.saveAndFlush(crear_titulo_jurisdiccion);

		AclHerencia crear_titulo_jurisdiccion_herencia = new AclHerencia();
		crear_titulo_jurisdiccion_herencia.setPermisoPadre(crear_titulo_jurisdiccion);
		crear_titulo_jurisdiccion_herencia.setPermisosHeredados(crTitJur);
		aclHerenciaRepository.saveAndFlush(crear_titulo_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_titulo_jurisdiccion = new AclPermiso();
		ver_titulo_jurisdiccion.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion.setPermiso("VER_TITULO");
		aclPermisoRepository.saveAndFlush(ver_titulo_jurisdiccion);

		AclPermiso read_jurisdiccion_ver_tit = new AclPermiso();
		read_jurisdiccion_ver_tit.setClase("Jurisdiccion");
		read_jurisdiccion_ver_tit.setPermiso("READ");

		List<AclPermiso> verTitJur = new ArrayList<>();
		adminJur.add(read_jurisdiccion_ver_tit);

		AclHerencia ver_titulo_jurisdiccion_herencia = new AclHerencia();
		ver_titulo_jurisdiccion_herencia.setPermisoPadre(ver_titulo_jurisdiccion);
		ver_titulo_jurisdiccion_herencia.setPermisosHeredados(verTitJur);
		aclHerenciaRepository.saveAndFlush(ver_titulo_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_ver_tit_us_j = new AclPermiso();
		read_jurisdiccion_ver_tit_us_j.setClase("Jurisdiccion");
		read_jurisdiccion_ver_tit_us_j.setPermiso("READ");

		List<AclPermiso> verTitUsJur = new ArrayList<>();
		verTitUsJur.add(read_jurisdiccion_ver_tit_us_j);

		AclPermiso ver_titulos_unidad_servicio_jurisdiccion = new AclPermiso();
		ver_titulos_unidad_servicio_jurisdiccion.setClase("Jurisdiccion");
		ver_titulos_unidad_servicio_jurisdiccion.setPermiso("VER_TITULOS_UNIDAD_SERVICIO");
		aclPermisoRepository.saveAndFlush(ver_titulos_unidad_servicio_jurisdiccion);

		AclHerencia ver_titulo_unidad_servicio_jurisdiccion_herencia = new AclHerencia();
		ver_titulo_unidad_servicio_jurisdiccion_herencia.setPermisoPadre(ver_titulos_unidad_servicio_jurisdiccion);
		ver_titulo_unidad_servicio_jurisdiccion_herencia.setPermisosHeredados(verTitUsJur);
		aclHerenciaRepository.saveAndFlush(ver_titulo_unidad_servicio_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_ver_dat_al_j = new AclPermiso();
		read_jurisdiccion_ver_dat_al_j.setClase("Jurisdiccion");
		read_jurisdiccion_ver_dat_al_j.setPermiso("READ");

		List<AclPermiso> verDatAlumJur = new ArrayList<>();
		verDatAlumJur.add(read_jurisdiccion_ver_dat_al_j);

		AclPermiso ver_datos_alumno_jurisdiccion = new AclPermiso();
		ver_datos_alumno_jurisdiccion.setClase("Jurisdiccion");
		ver_datos_alumno_jurisdiccion.setPermiso("VER_DATOS_ALUMNO");
		aclPermisoRepository.saveAndFlush(ver_datos_alumno_jurisdiccion);

		AclHerencia ver_datos_alumno_jurisdiccion_herencia = new AclHerencia();
		ver_datos_alumno_jurisdiccion_herencia.setPermisoPadre(ver_datos_alumno_jurisdiccion);
		ver_datos_alumno_jurisdiccion_herencia.setPermisosHeredados(verDatAlumJur);
		aclHerenciaRepository.saveAndFlush(ver_datos_alumno_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_mod_org_cur = new AclPermiso();
		read_jurisdiccion_mod_org_cur.setClase("Jurisdiccion");
		read_jurisdiccion_mod_org_cur.setPermiso("READ");

		List<AclPermiso> modOrgCurJur = new ArrayList<>();
		modOrgCurJur.add(read_jurisdiccion_mod_org_cur);

		AclPermiso modificar_organizacion_cursada_jurisdiccion = new AclPermiso();
		modificar_organizacion_cursada_jurisdiccion.setClase("Jurisdiccion");
		modificar_organizacion_cursada_jurisdiccion.setPermiso("MODIFICAR_ORGANIZACION_CURSADA");
		aclPermisoRepository.saveAndFlush(modificar_organizacion_cursada_jurisdiccion);

		AclHerencia modificar_organizacion_cursada_jurisdiccion_herencia = new AclHerencia();
		modificar_organizacion_cursada_jurisdiccion_herencia
				.setPermisoPadre(modificar_organizacion_cursada_jurisdiccion);
		modificar_organizacion_cursada_jurisdiccion_herencia.setPermisosHeredados(modOrgCurJur);
		aclHerenciaRepository.saveAndFlush(modificar_organizacion_cursada_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_ver_org_cur = new AclPermiso();
		read_jurisdiccion_ver_org_cur.setClase("Jurisdiccion");
		read_jurisdiccion_ver_org_cur.setPermiso("READ");

		List<AclPermiso> verOrgCurJur = new ArrayList<>();
		verOrgCurJur.add(read_jurisdiccion_ver_org_cur);

		AclPermiso ver_organizacion_cursada_jurisdiccion = new AclPermiso();
		ver_organizacion_cursada_jurisdiccion.setClase("Jurisdiccion");
		ver_organizacion_cursada_jurisdiccion.setPermiso("VER_ORGANIZACION_CURSADA");
		aclPermisoRepository.saveAndFlush(ver_organizacion_cursada_jurisdiccion);

		AclHerencia ver_organizacion_cursada_jurisdiccion_herencia = new AclHerencia();
		ver_organizacion_cursada_jurisdiccion_herencia.setPermisoPadre(ver_organizacion_cursada_jurisdiccion);
		ver_organizacion_cursada_jurisdiccion_herencia.setPermisosHeredados(verOrgCurJur);
		aclHerenciaRepository.saveAndFlush(ver_organizacion_cursada_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_titulo_jurisdiccion_conf_tit = new AclPermiso();
		ver_titulo_jurisdiccion_conf_tit.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion_conf_tit.setPermiso("VER_TITULO");

		AclPermiso read_jurisdiccion_conf_tit = new AclPermiso();
		read_jurisdiccion_conf_tit.setClase("Jurisdiccion");
		read_jurisdiccion_conf_tit.setPermiso("READ");

		List<AclPermiso> conf_tit_jur = new ArrayList<>();
		conf_tit_jur.add(read_jurisdiccion_conf_tit);
		conf_tit_jur.add(ver_titulo_jurisdiccion_conf_tit);

		AclPermiso confirmar_titulo_jurisdiccion = new AclPermiso();
		confirmar_titulo_jurisdiccion.setClase("Jurisdiccion");
		confirmar_titulo_jurisdiccion.setPermiso("CONFIRMAR_TITULO");
		aclPermisoRepository.saveAndFlush(confirmar_titulo_jurisdiccion);

		AclHerencia confirmar_titulo_jurisdiccion_herencia = new AclHerencia();
		confirmar_titulo_jurisdiccion_herencia.setPermisoPadre(confirmar_titulo_jurisdiccion);
		confirmar_titulo_jurisdiccion_herencia.setPermisosHeredados(conf_tit_jur);
		aclHerenciaRepository.saveAndFlush(confirmar_titulo_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_titulo_jurisdiccion_borr_tit = new AclPermiso();
		ver_titulo_jurisdiccion_borr_tit.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion_borr_tit.setPermiso("VER_TITULO");

		AclPermiso read_jurisdiccion_borr_tit = new AclPermiso();
		read_jurisdiccion_borr_tit.setClase("Jurisdiccion");
		read_jurisdiccion_borr_tit.setPermiso("READ");

		List<AclPermiso> borr_tit_jur = new ArrayList<>();
		borr_tit_jur.add(read_jurisdiccion_borr_tit);
		borr_tit_jur.add(ver_titulo_jurisdiccion_borr_tit);

		AclPermiso borrar_titulo_jurisdiccion = new AclPermiso();
		borrar_titulo_jurisdiccion.setClase("Jurisdiccion");
		borrar_titulo_jurisdiccion.setPermiso("BORRAR_TITULO");
		aclPermisoRepository.saveAndFlush(borrar_titulo_jurisdiccion);

		AclHerencia borrar_titulo_jurisdiccion_herencia = new AclHerencia();
		borrar_titulo_jurisdiccion_herencia.setPermisoPadre(borrar_titulo_jurisdiccion);
		borrar_titulo_jurisdiccion_herencia.setPermisosHeredados(borr_tit_jur);
		aclHerenciaRepository.saveAndFlush(borrar_titulo_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_titulo_jurisdiccion_mod_tit = new AclPermiso();
		ver_titulo_jurisdiccion_mod_tit.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion_mod_tit.setPermiso("VER_TITULO");

		AclPermiso read_jurisdiccion_mod_tit = new AclPermiso();
		read_jurisdiccion_mod_tit.setClase("Jurisdiccion");
		read_jurisdiccion_mod_tit.setPermiso("READ");

		List<AclPermiso> mod_tit_jur = new ArrayList<>();
		mod_tit_jur.add(read_jurisdiccion_mod_tit);
		mod_tit_jur.add(ver_titulo_jurisdiccion_mod_tit);

		AclPermiso modificar_titulo_jurisdiccion = new AclPermiso();
		modificar_titulo_jurisdiccion.setClase("Jurisdiccion");
		modificar_titulo_jurisdiccion.setPermiso("MODIFICAR_TITULO");
		aclPermisoRepository.saveAndFlush(modificar_titulo_jurisdiccion);

		AclHerencia modificar_titulo_jurisdiccion_herencia = new AclHerencia();
		modificar_titulo_jurisdiccion_herencia.setPermisoPadre(modificar_titulo_jurisdiccion);
		modificar_titulo_jurisdiccion_herencia.setPermisosHeredados(mod_tit_jur);
		aclHerenciaRepository.saveAndFlush(modificar_titulo_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_titulo_jurisdiccion_desc_tit = new AclPermiso();
		ver_titulo_jurisdiccion_desc_tit.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion_desc_tit.setPermiso("VER_TITULO");

		AclPermiso read_jurisdiccion_desc_tit = new AclPermiso();
		read_jurisdiccion_desc_tit.setClase("Jurisdiccion");
		read_jurisdiccion_desc_tit.setPermiso("READ");

		List<AclPermiso> desc_tit_jur = new ArrayList<>();
		desc_tit_jur.add(read_jurisdiccion_desc_tit);
		desc_tit_jur.add(ver_titulo_jurisdiccion_desc_tit);

		AclPermiso desconfirmar_titulo_jurisdiccion = new AclPermiso();
		desconfirmar_titulo_jurisdiccion.setClase("Jurisdiccion");
		desconfirmar_titulo_jurisdiccion.setPermiso("DESCONFIRMAR_TITULO");
		aclPermisoRepository.saveAndFlush(desconfirmar_titulo_jurisdiccion);

		AclHerencia desconfirmar_titulo_jurisdiccion_herencia = new AclHerencia();
		desconfirmar_titulo_jurisdiccion_herencia.setPermisoPadre(desconfirmar_titulo_jurisdiccion);
		desconfirmar_titulo_jurisdiccion_herencia.setPermisosHeredados(desc_tit_jur);
		aclHerenciaRepository.saveAndFlush(desconfirmar_titulo_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_titulo_jurisdiccion_mod_tit_us = new AclPermiso();
		ver_titulo_jurisdiccion_mod_tit_us.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion_mod_tit_us.setPermiso("VER_TITULO");

		AclPermiso read_jurisdiccion_mod_tit_us = new AclPermiso();
		read_jurisdiccion_mod_tit_us.setClase("Jurisdiccion");
		read_jurisdiccion_mod_tit_us.setPermiso("READ");

		List<AclPermiso> mod_tit_usjur = new ArrayList<>();
		mod_tit_usjur.add(read_jurisdiccion_mod_tit_us);
		mod_tit_usjur.add(ver_titulo_jurisdiccion_mod_tit_us);

		AclPermiso modificar_titulos_unidad_servicio_jurisdiccion = new AclPermiso();
		modificar_titulos_unidad_servicio_jurisdiccion.setClase("Jurisdiccion");
		modificar_titulos_unidad_servicio_jurisdiccion.setPermiso("MODIFICAR_TITULOS_UNIDAD_SERVICIO");
		aclPermisoRepository.saveAndFlush(modificar_titulos_unidad_servicio_jurisdiccion);

		AclHerencia modificar_titulos_unidad_servicio_jurisdiccion_herencia = new AclHerencia();
		modificar_titulos_unidad_servicio_jurisdiccion_herencia
				.setPermisoPadre(modificar_titulos_unidad_servicio_jurisdiccion);
		modificar_titulos_unidad_servicio_jurisdiccion_herencia.setPermisosHeredados(mod_tit_usjur);
		aclHerenciaRepository.saveAndFlush(modificar_titulos_unidad_servicio_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_titulo_jurisdiccion_conf_tit_us = new AclPermiso();
		ver_titulo_jurisdiccion_conf_tit_us.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion_conf_tit_us.setPermiso("VER_TITULO");

		AclPermiso read_jurisdiccion_conf_tit_us = new AclPermiso();
		read_jurisdiccion_conf_tit_us.setClase("Jurisdiccion");
		read_jurisdiccion_conf_tit_us.setPermiso("READ");

		List<AclPermiso> conf_tit_usjur = new ArrayList<>();
		conf_tit_usjur.add(read_jurisdiccion_conf_tit_us);
		conf_tit_usjur.add(ver_titulo_jurisdiccion_conf_tit_us);

		AclPermiso confirmar_titulo_unidad_servicio_jurisdiccion = new AclPermiso();
		confirmar_titulo_unidad_servicio_jurisdiccion.setClase("Jurisdiccion");
		confirmar_titulo_unidad_servicio_jurisdiccion.setPermiso("CONFIRMAR_TITULO_UNIDAD_SERVICIO");
		aclPermisoRepository.saveAndFlush(confirmar_titulo_unidad_servicio_jurisdiccion);

		AclHerencia confirmar_titulos_unidad_servicio_jurisdiccion_herencia = new AclHerencia();
		confirmar_titulos_unidad_servicio_jurisdiccion_herencia
				.setPermisoPadre(confirmar_titulo_unidad_servicio_jurisdiccion);
		confirmar_titulos_unidad_servicio_jurisdiccion_herencia.setPermisosHeredados(conf_tit_usjur);
		aclHerenciaRepository.saveAndFlush(confirmar_titulos_unidad_servicio_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_titulo_jurisdiccion_vinc_tit_us = new AclPermiso();
		ver_titulo_jurisdiccion_vinc_tit_us.setClase("Jurisdiccion");
		ver_titulo_jurisdiccion_vinc_tit_us.setPermiso("VER_TITULO");

		AclPermiso read_jurisdiccion_vinc_tit_us = new AclPermiso();
		read_jurisdiccion_vinc_tit_us.setClase("Jurisdiccion");
		read_jurisdiccion_vinc_tit_us.setPermiso("READ");

		List<AclPermiso> vinc_tit_usjur = new ArrayList<>();
		vinc_tit_usjur.add(read_jurisdiccion_vinc_tit_us);
		vinc_tit_usjur.add(ver_titulo_jurisdiccion_vinc_tit_us);

		AclPermiso vincular_titulo_unidad_servicio_jurisdiccion = new AclPermiso();
		vincular_titulo_unidad_servicio_jurisdiccion.setClase("Jurisdiccion");
		vincular_titulo_unidad_servicio_jurisdiccion.setPermiso("VINCULAR_TITULO_UNIDAD_SERVICIO");
		aclPermisoRepository.saveAndFlush(vincular_titulo_unidad_servicio_jurisdiccion);

		AclHerencia vincular_titulos_unidad_servicio_jurisdiccion_herencia = new AclHerencia();
		vincular_titulos_unidad_servicio_jurisdiccion_herencia
				.setPermisoPadre(vincular_titulo_unidad_servicio_jurisdiccion);
		vincular_titulos_unidad_servicio_jurisdiccion_herencia.setPermisosHeredados(vinc_tit_usjur);
		aclHerenciaRepository.saveAndFlush(vincular_titulos_unidad_servicio_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_borr_insc_j = new AclPermiso();
		read_jurisdiccion_borr_insc_j.setClase("Jurisdiccion");
		read_jurisdiccion_borr_insc_j.setPermiso("READ");

		AclPermiso read_uservicio_borr_insc_j = new AclPermiso();
		read_uservicio_borr_insc_j.setClase("unidadServicio");
		read_uservicio_borr_insc_j.setPermiso("READ");

		AclPermiso ver_inscripcion_jurisdiccion_borr_insc_j = new AclPermiso();
		ver_inscripcion_jurisdiccion_borr_insc_j.setClase("Jurisdiccion");
		ver_inscripcion_jurisdiccion_borr_insc_j.setPermiso("VER_INSCRIPCIONES");

		AclPermiso ver_datos_alumno_uservicio_borr_insc_j = new AclPermiso();
		ver_datos_alumno_uservicio_borr_insc_j.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_borr_insc_j.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso ver_datos_alumno_jurisdiccion_borr_insc_j = new AclPermiso();
		ver_datos_alumno_jurisdiccion_borr_insc_j.setClase("Jurisdiccion");
		ver_datos_alumno_jurisdiccion_borr_insc_j.setPermiso("VER_DATOS_ALUMNO");

		List<AclPermiso> borrInscJ = new ArrayList<>();
		borrInscJ.add(read_jurisdiccion_borr_insc_j);
		borrInscJ.add(read_uservicio_borr_insc_j);
		borrInscJ.add(ver_inscripcion_jurisdiccion_borr_insc_j);
		borrInscJ.add(ver_datos_alumno_uservicio_borr_insc_j);
		borrInscJ.add(ver_datos_alumno_jurisdiccion_borr_insc_j);

		AclPermiso borrar_inscripcion_jurisdiccion = new AclPermiso();
		borrar_inscripcion_jurisdiccion.setClase("Jurisdiccion");
		borrar_inscripcion_jurisdiccion.setPermiso("BORRAR_INSCRIPCION");
		aclPermisoRepository.saveAndFlush(borrar_inscripcion_jurisdiccion);

		AclHerencia borrar_inscripcion_jurisdiccion_herencia = new AclHerencia();
		borrar_inscripcion_jurisdiccion_herencia.setPermisoPadre(borrar_inscripcion_jurisdiccion);
		borrar_inscripcion_jurisdiccion_herencia.setPermisosHeredados(borrInscJ);
		aclHerenciaRepository.saveAndFlush(borrar_inscripcion_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_ag_insc_j = new AclPermiso();
		read_jurisdiccion_ag_insc_j.setClase("Jurisdiccion");
		read_jurisdiccion_ag_insc_j.setPermiso("READ");

		AclPermiso read_uservicio_ag_insc_j = new AclPermiso();
		read_uservicio_ag_insc_j.setClase("unidadServicio");
		read_uservicio_ag_insc_j.setPermiso("READ");

		AclPermiso ver_inscripcion_jurisdiccion_ag_insc_j = new AclPermiso();
		ver_inscripcion_jurisdiccion_ag_insc_j.setClase("Jurisdiccion");
		ver_inscripcion_jurisdiccion_ag_insc_j.setPermiso("VER_INSCRIPCIONES");

		AclPermiso ver_datos_alumno_uservicio_ag_insc_j = new AclPermiso();
		ver_datos_alumno_uservicio_ag_insc_j.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_ag_insc_j.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso ver_datos_alumno_jurisdiccion_ag_insc_j = new AclPermiso();
		ver_datos_alumno_jurisdiccion_ag_insc_j.setClase("Jurisdiccion");
		ver_datos_alumno_jurisdiccion_ag_insc_j.setPermiso("VER_DATOS_ALUMNO");

		List<AclPermiso> agInscJ = new ArrayList<>();
		agInscJ.add(read_jurisdiccion_ag_insc_j);
		agInscJ.add(read_uservicio_ag_insc_j);
		agInscJ.add(ver_inscripcion_jurisdiccion_ag_insc_j);
		agInscJ.add(ver_datos_alumno_uservicio_ag_insc_j);
		agInscJ.add(ver_datos_alumno_jurisdiccion_ag_insc_j);

		AclPermiso agregar_inscripcion_jurisdiccion = new AclPermiso();
		agregar_inscripcion_jurisdiccion.setClase("Jurisdiccion");
		agregar_inscripcion_jurisdiccion.setPermiso("AGREGAR_INSCRIPCION");
		aclPermisoRepository.saveAndFlush(agregar_inscripcion_jurisdiccion);

		AclHerencia agregar_inscripcion_jurisdiccion_herencia = new AclHerencia();
		agregar_inscripcion_jurisdiccion_herencia.setPermisoPadre(agregar_inscripcion_jurisdiccion);
		agregar_inscripcion_jurisdiccion_herencia.setPermisosHeredados(agInscJ);
		aclHerenciaRepository.saveAndFlush(agregar_inscripcion_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_mod_insc_j = new AclPermiso();
		read_jurisdiccion_mod_insc_j.setClase("Jurisdiccion");
		read_jurisdiccion_mod_insc_j.setPermiso("READ");

		AclPermiso read_uservicio_mod_insc_j = new AclPermiso();
		read_uservicio_mod_insc_j.setClase("unidadServicio");
		read_uservicio_mod_insc_j.setPermiso("READ");

		AclPermiso ver_inscripcion_jurisdiccion_mod_insc_j = new AclPermiso();
		ver_inscripcion_jurisdiccion_mod_insc_j.setClase("Jurisdiccion");
		ver_inscripcion_jurisdiccion_mod_insc_j.setPermiso("VER_INSCRIPCIONES");

		AclPermiso ver_datos_alumno_uservicio_mod_insc_j = new AclPermiso();
		ver_datos_alumno_uservicio_mod_insc_j.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_mod_insc_j.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso ver_datos_alumno_jurisdiccion_mod_insc_j = new AclPermiso();
		ver_datos_alumno_jurisdiccion_mod_insc_j.setClase("Jurisdiccion");
		ver_datos_alumno_jurisdiccion_mod_insc_j.setPermiso("VER_DATOS_ALUMNO");

		List<AclPermiso> modInscJ = new ArrayList<>();
		modInscJ.add(read_jurisdiccion_mod_insc_j);
		modInscJ.add(read_uservicio_mod_insc_j);
		modInscJ.add(ver_inscripcion_jurisdiccion_mod_insc_j);
		modInscJ.add(ver_datos_alumno_uservicio_mod_insc_j);
		modInscJ.add(ver_datos_alumno_jurisdiccion_mod_insc_j);

		AclPermiso modificar_inscripcion_jurisdiccion = new AclPermiso();
		modificar_inscripcion_jurisdiccion.setClase("Jurisdiccion");
		modificar_inscripcion_jurisdiccion.setPermiso("MODIFICAR_INSCRIPCION");
		aclPermisoRepository.saveAndFlush(modificar_inscripcion_jurisdiccion);

		AclHerencia modificar_inscripcion_jurisdiccion_herencia = new AclHerencia();
		modificar_inscripcion_jurisdiccion_herencia.setPermisoPadre(modificar_inscripcion_jurisdiccion);
		modificar_inscripcion_jurisdiccion_herencia.setPermisosHeredados(modInscJ);
		aclHerenciaRepository.saveAndFlush(modificar_inscripcion_jurisdiccion_herencia);

		// ----- //

		AclPermiso read_jurisdiccion_ver_inscr = new AclPermiso();
		read_jurisdiccion_ver_inscr.setClase("Jurisdiccion");
		read_jurisdiccion_ver_inscr.setPermiso("READ");

		AclPermiso read_uservicio_ver_inscr = new AclPermiso();
		read_uservicio_ver_inscr.setClase("unidadServicio");
		read_uservicio_ver_inscr.setPermiso("READ");

		AclPermiso ver_datos_alumno_uservicio_ver_inscr = new AclPermiso();
		ver_datos_alumno_uservicio_ver_inscr.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_ver_inscr.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso ver_datos_alumno_jurisdiccion_ver_inscr = new AclPermiso();
		ver_datos_alumno_jurisdiccion_ver_inscr.setClase("Jurisdiccion");
		ver_datos_alumno_jurisdiccion_ver_inscr.setPermiso("VER_DATOS_ALUMNO");

		List<AclPermiso> verInscrJ = new ArrayList<>();
		verInscrJ.add(read_jurisdiccion_ver_inscr);
		verInscrJ.add(read_uservicio_ver_inscr);
		verInscrJ.add(ver_datos_alumno_uservicio_ver_inscr);
		verInscrJ.add(ver_datos_alumno_jurisdiccion_ver_inscr);

		AclPermiso ver_inscripcion_jurisdiccion = new AclPermiso();
		ver_inscripcion_jurisdiccion.setClase("Jurisdiccion");
		ver_inscripcion_jurisdiccion.setPermiso("VER_INSCRIPCIONES");
		aclPermisoRepository.saveAndFlush(ver_inscripcion_jurisdiccion);

		AclHerencia ver_inscripciones_jurisdiccion_herencia = new AclHerencia();
		ver_inscripciones_jurisdiccion_herencia.setPermisoPadre(ver_inscripcion_jurisdiccion);
		ver_inscripciones_jurisdiccion_herencia.setPermisosHeredados(verInscrJ);
		aclHerenciaRepository.saveAndFlush(ver_inscripciones_jurisdiccion_herencia);

		// ----- //

		AclPermiso ver_datos_alumno_uservicio_mod_dat_al = new AclPermiso();
		ver_datos_alumno_uservicio_mod_dat_al.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_mod_dat_al.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso ver_datos_alumno_jurisdiccion_mod_dat_al = new AclPermiso();
		ver_datos_alumno_jurisdiccion_mod_dat_al.setClase("Jurisdiccion");
		ver_datos_alumno_jurisdiccion_mod_dat_al.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso read_jurisdiccion_mod_dat_al = new AclPermiso();
		read_jurisdiccion_mod_dat_al.setClase("Jurisdiccion");
		read_jurisdiccion_mod_dat_al.setPermiso("READ");

		AclPermiso read_uservicio_mod_dat_al = new AclPermiso();
		read_uservicio_mod_dat_al.setClase("unidadServicio");
		read_uservicio_mod_dat_al.setPermiso("READ");

		List<AclPermiso> modDatAlumJ = new ArrayList<>();
		modDatAlumJ.add(read_jurisdiccion_mod_dat_al);
		modDatAlumJ.add(read_uservicio_mod_dat_al);
		modDatAlumJ.add(ver_datos_alumno_uservicio_mod_dat_al);
		modDatAlumJ.add(ver_datos_alumno_jurisdiccion_mod_dat_al);

		AclPermiso modifidicar_datos_alumno_jurisdiccion = new AclPermiso();
		modifidicar_datos_alumno_jurisdiccion.setClase("Jurisdiccion");
		modifidicar_datos_alumno_jurisdiccion.setPermiso("MODIFICAR_DATOS_ALUMNO");
		aclPermisoRepository.saveAndFlush(modifidicar_datos_alumno_jurisdiccion);

		AclHerencia modificar_datos_alumno_jurisdiccion_herencia = new AclHerencia();
		modificar_datos_alumno_jurisdiccion_herencia.setPermisoPadre(modifidicar_datos_alumno_jurisdiccion);
		modificar_datos_alumno_jurisdiccion_herencia.setPermisosHeredados(modDatAlumJ);
		aclHerenciaRepository.saveAndFlush(modificar_datos_alumno_jurisdiccion_herencia);

		///////////////////////////////
		// Herencia Unidad Servicio //
		/////////////////////////////

		AclPermiso read_uservicio_admi_us = new AclPermiso();
		read_uservicio_admi_us.setClase("unidadServicio");
		read_uservicio_admi_us.setPermiso("READ");

		List<AclPermiso> adminUS = new ArrayList<>();
		adminUS.add(read_uservicio_admi_us);

		AclPermiso administrador_uservicio = new AclPermiso();
		administrador_uservicio.setClase("UnidadServicio");
		administrador_uservicio.setPermiso("ADMINISTRATION");
		aclPermisoRepository.saveAndFlush(administrador_uservicio);

		AclHerencia administrador_uservicio_herencia = new AclHerencia();
		administrador_uservicio_herencia.setPermisoPadre(administrador_uservicio);
		administrador_uservicio_herencia.setPermisosHeredados(adminUS);
		aclHerenciaRepository.saveAndFlush(administrador_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_ver_tit_us = new AclPermiso();
		read_uservicio_ver_tit_us.setClase("unidadServicio");
		read_uservicio_ver_tit_us.setPermiso("READ");

		List<AclPermiso> verTitUS = new ArrayList<>();
		verTitUS.add(read_uservicio_ver_tit_us);

		AclPermiso ver_titulo_uservicio = new AclPermiso();
		ver_titulo_uservicio.setClase("UnidadServicio");
		ver_titulo_uservicio.setPermiso("VER_TITULO");
		aclPermisoRepository.saveAndFlush(ver_titulo_uservicio);

		AclHerencia ver_titulo_uservicio_herencia = new AclHerencia();
		ver_titulo_uservicio_herencia.setPermisoPadre(ver_titulo_uservicio);
		ver_titulo_uservicio_herencia.setPermisosHeredados(verTitUS);
		aclHerenciaRepository.saveAndFlush(ver_titulo_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_ver_inf_inst_us = new AclPermiso();
		read_uservicio_ver_inf_inst_us.setClase("unidadServicio");
		read_uservicio_ver_inf_inst_us.setPermiso("READ");

		List<AclPermiso> verInfInstUS = new ArrayList<>();
		verInfInstUS.add(read_uservicio_ver_inf_inst_us);

		AclPermiso ver_informacion_institucional_uservicio = new AclPermiso();
		ver_informacion_institucional_uservicio.setClase("UnidadServicio");
		ver_informacion_institucional_uservicio.setPermiso("VER_INFORMACION_INSTITUCIONAL");
		aclPermisoRepository.saveAndFlush(ver_informacion_institucional_uservicio);

		AclHerencia ver_informacion_institucional_uservicio_herencia = new AclHerencia();
		ver_informacion_institucional_uservicio_herencia.setPermisoPadre(ver_informacion_institucional_uservicio);
		ver_informacion_institucional_uservicio_herencia.setPermisosHeredados(verInfInstUS);
		aclHerenciaRepository.saveAndFlush(ver_informacion_institucional_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_ver_org_curs_us = new AclPermiso();
		read_uservicio_ver_org_curs_us.setClase("unidadServicio");
		read_uservicio_ver_org_curs_us.setPermiso("READ");

		List<AclPermiso> verOrgCursUS = new ArrayList<>();
		verOrgCursUS.add(read_uservicio_ver_org_curs_us);

		AclPermiso ver_organizacion_cursada_uservicio = new AclPermiso();
		ver_organizacion_cursada_uservicio.setClase("UnidadServicio");
		ver_organizacion_cursada_uservicio.setPermiso("VER_ORGANIZACION_CURSADA");
		aclPermisoRepository.saveAndFlush(ver_organizacion_cursada_uservicio);

		AclHerencia ver_organizacion_cursada_uservicio_herencia = new AclHerencia();
		ver_organizacion_cursada_uservicio_herencia.setPermisoPadre(ver_organizacion_cursada_uservicio);
		ver_organizacion_cursada_uservicio_herencia.setPermisosHeredados(verOrgCursUS);
		aclHerenciaRepository.saveAndFlush(ver_organizacion_cursada_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_ver_dat_alum_us = new AclPermiso();
		read_uservicio_ver_dat_alum_us.setClase("unidadServicio");
		read_uservicio_ver_dat_alum_us.setPermiso("READ");

		List<AclPermiso> verDatAlumUS = new ArrayList<>();
		verDatAlumUS.add(read_uservicio_ver_dat_alum_us);

		AclPermiso ver_datos_alumno_uservicio = new AclPermiso();
		ver_datos_alumno_uservicio.setClase("UnidadServicio");
		ver_datos_alumno_uservicio.setPermiso("VER_DATOS_ALUMNO");
		aclPermisoRepository.saveAndFlush(ver_datos_alumno_uservicio);

		AclHerencia ver_datos_alumno_uservicio_herencia = new AclHerencia();
		ver_datos_alumno_uservicio_herencia.setPermisoPadre(ver_datos_alumno_uservicio);
		ver_datos_alumno_uservicio_herencia.setPermisosHeredados(verDatAlumUS);
		aclHerenciaRepository.saveAndFlush(ver_datos_alumno_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_ver_insc_us = new AclPermiso();
		read_uservicio_ver_insc_us.setClase("unidadServicio");
		read_uservicio_ver_insc_us.setPermiso("READ");

		List<AclPermiso> verInscUS = new ArrayList<>();
		verInscUS.add(read_uservicio_ver_insc_us);

		AclPermiso ver_inscripcion_uservicio = new AclPermiso();
		ver_inscripcion_uservicio.setClase("UnidadServicio");
		ver_inscripcion_uservicio.setPermiso("VER_INSCRIPCIONES");
		aclPermisoRepository.saveAndFlush(ver_inscripcion_uservicio);

		AclHerencia ver_inscripciones_uservicio_herencia = new AclHerencia();
		ver_inscripciones_uservicio_herencia.setPermisoPadre(ver_inscripcion_uservicio);
		ver_inscripciones_uservicio_herencia.setPermisosHeredados(verInscUS);
		aclHerenciaRepository.saveAndFlush(ver_inscripciones_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_conf_tit_us = new AclPermiso();
		read_uservicio_conf_tit_us.setClase("unidadServicio");
		read_uservicio_conf_tit_us.setPermiso("READ");

		AclPermiso ver_titulo_uservicio_conf_tit_us = new AclPermiso();
		ver_titulo_uservicio_conf_tit_us.setClase("UnidadServicio");
		ver_titulo_uservicio_conf_tit_us.setPermiso("VER_TITULO");

		List<AclPermiso> confTitUS = new ArrayList<>();
		confTitUS.add(read_uservicio_conf_tit_us);
		confTitUS.add(ver_titulo_uservicio_conf_tit_us);

		AclPermiso confirmar_titulo_uservicio = new AclPermiso();
		confirmar_titulo_uservicio.setClase("UnidadServicio");
		confirmar_titulo_uservicio.setPermiso("CONFIRMAR_TITULO");
		aclPermisoRepository.saveAndFlush(confirmar_titulo_uservicio);

		AclHerencia confirmar_titulo_uservicio_herencia = new AclHerencia();
		confirmar_titulo_uservicio_herencia.setPermisoPadre(confirmar_titulo_uservicio);
		confirmar_titulo_uservicio_herencia.setPermisosHeredados(confTitUS);
		aclHerenciaRepository.saveAndFlush(confirmar_titulo_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_mod_tit_us = new AclPermiso();
		read_uservicio_mod_tit_us.setClase("unidadServicio");
		read_uservicio_mod_tit_us.setPermiso("READ");

		AclPermiso ver_titulo_uservicio_mod_tit_us = new AclPermiso();
		ver_titulo_uservicio_mod_tit_us.setClase("UnidadServicio");
		ver_titulo_uservicio_mod_tit_us.setPermiso("VER_TITULO");

		List<AclPermiso> modTitUS = new ArrayList<>();
		modTitUS.add(read_uservicio_mod_tit_us);
		modTitUS.add(ver_titulo_uservicio_mod_tit_us);

		AclPermiso modificar_titulo_uservicio = new AclPermiso();
		modificar_titulo_uservicio.setClase("UnidadServicio");
		modificar_titulo_uservicio.setPermiso("MODIFICAR_TITULO");
		aclPermisoRepository.saveAndFlush(modificar_titulo_uservicio);

		AclHerencia modificar_titulo_uservicio_herencia = new AclHerencia();
		modificar_titulo_uservicio_herencia.setPermisoPadre(modificar_titulo_uservicio);
		modificar_titulo_uservicio_herencia.setPermisosHeredados(modTitUS);
		aclHerenciaRepository.saveAndFlush(modificar_titulo_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_desv_tit_us = new AclPermiso();
		read_uservicio_desv_tit_us.setClase("unidadServicio");
		read_uservicio_desv_tit_us.setPermiso("READ");

		AclPermiso ver_titulo_uservicio_desv_tit_us = new AclPermiso();
		ver_titulo_uservicio_desv_tit_us.setClase("UnidadServicio");
		ver_titulo_uservicio_desv_tit_us.setPermiso("VER_TITULO");

		List<AclPermiso> desvTitUS = new ArrayList<>();
		desvTitUS.add(read_uservicio_desv_tit_us);
		desvTitUS.add(ver_titulo_uservicio_desv_tit_us);

		AclPermiso desvincular_titulo_uservicio = new AclPermiso();
		desvincular_titulo_uservicio.setClase("UnidadServicio");
		desvincular_titulo_uservicio.setPermiso("DESVINCULAR_TITULO");
		aclPermisoRepository.saveAndFlush(desvincular_titulo_uservicio);

		AclHerencia desvincular_titulo_uservicio_herencia = new AclHerencia();
		desvincular_titulo_uservicio_herencia.setPermisoPadre(desvincular_titulo_uservicio);
		desvincular_titulo_uservicio_herencia.setPermisosHeredados(desvTitUS);
		aclHerenciaRepository.saveAndFlush(desvincular_titulo_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_vinc_tit_us = new AclPermiso();
		read_uservicio_vinc_tit_us.setClase("unidadServicio");
		read_uservicio_vinc_tit_us.setPermiso("READ");

		AclPermiso ver_titulo_uservicio_vinc_tit_us = new AclPermiso();
		ver_titulo_uservicio_vinc_tit_us.setClase("UnidadServicio");
		ver_titulo_uservicio_vinc_tit_us.setPermiso("VER_TITULO");

		List<AclPermiso> vincTitUS = new ArrayList<>();
		vincTitUS.add(read_uservicio_vinc_tit_us);
		vincTitUS.add(ver_titulo_uservicio_vinc_tit_us);

		AclPermiso vincular_titulo_uservicio = new AclPermiso();
		vincular_titulo_uservicio.setClase("UnidadServicio");
		vincular_titulo_uservicio.setPermiso("VINCULAR_TITULO");
		aclPermisoRepository.saveAndFlush(vincular_titulo_uservicio);

		AclHerencia vincular_titulo_uservicio_herencia = new AclHerencia();
		vincular_titulo_uservicio_herencia.setPermisoPadre(vincular_titulo_uservicio);
		vincular_titulo_uservicio_herencia.setPermisosHeredados(vincTitUS);
		aclHerenciaRepository.saveAndFlush(vincular_titulo_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_mod_inf_inst_us = new AclPermiso();
		read_uservicio_mod_inf_inst_us.setClase("unidadServicio");
		read_uservicio_mod_inf_inst_us.setPermiso("READ");

		AclPermiso ver_informacion_institucional_uservicio_mod_inf_inst_us = new AclPermiso();
		ver_informacion_institucional_uservicio_mod_inf_inst_us.setClase("UnidadServicio");
		ver_informacion_institucional_uservicio_mod_inf_inst_us.setPermiso("VER_INFORMACION_INSTITUCIONAL");

		List<AclPermiso> modInfInstUS = new ArrayList<>();
		modInfInstUS.add(read_uservicio_mod_inf_inst_us);
		modInfInstUS.add(ver_informacion_institucional_uservicio_mod_inf_inst_us);

		AclPermiso modificar_informacion_institucional_uservicio = new AclPermiso();
		modificar_informacion_institucional_uservicio.setClase("UnidadServicio");
		modificar_informacion_institucional_uservicio.setPermiso("MODIFICAR_INFORMACION_INSTITUCIONAL");
		aclPermisoRepository.saveAndFlush(modificar_informacion_institucional_uservicio);

		AclHerencia modificar_informacion_institucional_uservicio_herencia = new AclHerencia();
		modificar_informacion_institucional_uservicio_herencia
				.setPermisoPadre(modificar_informacion_institucional_uservicio);
		modificar_informacion_institucional_uservicio_herencia.setPermisosHeredados(modInfInstUS);
		aclHerenciaRepository.saveAndFlush(modificar_informacion_institucional_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_borr_org_curs_us = new AclPermiso();
		read_uservicio_borr_org_curs_us.setClase("unidadServicio");
		read_uservicio_borr_org_curs_us.setPermiso("READ");

		AclPermiso ver_organizacion_cursada_uservicio_borr_org_curs_us = new AclPermiso();
		ver_organizacion_cursada_uservicio_borr_org_curs_us.setClase("UnidadServicio");
		ver_organizacion_cursada_uservicio_borr_org_curs_us.setPermiso("VER_ORGANIZACION_CURSADA");

		List<AclPermiso> borrOrgCursUS = new ArrayList<>();
		borrOrgCursUS.add(read_uservicio_borr_org_curs_us);
		borrOrgCursUS.add(ver_organizacion_cursada_uservicio_borr_org_curs_us);

		AclPermiso borrar_organizacion_cursada_uservicio = new AclPermiso();
		borrar_organizacion_cursada_uservicio.setClase("UnidadServicio");
		borrar_organizacion_cursada_uservicio.setPermiso("BORRAR_ORGANIZACION_CURSADA");
		aclPermisoRepository.saveAndFlush(borrar_organizacion_cursada_uservicio);

		AclHerencia borrar_organizacion_cursada_uservicio_herencia = new AclHerencia();
		borrar_organizacion_cursada_uservicio_herencia.setPermisoPadre(borrar_organizacion_cursada_uservicio);
		borrar_organizacion_cursada_uservicio_herencia.setPermisosHeredados(borrOrgCursUS);
		aclHerenciaRepository.saveAndFlush(borrar_organizacion_cursada_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_mod_org_curs_us = new AclPermiso();
		read_uservicio_mod_org_curs_us.setClase("unidadServicio");
		read_uservicio_mod_org_curs_us.setPermiso("READ");

		AclPermiso ver_organizacion_cursada_uservicio_mod_org_curs_us = new AclPermiso();
		ver_organizacion_cursada_uservicio_mod_org_curs_us.setClase("UnidadServicio");
		ver_organizacion_cursada_uservicio_mod_org_curs_us.setPermiso("VER_ORGANIZACION_CURSADA");

		List<AclPermiso> modOrgCursUS = new ArrayList<>();
		modOrgCursUS.add(read_uservicio_mod_org_curs_us);
		modOrgCursUS.add(ver_organizacion_cursada_uservicio_mod_org_curs_us);

		AclPermiso modificar_organizacion_cursada_uservicio = new AclPermiso();
		modificar_organizacion_cursada_uservicio.setClase("UnidadServicio");
		modificar_organizacion_cursada_uservicio.setPermiso("MODIFICAR_ORGANIZACION_CURSADA");
		aclPermisoRepository.saveAndFlush(modificar_organizacion_cursada_uservicio);

		AclHerencia modificar_organizacion_cursada_uservicio_herencia = new AclHerencia();
		modificar_organizacion_cursada_uservicio_herencia.setPermisoPadre(modificar_organizacion_cursada_uservicio);
		modificar_organizacion_cursada_uservicio_herencia.setPermisosHeredados(modOrgCursUS);
		aclHerenciaRepository.saveAndFlush(modificar_organizacion_cursada_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_borr_insc_us = new AclPermiso();
		read_uservicio_borr_insc_us.setClase("unidadServicio");
		read_uservicio_borr_insc_us.setPermiso("READ");

		AclPermiso ver_datos_alumno_uservicio_borr_insc_us = new AclPermiso();
		ver_datos_alumno_uservicio_borr_insc_us.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_borr_insc_us.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso ver_inscripcion_uservicio_borr_insc_us = new AclPermiso();
		ver_inscripcion_uservicio_borr_insc_us.setClase("UnidadServicio");
		ver_inscripcion_uservicio_borr_insc_us.setPermiso("VER_INSCRIPCIONES");

		List<AclPermiso> borrInscUS = new ArrayList<>();
		borrInscUS.add(read_uservicio_borr_insc_us);
		borrInscUS.add(ver_datos_alumno_uservicio_borr_insc_us);
		borrInscUS.add(ver_inscripcion_uservicio_borr_insc_us);

		AclPermiso borrar_inscripcion_uservicio = new AclPermiso();
		borrar_inscripcion_uservicio.setClase("UnidadServicio");
		borrar_inscripcion_uservicio.setPermiso("BORRAR_INSCRIPCION");
		aclPermisoRepository.saveAndFlush(borrar_inscripcion_uservicio);

		AclHerencia borrar_inscripcion_uservicio_herencia = new AclHerencia();
		borrar_inscripcion_uservicio_herencia.setPermisoPadre(borrar_inscripcion_uservicio);
		borrar_inscripcion_uservicio_herencia.setPermisosHeredados(borrInscUS);
		aclHerenciaRepository.saveAndFlush(borrar_inscripcion_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_agr_inscr_us = new AclPermiso();
		read_uservicio_agr_inscr_us.setClase("unidadServicio");
		read_uservicio_agr_inscr_us.setPermiso("READ");

		AclPermiso ver_datos_alumno_uservicio_agr_inscr_us = new AclPermiso();
		ver_datos_alumno_uservicio_agr_inscr_us.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_agr_inscr_us.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso ver_inscripcion_uservicio_agr_inscr_us = new AclPermiso();
		ver_inscripcion_uservicio_agr_inscr_us.setClase("UnidadServicio");
		ver_inscripcion_uservicio_agr_inscr_us.setPermiso("VER_INSCRIPCIONES");

		List<AclPermiso> agrInscrUS = new ArrayList<>();
		agrInscrUS.add(read_uservicio_agr_inscr_us);
		agrInscrUS.add(ver_datos_alumno_uservicio_agr_inscr_us);
		agrInscrUS.add(ver_inscripcion_uservicio_agr_inscr_us);

		AclPermiso agregar_inscripcion_uservicio = new AclPermiso();
		agregar_inscripcion_uservicio.setClase("UnidadServicio");
		agregar_inscripcion_uservicio.setPermiso("AGREGAR_INSCRIPCION");
		aclPermisoRepository.saveAndFlush(agregar_inscripcion_uservicio);

		AclHerencia agregar_inscripcion_uservicio_herencia = new AclHerencia();
		agregar_inscripcion_uservicio_herencia.setPermisoPadre(agregar_inscripcion_uservicio);
		agregar_inscripcion_uservicio_herencia.setPermisosHeredados(agrInscrUS);
		aclHerenciaRepository.saveAndFlush(agregar_inscripcion_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_mod_insc_us = new AclPermiso();
		read_uservicio_mod_insc_us.setClase("unidadServicio");
		read_uservicio_mod_insc_us.setPermiso("READ");

		AclPermiso ver_datos_alumno_uservicio_mod_insc_us = new AclPermiso();
		ver_datos_alumno_uservicio_mod_insc_us.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_mod_insc_us.setPermiso("VER_DATOS_ALUMNO");

		AclPermiso ver_inscripcion_uservicio_mod_insc_us = new AclPermiso();
		ver_inscripcion_uservicio_mod_insc_us.setClase("UnidadServicio");
		ver_inscripcion_uservicio_mod_insc_us.setPermiso("VER_INSCRIPCIONES");

		List<AclPermiso> modInscrUS = new ArrayList<>();
		modInscrUS.add(read_uservicio_mod_insc_us);
		modInscrUS.add(ver_datos_alumno_uservicio_mod_insc_us);
		modInscrUS.add(ver_inscripcion_uservicio_mod_insc_us);

		AclPermiso modificar_inscripcion_uservicio = new AclPermiso();
		modificar_inscripcion_uservicio.setClase("UnidadServicio");
		modificar_inscripcion_uservicio.setPermiso("MODIFICAR_INSCRIPCION");
		aclPermisoRepository.saveAndFlush(modificar_inscripcion_uservicio);

		AclHerencia modificar_inscripcion_uservicio_herencia = new AclHerencia();
		modificar_inscripcion_uservicio_herencia.setPermisoPadre(modificar_inscripcion_uservicio);
		modificar_inscripcion_uservicio_herencia.setPermisosHeredados(modInscrUS);
		aclHerenciaRepository.saveAndFlush(modificar_inscripcion_uservicio_herencia);

		// ----- //

		AclPermiso read_uservicio_mod_dat_alum_us = new AclPermiso();
		read_uservicio_mod_dat_alum_us.setClase("unidadServicio");
		read_uservicio_mod_dat_alum_us.setPermiso("READ");

		AclPermiso ver_datos_alumno_uservicio_mod_dat_alum_us = new AclPermiso();
		ver_datos_alumno_uservicio_mod_dat_alum_us.setClase("UnidadServicio");
		ver_datos_alumno_uservicio_mod_dat_alum_us.setPermiso("VER_DATOS_ALUMNO");

		List<AclPermiso> modDatAlumUS = new ArrayList<>();
		modDatAlumUS.add(read_uservicio_mod_dat_alum_us);
		modDatAlumUS.add(ver_datos_alumno_uservicio_mod_dat_alum_us);

		AclPermiso modificar_datos_alumno_uservicio = new AclPermiso();
		modificar_datos_alumno_uservicio.setClase("UnidadServicio");
		modificar_datos_alumno_uservicio.setPermiso("MODIFICAR_DATOS_ALUMNO");
		aclPermisoRepository.saveAndFlush(modificar_datos_alumno_uservicio);

		AclHerencia modificar_datos_alumno_uservicio_herencia = new AclHerencia();
		modificar_datos_alumno_uservicio_herencia.setPermisoPadre(modificar_datos_alumno_uservicio);
		modificar_datos_alumno_uservicio_herencia.setPermisosHeredados(modDatAlumUS);
		aclHerenciaRepository.saveAndFlush(modificar_datos_alumno_uservicio_herencia);

		////////////////////////
		// Herencia Sección //
		//////////////////////

		AclPermiso tomar_asistencia_seccion = new AclPermiso();
		tomar_asistencia_seccion.setClase("Seccion");
		tomar_asistencia_seccion.setPermiso("TOMAR_ASISTENCIA");
		aclPermisoRepository.saveAndFlush(tomar_asistencia_seccion);

		AclPermiso read_uservicio_sec = new AclPermiso();
		read_uservicio_sec.setClase("unidadServicio");
		read_uservicio_sec.setPermiso("READ");

		AclPermiso ver_organizacion_cursada_uservicio_sec = new AclPermiso();
		ver_organizacion_cursada_uservicio_sec.setClase("UnidadServicio");
		ver_organizacion_cursada_uservicio_sec.setPermiso("VER_ORGANIZACION_CURSADA");

		List<AclPermiso> secc = new ArrayList<>();
		secc.add(read_uservicio_sec);
		secc.add(ver_organizacion_cursada_uservicio_sec);

		AclHerencia tomar_asistencia_seccion_herencia = new AclHerencia();
		tomar_asistencia_seccion_herencia.setPermisoPadre(tomar_asistencia_seccion);
		tomar_asistencia_seccion_herencia.setPermisosHeredados(secc);
		aclHerenciaRepository.saveAndFlush(tomar_asistencia_seccion_herencia);

		///////////////////////////////////
		// Herencia Sección Curricular //
		/////////////////////////////////

		AclPermiso tomar_asistencia_seccion_curricular = new AclPermiso();
		tomar_asistencia_seccion_curricular.setClase("SeccionCurricular");
		tomar_asistencia_seccion_curricular.setPermiso("TOMAR_ASISTENCIA");
		aclPermisoRepository.saveAndFlush(tomar_asistencia_seccion_curricular);

		AclPermiso read_uservicio_secc = new AclPermiso();
		read_uservicio_secc.setClase("unidadServicio");
		read_uservicio_secc.setPermiso("READ");

		AclPermiso ver_organizacion_cursada_uservicio_secc = new AclPermiso();
		ver_organizacion_cursada_uservicio_secc.setClase("UnidadServicio");
		ver_organizacion_cursada_uservicio_secc.setPermiso("VER_ORGANIZACION_CURSADA");

		List<AclPermiso> seccCurr = new ArrayList<>();
		seccCurr.add(read_uservicio_secc);
		seccCurr.add(ver_organizacion_cursada_uservicio_secc);

		AclHerencia tomar_asistencia_seccion_curricular_herencia = new AclHerencia();
		tomar_asistencia_seccion_curricular_herencia.setPermisoPadre(tomar_asistencia_seccion_curricular);
		tomar_asistencia_seccion_curricular_herencia.setPermisosHeredados(seccCurr);
		aclHerenciaRepository.saveAndFlush(tomar_asistencia_seccion_curricular_herencia);

	}

	@Test
	public void testPuede() {
	}
}
