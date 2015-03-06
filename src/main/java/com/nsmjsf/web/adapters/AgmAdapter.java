package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Agm;
import com.nsmjsf.web.wrappers.AgmWrapper;

public class AgmAdapter {
	private static final Log log = LogFactory.getLog(AgmAdapter.class);

	public static List<AgmWrapper> wrapAll(List<Agm> agmList) {
		List<AgmWrapper> agmWrapperList = new ArrayList<AgmWrapper>();
		for (Agm agm : agmList) {
			AgmWrapper agmWrapper = new AgmWrapper();
			agmWrapper.setAgm(agm);
			agmWrapperList.add(agmWrapper);
		}
		return agmWrapperList;

	}

	public static AgmWrapper wrap(Agm agm) {
		AgmWrapper agmWrapper = new AgmWrapper();
		agmWrapper.setAgm(agm);
		return agmWrapper;

	}

}
