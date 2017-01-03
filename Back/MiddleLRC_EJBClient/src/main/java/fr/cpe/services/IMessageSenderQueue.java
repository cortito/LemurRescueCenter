package fr.cpe.services;

import javax.ejb.Local;

@Local
public interface IMessageSenderQueue {

	public void sendMessage(String o);

}
