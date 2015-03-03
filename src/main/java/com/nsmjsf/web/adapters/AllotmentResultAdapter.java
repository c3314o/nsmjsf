
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.AllotmentResult;
import com.nsmjsf.web.wrappers.AllotmentResultWrapper;

public class AllotmentResultAdapter {
private static final Log log = LogFactory
			.getLog(AllotmentResultAdapter.class);
	
	public static List<AllotmentResultWrapper> wrapAll(List<AllotmentResult> allotmentResultList)
	{
		List<AllotmentResultWrapper> allotmentResultWrapperList=new ArrayList<AllotmentResultWrapper>();
		for(AllotmentResult allotmentResult:allotmentResultList)
		{
			AllotmentResultWrapper allotmentResultWrapper=new AllotmentResultWrapper();
			allotmentResultWrapper.setAllotmentResult(allotmentResult);
			allotmentResultWrapperList.add(allotmentResultWrapper);
		}
		return allotmentResultWrapperList;
		
	}
	
	public static AllotmentResultWrapper wrap(AllotmentResult allotmentResult)
	{
		AllotmentResultWrapper allotmentResultWrapper=new AllotmentResultWrapper();
		allotmentResultWrapper.setAllotmentResult(allotmentResult);
		return allotmentResultWrapper;
		
	}

}

