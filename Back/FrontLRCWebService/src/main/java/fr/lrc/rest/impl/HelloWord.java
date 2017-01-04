package fr.lrc.rest.impl;

import javax.inject.Inject;

import fr.lrc.rest.IHelloWord;
import fr.lrc.services.IHelloWorldService;

/**
 * @author ubuntu
 *
 */
public class HelloWord implements IHelloWord {

	@Inject
	IHelloWorldService helloWorldService;
	
	@Override
	public String hello() {
		return helloWorldService.helloService(null);
	}
	
	@Override
	public String hello(String name) {
		return helloWorldService.helloService(name);
	}
}
