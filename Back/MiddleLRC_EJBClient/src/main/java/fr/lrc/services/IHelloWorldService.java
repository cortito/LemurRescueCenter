package fr.lrc.services;

import javax.ejb.Local;

@Local
public interface IHelloWorldService {
	
	String helloService(String name);
	
}