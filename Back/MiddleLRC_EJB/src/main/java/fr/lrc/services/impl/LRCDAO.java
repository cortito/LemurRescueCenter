package fr.lrc.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import fr.lrc.model.commentaire.StringReturn;
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
	public List<LemurienEntity> getAllLemurien() {
		String qry = "FROM LemurienEntity";
		List<LemurienEntity> lemurienList = new ArrayList<>();
		try {
			lemurienList.addAll(em.createQuery(qry).getResultList());
		} catch (Exception e) {
			lemurienList = null;
		}
		return lemurienList;
	}

	@Override
	public LemurienEntity getLemurienById(int id) {
		String qry = "SELECT u FROM LemurienEntity u WHERE u.idDB=:idDB ";
		LemurienEntity lemurienE = null;
		try {
			lemurienE = (LemurienEntity) em.createQuery(qry).setParameter("idDB", id).getSingleResult();
		} catch (Exception e) {
			lemurienE = null;
		}
		return lemurienE;
	}

	@Override
	public LemurienEntity getLemurienByName(String nom) {
		String qry = "SELECT u FROM LemurienEntity u WHERE u.nom=:nom ";
		LemurienEntity lemurienE = null;
		try {
			lemurienE = (LemurienEntity) em.createQuery(qry).setParameter("nom", nom).getSingleResult();
		} catch (NoResultException e) {
			lemurienE = null;
		} catch (Exception e) {
			e.printStackTrace();
			lemurienE = null;
		}
		System.out.println(lemurienE);
		return lemurienE;
	}

	@Override
	public String addLemurien(LemurienModel lemurienM) {
		if (getLemurienByName(lemurienM.getNom()) != null) {
			return StringReturn.stringMessage(false, "Lémurien déjà existant");
		}
		LemurienEntity lemurienE = new LemurienEntity(lemurienM, "id");
		try {
			em.persist(lemurienE);
		} catch (Exception e) {
			return StringReturn.stringMessage(false, "Erreur à la base de donnée");
		}
		return StringReturn.stringMessage(true, "Lémurien ajouté");
	}

	@Override
	public String updateLemurien(LemurienModel lemurienM) {
		LemurienEntity lemurienMerge = getLemurienById(lemurienM.getIdDB());
		if (lemurienMerge == null) {
			return StringReturn.stringMessage(false, "Lémurien introuvable");
		}

		log.info(lemurienMerge.getNom());
		log.info(lemurienM.getNom());

		if (!lemurienMerge.getNom().equals(lemurienM.getNom())) {
			if (getLemurienByName(lemurienM.getNom()) != null) {
				return StringReturn.stringMessage(false, "Nom déjà existant");
			}
		}

		LemurienEntity lemurienE = new LemurienEntity(lemurienMerge, lemurienM);
		try {
			em.merge(lemurienE);
		} catch (Exception e) {
			return StringReturn.stringMessage(false, "Erreur à la base de donnée");
		}
		return StringReturn.stringMessage(true, "Lémurien mis à jour");
	}

	@Override
	public String deleteLemurien(LemurienModel lemurienM) {
		LemurienEntity lemurienE = getLemurienById(lemurienM.getIdDB());
		if (lemurienE == null) {
			return StringReturn.stringMessage(false, "Lémurien introuvable");
		} else {
			boolean ret = false;
			try {
				em.remove(lemurienE);
				ret = true;
				// DELETE all Poids
				if (ret) {
					for (PoidsEntity p : getPoidsByName(lemurienE.getNom())) {
						em.remove(p);
					}
				}
			} catch (Exception e) {
				return StringReturn.stringMessage(false, "Erreur à la base de donnée");
			}
			return StringReturn.stringMessage(true, "Lémurien supprimé");
		}
	}

	@Override
	public List<PoidsEntity> getPoidsByName(String nom) {
		String qry = "SELECT u FROM PoidsEntity u WHERE u.nom=:nom ";
		List<PoidsEntity> poidsList = new ArrayList<>();
		try {
			poidsList.addAll(em.createQuery(qry).setParameter("nom", nom).getResultList());
		} catch (Exception e) {
			return null;
		}
		return poidsList;
	}

	@Override
	public String addPoids(PoidsEntity poidsE) {
		if (getLemurienByName(poidsE.getNom()) == null) {
			return StringReturn.stringMessage(false, "Lemurien introuvable");
		}
		try {
			em.merge(poidsE);
		} catch (Exception e) {
			return StringReturn.stringMessage(false, "Erreur à la base de donnée");
		}
		return StringReturn.stringMessage(true, "Poids ajouté ");
	}

	@Override
	public PoidsEntity getPoidsById(int id) {
		String qry = "SELECT u FROM PoidsEntity u WHERE u.idDB=:idDB ";
		PoidsEntity poidsE = null;
		try {
			poidsE = (PoidsEntity) em.createQuery(qry).setParameter("idDB", id).getSingleResult();
		} catch (Exception e) {
		}
		return poidsE;
	}

	@Override
	public PoidsEntity getPoidsByNameAndDate(String nom, String date) {
		String qry = "SELECT u FROM PoidsEntity u WHERE u.nom=:nom AND u.date=:date ";
		PoidsEntity poidsE = null;
		try {
			poidsE = (PoidsEntity) em.createQuery(qry).setParameter("nom", nom).setParameter("date", date)
					.getSingleResult();
		} catch (Exception e) {
		}
		return poidsE;
	}

	@Override
	public String deletePoids(PoidsModel poidsM) {
		PoidsEntity poidsE = getPoidsByNameAndDate(poidsM.getNom(), poidsM.getDate());
		if (poidsE != null) {
			try {
				em.remove(poidsE);
			} catch (Exception e) {
				return StringReturn.stringMessage(false, "Erreur à la base de donnée");
			}
			return StringReturn.stringMessage(true, "Poids supprimé");
		} else {
			return StringReturn.stringMessage(false, "Poids introuvable");
		}
	}
}
