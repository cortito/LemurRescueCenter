package fr.lrc.services.impl;

import javax.ejb.Stateless;

import fr.lrc.services.IHelloWorldService;

@Stateless
public class HelloWorldService implements IHelloWorldService {

	@Override
	public String helloService(String name) {
		if (name != null) {
			return "Hello " + name + " !";
		}
		
		return "Hello World !";
	}
	
}