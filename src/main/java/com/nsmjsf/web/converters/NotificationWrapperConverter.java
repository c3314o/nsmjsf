package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.NotificationAdapter;
import com.nsmjsf.web.datasources.NotificationDataSource;
import com.nsmjsf.web.datamodels.Notification;
import com.nsmjsf.web.wrappers.NotificationWrapper;


@FacesConverter("notificationWrapperConverter")
public class NotificationWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(NotificationWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			NotificationDataSource notificationDataSource = new NotificationDataSource();
			Notification notification = notificationDataSource.get(Integer.parseInt(value));
			NotificationWrapper notificationWrapper=NotificationAdapter.wrap(notification);
			return notificationWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((NotificationWrapper) object).getNotification().getNotificationId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

