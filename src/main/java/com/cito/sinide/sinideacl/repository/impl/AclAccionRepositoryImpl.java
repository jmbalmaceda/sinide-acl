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
import com.cito.sinide.sinideacl.entity.AclAccionClase;
import com.cito.sinide.sinideacl.entity.AclHerencia;
import com.cito.sinide.sinideacl.repository.AclAccionClaseRepository;
import com.cito.sinide.sinideacl.repository.AclAccionRepository;
import com.cito.sinide.sinideacl.repository.AclAccionRepositoryCustom;
import com.cito.sinide.sinideacl.repository.AclHerenciaRepository;
import com.cito.sinide.sinideacl.service.AclService;

public class AclAccionRepositoryImpl implements AclAccionRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	AclHerenciaRepository aclHerenciaRepository;

	@Autowired
	AclAccionClaseRepository aclAccionClaseRepository;

	@Autowired
	AclAccionRepository aclAccionRepository;

	@Autowired
	AclService aclService;

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

	@Override
	public List<String> getPermisos(Long idUsuario, String clase, Long id, Hashtable<String, Long> inf) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AclAccion> cq = cb.createQuery(AclAccion.class);
		Root<AclAccion> rootAccion = cq.from(AclAccion.class);
		List<Predicate> listaPredicados = armarListaPredicadosAcciones(idUsuario, clase, id, inf, cb, rootAccion);
		cq.where(cb.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
		List<AclAccion> resultAccionList = entityManager.createQuery(cq).getResultList();

		List<String> result = new ArrayList<>();

		for (AclAccion aclAccion : resultAccionList) {
			List<AclAccionClase> claseAndAccion = aclAccionClaseRepository.findByClaseAndAccion(clase,
					aclAccion.getAccion());
			if ((!claseAndAccion.isEmpty()) && (!result.contains(aclAccion.getAccion()))) {
				result.add(aclAccion.getAccion());
			} else {
				List<AclHerencia> permisoPadre = aclHerenciaRepository.findByPermisoPadre(aclAccion.getAccion());
				for (AclHerencia aclHerencia : permisoPadre) {
					List<AclAccionClase> claseAndAccion2 = aclAccionClaseRepository.findByClaseAndAccion(clase,
							aclHerencia.getPermisoHeredado());
					if ((!claseAndAccion2.isEmpty()) && (!result.contains(aclHerencia.getPermisoHeredado())))
						result.add(aclHerencia.getPermisoHeredado());
				}
			}
		}

		return result;
	}

	public List<Long> getIdsPermiso(Long idUsuario, String accion, String clase, Hashtable<String, Long> info) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AclAccion> cq = cb.createQuery(AclAccion.class);
		Root<AclAccion> rootAccion = cq.from(AclAccion.class);
		List<Predicate> listaPredicados = armarListaPredicadosIdsPermisos(idUsuario, accion, clase, info, cb,
				rootAccion);
		cq.where(cb.and(listaPredicados.toArray(new Predicate[listaPredicados.size()])));
		List<AclAccion> resultList = entityManager.createQuery(cq).getResultList();

		List<Long> result = new ArrayList<>();

		for (AclAccion aclAccion : resultList) {

			switch (clase) {

			case "jurisdiccion":
				if (aclAccion.getIdJurisdiccion() != null) {
					if (!result.contains(aclAccion.getIdJurisdiccion()))
						result.add(aclAccion.getIdJurisdiccion());
				} else {
					List<Long> idsJurisdiccion = aclService.getIdsJurisdiccion();
					for (Long jur1 : idsJurisdiccion) {
						if (!result.contains(jur1)) {
							result.add(jur1);
						}
					}
				}

				break;

			case "nivelServicio":
				if (aclAccion.getIdNivelServicio() != null) {
					if (!result.contains(aclAccion.getIdNivelServicio()))
						result.add(aclAccion.getIdNivelServicio());
				} else {
					if (aclAccion.getIdJurisdiccion() != null) {
						List<Long> idsNivelServicioDadoJurisdiccion = aclService
								.getIdsNivelServicioDadoJurisdiccion(aclAccion.getIdJurisdiccion());
						for (Long idsNivServ : idsNivelServicioDadoJurisdiccion) {
							if (!result.contains(idsNivServ)) {
								result.add(idsNivServ);
							}
						}
					}
				}
				break;

			case "unidadServicio":
				if (aclAccion.getIdUnidadServicio() != null) {
					if (!result.contains(aclAccion.getIdUnidadServicio()))
						result.add(aclAccion.getIdUnidadServicio());
				} else {
					if (aclAccion.getIdNivelServicio() != null) {
						List<Long> idsUnidadServicioDadoNivelServicio = aclService
								.getIdsUnidadServicioDadoNivelServicio(aclAccion.getIdNivelServicio());
						for (Long idsUnidServN : idsUnidadServicioDadoNivelServicio) {
							if (!result.contains(idsUnidServN)) {
								result.add(idsUnidServN);
							}
						}
					} else if (aclAccion.getIdJurisdiccion() != null) {
						List<Long> idsUnidadServicioDadoJurisdiccion = aclService
								.getIdsUnidadServicioDadoJurisdiccion(aclAccion.getIdJurisdiccion());
						for (Long idsUnidServJ : idsUnidadServicioDadoJurisdiccion) {
							if (!result.contains(idsUnidServJ)) {
								result.add(idsUnidServJ);
							}
						}
					}
				}
				break;

			case "seccion":
				if (aclAccion.getIdSeccion() != null) {
					if (!result.contains(aclAccion.getIdSeccion()))
						result.add(aclAccion.getIdSeccion());
				} else {
					if (aclAccion.getIdUnidadServicio() != null) {
						List<Long> idsSeccionDadoUnidadServicio = aclService.getIdsSeccionDadoUnidadServicio(aclAccion.getIdUnidadServicio());
						for (Long idsSeccU : idsSeccionDadoUnidadServicio) {
							if (!result.contains(idsSeccU)) {
								result.add(idsSeccU);
							}
						}
					} else if (aclAccion.getIdNivelServicio() != null) {
						List<Long> idsSeccionDadoNivelServicio = aclService.getIdsSeccionDadoNivelServicio(aclAccion.getIdNivelServicio());
						for (Long idsSeccN : idsSeccionDadoNivelServicio) {
							if (!result.contains(idsSeccN)) {
								result.add(idsSeccN);
							}
						}
					} else if (aclAccion.getIdJurisdiccion() != null) {
						List<Long> idsSeccionDadoJurisdiccion = aclService.getIdsSeccionDadoJurisdiccion(aclAccion.getIdJurisdiccion());
								
						for (Long idsSeccJ : idsSeccionDadoJurisdiccion) {
							if (!result.contains(idsSeccJ)) {
								result.add(idsSeccJ);
							}
						}
					}
				}
				break;

			case "seccionCurricular":
				if (aclAccion.getIdSeccionCurricular() != null) {
					if (!result.contains(aclAccion.getIdSeccionCurricular()))
						result.add(aclAccion.getIdSeccionCurricular());
				} else {
					if (aclAccion.getIdSeccion() != null) {
						List<Long> idsSeccionCurricularDadoSeccion = aclService.getIdsSeccionCurricularDadoSeccion(aclAccion.getIdSeccion());
						for (Long idsSeccCurrS : idsSeccionCurricularDadoSeccion) {
							if (!result.contains(idsSeccCurrS)) {
								result.add(idsSeccCurrS);
							}
						}
					} else if (aclAccion.getIdUnidadServicio() != null) {
						List<Long> idsSeccionDadoUnidadServicio = aclService.getIdsSeccionCurricularDadoUnidadServicio(aclAccion.getIdUnidadServicio());
						for (Long idsSeccCurrU : idsSeccionDadoUnidadServicio) {
							if (!result.contains(idsSeccCurrU)) {
								result.add(idsSeccCurrU);
							}
						}
					} else if (aclAccion.getIdNivelServicio() != null) {
						List<Long> idsSeccionCurricularDadoNivelServicio = aclService.getIdsSeccionCurricularDadoNivelServicio(aclAccion.getIdNivelServicio());
						for (Long idsSeccCurrN : idsSeccionCurricularDadoNivelServicio) {
							if (!result.contains(idsSeccCurrN)) {
								result.add(idsSeccCurrN);
							}
						}
					} else if (aclAccion.getIdJurisdiccion() != null) {
						List<Long> idsSeccionCurricularDadoJurisdiccion = aclService.getIdsSeccionCurricularDadoJurisdiccion(aclAccion.getIdJurisdiccion());
						for (Long idsSeccCurrJ : idsSeccionCurricularDadoJurisdiccion) {
							if (!result.contains(idsSeccCurrJ)) {
								result.add(idsSeccCurrJ);
							}
						}
					}
				}
				break;

			}
		}

		return result;
	}

	private List<Predicate> armarListaPredicadosIdsPermisos(Long idUsuario, String accion, String clase,
			Hashtable<String, Long> info, CriteriaBuilder cb, Root<AclAccion> rootAccion) {
		List<Predicate> listaPredicados = new ArrayList<Predicate>();

		listaPredicados.add(cb.equal(rootAccion.get("idUsuario"), idUsuario));
		listaPredicados.add(cb.equal(rootAccion.get("accion"), accion));

		boolean filtroSobreJurisdiccion = false;
		boolean filtroSobreNivelServicio = false;
		boolean filtroSobreUnidadServicio = false;
		boolean filtroSobreSeccion = false;
		@SuppressWarnings("unused")
		boolean filtroSobreSeccionCurricular = false;

		switch (clase) {
		/*
		 * si está verificando por una instancia de jurisdicción, tengo que verificar
		 * que el permiso para realizar dicha acción esté asociado a dicha jurisdicción
		 * o a cualquiera (valor null)
		 */
		case "jurisdiccion":
			filtroSobreJurisdiccion = true;
			break;
		/* idem nivelServicio */
		case "nivelServicio":
			filtroSobreNivelServicio = true;
			break;
		/* idem unidadServicio */
		case "unidadServicio":
			filtroSobreUnidadServicio = true;
			break;
		/* idem seccion */
		case "seccion":
			filtroSobreSeccion = true;
			break;
		/* idem seccionCurricular */
		case "seccionCurricular":
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

		return listaPredicados;
	}

	private List<Predicate> armarListaPredicadosAcciones(Long idUsuario, String clase, Long id,
			Hashtable<String, Long> inf, CriteriaBuilder cb, Root<AclAccion> rootAccion) {
		List<Predicate> listaPredicados = new ArrayList<Predicate>();

		listaPredicados.add(cb.equal(rootAccion.get("idUsuario"), idUsuario));

		boolean filtroSobreJurisdiccion = false;
		boolean filtroSobreNivelServicio = false;
		boolean filtroSobreUnidadServicio = false;
		boolean filtroSobreSeccion = false;
		@SuppressWarnings("unused")
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
			Long idJurisdiccion = inf.get("jurisdiccion");
			if (idJurisdiccion != null) {
				listaPredicados.add(cb.or(cb.equal(rootAccion.get("idJurisdiccion"), idJurisdiccion),
						cb.isNull(rootAccion.get("idJurisdiccion"))));
			}

			if (filtroSobreNivelServicio) {
				listaPredicados.add(cb.and(cb.isNull(rootAccion.get("idUnidadServicio")),
						cb.isNull(rootAccion.get("idSeccion")), cb.isNull(rootAccion.get("idSeccionCurricular"))));
			} else {
				Long idNivelServicio = inf.get("nivelServicio");
				if (idNivelServicio != null) {
					listaPredicados.add(cb.or(cb.equal(rootAccion.get("idNivelServicio"), idNivelServicio),
							cb.isNull(rootAccion.get("idNivelServicio"))));
				}

				if (filtroSobreUnidadServicio) {
					listaPredicados.add(cb.and(cb.isNull(rootAccion.get("idSeccion")),
							cb.isNull(rootAccion.get("idSeccionCurricular"))));
				} else {
					Long idUnidadServicio = inf.get("unidadServicio");
					if (idUnidadServicio != null) {
						listaPredicados.add(cb.or(cb.equal(rootAccion.get("idUnidadServicio"), idUnidadServicio),
								cb.isNull(rootAccion.get("idUnidadServicio"))));
					}

					if (filtroSobreSeccion) {
						listaPredicados.add(cb.and(cb.isNull(rootAccion.get("idSeccionCurricular"))));
					} else {
						Long idSeccion = inf.get("seccion");
						if (idSeccion != null) {
							listaPredicados.add(cb.or(cb.equal(rootAccion.get("idSeccion"), idSeccion),
									cb.isNull(rootAccion.get("idSeccion"))));
						}

					}
				}
			}
		}

		return listaPredicados;
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
		@SuppressWarnings("unused")
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
