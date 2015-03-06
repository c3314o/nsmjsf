package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Bod;
import com.nsmjsf.web.wrappers.BodWrapper;

public class BodAdapter {
	private static final Log log = LogFactory.getLog(BodAdapter.class);

	public static List<BodWrapper> wrapAll(List<Bod> bodList) {
		List<BodWrapper> bodWrapperList = new ArrayList<BodWrapper>();
		for (Bod bod : bodList) {
			BodWrapper bodWrapper = new BodWrapper();
			bodWrapper.setBod(bod);
			bodWrapperList.add(bodWrapper);
		}
		return bodWrapperList;

	}

	public static BodWrapper wrap(Bod bod) {
		BodWrapper bodWrapper = new BodWrapper();
		bodWrapper.setBod(bod);
		return bodWrapper;

	}

}
