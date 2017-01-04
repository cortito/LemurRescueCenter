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
				} else if (msg.getObject() instanceof LemurienModel) {
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
					 * Get Lemurien By Id
					 */
					else {
						lemurienE = dao.getLemurienById(lemurienM.getIdDB());
					}
					s.append(lemurienM.toString());
					if (lemurienE != null) {
						lemurienM = new LemurienModel(lemurienE);
					} else {
						lemurienM = null;
					}
				}
				/**
				 * Get Poids By Name
				 */
				else if (msg.getObject() instanceof PoidsModel) {
					log.info("Poids");
					PoidsModel poidsM = (PoidsModel) msg.getObject();
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
