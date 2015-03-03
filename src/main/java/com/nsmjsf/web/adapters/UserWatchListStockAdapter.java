
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserWatchListStock;
import com.nsmjsf.web.wrappers.UserWatchListStockWrapper;

public class UserWatchListStockAdapter {
private static final Log log = LogFactory
			.getLog(UserWatchListStockAdapter.class);
	
	public static List<UserWatchListStockWrapper> wrapAll(List<UserWatchListStock> userWatchListStockList)
	{
		List<UserWatchListStockWrapper> userWatchListStockWrapperList=new ArrayList<UserWatchListStockWrapper>();
		for(UserWatchListStock userWatchListStock:userWatchListStockList)
		{
			UserWatchListStockWrapper userWatchListStockWrapper=new UserWatchListStockWrapper();
			userWatchListStockWrapper.setUserWatchListStock(userWatchListStock);
			userWatchListStockWrapperList.add(userWatchListStockWrapper);
		}
		return userWatchListStockWrapperList;
		
	}
	
	public static UserWatchListStockWrapper wrap(UserWatchListStock userWatchListStock)
	{
		UserWatchListStockWrapper userWatchListStockWrapper=new UserWatchListStockWrapper();
		userWatchListStockWrapper.setUserWatchListStock(userWatchListStock);
		return userWatchListStockWrapper;
		
	}

}

