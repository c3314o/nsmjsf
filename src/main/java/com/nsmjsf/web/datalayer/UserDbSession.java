package com.nsmjsf.web.datalayer;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class UserDbSession implements Serializable, HttpSessionBindingListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1702738050185590986L;
	private static int counter;
	private static final Log log = LogFactory.getLog(UserDbSession.class);

	Session session;

	public UserDbSession() {
		counter++;
		log.info("creating userdb session" + counter);
		session = getSession();
	}

	public Session getSession() {
		if (this.session == null || (!this.session.isOpen())) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		return session;
	}

	public void closeSession() {
		counter--;
		log.info("DbSession Closing.....left" + counter);
		this.session.clear();
		this.session.close();
	}

	@PreDestroy
	public void autoClose() {
		if (this.session != null) {
			closeSession();
		}
	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		log.info("attached to session" + counter);

	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		log.info("detached from session");
		closeSession();

	}

	public void refresh() {
		this.session.flush();

	}

}
