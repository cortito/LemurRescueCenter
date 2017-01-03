package fr.cpe.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import fr.cpe.model.lemurien.LemurienEntity;
import fr.cpe.model.poids.PoidsEntity;
import fr.cpe.services.ILRCDAO;

@Stateless
public class LRCDAO implements ILRCDAO {

	Logger log = Logger.getLogger(LRCDAO.class);

	@PersistenceContext
	EntityManager em;

	@Override
	public LemurienEntity getLemurienById(int id) {
		String qry = "SELECT u FROM LemurienEntity u WHERE u.id=:id ";

		LemurienEntity lemurienE = null;
		try {
			lemurienE = (LemurienEntity) em.createQuery(qry).setParameter("id", id).getSingleResult();
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
}
