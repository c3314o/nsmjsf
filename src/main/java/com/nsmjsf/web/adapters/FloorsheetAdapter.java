package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Floorsheet;
import com.nsmjsf.web.wrappers.FloorsheetWrapper;

public class FloorsheetAdapter {
	private static final Log log = LogFactory.getLog(FloorsheetAdapter.class);

	public static List<FloorsheetWrapper> wrapAll(
			List<Floorsheet> floorsheetList) {
		List<FloorsheetWrapper> floorsheetWrapperList = new ArrayList<FloorsheetWrapper>();
		for (Floorsheet floorsheet : floorsheetList) {
			FloorsheetWrapper floorsheetWrapper = new FloorsheetWrapper();
			floorsheetWrapper.setFloorsheet(floorsheet);
			floorsheetWrapperList.add(floorsheetWrapper);
		}
		return floorsheetWrapperList;

	}

	public static FloorsheetWrapper wrap(Floorsheet floorsheet) {
		FloorsheetWrapper floorsheetWrapper = new FloorsheetWrapper();
		floorsheetWrapper.setFloorsheet(floorsheet);
		return floorsheetWrapper;

	}

}
