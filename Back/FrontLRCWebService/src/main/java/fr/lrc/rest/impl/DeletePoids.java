package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.model.poids.PoidsModel;
import fr.lrc.rest.IDeletePoids;
import fr.lrc.services.IMessageReceiverQueue;
import fr.lrc.services.IMessageSenderTopic;

public class DeletePoids implements IDeletePoids {

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	@Override
	public String deletePoids(PoidsModel poidsM) {
		messageSender.sendMessage(poidsM,"delete");
		return messageReceiver.receiveMessage();
	}

}
