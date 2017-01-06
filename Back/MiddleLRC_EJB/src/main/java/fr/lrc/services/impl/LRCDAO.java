package fr.lrc.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import fr.lrc.model.lemurien.LemurienEntity;
import fr.lrc.model.lemurien.LemurienModel;
import fr.lrc.model.poids.PoidsEntity;
import fr.lrc.model.poids.PoidsModel;
import fr.lrc.services.ILRCDAO;

@Stateless
public class LRCDAO implements ILRCDAO {

	Logger log = Logger.getLogger(LRCDAO.class);

	@PersistenceContext
	EntityManager em;

	@Override
	public LemurienEntity getLemurienById(int id) {
		String qry = "SELECT u FROM LemurienEntity u WHERE u.idDB=:idDB ";

		LemurienEntity lemurienE = null;
		try {
			lemurienE = (LemurienEntity) em.createQuery(qry).setParameter("idDB", id).getSingleResult();
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return lemurienE;
	}

	@Override
	public List<LemurienEntity> getAllLemurien() {
		String qry = "FROM LemurienEntity";

		List<LemurienEntity> lemurienList = new ArrayList<>();
		try {
			lemurienList.addAll(em.createQuery(qry).getResultList());
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return lemurienList;
	}

	@Override
	public List<PoidsEntity> getPoidsByName(String nom) {
		String qry = "SELECT u FROM PoidsEntity u WHERE u.nom=:nom ";

		List<PoidsEntity> poidsList = new ArrayList<>();
		try {
			poidsList.addAll(em.createQuery(qry).setParameter("nom", nom).getResultList());
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return poidsList;
	}

	@Override
	public LemurienEntity addLemurien(LemurienModel lemurienM) {

		LemurienEntity lemurienE = new LemurienEntity(lemurienM, "id");
		try {
			em.persist(lemurienE);
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return getLemurienByName(lemurienE.getNom());
	}

	@Override
	public LemurienEntity getLemurienByName(String nom) {
		String qry = "SELECT u FROM LemurienEntity u WHERE u.nom=:nom ";

		LemurienEntity lemurienE = null;
		try {
			lemurienE = (LemurienEntity) em.createQuery(qry).setParameter("nom", nom).getSingleResult();
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		log.info(lemurienE);
		return lemurienE;
	}

	@Override
	public LemurienEntity updateLemurien(LemurienModel lemurienM) {

		LemurienEntity lemurienE = new LemurienEntity(lemurienM);
		try {
			em.merge(lemurienE);
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		log.info(lemurienE);
		return lemurienE;
	}

	@Override
	public boolean deleteLemurien(LemurienModel lemurienM) {
		LemurienEntity lemurienE = getLemurienById(lemurienM.getIdDB());
		boolean ret = false;
		try {
			em.remove(lemurienE);
			ret = true;
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return ret;
	}

	@Override
	public PoidsEntity addPoids(PoidsModel poidsM) {

		PoidsEntity poidsE = getPoidsByNameAndDate(poidsM.getNom(), poidsM.getDate());
		if (poidsE == null) {
			poidsE = new PoidsEntity(poidsM, "id");
		} else {
			poidsE.setPoids(poidsM.getPoids());
		}
		try {
			em.merge(poidsE);
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return poidsE;
	}

	@Override
	public PoidsEntity getPoidsById(int id) {
		String qry = "SELECT u FROM PoidsEntity u WHERE u.idDB=:idDB ";

		PoidsEntity poidsE = null;
		try {
			poidsE = (PoidsEntity) em.createQuery(qry).setParameter("idDB", id).getSingleResult();
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return poidsE;
	}

	public PoidsEntity getPoidsByNameAndDate(String nom, String date) {
		String qry = "SELECT u FROM PoidsEntity u WHERE u.nom=:nom AND u.date=:date ";

		PoidsEntity poidsE = null;
		try {
			poidsE = (PoidsEntity) em.createQuery(qry).setParameter("nom", nom).setParameter("date", date)
					.getSingleResult();
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return poidsE;
	}

	@Override
	public Object deletePoids(PoidsModel poidsM) {
		PoidsEntity poidsE = getPoidsByNameAndDate(poidsM.getNom(), poidsM.getDate());
		boolean ret = false;
		try {
			if (poidsE != null) {
				em.remove(poidsE);
				ret = true;
			}
		} catch (NoResultException e) {
			log.warn(e.getMessage());
		}
		return ret;
	}
}
