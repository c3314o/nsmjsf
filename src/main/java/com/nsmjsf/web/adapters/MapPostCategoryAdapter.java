package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MapPostCategory;
import com.nsmjsf.web.wrappers.MapPostCategoryWrapper;

public class MapPostCategoryAdapter {
	private static final Log log = LogFactory
			.getLog(MapPostCategoryAdapter.class);

	public static List<MapPostCategoryWrapper> wrapAll(
			List<MapPostCategory> mapPostCategoryList) {
		List<MapPostCategoryWrapper> mapPostCategoryWrapperList = new ArrayList<MapPostCategoryWrapper>();
		for (MapPostCategory mapPostCategory : mapPostCategoryList) {
			MapPostCategoryWrapper mapPostCategoryWrapper = new MapPostCategoryWrapper();
			mapPostCategoryWrapper.setMapPostCategory(mapPostCategory);
			mapPostCategoryWrapperList.add(mapPostCategoryWrapper);
		}
		return mapPostCategoryWrapperList;

	}

	public static MapPostCategoryWrapper wrap(MapPostCategory mapPostCategory) {
		MapPostCategoryWrapper mapPostCategoryWrapper = new MapPostCategoryWrapper();
		mapPostCategoryWrapper.setMapPostCategory(mapPostCategory);
		return mapPostCategoryWrapper;

	}

}
