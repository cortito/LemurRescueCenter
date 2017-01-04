package fr.lrc.services;

import java.io.Serializable;

import javax.ejb.Local;

@Local
public interface IMessageSenderTopic {

	public void sendMessage(Serializable message);
	public void sendMessageAdd(Serializable message);

}
