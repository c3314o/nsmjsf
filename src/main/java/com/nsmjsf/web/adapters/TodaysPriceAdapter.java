
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.TodaysPrice;
import com.nsmjsf.web.wrappers.TodaysPriceWrapper;

public class TodaysPriceAdapter {
private static final Log log = LogFactory
			.getLog(TodaysPriceAdapter.class);
	
	public static List<TodaysPriceWrapper> wrapAll(List<TodaysPrice> todaysPriceList)
	{
		List<TodaysPriceWrapper> todaysPriceWrapperList=new ArrayList<TodaysPriceWrapper>();
		for(TodaysPrice todaysPrice:todaysPriceList)
		{
			TodaysPriceWrapper todaysPriceWrapper=new TodaysPriceWrapper();
			todaysPriceWrapper.setTodaysPrice(todaysPrice);
			todaysPriceWrapperList.add(todaysPriceWrapper);
		}
		return todaysPriceWrapperList;
		
	}
	
	public static TodaysPriceWrapper wrap(TodaysPrice todaysPrice)
	{
		TodaysPriceWrapper todaysPriceWrapper=new TodaysPriceWrapper();
		todaysPriceWrapper.setTodaysPrice(todaysPrice);
		return todaysPriceWrapper;
		
	}

}

