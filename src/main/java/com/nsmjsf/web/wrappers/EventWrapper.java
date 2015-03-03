
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Event;

public class EventWrapper {

private static final Log log = LogFactory
			.getLog(EventWrapper.class);


	Event event;

	public EventWrapper(Event event) {
		this.event = event;
	}

	public EventWrapper() {

	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.event.getEventId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EventWrapper other = (EventWrapper) obj;
		if (!Objects.equals(this.event.getEventId(), other.getEvent().getEventId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.event.toString();

	}

}

