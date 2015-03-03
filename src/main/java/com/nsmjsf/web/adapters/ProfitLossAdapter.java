
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.ProfitLoss;
import com.nsmjsf.web.wrappers.ProfitLossWrapper;

public class ProfitLossAdapter {
private static final Log log = LogFactory
			.getLog(ProfitLossAdapter.class);
	
	public static List<ProfitLossWrapper> wrapAll(List<ProfitLoss> profitLossList)
	{
		List<ProfitLossWrapper> profitLossWrapperList=new ArrayList<ProfitLossWrapper>();
		for(ProfitLoss profitLoss:profitLossList)
		{
			ProfitLossWrapper profitLossWrapper=new ProfitLossWrapper();
			profitLossWrapper.setProfitLoss(profitLoss);
			profitLossWrapperList.add(profitLossWrapper);
		}
		return profitLossWrapperList;
		
	}
	
	public static ProfitLossWrapper wrap(ProfitLoss profitLoss)
	{
		ProfitLossWrapper profitLossWrapper=new ProfitLossWrapper();
		profitLossWrapper.setProfitLoss(profitLoss);
		return profitLossWrapper;
		
	}

}

