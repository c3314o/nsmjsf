package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FloorsheetHeader;
import com.nsmjsf.web.wrappers.FloorsheetHeaderWrapper;

public class FloorsheetHeaderAdapter {
	private static final Log log = LogFactory
			.getLog(FloorsheetHeaderAdapter.class);

	public static List<FloorsheetHeaderWrapper> wrapAll(
			List<FloorsheetHeader> floorsheetHeaderList) {
		List<FloorsheetHeaderWrapper> floorsheetHeaderWrapperList = new ArrayList<FloorsheetHeaderWrapper>();
		for (FloorsheetHeader floorsheetHeader : floorsheetHeaderList) {
			FloorsheetHeaderWrapper floorsheetHeaderWrapper = new FloorsheetHeaderWrapper();
			floorsheetHeaderWrapper.setFloorsheetHeader(floorsheetHeader);
			floorsheetHeaderWrapperList.add(floorsheetHeaderWrapper);
		}
		return floorsheetHeaderWrapperList;

	}

	public static FloorsheetHeaderWrapper wrap(FloorsheetHeader floorsheetHeader) {
		FloorsheetHeaderWrapper floorsheetHeaderWrapper = new FloorsheetHeaderWrapper();
		floorsheetHeaderWrapper.setFloorsheetHeader(floorsheetHeader);
		return floorsheetHeaderWrapper;

	}

}
