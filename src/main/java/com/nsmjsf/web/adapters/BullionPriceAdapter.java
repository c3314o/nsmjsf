
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BullionPrice;
import com.nsmjsf.web.wrappers.BullionPriceWrapper;

public class BullionPriceAdapter {
private static final Log log = LogFactory
			.getLog(BullionPriceAdapter.class);
	
	public static List<BullionPriceWrapper> wrapAll(List<BullionPrice> bullionPriceList)
	{
		List<BullionPriceWrapper> bullionPriceWrapperList=new ArrayList<BullionPriceWrapper>();
		for(BullionPrice bullionPrice:bullionPriceList)
		{
			BullionPriceWrapper bullionPriceWrapper=new BullionPriceWrapper();
			bullionPriceWrapper.setBullionPrice(bullionPrice);
			bullionPriceWrapperList.add(bullionPriceWrapper);
		}
		return bullionPriceWrapperList;
		
	}
	
	public static BullionPriceWrapper wrap(BullionPrice bullionPrice)
	{
		BullionPriceWrapper bullionPriceWrapper=new BullionPriceWrapper();
		bullionPriceWrapper.setBullionPrice(bullionPrice);
		return bullionPriceWrapper;
		
	}

}

