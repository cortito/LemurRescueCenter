package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.controller.LemurienController;
import fr.lrc.model.commentaire.StringReturn;
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
		StringReturn s = LemurienController.lemurienController(lemurienM);
		if (s.isResponse()) {
			System.out.println(lemurienM);
			if (lemurienM.getIdDB() != 0) {
				messageSender.sendMessage(lemurienM, "delete");
				return messageReceiver.receiveMessage();
			} else {
				return StringReturn.stringMessage(false, "Id de base de donnée ne doit pas être null");
			}
		} else {
			return StringReturn.stringMessage(s.isResponse(), s.getCommentaire());
		}
	}

}
