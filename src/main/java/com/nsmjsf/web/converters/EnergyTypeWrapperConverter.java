package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.EnergyTypeAdapter;
import com.nsmjsf.web.datasources.EnergyTypeDataSource;
import com.nsmjsf.web.datamodels.EnergyType;
import com.nsmjsf.web.wrappers.EnergyTypeWrapper;


@FacesConverter("energyTypeWrapperConverter")
public class EnergyTypeWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(EnergyTypeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			EnergyTypeDataSource energyTypeDataSource = new EnergyTypeDataSource();
			EnergyType energyType = energyTypeDataSource.get(Integer.parseInt(value));
			EnergyTypeWrapper energyTypeWrapper=EnergyTypeAdapter.wrap(energyType);
			return energyTypeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((EnergyTypeWrapper) object).getEnergyType().getEnergyTypeId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

