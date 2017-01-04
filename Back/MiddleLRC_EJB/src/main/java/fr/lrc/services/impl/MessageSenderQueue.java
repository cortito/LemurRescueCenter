package fr.lrc.services.impl;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import org.jboss.logging.Logger;

import fr.lrc.services.IMessageSenderQueue;

@Stateless
@LocalBean
public class MessageSenderQueue implements IMessageSenderQueue {

	Logger log = Logger.getLogger(MessageSenderQueue.class);

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/lRCQueue")
	Queue queue;

	@Override
	public void sendMessage(String o) {
		context.createProducer().send(queue, o);
	}

}
