package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserEnergy;
import com.nsmjsf.web.wrappers.UserEnergyWrapper;

public class UserEnergyAdapter {
	private static final Log log = LogFactory.getLog(UserEnergyAdapter.class);

	public static List<UserEnergyWrapper> wrapAll(
			List<UserEnergy> userEnergyList) {
		List<UserEnergyWrapper> userEnergyWrapperList = new ArrayList<UserEnergyWrapper>();
		for (UserEnergy userEnergy : userEnergyList) {
			UserEnergyWrapper userEnergyWrapper = new UserEnergyWrapper();
			userEnergyWrapper.setUserEnergy(userEnergy);
			userEnergyWrapperList.add(userEnergyWrapper);
		}
		return userEnergyWrapperList;

	}

	public static UserEnergyWrapper wrap(UserEnergy userEnergy) {
		UserEnergyWrapper userEnergyWrapper = new UserEnergyWrapper();
		userEnergyWrapper.setUserEnergy(userEnergy);
		return userEnergyWrapper;

	}

}
