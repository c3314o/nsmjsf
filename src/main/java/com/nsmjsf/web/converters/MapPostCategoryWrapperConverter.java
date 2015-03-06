package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.MapPostCategoryAdapter;
import com.nsmjsf.web.datasources.MapPostCategoryDataSource;
import com.nsmjsf.web.datamodels.MapPostCategory;
import com.nsmjsf.web.wrappers.MapPostCategoryWrapper;

@FacesConverter("mapPostCategoryWrapperConverter")
public class MapPostCategoryWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(MapPostCategoryWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			MapPostCategoryDataSource mapPostCategoryDataSource = new MapPostCategoryDataSource();
			MapPostCategory mapPostCategory = mapPostCategoryDataSource
					.get(Integer.parseInt(value));
			MapPostCategoryWrapper mapPostCategoryWrapper = MapPostCategoryAdapter
					.wrap(mapPostCategory);
			return mapPostCategoryWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((MapPostCategoryWrapper) object)
					.getMapPostCategory().getMapPostCategoryId());
		} else {
			return null;
		}
	}

}
