package fr.lrc.ejb;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

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
		log.info("Reception d'un message");

		StringBuilder s = new StringBuilder();

		try {
			if (message instanceof ObjectMessage) {
				log.info("Objet Message");
				ObjectMessage msg = (ObjectMessage) message;

				/**
				 * Get All Lemurien
				 */
				if (msg.getObject() instanceof String) {
					String m = (String) msg.getObject();
					log.info("String");
					if (m.isEmpty()) {
						List<LemurienEntity> lemurienEList = dao.getAllLemurien();
						s.append("[");
						if (lemurienEList != null) {
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
					}
				}
				/**
				 * Get Lemurien by ID
				 * 
				 * Add Lemurien
				 * 
				 * Update Lemurien
				 */
				else if (msg.getObject() instanceof LemurienModel) {
					log.info("Lemurien");
					LemurienModel lemurienM = (LemurienModel) msg.getObject();
					LemurienEntity lemurienE = null;
					/**
					 * ADD Lemurien
					 */
					if (msg.getBooleanProperty("add")) {
						log.info(lemurienM.toString());
						lemurienE = dao.addLemurien(lemurienM);
					}
					/**
					 * UPDATE Lemurien
					 */
					else if (msg.getBooleanProperty("update")) {
						log.info(lemurienM.toString());
						lemurienE = dao.updateLemurien(lemurienM);
					}
					/**
					 * DELETE Lemurien
					 */
					else if (msg.getBooleanProperty("delete")) {
						log.info(lemurienM.toString());
						s.append("{ \"delete\":");
						s.append(dao.deleteLemurien(lemurienM));
						s.append("}");
					}
					/**
					 * GET Lemurien By Id
					 */
					else {
						lemurienE = dao.getLemurienById(lemurienM.getIdDB());
					}
					if (lemurienE != null) {
						lemurienM = new LemurienModel(lemurienE);
						s.append(lemurienM.toString());
					} else {
						lemurienM = null;
					}
				}
				/**
				 * Poids
				 */
				else if (msg.getObject() instanceof PoidsModel) {
					log.info("Poids");
					PoidsModel poidsM = (PoidsModel) msg.getObject();
					PoidsEntity poidsE = null;
					/**
					 * ADD Poids
					 */
					if (msg.getBooleanProperty("add")) {
						log.info(poidsM.toString());
						if (poidsM.getNom() != null || !poidsM.getNom().isEmpty()) {
							poidsE = dao.addPoids(poidsM);

							if (poidsE != null) {
								poidsM = new PoidsModel(poidsE);
								s.append(poidsM.toString());
							} else {
								poidsM = null;
							}
						}
					} 
					/**
					 * DELETE Poids
					 */
					else if (msg.getBooleanProperty("delete")) {
						log.info(poidsM.toString());
						s.append("{ \"delete\":");
						s.append(dao.deletePoids(poidsM));
						s.append("}");
					}
					else {
						log.info("Poids");
						List<PoidsEntity> poidsEList = dao.getPoidsByName(poidsM.getNom());

						s.append("[");
						if (poidsEList != null) {

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
					}
				}
			} else {
				log.info("Message invalid pour la Queue");
			}
		} catch (NumberFormatException | JMSException e) {
			e.printStackTrace();
			senderQueue.sendMessage("");
		}
		log.info("Envoi to Queue " + s.toString());
		senderQueue.sendMessage(s.toString());
	}
}
