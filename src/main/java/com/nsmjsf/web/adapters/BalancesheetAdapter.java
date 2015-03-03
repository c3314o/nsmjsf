
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Balancesheet;
import com.nsmjsf.web.wrappers.BalancesheetWrapper;

public class BalancesheetAdapter {
private static final Log log = LogFactory
			.getLog(BalancesheetAdapter.class);
	
	public static List<BalancesheetWrapper> wrapAll(List<Balancesheet> balancesheetList)
	{
		List<BalancesheetWrapper> balancesheetWrapperList=new ArrayList<BalancesheetWrapper>();
		for(Balancesheet balancesheet:balancesheetList)
		{
			BalancesheetWrapper balancesheetWrapper=new BalancesheetWrapper();
			balancesheetWrapper.setBalancesheet(balancesheet);
			balancesheetWrapperList.add(balancesheetWrapper);
		}
		return balancesheetWrapperList;
		
	}
	
	public static BalancesheetWrapper wrap(Balancesheet balancesheet)
	{
		BalancesheetWrapper balancesheetWrapper=new BalancesheetWrapper();
		balancesheetWrapper.setBalancesheet(balancesheet);
		return balancesheetWrapper;
		
	}

}

