package fr.cpe.rest.impl;

import javax.inject.Inject;

import fr.cpe.rest.IGetLemurien;

public class GetLemurien implements IGetLemurien {

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	@Override
	public String getAllLemurien() {
		messageSender.sendMessage(null);
		return messageReceiver.receiveMessage().toJSON();
	}

}
