package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.UserAdapter;
import com.nsmjsf.web.datasources.UserDataSource;
import com.nsmjsf.web.datamodels.User;
import com.nsmjsf.web.wrappers.UserWrapper;

@FacesConverter("userWrapperConverter")
public class UserWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(UserWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserDataSource userDataSource = new UserDataSource();
			User user = userDataSource.get(Integer.parseInt(value));
			UserWrapper userWrapper = UserAdapter.wrap(user);
			return userWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserWrapper) object).getUser().getUserId());
		} else {
			return null;
		}
	}

}
