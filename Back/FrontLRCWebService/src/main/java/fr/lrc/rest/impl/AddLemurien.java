package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.controller.LemurienController;
import fr.lrc.model.commentaire.StringReturn;
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
		StringReturn s = LemurienController.lemurienController(lemurienM);
		if (s.isResponse()) {
			if (lemurienM.getIdDB() == 0) {
				if (!lemurienM.getNom().isEmpty()) {
					messageSender.sendMessage(lemurienM, "add");
					return messageReceiver.receiveMessage();
				} else {
					return StringReturn.stringMessage(false, "Le nom ne peut pas être vide");
				}
			} else {
				return StringReturn.stringMessage(false, "Id de base de donnée doit être null");
			}
		} else {
			return StringReturn.stringMessage(s.isResponse(), s.getCommentaire());
		}
	}
}
