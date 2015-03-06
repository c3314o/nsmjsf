package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.UserInfoAdapter;
import com.nsmjsf.web.datasources.UserInfoDataSource;
import com.nsmjsf.web.datamodels.UserInfo;
import com.nsmjsf.web.wrappers.UserInfoWrapper;

@FacesConverter("userInfoWrapperConverter")
public class UserInfoWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(UserInfoWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserInfoDataSource userInfoDataSource = new UserInfoDataSource();
			UserInfo userInfo = userInfoDataSource.get(Integer.parseInt(value));
			UserInfoWrapper userInfoWrapper = UserInfoAdapter.wrap(userInfo);
			return userInfoWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserInfoWrapper) object).getUserInfo()
					.getUserInfoId());
		} else {
			return null;
		}
	}

}
