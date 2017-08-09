package com.cito.sinide.sinideacl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matriz_acciones")
public class Accion {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "id_user")
	private Long idUser;
	
	@Column(name = "accion")
	private String accion;
	
	@Column(name="id_jurisdiccion")
	private Long idJurisdiccion;
	
	@Column(name="id_nivel_servicio")
	private Long idNivelServicio;
	
	@Column(name="id_unidad_servicio")
	private Long idUnidadServicio;
	
	@Column(name="id_seccion")
	private Long idSeccion;
	
	@Column(name="id_seccion_curricular")
	private Long idSeccionCurricular;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Long getIdJurisdiccion() {
		return idJurisdiccion;
	}

	public void setIdJurisdiccion(Long idJurisdiccion) {
		this.idJurisdiccion = idJurisdiccion;
	}

	public Long getIdNivelServicio() {
		return idNivelServicio;
	}

	public void setIdNivelServicio(Long idNivelServicio) {
		this.idNivelServicio = idNivelServicio;
	}

	public Long getIdUnidadServicio() {
		return idUnidadServicio;
	}

	public void setIdUnidadServicio(Long idUnidadServicio) {
		this.idUnidadServicio = idUnidadServicio;
	}

	public Long getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(Long idSeccion) {
		this.idSeccion = idSeccion;
	}

	public Long getIdSeccionCurricular() {
		return idSeccionCurricular;
	}

	public void setIdSeccionCurricular(Long idSeccionCurricular) {
		this.idSeccionCurricular = idSeccionCurricular;
	}
	
	
}
