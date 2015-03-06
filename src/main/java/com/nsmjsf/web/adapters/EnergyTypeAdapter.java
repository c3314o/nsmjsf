package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.EnergyType;
import com.nsmjsf.web.wrappers.EnergyTypeWrapper;

public class EnergyTypeAdapter {
	private static final Log log = LogFactory.getLog(EnergyTypeAdapter.class);

	public static List<EnergyTypeWrapper> wrapAll(
			List<EnergyType> energyTypeList) {
		List<EnergyTypeWrapper> energyTypeWrapperList = new ArrayList<EnergyTypeWrapper>();
		for (EnergyType energyType : energyTypeList) {
			EnergyTypeWrapper energyTypeWrapper = new EnergyTypeWrapper();
			energyTypeWrapper.setEnergyType(energyType);
			energyTypeWrapperList.add(energyTypeWrapper);
		}
		return energyTypeWrapperList;

	}

	public static EnergyTypeWrapper wrap(EnergyType energyType) {
		EnergyTypeWrapper energyTypeWrapper = new EnergyTypeWrapper();
		energyTypeWrapper.setEnergyType(energyType);
		return energyTypeWrapper;

	}

}
