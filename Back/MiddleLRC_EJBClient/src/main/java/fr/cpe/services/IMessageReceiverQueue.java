package fr.cpe.services;

import javax.ejb.Local;

import fr.cpe.model.LemurienModel;

@Local
public interface IMessageReceiverQueue {
	String receiveMessage();
}
