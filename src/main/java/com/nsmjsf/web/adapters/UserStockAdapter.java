
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserStock;
import com.nsmjsf.web.wrappers.UserStockWrapper;

public class UserStockAdapter {
private static final Log log = LogFactory
			.getLog(UserStockAdapter.class);
	
	public static List<UserStockWrapper> wrapAll(List<UserStock> userStockList)
	{
		List<UserStockWrapper> userStockWrapperList=new ArrayList<UserStockWrapper>();
		for(UserStock userStock:userStockList)
		{
			UserStockWrapper userStockWrapper=new UserStockWrapper();
			userStockWrapper.setUserStock(userStock);
			userStockWrapperList.add(userStockWrapper);
		}
		return userStockWrapperList;
		
	}
	
	public static UserStockWrapper wrap(UserStock userStock)
	{
		UserStockWrapper userStockWrapper=new UserStockWrapper();
		userStockWrapper.setUserStock(userStock);
		return userStockWrapper;
		
	}

}

