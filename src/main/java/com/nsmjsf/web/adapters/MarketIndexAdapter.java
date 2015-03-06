package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MarketIndex;
import com.nsmjsf.web.wrappers.MarketIndexWrapper;

public class MarketIndexAdapter {
	private static final Log log = LogFactory.getLog(MarketIndexAdapter.class);

	public static List<MarketIndexWrapper> wrapAll(
			List<MarketIndex> marketIndexList) {
		List<MarketIndexWrapper> marketIndexWrapperList = new ArrayList<MarketIndexWrapper>();
		for (MarketIndex marketIndex : marketIndexList) {
			MarketIndexWrapper marketIndexWrapper = new MarketIndexWrapper();
			marketIndexWrapper.setMarketIndex(marketIndex);
			marketIndexWrapperList.add(marketIndexWrapper);
		}
		return marketIndexWrapperList;

	}

	public static MarketIndexWrapper wrap(MarketIndex marketIndex) {
		MarketIndexWrapper marketIndexWrapper = new MarketIndexWrapper();
		marketIndexWrapper.setMarketIndex(marketIndex);
		return marketIndexWrapper;

	}

}
