
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BonusDividendApproved;
import com.nsmjsf.web.wrappers.BonusDividendApprovedWrapper;

public class BonusDividendApprovedAdapter {
private static final Log log = LogFactory
			.getLog(BonusDividendApprovedAdapter.class);
	
	public static List<BonusDividendApprovedWrapper> wrapAll(List<BonusDividendApproved> bonusDividendApprovedList)
	{
		List<BonusDividendApprovedWrapper> bonusDividendApprovedWrapperList=new ArrayList<BonusDividendApprovedWrapper>();
		for(BonusDividendApproved bonusDividendApproved:bonusDividendApprovedList)
		{
			BonusDividendApprovedWrapper bonusDividendApprovedWrapper=new BonusDividendApprovedWrapper();
			bonusDividendApprovedWrapper.setBonusDividendApproved(bonusDividendApproved);
			bonusDividendApprovedWrapperList.add(bonusDividendApprovedWrapper);
		}
		return bonusDividendApprovedWrapperList;
		
	}
	
	public static BonusDividendApprovedWrapper wrap(BonusDividendApproved bonusDividendApproved)
	{
		BonusDividendApprovedWrapper bonusDividendApprovedWrapper=new BonusDividendApprovedWrapper();
		bonusDividendApprovedWrapper.setBonusDividendApproved(bonusDividendApproved);
		return bonusDividendApprovedWrapper;
		
	}

}

