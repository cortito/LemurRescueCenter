package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.model.lemurien.LemurienModel;
import fr.lrc.rest.IAddLemurien;
import fr.lrc.services.IMessageReceiverQueue;
import fr.lrc.services.IMessageSenderTopic;

public class AddLemurien implements IAddLemurien {

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	@Override
	public String addLemurien(LemurienModel lemurienM) {
		messageSender.sendMessageAdd(lemurienM);
		return messageReceiver.receiveMessage();
	}

}
