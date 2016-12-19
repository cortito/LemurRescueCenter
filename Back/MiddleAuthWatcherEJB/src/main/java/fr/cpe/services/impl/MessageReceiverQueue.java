package fr.cpe.services.impl;


import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

@Stateless
@LocalBean
public class MessageReceiverQueue implements IMessageReceiverQueue {

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/lemurQueue")
	Queue queue;

	@Override
	public Lemurien receiveMessage(){
		Lemurien lemurien = null;
		JMSConsumer receiver = context.createConsumer(queue);
		Message message = receiver.receive(1000);
		
		if(message instanceof ObjectMessage){
			ObjectMessage msg = (ObjectMessage) message;
			try{
				if (msg.getObject() instanceof Lemurien){
					lemurien = (Lemurien) msg.getObject();
				}
			}
			catch(JMSException e){
				e.printStackTrace();
			}
		}
		receiver.close();
		return lemurien;
	}
}
