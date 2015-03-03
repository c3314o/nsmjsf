package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.EnergyPriceAdapter;
import com.nsmjsf.web.datasources.EnergyPriceDataSource;
import com.nsmjsf.web.datamodels.EnergyPrice;
import com.nsmjsf.web.wrappers.EnergyPriceWrapper;


@FacesConverter("energyPriceWrapperConverter")
public class EnergyPriceWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(EnergyPriceWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			EnergyPriceDataSource energyPriceDataSource = new EnergyPriceDataSource();
			EnergyPrice energyPrice = energyPriceDataSource.get(Integer.parseInt(value));
			EnergyPriceWrapper energyPriceWrapper=EnergyPriceAdapter.wrap(energyPrice);
			return energyPriceWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((EnergyPriceWrapper) object).getEnergyPrice().getEnergyPriceId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

