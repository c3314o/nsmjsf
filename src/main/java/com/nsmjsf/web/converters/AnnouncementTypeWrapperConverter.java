package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.AnnouncementTypeAdapter;
import com.nsmjsf.web.datasources.AnnouncementTypeDataSource;
import com.nsmjsf.web.datamodels.AnnouncementType;
import com.nsmjsf.web.wrappers.AnnouncementTypeWrapper;

@FacesConverter("announcementTypeWrapperConverter")
public class AnnouncementTypeWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(AnnouncementTypeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			AnnouncementTypeDataSource announcementTypeDataSource = new AnnouncementTypeDataSource();
			AnnouncementType announcementType = announcementTypeDataSource
					.get(Integer.parseInt(value));
			AnnouncementTypeWrapper announcementTypeWrapper = AnnouncementTypeAdapter
					.wrap(announcementType);
			return announcementTypeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((AnnouncementTypeWrapper) object)
					.getAnnouncementType().getAnnouncementTypeId());
		} else {
			return null;
		}
	}

}
