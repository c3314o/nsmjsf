package com.nsmjsf.web.datalayer;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Session;

@ManagedBean(eager = true)
@ApplicationScoped
public class SingletonSession {

	private static Session session;

	public static Session getSession() {
		if (session == null || (!session.isOpen())) {
			session = HibernateUtil.getSessionFactory().openSession();
		}
		return session;
	}

}
