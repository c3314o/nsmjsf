package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Notification;

public class NotificationWrapper {

	private static final Log log = LogFactory.getLog(NotificationWrapper.class);

	Notification notification;

	public NotificationWrapper(Notification notification) {
		this.notification = notification;
	}

	public NotificationWrapper() {

	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.notification.getNotificationId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final NotificationWrapper other = (NotificationWrapper) obj;
		if (!Objects.equals(this.notification.getNotificationId(), other
				.getNotification().getNotificationId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.notification.toString();

	}

}
