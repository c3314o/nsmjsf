package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.LatestPrice;
import com.nsmjsf.web.wrappers.LatestPriceWrapper;

public class LatestPriceAdapter {
	private static final Log log = LogFactory.getLog(LatestPriceAdapter.class);

	public static List<LatestPriceWrapper> wrapAll(
			List<LatestPrice> latestPriceList) {
		List<LatestPriceWrapper> latestPriceWrapperList = new ArrayList<LatestPriceWrapper>();
		for (LatestPrice latestPrice : latestPriceList) {
			LatestPriceWrapper latestPriceWrapper = new LatestPriceWrapper();
			latestPriceWrapper.setLatestPrice(latestPrice);
			latestPriceWrapperList.add(latestPriceWrapper);
		}
		return latestPriceWrapperList;

	}

	public static LatestPriceWrapper wrap(LatestPrice latestPrice) {
		LatestPriceWrapper latestPriceWrapper = new LatestPriceWrapper();
		latestPriceWrapper.setLatestPrice(latestPrice);
		return latestPriceWrapper;

	}

}
