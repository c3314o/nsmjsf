package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserWatchList;
import com.nsmjsf.web.wrappers.UserWatchListWrapper;

public class UserWatchListAdapter {
	private static final Log log = LogFactory
			.getLog(UserWatchListAdapter.class);

	public static List<UserWatchListWrapper> wrapAll(
			List<UserWatchList> userWatchListList) {
		List<UserWatchListWrapper> userWatchListWrapperList = new ArrayList<UserWatchListWrapper>();
		for (UserWatchList userWatchList : userWatchListList) {
			UserWatchListWrapper userWatchListWrapper = new UserWatchListWrapper();
			userWatchListWrapper.setUserWatchList(userWatchList);
			userWatchListWrapperList.add(userWatchListWrapper);
		}
		return userWatchListWrapperList;

	}

	public static UserWatchListWrapper wrap(UserWatchList userWatchList) {
		UserWatchListWrapper userWatchListWrapper = new UserWatchListWrapper();
		userWatchListWrapper.setUserWatchList(userWatchList);
		return userWatchListWrapper;

	}

}
