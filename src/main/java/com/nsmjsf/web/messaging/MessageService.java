package com.nsmjsf.web.messaging;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class MessageService implements Serializable {

	public static void info(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
	}

	public static void warn(String message) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
						message));
	}

	public static void error(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
								message));
	}

	public static void fatal(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!",
								message));
	}

	public static void info(String id, String message) {
		FacesContext.getCurrentInstance().addMessage(id,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
	}

	public static void warn(String id, String message) {
		FacesContext.getCurrentInstance().addMessage(
				id,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!",
						message));
	}

	public static void error(String id, String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						id,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
								message));
	}

	public static void fatal(String id, String message) {
		FacesContext.getCurrentInstance()
				.addMessage(
						id,
						new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!",
								message));
	}

}
