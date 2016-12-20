package fr.cpe.rest.impl;

import javax.inject.Inject;

import fr.cpe.model.LemurienModel;
import fr.cpe.rest.IGetLemurien;
import fr.cpe.services.IMessageReceiverQueue;
import fr.cpe.services.IMessageSenderTopic;

public class GetLemurien implements IGetLemurien {

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	@Override
	public String getAllLemurien() {
		messageSender.sendMessage("");
		return messageReceiver.receiveMessage();
	}

	@Override
	public String getLemurienById(LemurienModel lemurienM) {
		messageSender.sendMessage(lemurienM);
		return messageReceiver.receiveMessage();
	}

}
