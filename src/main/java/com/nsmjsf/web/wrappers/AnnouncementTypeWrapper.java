
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.AnnouncementType;

public class AnnouncementTypeWrapper {

private static final Log log = LogFactory
			.getLog(AnnouncementTypeWrapper.class);


	AnnouncementType announcementType;

	public AnnouncementTypeWrapper(AnnouncementType announcementType) {
		this.announcementType = announcementType;
	}

	public AnnouncementTypeWrapper() {

	}

	public AnnouncementType getAnnouncementType() {
		return announcementType;
	}

	public void setAnnouncementType(AnnouncementType announcementType) {
		this.announcementType = announcementType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.announcementType.getAnnouncementTypeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AnnouncementTypeWrapper other = (AnnouncementTypeWrapper) obj;
		if (!Objects.equals(this.announcementType.getAnnouncementTypeId(), other.getAnnouncementType().getAnnouncementTypeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.announcementType.toString();

	}

}

