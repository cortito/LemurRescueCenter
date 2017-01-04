package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.model.lemurien.LemurienModel;
import fr.lrc.rest.IGetLemurien;
import fr.lrc.services.IMessageReceiverQueue;
import fr.lrc.services.IMessageSenderTopic;

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
