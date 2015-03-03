
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Quarter;
import com.nsmjsf.web.wrappers.QuarterWrapper;

public class QuarterAdapter {
private static final Log log = LogFactory
			.getLog(QuarterAdapter.class);
	
	public static List<QuarterWrapper> wrapAll(List<Quarter> quarterList)
	{
		List<QuarterWrapper> quarterWrapperList=new ArrayList<QuarterWrapper>();
		for(Quarter quarter:quarterList)
		{
			QuarterWrapper quarterWrapper=new QuarterWrapper();
			quarterWrapper.setQuarter(quarter);
			quarterWrapperList.add(quarterWrapper);
		}
		return quarterWrapperList;
		
	}
	
	public static QuarterWrapper wrap(Quarter quarter)
	{
		QuarterWrapper quarterWrapper=new QuarterWrapper();
		quarterWrapper.setQuarter(quarter);
		return quarterWrapper;
		
	}

}

