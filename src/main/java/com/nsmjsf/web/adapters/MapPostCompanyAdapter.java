package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MapPostCompany;
import com.nsmjsf.web.wrappers.MapPostCompanyWrapper;

public class MapPostCompanyAdapter {
	private static final Log log = LogFactory
			.getLog(MapPostCompanyAdapter.class);

	public static List<MapPostCompanyWrapper> wrapAll(
			List<MapPostCompany> mapPostCompanyList) {
		List<MapPostCompanyWrapper> mapPostCompanyWrapperList = new ArrayList<MapPostCompanyWrapper>();
		for (MapPostCompany mapPostCompany : mapPostCompanyList) {
			MapPostCompanyWrapper mapPostCompanyWrapper = new MapPostCompanyWrapper();
			mapPostCompanyWrapper.setMapPostCompany(mapPostCompany);
			mapPostCompanyWrapperList.add(mapPostCompanyWrapper);
		}
		return mapPostCompanyWrapperList;

	}

	public static MapPostCompanyWrapper wrap(MapPostCompany mapPostCompany) {
		MapPostCompanyWrapper mapPostCompanyWrapper = new MapPostCompanyWrapper();
		mapPostCompanyWrapper.setMapPostCompany(mapPostCompany);
		return mapPostCompanyWrapper;

	}

}
