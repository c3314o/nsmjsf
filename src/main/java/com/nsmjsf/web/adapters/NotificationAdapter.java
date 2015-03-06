package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Notification;
import com.nsmjsf.web.wrappers.NotificationWrapper;

public class NotificationAdapter {
	private static final Log log = LogFactory.getLog(NotificationAdapter.class);

	public static List<NotificationWrapper> wrapAll(
			List<Notification> notificationList) {
		List<NotificationWrapper> notificationWrapperList = new ArrayList<NotificationWrapper>();
		for (Notification notification : notificationList) {
			NotificationWrapper notificationWrapper = new NotificationWrapper();
			notificationWrapper.setNotification(notification);
			notificationWrapperList.add(notificationWrapper);
		}
		return notificationWrapperList;

	}

	public static NotificationWrapper wrap(Notification notification) {
		NotificationWrapper notificationWrapper = new NotificationWrapper();
		notificationWrapper.setNotification(notification);
		return notificationWrapper;

	}

}
