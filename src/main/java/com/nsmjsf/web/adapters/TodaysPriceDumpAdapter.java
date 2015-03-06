package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.TodaysPriceDump;
import com.nsmjsf.web.wrappers.TodaysPriceDumpWrapper;

public class TodaysPriceDumpAdapter {
	private static final Log log = LogFactory
			.getLog(TodaysPriceDumpAdapter.class);

	public static List<TodaysPriceDumpWrapper> wrapAll(
			List<TodaysPriceDump> todaysPriceDumpList) {
		List<TodaysPriceDumpWrapper> todaysPriceDumpWrapperList = new ArrayList<TodaysPriceDumpWrapper>();
		for (TodaysPriceDump todaysPriceDump : todaysPriceDumpList) {
			TodaysPriceDumpWrapper todaysPriceDumpWrapper = new TodaysPriceDumpWrapper();
			todaysPriceDumpWrapper.setTodaysPriceDump(todaysPriceDump);
			todaysPriceDumpWrapperList.add(todaysPriceDumpWrapper);
		}
		return todaysPriceDumpWrapperList;

	}

	public static TodaysPriceDumpWrapper wrap(TodaysPriceDump todaysPriceDump) {
		TodaysPriceDumpWrapper todaysPriceDumpWrapper = new TodaysPriceDumpWrapper();
		todaysPriceDumpWrapper.setTodaysPriceDump(todaysPriceDump);
		return todaysPriceDumpWrapper;

	}

}
