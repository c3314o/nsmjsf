package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BasePrice;
import com.nsmjsf.web.wrappers.BasePriceWrapper;

public class BasePriceAdapter {
	private static final Log log = LogFactory.getLog(BasePriceAdapter.class);

	public static List<BasePriceWrapper> wrapAll(List<BasePrice> basePriceList) {
		List<BasePriceWrapper> basePriceWrapperList = new ArrayList<BasePriceWrapper>();
		for (BasePrice basePrice : basePriceList) {
			BasePriceWrapper basePriceWrapper = new BasePriceWrapper();
			basePriceWrapper.setBasePrice(basePrice);
			basePriceWrapperList.add(basePriceWrapper);
		}
		return basePriceWrapperList;

	}

	public static BasePriceWrapper wrap(BasePrice basePrice) {
		BasePriceWrapper basePriceWrapper = new BasePriceWrapper();
		basePriceWrapper.setBasePrice(basePrice);
		return basePriceWrapper;

	}

}
