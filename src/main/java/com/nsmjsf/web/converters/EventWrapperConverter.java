package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.EventAdapter;
import com.nsmjsf.web.datasources.EventDataSource;
import com.nsmjsf.web.datamodels.Event;
import com.nsmjsf.web.wrappers.EventWrapper;

@FacesConverter("eventWrapperConverter")
public class EventWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(EventWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			EventDataSource eventDataSource = new EventDataSource();
			Event event = eventDataSource.get(Integer.parseInt(value));
			EventWrapper eventWrapper = EventAdapter.wrap(event);
			return eventWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((EventWrapper) object).getEvent()
					.getEventId());
		} else {
			return null;
		}
	}

}
