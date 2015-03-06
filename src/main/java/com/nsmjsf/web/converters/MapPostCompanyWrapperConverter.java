package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.MapPostCompanyAdapter;
import com.nsmjsf.web.datasources.MapPostCompanyDataSource;
import com.nsmjsf.web.datamodels.MapPostCompany;
import com.nsmjsf.web.wrappers.MapPostCompanyWrapper;

@FacesConverter("mapPostCompanyWrapperConverter")
public class MapPostCompanyWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(MapPostCompanyWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			MapPostCompanyDataSource mapPostCompanyDataSource = new MapPostCompanyDataSource();
			MapPostCompany mapPostCompany = mapPostCompanyDataSource
					.get(Integer.parseInt(value));
			MapPostCompanyWrapper mapPostCompanyWrapper = MapPostCompanyAdapter
					.wrap(mapPostCompany);
			return mapPostCompanyWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((MapPostCompanyWrapper) object)
					.getMapPostCompany().getMapPostCompanyId());
		} else {
			return null;
		}
	}

}
