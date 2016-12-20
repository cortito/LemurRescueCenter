package fr.cpe.services.impl;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

import org.jboss.logging.Logger;

import fr.cpe.model.LemurienModel;
import fr.cpe.services.IMessageSenderTopic;

@Stateless
@LocalBean
public class MessageSenderTopic implements IMessageSenderTopic {

	Logger log = Logger.getLogger(MessageSenderTopic.class);

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/lemurienTopic")
	Topic topic;

	@Override
	public void sendMessage(String message) {
		log.info("Envoi au Topic d'un message");
		JMSProducer prod = context.createProducer();
		prod.send(topic, message);
	}

	@Override
	public void sendMessage(LemurienModel lemurienM) {
		log.info("Envoi au Topic d'un Lemurien");

		try {
			JMSProducer prod = context.createProducer();
			ObjectMessage message = context.createObjectMessage();
			message.setObject(lemurienM);
			prod.send(topic, message);
		} catch (NullPointerException | JMSException e) {
			e.printStackTrace();
		}
	}

}
