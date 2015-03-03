
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.User;
import com.nsmjsf.web.wrappers.UserWrapper;

public class UserAdapter {
private static final Log log = LogFactory
			.getLog(UserAdapter.class);
	
	public static List<UserWrapper> wrapAll(List<User> userList)
	{
		List<UserWrapper> userWrapperList=new ArrayList<UserWrapper>();
		for(User user:userList)
		{
			UserWrapper userWrapper=new UserWrapper();
			userWrapper.setUser(user);
			userWrapperList.add(userWrapper);
		}
		return userWrapperList;
		
	}
	
	public static UserWrapper wrap(User user)
	{
		UserWrapper userWrapper=new UserWrapper();
		userWrapper.setUser(user);
		return userWrapper;
		
	}

}

