package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserBullion;
import com.nsmjsf.web.wrappers.UserBullionWrapper;

public class UserBullionAdapter {
	private static final Log log = LogFactory.getLog(UserBullionAdapter.class);

	public static List<UserBullionWrapper> wrapAll(
			List<UserBullion> userBullionList) {
		List<UserBullionWrapper> userBullionWrapperList = new ArrayList<UserBullionWrapper>();
		for (UserBullion userBullion : userBullionList) {
			UserBullionWrapper userBullionWrapper = new UserBullionWrapper();
			userBullionWrapper.setUserBullion(userBullion);
			userBullionWrapperList.add(userBullionWrapper);
		}
		return userBullionWrapperList;

	}

	public static UserBullionWrapper wrap(UserBullion userBullion) {
		UserBullionWrapper userBullionWrapper = new UserBullionWrapper();
		userBullionWrapper.setUserBullion(userBullion);
		return userBullionWrapper;

	}

}
