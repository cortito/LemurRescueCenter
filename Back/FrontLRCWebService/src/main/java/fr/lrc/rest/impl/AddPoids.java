package fr.lrc.rest.impl;

import java.util.regex.Pattern;

import javax.inject.Inject;

import fr.lrc.model.commentaire.StringReturn;
import fr.lrc.model.poids.PoidsModel;
import fr.lrc.rest.IAddPoids;
import fr.lrc.services.IMessageReceiverQueue;
import fr.lrc.services.IMessageSenderTopic;

public class AddPoids implements IAddPoids {

	private final String regexDate = "^(1[0-2]|0[1-9]|\\d)\\/([0-9]\\d)$";

	@Inject
	IMessageSenderTopic messageSender;
	@Inject
	IMessageReceiverQueue messageReceiver;

	@Override
	public String addPoids(PoidsModel poidsM) {
		StringReturn ret = new StringReturn();
		if (poidsM != null) {
			if (poidsM.getNom() != null) {
				if (poidsM.getPoids() != null) {
					if (Pattern.matches(regexDate, poidsM.getDate())) {
						messageSender.sendMessage(poidsM, "add");
						return messageReceiver.receiveMessage();
					} else {
						ret.setCommentaire("Date non conforme");
					}
				} else {
					ret.setCommentaire("Poids null");
				}
			} else {
				ret.setCommentaire("Nom de Lémurien null");
			}
		} else {
			ret.setCommentaire("Object Poids null");
		}
		return ret.message();
	}
}
