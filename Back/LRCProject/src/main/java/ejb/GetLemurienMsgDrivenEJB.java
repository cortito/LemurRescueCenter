package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

import fr.cpe.model.LemurienEntity;
import fr.cpe.model.LemurienModel;
import fr.cpe.services.ILemurienDAO;
import fr.cpe.services.IMessageSenderQueue;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/lemurienTopic") })
public class GetLemurienMsgDrivenEJB implements MessageListener {

	Logger log = Logger.getLogger(GetLemurienMsgDrivenEJB.class);

	@Inject
	IMessageSenderQueue senderQueue;
	@Inject
	ILemurienDAO dao;

	/**
	 * Reception d'un message par le topic
	 */
	@Override
	public void onMessage(Message message) {
		log.info("Reception d'un message");

		try {
			if (message instanceof TextMessage) {
				log.info("Text Message");

				TextMessage msg = (TextMessage) message;

				List<LemurienModel> lemurienMList = new ArrayList<>();
				if (msg.getText().isEmpty()) {
					List<LemurienEntity> lemurienEList = dao.getAllLemurien();
					StringBuilder s = new StringBuilder();
					s.append("[");
					// Appel BDD
					if (lemurienEList != null) {
						LemurienModel lemurienM = null;

						for (LemurienEntity e : lemurienEList) {
							lemurienM = new LemurienModel(e);
							lemurienMList.add(lemurienM);
							s.append(lemurienM.toJSON());
							s.append(",");
						}
					}
					s.replace(s.length() - 1, s.length(), "]");
					log.info(s.toString());
					senderQueue.sendMessage(s.toString());
				}
				// Repond par la Queue
			} else if (message instanceof ObjectMessage) {
				log.info("Objet Message");
				ObjectMessage msg = (ObjectMessage) message;
				if (msg.getObject() instanceof LemurienModel) {
					log.info("Lemurien");
					LemurienModel lemurienM = (LemurienModel) msg.getObject();
					LemurienEntity lemurienE = dao.getLemurienById(lemurienM.getIdDB());

					if (lemurienE != null) {
						lemurienM = new LemurienModel(lemurienE);
					} else {
						lemurienM = null;
					}
					senderQueue.sendMessage(lemurienM);
				}
			} else {

				log.info("Message invalid pour la Queue");
			}
		} catch (NumberFormatException | JMSException e) {
			e.printStackTrace();
		}
	}
}
