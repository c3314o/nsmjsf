package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserType;
import com.nsmjsf.web.wrappers.UserTypeWrapper;

public class UserTypeAdapter {
	private static final Log log = LogFactory.getLog(UserTypeAdapter.class);

	public static List<UserTypeWrapper> wrapAll(List<UserType> userTypeList) {
		List<UserTypeWrapper> userTypeWrapperList = new ArrayList<UserTypeWrapper>();
		for (UserType userType : userTypeList) {
			UserTypeWrapper userTypeWrapper = new UserTypeWrapper();
			userTypeWrapper.setUserType(userType);
			userTypeWrapperList.add(userTypeWrapper);
		}
		return userTypeWrapperList;

	}

	public static UserTypeWrapper wrap(UserType userType) {
		UserTypeWrapper userTypeWrapper = new UserTypeWrapper();
		userTypeWrapper.setUserType(userType);
		return userTypeWrapper;

	}

}
