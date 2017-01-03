package fr.cpe.services.impl;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

import fr.cpe.services.IMessageReceiverQueue;

@Stateless
@LocalBean
public class MessageReceiverQueue implements IMessageReceiverQueue {

	Logger log = Logger.getLogger(MessageReceiverQueue.class);

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/lRCQueue")
	Queue queue;

	@Override
	public String receiveMessage() {

		JMSConsumer receiver = context.createConsumer(queue);
		Message message = receiver.receive(2000);
		log.info("Reception de la Queue d'un Message");

		String s = "";
		try {
			if (message instanceof TextMessage) {
				s = (String) ((TextMessage) message).getText();
			} else {
				log.info("Unreadable for the Queue");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		receiver.close();
		return s;
	}
}
