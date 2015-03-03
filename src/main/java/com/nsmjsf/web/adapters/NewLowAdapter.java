
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.NewLow;
import com.nsmjsf.web.wrappers.NewLowWrapper;

public class NewLowAdapter {
private static final Log log = LogFactory
			.getLog(NewLowAdapter.class);
	
	public static List<NewLowWrapper> wrapAll(List<NewLow> newLowList)
	{
		List<NewLowWrapper> newLowWrapperList=new ArrayList<NewLowWrapper>();
		for(NewLow newLow:newLowList)
		{
			NewLowWrapper newLowWrapper=new NewLowWrapper();
			newLowWrapper.setNewLow(newLow);
			newLowWrapperList.add(newLowWrapper);
		}
		return newLowWrapperList;
		
	}
	
	public static NewLowWrapper wrap(NewLow newLow)
	{
		NewLowWrapper newLowWrapper=new NewLowWrapper();
		newLowWrapper.setNewLow(newLow);
		return newLowWrapper;
		
	}

}

