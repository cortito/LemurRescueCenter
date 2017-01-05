package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.model.lemurien.LemurienModel;
import fr.lrc.rest.IDeleteLemurien;
import fr.lrc.services.IMessageReceiverQueue;
import fr.lrc.services.IMessageSenderTopic;

public class DeleteLemurien implements IDeleteLemurien {

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	@Override
	public String deleteLemurien(LemurienModel lemurienM) {
		messageSender.sendMessageDelete(lemurienM);
		return messageReceiver.receiveMessage();
	}

}
