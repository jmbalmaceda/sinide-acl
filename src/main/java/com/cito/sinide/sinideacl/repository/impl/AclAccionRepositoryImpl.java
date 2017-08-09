package com.cito.sinide.sinideacl.repository.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cito.sinide.sinideacl.entity.AclAccion;
import com.cito.sinide.sinideacl.repository.AclAccionRepositoryCustom;

public class AclAccionRepositoryImpl implements AclAccionRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean puede(Long idUsuario, String accion, String clase, Long id,
			Hashtable<String, Long> info) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<AclAccion> rootAccion = cq.from(AclAccion.class);
		List<Predicate> listaPredicados = armarListaPredicados(idUsuario, accion, clase, id, info, cb, rootAccion);
		cq.select(cb.count(rootAccion));
		cq.where(cb.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
		Long count = entityManager.createQuery(cq).getSingleResult();
		return count>0;
	}

	private List<Predicate> armarListaPredicados(Long idUsuario, String accion,
			String clase, Long id, Hashtable<String, Long> info,
			CriteriaBuilder cb, Root<AclAccion> rootAccion) {
		List<Predicate> listaPredicados = new ArrayList<Predicate>();
		
		listaPredicados.add(cb.equal(rootAccion.get("idUsuario"), idUsuario));
		listaPredicados.add(cb.equal(rootAccion.get("accion"), accion));
		
		boolean filtroSobreJurisdiccion = false;
		boolean filtroSobreNivelServicio = false;
		boolean filtroSobreUnidadServicio = false;
		boolean filtroSobreSeccion = false;
		boolean filtroSobreSeccionCurricular = false;
		
		switch (clase) {
		/* si está verificando por una instancia de jurisdicción, tengo que verificar
		 * que el permiso para realizar dicha acción esté asociado a dicha jurisdicción o a cualquier (valor null)
		*/
		case "jurisdiccion":
			listaPredicados.add(cb.or(cb.equal(rootAccion.get("idJurisdiccion"), id), cb.isNull(rootAccion.get("idJurisdiccion"))));
			filtroSobreJurisdiccion = true;
			break;
		case "nivelServicio":
			listaPredicados.add(cb.or(cb.equal(rootAccion.get("idNivelServicio"), id), cb.isNull(rootAccion.get("idNivelServicio"))));
			filtroSobreNivelServicio = true;
			break;
		}
		
		/*
		 * Para el restro de los atributos no filtrados, se debe verificar que se cumplan en el contexto de la verificación.
		 */
		if (!filtroSobreJurisdiccion){
			Long idJurisdiccion = info.get("jurisdiccion");
			if (idJurisdiccion!=null){
				listaPredicados.add(cb.or(cb.equal(rootAccion.get("idJurisdiccion"), idJurisdiccion), cb.isNull(rootAccion.get("idJurisdiccion"))));
			}
		}
		
		return listaPredicados ;
	}

}
