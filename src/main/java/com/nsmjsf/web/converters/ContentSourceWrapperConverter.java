package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.ContentSourceAdapter;
import com.nsmjsf.web.datasources.ContentSourceDataSource;
import com.nsmjsf.web.datamodels.ContentSource;
import com.nsmjsf.web.wrappers.ContentSourceWrapper;

@FacesConverter("contentSourceWrapperConverter")
public class ContentSourceWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(ContentSourceWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			ContentSourceDataSource contentSourceDataSource = new ContentSourceDataSource();
			ContentSource contentSource = contentSourceDataSource.get(Integer
					.parseInt(value));
			ContentSourceWrapper contentSourceWrapper = ContentSourceAdapter
					.wrap(contentSource);
			return contentSourceWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((ContentSourceWrapper) object)
					.getContentSource().getContentSourceId());
		} else {
			return null;
		}
	}

}
