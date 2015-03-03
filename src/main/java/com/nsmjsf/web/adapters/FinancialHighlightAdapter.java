
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FinancialHighlight;
import com.nsmjsf.web.wrappers.FinancialHighlightWrapper;

public class FinancialHighlightAdapter {
private static final Log log = LogFactory
			.getLog(FinancialHighlightAdapter.class);
	
	public static List<FinancialHighlightWrapper> wrapAll(List<FinancialHighlight> financialHighlightList)
	{
		List<FinancialHighlightWrapper> financialHighlightWrapperList=new ArrayList<FinancialHighlightWrapper>();
		for(FinancialHighlight financialHighlight:financialHighlightList)
		{
			FinancialHighlightWrapper financialHighlightWrapper=new FinancialHighlightWrapper();
			financialHighlightWrapper.setFinancialHighlight(financialHighlight);
			financialHighlightWrapperList.add(financialHighlightWrapper);
		}
		return financialHighlightWrapperList;
		
	}
	
	public static FinancialHighlightWrapper wrap(FinancialHighlight financialHighlight)
	{
		FinancialHighlightWrapper financialHighlightWrapper=new FinancialHighlightWrapper();
		financialHighlightWrapper.setFinancialHighlight(financialHighlight);
		return financialHighlightWrapper;
		
	}

}

