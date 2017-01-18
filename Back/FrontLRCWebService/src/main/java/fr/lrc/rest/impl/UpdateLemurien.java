package fr.lrc.rest.impl;

import javax.inject.Inject;
import javax.ws.rs.Path;

import fr.lrc.controller.LemurienController;
import fr.lrc.model.commentaire.StringReturn;
import fr.lrc.model.lemurien.LemurienModel;
import fr.lrc.rest.IUpdateLemurien;
import fr.lrc.services.IMessageReceiverQueue;
import fr.lrc.services.IMessageSenderTopic;

@Path("/updateLemurien")
public class UpdateLemurien implements IUpdateLemurien {

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	public String updateLemurien(LemurienModel lemurienM) {
		StringReturn s = LemurienController.lemurienController(lemurienM);
		if (s.isResponse()) {
			if (lemurienM.getIdDB() != 0) {
				if (!lemurienM.getNom().isEmpty()) {
					messageSender.sendMessage(lemurienM, "update");
					return messageReceiver.receiveMessage();
				} else {
					return StringReturn.stringMessage(false, "Le nom ne peut pas être vide");
				}
			} else {
				return StringReturn.stringMessage(false, "Id de base de donnée ne doit pas être null");
			}
		} else {
			return StringReturn.stringMessage(s.isResponse(), s.getCommentaire());
		}
	}
}
