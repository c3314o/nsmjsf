package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Event;
import com.nsmjsf.web.wrappers.EventWrapper;

public class EventAdapter {
	private static final Log log = LogFactory.getLog(EventAdapter.class);

	public static List<EventWrapper> wrapAll(List<Event> eventList) {
		List<EventWrapper> eventWrapperList = new ArrayList<EventWrapper>();
		for (Event event : eventList) {
			EventWrapper eventWrapper = new EventWrapper();
			eventWrapper.setEvent(event);
			eventWrapperList.add(eventWrapper);
		}
		return eventWrapperList;

	}

	public static EventWrapper wrap(Event event) {
		EventWrapper eventWrapper = new EventWrapper();
		eventWrapper.setEvent(event);
		return eventWrapper;

	}

}
