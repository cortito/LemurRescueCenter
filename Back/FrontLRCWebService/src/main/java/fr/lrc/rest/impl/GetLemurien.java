package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.model.commentaire.StringReturn;
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
		messageSender.sendMessage("", "all");
		return messageReceiver.receiveMessage();
	}

	@Override
	public String getLemurienById(LemurienModel lemurienM) {
		if (lemurienM.getIdDB() != 0) {
			messageSender.sendMessage(lemurienM, "id");
		} else if (lemurienM.getNom() != null) {
			messageSender.sendMessage(lemurienM, "name");
		} else {
			return StringReturn.stringMessage(false, "Lemurien introuvale");
		}
		return messageReceiver.receiveMessage();
	}

}
