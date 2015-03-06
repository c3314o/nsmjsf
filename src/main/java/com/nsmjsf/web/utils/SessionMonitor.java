package com.nsmjsf.web.utils;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datalayer.DbSessionManager;

public class SessionMonitor implements Serializable, HttpSessionBindingListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2311637918603728583L;
	private static final Log log = LogFactory.getLog(SessionMonitor.class);

	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		log.info("valueBound:" + event.getName() + " session:"
				+ event.getSession().getId());

	}

	public void registerSession() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("sessionBindingListener", this);
		log.info("registered sessionBindingListener");
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		log.info("valueUnBound:" + event.getName() + " session:"
				+ event.getSession().getId());
		// add you unlock code here:
		// clearLocksForSession( event.getSession().getId() );
		DbSessionManager.getUserDbsession().closeSession();
	}
}
