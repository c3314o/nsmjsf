package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MapPostPost;
import com.nsmjsf.web.wrappers.MapPostPostWrapper;

public class MapPostPostAdapter {
	private static final Log log = LogFactory.getLog(MapPostPostAdapter.class);

	public static List<MapPostPostWrapper> wrapAll(
			List<MapPostPost> mapPostPostList) {
		List<MapPostPostWrapper> mapPostPostWrapperList = new ArrayList<MapPostPostWrapper>();
		for (MapPostPost mapPostPost : mapPostPostList) {
			MapPostPostWrapper mapPostPostWrapper = new MapPostPostWrapper();
			mapPostPostWrapper.setMapPostPost(mapPostPost);
			mapPostPostWrapperList.add(mapPostPostWrapper);
		}
		return mapPostPostWrapperList;

	}

	public static MapPostPostWrapper wrap(MapPostPost mapPostPost) {
		MapPostPostWrapper mapPostPostWrapper = new MapPostPostWrapper();
		mapPostPostWrapper.setMapPostPost(mapPostPost);
		return mapPostPostWrapper;

	}

}
