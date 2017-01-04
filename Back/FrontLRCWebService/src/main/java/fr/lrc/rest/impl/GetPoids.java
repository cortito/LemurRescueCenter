package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.model.poids.PoidsModel;
import fr.lrc.rest.IGetPoids;
import fr.lrc.services.IMessageReceiverQueue;
import fr.lrc.services.IMessageSenderTopic;

public class GetPoids implements IGetPoids {

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	@Override
	public String getPoidsByName(PoidsModel poidsM) {
		messageSender.sendMessage(poidsM);
		return messageReceiver.receiveMessage();
	}

}
