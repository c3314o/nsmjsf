
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.SemiYearAverage;
import com.nsmjsf.web.wrappers.SemiYearAverageWrapper;

public class SemiYearAverageAdapter {
private static final Log log = LogFactory
			.getLog(SemiYearAverageAdapter.class);
	
	public static List<SemiYearAverageWrapper> wrapAll(List<SemiYearAverage> semiYearAverageList)
	{
		List<SemiYearAverageWrapper> semiYearAverageWrapperList=new ArrayList<SemiYearAverageWrapper>();
		for(SemiYearAverage semiYearAverage:semiYearAverageList)
		{
			SemiYearAverageWrapper semiYearAverageWrapper=new SemiYearAverageWrapper();
			semiYearAverageWrapper.setSemiYearAverage(semiYearAverage);
			semiYearAverageWrapperList.add(semiYearAverageWrapper);
		}
		return semiYearAverageWrapperList;
		
	}
	
	public static SemiYearAverageWrapper wrap(SemiYearAverage semiYearAverage)
	{
		SemiYearAverageWrapper semiYearAverageWrapper=new SemiYearAverageWrapper();
		semiYearAverageWrapper.setSemiYearAverage(semiYearAverage);
		return semiYearAverageWrapper;
		
	}

}

