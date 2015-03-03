
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BonusDividend;
import com.nsmjsf.web.wrappers.BonusDividendWrapper;

public class BonusDividendAdapter {
private static final Log log = LogFactory
			.getLog(BonusDividendAdapter.class);
	
	public static List<BonusDividendWrapper> wrapAll(List<BonusDividend> bonusDividendList)
	{
		List<BonusDividendWrapper> bonusDividendWrapperList=new ArrayList<BonusDividendWrapper>();
		for(BonusDividend bonusDividend:bonusDividendList)
		{
			BonusDividendWrapper bonusDividendWrapper=new BonusDividendWrapper();
			bonusDividendWrapper.setBonusDividend(bonusDividend);
			bonusDividendWrapperList.add(bonusDividendWrapper);
		}
		return bonusDividendWrapperList;
		
	}
	
	public static BonusDividendWrapper wrap(BonusDividend bonusDividend)
	{
		BonusDividendWrapper bonusDividendWrapper=new BonusDividendWrapper();
		bonusDividendWrapper.setBonusDividend(bonusDividend);
		return bonusDividendWrapper;
		
	}

}

