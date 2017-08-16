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

import org.springframework.beans.factory.annotation.Autowired;

import com.cito.sinide.sinideacl.entity.AclAccion;
import com.cito.sinide.sinideacl.entity.AclHerencia;
import com.cito.sinide.sinideacl.repository.AclAccionRepositoryCustom;
import com.cito.sinide.sinideacl.repository.AclHerenciaRepository;

public class AclAccionRepositoryImpl implements AclAccionRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	AclHerenciaRepository aclHerenciaRepository;

	@Override
	public boolean puede(Long idUsuario, String accion, String clase, Long id, Hashtable<String, Long> info) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<AclAccion> rootAccion = cq.from(AclAccion.class);
		List<Predicate> listaPredicados = armarListaPredicados(idUsuario, accion, clase, id, info, cb, rootAccion);
		cq.select(cb.count(rootAccion));
		cq.where(cb.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
		Long count = entityManager.createQuery(cq).getSingleResult();

		if (count > 0)
			return true;
		else {
			List<AclHerencia> permisosPadres = aclHerenciaRepository.findByPermisoHeredado(accion);
			for (AclHerencia aclHerencia : permisosPadres) {
				boolean puede = puede(idUsuario, aclHerencia.getPermisoPadre(), clase, id, info);
				if (puede)
					return true;
			}
		}
		return false;
	}

	private List<Predicate> armarListaPredicados(Long idUsuario, String accion, String clase, Long id,
			Hashtable<String, Long> info, CriteriaBuilder cb, Root<AclAccion> rootAccion) {
		List<Predicate> listaPredicados = new ArrayList<Predicate>();

		listaPredicados.add(cb.equal(rootAccion.get("idUsuario"), idUsuario));
		listaPredicados.add(cb.equal(rootAccion.get("accion"), accion));

		boolean filtroSobreJurisdiccion = false;
		boolean filtroSobreNivelServicio = false;
		boolean filtroSobreUnidadServicio = false;
		boolean filtroSobreSeccion = false;
		boolean filtroSobreSeccionCurricular = false;

		switch (clase) {
		/*
		 * si está verificando por una instancia de jurisdicción, tengo que verificar
		 * que el permiso para realizar dicha acción esté asociado a dicha jurisdicción
		 * o a cualquiera (valor null)
		 */
		case "jurisdiccion":
			listaPredicados.add(
					cb.or(cb.equal(rootAccion.get("idJurisdiccion"), id), cb.isNull(rootAccion.get("idJurisdiccion"))));
			filtroSobreJurisdiccion = true;
			break;
		/* idem nivelServicio */
		case "nivelServicio":
			listaPredicados.add(cb.or(cb.equal(rootAccion.get("idNivelServicio"), id),
					cb.isNull(rootAccion.get("idNivelServicio"))));
			filtroSobreNivelServicio = true;
			break;
		/* idem unidadServicio */
		case "unidadServicio":
			listaPredicados.add(cb.or(cb.equal(rootAccion.get("idUnidadServicio"), id),
					cb.isNull(rootAccion.get("idUnidadServicio"))));
			filtroSobreUnidadServicio = true;
			break;
		/* idem seccion */
		case "seccion":
			listaPredicados
					.add(cb.or(cb.equal(rootAccion.get("idSeccion"), id), cb.isNull(rootAccion.get("idSeccion"))));
			filtroSobreSeccion = true;
			break;
		/* idem seccionCurricular */
		case "seccionCurricular":
			listaPredicados.add(cb.or(cb.equal(rootAccion.get("idSeccionCurricular"), id),
					cb.isNull(rootAccion.get("idSeccionCurricular"))));
			filtroSobreSeccionCurricular = true;
			break;
		}

		/*
		 * Para el restro de los atributos no filtrados, se debe verificar que se
		 * cumplan en el contexto de la verificación.
		 */

		if (filtroSobreJurisdiccion) {
			listaPredicados.add(
					cb.and(cb.isNull(rootAccion.get("idNivelServicio")), cb.isNull(rootAccion.get("idUnidadServicio")),
							cb.isNull(rootAccion.get("idSeccion")), cb.isNull(rootAccion.get("idSeccionCurricular"))));
		} else {
			Long idJurisdiccion = info.get("jurisdiccion");
			if (idJurisdiccion != null) {
				listaPredicados.add(cb.or(cb.equal(rootAccion.get("idJurisdiccion"), idJurisdiccion),
						cb.isNull(rootAccion.get("idJurisdiccion"))));
			}

			if (filtroSobreNivelServicio) {
				listaPredicados.add(cb.and(cb.isNull(rootAccion.get("idUnidadServicio")),
						cb.isNull(rootAccion.get("idSeccion")), cb.isNull(rootAccion.get("idSeccionCurricular"))));
			} else {
				Long idNivelServicio = info.get("nivelServicio");
				if (idNivelServicio != null) {
					listaPredicados.add(cb.or(cb.equal(rootAccion.get("idNivelServicio"), idNivelServicio),
							cb.isNull(rootAccion.get("idNivelServicio"))));
				}

				if (filtroSobreUnidadServicio) {
					listaPredicados.add(cb.and(cb.isNull(rootAccion.get("idSeccion")),
							cb.isNull(rootAccion.get("idSeccionCurricular"))));
				} else {
					Long idUnidadServicio = info.get("unidadServicio");
					if (idUnidadServicio != null) {
						listaPredicados.add(cb.or(cb.equal(rootAccion.get("idUnidadServicio"), idUnidadServicio),
								cb.isNull(rootAccion.get("idUnidadServicio"))));
					}

					if (filtroSobreSeccion) {
						listaPredicados.add(cb.and(cb.isNull(rootAccion.get("idSeccionCurricular"))));
					} else {
						Long idSeccion = info.get("seccion");
						if (idSeccion != null) {
							listaPredicados.add(cb.or(cb.equal(rootAccion.get("idSeccion"), idSeccion),
									cb.isNull(rootAccion.get("idSeccion"))));
						}

					}
				}
			}
		}

		// if (!filtroSobreJurisdiccion) {
		// Long idJurisdiccion = info.get("jurisdiccion");
		// if (idJurisdiccion != null) {
		// listaPredicados.add(cb.or(cb.equal(rootAccion.get("idJurisdiccion"),
		// idJurisdiccion),
		// cb.isNull(rootAccion.get("idJurisdiccion"))));
		// }
		// }
		// if (!filtroSobreNivelServicio) {
		// Long idNivelServicio = info.get("nivelServicio");
		// if (idNivelServicio != null) {
		// listaPredicados.add(cb.or(cb.equal(rootAccion.get("idNivelServicio"),
		// idNivelServicio),
		// cb.isNull(rootAccion.get("idNivelServicio"))));
		// }
		// }
		// if (!filtroSobreUnidadServicio) {
		// Long idUnidadServicio = info.get("unidadServicio");
		// if (idUnidadServicio != null) {
		// listaPredicados.add(cb.or(cb.equal(rootAccion.get("idUnidadServicio"),
		// idUnidadServicio),
		// cb.isNull(rootAccion.get("idUnidadServicio"))));
		// }
		// }
		// if (!filtroSobreSeccion) {
		// Long idSeccion = info.get("seccion");
		// if (idSeccion != null) {
		// listaPredicados.add(cb.or(cb.equal(rootAccion.get("idSeccion"), idSeccion),
		// cb.isNull(rootAccion.get("idSeccion"))));
		// }
		// }
		// if (!filtroSobreSeccionCurricular) {
		// Long idSeccionCurricular = info.get("seccionCurricular");
		// if (idSeccionCurricular != null) {
		// listaPredicados.add(cb.or(cb.equal(rootAccion.get("idSeccionCurricular"),
		// idSeccionCurricular),
		// cb.isNull(rootAccion.get("idSeccionCurricular"))));
		// }
		// }

		return listaPredicados;
	}
}
