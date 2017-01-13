package fr.lrc.services.impl;

import java.io.Serializable;

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

import fr.lrc.services.IMessageSenderTopic;

@Stateless
@LocalBean
public class MessageSenderTopic implements IMessageSenderTopic {

	Logger log = Logger.getLogger(MessageSenderTopic.class);

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/LRCTopic")
	Topic topic;

	public void sendMessage(Serializable o, String param) {
		log.info("Envoi d'un message au Topic");
		try {
			JMSProducer prod = context.createProducer();
			ObjectMessage message = context.createObjectMessage();
			message.setObject(o);
			message.setBooleanProperty(param, true);
			prod.send(topic, message);
		} catch (NullPointerException | JMSException e) {
			e.printStackTrace();
		}
	}

}
