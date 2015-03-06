package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.UserBullionAdapter;
import com.nsmjsf.web.datasources.UserBullionDataSource;
import com.nsmjsf.web.datamodels.UserBullion;
import com.nsmjsf.web.wrappers.UserBullionWrapper;

@FacesConverter("userBullionWrapperConverter")
public class UserBullionWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(UserBullionWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserBullionDataSource userBullionDataSource = new UserBullionDataSource();
			UserBullion userBullion = userBullionDataSource.get(Integer
					.parseInt(value));
			UserBullionWrapper userBullionWrapper = UserBullionAdapter
					.wrap(userBullion);
			return userBullionWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserBullionWrapper) object)
					.getUserBullion().getUserBullionId());
		} else {
			return null;
		}
	}

}
