
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FinancialReport;
import com.nsmjsf.web.wrappers.FinancialReportWrapper;

public class FinancialReportAdapter {
private static final Log log = LogFactory
			.getLog(FinancialReportAdapter.class);
	
	public static List<FinancialReportWrapper> wrapAll(List<FinancialReport> financialReportList)
	{
		List<FinancialReportWrapper> financialReportWrapperList=new ArrayList<FinancialReportWrapper>();
		for(FinancialReport financialReport:financialReportList)
		{
			FinancialReportWrapper financialReportWrapper=new FinancialReportWrapper();
			financialReportWrapper.setFinancialReport(financialReport);
			financialReportWrapperList.add(financialReportWrapper);
		}
		return financialReportWrapperList;
		
	}
	
	public static FinancialReportWrapper wrap(FinancialReport financialReport)
	{
		FinancialReportWrapper financialReportWrapper=new FinancialReportWrapper();
		financialReportWrapper.setFinancialReport(financialReport);
		return financialReportWrapper;
		
	}

}

