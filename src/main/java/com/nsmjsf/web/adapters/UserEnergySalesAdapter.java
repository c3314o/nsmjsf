
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserEnergySales;
import com.nsmjsf.web.wrappers.UserEnergySalesWrapper;

public class UserEnergySalesAdapter {
private static final Log log = LogFactory
			.getLog(UserEnergySalesAdapter.class);
	
	public static List<UserEnergySalesWrapper> wrapAll(List<UserEnergySales> userEnergySalesList)
	{
		List<UserEnergySalesWrapper> userEnergySalesWrapperList=new ArrayList<UserEnergySalesWrapper>();
		for(UserEnergySales userEnergySales:userEnergySalesList)
		{
			UserEnergySalesWrapper userEnergySalesWrapper=new UserEnergySalesWrapper();
			userEnergySalesWrapper.setUserEnergySales(userEnergySales);
			userEnergySalesWrapperList.add(userEnergySalesWrapper);
		}
		return userEnergySalesWrapperList;
		
	}
	
	public static UserEnergySalesWrapper wrap(UserEnergySales userEnergySales)
	{
		UserEnergySalesWrapper userEnergySalesWrapper=new UserEnergySalesWrapper();
		userEnergySalesWrapper.setUserEnergySales(userEnergySales);
		return userEnergySalesWrapper;
		
	}

}

