package fr.cpe.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import fr.cpe.model.LemurienEntity;
import fr.cpe.model.LemurienModel;
import fr.cpe.services.ILemurienDAO;

@Stateless
public class LemurienDAO implements ILemurienDAO {

	Logger log = Logger.getLogger(LemurienDAO.class);

	@PersistenceContext
	EntityManager em;

	// private final String qry = "SELECT * FROM `Lemurien`";

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
}
