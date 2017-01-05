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

	public void sendMessage(Serializable o) {
		log.info("Envoi d'un message au Topic");
		try {
			JMSProducer prod = context.createProducer();
			ObjectMessage message = context.createObjectMessage();
			message.setObject(o);
			prod.send(topic, message);
		} catch (NullPointerException | JMSException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageAdd(Serializable o) {
		log.info("Envoi d'un message au Topic");
		try {
			JMSProducer prod = context.createProducer();
			ObjectMessage message = context.createObjectMessage();
			message.setObject(o);
			message.setBooleanProperty("add", true);
			prod.send(topic, message);
		} catch (NullPointerException | JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessageUpdate(Serializable o) {
		log.info("Envoi d'un message au Topic");
		try {
			JMSProducer prod = context.createProducer();
			ObjectMessage message = context.createObjectMessage();
			message.setObject(o);
			message.setBooleanProperty("update", true);
			prod.send(topic, message);
		} catch (NullPointerException | JMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessageDelete(Serializable o) {
		log.info("Envoi d'un message au Topic");
		try {
			JMSProducer prod = context.createProducer();
			ObjectMessage message = context.createObjectMessage();
			message.setObject(o);
			message.setBooleanProperty("delete", true);
			prod.send(topic, message);
		} catch (NullPointerException | JMSException e) {
			e.printStackTrace();
		}
	}

}
