package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.EnergyPrice;
import com.nsmjsf.web.wrappers.EnergyPriceWrapper;

public class EnergyPriceAdapter {
	private static final Log log = LogFactory.getLog(EnergyPriceAdapter.class);

	public static List<EnergyPriceWrapper> wrapAll(
			List<EnergyPrice> energyPriceList) {
		List<EnergyPriceWrapper> energyPriceWrapperList = new ArrayList<EnergyPriceWrapper>();
		for (EnergyPrice energyPrice : energyPriceList) {
			EnergyPriceWrapper energyPriceWrapper = new EnergyPriceWrapper();
			energyPriceWrapper.setEnergyPrice(energyPrice);
			energyPriceWrapperList.add(energyPriceWrapper);
		}
		return energyPriceWrapperList;

	}

	public static EnergyPriceWrapper wrap(EnergyPrice energyPrice) {
		EnergyPriceWrapper energyPriceWrapper = new EnergyPriceWrapper();
		energyPriceWrapper.setEnergyPrice(energyPrice);
		return energyPriceWrapper;

	}

}
