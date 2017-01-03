package fr.cpe.services;

import java.io.Serializable;

import javax.ejb.Local;

@Local
public interface IMessageSenderTopic {

	public void sendMessage(Serializable message);
}
