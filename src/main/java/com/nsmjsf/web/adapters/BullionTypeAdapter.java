
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BullionType;
import com.nsmjsf.web.wrappers.BullionTypeWrapper;

public class BullionTypeAdapter {
private static final Log log = LogFactory
			.getLog(BullionTypeAdapter.class);
	
	public static List<BullionTypeWrapper> wrapAll(List<BullionType> bullionTypeList)
	{
		List<BullionTypeWrapper> bullionTypeWrapperList=new ArrayList<BullionTypeWrapper>();
		for(BullionType bullionType:bullionTypeList)
		{
			BullionTypeWrapper bullionTypeWrapper=new BullionTypeWrapper();
			bullionTypeWrapper.setBullionType(bullionType);
			bullionTypeWrapperList.add(bullionTypeWrapper);
		}
		return bullionTypeWrapperList;
		
	}
	
	public static BullionTypeWrapper wrap(BullionType bullionType)
	{
		BullionTypeWrapper bullionTypeWrapper=new BullionTypeWrapper();
		bullionTypeWrapper.setBullionType(bullionType);
		return bullionTypeWrapper;
		
	}

}

