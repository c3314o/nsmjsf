package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserStockSales;
import com.nsmjsf.web.wrappers.UserStockSalesWrapper;

public class UserStockSalesAdapter {
	private static final Log log = LogFactory
			.getLog(UserStockSalesAdapter.class);

	public static List<UserStockSalesWrapper> wrapAll(
			List<UserStockSales> userStockSalesList) {
		List<UserStockSalesWrapper> userStockSalesWrapperList = new ArrayList<UserStockSalesWrapper>();
		for (UserStockSales userStockSales : userStockSalesList) {
			UserStockSalesWrapper userStockSalesWrapper = new UserStockSalesWrapper();
			userStockSalesWrapper.setUserStockSales(userStockSales);
			userStockSalesWrapperList.add(userStockSalesWrapper);
		}
		return userStockSalesWrapperList;

	}

	public static UserStockSalesWrapper wrap(UserStockSales userStockSales) {
		UserStockSalesWrapper userStockSalesWrapper = new UserStockSalesWrapper();
		userStockSalesWrapper.setUserStockSales(userStockSales);
		return userStockSalesWrapper;

	}

}
