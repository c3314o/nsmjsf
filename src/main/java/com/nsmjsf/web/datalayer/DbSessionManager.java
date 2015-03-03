package com.nsmjsf.web.datalayer;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DbSessionManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1423285824367668322L;
	private static final Log log = LogFactory.getLog(DbSessionManager.class);

	public static UserDbSession getUserDbsession() {

		UserDbSession uSession = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		try {
			uSession = (UserDbSession) request.getSession().getAttribute(
					"userDbSession");

		} catch (Exception ex) {
			log.debug(ex.getMessage());
		}
		if (uSession == null) {
			uSession = new UserDbSession();
			HttpSession session = (HttpSession) FacesContext
					.getCurrentInstance().getExternalContext()
					.getSession(false);// sessionCreated() is executed

			session.setAttribute("userDbSession", uSession);
		}
		return uSession;

	}

}
