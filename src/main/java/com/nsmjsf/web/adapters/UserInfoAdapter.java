package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserInfo;
import com.nsmjsf.web.wrappers.UserInfoWrapper;

public class UserInfoAdapter {
	private static final Log log = LogFactory.getLog(UserInfoAdapter.class);

	public static List<UserInfoWrapper> wrapAll(List<UserInfo> userInfoList) {
		List<UserInfoWrapper> userInfoWrapperList = new ArrayList<UserInfoWrapper>();
		for (UserInfo userInfo : userInfoList) {
			UserInfoWrapper userInfoWrapper = new UserInfoWrapper();
			userInfoWrapper.setUserInfo(userInfo);
			userInfoWrapperList.add(userInfoWrapper);
		}
		return userInfoWrapperList;

	}

	public static UserInfoWrapper wrap(UserInfo userInfo) {
		UserInfoWrapper userInfoWrapper = new UserInfoWrapper();
		userInfoWrapper.setUserInfo(userInfo);
		return userInfoWrapper;

	}

}
