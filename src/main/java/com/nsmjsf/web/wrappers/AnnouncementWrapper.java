
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Announcement;

public class AnnouncementWrapper {

private static final Log log = LogFactory
			.getLog(AnnouncementWrapper.class);


	Announcement announcement;

	public AnnouncementWrapper(Announcement announcement) {
		this.announcement = announcement;
	}

	public AnnouncementWrapper() {

	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.announcement.getAnnouncementId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AnnouncementWrapper other = (AnnouncementWrapper) obj;
		if (!Objects.equals(this.announcement.getAnnouncementId(), other.getAnnouncement().getAnnouncementId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.announcement.toString();

	}

}

