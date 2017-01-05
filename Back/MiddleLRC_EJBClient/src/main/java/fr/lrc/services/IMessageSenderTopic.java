package fr.lrc.services;

import java.io.Serializable;

import javax.ejb.Local;

@Local
public interface IMessageSenderTopic {

	public void sendMessage(Serializable o);

	public void sendMessageAdd(Serializable o);

	public void sendMessageUpdate(Serializable o);

	public void sendMessageDelete(Serializable o);
}
