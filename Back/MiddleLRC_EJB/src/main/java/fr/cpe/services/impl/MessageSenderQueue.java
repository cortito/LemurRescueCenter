package fr.cpe.services.impl;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import org.jboss.logging.Logger;

import fr.cpe.model.LemurienModel;
import fr.cpe.services.IMessageSenderQueue;

@Stateless
@LocalBean
public class MessageSenderQueue implements IMessageSenderQueue {

	Logger log = Logger.getLogger(MessageSenderQueue.class);
	
	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/lemurienQueue")
	Queue queue;

	@Override
	public void sendMessage(String message) {
		log.info("Envoi a la Queue d'un message");

		context.createProducer().send(queue, message);
	}

	@Override
	public void sendMessage(LemurienModel lemurienM) {
		log.info("Envoi a la Queue d'un Lemurien");

		try {
			ObjectMessage message = context.createObjectMessage();
			message.setObject(lemurienM);
			context.createProducer().send(queue, lemurienM);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
