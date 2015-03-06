package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserPortfolio;
import com.nsmjsf.web.wrappers.UserPortfolioWrapper;

public class UserPortfolioAdapter {
	private static final Log log = LogFactory
			.getLog(UserPortfolioAdapter.class);

	public static List<UserPortfolioWrapper> wrapAll(
			List<UserPortfolio> userPortfolioList) {
		List<UserPortfolioWrapper> userPortfolioWrapperList = new ArrayList<UserPortfolioWrapper>();
		for (UserPortfolio userPortfolio : userPortfolioList) {
			UserPortfolioWrapper userPortfolioWrapper = new UserPortfolioWrapper();
			userPortfolioWrapper.setUserPortfolio(userPortfolio);
			userPortfolioWrapperList.add(userPortfolioWrapper);
		}
		return userPortfolioWrapperList;

	}

	public static UserPortfolioWrapper wrap(UserPortfolio userPortfolio) {
		UserPortfolioWrapper userPortfolioWrapper = new UserPortfolioWrapper();
		userPortfolioWrapper.setUserPortfolio(userPortfolio);
		return userPortfolioWrapper;

	}

}
