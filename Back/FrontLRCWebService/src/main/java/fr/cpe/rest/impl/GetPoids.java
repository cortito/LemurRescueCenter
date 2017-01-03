package fr.cpe.rest.impl;

import javax.inject.Inject;

import fr.cpe.model.poids.PoidsModel;
import fr.cpe.rest.IGetPoids;
import fr.cpe.services.IMessageReceiverQueue;
import fr.cpe.services.IMessageSenderTopic;

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
