package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.UserEnergyAdapter;
import com.nsmjsf.web.datasources.UserEnergyDataSource;
import com.nsmjsf.web.datamodels.UserEnergy;
import com.nsmjsf.web.wrappers.UserEnergyWrapper;

@FacesConverter("userEnergyWrapperConverter")
public class UserEnergyWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(UserEnergyWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserEnergyDataSource userEnergyDataSource = new UserEnergyDataSource();
			UserEnergy userEnergy = userEnergyDataSource.get(Integer
					.parseInt(value));
			UserEnergyWrapper userEnergyWrapper = UserEnergyAdapter
					.wrap(userEnergy);
			return userEnergyWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserEnergyWrapper) object).getUserEnergy()
					.getUserEnergyId());
		} else {
			return null;
		}
	}

}
