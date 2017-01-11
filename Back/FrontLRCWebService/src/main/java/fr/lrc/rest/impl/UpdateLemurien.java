package fr.lrc.rest.impl;

import javax.inject.Inject;
import javax.ws.rs.Path;

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
		messageSender.sendMessage(lemurienM, "update");
		return messageReceiver.receiveMessage();
	}
}
