package com.nsmjsf.web.utils;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent event) {

		System.out.println("ID Session Created: " + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("ID Session Destroyed: "
				+ event.getSession().getId());
	}
}