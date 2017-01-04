package fr.lrc.services;

import javax.ejb.Local;

@Local
public interface IMessageReceiverQueue {
	String receiveMessage();
}
