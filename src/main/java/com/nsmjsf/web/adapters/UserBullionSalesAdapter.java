package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserBullionSales;
import com.nsmjsf.web.wrappers.UserBullionSalesWrapper;

public class UserBullionSalesAdapter {
	private static final Log log = LogFactory
			.getLog(UserBullionSalesAdapter.class);

	public static List<UserBullionSalesWrapper> wrapAll(
			List<UserBullionSales> userBullionSalesList) {
		List<UserBullionSalesWrapper> userBullionSalesWrapperList = new ArrayList<UserBullionSalesWrapper>();
		for (UserBullionSales userBullionSales : userBullionSalesList) {
			UserBullionSalesWrapper userBullionSalesWrapper = new UserBullionSalesWrapper();
			userBullionSalesWrapper.setUserBullionSales(userBullionSales);
			userBullionSalesWrapperList.add(userBullionSalesWrapper);
		}
		return userBullionSalesWrapperList;

	}

	public static UserBullionSalesWrapper wrap(UserBullionSales userBullionSales) {
		UserBullionSalesWrapper userBullionSalesWrapper = new UserBullionSalesWrapper();
		userBullionSalesWrapper.setUserBullionSales(userBullionSales);
		return userBullionSalesWrapper;

	}

}
