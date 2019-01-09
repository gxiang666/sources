package com.demos.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener  implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("SessionListener sessionCreated..........");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("SessionListener sessionDestroyed..........");
	}

}
