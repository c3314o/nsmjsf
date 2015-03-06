package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.NewHigh;
import com.nsmjsf.web.wrappers.NewHighWrapper;

public class NewHighAdapter {
	private static final Log log = LogFactory.getLog(NewHighAdapter.class);

	public static List<NewHighWrapper> wrapAll(List<NewHigh> newHighList) {
		List<NewHighWrapper> newHighWrapperList = new ArrayList<NewHighWrapper>();
		for (NewHigh newHigh : newHighList) {
			NewHighWrapper newHighWrapper = new NewHighWrapper();
			newHighWrapper.setNewHigh(newHigh);
			newHighWrapperList.add(newHighWrapper);
		}
		return newHighWrapperList;

	}

	public static NewHighWrapper wrap(NewHigh newHigh) {
		NewHighWrapper newHighWrapper = new NewHighWrapper();
		newHighWrapper.setNewHigh(newHigh);
		return newHighWrapper;

	}

}
