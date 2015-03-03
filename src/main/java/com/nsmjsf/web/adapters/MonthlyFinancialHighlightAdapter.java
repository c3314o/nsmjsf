
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;
import com.nsmjsf.web.wrappers.MonthlyFinancialHighlightWrapper;

public class MonthlyFinancialHighlightAdapter {
private static final Log log = LogFactory
			.getLog(MonthlyFinancialHighlightAdapter.class);
	
	public static List<MonthlyFinancialHighlightWrapper> wrapAll(List<MonthlyFinancialHighlight> monthlyFinancialHighlightList)
	{
		List<MonthlyFinancialHighlightWrapper> monthlyFinancialHighlightWrapperList=new ArrayList<MonthlyFinancialHighlightWrapper>();
		for(MonthlyFinancialHighlight monthlyFinancialHighlight:monthlyFinancialHighlightList)
		{
			MonthlyFinancialHighlightWrapper monthlyFinancialHighlightWrapper=new MonthlyFinancialHighlightWrapper();
			monthlyFinancialHighlightWrapper.setMonthlyFinancialHighlight(monthlyFinancialHighlight);
			monthlyFinancialHighlightWrapperList.add(monthlyFinancialHighlightWrapper);
		}
		return monthlyFinancialHighlightWrapperList;
		
	}
	
	public static MonthlyFinancialHighlightWrapper wrap(MonthlyFinancialHighlight monthlyFinancialHighlight)
	{
		MonthlyFinancialHighlightWrapper monthlyFinancialHighlightWrapper=new MonthlyFinancialHighlightWrapper();
		monthlyFinancialHighlightWrapper.setMonthlyFinancialHighlight(monthlyFinancialHighlight);
		return monthlyFinancialHighlightWrapper;
		
	}

}

