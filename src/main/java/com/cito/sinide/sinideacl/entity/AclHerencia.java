package com.cito.sinide.sinideacl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acl_herencia_permisos")
public class AclHerencia {
	@Id
	@Column(name = "id_herencia")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idHerencia;

	@Column(name = "accion_padre")
	private String accionPadre;
	
	@Column(name = "tipo_padre")
	private String tipoPadre;

	@Column(name = "accion_heredado")
	private String accionHeredado;

	@Column(name = "tipo_heredado")
	private String tipoHeredado;
	
	public Long getIdHerencia() {
		return idHerencia;
	}

	public void setIdHerencia(Long idHerencia) {
		this.idHerencia = idHerencia;
	}

	public String getAccionPadre() {
		return accionPadre;
	}

	public void setAccionPadre(String accionPadre) {
		this.accionPadre = accionPadre;
	}

	public String getAccionHeredado() {
		return accionHeredado;
	}

	public void setAccionHeredado(String accionHeredado) {
		this.accionHeredado = accionHeredado;
	}

	public String getTipoPadre() {
		return tipoPadre;
	}

	public void setTipoPadre(String tipoPadre) {
		this.tipoPadre = tipoPadre;
	}

	public String getTipoHeredado() {
		return tipoHeredado;
	}

	public void setTipoHeredado(String tipoHeredado) {
		this.tipoHeredado = tipoHeredado;
	}

}
