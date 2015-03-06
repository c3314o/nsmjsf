package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BodType;
import com.nsmjsf.web.wrappers.BodTypeWrapper;

public class BodTypeAdapter {
	private static final Log log = LogFactory.getLog(BodTypeAdapter.class);

	public static List<BodTypeWrapper> wrapAll(List<BodType> bodTypeList) {
		List<BodTypeWrapper> bodTypeWrapperList = new ArrayList<BodTypeWrapper>();
		for (BodType bodType : bodTypeList) {
			BodTypeWrapper bodTypeWrapper = new BodTypeWrapper();
			bodTypeWrapper.setBodType(bodType);
			bodTypeWrapperList.add(bodTypeWrapper);
		}
		return bodTypeWrapperList;

	}

	public static BodTypeWrapper wrap(BodType bodType) {
		BodTypeWrapper bodTypeWrapper = new BodTypeWrapper();
		bodTypeWrapper.setBodType(bodType);
		return bodTypeWrapper;

	}

}
