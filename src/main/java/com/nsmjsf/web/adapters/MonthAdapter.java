
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Month;
import com.nsmjsf.web.wrappers.MonthWrapper;

public class MonthAdapter {
private static final Log log = LogFactory
			.getLog(MonthAdapter.class);
	
	public static List<MonthWrapper> wrapAll(List<Month> monthList)
	{
		List<MonthWrapper> monthWrapperList=new ArrayList<MonthWrapper>();
		for(Month month:monthList)
		{
			MonthWrapper monthWrapper=new MonthWrapper();
			monthWrapper.setMonth(month);
			monthWrapperList.add(monthWrapper);
		}
		return monthWrapperList;
		
	}
	
	public static MonthWrapper wrap(Month month)
	{
		MonthWrapper monthWrapper=new MonthWrapper();
		monthWrapper.setMonth(month);
		return monthWrapper;
		
	}

}

