package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.controller.PoidsController;
import fr.lrc.model.commentaire.StringReturn;
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
		StringReturn s = PoidsController.poidsController(poidsM);
		if (s.isResponse()) {
			if (!poidsM.getNom().isEmpty()) {
				if (!poidsM.getDate().isEmpty()) {
					messageSender.sendMessage(poidsM, "add");
					return messageReceiver.receiveMessage();
				} else {
					return StringReturn.stringMessage(false, "La date ne peut pas être vide");
				}
			} else {
				return StringReturn.stringMessage(false, "Le nom ne peut pas être vide");
			}
		} else {
			return StringReturn.stringMessage(s.isResponse(), s.getCommentaire());
		}
	}
}
