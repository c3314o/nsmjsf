package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.AnnouncementAdapter;
import com.nsmjsf.web.datasources.AnnouncementDataSource;
import com.nsmjsf.web.datamodels.Announcement;
import com.nsmjsf.web.wrappers.AnnouncementWrapper;


@FacesConverter("announcementWrapperConverter")
public class AnnouncementWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(AnnouncementWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			AnnouncementDataSource announcementDataSource = new AnnouncementDataSource();
			Announcement announcement = announcementDataSource.get(Integer.parseInt(value));
			AnnouncementWrapper announcementWrapper=AnnouncementAdapter.wrap(announcement);
			return announcementWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((AnnouncementWrapper) object).getAnnouncement().getAnnouncementId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

