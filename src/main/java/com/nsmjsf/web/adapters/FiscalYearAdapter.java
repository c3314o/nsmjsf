
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FiscalYear;
import com.nsmjsf.web.wrappers.FiscalYearWrapper;

public class FiscalYearAdapter {
private static final Log log = LogFactory
			.getLog(FiscalYearAdapter.class);
	
	public static List<FiscalYearWrapper> wrapAll(List<FiscalYear> fiscalYearList)
	{
		List<FiscalYearWrapper> fiscalYearWrapperList=new ArrayList<FiscalYearWrapper>();
		for(FiscalYear fiscalYear:fiscalYearList)
		{
			FiscalYearWrapper fiscalYearWrapper=new FiscalYearWrapper();
			fiscalYearWrapper.setFiscalYear(fiscalYear);
			fiscalYearWrapperList.add(fiscalYearWrapper);
		}
		return fiscalYearWrapperList;
		
	}
	
	public static FiscalYearWrapper wrap(FiscalYear fiscalYear)
	{
		FiscalYearWrapper fiscalYearWrapper=new FiscalYearWrapper();
		fiscalYearWrapper.setFiscalYear(fiscalYear);
		return fiscalYearWrapper;
		
	}

}

