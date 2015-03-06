package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.MapPostPostAdapter;
import com.nsmjsf.web.datasources.MapPostPostDataSource;
import com.nsmjsf.web.datamodels.MapPostPost;
import com.nsmjsf.web.wrappers.MapPostPostWrapper;

@FacesConverter("mapPostPostWrapperConverter")
public class MapPostPostWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(MapPostPostWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			MapPostPostDataSource mapPostPostDataSource = new MapPostPostDataSource();
			MapPostPost mapPostPost = mapPostPostDataSource.get(Integer
					.parseInt(value));
			MapPostPostWrapper mapPostPostWrapper = MapPostPostAdapter
					.wrap(mapPostPost);
			return mapPostPostWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((MapPostPostWrapper) object)
					.getMapPostPost().getMapPostPostId());
		} else {
			return null;
		}
	}

}
