package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.model.poids.PoidsModel;
import fr.lrc.rest.IAddPoids;
import fr.lrc.services.IMessageReceiverQueue;
import fr.lrc.services.IMessageSenderTopic;

public class AddPoids implements IAddPoids {

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	@Override
	public String addPoids(PoidsModel poidsM) {
		messageSender.sendMessage(poidsM, "add");
		return messageReceiver.receiveMessage();
	}
}
