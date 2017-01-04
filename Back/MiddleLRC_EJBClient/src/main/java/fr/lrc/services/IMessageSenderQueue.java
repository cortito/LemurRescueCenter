package fr.lrc.services;

import javax.ejb.Local;

@Local
public interface IMessageSenderQueue {

	public void sendMessage(String o);

}
