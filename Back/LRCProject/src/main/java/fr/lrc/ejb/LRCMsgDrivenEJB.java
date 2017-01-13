package fr.lrc.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

import fr.lrc.model.commentaire.StringReturn;
import fr.lrc.model.lemurien.LemurienEntity;
import fr.lrc.model.lemurien.LemurienModel;
import fr.lrc.model.poids.PoidsEntity;
import fr.lrc.model.poids.PoidsModel;
import fr.lrc.services.ILRCDAO;
import fr.lrc.services.IMessageSenderQueue;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/LRCTopic") })
public class LRCMsgDrivenEJB implements MessageListener {

	Logger log = Logger.getLogger(LRCMsgDrivenEJB.class);

	@Inject
	IMessageSenderQueue senderQueue;
	@Inject
	ILRCDAO dao;

	/**
	 * Reception d'un message par le topic
	 */
	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage msg = (ObjectMessage) message;

				/**
				 * Get All Lemurien
				 */
				if (msg.getObject() instanceof String) {
					try {
						senderQueue.sendMessage(manageAllLemurien(msg));
					} catch (JMSException e) {
					}
				}
				/**
				 * Lemurien
				 */
				else if (msg.getObject() instanceof LemurienModel) {
					try {
						senderQueue.sendMessage(manageLemurien(msg));
					} catch (JMSException e) {
					}
				}
				/**
				 * Poids
				 */
				else if (msg.getObject() instanceof PoidsModel) {
					try {
						senderQueue.sendMessage(managePoids(msg));
					} catch (JMSException e) {
					}
				} else {
					senderQueue.sendMessage(StringReturn.stringMessage(false, "Objet non pris en compte"));
				}
			} else {
				senderQueue.sendMessage(StringReturn.stringMessage(false, "Message non pris en compte"));
			}
		} catch (JMSException e) {
			e.printStackTrace();
			senderQueue.sendMessage(StringReturn.stringMessage(false, "Erreur"));
		}
	}

	private String manageAllLemurien(ObjectMessage msg) throws JMSException {
		StringBuilder s = new StringBuilder();

		String m = (String) msg.getObject();

		if (m.isEmpty() && msg.getBooleanProperty("all")) {
			List<LemurienEntity> lemurienEList = new ArrayList<>();
			lemurienEList = dao.getAllLemurien();
			if (lemurienEList == null) {
				return StringReturn.stringMessage(false, "Erreur acces base de donnée");
			} else {
				s.append("[");

				for (LemurienEntity e : lemurienEList) {
					s.append(new LemurienModel(e).toJSON());
					s.append(",");
				}
			}
			if (s.length() == 1) {
				s.append("]");
			} else {
				s.replace(s.length() - 1, s.length(), "]");
			}
			return s.toString();
		} else {
			return StringReturn.stringMessage(false, "Erreur - Message incompris");
		}
	}

	private String manageLemurien(ObjectMessage msg) throws JMSException {
		LemurienModel lemurienM = (LemurienModel) msg.getObject();

		/**
		 * ADD Lemurien
		 */
		if (msg.getBooleanProperty("add")) {
			return dao.addLemurien(lemurienM);
		}
		/**
		 * UPDATE Lemurien
		 */
		else if (msg.getBooleanProperty("update")) {
			return dao.updateLemurien(lemurienM);
		}
		/**
		 * DELETE Lemurien
		 */
		else if (msg.getBooleanProperty("delete")) {
			return dao.deleteLemurien(lemurienM);
		}
		/**
		 * GET Lemurien By Id
		 */
		else if (msg.getBooleanProperty("id")) {
			try {
				return new LemurienModel(dao.getLemurienById(lemurienM.getIdDB())).toJSON();
			} catch (NullPointerException e) {
				return StringReturn.stringMessage(false, "Lémurien introuvable");
			}
		}
		/**
		 * GET Lemurien By Name
		 */
		else if (msg.getBooleanProperty("name")) {
			try {
				return new LemurienModel(dao.getLemurienByName((lemurienM.getNom()))).toJSON();
			} catch (NullPointerException e) {
				return StringReturn.stringMessage(false, "Lémurien introuvable");
			}
		}
		/**
		 * DEFAULT
		 */
		else {
			return StringReturn.stringMessage(false, "Erreur - Message incompris");
		}
	}

	private String managePoids(ObjectMessage msg) throws JMSException {

		StringBuilder s = new StringBuilder();
		PoidsModel poidsM = (PoidsModel) msg.getObject();
		/**
		 * ADD Poids
		 */
		if (msg.getBooleanProperty("add")) {
			PoidsEntity poidsE = null;
			if (poidsM.getNom() != null || !poidsM.getNom().isEmpty()) {
				poidsE = dao.getPoidsByNameAndDate(poidsM.getNom(), poidsM.getDate());
				// Add new poids
				if (poidsE == null) {
					poidsE = new PoidsEntity(poidsM, "id");
				}
				// Update poids
				else {
					poidsE.setPoids(poidsM.getPoids());
				}
				// Push to the DB
				return dao.addPoids(poidsE);
			} else {
				return StringReturn.stringMessage(false, "Nom introuvable");
			}
		}
		/**
		 * DELETE Poids
		 */
		else if (msg.getBooleanProperty("delete")) {
			return dao.deletePoids(poidsM);
		}
		/**
		 * GET Poids by Name
		 */
		else if (msg.getBooleanProperty("get")) {
			List<PoidsEntity> poidsEList = new ArrayList<>();
			poidsEList = dao.getPoidsByName(poidsM.getNom());

			if (poidsEList == null) {
				return StringReturn.stringMessage(false, "Erreur acces base de donnée");
			} else {
				s.append("[");
				for (PoidsEntity pE : poidsEList) {
					s.append(new PoidsModel(pE).toJSON());
					s.append(",");
				}
			}

			if (s.length() == 1) {
				s.append("]");
			} else {
				s.replace(s.length() - 1, s.length(), "]");
			}

			return s.toString();
		}
		/**
		 * DEFAULT
		 */
		else {
			return StringReturn.stringMessage(false, "Erreur - Message incompris");
		}
	}

}
