
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CurrencyType;
import com.nsmjsf.web.wrappers.CurrencyTypeWrapper;

public class CurrencyTypeAdapter {
private static final Log log = LogFactory
			.getLog(CurrencyTypeAdapter.class);
	
	public static List<CurrencyTypeWrapper> wrapAll(List<CurrencyType> currencyTypeList)
	{
		List<CurrencyTypeWrapper> currencyTypeWrapperList=new ArrayList<CurrencyTypeWrapper>();
		for(CurrencyType currencyType:currencyTypeList)
		{
			CurrencyTypeWrapper currencyTypeWrapper=new CurrencyTypeWrapper();
			currencyTypeWrapper.setCurrencyType(currencyType);
			currencyTypeWrapperList.add(currencyTypeWrapper);
		}
		return currencyTypeWrapperList;
		
	}
	
	public static CurrencyTypeWrapper wrap(CurrencyType currencyType)
	{
		CurrencyTypeWrapper currencyTypeWrapper=new CurrencyTypeWrapper();
		currencyTypeWrapper.setCurrencyType(currencyType);
		return currencyTypeWrapper;
		
	}

}

