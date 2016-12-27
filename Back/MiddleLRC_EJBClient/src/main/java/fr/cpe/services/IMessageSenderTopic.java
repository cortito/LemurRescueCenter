package fr.cpe.services;

import javax.ejb.Local;

import fr.cpe.model.LemurienModel;

@Local
public interface IMessageSenderTopic {

	public void sendMessage(String message);

	public void sendMessage(LemurienModel lemurienM);

}
