
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CurrencyRate;
import com.nsmjsf.web.wrappers.CurrencyRateWrapper;

public class CurrencyRateAdapter {
private static final Log log = LogFactory
			.getLog(CurrencyRateAdapter.class);
	
	public static List<CurrencyRateWrapper> wrapAll(List<CurrencyRate> currencyRateList)
	{
		List<CurrencyRateWrapper> currencyRateWrapperList=new ArrayList<CurrencyRateWrapper>();
		for(CurrencyRate currencyRate:currencyRateList)
		{
			CurrencyRateWrapper currencyRateWrapper=new CurrencyRateWrapper();
			currencyRateWrapper.setCurrencyRate(currencyRate);
			currencyRateWrapperList.add(currencyRateWrapper);
		}
		return currencyRateWrapperList;
		
	}
	
	public static CurrencyRateWrapper wrap(CurrencyRate currencyRate)
	{
		CurrencyRateWrapper currencyRateWrapper=new CurrencyRateWrapper();
		currencyRateWrapper.setCurrencyRate(currencyRate);
		return currencyRateWrapper;
		
	}

}

