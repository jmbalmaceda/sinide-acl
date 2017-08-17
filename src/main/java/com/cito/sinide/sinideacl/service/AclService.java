package com.cito.sinide.sinideacl.service;

import java.util.List;

public interface AclService {
	
	public List<Long> getIdsJurisdiccion();
	
	public List<Long> getIdsNivelServicioDadoJurisdiccion(Long idJurisdiccion);
	
	public List<Long> getIdsUnidadServicioDadoNivelServicio(Long idNivelServicio);
	
	public List<Long> getIdsUnidadServicioDadoJurisdiccion(Long idJurisdiccion);	
	
	public List<Long> getIdsSeccionDadoUnidadServicio(Long idUnidadServicio);
	
	public List<Long> getIdsSeccionDadoNivelServicio(Long idNivelServicio);
	
	public List<Long> getIdsSeccionDadoJurisdiccion(Long idJurisdiccion);
	
	public List<Long> getIdsSeccionCurricularDadoSeccion(Long idSeccion);
	
	public List<Long> getIdsSeccionCurricularDadoUnidadServicio(Long idUnidadServicio);
	
	public List<Long> getIdsSeccionCurricularDadoNivelServicio(Long idNivelServicio);
	
	public List<Long> getIdsSeccionCurricularDadoJurisdiccion(Long idJurisdiccion);
}
